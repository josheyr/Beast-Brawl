package pw.forseeable.beastbrawl.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.koths.KOTH;
import pw.forseeable.beastbrawl.koths.PondKOTH;
import pw.forseeable.beastbrawl.koths.RockKOTH;
import pw.forseeable.beastbrawl.koths.TowerKOTH;
import pw.forseeable.beastbrawl.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Ur boi forseeable made this class #gang
 */
public class KOTHManager {
    public ArrayList<KOTH> koths = new ArrayList<>();
    BeastBrawl bb;
    ArrayList<KOTH> currentkoths = new ArrayList<>();

    int kothTimer = 300;

    RockKOTH rockKOTH;
    TowerKOTH towerKOTH;
    PondKOTH pondKOTH;

    public KOTHManager(BeastBrawl bb) {
        this.bb = bb;
        rockKOTH = new RockKOTH();
        towerKOTH = new TowerKOTH();
        pondKOTH = new PondKOTH();
        koths.add(rockKOTH);
        koths.add(pondKOTH);
        koths.add(towerKOTH);
    }


    public void startKOTHScheduler() {
        Random r = new Random();
        activateKOTH(koths.get(r.nextInt(koths.size())));
    }

    public void terminateKOTH(KOTH koth) {
        koth.getBeacon().setType(Material.COAL_BLOCK);
        currentkoths.clear();
    }

    public void activateKOTH(KOTH koth){
        koth.getBeacon().setType(Material.BEACON);
        currentkoths.add(koth);
        koth.setCountdown(kothTimer);
    }

    public void capKOTH(KOTH k){
        terminateKOTH(k);
        k.getCapper().sendMessage("you capped the koth lol xd");
        k.getBeacon().setType(Material.COAL_BLOCK);
        currentkoths.clear();
        bb.getServer().getScheduler().runTaskLater(bb, () -> {
            Random r = new Random();
            activateKOTH(koths.get(r.nextInt(koths.size())));
        }, 120L);
    }

    public void checkKOTH(){
        for(KOTH k : currentkoths){
            ArrayList<Entity> entities = new ArrayList<>();

            for(Player p : Bukkit.getOnlinePlayers()) {
                boolean movedSafely = true;

                int y = 255;
                for (int cy = y; cy > 0; cy--) {
                    Block block = Bukkit.getWorld("world").getBlockAt(p.getLocation().getBlockX(), cy, p.getLocation().getBlockZ());
                    if (block.getType() == Material.IRON_BLOCK) {
                        movedSafely = false;
                        cy = 0;
                    }
                }
                if(!movedSafely && Utils.inRadius(k.getCenter(), p.getLocation(), 10)) {
                    entities.add(p);
                }
            }

            if(k.getCapper() != null){
                if(!entities.contains(k.getCapper())){
                    k.setCapper(null);
                    k.setCountdown(kothTimer);
                }else{
                    k.setCountdown(k.getCountdown() - 1);
                }
            }else{
                k.setCountdown(kothTimer);
                for(Entity e : entities) {
                    if(k.getCapper() == null){
                        if(e instanceof Player){
                            Player p = (Player)e;
                            k.setCapper(p);
                        }
                    }
                }
            }

            if(k.getCountdown() == 0){
                capKOTH(k);
            }
        }
    }
}
