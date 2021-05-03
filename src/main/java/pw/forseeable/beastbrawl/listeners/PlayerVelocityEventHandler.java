package pw.forseeable.beastbrawl.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerVelocityEvent;
import pw.forseeable.beastbrawl.BeastBrawl;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerVelocityEventHandler implements Listener {
    public PlayerVelocityEventHandler(BeastBrawl bb) {
    }
    
    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent e){
        Player p = e.getPlayer();
    }
}
