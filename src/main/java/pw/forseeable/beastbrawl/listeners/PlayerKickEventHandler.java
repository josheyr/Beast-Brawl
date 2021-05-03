package pw.forseeable.beastbrawl.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerKickEventHandler implements Listener {

    private BeastBrawl bb;
    public PlayerKickEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e){
//        new ScoreboardManager(e.getPlayer().getUniqueId(), this.bb).destory();
        Kit kit = bb.getKitManager().getKit(e.getPlayer());
        if (kit!=null) {
            bb.getKitManager().removeKit(e.getPlayer());
        }
        e.setLeaveMessage("");
    }
}
