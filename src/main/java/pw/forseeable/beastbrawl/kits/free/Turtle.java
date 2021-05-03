package pw.forseeable.beastbrawl.kits.free;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.dependencies.ParticleEffect;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.managers.ConfigManager;

/**
 * Ur boi forseeable made this class #gang
 */
public class Turtle implements Kit {

    ArrayList<UUID> selected = new ArrayList<>();
    HashMap<UUID, Long> cooldown = new HashMap<>();

    @Override
    public String getID(){return "012";}

    @Override
    public String getColorCode(){
        return "§2";
    }

    @Override
    public String getName(){
        return "Turtle";
    }

    @Override
    public int getGUIID(){
        return 0;
    }

    @Override
    public ItemStack getGUIItem(UUID uuid) {
        ConfigManager config = new ConfigManager(uuid);

        ItemStack item = new ItemStack(Material.IRON_BARDING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getColorCode() + "§l" + getName());
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.setLore(Arrays.asList(
                "§a§lFREE",
                "",
                "§eEffects",
                "§7Perm Slowness 1.",
                "§7§oSHELTERS 20% OF HITS.",
                "",
                "§eAbility:",
                "§7Shell Up",
                "§7§oShelter from all hits for 5",
                "§7§oseconds, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
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
        items.add(new PotionEffect(PotionEffectType.SLOW, 9999999, 0));

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
        ItemStack ability = new ItemStack(Material.IRON_BARDING);
        ItemMeta abilityM = ability.getItemMeta();
        abilityM.setDisplayName(getColorCode() + "§l" + getName() + " Ability");
        abilityM.setLore(Arrays.asList(
                getColorCode() + "Name: §7Shell Up",
                "",
                "§7§oShelter from all hits for 5",
                "§7§oseconds, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
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
        hatM.setOwner("Turtle");
        hat.setItemMeta(hatM);
        items.add(hat);

        //CHESTPLATE
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateM = (LeatherArmorMeta)chestplate.getItemMeta();
        chestplateM.setDisplayName(getColorCode() + "§l" + getName() + " Chestplate");
        chestplateM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        chestplateM.addEnchant(Enchantment.DURABILITY, 10, true);
        chestplateM.setColor(Color.fromRGB(189, 108, 50));
        chestplate.setItemMeta(chestplateM);
        items.add(chestplate);

        //LEGGINGS
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsM = (LeatherArmorMeta)leggings.getItemMeta();
        leggingsM.setDisplayName(getColorCode() + "§l" + getName() + " Leggings");
        leggingsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        leggingsM.addEnchant(Enchantment.DURABILITY, 10, true);
        leggingsM.setColor(Color.fromRGB(42, 241, 117));
        leggings.setItemMeta(leggingsM);
        items.add(leggings);

        //BOOTS
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsM = (LeatherArmorMeta)boots.getItemMeta();
        bootsM.setDisplayName(getColorCode() + "§l" + getName() + " Boots");
        bootsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        bootsM.addEnchant(Enchantment.DURABILITY, 10, true);
        bootsM.setColor(Color.fromRGB(0, 160, 97));
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
        bb.hitPercentage.put(p.getUniqueId(), 0);
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 150, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 150, 15));
        
        for (Player pe : Bukkit.getOnlinePlayers()) {
            pe.playSound(p.getLocation(), Sound.STEP_GRASS, 1, 1);
        }
        bb.turtlePUActive.add(p.getUniqueId());

        int points = 12;
        for (int i = 0; i < 360; i += 360/points) {

            double angle = (i * Math.PI / 180);
            double x = 1 * Math.cos(angle);
            double z = 1 * Math.sin(angle);
            Location loc = p.getLocation().add(x, 0, z);
            ParticleEffect.SMOKE_LARGE.display(new Vector(0, 0, 0), 0.0f, loc.add(0,1, 0), 50);
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
        strings.add("§2█§2█§a█§a█§a█§a█§2█§2█");
        strings.add("§2█§a█§a█§a█§a█§a█§a█§2█");
        strings.add("§a█§0█§a█§a█§a█§a█§0█§a█");
        strings.add("§a█§a█§a█§a█§a█§a█§a█§a█");
        strings.add("§a█§a█§a█§a█§a█§a█§a█§a█");
        strings.add("§a█§a█§a█§a█§a█§a█§a█§a█");
        strings.add("§2█§a█§a█§a█§0█§0█§0█§2█");
        strings.add("§2█§2█§a█§a█§a█§a█§2█§2█");
        return strings;
    }
}
