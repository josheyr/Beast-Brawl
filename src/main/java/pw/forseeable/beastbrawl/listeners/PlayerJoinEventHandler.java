package pw.forseeable.beastbrawl.listeners;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pw.forseeable.beastbrawl.managers.ConfigManager;
import pw.forseeable.beastbrawl.managers.ScoreboardManager;
import pw.forseeable.beastbrawl.managers.TogglesManager;
import pw.forseeable.beastbrawl.utils.Prefix;
import pw.forseeable.beastbrawl.utils.Utils;
import pw.forseeable.beastbrawl.BeastBrawl;

/**
 * Ur boi forseeable made this class #gang
 */
public class PlayerJoinEventHandler implements Listener {

    private BeastBrawl bb;

    public PlayerJoinEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        ConfigManager config = new ConfigManager(p.getUniqueId());


        new ScoreboardManager(e.getPlayer().getUniqueId(), bb).create();
        p.setAllowFlight(false);
        p.setGameMode(GameMode.SURVIVAL);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.teleport(bb.spawn);

        p.getInventory().setItem(4, Utils.getKitSelector());

        TogglesManager toggles = new TogglesManager();

        if(toggles.getDoubleHearts()){
            p.setMaxHealth(40);
        }else{
            p.setMaxHealth(20);
        }
        
        try {
            if(Utils.getProtocolVersion(p) == 47){
                bb.using18.add(p.getUniqueId());
            }else{
                bb.using18.remove(p.getUniqueId());
                p.sendMessage(Prefix.ERROR + "We recommend using 1.8 on our server so we can show you some of our cool features, expect bugs and issues on this version.");
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        

        p.setHealth(p.getMaxHealth());
    }
}
