package pw.forseeable.beastbrawl.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.forseeable.beastbrawl.BeastBrawl;
import pw.forseeable.beastbrawl.utils.Prefix;

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
public class TeleportCommand implements CommandExecutor {

    private BeastBrawl bb;

    public TeleportCommand(BeastBrawl bb) {
        this.bb = bb;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("bb.admin")) {
                if (args.length <= 2 && args.length != 0) {
                    if(args.length == 1){
                        if(Bukkit.getPlayer(args[0]) != null){
                            p.teleport(Bukkit.getPlayer(args[0]).getLocation());
                            p.sendMessage(Prefix.SUCCESS + "Teleporting to §l" + Bukkit.getPlayer(args[0]).getName() + "§a.");
                        }else{
                            p.sendMessage(Prefix.ERROR + " Player §l" + args[0] + "§c not found.");
                        }
                    }else{
                        if(args[0].equals("*")){
                            if (Bukkit.getPlayer(args[1]) != null) {
                                    for (Player r: Bukkit.getOnlinePlayers()) {
                                        r.teleport(Bukkit.getPlayer(args[1]).getLocation());
                                    }
                                    p.sendMessage(Prefix.SUCCESS + "Teleporting every player to §l" + Bukkit.getPlayer(args[1]).getName() + "§a.");
                                    Bukkit.getPlayer(args[0]).sendMessage(Prefix.INFO + "Teleporting...");
                                }else{
                                p.sendMessage(Prefix.ERROR + "Player §l" + args[1] + "§c not found.");
                            }

                            }else {
                            if (Bukkit.getPlayer(args[0]) != null) {
                                if (Bukkit.getPlayer(args[1]) != null) {
                                    Bukkit.getPlayer(args[0]).teleport(Bukkit.getPlayer(args[1]).getLocation());
                                    p.sendMessage(Prefix.SUCCESS + "Teleporting §l" +Bukkit.getPlayer(args[0]).getName()+ "§a to §l" + Bukkit.getPlayer(args[1]).getName() + "§a.");
                                    Bukkit.getPlayer(args[0]).sendMessage(Prefix.INFO + "Teleporting...");
                                } else {
                                    p.sendMessage(Prefix.ERROR + "Player §l" + args[1] + "§c not found.");
                                }
                            } else {
                                p.sendMessage(Prefix.ERROR + "Player §l" + args[0] + "§c not found.");
                            }
                        }
                    }
                } else {
                    p.sendMessage(Prefix.ERROR + "Invalid syntax: /teleport <player> [player]");
                }
            }else{
                p.sendMessage(Prefix.ERROR + "You do not have permission to use this command!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10, 10);
            }
        }
        return true;
    }
}
