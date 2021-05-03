package pw.forseeable.beastbrawl.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.utils.Utils;


/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerMoveEventHandler implements Listener {
    private BeastBrawl bb;

    public PlayerMoveEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if(e.getPlayer() instanceof Player) {
            Player p = e.getPlayer();

            if(e.getTo().getBlock().isLiquid() && bb.isGoodSwimmer.containsKey(p.getUniqueId())){
                Vector direction = e.getPlayer().getLocation().getDirection().multiply(bb.isGoodSwimmer.get(p.getUniqueId()));
                e.getPlayer().setVelocity(direction.add(new org.bukkit.util.Vector(0, 0, 0)));
            }

            if(p.getGameMode() != GameMode.CREATIVE) {
                if(bb.getKitManager().getKit(p) == null && e.getTo().getBlockY() < 100){
                    p.teleport(bb.getSpawn());
                    bb.getKitManager().openKitSelector(p);
                }
                if(bb.getKitManager().getKit(p) != null && e.getTo().getBlockY() < 40){
                    p.teleport(Utils.getRandomLocation());
                }
                if(bb.getKitManager().getKit(p) != null && e.getTo().getBlockY() > 115){
                    p.teleport(e.getFrom());
                }

                boolean movedSafely = true;

                int y = 255;
                for (int cy = y; cy > 0; cy--) {
                    Block block = p.getWorld().getBlockAt(e.getTo().getBlockX(), cy, e.getTo().getBlockZ());
                    if (block.getType() == Material.GOLD_ORE) {
                        movedSafely = false;
                        cy = 0;
                    }
                }
                if(!movedSafely)
                p.teleport(e.getFrom());
            }
        }
    }
}