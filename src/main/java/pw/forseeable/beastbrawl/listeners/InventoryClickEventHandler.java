package pw.forseeable.beastbrawl.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.managers.ConfigManager;
import pw.forseeable.beastbrawl.utils.Prefix;

import static pw.forseeable.beastbrawl.utils.Utils.sendLinkMessage;

/**
 * **************************************************************************************
 * Copyright Molten Rock Ltd (c) 2017. All Right Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of Molten Rock Ltd. Distribution, taking snippets, or
 * claiming any contents as your own will break the terms of licence, and void any
 * agreements with you, the third party.
 * Regards Molten Rock Ltd - @MoltenRockLtd - https://moltenrock.io
 * ***************************************************************************************
 */
public class InventoryClickEventHandler implements Listener {

    private BeastBrawl bb;

    public InventoryClickEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem() != null) {
                if (p.getGameMode() != GameMode.CREATIVE && e.getCurrentItem().getType() != Material.MUSHROOM_SOUP && e.getCurrentItem().getType() != Material.BOWL && e.getCurrentItem().getType() != Material.GOLD_NUGGET&& e.getCurrentItem().getType() != Material.AIR) {
                    //e.setCancelled(true);
                }

            }

            if (e.getInventory().getName().contains("Kit Selector")) {
                e.setCancelled(true);
                if (e.getClick() == ClickType.RIGHT || e.getClick() == ClickType.LEFT) {
                    if (e.getCurrentItem().getType() == Material.WOOD_SWORD) {
                        bb.getKitManager().openFreeKitSelector(p);
                    }
                    if (e.getCurrentItem().getType() == Material.GOLD_SWORD) {
                        bb.getKitManager().openPayKitSelector(p);
                    }
                    if (e.getCurrentItem().getType() == Material.IRON_SWORD) {
                        bb.getKitManager().openBuyKitSelector(p);
                    }
                    if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                        bb.getKitManager().openDonorKitSelector(p);
                    }
                }
            }

            if (e.getInventory().getName().contains("Kit Selector")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getType() == Material.ARROW) {
                    bb.getKitManager().openKitSelector(p);
                } else {
                    for (Kit kit : bb.getKitManager().getKits()) {
                        if (kit.getGUIItem(p.getPlayer().getUniqueId()).getItemMeta().getDisplayName().equals(e.getCurrentItem().getItemMeta().getDisplayName())) {
                            if (kit != null) {
                                if (e.getClick() == ClickType.LEFT) {
                                    ConfigManager config = new ConfigManager(p.getUniqueId());

                                    if (kit.getGUIID() == 0) {
                                        bb.getKitManager().selectKit(kit, p);
                                    }

                                    if (kit.getGUIID() == 1) {
                                        if (config.getBalance() >= kit.getCost()) {
                                            bb.getKitManager().selectKit(kit, p);
                                            config.setBalance(config.getBalance() - kit.getCost());
                                        } else {
                                            p.sendMessage(Prefix.ERROR + "You need " + (int)kit.getCost() + " §ccoins for this kit.");
                                        }
                                    }

                                    if (kit.getGUIID() == 2) {
                                        if (config.isUnlocked(kit)) {
                                            bb.getKitManager().selectKit(kit, p);
                                        } else {
                                            if (config.getBalance() >= kit.getCost()) {
                                                p.sendMessage(Prefix.SUCCESS + "You successfully purchased the " + kit.getColorCode() + "§l" + kit.getName() + " §akit for " + kit.getCost() + " §acoins.");
                                                config.setBalance(config.getBalance() - kit.getCost());
                                                config.setUnlocked(kit, true);
                                                bb.getKitManager().openBuyKitSelector(p);
                                            } else {
                                                p.sendMessage(Prefix.ERROR + "You need " + (int)kit.getCost() + " §ccoins for this kit.");
                                            }
                                        }

                                    }

                                    if(kit.getGUIID() == 3){
                                        if(p.hasPermission("bb.kit." + kit.getName().toLowerCase())){
                                            bb.getKitManager().selectKit(kit, p);
                                        }else{
                                            sendLinkMessage(p, Prefix.ERROR + "You do not own this kit, buy it in the store at §nstore." + bb.getDomain() + "§c.", bb.getStoreLink());
                                        }
                                    }
                                }else if(e.getClick() == ClickType.RIGHT){
                                    bb.getKitManager().preview(kit, p);
                                }else if(e.getClick() == ClickType.MIDDLE){
                                    ConfigManager config = new ConfigManager(p.getUniqueId());

                                    if (kit.getGUIID() == 0) {
                                        bb.getKitManager().selectKit(kit, p);
                                    }

                                    if (kit.getGUIID() == 1) {
                                        if (config.getBalance() >= kit.getCost()) {
                                            bb.getKitManager().selectKit(kit, p);
                                            config.setBalance(config.getBalance() - kit.getCost());
                                        } else {
                                            p.sendMessage(Prefix.ERROR + "You need " + (int)kit.getCost() + " §ccoins for this kit.");
                                        }
                                    }

                                    if (kit.getGUIID() == 2) {
                                        if (config.isUnlocked(kit)) {
                                            bb.getKitManager().selectKit(kit, p);
                                        } else {
                                            if (config.getBalance() >= kit.getCost()) {
                                                p.sendMessage(Prefix.SUCCESS + "You successfully purchased the " + kit.getColorCode() + "§l" + kit.getName() + " §akit for " + kit.getCost() + " §acoins.");
                                                config.setBalance(config.getBalance() - kit.getCost());
                                                config.setUnlocked(kit, true);
                                                bb.getKitManager().openBuyKitSelector(p);
                                            } else {
                                                p.sendMessage(Prefix.ERROR + "You need " + (int)kit.getCost() + " §ccoins for this kit.");
                                            }
                                        }

                                    }

                                    if(kit.getGUIID() == 3){
                                        if(p.hasPermission("bb.kit." + kit.getName().toLowerCase())){
                                            bb.getKitManager().selectKit(kit, p);
                                        }else{
                                            sendLinkMessage(p, Prefix.ERROR + "You do not own this kit, buy it in the store at §nstore." + bb.getDomain() + "§c.", bb.getStoreLink());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (e.getInventory().getName().contains("Preview")) {
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.ARROW) {
                    bb.getKitManager().openKitSelector(p);
                }
            }
            p.updateInventory();
        }
    }
}
