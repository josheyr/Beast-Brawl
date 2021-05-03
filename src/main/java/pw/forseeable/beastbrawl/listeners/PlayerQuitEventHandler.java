package pw.forseeable.beastbrawl.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.managers.ScoreboardManager;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerQuitEventHandler implements Listener {
    private BeastBrawl bb;

    public PlayerQuitEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        new ScoreboardManager(e.getPlayer().getUniqueId(), this.bb).destroy();
        Kit kit = bb.getKitManager().getKit(e.getPlayer());
        if (kit!=null) {
            bb.getKitManager().removeKit(e.getPlayer());
        }
        bb.canFly.remove(e.getPlayer().getUniqueId());
        bb.using18.remove(e.getPlayer().getUniqueId());
        bb.hitPercentage.remove(e.getPlayer().getUniqueId());

        e.setQuitMessage("");
    }
}