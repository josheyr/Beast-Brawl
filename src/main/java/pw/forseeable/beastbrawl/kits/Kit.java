package pw.forseeable.beastbrawl.kits;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import pw.forseeable.beastbrawl.BeastBrawl;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * Ur boi forseeable made this class #gang
 */
public interface Kit {

    String getName();

    String getID();

    String getColorCode();

    ItemStack getGUIItem(UUID uuid);

    int getGUIID();

    double getCost();

    boolean isSelected(UUID uuid);

    void selectedAdd(Player p);

    void selectedRemove(Player p);

    boolean inCooldown(UUID uuid);

    void cooldown(UUID uuid);

    void resetCooldown(UUID uuid);

    long cooldownStarted(UUID uuid);

    int cooldownTime();


    void handleAbility(Player p, Block block, BeastBrawl bb);

    ItemStack getAbility(UUID uuid);

    Collection <PotionEffect> getPotionEffects();
    
    Collection<ItemStack> getItems();

    Collection<ItemStack> getArmor();

    Collection<String> skullStrings();
}