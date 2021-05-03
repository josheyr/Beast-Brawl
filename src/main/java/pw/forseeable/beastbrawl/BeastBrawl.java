package pw.forseeable.beastbrawl;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import pw.forseeable.beastbrawl.commands.*;
import pw.forseeable.beastbrawl.kits.pay.Elephant;
import pw.forseeable.beastbrawl.kits.pay.Frog;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.kits.free.Giraffe;
import pw.forseeable.beastbrawl.kits.free.Lion;
import pw.forseeable.beastbrawl.kits.free.Rhino;
import pw.forseeable.beastbrawl.kits.free.Turtle;
import pw.forseeable.beastbrawl.koths.KOTH;
import pw.forseeable.beastbrawl.listeners.*;
import pw.forseeable.beastbrawl.dependencies.ParticleEffect;
import pw.forseeable.beastbrawl.managers.*;
import pw.forseeable.beastbrawl.utils.Configuration;
import pw.forseeable.beastbrawl.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

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
public class BeastBrawl extends JavaPlugin {

    String storeLink = "https://store.beastbrawl.us/";
    String domain = "beastbrawl.us";
    String name = "Beast Kits";

    KitManager km;
    KOTHManager kothm;
    PlayerToggleFlightEventHandler playerToggleFlight;

    private BeastBrawl bb;
    public Location spawn;

    public ArrayList<UUID> lionPUActive = new ArrayList<>();
    public ArrayList<UUID> giraffePUActive = new ArrayList<>();
    public ArrayList<UUID> rhinoPUActive = new ArrayList<>();
    public ArrayList<UUID> turtlePUActive = new ArrayList<>();

    public ArrayList<UUID> using18 = new ArrayList<>();

    public HashMap<UUID, Float> isGoodSwimmer = new HashMap<>();
    public HashMap<UUID, Integer> hitPercentage = new HashMap<>();

    public ArrayList<UUID> canFly = new ArrayList<>();
    public ArrayList<UUID> canDoublejump = new ArrayList<>();

    public HashMap<UUID, Inventory> currentInventory = new HashMap<>();

    public TogglesManager toggles;

    boolean flashed;
    int scoreboardTaskId;
    int xpbarTaskId;
    int durabilityTaskId;
    int powerupTaskId;
    int kothTaskId;


    @Override
    public void onEnable() {
        bb = this;
        km = new KitManager(this);
        kothm = new KOTHManager(this);

        kothm.startKOTHScheduler();

        toggles = new TogglesManager();

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            saveDefaultConfig();
            Configuration newConfig = new Configuration(file);
            newConfig.reload(true);
            newConfig.save();
        }

        //for (KOTH k : kothm.koths){
        //    kothm.terminateKOTH(k);
        //}

        updateSpawn();

        registerListeners();
        registerCommands();

        xpbarTaskId = this.getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            for(Entity e : Bukkit.getWorld("world").getEntities()){
                if(e instanceof Item){
                    Item i = (Item)e;

                    if(i.getItemStack().getType() == Material.BOWL && i.isOnGround()){
                        i.remove();
                    }
                }
            }

            for(Player p : Bukkit.getOnlinePlayers()){
                try {
                    if(Utils.getProtocolVersion(p) == 47){
                        bb.using18.add(p.getUniqueId());
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if(getKitManager().getKit(p) != null){
                    Kit kit = getKitManager().getKit(p);

                    if(kit.inCooldown(p.getUniqueId())){
                        int passed = (int) ((System.currentTimeMillis() - kit.cooldownStarted(p.getUniqueId())) / 1000);
                        ConfigManager config = new ConfigManager(p.getUniqueId());
                        p.setLevel((kit.cooldownTime() * 2) -  ((kit.cooldownTime() / 5) * (config.kitLevel(kit))) - passed);
                        p.setExp(((float)passed/(float)(kit.cooldownTime() * 2) -  ((kit.cooldownTime() / 5) * (config.kitLevel(kit)))));

                    }else{
                        flashed = !flashed;
                        p.setLevel(0);
                        if(flashed)
                            p.setExp(0);
                        else
                            p.setExp(1);
                    }
                }else{
                    p.setLevel(0);
                }
            }
        }, 0L, 10L).getTaskId();
        
