package pw.forseeable.beastbrawl.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import pw.forseeable.beastbrawl.BeastBrawl;

/**
 * Ur boi forseeable made this class #gang
 */
public class ServerListPingEventHandler implements Listener {

    BeastBrawl bb;

    public ServerListPingEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }
    
    @EventHandler
    public void onServerListPing(ServerListPingEvent e){
        e.setMotd("No-longer smorkeeables minecraft server :D | Also you're on " + bb.getName());
    }
}
