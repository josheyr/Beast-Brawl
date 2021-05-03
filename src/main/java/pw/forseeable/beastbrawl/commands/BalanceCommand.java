package pw.forseeable.beastbrawl.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.managers.ConfigManager;
import pw.forseeable.beastbrawl.utils.Prefix;

/**
 * Ur boi forseeable made this class #gang
 */
public class BalanceCommand implements CommandExecutor{

    private BeastBrawl bb;

    public BalanceCommand(BeastBrawl bb){
        this.bb = bb;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length <= 1){
            if(args.length == 0){
                if(sender instanceof Player) {
                    Player p = (Player)sender;
                    ConfigManager config = new ConfigManager(p.getUniqueId());
                    sender.sendMessage(Prefix.INFO + "Your balance is: §l" + (int) config.getBalance() + "§7.");
                }else{
                    sender.sendMessage(Prefix.ERROR + "Invalid syntax: /bal <player>");
                }
            }else {
                if (Bukkit.getServer().getPlayer(args[0]) != null) {
                    ConfigManager config = new ConfigManager(Bukkit.getServer().getPlayer(args[0]).getUniqueId());
                    sender.sendMessage(Prefix.INFO + Bukkit.getServer().getPlayer(args[0]).getName() + "'s balance is: §l" + (int) config.getBalance() + "§7.");
                } else
                    //TODO replace bcs deprecated :(
                    if (Bukkit.getServer().getOfflinePlayer(args[0]) != null) {
                        ConfigManager config = new ConfigManager(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId());
                        sender.sendMessage(Prefix.INFO + Bukkit.getServer().getOfflinePlayer(args[0]).getName() + "'s balance is: §l" + (int) config.getBalance() + "§7.");
                    } else {
                        sender.sendMessage(Prefix.ERROR + "Player §l" + args[0] + " §cnot found.");

                    }
            }
        }else{
            if(sender instanceof Player) {
                sender.sendMessage(Prefix.ERROR + "Invalid syntax: /bal [player]");
            }else {
                sender.sendMessage(Prefix.ERROR + "Invalid syntax: /bal <player>");
            }
        }
        return false;
    }
}
