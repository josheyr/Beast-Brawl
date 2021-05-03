package pw.forseeable.beastbrawl.kits.buy;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.*;
import org.bukkit.util.Vector;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.managers.ConfigManager;

import java.util.*;

/**
 * Ur boi forseeable made this class #gang
 */
public class Dolphin implements Kit {
    ArrayList<UUID> selected = new ArrayList<>();
    HashMap<UUID, Long> cooldown = new HashMap<>();

    @Override
    public String getColorCode(){
        return "§9";
    }

    @Override
    public String getName(){
        return "Dolphin";
    }

    @Override
    public int getGUIID(){
        return 2;
    }

    @Override
    public ItemStack getGUIItem(UUID uuid) {
        ItemStack item = new ItemStack(Material.RAW_FISH);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getColorCode() + "§l" + getName());

        ConfigManager config = new ConfigManager(uuid);
        String topLine;

        if(config.isUnlocked(this)){
            topLine = "§a§lUNLOCKED";
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
            meta.setLore(Arrays.asList(
                    topLine,
                    "",
                    "§eEffects",
                    "§7Water Breathing 1.",
                    "§7§oCAN SWIM FAST",
                    "",
                    "§eAbility:",
                    "§7Splash",
                    "§7§oDeal damage in water",
                    "§7§owithin radius, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
                    "",
                    "§e§lLeft §7click to equip.",
                    "§e§lRight §7click preview/upgrade.",
                    "§e§lMiddle §7click to default."
            ));
        }else{
            if(config.getBalance() >= getCost()){
                topLine = "§e§lBUY: §7" + (int)getCost();
                meta.setLore(Arrays.asList(
                        topLine,
                        "",
                        "§eEffects",
                        "§7Water Breathing 1.",
                        "§7§oCAN SWIM FAST",
                        "",
                        "§eAbility:",
                        "§7Splash",
                        "§7§oDeal damage in water",
                        "§7§owithin radius, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
                        "",
                        "§e§lLeft §7click to buy.",
                        "§e§lRight §7click preview."
                ));
            }else{
                topLine = "§c§lBUY: §7" + (int)getCost();
                meta.setLore(Arrays.asList(
                        topLine,
                        "",
                        "§eEffects",
                        "§7Water Breathing 1.",
                        "§7§oCAN SWIM FAST",
                        "",
                        "§eAbility:",
                        "§7Splash",
                        "§7§oDeal damage in water",
                        "§7§owithin radius, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
                        "",
                        "§e§lRight §7click preview."
                ));
            }
        }
        //meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public double getCost() {
        return 300;
    }

    @Override
    public boolean isSelected(UUID uuid) {
        return selected.contains(uuid);
    }


    @Override
    public void selectedAdd(Player p){
        selected.add(p.getUniqueId());
    }

    @Override
    public Collection<PotionEffect> getPotionEffects(){
        ArrayList<PotionEffect> items = new ArrayList<>();
        items.add(new PotionEffect(PotionEffectType.WATER_BREATHING, 9999999, 0));

        return items;
    }

    @Override
    public void cooldown(UUID uuid) {
        cooldown.put(uuid, System.currentTimeMillis());
    }

    @Override
    public long cooldownStarted(UUID uuid) {
        return cooldown.get(uuid);
    }

    @Override
    public int cooldownTime() {
        return 10;
    }

    @Override
    public void selectedRemove(Player p) {
        selected.remove(p.getUniqueId());
        
    }

    @Override
    public ItemStack getAbility(UUID uuid){
        ConfigManager config = new ConfigManager(uuid);

        ItemStack ability = new ItemStack(Material.RAW_FISH);
        ItemMeta abilityM = ability.getItemMeta();
        abilityM.setDisplayName(getColorCode() + "§l" + getName() + " Ability");
        abilityM.setLore(Arrays.asList(
                getColorCode() + "Name: §7Splash",
                "",
                "§7§oDeal damage in water",
                "§7§owithin radius, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
                "",
                getColorCode() + "Right §7click to activate."
        ));
        ability.setItemMeta(abilityM);

        return ability;
    }

    @Override
    public Collection<ItemStack> getItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta swordM = sword.getItemMeta();
        swordM.setDisplayName(getColorCode() + "§l" + getName() + " Sword");
        swordM.addEnchant(Enchantment.DURABILITY, 10, true);
        sword.setItemMeta(swordM);
        items.add(sword);

        return items;
    }

    @Override
    public Collection<ItemStack> getArmor() {
        ArrayList<ItemStack> items = new ArrayList<>();

        //HAT
        ItemStack hat = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta hatM = (SkullMeta)hat.getItemMeta();
        hatM.setDisplayName(getColorCode() + "§l" + getName() + " Head");
        hatM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        hatM.addEnchant(Enchantment.DURABILITY, 10, true);
        hatM.setOwner("dolph");
        hat.setItemMeta(hatM);
        items.add(hat);

        //CHESTPLATE
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateM = (LeatherArmorMeta)chestplate.getItemMeta();
        chestplateM.setDisplayName(getColorCode() + "§l" + getName() + " Chestplate");
        chestplateM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        chestplateM.addEnchant(Enchantment.DURABILITY, 10, true);
        chestplateM.setColor(Color.fromRGB(231, 231, 231));
        chestplate.setItemMeta(chestplateM);
        items.add(chestplate);

        //LEGGINGS
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsM = (LeatherArmorMeta)leggings.getItemMeta();
        leggingsM.setDisplayName(getColorCode() + "§l" + getName() + " Leggings");
        leggingsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        leggingsM.addEnchant(Enchantment.DURABILITY, 10, true);
        leggingsM.setColor(Color.fromRGB(147, 173, 207));
        leggings.setItemMeta(leggingsM);
        items.add(leggings);

        //BOOTS
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsM = (LeatherArmorMeta)boots.getItemMeta();
        bootsM.setDisplayName(getColorCode() + "§l" + getName() + " Boots");
        bootsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        bootsM.addEnchant(Enchantment.DURABILITY, 10, true);
        bootsM.setColor(Color.fromRGB(147, 173, 207));
        boots.setItemMeta(bootsM);
        items.add(boots);

        return items;
    }

    @Override
    public void handleAbility(Player p, Block block, BeastBrawl bb) {
        p.setVelocity(new Vector(0, 1, 0));
        for (Player pe : Bukkit.getOnlinePlayers()) {
            pe.playSound(p.getLocation(), Sound.WOLF_SHAKE, 1, 1);
        }

        int points = 12;
        for (int i = 0; i < 360; i += 360/points) {

            double angle = (i * Math.PI / 180);
            double x = 2 * Math.cos(angle);
            double z = 2 * Math.sin(angle);
            Location loc = p.getLocation().add(x, 0, z);
            loc.getWorld().playEffect(loc, Effect.WATERDRIP, 3);
            // p.getWorld().playEffect(loc.subtract(0,.2,0), Effect.VILLAGER_THUNDERCLOUD, 20, 0);
        }

        for (int i = 0; i < 360; i += 360/points) {

            double angle = (i * Math.PI / 180);
            double x = 2 * Math.cos(angle);
            double z = 2 * Math.sin(angle);
            Location loc = p.getLocation().add(x, 1, z);
            loc.getWorld().playEffect(loc, Effect.WATERDRIP, 3);
            // p.getWorld().playEffect(loc.subtract(0,.2,0), Effect.VILLAGER_THUNDERCLOUD, 20, 0);
        }

        for (int i = 0; i < 360; i += 360/points) {

            double angle = (i * Math.PI / 180);
            double x = 2 * Math.cos(angle);
            double z = 2 * Math.sin(angle);
            Location loc = p.getLocation().add(x, -1, z);
            loc.getWorld().playEffect(loc, Effect.WATERDRIP, 3);
            // p.getWorld().playEffect(loc.subtract(0,.2,0), Effect.VILLAGER_THUNDERCLOUD, 20, 0);
        }
    }

    @Override
    public void resetCooldown(UUID uuid){
        cooldown.remove(uuid);
    }

    @Override
    public boolean inCooldown(UUID uuid) {
        if (cooldown.containsKey(uuid)) {
            ConfigManager config = new ConfigManager(uuid);

            int passed = (int) ((System.currentTimeMillis() - cooldown.get(uuid)) / 1000);
            return passed < (cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)));
        }
        return false;
    }

    @Override
    public Collection<String> skullStrings(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("§3█§3█§3█§3█§3█§3█§3█§3█");
        strings.add("§3█§3█§3█§3█§3█§3█§3█§3█");
        strings.add("§3█§3█§3█§3█§3█§3█§3█§3█");
        strings.add("§3█§3█§3█§3█§3█§3█§3█§3█");
        strings.add("§0█§3█§3█§3█§3█§3█§3█§0█");
        strings.add("§3█§3█§f█§f█§f█§f█§3█§3█");
        strings.add("§3█§f█§f█§f█§f█§f█§f█§3█");
        strings.add("§3█§f█§f█§f█§f█§f█§f█§3█");
        return strings;
    }
    @Override
    public String getID(){return "010";}

}
