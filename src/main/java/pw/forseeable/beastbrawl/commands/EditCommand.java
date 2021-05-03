package pw.forseeable.beastbrawl.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.forseeable.beastbrawl.managers.ConfigManager;
import pw.forseeable.beastbrawl.managers.ScoreboardManager;
import pw.forseeable.beastbrawl.utils.Utils;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.utils.Prefix;

/**
 * Ur boi forseeable made this class #gang
 */
public class EditCommand implements CommandExecutor {

    private BeastBrawl bb;

    public EditCommand(BeastBrawl bb) {
        this.bb = bb;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("bb.admin")){
                if(args.length == 0) {
                    //if(!p.hasKitSelected){
                        if (p.getGameMode() == GameMode.CREATIVE) {
                            p.setGameMode(GameMode.SURVIVAL);
                            bb.getKitManager().clearPlayer(p);
                            p.sendMessage(Prefix.SUCCESS + "Edit mode disabled.");
                            bb.canFly.remove(p.getUniqueId());
                            new ScoreboardManager(p.getPlayer().getUniqueId(), bb).destroy();
                            new ScoreboardManager(p.getPlayer().getUniqueId(), bb).create();
                        } else {
                            p.setGameMode(GameMode.CREATIVE);
                            p.getInventory().clear();
                            p.getInventory().setArmorContents(null);
                            p.getInventory().setItem(0, new ItemStack(Material.WOOD_AXE));
                            p.getInventory().setItem(3, new ItemStack(Material.ARROW));
                            p.getInventory().setItem(3, new ItemStack(Material.SULPHUR));
                            p.sendMessage(Prefix.SUCCESS + "Edit mode enabled.");
                            ConfigManager config = new ConfigManager(p.getUniqueId());
                            bb.getKitManager().clearPlayer(p);
                            config.setBalance(250.0);
                            bb.canFly.add(p.getUniqueId());
                            new ScoreboardManager(p.getPlayer().getUniqueId(), bb).destroy();
                            new ScoreboardManager(p.getPlayer().getUniqueId(), bb).create();
                        }
                    //}else{
                    //  p.sendMessage(Prefix.ERROR + "You mustn't have a kit selected to switch to this mode.");
                    //}
                }else{
                    p.sendMessage(Prefix.ERROR + "Invalid syntax: /edit");
                }
            }else{
                p.sendMessage(Prefix.ERROR + "You do not have permission to use this command!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10, 10);
            }
        }
        return false;
    }
}
