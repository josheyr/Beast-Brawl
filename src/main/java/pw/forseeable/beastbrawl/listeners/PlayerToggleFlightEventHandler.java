package pw.forseeable.beastbrawl.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import pw.forseeable.beastbrawl.BeastBrawl;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerToggleFlightEventHandler implements Listener {
    private BeastBrawl bb;

    public PlayerToggleFlightEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e){
        if(bb.getCanFly().contains(e.getPlayer().getUniqueId())){

        }
        else if(bb.getCanDoublejump().contains(e.getPlayer().getUniqueId())) {
            org.bukkit.util.Vector direction = e.getPlayer().getLocation().getDirection().multiply(1.10);
            e.getPlayer().setVelocity(direction.add(new org.bukkit.util.Vector(0, 0.5, 0)));
            e.setCancelled(true);
            e.getPlayer().setAllowFlight(false);
            bb.getServer().getScheduler().runTaskLater(bb, () -> {
                if(bb.getCanDoublejump().contains(e.getPlayer().getUniqueId())) {
                    e.getPlayer().setAllowFlight(true);
                }
            }, 60);
            }
        else{
            e.setCancelled(true);
        }
    }
}
