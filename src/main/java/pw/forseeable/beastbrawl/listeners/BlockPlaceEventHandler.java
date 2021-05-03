package pw.forseeable.beastbrawl.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
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
public class BlockPlaceEventHandler implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.getPlayer().getGameMode()!= GameMode.CREATIVE) {
            e.setCancelled(true);
            //e.getPlayer().sendMessage(Prefix.ERROR + "You cannot edit the map!");
            e.getPlayer().updateInventory();
        }
    }

}
