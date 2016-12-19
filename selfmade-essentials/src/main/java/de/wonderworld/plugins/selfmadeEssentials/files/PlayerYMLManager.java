package de.wonderworld.plugins.selfmadeEssentials.files;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public Location getBackLocation(String name) {
        YamlConfiguration cfg = loadCfg(name);
        String world = "";
        if(cfg.getString("back.world") != null)
            world = cfg.getString("back.world");
        return new Location(Bukkit.getWorld(world), cfg.getDouble("back.x"), cfg.getDouble("back.y"), cfg.getDouble("back.z"));
    }

    public void setBackLocation(String name, Location location) {
        YamlConfiguration cfg = loadCfg(name);
        cfg.set("back.world", location.getWorld().getName());
        cfg.set("back.x", location.getX());
        cfg.set("back.y", location.getY());
        cfg.set("back.z", location.getZ());
        safeFile(cfg, name);
    }

    public void remBackLocation(String name) {
        YamlConfiguration cfg = loadCfg(name);
        cfg.set("back", null);
        safeFile(cfg, name);
    }

    public void setHomeLocation(String name, Location location, String home) {
        YamlConfiguration cfg = loadCfg(name);
        cfg.set("homes." + home + ".world", location.getWorld().getName());
        cfg.set("homes." + home + ".x", location.getX());
        cfg.set("homes." + home + ".y", location.getY());
        cfg.set("homes." + home + ".z", location.getZ());
        safeFile(cfg, name);
    }

    public void remHomeLocation(String name, String home) {
        YamlConfiguration cfg = loadCfg(name);
        cfg.set("homes." + home, null);
        return;
    }

    public List<String> getHomeList(String name) {
        YamlConfiguration cfg = loadCfg(name);
        ConfigurationSection section = cfg.getConfigurationSection("homes");
        Map<String, Object> sectionValues = section.getValues(false);
        List<String> list = new ArrayList<>();
        for(String key : sectionValues.keySet()) {
            list.add(key);
        }
        return list;
    }

    public Location getHome(String name, String home) {
        YamlConfiguration cfg = loadCfg(name);
        String world = "";
        if(cfg.getString("homes." + home + ".world") != null)
            world = cfg.getString("homes." + home + ".world");
        return new Location(Bukkit.getWorld(world), cfg.getDouble("homes." + home + ".x"), cfg.getDouble("homes." + home + ".y"), cfg.getDouble("homes." + home + ".z"));
    }
}
