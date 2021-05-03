package pw.forseeable.beastbrawl.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import pw.forseeable.beastbrawl.BeastBrawl;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerRespawnEventHandler implements Listener {
    private BeastBrawl bb;

    public PlayerRespawnEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        p.teleport(bb.spawn);
    }
}
