package pw.forseeable.beastbrawl.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.buy.Wolf;
import pw.forseeable.beastbrawl.kits.donor.Snake;
import pw.forseeable.beastbrawl.kits.pay.Bear;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerInteractEntityEventHandler implements Listener {

    private BeastBrawl bb;

    public PlayerInteractEntityEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e){
        if(e.getRightClicked() instanceof Player) {
            Player c = (Player) e.getRightClicked();
            Player p = e.getPlayer();

            //TARGETTED KITS
            if (bb.getKitManager().getKit(p) instanceof Wolf) {
                if (p.getInventory().getItemInHand() != null) {
                    if (p.getInventory().getItemInHand().getType() == Material.BONE) {
                        if (!bb.getKitManager().getKit(p).inCooldown(p.getUniqueId())) {
                            Wolf kit = (Wolf) bb.getKitManager().getKit(p);
                            kit.targeted.put(p.getUniqueId(), c.getUniqueId());
                        }
                    }
                }
            }
            if (bb.getKitManager().getKit(p) instanceof Bear) {
                if (p.getInventory().getItemInHand() != null) {
                    if (p.getInventory().getItemInHand().getType() == Material.COAL) {
                        if (!bb.getKitManager().getKit(p).inCooldown(p.getUniqueId())) {
                            Bear kit = (Bear) bb.getKitManager().getKit(p);
                            kit.targeted.put(p.getUniqueId(), c.getUniqueId());
                        }
                    }
                }
            }
            if (bb.getKitManager().getKit(p) instanceof Snake) {
                if (p.getInventory().getItemInHand() != null) {
                    if (p.getInventory().getItemInHand().getType() == Material.SUGAR_CANE) {
                        if (!bb.getKitManager().getKit(p).inCooldown(p.getUniqueId())) {
                            Snake kit = (Snake) bb.getKitManager().getKit(p);
                            kit.targeted.put(p.getUniqueId(), c.getUniqueId());
                        }
                    }
                }
            }
        }
    }
}