        durabilityTaskId = this.getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            try {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    for (ItemStack item : p.getInventory().getContents()) {
                        if (item != null) {
                            if (Enchantment.DURABILITY.canEnchantItem(item)) {
                                item.setDurability((short) 0);
                            }
                        }
                    }
                    if (p.getInventory().getArmorContents() != null) {
                        for (ItemStack item : p.getInventory().getArmorContents()) {
                            if (item != null) {
                                if (Enchantment.DURABILITY.canEnchantItem(item)) {
                                    item.setDurability((short) 0);
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {

            }
        }, 0L, 10L).getTaskId();

        powerupTaskId = this.getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            try {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Player target = Utils.getNearest(p);
                    if (target!=null) {
                        p.setCompassTarget(target.getLocation());
                    }else{
                        p.setCompassTarget(p.getLocation());
                    }


                    Kit kit = km.getKit(p);
                    if(kit != null){
                        if (kit instanceof Lion) {
                            if (!p.hasPotionEffect(PotionEffectType.SPEED)) {
                                lionPUActive.remove(p.getUniqueId());
                                for(PotionEffect potionEffect : kit.getPotionEffects()){
                                    p.addPotionEffect(potionEffect);
                                }                            }else {
                                if(lionPUActive.contains(p.getUniqueId())){
                                    int points = 12;
                                    for (int i = 0; i < 360; i += 360/points) {

                                        double angle = (i * Math.PI / 180);
                                        double x = 1 * Math.cos(angle);
                                        double z = 1 * Math.sin(angle);
                                        Location loc = p.getLocation().add(x, 0, z);
                                        ParticleEffect.FLAME.display(new Vector(0, 0, 0), 0.0f, loc.add(0,2, 0), 50);
                                       // p.getWorld().playEffect(loc.subtract(0,.2,0), Effect.VILLAGER_THUNDERCLOUD, 20, 0);
                                    }
                                }
                            }
                        }else if (kit instanceof Turtle) {
                            if (!p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
                                turtlePUActive.remove(p.getUniqueId());
                                hitPercentage.remove(p.getUniqueId());
                                for(PotionEffect potionEffect : kit.getPotionEffects()){
                                    p.addPotionEffect(potionEffect);
                                }
                            }else {
                                if(turtlePUActive.contains(p.getUniqueId())){
                                    int points = 12;
                                    for (int i = 0; i < 360; i += 360/points) {

                                        double angle = (i * Math.PI / 180);
                                        double x = 1 * Math.cos(angle);
                                        double z = 1 * Math.sin(angle);
                                        Location loc = p.getLocation().add(x, 0, z);
                                        ParticleEffect.SMOKE_LARGE.display(new Vector(0, 0, 0), 0.0f, loc.add(0,1, 0), 50);
                                       // p.getWorld().playEffect(loc.subtract(0,.2,0), Effect.VILLAGER_THUNDERCLOUD, 20, 0);
                                    }
                                }
                            }
                        }else if (kit instanceof Rhino) {
                            if (!p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
                                rhinoPUActive.remove(p.getUniqueId());
                                for(PotionEffect potionEffect : kit.getPotionEffects()){
                                    p.addPotionEffect(potionEffect);
                                }
                            }else {
                                if(rhinoPUActive.contains(p.getUniqueId())){
                                    
                                    Vector direction = p.getLocation().getDirection().multiply(2);
                                    p.setVelocity(direction.add(new org.bukkit.util.Vector(0, -2, 0)));
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
                                            n.damage(3, p);
                                            n.setVelocity(direction.add(new org.bukkit.util.Vector(0, 0.5, 0)));
                                        }
                                    }
                            
                                    for(Entity nearbyEntity : p.getNearbyEntities(4, 4, 4)){
                                        if(nearbyEntity instanceof Player){
                                            Player n = (Player)nearbyEntity;
                                            n.damage(4, p);
                                            n.setVelocity(direction.add(new org.bukkit.util.Vector(0, 0.5, 0)));
                                        }
                                    }
                            
                                    for(Entity nearbyEntity : p.getNearbyEntities(3, 3, 3)){
                                        if(nearbyEntity instanceof Player){
                                            Player n = (Player)nearbyEntity;
                                            n.damage(5, p);
                                            n.setVelocity(direction.add(new org.bukkit.util.Vector(0, 0.5, 0)));
                                        }
                                    }
                            
                                    for(Entity nearbyEntity : p.getNearbyEntities(2, 2, 2)){
                                        if(nearbyEntity instanceof Player){
                                            Player n = (Player)nearbyEntity;
                                            n.damage(6, p);
                                            n.setVelocity(direction.add(new org.bukkit.util.Vector(0, 0.5, 0)));
                                        }
                                    }
                                }
                            }
                        }else if (kit instanceof Giraffe) {
                            if (!p.hasPotionEffect(PotionEffectType.JUMP)) {
                                giraffePUActive.remove(p.getUniqueId());
                            }else {
                                if(giraffePUActive.contains(p.getUniqueId())){
                                    int points = 12;
                                    for (int i = 0; i < 360; i += 360/points) {

                                        double angle = (i * Math.PI / 180);
                                        double x = 0.5 * Math.cos(angle);
                                        double z = 0.5 * Math.sin(angle);
                                        Location loc = p.getLocation().add(x, 0, z);
                                        ParticleEffect.WATER_SPLASH.display(new Vector(0, 0, 0), 0.0f, loc.add(0,2, 0), 50);
                                    }
                                }
                            }
                        }else if(kit instanceof Frog){
                            if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
                                if(!p.hasPotionEffect(PotionEffectType.BLINDNESS)){
                                    for (Entity e:p.getNearbyEntities(15,15,15)
                                         ) {
                                        if(e instanceof Player){
                                            Player n = (Player)e;
                                            if(p != n){
                                            n.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 3));
                                            }
                                        }
                                    }
                                }
                            }
                            if (!p.hasPotionEffect(PotionEffectType.SPEED)) {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0));
                            }
                            if(!p.hasPotionEffect(PotionEffectType.JUMP)){
                                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 1));
                            }
                        }else if(kit instanceof Elephant){
                            if(!p.hasPotionEffect(PotionEffectType.SLOW)){
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0));
                            }
                        }
                    }
                }
            } catch (Exception ex) {

            }
        }, 0L, 10L).getTaskId();


        for (Player p:
                Bukkit.getOnlinePlayers()) {
            km.clearPlayer(p);
        }


        for (Player p : Bukkit.getOnlinePlayers()) {
            new ScoreboardManager(p.getUniqueId(), bb).create();
            p.setAllowFlight(false);
            p.setGameMode(GameMode.SURVIVAL);
        }

        scoreboardTaskId = this.getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                new ScoreboardManager(p.getUniqueId(), this.bb).update();
            }
        }, 0L, 1L).getTaskId();

        kothTaskId = this.getServer().getScheduler().runTaskTimer(this, () -> {
            kothm.checkKOTH();
        }, 0L, 1L).getTaskId();

        if(toggles.getDoubleHearts()){
            for(Player p: Bukkit.getOnlinePlayers()) {
                p.setMaxHealth(40);
            }
        }else{
            for(Player p: Bukkit.getOnlinePlayers()) {
                p.setMaxHealth(20);
            }
        }

}

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }


    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BlockBreakEventHandler(), this);
        pm.registerEvents(new BlockPlaceEventHandler(), this);
        pm.registerEvents(new InventoryClickEventHandler(this), this);
        pm.registerEvents(new PlayerJoinEventHandler(this), this);
        pm.registerEvents(new PlayerInteractEventHandler(this), this);
        pm.registerEvents(new FoodLevelChangeEventHandler(this), this);
        pm.registerEvents(new PlayerDropItemEventHandler(this), this);
        pm.registerEvents(new BlockSpreadEventHander(this), this);
        pm.registerEvents(new BlockGrowEventHandler(), this);
        pm.registerEvents(new PlayerDeathEventHandler(this), this);
        pm.registerEvents(new PlayerRespawnEventHandler(this), this);
        pm.registerEvents(new PlayerInteractEntityEventHandler(this), this);
        pm.registerEvents(new PlayerVelocityEventHandler(this), this);
        pm.registerEvents(new EntityDamageByEntityEventHandler(this), this);
        pm.registerEvents(new EntityDamageEventHandler(this), this);
        pm.registerEvents(new PlayerMoveEventHandler(this), this);
        pm.registerEvents(new PlayerKickEventHandler(this), this);
        pm.registerEvents(new PlayerQuitEventHandler(this), this);
        pm.registerEvents(new ServerListPingEventHandler(this), this);
        playerToggleFlight = new PlayerToggleFlightEventHandler(this);
        pm.registerEvents(playerToggleFlight, this);
    }

    private void registerCommands() {
        getCommand("admin").setExecutor(new AdminCommand(this));
        getCommand("teleport").setExecutor(new TeleportCommand(this));
        getCommand("edit").setExecutor(new EditCommand(this));
        getCommand("kit").setExecutor(new KitCommand(this));
        getCommand("suicide").setExecutor(new SuicideCommand(this));
        getCommand("coins").setExecutor(new CoinsCommand(this));
        getCommand("balance").setExecutor(new BalanceCommand(this));
        getCommand("toggle").setExecutor(new ToggleCommand());
    }

    public void updateSpawn(){
        spawn = getSpawn();
    }

    public Location getSpawn(){
        return new Location(Bukkit.getWorld("world"), 120, 140, -140, 0.0f, 0.0f);
    }

    public KitManager getKitManager() {
        return km;
    }

    public KOTHManager getKOTHManager() {return kothm;}

    public PlayerToggleFlightEventHandler getFlightToggleEventHandler(){
        return playerToggleFlight;
    }

    public String getStoreLink(){ return storeLink; }

    public String getDomain(){ return domain; }

    public String getServerName(){ return name; }

    public Collection<UUID> getCanFly(){
        return canFly;
    }

    public Collection<UUID> getCanDoublejump(){
        return canDoublejump;
    }

    public TogglesManager getToggles(){
        return toggles;
    }
}
