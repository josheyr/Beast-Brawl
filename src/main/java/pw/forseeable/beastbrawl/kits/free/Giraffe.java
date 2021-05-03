package pw.forseeable.beastbrawl.kits.free;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.managers.ConfigManager;

/**
 * Ur boi forseeable made this class #gang
 */
public class Giraffe implements Kit {

    ArrayList<UUID> selected = new ArrayList<>();
    HashMap<UUID, Long> cooldown = new HashMap<>();

    @Override
    public String getID(){return "008";}

    @Override
    public String getColorCode(){
        return "§e";
    }

    @Override
    public String getName(){
        return "Giraffe";
    }

    @Override
    public int getGUIID(){
        return 0;
    }

    @Override
    public ItemStack getGUIItem(UUID uuid) {
        ConfigManager config = new ConfigManager(uuid);

        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getColorCode() + "§l" + getName());
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.setLore(Arrays.asList(
                "§a§lFREE",
                "",
                "§eEffects",
                "§7Perm Speed 1.",
                "",
                "§eAbility:",
                "§7Eat Trees",
                "§7§oReaches up and eats the trees",
                "§7§oabove, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
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
        return 0;
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
        items.add(new PotionEffect(PotionEffectType.SPEED, 9999999, 0));

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
    public ItemStack getAbility(UUID uuid){
        ConfigManager config = new ConfigManager(uuid);

        //ABILITY
        ItemStack ability = new ItemStack(Material.BLAZE_ROD);
        ItemMeta abilityM = ability.getItemMeta();
        abilityM.setDisplayName(getColorCode() + "§l" + getName() + " Ability");
        abilityM.setLore(Arrays.asList(
                getColorCode() + "Name: §7Eat Trees",
                "",
                "§7§oReaches up and eats the trees",
                "§7§oabove, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
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


        return items;
    }

    @Override
    public Collection<ItemStack> getArmor() {
        ArrayList<ItemStack> items = new ArrayList<>();
//HAT
        ItemStack hat = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
        SkullMeta hatM = (SkullMeta)hat.getItemMeta();
        hatM.setDisplayName(getColorCode() + "§l" + getName() + " Head");
        hatM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        hatM.addEnchant(Enchantment.DURABILITY, 10, true);
        hatM.setOwner("Giraffe");
        hat.setItemMeta(hatM);
        items.add(hat);

        //CHESTPLATE
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateM = (LeatherArmorMeta)chestplate.getItemMeta();
        chestplateM.setDisplayName(getColorCode() + "§l" + getName() + " Chestplate");
        chestplateM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        chestplateM.addEnchant(Enchantment.DURABILITY, 10, true);
        chestplateM.setColor(Color.fromRGB(154, 101, 52));
        chestplate.setItemMeta(chestplateM);
        items.add(chestplate);

        //LEGGINGS
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsM = (LeatherArmorMeta)leggings.getItemMeta();
        leggingsM.setDisplayName(getColorCode() + "§l" + getName() + " Leggings");
        leggingsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        leggingsM.addEnchant(Enchantment.DURABILITY, 10, true);
        leggingsM.setColor(Color.fromRGB(240, 209, 117));
        leggings.setItemMeta(leggingsM);
        items.add(leggings);

        //BOOTS
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsM = (LeatherArmorMeta)boots.getItemMeta();
        bootsM.setDisplayName(getColorCode() + "§l" + getName() + " Boots");
        bootsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        bootsM.addEnchant(Enchantment.DURABILITY, 10, true);
        bootsM.setColor(Color.fromRGB(154, 101, 52));
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
        p.setVelocity(new Vector(0, 2, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 150, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 150, 1));
        for (Player pe : Bukkit.getOnlinePlayers()) {
            pe.playSound(p.getLocation(), Sound.BLAZE_BREATH, 1, 1);
        }
        bb.giraffePUActive.add(p.getUniqueId());

        int points = 12;
        for (int i = 0; i < 360; i += 360/points) {

            double angle = (i * Math.PI / 180);
            double x = 0.1 * Math.cos(angle);
            double z = 0.1 * Math.sin(angle);
            Location loc = p.getLocation().add(x, 0, z);

            p.getWorld().playEffect(loc.add(0,0.5,0), Effect.WATERDRIP, 100);
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
        strings.add("§8█§e█§8█§8█§8█§e█§e█§8█");
        strings.add("§8█§e█§e█§8█§8█§e█§e█§8█");
        strings.add("§e█§e█§e█§e█§e█§e█§e█§8█");
        strings.add("§0█§f█§e█§e█§e█§e█§0█§f█");
        strings.add("§0█§0█§e█§e█§e█§e█§0█§0█");
        strings.add("§8█§6█§0█§6█§6█§0█§6█§8█");
        strings.add("§8█§6█§6█§6█§6█§6█§6█§8█");
        strings.add("§8█§6█§8█§8█§8█§8█§6█§8█");
        return strings;
    }
}
