package pw.forseeable.beastbrawl.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.util.Vector;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.managers.ScoreboardManager;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerDeathEventHandler implements Listener {
    private BeastBrawl bb;

    public PlayerDeathEventHandler(BeastBrawl bb){
        this.bb = bb;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        bb.getKitManager().removeKit(p);

        p.setHealth(p.getMaxHealth());

        bb.getKitManager().clearPlayer(p);
        new ScoreboardManager(p.getUniqueId(), bb).destroy();
        new ScoreboardManager(p.getUniqueId(), bb).create();
    }
}
