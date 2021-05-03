package pw.forseeable.beastbrawl.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.managers.ConfigManager;
import pw.forseeable.beastbrawl.utils.Prefix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ur boi forseeable made this class #gang
 */
public class CoinsCommand implements CommandExecutor {

    private BeastBrawl bb;

    public CoinsCommand(BeastBrawl bb){
        this.bb = bb;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("bb.admin")) {
                if (args.length != 0) {
                    String subcommand = args[0];
                    List<String> subargs = new ArrayList<>();
                    Collections.addAll(subargs, args);
                    subargs.remove(0);

                    switch (subcommand.toLowerCase()) {
                        case "set":
                            if(args.length == 3){
                                if(Bukkit.getServer().getPlayer(args[1]) != null) {
                                    ConfigManager config = new ConfigManager(Bukkit.getServer().getPlayer(args[1]).getUniqueId());
                                    config.setBalance(0);
                                    try {
                                        int i;
                                        i = Integer.parseInt(args[2]);
                                        config.setBalance(i);
                                        p.sendMessage(Prefix.SUCCESS + "Players balance set to §l" + i + "§a.");
                                    } catch (NumberFormatException e) {
                                        p.sendMessage(Prefix.ERROR + "Input §l" + args[2] + "§c is not a valid integer.");
                                    }
                                }else{
                                    p.sendMessage(Prefix.ERROR + "Player §l" + args[1] + "§c not found.");
                                }
                            }else{
                                p.sendMessage(Prefix.ERROR + "Invalid syntax: /coins set <player> <amount>");
                            }
                            break;

                        case "add":
                            if(args.length == 3){
                                if(Bukkit.getServer().getPlayer(args[1]) != null){
                                    ConfigManager config = new ConfigManager(Bukkit.getServer().getPlayer(args[1]).getUniqueId());
                                    try {
                                        int r = Integer.parseInt(args[2]);
                                        int b = (int)config.getBalance();
                                        int i = b + r;

                                        config.setBalance(i);

                                        p.sendMessage(Prefix.SUCCESS + "Players balance set to §l" + i + "§a.");
                                    }catch(NumberFormatException e){
                                        p.sendMessage(Prefix.ERROR + "Input §l" + args[2] + "§c is not a valid integer.");
                                    }
                                }else{
                                    p.sendMessage(Prefix.ERROR + "Player §l" + args[1] + "§c not found.");
                                }
                            }else{
                                p.sendMessage(Prefix.ERROR + "Invalid syntax: /coins add <player> <amount>");
                            }
                            break;

                        case "remove":
                            if(args.length == 3){
                                if(Bukkit.getServer().getPlayer(args[1]) != null){
                                    ConfigManager config = new ConfigManager(Bukkit.getServer().getPlayer(args[1]).getUniqueId());
                                    try {
                                        int r = Integer.parseInt(args[2]);
                                        int b = (int)config.getBalance();
                                        int i = b - r;

                                        config.setBalance(i);

                                        p.sendMessage(Prefix.SUCCESS + "Players balance set to §l" + i + "§a.");
                                    }catch(NumberFormatException e){
                                        p.sendMessage(Prefix.ERROR + "Input §l" + args[2] + "§c is not a valid integer.");
                                    }
                                }else{
                                    p.sendMessage(Prefix.ERROR + "Player §l" + args[1] + "§c not found.");
                                }
                            }else{
                                p.sendMessage(Prefix.ERROR + "Invalid syntax: /coins remove <player> <amount>");
                            }
                            break;

                        case "reset":
                            if(args.length == 2){
                                if(Bukkit.getServer().getPlayer(args[1]) != null){
                                    ConfigManager config = new ConfigManager(Bukkit.getServer().getPlayer(args[1]).getUniqueId());
                                    config.setBalance(0);
                                    p.sendMessage(Prefix.SUCCESS + "Players balance set to §l0§a.");
                                }else{
                                    p.sendMessage(Prefix.ERROR + "Player §l" + args[1] + "§c not found.");
                                }
                            }else{
                                p.sendMessage(Prefix.ERROR + "Invalid syntax: /coins reset <player>");
                            }
                            break;

                        default:
                            sendHelp(p);
                            break;
                    }
                } else {
                    sendHelp(p);
                }
            } else {
                p.sendMessage(Prefix.ERROR + "You do not have permission to use this command!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10, 10);
            }
        } else {
            sender.sendMessage("Go in game plz dad!");
        }
        return true;
    }

    private void sendHelp(Player p) {
        p.sendMessage(Prefix.ALERT + "Commands for Coins:");
        p.sendMessage(Prefix.INFO + "/coins set <player> <amount> - sets players coins");
        p.sendMessage(Prefix.INFO + "/coins add <player> <amount> - adds to players coins");
        p.sendMessage(Prefix.INFO + "/coins remove <player> <amount> - removes from players coins");
        p.sendMessage(Prefix.INFO + "/coins reset <player> - resets players coins to 0");
    }
}
