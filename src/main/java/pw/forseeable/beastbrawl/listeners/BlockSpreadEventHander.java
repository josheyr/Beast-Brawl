package pw.forseeable.beastbrawl.listeners;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;
import pw.forseeable.beastbrawl.BeastBrawl;

/**
 * Ur boi forseeable made this class #gang
 */
public class BlockSpreadEventHander implements Listener {
    public BlockSpreadEventHander(BeastBrawl bb) {
    }

    public void onBlockSpread(BlockSpreadEvent e){
        if(e.getSource().getType() == Material.VINE){
            e.setCancelled(true);
        }
    }
}
