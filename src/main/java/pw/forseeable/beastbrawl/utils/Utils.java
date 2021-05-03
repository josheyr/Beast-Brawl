package pw.forseeable.beastbrawl.utils;

import com.google.common.base.Charsets;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.managers.TogglesManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Ur boi forseeable made this class #gang
 */
public class Utils {

    public static ItemStack soup(){
        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
            ItemMeta soupM = soup.getItemMeta();
            soupM.setDisplayName("§7Soup");
            soup.setItemMeta(soupM);
            return soup;
    }

    public static ItemStack potion(){
        ItemStack potion = new ItemStack(Material.POTION);
        Potion pot = new Potion(1);
        pot.setSplash(true);
        pot.setType(PotionType.INSTANT_HEAL);
        pot.apply(potion);
        potion.setAmount(1);
        PotionMeta potionM = (PotionMeta)Bukkit.getServer().getItemFactory().getItemMeta(Material.POTION);
        PotionEffect effect = new PotionEffect(PotionEffectType.HEAL, 0, 1);
        potionM.setMainEffect(effect.getType());
        potionM.addCustomEffect(effect, true);
        potionM.setDisplayName("§7Potion");
        potion.setItemMeta(potionM);

        return potion;
    }

    public static int getProtocolVersion(Player player) throws Exception {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        Class craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
        Class entityPlayerClass = Class.forName("net.minecraft.server." + version + ".EntityPlayer");
        Class playerConnectionClass = Class.forName("net.minecraft.server." + version + ".PlayerConnection");
        Class networkManagerClass = Class.forName("net.minecraft.server." + version + ".NetworkManager");
    
        Object entityPlayer;
        {
            Method method = craftPlayerClass.getDeclaredMethod("getHandle");
            method.setAccessible(true);
            entityPlayer = method.invoke(player);
        }
    
        Object playerConnection;
        {
            Field field = entityPlayerClass.getDeclaredField("playerConnection");
            field.setAccessible(true);
            playerConnection = field.get(entityPlayer);
        }
    
        Object networkManager;
        {
            Field field = playerConnectionClass.getDeclaredField("networkManager");
            field.setAccessible(true);
            networkManager = field.get(playerConnection);
        }
    
        int ver;
        {
            Method method = networkManagerClass.getDeclaredMethod("getVersion");
            method.setAccessible(true);
            ver = (int) method.invoke(networkManager);
        }
    
        return ver;
    }

