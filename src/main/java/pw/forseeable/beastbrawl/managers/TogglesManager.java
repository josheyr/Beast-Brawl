package pw.forseeable.beastbrawl.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Ur boi forseeable made this class #gang
 */
public class TogglesManager {
    Storage storage;

    public TogglesManager() {
        this.storage = new TogglesManager.Storage((BeastBrawl) Bukkit.getPluginManager().getPlugin("BeastBrawl"));
    }

    public boolean getKOTHStart(){
        return storage.getConfig().getBoolean("kothstart", false);
    }
    

    public void setKOTHStart( boolean bool){
        storage.getConfig().set("kothstart", bool);
        storage.saveConfig();
    }

    public void updateKOTHStart(){

    }

    public boolean getDoubleHearts(){
        return storage.getConfig().getBoolean("doublehearts", false);
    }

    public void setDoubleHearts( boolean bool){
        storage.getConfig().set("doublehearts", bool);
        storage.saveConfig();
        updateDoubleHearts();
    }

    public void updateDoubleHearts(){
        if(getDoubleHearts()){
            for(Player p: Bukkit.getOnlinePlayers()){
                p.setMaxHealth(40);
            }
        }else{
            for(Player p: Bukkit.getOnlinePlayers()){
                p.setMaxHealth(20);
            }
        }
    }

    public boolean getPotions(){
        return storage.getConfig().getBoolean("potions", false);
    }

    public void setPotions( boolean bool){
        storage.getConfig().set("potions", bool);
        storage.saveConfig();
        updatePotions();
    }

    public void updatePotions(){
        if(!getPotions()){
            for(Player p : Bukkit.getOnlinePlayers()) {
                for (int i =0; i < p.getInventory().getContents().length; i++) {
                    if (!p.getInventory().contains(i)) {
                        try{
                            if(p.getInventory().getContents()[i].getItemMeta().getDisplayName().contains("7Potion")){
                                p.getInventory().setItem(i, Utils.soup());
                            }
                        }catch(Exception ex){

                        }
                    }
                }
            }
        }else{
            for(Player p : Bukkit.getOnlinePlayers()) {
                for (int i =0; i < p.getInventory().getContents().length; i++) {
                    if (!p.getInventory().contains(i)) {
                        try{
                            if(p.getInventory().getContents()[i].getItemMeta().getDisplayName().contains("7Soup")){
                                p.getInventory().setItem(i, Utils.potion());

                            }
                        }catch(Exception ex){

                        }
                    }
                }
            }
        }
    }

        public class Storage {
            BeastBrawl bb;

            public Storage(BeastBrawl bb) {
                this.bb = bb;
            }

            private FileConfiguration config = null;
            private File file = null;

            public void reloadConfig() {
                if (file == null) {
                    file = new File(bb.getDataFolder() + "/toggles.yml");
                }
                config = YamlConfiguration.loadConfiguration(file);
            }

            public FileConfiguration getConfig() {
                if (config == null) {
                    reloadConfig();
                }
                return config;
            }

            public void saveConfig() {
                if (config == null || file == null) {
                    return;
                }
                try {
                    getConfig().save(file);
                } catch (IOException ex) {
                    bb.getLogger().log(Level.SEVERE, "Could not save config to toggles.yml", ex);
                }
            }
    }
}
