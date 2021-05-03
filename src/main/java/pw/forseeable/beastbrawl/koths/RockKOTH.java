package pw.forseeable.beastbrawl.koths;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Ur boi forseeable made this class #gang
 */
public class RockKOTH implements KOTH {

    UUID capper;
    int countdown;

    @Override
    public RockKOTH getInstance(){
        return this;
    }

    @Override
    public String getName() {
        return "Rock";
    }

    @Override
    public Block getBeacon() {
        Block b = Bukkit.getWorld("world").getBlockAt(119,61,-31);
        return b;
    }

    @Override
    public Location getCenter() {
        Location l = new Location(Bukkit.getWorld("world"), 120, 62, -30);
        return l;
    }

    @Override
    public Player getCapper() {
        if (capper != null) {
            return Bukkit.getPlayer(capper);
        }else {
            return null;
        }
    }

    @Override
    public void setCapper(Player p) {
        if(p != null) {
            capper = p.getUniqueId();
        }else{
            capper = null;
        }
    }

    @Override
    public boolean isCapping(Player p) {
        if(capper == p.getUniqueId())
            return true;
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void becomeActive() {

    }

    @Override
    public int getCountdown(){
        return countdown;
    }

    @Override
    public void setCountdown(int i) {
        countdown = i;
    }
}
