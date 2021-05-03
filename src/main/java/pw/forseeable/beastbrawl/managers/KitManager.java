package pw.forseeable.beastbrawl.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.util.Vector;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.buy.Dolphin;
import pw.forseeable.beastbrawl.kits.buy.Kangaroo;
import pw.forseeable.beastbrawl.kits.donor.Parrot;
import pw.forseeable.beastbrawl.kits.donor.Snake;
import pw.forseeable.beastbrawl.kits.pay.Bear;
import pw.forseeable.beastbrawl.kits.pay.Elephant;
import pw.forseeable.beastbrawl.kits.pay.Frog;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.kits.free.Giraffe;
import pw.forseeable.beastbrawl.kits.free.Lion;
import pw.forseeable.beastbrawl.kits.free.Rhino;
import pw.forseeable.beastbrawl.kits.free.Turtle;
import pw.forseeable.beastbrawl.kits.buy.Wolf;
import pw.forseeable.beastbrawl.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class KitManager {

    ArrayList<Kit> kits = new ArrayList<>();
    BeastBrawl bb;

    public KitManager(BeastBrawl bb) {
        this.bb = bb;
        //free
        kits.add(new Lion());
        kits.add(new Giraffe());
        kits.add(new Turtle());
        kits.add(new Rhino());
        //pay
        kits.add(new Frog());
        kits.add(new Elephant());
        kits.add(new Bear());
        //buy
        kits.add(new Wolf());
        kits.add(new Kangaroo());
        kits.add(new Dolphin());
        //donor
        kits.add(new Snake());
        kits.add(new Parrot());
    }

    public Integer realCooldownTime(Player p, Kit k){
        ConfigManager config = new ConfigManager(p.getUniqueId());
        return (k.cooldownTime() * 2) -  ((k.cooldownTime() / 5) * (config.kitLevel(k)));
    }

    public void openFreeKitSelector(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 6*9, "Kit Selector > §c§lFree Kits");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)12);
        ItemMeta glassM = glass.getItemMeta();
        glassM.setDisplayName("§l");
        glass.setItemMeta(glassM);

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowM = glass.getItemMeta();
        arrowM.setDisplayName("§8§lBack");
        arrowM.setLore(Arrays.asList(
                "§7Return to main kit menu."
        ));
        arrow.setItemMeta(arrowM);

        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(2, glass);
        inventory.setItem(3, glass);
        inventory.setItem(4, glass);
        inventory.setItem(5, glass);
        inventory.setItem(6, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);
        inventory.setItem(9, glass);
        inventory.setItem(17, glass);
        inventory.setItem(18, glass);
        inventory.setItem(26, glass);
        inventory.setItem(27, glass);
        inventory.setItem(35, glass);
        inventory.setItem(36, glass);
        inventory.setItem(44, glass);
        inventory.setItem(45, glass);
        inventory.setItem(46, glass);
        inventory.setItem(47, glass);
        inventory.setItem(48, glass);
        inventory.setItem(49, arrow);
        inventory.setItem(50, glass);
        inventory.setItem(51, glass);
        inventory.setItem(52, glass);
        inventory.setItem(53, glass);


        for (Kit kit : kits) {
            if(kit.getGUIID() == 0) {
                ItemStack item = kit.getGUIItem(p.getUniqueId());

                if(bb.using18.contains(p.getUniqueId())){
                    item.setType(Material.SKULL_ITEM);
                    item.setDurability((short) SkullType.PLAYER.ordinal());

                    SkullMeta itemM = (SkullMeta) item.getItemMeta();
                    itemM.setOwner(((SkullMeta)kit.getArmor().iterator().next().getItemMeta()).getOwner());

                    item.setItemMeta(itemM);
                }
            inventory.addItem(item);            }
        }

        p.updateInventory();
        p.openInventory(inventory);
    }

    public void openPayKitSelector(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 6*9, "Kit Selector > §e§lPay2use Kits");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)4);
        ItemMeta glassM = glass.getItemMeta();
        glassM.setDisplayName("§l");
        glass.setItemMeta(glassM);

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowM = glass.getItemMeta();
        arrowM.setDisplayName("§8§lBack");
        arrowM.setLore(Arrays.asList(
                "§7Return to main kit menu."
        ));
        arrow.setItemMeta(arrowM);

        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(2, glass);
        inventory.setItem(3, glass);
        inventory.setItem(4, glass);
        inventory.setItem(5, glass);
        inventory.setItem(6, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);
        inventory.setItem(9, glass);
        inventory.setItem(17, glass);
        inventory.setItem(18, glass);
        inventory.setItem(26, glass);
        inventory.setItem(27, glass);
        inventory.setItem(35, glass);
        inventory.setItem(36, glass);
        inventory.setItem(44, glass);
        inventory.setItem(45, glass);
        inventory.setItem(46, glass);
        inventory.setItem(47, glass);
        inventory.setItem(48, glass);
        inventory.setItem(49, arrow);
        inventory.setItem(50, glass);
        inventory.setItem(51, glass);
        inventory.setItem(52, glass);
        inventory.setItem(53, glass);


        for (Kit kit : kits) {
            if(kit.getGUIID() == 1) {
                ItemStack item = kit.getGUIItem(p.getUniqueId());

                if(bb.using18.contains(p.getUniqueId())){
                    item.setType(Material.SKULL_ITEM);
                    item.setDurability((short) SkullType.PLAYER.ordinal());

                    SkullMeta itemM = (SkullMeta) item.getItemMeta();
                    itemM.setOwner(((SkullMeta)kit.getArmor().iterator().next().getItemMeta()).getOwner());

                    item.setItemMeta(itemM);
                }
            inventory.addItem(item);            
        }
        }
        p.openInventory(inventory);
    }

    public void openBuyKitSelector(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 6*9, "Kit Selector > §f§lBuyable Kits");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassM = glass.getItemMeta();
        glassM.setDisplayName("§l");
        glass.setItemMeta(glassM);

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowM = glass.getItemMeta();
        arrowM.setDisplayName("§8§lBack");
        arrowM.setLore(Arrays.asList(
                "§7Return to main kit menu."
        ));
        arrow.setItemMeta(arrowM);

        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(2, glass);
        inventory.setItem(3, glass);
        inventory.setItem(4, glass);
        inventory.setItem(5, glass);
        inventory.setItem(6, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);
        inventory.setItem(9, glass);
        inventory.setItem(17, glass);
        inventory.setItem(18, glass);
        inventory.setItem(26, glass);
        inventory.setItem(27, glass);
        inventory.setItem(35, glass);
        inventory.setItem(36, glass);
        inventory.setItem(44, glass);
        inventory.setItem(45, glass);
        inventory.setItem(46, glass);
        inventory.setItem(47, glass);
        inventory.setItem(48, glass);
        inventory.setItem(49, arrow);
        inventory.setItem(50, glass);
        inventory.setItem(51, glass);
        inventory.setItem(52, glass);
        inventory.setItem(53, glass);


        for (Kit kit : kits) {
            if(kit.getGUIID() == 2) {
                ItemStack item = kit.getGUIItem(p.getUniqueId());

                if(bb.using18.contains(p.getUniqueId())){
                    item.setType(Material.SKULL_ITEM);
                    item.setDurability((short) SkullType.PLAYER.ordinal());

                    SkullMeta itemM = (SkullMeta) item.getItemMeta();
                    itemM.setOwner(((SkullMeta)kit.getArmor().iterator().next().getItemMeta()).getOwner());

                    item.setItemMeta(itemM);
                }
            inventory.addItem(item);
                    }
        }
        p.openInventory(inventory);
    }

    public void openDonorKitSelector(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 6*9, "Kit Selector > §b§lDonor Kits");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)3);
        ItemMeta glassM = glass.getItemMeta();
        glassM.setDisplayName("§l");
        glass.setItemMeta(glassM);

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowM = glass.getItemMeta();
        arrowM.setDisplayName("§8§lBack");
        arrowM.setLore(Arrays.asList(
                "§7Return to main kit menu."
                ));
        arrow.setItemMeta(arrowM);

        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(2, glass);
        inventory.setItem(3, glass);
        inventory.setItem(4, glass);
        inventory.setItem(5, glass);
        inventory.setItem(6, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);
        inventory.setItem(9, glass);
        inventory.setItem(17, glass);
        inventory.setItem(18, glass);
        inventory.setItem(26, glass);
        inventory.setItem(27, glass);
        inventory.setItem(35, glass);
        inventory.setItem(36, glass);
        inventory.setItem(44, glass);
        inventory.setItem(45, glass);
        inventory.setItem(46, glass);
        inventory.setItem(47, glass);
        inventory.setItem(48, glass);
        inventory.setItem(49, arrow);
        inventory.setItem(50, glass);
        inventory.setItem(51, glass);
        inventory.setItem(52, glass);
        inventory.setItem(53, glass);

        for (Kit kit : kits) {
            if(kit.getGUIID() == 3){
                ItemStack item = kit.getGUIItem(p.getUniqueId());

                if(bb.using18.contains(p.getUniqueId())){
                    item.setType(Material.SKULL_ITEM);
                    item.setDurability((short) SkullType.PLAYER.ordinal());

                    SkullMeta itemM = (SkullMeta) item.getItemMeta();
                    itemM.setOwner(((SkullMeta)kit.getArmor().iterator().next().getItemMeta()).getOwner());

                    item.setItemMeta(itemM);
                }
            inventory.addItem(item);
            }
        }
        p.openInventory(inventory);
    }

    public void openKitSelector(Player p) {
        int i = 0;
        for (Kit kit : kits) {
            i++;
        }
        Inventory inventory = Bukkit.createInventory(p, 6*9, "Kit Selector");

        ItemStack freeKits = new ItemStack(Material.WOOD_SWORD);
        ItemMeta freeKitsM = freeKits.getItemMeta();
        freeKitsM.setDisplayName("§c§lFree Kits");
        freeKitsM.setLore(Arrays.asList(
                "§7These kits are free",
                "§7to use. Any time, any day!"));
        freeKits.setItemMeta(freeKitsM);
        inventory.setItem(1, freeKits);

        ItemStack payKits = new ItemStack(Material.GOLD_SWORD);
        ItemMeta payKitsM = payKits.getItemMeta();
        payKitsM.setDisplayName("§e§lPay to use Kits");
        payKitsM.setLore(Arrays.asList(
                "§7These kits you pay every",
                "§7time you use. Don't waste",
                "§7your coins!"));
        payKits.setItemMeta(payKitsM);
        inventory.setItem(3, payKits);

        ItemStack buyKits = new ItemStack(Material.IRON_SWORD);
        ItemMeta buyKitsM = buyKits.getItemMeta();
        buyKitsM.setDisplayName("§f§lBuyable Kits");
        buyKitsM.setLore(Arrays.asList(
                "§7These kits you buy once",
                "§7and you keep it forever.",
                "§7Try buy the most expensive!"));
        buyKits.setItemMeta(buyKitsM);
        inventory.setItem(5, buyKits);

        ItemStack donorKits = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta donorKitsM = donorKits.getItemMeta();
        donorKitsM.setDisplayName("§b§lDonor Kits");
        donorKitsM.setLore(Arrays.asList(
                "§7These kits you buy in the",
                "§7store. (store.beastbrawl.us).",
                "§7These kits are the most fun!"));
        donorKits.setItemMeta(donorKitsM);
        inventory.setItem(7, donorKits);

        int slot = 9;
        for (Kit kit : kits) {
                ItemStack item = kit.getGUIItem(p.getUniqueId());

                if(bb.using18.contains(p.getUniqueId())){
                    item.setType(Material.SKULL_ITEM);
                    item.setDurability((short) SkullType.PLAYER.ordinal());

                    SkullMeta itemM = (SkullMeta) item.getItemMeta();
                    itemM.setOwner(((SkullMeta)kit.getArmor().iterator().next().getItemMeta()).getOwner());

                    item.setItemMeta(itemM);
                }
            inventory.setItem(slot, item);
            slot++;
        }

        p.openInventory(inventory);
    }

    public void selectKit(Kit kit, Player p) {
        if(kit instanceof Dolphin){
            bb.isGoodSwimmer.put(p.getUniqueId(), (float)1.025);
        }
        if(kit instanceof Parrot){
            p.setAllowFlight(true);
            bb.canDoublejump.add(p.getUniqueId());
        }

        if(kit instanceof Turtle){
            bb.hitPercentage.put(p.getUniqueId(), 90);
        }
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        for(PotionEffect effect : kit.getPotionEffects()){
            p.addPotionEffect(effect);
        }
        
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        
        Collection<ItemStack> armor = kit.getArmor();
        Collection<ItemStack> items = kit.getItems();
        for(ItemStack item : items){
            p.getInventory().addItem(item);
        }
        p.getInventory().addItem(kit.getAbility(p.getUniqueId()));
        p.getInventory().setBoots((ItemStack)armor.toArray()[3]);
        p.getInventory().setLeggings((ItemStack)armor.toArray()[2]);
        p.getInventory().setChestplate((ItemStack)armor.toArray()[1]);
        p.getInventory().setHelmet((ItemStack)armor.toArray()[0]);

        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);


        //SOUPS
        for (int j = 0; j < 34; j++){
            TogglesManager toggles = new TogglesManager();

            if(toggles.getPotions()){
                p.getInventory().addItem(Utils.potion());
            }
            else{
                p.getInventory().addItem(Utils.soup());
            }
        }

        p.teleport(Utils.getRandomLocation());
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
        sendSelectedMessage(kit, p);

        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(kit.getColorCode() + "§lPlayer Tracker");
        meta.setLore(Arrays.asList(kit.getColorCode() + "Hold §7to locate the nearest player!"));
        item.setItemMeta(meta);
        p.getInventory().setItem(8, item);
        p.updateInventory();
        kit.selectedAdd(p);
    }

    public Kit getKit(Player p) {
        for (Kit kit : kits) {
            if (kit.isSelected(p.getUniqueId())) {
                return kit;
            }
        }
        return null;
    }

    public boolean canAfford(Player p, Kit k){


        return false;
    }

    public ArrayList<Kit> getKits() {
        return kits;
    }

    

    public void preview(Kit kit, Player p) {

        Inventory preview = Bukkit.createInventory(null, 9*6, "§8Kit Preview > " + kit.getColorCode() + "§l" + kit.getName());

        int slot = 11;
        for (ItemStack item : kit.getItems()) {
            slot++;
            preview.setItem(slot, item);
        }

        preview.setItem(23, kit.getAbility(p.getUniqueId()));

        slot = 1;
        for (ItemStack item : kit.getArmor()) {
            slot = slot + 9;
            preview.setItem(slot, item);
        }

        ConfigManager config = new ConfigManager(p.getUniqueId());

        slot = 30;
        for (int i = 1; i < 6; i++) {

            ItemStack level = new ItemStack(Material.STAINED_GLASS_PANE);
            ItemMeta levelM = level.getItemMeta();

            String topLore = "";
            if(config.kitLevel(kit) > i){
                level.setDurability((short)5);
                topLore = "§a§lUNLOCKED";
            }else{
                level.setDurability((short)14);
                topLore = "§c§lBUY: §7" + Integer.toString((int)((kit.getGUIID() + 1)*(Math.pow(5,i)*100)));
            }
            levelM.setLore(Arrays.asList(topLore, "", "§ePerks", ("§7Reduces cooldown to " + Integer.toString((kit.cooldownTime() * 2) -  ((kit.cooldownTime() / 5) * (i)))), "§7seconds."));
            levelM.setDisplayName(kit.getColorCode() + "§l" + kit.getName() + " §7" + Utils.IntegerToRomanNumeral(i + 1));
            level.setItemMeta(levelM);
            level.setAmount(i + 1);
            preview.setItem(slot, level);
            slot++;
        }
        ItemStack prestige = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta prestigeM = prestige.getItemMeta();
        prestigeM.setDisplayName(kit.getColorCode() + "§l" + kit.getName() + " §6Prestige");
        prestigeM.setLore(Arrays.asList("§c§lBUY: §71000000"));
        prestige.setItemMeta(prestigeM);
        preview.setItem(41, prestige);



        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backM = back.getItemMeta();
        backM.setDisplayName("§8§lBack");
        backM.setLore(Arrays.asList("§7Return to main kit menu."));
        back.setItemMeta(backM);
        preview.setItem(preview.getSize()-5, back);

        p.openInventory(preview);
        p.updateInventory();
    }


    public void removeKit(Player p){
        for(Kit kit : kits){
            if(kit.isSelected(p.getUniqueId())){
                if(kit instanceof Dolphin){
                    bb.isGoodSwimmer.remove(p.getUniqueId());
                }
                if(kit instanceof Parrot){
                    bb.canDoublejump.remove(p.getUniqueId());
                }
                if(kit instanceof Turtle){
                    bb.hitPercentage.remove(p.getUniqueId());
                }
            }
        }



        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.setAllowFlight(false);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);
        for(Kit kit : kits){
            kit.selectedRemove(p);
        }
    }

    public void clearPlayer(Player p){
        if(p.getActivePotionEffects() != null) {
            for (PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
            }
        }
        Kit selectedKit = getKit(p);

        p.teleport(bb.spawn);
        p.setVelocity(new Vector(0, 0, 0));
        p.setFireTicks(0);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().setItem(4, Utils.getKitSelector());

        if(selectedKit != null) {
            selectedKit.resetCooldown(p.getUniqueId());
            removeKit(p);
        }


    }

    public void sendSelectedMessage(Kit kit, Player p) {
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        int i = 0;
        for (String s : kit.skullStrings()) {
            i++;
            String a = "";
            if (i == 1)
                a = kit.getColorCode() + "  §m§l-------------------------------------";
            if (i == 2)
                a = "              ";
            if (i == 3)
                a = "                       §7§lYOU SELECTED " + kit.getColorCode() + "§l" + kit.getName().toUpperCase() + "§7§l!";
            if (i == 4)
                a = "              ";
            if (i == 5)
                a = "  §7§oStop playing kit by using /kill, right-click soups for";
            if (i == 6)
                a = "      §7§o3§c§l❤§7§o. Use player tracker to find players!";
            if (i == 7)
                a = "";
            if (i == 8)
                a = kit.getColorCode() + "  §m§l-------------------------------------";
            p.sendMessage(s + a);
        }
    }
}