package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Utilities;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandVanish extends PlayerCommand {

    private final ModYMLManager modYMLManager;
    private final Essentials plugin;
    private final LocalizedMessenger localizedMessenger;

    public CommandVanish(ModYMLManager modYMLManager, Essentials plugin, LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.modYMLManager = modYMLManager;
        this.plugin = plugin;
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        List<String> vanishActiveList = modYMLManager.getVanishActiveList();
        if(args.length == 0) {
            System.out.println(Essentials.board.getTeam("vanishVisible").getEntries());
            if(Essentials.board.getTeam("vanishVisible").getEntries().contains(sender.getName())) {
                if (!vanishActiveList.contains(sender.getName())) {
                    hidePlayerForAll(sender);
                } else {
                    showPlayerForAll(sender);
                }
            }
            else {
                localizedMessenger.sendLocalizedMessage(sender, "VANISH_NOT_PERMITTED");
            }
        }
        else if(args[0].equalsIgnoreCase("see")) {
            if(!sender.hasPermission("selfmadeEssentials.vanish")) {
                sender.addAttachment(plugin, "selfmadeEssentials.vanish", true);
                Bukkit.getOnlinePlayers()
                        .stream()
                        .filter(player -> vanishActiveList.contains(player.getName()))
                        .forEach((sender)::showPlayer);
                Utilities.addVanishPlayerToBoard(sender.getName());
                localizedMessenger.sendLocalizedMessage(sender, "ENTERED_VANISH_VISIBLE");
            }
            else {
                sender.getEffectivePermissions()
                        .stream()
                        .filter(info -> info.getPermission().equals("selfmadeEssentials.vanish"))
                        .forEach(info -> sender.removeAttachment(info.getAttachment()));
                if(vanishActiveList.contains(sender.getName())) {
                    modYMLManager.remVanish(sender.getName());
                    sender.removePotionEffect(PotionEffectType.INVISIBILITY);
                    localizedMessenger.sendLocalizedMessage(sender, "VANISH_INACTIVE");
                }
                Bukkit.getOnlinePlayers()
                        .stream()
                        .filter(player -> vanishActiveList.contains(player.getName()))
                        .forEach((sender)::hidePlayer);
                Utilities.removeVanishPlayerFromBoard(sender.getName());
                localizedMessenger.sendLocalizedMessage(sender, "LEFT_VANISH_VISIBLE");
            }
        }
        else if(args[0].equalsIgnoreCase("list")) {
            List<String> list = Bukkit.getOnlinePlayers()
                    .stream()
                    .filter(player -> player.hasPermission("selfmadeEssentials.vanish")).map((Function<Player, String>) HumanEntity::getName)
                    .collect(Collectors.toList());
            localizedMessenger.sendLocalizedMessage(sender, "VANISH_LIST_INTRO");
            sender.sendMessage(list.toString());
        }

        return true;
    }

    private void showPlayerForAll(Player player) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!Essentials.board.getTeam("vanishVisible").getEntries().contains(p.getName())) {
                p.showPlayer(p);
                localizedMessenger.sendLocalizedMessage(p, "PLAYER_FAKE_JOIN_FORMAT", p.getName());
            } else {
                localizedMessenger.sendLocalizedMessage(p, "PLAYER_LEFT_VANISH_FORMAT", p.getName());
            }
        }
        modYMLManager.remVanish(player.getName());
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        localizedMessenger.sendLocalizedMessage(player, "VANISH_INACTIVE");
    }

    private void hidePlayerForAll(Player player) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!Essentials.board.getTeam("vanishVisible").getEntries().contains(p.getName())) {
                p.hidePlayer(p);
                localizedMessenger.sendLocalizedMessage(p, "PLAYER_FAKE_QUIT_FORMAT", p.getName());
            } else {
                localizedMessenger.sendLocalizedMessage(p, "PLAYER_ENTERED_VANISH", p.getName());
            }
        }
        modYMLManager.setVanish(player.getName());
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        localizedMessenger.sendLocalizedMessage(player, "VANISH_ACTIVE");
    }

}
