package com.rogermiranda1000.placedblockctrlz;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is an example of a plugin to debug. Don't use this plugin in your server.
 */
public class Main extends JavaPlugin implements Listener {
    /**
     * What was the last block placed
     */
    private Block lastPlacedBlock;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this); // call onBlockPlace when a block is being placed
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        this.lastPlacedBlock = event.getBlock();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!args[0].equals("ctrl-z")) return false;

        // ctrl-z; undo the last placed block
        Material itemToGiveToPlayer = this.lastPlacedBlock.getType();
        this.lastPlacedBlock.setType(Material.AIR); // remove the block
        ((Player)sender).getInventory().addItem(new ItemStack(itemToGiveToPlayer,1)); // add the block to the inventory

        return true;
    }
}
