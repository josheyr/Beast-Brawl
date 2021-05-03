package pw.forseeable.beastbrawl.kits.pay;

import com.google.common.base.Strings;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.managers.ConfigManager;

import java.util.*;

/**
 * Ur boi forseeable made this class #gang
 */
public class Frog implements Kit {

    ArrayList<UUID> selected = new ArrayList<>();
    HashMap<UUID, Long> cooldown = new HashMap<>();

    @Override
    public String getID(){return "003";}


    @Override
    public String getColorCode(){
        return "§a";
    }

    @Override
    public String getName(){
        return "Frog";
    }

    @Override
    public int getGUIID(){
        return 1;
    }

    @Override
    public ItemStack getGUIItem(UUID uuid) {
        ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte)2);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getColorCode() + "§l" + getName());

        String topLine;
        ConfigManager config = new ConfigManager(uuid);

        if(config.getBalance() >= getCost()){
            topLine = "§a§lUSE: §7" + (int)getCost();
            meta.addEnchant(Enchantment.DURABILITY, 1, false);

        }else{
            topLine = "§c§lUSE: §7" + (int)getCost();
        }

        meta.setLore(Arrays.asList(
                topLine,
                "",
                "§eEffects",
                "§7Perm Speed 1, Jumpboost 1.",
                "",
                "§eAbility:",
                "§7Hibernate",
                "§7§oPlayer hibernates for 3",
                "§7§oseconds then wakes up with",
                "§7§oinsane buffs, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
                "",
                "§e§lLeft §7click to equip.",
                "§e§lRight §7click preview/upgrade.",
                "§e§lMiddle §7click to default."
        ));
        //meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public double getCost() {
        return 20;
    }

    @Override
    public boolean isSelected(UUID uuid) {
        return selected.contains(uuid);
    }

    @Override
    public void selectedAdd(Player p) {
        selected.add(p.getUniqueId());
    }

    @Override
    public void selectedRemove(Player p){
        selected.remove(p.getUniqueId());
    }

    @Override
    public Collection<PotionEffect> getPotionEffects(){
        ArrayList<PotionEffect> items = new ArrayList<>();
        items.add(new PotionEffect(PotionEffectType.SPEED, 9999999, 0));
        items.add(new PotionEffect(PotionEffectType.JUMP, 9999999, 1));

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
        return 60;
    }
    
    @Override
    public ItemStack getAbility(UUID uuid){
        ConfigManager config = new ConfigManager(uuid);
        //ABILITY
        ItemStack ability = new ItemStack(Material.INK_SACK, 1, (byte)2);
        ItemMeta abilityM = ability.getItemMeta();
        abilityM.setDisplayName(getColorCode() + "§l" + getName() + " Ability");
        abilityM.setLore(Arrays.asList(
                getColorCode() + "Name: §7Hibernate",
                "",
                "§7§oPlayer hibernates for 3",
                "§7§oseconds then wakes up with",
                "§7§oinsane buffs and debuffs for",
                "§7§onearby, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
                "",
                getColorCode() + "Right §7click to activate."
        ));
        ability.setItemMeta(abilityM);
        return (ability);
    }

    @Override
    public Collection<ItemStack> getItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
 //SWORD
        ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta swordM = sword.getItemMeta();
        swordM.setDisplayName(getColorCode() + "§l" + getName() + " Sword");
        swordM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        swordM.addEnchant(Enchantment.DURABILITY, 10, true);
        sword.setItemMeta(swordM);
        items.add(sword);

        //EXTRA POTIONS
        ItemStack extra = new ItemStack(Material.POTION, 16388);
        Potion pot = new Potion(1);
        pot.setSplash(true);
        pot.apply(extra);
        pot.setType(PotionType.POISON);
        extra.setAmount(3);
        PotionMeta extraM = (PotionMeta)Bukkit.getServer().getItemFactory().getItemMeta(Material.POTION);
        PotionEffect effect = new PotionEffect(PotionEffectType.POISON, 100, 1);
        extraM.setMainEffect(effect.getType());
        extraM.addCustomEffect(effect, true);
        extraM.setDisplayName(getColorCode() + "§l" + getName() + " Potion");
        extra.setItemMeta(extraM);
        items.add(extra);

        return items;
    }

    @Override
    public List<ItemStack> getArmor() {
        ArrayList<ItemStack> items = new ArrayList<>();
        //HAT
        ItemStack hat = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta hatM = (SkullMeta)hat.getItemMeta();
        hatM.setDisplayName(getColorCode() + "§l" + getName() + " Head");
        hatM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        hatM.addEnchant(Enchantment.DURABILITY, 10, true);
        hatM.setOwner("TollerFrosch");
        hat.setItemMeta(hatM);
        items.add(hat);

        //CHESTPLATE
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateM = (LeatherArmorMeta)chestplate.getItemMeta();
        chestplateM.setDisplayName(getColorCode() + "§l" + getName() + " Chestplate");
        chestplateM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        chestplateM.addEnchant(Enchantment.DURABILITY, 10, true);
        chestplateM.setColor(Color.fromRGB(182, 221, 90));
        chestplate.setItemMeta(chestplateM);
        items.add(chestplate);

        //LEGGINGS
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsM = (LeatherArmorMeta)leggings.getItemMeta();
        leggingsM.setDisplayName(getColorCode() + "§l" + getName() + " Leggings");
        leggingsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        leggingsM.addEnchant(Enchantment.DURABILITY, 10, true);
        leggingsM.setColor(Color.fromRGB(114, 178, 80));
        leggings.setItemMeta(leggingsM);
        items.add(leggings);

        //BOOTS
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsM = (LeatherArmorMeta)boots.getItemMeta();
        bootsM.setDisplayName(getColorCode() + "§l" + getName() + " Boots");
        bootsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        bootsM.addEnchant(Enchantment.DURABILITY, 10, true);
        bootsM.setColor(Color.fromRGB(75, 109, 57));
        boots.setItemMeta(bootsM);
        items.add(boots);

        return items;
    }

    @Override
    public void handleAbility(Player p, Block block, BeastBrawl bb) {
        for (PotionEffect pf:p.getActivePotionEffects()
                ) {
            p.removePotionEffect(pf.getType());
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 3));
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 9));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 9));

        for (Player pe : Bukkit.getOnlinePlayers()) {
            pe.playSound(p.getLocation(), Sound.BURP, 1, 1);
        }
        bb.getServer().getScheduler().runTaskLater(bb, () -> {
            for (Player pe : Bukkit.getOnlinePlayers()) {
                pe.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 1, 1);
            }

        }, 80);
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
        strings.add("§a█§a█§a█§a█§a█§a█§a█§a█");
        strings.add("§a█§a█§a█§a█§a█§a█§a█§a█");
        strings.add("§6█§6█§a█§a█§a█§a█§6█§6█");
        strings.add("§2█§2█§a█§a█§a█§a█§2█§2█");
        strings.add("§a█§a█§a█§a█§a█§a█§a█§a█");
        strings.add("§e█§e█§e█§e█§e█§e█§e█§e█");
        strings.add("§e█§e█§e█§e█§e█§e█§e█§e█");
        strings.add("§e█§e█§e█§e█§e█§e█§e█§e█");
        return strings;
    }
}
