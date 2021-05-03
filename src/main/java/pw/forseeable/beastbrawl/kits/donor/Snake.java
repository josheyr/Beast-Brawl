package pw.forseeable.beastbrawl.kits.donor;

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
import pw.forseeable.beastbrawl.utils.Prefix;

import java.util.*;

/**
 * Ur boi forseeable made this class #gang
 */
public class Snake implements Kit {
    ArrayList<UUID> selected = new ArrayList<>();
    HashMap<UUID, Long> cooldown = new HashMap<>();

    public HashMap<UUID, UUID> targeted = new HashMap<>();

    @Override
    public String getColorCode(){
        return "§2";
    }

    @Override
    public String getName(){
        return "Snake";
    }

    @Override
    public int getGUIID(){
        return 3;
    }

    @Override
    public ItemStack getGUIItem(UUID uuid) {
        ConfigManager config = new ConfigManager(uuid);

        ItemStack item = new ItemStack(Material.SUGAR_CANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getColorCode() + "§l" + getName());
        if(Bukkit.getPlayer(uuid).hasPermission("bb.kit.snake")) {
            meta.setLore(Arrays.asList(
                    "§a§lUNLOCKED",
                    "",
                    "§eEffects",
                    "§7Perm Speed 1.",
                    "§7Sword deals poison.",
                    "",
                    "§eAbility:",
                    "§7Constrict",
                    "§7§oPulls towards nearest player",
                    "§7§oand chokes them, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second",
                    "§7§ocooldown.",
                    "",
                    "§e§lLeft §7click to equip.",
                    "§e§lRight §7click preview/upgrade.",
                    "§e§lMiddle §7click to default."
            ));
            meta.addEnchant(Enchantment.DURABILITY, 1, false);

        }else{
            meta.setLore(Arrays.asList(
                    "§e§lBUY IN STORE",
                    "",
                    "§eEffects",
                    "§7Perm Speed 1.",
                    "§7Sword deals poison.",
                    "",
                    "§eAbility:",
                    "§7Constrict",
                    "§7§oPulls towards nearest player",
                    "§7§oand chokes them, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second",
                    "§7§ocooldown.",
                    "",
                    "§e§lLeft §7click to buy.",
                    "§e§lRight §7click preview/upgrade."
            ));
        }
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
        return 30;
    }

    @Override
    public ItemStack getAbility(UUID uuid){
        ConfigManager config = new ConfigManager(uuid);
 //ABILITY
        ItemStack ability = new ItemStack(Material.SUGAR_CANE);
        ItemMeta abilityM = ability.getItemMeta();
        abilityM.setDisplayName(getColorCode() + "§l" + getName() + " Ability");
        abilityM.setLore(Arrays.asList(
                getColorCode() + "Name: §7Constrict",
                "",
                "§7§oPulls towards nearest player",
                "§7§oand chokes them, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second",
                "§7§ocooldown.",
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
        ItemStack hat = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta hatM = (SkullMeta)hat.getItemMeta();
        hatM.setDisplayName(getColorCode() + "§l" + getName() + " Head");
        hatM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        hatM.addEnchant(Enchantment.DURABILITY, 10, true);
        hatM.setOwner("imsorryfrenchies");
        hat.setItemMeta(hatM);
        items.add(hat);

        //CHESTPLATE
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateM = (LeatherArmorMeta)chestplate.getItemMeta();
        chestplateM.setDisplayName(getColorCode() + "§l" + getName() + " Chestplate");
        chestplateM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        chestplateM.addEnchant(Enchantment.DURABILITY, 10, true);
        chestplateM.setColor(Color.fromRGB(51, 135, 56));
        chestplate.setItemMeta(chestplateM);
        items.add(chestplate);

        //LEGGINGS
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsM = (LeatherArmorMeta)leggings.getItemMeta();
        leggingsM.setDisplayName(getColorCode() + "§l" + getName() + " Leggings");
        leggingsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        leggingsM.addEnchant(Enchantment.DURABILITY, 10, true);
        leggingsM.setColor(Color.fromRGB(29, 86, 32));
        leggings.setItemMeta(leggingsM);
        items.add(leggings);

        //BOOTS
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsM = (LeatherArmorMeta)boots.getItemMeta();
        bootsM.setDisplayName(getColorCode() + "§l" + getName() + " Boots");
        bootsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        bootsM.addEnchant(Enchantment.DURABILITY, 10, true);
        bootsM.setColor(Color.fromRGB(25, 81, 28));
        boots.setItemMeta(bootsM);
        items.add(boots);
        return items;
    }

    @Override
    public void handleAbility(Player p, Block block, BeastBrawl bb) {
        if(targeted.containsKey(p.getUniqueId())) {
            cooldown(p.getUniqueId());

            Player c = Bukkit.getServer().getPlayer(targeted.get(p.getUniqueId()));

            for(int i = 4; i >= 0; i--) {
                bb.getServer().getScheduler().runTaskLater(bb, () -> {

                    org.bukkit.util.Vector direction = c.getLocation().toVector().subtract(p.getLocation().toVector()).normalize();
                    p.setVelocity(direction.add(new org.bukkit.util.Vector(0, 0.5, 0)));

                    bb.getServer().getScheduler().runTaskLater(bb, () -> {
                        for (Player pe : Bukkit.getOnlinePlayers()) {
                            pe.playSound(p.getLocation(), Sound.SPIDER_IDLE, 1, 1);
                        }
                        c.damage(4, p);
                        c.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
                        c.getLocation().getWorld().playEffect(c.getLocation().add(0, 0, 0), Effect.STEP_SOUND, 152);
                    }, 2);
                }, i * 20);

                for (Player pe : Bukkit.getOnlinePlayers()) {
                    pe.playSound(p.getLocation(), Sound.SPIDER_DEATH, 1, 1);
                }
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
        strings.add("§0█§6█§0█§2█§2█§0█§6█§0█");
        strings.add("§0█§e█§0█§2█§2█§0█§e█§0█");
        strings.add("§0█§6█§0█§2█§2█§0█§6█§0█");
        strings.add("§0█§0█§2█§2█§2█§2█§0█§0█");
        strings.add("§2█§2█§2█§2█§2█§2█§2█§2█");
        strings.add("§f█§c█§c█§c█§c█§c█§c█§f█");
        strings.add("§f█§4█§4█§4█§4█§4█§4█§f█");
        strings.add("§0█§0█§0█§c█§c█§0█§0█§0█");
        return strings;
    }

    @Override
    public String getID(){return "004";}

}
