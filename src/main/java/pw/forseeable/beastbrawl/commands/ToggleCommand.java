package pw.forseeable.beastbrawl.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pw.forseeable.beastbrawl.managers.TogglesManager;
import pw.forseeable.beastbrawl.utils.Prefix;

/**
 * Ur boi forseeable made this class #gang
 */
public class ToggleCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        TogglesManager toggles = new TogglesManager();
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length >= 1) {
                if (args[0].toLowerCase() != "list") {
                    if (args.length >= 1) {
                        switch (args[0].toLowerCase()) {
                            case "list":
                                p.sendMessage(Prefix.INFO + "Available toggles: §lPotions§7, §lKothActive§7, §lDoubleHearts§7.");
                            break;
                            case "doublehearts":
                                toggles.setDoubleHearts(!toggles.getDoubleHearts());
                                if(toggles.getDoubleHearts()){
                                    p.sendMessage(Prefix.SUCCESS + "Toggle §lDoubleHearts§a set to §lTRUE§a.");
                                }else{
                                    p.sendMessage(Prefix.SUCCESS + "Toggle §lDoubleHearts§a set to §c§lFALSE§a.");
                                }
                                break;
                            case "kothactive":
                            toggles.setKOTHStart(!toggles.getKOTHStart());
                            if(toggles.getKOTHStart()){
                                p.sendMessage(Prefix.SUCCESS + "Toggle §lKothActive§a set to §lTRUE§a.");
                            }else{
                                p.sendMessage(Prefix.SUCCESS + "Toggle §lKothActive§a set to §c§lFALSE§a.");
                            }
                                break;
                            case "potions":
                            toggles.setPotions(!toggles.getPotions());
                            if(toggles.getPotions()){
                                p.sendMessage(Prefix.SUCCESS + "Toggle §lPotions§a set to §lTRUE§a.");
                            }else{
                                p.sendMessage(Prefix.SUCCESS + "Toggle §lPotions§a set to §c§lFALSE§a.");
                            }
                                break;
                            default:
                                p.sendMessage(Prefix.ERROR + "No toggle with the name §l" + args[0] + "§c found.");
                                break;
                        }
                    } else {
                        p.sendMessage(Prefix.ERROR + "Invalid syntax: /toggle [toggle/list]");
                    }
                } else {
                }
            }
            else{

            }
        }
        return false;
    }
}
