package pw.forseeable.beastbrawl.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.buy.Dolphin;
import pw.forseeable.beastbrawl.kits.buy.Kangaroo;
import pw.forseeable.beastbrawl.kits.buy.Wolf;
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
import pw.forseeable.beastbrawl.utils.Prefix;
import pw.forseeable.beastbrawl.utils.Utils;

import java.util.HashMap;
import java.util.UUID;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerInteractEventHandler implements Listener {

    private BeastBrawl bb;
    HashMap<UUID, Long> lastUsed = new HashMap<>();
    HashMap<String, Integer> ids = new HashMap<>();

    public PlayerInteractEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.PHYSICAL) {
            Block block = p.getWorld().getBlockAt(p.getLocation());
            if (block != null) {
                if (block.getType() == Material.CROPS) {
                    e.setCancelled(true);
                }
            }
        }else {


            if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(e.getClickedBlock().getState() instanceof Sign){
                    Sign s = (Sign)e.getClickedBlock().getState();
                    if(s.getLine(3).equals("§fI_I")){
                        Utils.openSoups(e.getPlayer());
                    }
                }
            }

            if (p.getItemInHand() != null) {
                if (p.getItemInHand().isSimilar(Utils.getKitSelector())) {
                    e.setCancelled(true);
                    bb.getKitManager().openKitSelector(p);
                }

                if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                    if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
                        if (p.getHealth() != p.getMaxHealth()) {
                            if (p.getHealth() + 6 > p.getMaxHealth()) {
                                p.setHealth(p.getMaxHealth());
                            } else {
                                p.setHealth(p.getHealth() + 6);
                            }
                            p.getItemInHand().setType(Material.BOWL);
                        }
                    }

                    Kit kit = bb.getKitManager().getKit(p);
                    if (kit instanceof Lion) {
                        if (p.getItemInHand().getType() == Material.PORK) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if (kit instanceof Giraffe) {
                        if (p.getItemInHand().getType() == Material.BLAZE_ROD) {
                            Location loc = p.getEyeLocation().add(0,1,0);

                            boolean found = false;
                            while(loc.getY() < 256)
                            {
                                if(loc.getBlock().getType() == Material.LEAVES || loc.getBlock().getType() == Material.LEAVES_2)
                                {
                                    handle(kit, p, null);
                                    found = true;
                                    break;
                                }
                                loc.add(0,1,0);
                            }
                            if(found == false){
                                p.sendMessage(Prefix.ERROR + "You must be beneath leaves to activate this ability!");
                                p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10, 10);
                            }
                            e.setCancelled(true);
                        }
                    } else if (kit instanceof Frog) {
                        if (p.getItemInHand().getType() == Material.INK_SACK) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if (kit instanceof Rhino) {
                        if (p.getItemInHand().getType() == Material.SUGAR) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if (kit instanceof Dolphin) {
                        if (p.getItemInHand().getType() == Material.RAW_FISH) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if (kit instanceof Kangaroo) {
                        if (p.getItemInHand().getType() == Material.FLOWER_POT_ITEM) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if (kit instanceof Snake) {
                        if (p.getItemInHand().getType() == Material.SUGAR_CANE) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if (kit instanceof Turtle) {
                        if (p.getItemInHand().getType() == Material.IRON_BARDING) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if(kit instanceof Wolf) {
                        if(p.getItemInHand().getType() == Material.BONE) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if(kit instanceof Bear) {
                        if(p.getItemInHand().getType() == Material.COAL) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if(kit instanceof Elephant) {
                        if(p.getItemInHand().getType() == Material.CLAY_BALL) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    } else if(kit instanceof Parrot) {
                        if(p.getItemInHand().getType() == Material.FEATHER) {
                            e.setCancelled(true);
                            handle(kit, p, null);
                        }
                    }
                }
            }
        }
    }

    private void handle(Kit kit, Player p, Block block) {
        if (kit.inCooldown(p.getUniqueId())) {
            p.sendMessage(Prefix.ERROR + "Your ability is still in cooldown!");
            p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10, 10);
        } else {
            kit.handleAbility(p, block, bb);
            if(kit instanceof Wolf || kit instanceof Snake || kit instanceof Bear)
            {

            }else{
            kit.cooldown(p.getUniqueId());
            }
        }
    }
}