package pw.forseeable.beastbrawl.utils;

import org.bukkit.ChatColor;

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
public enum  Prefix {
    ERROR(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "»" + ChatColor.RED + " "),
    SUCCESS(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "»" + ChatColor.GREEN + " "),
    INFO(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + "»" + ChatColor.GRAY + " "),
    ALERT(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "»" + ChatColor.LIGHT_PURPLE + " ");

    String string;
    Prefix(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
