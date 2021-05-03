package pw.forseeable.beastbrawl.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.forseeable.beastbrawl.BeastBrawl;

/**
 * Ur boi forseeable made this class #gang
 */
public class KitCommand implements CommandExecutor {
    private BeastBrawl bb;
    public KitCommand(BeastBrawl bb) {
        this.bb = bb;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        bb.getKitManager().openKitSelector(p);
        return true;
    }
}
