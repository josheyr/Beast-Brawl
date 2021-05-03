package pw.forseeable.beastbrawl.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.donor.Snake;

/**
 * Ur boi forseeable made this class #gang
 */
public class EntityDamageByEntityEventHandler implements Listener {

    private BeastBrawl bb;

    public EntityDamageByEntityEventHandler(BeastBrawl bb) {
       this.bb = bb;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            Player d = (Player)e.getDamager();
            Player v = (Player)e.getEntity();

            if(bb.hitPercentage.containsKey(v.getUniqueId())){
                Random rand = new Random();
                if((rand.nextInt(100) < bb.hitPercentage.get(v.getUniqueId()))){

                }else{
                    d.playSound(d.getLocation(), Sound.ANVIL_USE, 2, 1);
                    e.setCancelled(true);
                }
            }
            if(bb.getKitManager().getKit(d) == null || bb.getKitManager().getKit(v) == null){
                e.setCancelled(true);
            }else{
                if(bb.getKitManager().getKit(d) instanceof Snake){
                    if(d.getItemInHand().getType() == Material.WOOD_SWORD) {
                        v.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 1));
                    }
                }
            }
        }
    }
}
