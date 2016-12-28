package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommandTree extends PlayerCommand{

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            List<Block> lineOfSight = sender.getLineOfSight((Set) null, 100);
            sender.getWorld().generateTree(lineOfSight.get(lineOfSight.size() - 1).getLocation(), TreeType.BIG_TREE);
            return true;
        }


        List<String> treeTypes = new ArrayList<>();
        for(TreeType type : TreeType.values()) {
            treeTypes.add(type.toString());
        }

        if(args[0].equalsIgnoreCase("list")) {
            LocaleHandler.sendLocalizedMessage(sender, "AVAILABLE_TREETYPES_FORMAT", treeTypes.toString());
            return true;
        }

        if(!treeTypes.contains(args[0].toUpperCase())) {
            LocaleHandler.sendLocalizedMessage(sender, "TREETYPE_NOT_FOUND_FORMAT", args[0]);
            return true;
        }

        /*int i = 0;
        List<Block> lineOfSight = ((Player) sender).getLineOfSight((Set) null, 100);
        for(Material mat : Material.values()) {
            if(mat.isTransparent()) {
                ((Player) sender).getWorld().getBlockAt(lineOfSight.get(lineOfSight.size() - 1).getLocation().getBlockX(),
                        lineOfSight.get(lineOfSight.size() - 1).getLocation().getBlockY(), lineOfSight.get(lineOfSight.size() - 1).getLocation().getBlockZ() + i).setType(mat);

                i++;
            }
        }*/

        List<Block> lineOfSight = sender.getLineOfSight((Set) null, 100);
        if(lineOfSight.get(lineOfSight.size() - 1).getType().equals(Material.GRASS))

        sender.getWorld().generateTree(lineOfSight.get(lineOfSight.size() - 1).getLocation(), TreeType.valueOf(args[0].toUpperCase()));

        return true;
    }
}
