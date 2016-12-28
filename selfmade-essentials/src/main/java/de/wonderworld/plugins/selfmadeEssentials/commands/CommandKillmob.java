package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandKillmob extends PlayerCommand {

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        List<Entity> nearbyEntities;
        nearbyEntities = sender.getNearbyEntities(25.0, 25.0, 25.0);

        List<String> mobList = new ArrayList<>();
        for (EntityType type : EntityType.values()) {
            mobList.add(type.toString());
        }

        for (String mob : args) {
            if (!mobList.contains(mob.toUpperCase())) {
                LocaleHandler.sendLocalizedMessage(sender, "MOB_NOT_FOUND_FORMAT, mob");
                return true;
            }
        }

        for (Entity e : nearbyEntities) {
            if (args.length == 0 && e instanceof Creature) {
                e.remove();
            } else {
                for (String mob : args) {
                    if (e.getType().equals(EntityType.valueOf(mob.toUpperCase()))) {
                        e.remove();
                    }
                }
            }
        }
        return true;
    }
}
