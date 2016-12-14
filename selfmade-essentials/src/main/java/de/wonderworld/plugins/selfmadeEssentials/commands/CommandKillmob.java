package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandKillmob implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }
        List<Entity> nearbyEntities;
        nearbyEntities = ((Entity) sender).getNearbyEntities(25.0, 25.0, 25.0);


        List<String> mobList = new ArrayList<>();
        for(EntityType type : EntityType.values()) {
            mobList.add(type.toString());
        }

        for(String mob : args) {
            if(!mobList.contains(mob.toUpperCase())) {
                sender.sendMessage(EssentialCommands.message(Constants.MOB_NOT_FOUND_FORMAT, mob));
                return true;
            }
        }


        for(Entity e : nearbyEntities) {
            if(args.length == 0) {
                if (e instanceof Creature)
                    e.remove();
            }
            else {
                for(String mob : args) {
                    if(e.getType().equals(EntityType.valueOf(mob.toUpperCase()))) {
                        e.remove();
                    }
                }
            }
        }

        return true;
    }
}
