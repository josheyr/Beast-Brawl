package pw.forseeable.beastbrawl.managers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Ur boi forseeable made this class #gang
 */
public class ConfigManager {
    /**
     * **************************************************************************************
     * Copyright Molten Rock Ltd (c) 2017. All Right Reserved.
     * Any code contained within this document, and any associated APIs with similar branding
     * are the sole property of Molten Rock Ltd. Distribution, taking snippets, or
     * claiming any contents as your own will break the terms of licence, and void any
     * agreements with you, the third party.
     * Regards Molten Rock Ltd - @MoltenRockLtd - https://moltenrock.io
     * ***************************************************************************************
     */
    Storage storage;

    public ConfigManager(UUID uuid) {
        this.storage = new Storage(uuid, (BeastBrawl) Bukkit.getPluginManager().getPlugin("BeastBrawl"));
    }

    public int getKills() {
        return storage.getConfig().getInt("kills", 0);
    }

    public void addKill() {
        storage.getConfig().set("kills", getKills() + 1);
        storage.saveConfig();
    }

    public int getDeaths() {
        return storage.getConfig().getInt("deaths", 0);
    }

    public void addDeath() {
        storage.getConfig().set("deaths", getDeaths() + 1);
        storage.saveConfig();
    }

    public void setKitLevel(Kit kit, int i){ 
        storage.getConfig().set("kits." + kit.getName() + ".level", i);
        storage.saveConfig();
    }

    public boolean isUnlocked(Kit kit){ return storage.getConfig().getBoolean("kits." + kit.getName(), false);}

    public int kitLevel(Kit kit){ return storage.getConfig().getInt("kits." + kit.getName() + ".level", 0);}

    public void setUnlocked(Kit kit, Boolean bool){
        storage.getConfig().set("kits." + kit.getName(), bool);
        storage.saveConfig();
    }


    public double getBalance() {
        return storage.getConfig().getDouble("balance", 0);
    }

    public void setBalance(double balance) {
        storage.getConfig().set("balance", balance);
        storage.saveConfig();
    }

    public class Storage {
        UUID uuid;
        BeastBrawl bb;

        public Storage(UUID uuid, BeastBrawl bb) {
            this.uuid = uuid;
            this.bb = bb;
        }

        private FileConfiguration config = null;
        private File file = null;

        public void reloadConfig() {
            if (file == null) {
                file = new File(bb.getDataFolder() + File.separator + "user-data", uuid.toString() + ".yml");
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
                bb.getLogger().log(Level.SEVERE, "Could not save config to /user-data/" + uuid.toString() + ".yml", ex);
            }
        }
    }
}
