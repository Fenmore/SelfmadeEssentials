package de.wonderworld.plugins.selfmadeEssentials.files;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerYMLManager {

    private File getPlayerFile(String name) {
        return new File(Essentials.dirPlayer.toString() + "\\" + name + ".yml");
    }

    private YamlConfiguration loadCfg(String name) {
        return YamlConfiguration.loadConfiguration(getPlayerFile(name));
    }

    private void safeFile(YamlConfiguration cfg, String name) {
        try {
            cfg.save(this.getPlayerFile(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlePlayerNameChange(String playerBefore, String playerNow) {
        File file = getPlayerFile(playerBefore);
        if (file.exists()) {
            file.renameTo(getPlayerFile(playerNow));
        }
    }

    public void setPtime(String playerName, long ticks, boolean constant) {
        YamlConfiguration cfg = loadCfg(playerName);
        cfg.set("ptimeTicks", ticks);
        cfg.set("ptimeState", constant);
        safeFile(cfg, playerName);
    }

    public void remPtime(String playerName) {
        YamlConfiguration cfg = loadCfg(playerName);
        cfg.set("ptimeTicks", null);
        cfg.set("ptimeState", null);
        safeFile(cfg, playerName);

    }

    public long getPtimeTicks(String playerName) {
        YamlConfiguration cfg = loadCfg(playerName);
        return cfg.getLong("ptimeTicks");
    }

    public boolean getPtimeState(String playerName) {
        YamlConfiguration cfg = loadCfg(playerName);
        return cfg.getBoolean("ptimeState");
    }


    public boolean hasPtime(String name) {
        YamlConfiguration cfg = loadCfg(name);
        if (cfg.get("ptimeTicks") == null)
            return false;
        else
            return true;
    }
}
