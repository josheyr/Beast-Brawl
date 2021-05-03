package pw.forseeable.beastbrawl.managers;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.kits.Kit;
import pw.forseeable.beastbrawl.koths.KOTH;
import pw.forseeable.beastbrawl.utils.Utils;

import java.text.DecimalFormat;
import java.util.UUID;

/**
 * Ur boi forseeable made this class #gang
 */
public class ScoreboardManager {

    private UUID uuid;
    private BeastBrawl bb;

    public ScoreboardManager(UUID uuid, BeastBrawl bb){
        this.uuid = uuid;
        this.bb = bb;
    }

    public void create(){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(this.uuid.toString().substring(0, 8), "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§4§l" + bb.getServerName() +  " §7(§cb1§7)");

        OfflinePlayer start = Utils.getOfflinePlayerSkipLookup("§7§4§m-------------------------------------------".substring(0, 15));
        Team startTeam = scoreboard.registerNewTeam("100");
        startTeam.setSuffix("§7§4§m--------------------------------------".substring(0, 12));
        startTeam.addPlayer(start);
        objective.getScore(start).setScore(13);

        OfflinePlayer end = Utils.getOfflinePlayerSkipLookup("§8§4§m-------------------------------------------".substring(0, 15));
        Team endTeam = scoreboard.registerNewTeam("0");
        endTeam.setSuffix("§8§4§m-------------------------------------------".substring(0, 12));
        endTeam.addPlayer(end);
        objective.getScore(end).setScore(0);

        Bukkit.getPlayer(this.uuid).setScoreboard(scoreboard);

    }

    public void update(){
        try {
            if (Bukkit.getPlayer(this.uuid).getScoreboard().getObjective(this.uuid.toString().substring(0, 8)) == null) {
                create();
            }
            Scoreboard scoreboard = Bukkit.getPlayer(this.uuid).getScoreboard();

            Objective objective = scoreboard.getObjective(this.uuid.toString().substring(0, 8));


            int lines = 12;

            ConfigManager cm = new ConfigManager(this.uuid);
            OfflinePlayer coins = Utils.getOfflinePlayerSkipLookup("§cCoins:");
            Team coinsTeam = getTeam(scoreboard, "" + lines);
            coinsTeam.setSuffix("§7 " + (int) cm.getBalance());
            coinsTeam.addPlayer(coins);
            objective.getScore(coins).setScore(lines);
            lines--;

            OfflinePlayer kit = Utils.getOfflinePlayerSkipLookup("§cKit: ");
            Team kitTeam = getTeam(scoreboard, "" + lines);
            kitTeam.addPlayer(kit);
            objective.getScore(kit).setScore(lines);
            lines--;

            if(bb.getKitManager().getKit(Bukkit.getPlayer(uuid)) != null) {
                Kit playerKit = bb.getKitManager().getKit(Bukkit.getPlayer(uuid));
                kitTeam.setSuffix(playerKit.getColorCode() + "§l" + playerKit.getName());
                int i = 0;
                for(String s : playerKit.skullStrings()){
                    OfflinePlayer kitSkullLine = Utils.getOfflinePlayerSkipLookup("§" + i + s.substring(6, 18));
                    Team kitSkullITeam = getTeam(scoreboard, "" + lines);
                    kitSkullITeam.setSuffix("§" + i + s.substring(18, 24));
                    kitSkullITeam.setPrefix("   §" + i + s.substring(0, 6));
                    kitSkullITeam.addPlayer(kitSkullLine);
                    objective.getScore(kitSkullLine).setScore(lines);
                    lines--;
                    i++;
                }
//                if(playerKit.inCooldown(uuid)){
//
//                    OfflinePlayer splitter = Utils.getOfflinePlayerSkipLookup("§9§4§m-------------------------------------------".substring(0, 15));
//                    Team splitterTeam = getTeam(scoreboard, "" + lines);
//                    splitterTeam.setSuffix("§9§4§m--------------------------------------".substring(0, 12));
//                    splitterTeam.addPlayer(splitter);
//                    objective.getScore(splitter).setScore(lines);
//                    lines--;
//
//                    OfflinePlayer cooldown = Utils.getOfflinePlayerSkipLookup("§cCooldown: ");
//                    Team cooldownTeam = getTeam(scoreboard, "" + lines);
//                    double passed = (((double)System.currentTimeMillis() - playerKit.cooldownStarted(uuid)) / 1000);
//                    cooldownTeam.setSuffix("§7" + Utils.round((double)playerKit.cooldownTime() - passed, 1));
//                    cooldownTeam.addPlayer(cooldown);
//                    objective.getScore(cooldown).setScore(lines);
//                    lines--;
//                }else{
//                    scoreboard.resetScores("§9§4§m-------------------------------------------".substring(0, 14));
//                    lines--;
//                    scoreboard.resetScores("§cCooldown: ");
//                    lines++;
//                }
            }else{
                kitTeam.setSuffix("§7§o(none)");
            }

            if(bb.getKOTHManager().currentkoths.size() != 0){

                OfflinePlayer splitter = Utils.getOfflinePlayerSkipLookup("§0§4§m--------------------------------------".substring(0, 15));
                Team splitterTeam = getTeam(scoreboard, "" + lines);
                splitterTeam.setSuffix("§0§4§m--------------------------------------".substring(0, 12));
                splitterTeam.addPlayer(splitter);
                objective.getScore(splitter).setScore(lines);
                lines--;

                int kid = 0;
                for(KOTH k : bb.getKOTHManager().currentkoths){
                    OfflinePlayer koth = Utils.getOfflinePlayerSkipLookup("§k§o");
                    Team kothTeam = getTeam(scoreboard, "" + lines);
                    kothTeam.setPrefix("§c§l" + k.getName() + " KOTH: ");

                    int minutes = k.getCountdown() / (60 * 20);
                    int seconds = (k.getCountdown() / 20) % 60;
                    String time = String.format("%d:%02d", minutes, seconds);

                    kothTeam.setSuffix("§7" + time);
                    kothTeam.addPlayer(koth);
                    objective.getScore(koth).setScore(lines);
                    lines--;
                    kid++;
                }
            }else{
                scoreboard.resetScores("§0§4§m--------------------------------------".substring(0, 15));
                for(int i = 0; i < 10; i++){
                    scoreboard.resetScores("§k§o");
                }
            }

        }catch(Exception ex){
            create();
        }
    }

    private Team getTeam(Scoreboard scoreboard, String s) {
        try {
            return scoreboard.registerNewTeam(s);
        }
        catch (IllegalArgumentException ex) {

        }
        return scoreboard.getTeam(s);
    }

    public void destroy() {
        if (Bukkit.getPlayer(this.uuid).getScoreboard() != null) {
            for (Objective objective : Bukkit.getPlayer(this.uuid).getScoreboard().getObjectives()) {
                objective.unregister();
            }
            for (Team team : Bukkit.getPlayer(this.uuid).getScoreboard().getTeams()) {
                team.unregister();
            }
        }
    }

    private String formatTimeSeconds(long i) {
        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(i / 1000.0D);
    }
}