    public static void sendCenteredMessage(Player p, String message) {
        int CENTER_PX = 154;
        int MAX_PX = 250;

        message = ChatColor.translateAlternateColorCodes('&', message);
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;
        int charIndex = 0;
        int lastSpaceIndex = 0;
        String toSendAfter = null;
        String recentColorCode = "";
        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode == true){
                previousCode = false;
                recentColorCode = "§" + c;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else if(c == ' ') lastSpaceIndex = charIndex;
            else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
            if(messagePxSize >= MAX_PX){
                toSendAfter = recentColorCode + message.substring(lastSpaceIndex + 1, message.length());
                message = message.substring(0, lastSpaceIndex + 1);
                break;
            }
            charIndex++;
        }
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        p.sendMessage(sb.toString() + message);
        if (toSendAfter!=null) sendCenteredMessage(p, toSendAfter);
    }

    public static String IntegerToRomanNumeral(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }    
        return s;
    }

    public static Location getRandomLocation() {
        List<Location> locations = Arrays.asList(
                new Location(Bukkit.getWorld("world"), 185.815, 64.000000, -3.692, (float) 90.0, (float) 0.0),
                new Location(Bukkit.getWorld("world"), 161.995, 64.000000, 24.536, (float) 135.0, (float) 0.0),
                new Location(Bukkit.getWorld("world"), 119.944, 63.000000, -27.830, (float) 0.0, (float) 0.0),
                new Location(Bukkit.getWorld("world"), 146.061, 62.0, -162.513, (float) -90.0, (float)0.0),
                new Location(Bukkit.getWorld("world"), 119.614, 72.0, -38.716, (float) -135.0, (float)0.0),
                new Location(Bukkit.getWorld("world"), 87.340, 63.00000, -174.324, (float) -45.0, (float) 0.0),
                new Location(Bukkit.getWorld("world"), 179.949, 67.50000, -81.106, (float) 0.0, (float) 0.0),
                new Location(Bukkit.getWorld("world"), 106.545, 64.000000, -64.576, (float) -180.0, (float) -0.0)
        );

        return locations.get(new Random().nextInt(locations.size()));
    }

    public static ItemStack getKitSelector() {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§4§lKit Selector");
        meta.setLore(Arrays.asList("§cClick§7 to select a kit and enter the arena!"));
        item.setItemMeta(meta);
        return item;
    }


    private static final UUID invalidUserUUID = UUID.nameUUIDFromBytes("InvalidUsername".getBytes(Charsets.UTF_8));
    private static Class<?> gameProfileClass;
    private static Constructor<?> gameProfileConstructor;
    private static Constructor<?> craftOfflinePlayerConstructor;

    public static OfflinePlayer getOfflinePlayerSkipLookup(String name) {
        try {
            if (gameProfileConstructor == null) {
                try { // 1.7
                    gameProfileClass = Class.forName("net.minecraft.util.com.mojang.authlib.GameProfile");
                } catch (ClassNotFoundException e) { // 1.8
                    gameProfileClass = Class.forName("com.mojang.authlib.GameProfile");
                }
                gameProfileConstructor = gameProfileClass.getDeclaredConstructor(UUID.class, String.class);
                gameProfileConstructor.setAccessible(true);
            }
            if (craftOfflinePlayerConstructor == null) {
                Class<?> serverClass = Bukkit.getServer().getClass();
                Class<?> craftOfflinePlayerClass = Class.forName(serverClass.getName()
                        .replace("CraftServer", "CraftOfflinePlayer"));
                craftOfflinePlayerConstructor = craftOfflinePlayerClass.getDeclaredConstructor(
                        serverClass, gameProfileClass
                );
                craftOfflinePlayerConstructor.setAccessible(true);
            }
            Object gameProfile = gameProfileConstructor.newInstance(invalidUserUUID, name);
            Object craftOfflinePlayer = craftOfflinePlayerConstructor.newInstance(Bukkit.getServer(), gameProfile);
            return (OfflinePlayer) craftOfflinePlayer;
        } catch (Throwable t) { // Fallback if fail
            return Bukkit.getOfflinePlayer(name);
        }
    }

    public static boolean isInWater(Player p) {
        return p.getLocation().getBlock().isLiquid() && (p.getLocation().getBlock().getType() == Material.WATER || p.getLocation().getBlock().getType() == Material.STATIONARY_WATER);
    }

    public static boolean isInLava(Player p) {
        return p.getLocation().getBlock().isLiquid() && (p.getLocation().getBlock().getType() == Material.LAVA || p.getLocation().getBlock().getType() == Material.STATIONARY_LAVA);
    }

    public static void sendActionbar(Player p, String message) {
        try {
            Constructor<?> constructor = getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"), byte.class);
            Object icbc = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}");
            Object packet = constructor.newInstance(icbc, (byte) 2);
            Object entityPlayer= p.getClass().getMethod("getHandle").invoke(p);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);

            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | InstantiationException e) {
            e.printStackTrace();
        }
    }


    private static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + getVersion() + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static Player getNearest(Player p) {
        double distance = 0.0;
        Player target = null;
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all == p)
                continue;
            if (((BeastBrawl)Bukkit.getPluginManager().getPlugin("AresKitPvP")).getKitManager().getKit(all)==null)
                continue;

            Location loc = p.getLocation();
            double dist = all.getLocation().distanceSquared(loc);
            if (target == null || dist < distance) {
                target = all;
                distance = dist;
            }
        }
        return target;
    }

    public static void sendLinkMessage(Player p, String text, String link){
        TextComponent component = new TextComponent(text);
        component.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, link ) );
        p.spigot().sendMessage(component);
    }

    public static List<Entity> getNearbyEntities(Location l, int size){
        List<Entity> entities = new ArrayList<Entity>();

        for(Entity e : l.getWorld().getEntities())
            if(l.distance(e.getLocation()) <= size)
                entities.add(e);

        return entities;
    }

    public static boolean inRadius(Location l, Location l2, int size){
        if(l.distance(l2) <= size)
            return true;
        return false;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static void openSoups(Player p){
        Inventory inventory = Bukkit.createInventory(p, 3*9, "Soups");
        for (int j = 0; j < 34; j++){
            TogglesManager toggles = new TogglesManager();
            if(toggles.getPotions()){
                inventory.addItem(potion());
            }else{
                inventory.addItem(soup());
            }
        }

        p.openInventory(inventory);
    }
}
