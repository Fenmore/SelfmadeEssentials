package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Fenmore on 21.11.2016.
 */
public class CommandSpawnmob implements CommandExecutor{

    private Essentials plugin;
    public CommandSpawnmob(Essentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        if(args.length == 0)
            return false;

        List<String> mobList = new ArrayList<>();
        for(EntityType type : EntityType.values())
            mobList.add(type.toString());

        if(!mobList.contains(args[0].toUpperCase())) {
            sender.sendMessage(EssentialCommands.message(Constants.MOB_NOT_FOUND_FORMAT, args[0]));
            return true;
        }

        int max = 1;
        if(args.length > 1) {
            try {
                if(Integer.valueOf(args[1]) < 20)
                    max = Integer.valueOf(args[1]);
                else
                    max = 20;
            } catch (NumberFormatException e) {
                sender.sendMessage(EssentialCommands.message(Constants.ARGUMENT_HAS_TO_BE_INTEGER_FORMAT, args[1]));
                return true;
            }
        }

        List<Block> sightList = ((Player) sender).getLineOfSight((Set) null, 100);
        Location loc = sightList.get(sightList.size() - 1).getLocation();
        loc.setY(loc.getY() + 1);

        for(int i = 0; i < max; i++)
            loc.getWorld().spawnEntity(loc, EntityType.valueOf(args[0].toUpperCase()));



        return true;
    }
}
