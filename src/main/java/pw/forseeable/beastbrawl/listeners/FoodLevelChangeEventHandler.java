package pw.forseeable.beastbrawl.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import pw.forseeable.beastbrawl.BeastBrawl;

/**
 * Ur boi forseeable made this class #gang
 */
public class FoodLevelChangeEventHandler implements Listener {
    public FoodLevelChangeEventHandler(BeastBrawl bb) {
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            p.setFoodLevel(20);
            e.setCancelled(true);
        }
    }
}
