package pw.forseeable.beastbrawl.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.utils.Prefix;

/**
 * Ur boi forseeable made this class #gang
 */
public class SuicideCommand implements CommandExecutor {
    public SuicideCommand(BeastBrawl bb) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                p.damage(999.0, p);
                p.sendMessage(Prefix.SUCCESS + "Killed self.");
            }else{
                p.sendMessage(Prefix.ERROR + "Invalid syntax: /suicide");
            }
        }
        return false;
    }
}
