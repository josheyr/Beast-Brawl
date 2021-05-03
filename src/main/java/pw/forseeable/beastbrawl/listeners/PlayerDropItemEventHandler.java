package pw.forseeable.beastbrawl.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.util.Vector;
import pw.forseeable.beastbrawl.BeastBrawl;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerDropItemEventHandler implements Listener {
    public PlayerDropItemEventHandler(BeastBrawl bb){

    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){
        if(e.getItemDrop().getItemStack().getType() != Material.MUSHROOM_SOUP && e.getItemDrop().getItemStack().getType() != Material.BOWL && e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }else{
            e.getItemDrop().setVelocity(new Vector(0, 0.5, 0));
        }
    }
}
