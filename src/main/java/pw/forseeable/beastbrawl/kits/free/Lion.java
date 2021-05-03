package pw.forseeable.beastbrawl.kits.free;

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
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.managers.ConfigManager;

import java.util.*;

/**
 * Ur boi forseeable made this class #gang
 */
public class Lion implements Kit {

    ArrayList<UUID> selected = new ArrayList<>();
    HashMap<UUID, Long> cooldown = new HashMap<>();

    @Override
    public String getID(){return "001";}

    @Override
    public String getColorCode(){
        return "§c";
    }

    @Override
    public String getName(){
        return "Lion";
    }

    @Override
    public int getGUIID(){
        return 0;
    }

    @Override
    public ItemStack getGUIItem(UUID uuid) {
        ConfigManager config = new ConfigManager(uuid);

        ItemStack item = new ItemStack(Material.PORK);
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
                "§7Prey Spotted",
                "§7§oInstant buff of strength for 5",
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
        return 20;
    }

    @Override
    public ItemStack getAbility(UUID uuid){
        ConfigManager config = new ConfigManager(uuid);

        //ABILITY
        ItemStack ability = new ItemStack(Material.PORK);
        ItemMeta abilityM = ability.getItemMeta();
        abilityM.setDisplayName(getColorCode() + "§l" + getName() + " Ability");
        abilityM.setLore(Arrays.asList(
                getColorCode() + "Name: §7Prey Spotted",
                "",
                "§7§oInstant buff of strength for 5",
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
        ItemStack sword = new ItemStack(Material.GOLD_SWORD);
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
        hatM.setOwner("Lion");
        hat.setItemMeta(hatM);
        items.add(hat);

        //CHESTPLATE
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateM = (LeatherArmorMeta)chestplate.getItemMeta();
        chestplateM.setDisplayName(getColorCode() + "§l" + getName() + " Chestplate");
        chestplateM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        chestplateM.addEnchant(Enchantment.DURABILITY, 10, true);
        chestplateM.setColor(Color.fromRGB(255, 186, 68));
        chestplate.setItemMeta(chestplateM);
        items.add(chestplate);

        //LEGGINGS
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsM = (LeatherArmorMeta)leggings.getItemMeta();
        leggingsM.setDisplayName(getColorCode() + "§l" + getName() + " Leggings");
        leggingsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        leggingsM.addEnchant(Enchantment.DURABILITY, 10, true);
        leggingsM.setColor(Color.fromRGB(255, 208, 68));
        leggings.setItemMeta(leggingsM);
        items.add(leggings);

        //BOOTS
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsM = (LeatherArmorMeta)boots.getItemMeta();
        bootsM.setDisplayName(getColorCode() + "§l" + getName() + " Boots");
        bootsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        bootsM.addEnchant(Enchantment.DURABILITY, 10, true);
        bootsM.setColor(Color.fromRGB(255, 164, 68));
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
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 150, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 150, 1));
        for (Player pe : Bukkit.getOnlinePlayers()) {
            pe.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
        }
        bb.lionPUActive.add(p.getUniqueId());

        int points = 12;
        for (int i = 0; i < 360; i += 360/points) {

            double angle = (i * Math.PI / 180);
            double x = 0.5 * Math.cos(angle);
            double z = 0.5 * Math.sin(angle);
            Location loc = p.getLocation().add(x, 0, z);

            p.getWorld().playEffect(loc.add(0,1,0), Effect.FLAME, 100);
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
        strings.add("§8█§8█§8█§8█§8█§8█§8█§8█");
        strings.add("§8█§8█§6█§6█§6█§6█§8█§8█");
        strings.add("§6█§8█§8█§6█§6█§8█§8█§6█");
        strings.add("§6█§f█§0█§6█§6█§0█§f█§6█");
        strings.add("§6█§6█§6█§6█§6█§6█§6█§6█");
        strings.add("§8█§6█§6█§0█§0█§6█§6█§8█");
        strings.add("§8█§6█§6█§8█§8█§6█§6█§8█");
        strings.add("§8█§8█§8█§6█§6█§8█§8█§8█");
        return strings;
    }
}
