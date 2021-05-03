package pw.forseeable.beastbrawl.commands;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.utils.Prefix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class AdminCommand implements CommandExecutor {

    private BeastBrawl bb;

    public AdminCommand(BeastBrawl bb) {
        this.bb = bb;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("bb.admin")) {
                if (args.length != 0) {
                    String subcommand = args[0];
                    List<String> subargs = new ArrayList<>();
                    Collections.addAll(subargs, args);
                    subargs.remove(0);

                    switch (subcommand.toLowerCase()) {
                        case "setspawn":
                            Location loc = p.getLocation();
                            p.sendMessage(Prefix.SUCCESS + "Spawn point set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ")!");
                            bb.getConfig().set("spawn.world", loc.getWorld().getName());
                            bb.getConfig().set("spawn.x", loc.getX());
                            bb.getConfig().set("spawn.y", loc.getY());
                            bb.getConfig().set("spawn.z", loc.getZ());
                            bb.getConfig().set("spawn.yaw", loc.getYaw());
                            bb.getConfig().set("spawn.pitch", loc.getPitch());
                            bb.saveConfig();
                            break;

                        case "edit":
                            p.performCommand("edit");
                            break;

                        default:
                            sendHelp(p);
                            break;
                    }
                } else {
                    sendHelp(p);
                }
            } else {
                p.sendMessage(Prefix.ERROR + "You do not have permission to use this command!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10, 10);
            }
        } else {
            sender.sendMessage("Go in game plz dad!");
        }
        return true;
    }

    private void sendHelp(Player p) {
        p.sendMessage(Prefix.ALERT + "Commands for Admins:");
        p.sendMessage(Prefix.INFO + "/admin setspawn - set the spawn location");
    }
}
