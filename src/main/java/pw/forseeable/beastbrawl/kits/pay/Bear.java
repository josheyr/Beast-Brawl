package pw.forseeable.beastbrawl.kits.pay;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.utils.Prefix;
import pw.forseeable.beastbrawl.utils.Utils;
import pw.forseeable.beastbrawl.dependencies.ParticleEffect;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.managers.ConfigManager;

import java.util.*;

/**
 * Ur boi forseeable made this class #gang
 */
public class Bear implements Kit {

    ArrayList<UUID> selected = new ArrayList<>();
    HashMap<UUID, Long> cooldown = new HashMap<>();

    public HashMap<UUID, UUID> targeted = new HashMap<>();

    @Override
    public String getID(){return "009";}

    @Override
    public String getColorCode() {
        return "§8";
    }

    @Override
    public String getName() {
        return "Bear";
    }

    @Override
    public int getGUIID() {
        return 1;
    }

    @Override
    public ItemStack getGUIItem(UUID uuid) {
        ItemStack item = new ItemStack(Material.COAL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getColorCode() + "§l" + getName());

        String topLine;
        ConfigManager config = new ConfigManager(uuid);

        if (config.getBalance() >= getCost()) {
            topLine = "§a§lUSE: §7" + (int) getCost();
            meta.addEnchant(Enchantment.DURABILITY, 1, false);

        } else {
            topLine = "§c§lUSE: §7" + (int) getCost();
        }

        meta.setLore(Arrays.asList(
                topLine,
                "",
                "§eEffects",
                "§7Perm Slowness 1.",
                "",
                "§eAbility:",
                "§7Scratch",
                "§7§oUnleashes rage at targetted",
                "§7§oplayer dealing high",
                "§7§odamage, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
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
    public void cooldown(UUID uuid) {
        cooldown.put(uuid, System.currentTimeMillis());
    }

    @Override
    public long cooldownStarted(UUID uuid) {
        return cooldown.get(uuid);
    }

    @Override
    public int cooldownTime() {
        return 3;
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
    public ItemStack getAbility(UUID uuid){
        ConfigManager config = new ConfigManager(uuid);

        //ABILITY
        ItemStack ability = new ItemStack(Material.COAL, 1);
        ItemMeta abilityM = ability.getItemMeta();
        abilityM.setDisplayName(getColorCode() + "§l" + getName() + " Ability");
        abilityM.setLore(Arrays.asList(
                getColorCode() + "Name: §7Scratch",
                "",
                "§7§oUnleashes rage at targetted",
                "§7§oplayer dealing high",
                "§7§odamage, §l" + Integer.toString((cooldownTime() * 2) -  ((cooldownTime() / 5) * (config.kitLevel(this)))) + "§7§o second cooldown.",
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
        swordM.addEnchant(Enchantment.DAMAGE_ALL, 0, true);
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
        SkullMeta hatM = (SkullMeta) hat.getItemMeta();
        hatM.setDisplayName(getColorCode() + "§l" + getName() + " Head");
        hatM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        hatM.addEnchant(Enchantment.DURABILITY, 10, true);
        hatM.setOwner("Bear");
        hat.setItemMeta(hatM);
        items.add(hat);

        //CHESTPLATE
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateM = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplateM.setDisplayName(getColorCode() + "§l" + getName() + " Chestplate");
        chestplateM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        chestplateM.addEnchant(Enchantment.DURABILITY, 10, true);
        chestplateM.setColor(Color.fromRGB(171, 129, 88));
        chestplate.setItemMeta(chestplateM);
        items.add(chestplate);

        //LEGGINGS
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsM = (LeatherArmorMeta) leggings.getItemMeta();
        leggingsM.setDisplayName(getColorCode() + "§l" + getName() + " Leggings");
        leggingsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        leggingsM.addEnchant(Enchantment.DURABILITY, 10, true);
        leggingsM.setColor(Color.fromRGB(117, 61, 8));
        leggings.setItemMeta(leggingsM);
        items.add(leggings);

        //BOOTS
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsM = (LeatherArmorMeta) boots.getItemMeta();
        bootsM.setDisplayName(getColorCode() + "§l" + getName() + " Boots");
        bootsM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        bootsM.addEnchant(Enchantment.DURABILITY, 10, true);
        bootsM.setColor(Color.fromRGB(117, 61, 8));
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
                pe.playSound(p.getLocation(), Sound.WOLF_GROWL, 1, 1);
            }

            targeted.remove(p.getUniqueId());
        }else{
            p.sendMessage(Prefix.ERROR + "You must right-click a player.");
        }

        for (Player pe : Bukkit.getOnlinePlayers()) {
            pe.playSound(p.getLocation(), Sound.DONKEY_HIT, 1, 1);
        }
    }

    @Override
    public void resetCooldown(UUID uuid) {
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
    public Collection<String> skullStrings() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("§8█§8█§8█§8█§8█§8█§8█§8█");
        strings.add("§8█§e█§e█§8█§8█§e█§e█§8█");
        strings.add("§8█§8█§8█§8█§8█§8█§8█§8█");
        strings.add("§8█§8█§8█§8█§8█§8█§8█§8█");
        strings.add("§8█§8█§8█§8█§8█§8█§8█§8█");
        strings.add("§8█§0█§8█§8█§8█§8█§0█§8█");
        strings.add("§8█§8█§e█§0█§0█§e█§7█§8█");
        strings.add("§8█§e█§e█§e█§e█§e█§e█§8█");
        return strings;
    }
}
