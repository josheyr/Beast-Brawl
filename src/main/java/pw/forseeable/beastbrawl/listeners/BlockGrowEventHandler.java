package pw.forseeable.beastbrawl.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

/**
 * Ur boi forseeable made this class #gang
 */
public class BlockGrowEventHandler implements Listener {
    public void onBlockGrow(BlockGrowEvent e){
        e.setCancelled(true);
    }
}
