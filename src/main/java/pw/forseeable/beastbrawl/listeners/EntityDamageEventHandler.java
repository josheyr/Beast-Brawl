package pw.forseeable.beastbrawl.listeners;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.buy.Kangaroo;

/**
 * Ur boi forseeable made this class #gang
 */
public class EntityDamageEventHandler implements Listener {
    private BeastBrawl bb;

    public EntityDamageEventHandler(BeastBrawl bb) {
        this.bb = bb;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();
            if(bb.getKitManager().getKit(p) != null){
                if(bb.getKitManager().getKit(p) instanceof Kangaroo){
                    if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                        if(e.getDamage() >= 5){
                            e.setCancelled(true);
                            int points = 12;
                            for (int i = 0; i < 360; i += 360/points) {

                                double angle = (i * Math.PI / 180);
                                double x = 2 * Math.cos(angle);
                                double z = 2 * Math.sin(angle);
                                Location loc = p.getLocation().add(x, 0, z);
                                loc.getWorld().playEffect(loc, Effect.STEP_SOUND, 3);
                                // p.getWorld().playEffect(loc.subtract(0,.2,0), Effect.VILLAGER_THUNDERCLOUD, 20, 0);
                            }
                            for(Entity nearbyEntity : p.getNearbyEntities(5, 5, 5)){
                                if(nearbyEntity instanceof Player){
                                    Player n = (Player)nearbyEntity;
                                    n.damage(e.getDamage() * 0.5, p);
                                }
                            }

                            for(Entity nearbyEntity : p.getNearbyEntities(4, 4, 4)){
                                if(nearbyEntity instanceof Player){
                                    Player n = (Player)nearbyEntity;
                                    n.damage(e.getDamage() * 0.5, p);
                                }
                            }

                            for(Entity nearbyEntity : p.getNearbyEntities(3, 3, 3)){
                                if(nearbyEntity instanceof Player){
                                    Player n = (Player)nearbyEntity;
                                    n.damage(e.getDamage(), p);
                                }
                            }

                            for(Entity nearbyEntity : p.getNearbyEntities(2, 2, 2)){
                                if(nearbyEntity instanceof Player){
                                    Player n = (Player)nearbyEntity;
                                    n.damage(e.getDamage(), p);
                                }
                            }

                        }else{

                        }
                    }
                }
            }else{
                e.setCancelled(true);
            }
        }
    }
}
