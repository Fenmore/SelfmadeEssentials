package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fenmore on 02.12.2016.
 */

public class CommandFb implements CommandExecutor {

    private Essentials plugin;

    public CommandFb(Essentials plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        /*Location loc = ((Player) sender).getLocation();
        loc.setY(loc.getY() + 50);
        FallingBlock fb = ((Player) sender).getWorld().spawnFallingBlock(((Player) sender).getLocation(), new MaterialData(Material.LOG));

        fb.setVelocity(new Vector(1,0,0));
        fb.setGravity(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                fb.setVelocity(new Vector(-1,0,0));
            }
        },Long.valueOf(args[0]));*/

        List<Block> sightList = EssentialCommands.getLineOfSight(((Player) sender));
        Block startBlock = sightList.get(sightList.size() - 1);
        if (startBlock.getType().equals(Material.LOG)) {
            plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
                @Override
                public void run() {
                    List<Block> blockDoneList = new ArrayList<>();
                    List<Block> blockToDoList = new ArrayList<>();
                    List<Block> blockToTestList = new ArrayList<>();
                    blockToDoList.add(startBlock);
                    do {
                        for (Block block : blockToDoList) {
                            Location loc = block.getLocation();
                            for (int x = -1; x < 2; x++) {
                                loc.setX(block.getLocation().getBlockX() + x);
                                for (int y = -1; y < 2; y++) {
                                    loc.setY(block.getLocation().getBlockY() + y);
                                    for (int z = -1; z < 2; z++) {
                                        loc.setZ(block.getLocation().getBlockZ() + z);
                                        Block testBlock = loc.getWorld().getBlockAt(loc);
                                        if (testBlock.getType().equals(Material.LOG) && !blockDoneList.contains(testBlock) && !blockToDoList.contains(testBlock) && !blockToTestList.contains(testBlock)) {
                                            blockToTestList.add(testBlock);
                                        }
                                    }
                                }
                            }
                            blockDoneList.add(block);
                        }
                        blockToDoList.clear();
                        blockToDoList.addAll(blockToTestList);
                        blockToTestList.clear();
                    }
                    while (!blockToDoList.isEmpty());
                    for (Block block : blockDoneList) {
                        block.setType(Material.AIR);
                    }
                }
            });

        }
        return true;
    }
}
