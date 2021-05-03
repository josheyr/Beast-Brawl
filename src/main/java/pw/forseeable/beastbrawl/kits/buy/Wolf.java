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
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.managers.ConfigManager;
import pw.forseeable.beastbrawl.utils.Prefix;

import java.util.*;
import java.util.Vector;

/**
 * Ur boi forseeable made this class #gang
 */
public class Wolf implements Kit {

    ArrayList<UUID> selected = new ArrayList<>();
    HashMap<UUID, Long> cooldown = new HashMap<>();

    public HashMap<UUID, UUID> targeted = new HashMap<>();

    @Override
    public String getColorCode(){
        return "§8";
    }

    @Override
    public String getName(){
        return "Wolf";
    }

    @Override
    public int getGUIID(){
        return 2;
    }

    @Override
    public ItemStack getGUIItem(UUID uuid) {
        ConfigManager config = new ConfigManager(uuid);

        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getColorCode() + "§l" + getName());

        String topLine;

        if(config.isUnlocked(this)){
            topLine = "§a§lUNLOCKED";
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
            meta.setLore(Arrays.asList(
                    topLine,
                    "",
                    "§eEffects",
                    "§7Perm Speed 1.",
                    "",
                    "§eAbility:",
                    "§7Bite",
                    "§7§oLaunch towards player and",
                    "§7§obite them, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
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
                        "§7Perm Speed 1.",
                        "",
                        "§eAbility:",
                        "§7Bite",
                        "§7§oLaunch towards player and",
                        "§7§obite them, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
                        "",
                        "§e§lLeft §7click to buy.",
                        "§e§lRight §7click preview/upgrade."
                ));
            }else{
                topLine = "§c§lBUY: §7" + (int)getCost();
                meta.setLore(Arrays.asList(
                        topLine,
                        "",
                        "§eEffects",
                        "§7Perm Speed 1.",
                        "",
                        "§eAbility:",
                        "§7Bite",
                        "§7§oLaunch towards player and",
                        "§7§obite them, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
                        "",
                        "§e§lRight §7click preview/upgrade."
                ));
            }
        }
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public double getCost() {
        return 100;
    }

    @Override
    public boolean isSelected(UUID uuid) {
        return selected.contains(uuid);
    }


    @Override
    public void selectedRemove(Player p) {
        selected.remove(p.getUniqueId());
    }

    @Override
    public void selectedAdd(Player p){
        selected.add(p.getUniqueId());
    }

    @Override
    public Collection<PotionEffect> getPotionEffects(){
        ArrayList<PotionEffect> items = new ArrayList<>();
        items.add(new PotionEffect(PotionEffectType.SPEED, 9999999, 1));

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
        return 20;
    }


    @Override
    public ItemStack getAbility(UUID uuid){
        ConfigManager config = new ConfigManager(uuid);

        //ABILITY
        ItemStack ability = new ItemStack(Material.BONE);
        ItemMeta abilityM = ability.getItemMeta();
        abilityM.setDisplayName(getColorCode() + "§l" + getName() + " Ability");
        abilityM.setLore(Arrays.asList(
                getColorCode() + "Name: §7Bite",
                "",
                "§7§oLaunch towards player and",
                "§7§obite them, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + " §7§osecond cooldown.",
                "",
                getColorCode() + "Right §7click to activate."
        ));
        ability.setItemMeta(abilityM);
        return ability;
    }

    @Override
    public Collection<ItemStack> getItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        //SWORD
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
         ItemStack hat = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
         SkullMeta hatM = (SkullMeta)hat.getItemMeta();
         hatM.setDisplayName(getColorCode() + "§l" + getName() + " Head");
         hatM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
         hatM.addEnchant(Enchantment.DURABILITY, 10, true);
         hatM.setOwner("ISB");
         hat.setItemMeta(hatM);
         items.add(hat);
 
         //CHESTPLATE
         ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
         LeatherArmorMeta chestplateM = (LeatherArmorMeta)chestplate.getItemMeta();
         chestplateM.setDisplayName(getColorCode() + "§l" + getName() + " Chestplate");
         chestplateM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
         chestplateM.addEnchant(Enchantment.DURABILITY, 10, true);
         chestplateM.setColor(Color.fromRGB(94, 94, 94));
         chestplate.setItemMeta(chestplateM);
         items.add(chestplate);
 
         //LEGGINGS
         ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
         LeatherArmorMeta leggingsM = (LeatherArmorMeta)leggings.getItemMeta();
         leggingsM.setDisplayName(getColorCode() + "§l" + getName() + " Leggings");
         leggingsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
         leggingsM.addEnchant(Enchantment.DURABILITY, 10, true);
         leggingsM.setColor(Color.fromRGB(135, 135, 135));
         leggings.setItemMeta(leggingsM);
         items.add(leggings);
 
         //BOOTS
         ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
         LeatherArmorMeta bootsM = (LeatherArmorMeta)boots.getItemMeta();
         bootsM.setDisplayName(getColorCode() + "§l" + getName() + " Boots");
         bootsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
         bootsM.addEnchant(Enchantment.DURABILITY, 10, true);
         bootsM.setColor(Color.fromRGB(102, 102, 102));
         boots.setItemMeta(bootsM);
         items.add(boots);
 

        return items;
    }

    @Override
    public void handleAbility(Player p, Block block, BeastBrawl bb) {
        if(targeted.containsKey(p.getUniqueId())) {
            cooldown(p.getUniqueId());

            Player c = Bukkit.getServer().getPlayer(targeted.get(p.getUniqueId()));

            org.bukkit.util.Vector direction = c.getLocation().toVector().subtract(p.getLocation().toVector()).normalize();
            p.setVelocity(direction.add(new org.bukkit.util.Vector(0, 0.5, 0)));

            bb.getServer().getScheduler().runTaskLater(bb, () -> {
                for (Player pe : Bukkit.getOnlinePlayers()) {
                    pe.playSound(p.getLocation(), Sound.WOLF_GROWL, 1, 1);
                }
                c.damage(8, p);
                c.getLocation().getWorld().playEffect(c.getLocation().add(0, 1, 0), Effect.STEP_SOUND, 152);
            }, 2);


            for (Player pe : Bukkit.getOnlinePlayers()) {
                pe.playSound(p.getLocation(), Sound.WOLF_BARK, 1, 1);
            }

            targeted.remove(p.getUniqueId());
        }else{
            p.sendMessage(Prefix.ERROR + "You must right-click a player.");
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
        strings.add("§7█§0█§7█§8█§8█§7█§0█§7█");
        strings.add("§8█§0█§0█§8█§7█§0█§0█§8█");
        strings.add("§8█§8█§8█§7█§7█§7█§8█§7█");
        strings.add("§8█§f█§0█§7█§7█§0█§f█§8█");
        strings.add("§7█§7█§7█§7█§7█§8█§7█§7█");
        strings.add("§7█§7█§8█§8█§8█§8█§8█§7█");
        strings.add("§7█§7█§8█§0█§0█§8█§8█§7█");
        strings.add("§8█§7█§7█§0█§0█§7█§7█§8█");
        return strings;
    }

    @Override
    public String getID(){return "006";}

}
