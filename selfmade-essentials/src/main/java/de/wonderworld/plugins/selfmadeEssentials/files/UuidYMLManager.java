package de.wonderworld.plugins.selfmadeEssentials.files;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class UuidYMLManager {

    private static File getUuidFile() {
        return new File(Essentials.dir.toString() + "\\uuids.yml");
    }

    private static YamlConfiguration loadCfg() {
        return YamlConfiguration.loadConfiguration(getUuidFile());
    }

    private static void safeFile(YamlConfiguration cfg) {
        try {
            cfg.save(getUuidFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkState(String uuid, String name) {
        YamlConfiguration cfg = loadCfg();
        if (cfg.getString(uuid) == null) {
            cfg.set(uuid, name);
            safeFile(cfg);
        } else if (!cfg.getString(uuid).equals(name)) {
            new ModYMLManager().handlePlayerNameChange(cfg.getString(uuid), name);
            new PlayerYMLManager().handlePlayerNameChange(cfg.getString(uuid), name);
            cfg.set(uuid, name);
            safeFile(cfg);
        }
    }

    private static boolean isKnown(Player p) {
        YamlConfiguration cfg = loadCfg();
        Set<String> set = cfg.getKeys(false);
        if (set.contains(p.getUniqueId().toString()))
            return true;
        else
            return false;
    }

    public static void setUuid(Player p) {
        YamlConfiguration cfg = loadCfg();
        cfg.set(p.getUniqueId().toString(), p.getName());
        safeFile(cfg);
    }


    public static void setUuid(UUID uniqueId, String name) {
        YamlConfiguration cfg = loadCfg();
        cfg.set(uniqueId.toString(), name);
        safeFile(cfg);
    }

    public static String getName(Player p) {
        YamlConfiguration cfg = loadCfg();
        return cfg.getString(p.getUniqueId().toString());
    }

    public static String getName(UUID playerUuid) {
        YamlConfiguration cfg = loadCfg();
        return cfg.getString(playerUuid.toString());
    }
}
