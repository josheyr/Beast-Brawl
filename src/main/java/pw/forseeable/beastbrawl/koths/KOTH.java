package pw.forseeable.beastbrawl.koths;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Ur boi forseeable made this class #gang
 */
public interface KOTH {
    Object getInstance();

    String getName();

    Block getBeacon();

    Location getCenter();

    Player getCapper();

    void setCapper(Player p);

    int getCountdown();

    void setCountdown(int i);

    boolean isCapping(Player p);

    boolean isActive();

    void becomeActive();
}
