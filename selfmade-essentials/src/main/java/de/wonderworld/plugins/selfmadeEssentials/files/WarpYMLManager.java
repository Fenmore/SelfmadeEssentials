package de.wonderworld.plugins.selfmadeEssentials.files;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WarpYMLManager {

    private File dirStats;
    private Essentials plugin;

    public WarpYMLManager(Essentials plugin) {
        this.dirStats = Essentials.dirStats;
        this.plugin = plugin;
    }


    private File getWarpFile() {
        return new File(dirStats.toString() + "\\Warps.yml");
    }

    private YamlConfiguration loadCfg() {
        return YamlConfiguration.loadConfiguration(getWarpFile());
    }

    private void safeFile(YamlConfiguration cfg) {
        try {
            cfg.save(this.getWarpFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Location getWarp(String str) {
        YamlConfiguration cfg = loadCfg();
        Location warp = null;
        String warpStr = str.toLowerCase();
        if(cfg.getString(warpStr + ".world") != null) {
            warp = new Location(plugin.getServer().getWorld(cfg.getString(warpStr + ".world")), cfg.getDouble(warpStr + ".x"), cfg.getDouble(warpStr + ".y"), cfg.getDouble(warpStr + ".z"));
            List<Float> directions = cfg.getFloatList(warpStr + ".direction");
            warp.setYaw(directions.get(0));
            warp.setPitch(directions.get(1));
        }
        return warp;
    }

    public void setWarp(Location warp, String str) {
        YamlConfiguration cfg = loadCfg();
        String warpStr = str.toLowerCase();
        cfg.set(warpStr + ".world", warp.getWorld().getName());
        cfg.set(warpStr + ".x", warp.getBlockX());
        cfg.set(warpStr + ".y", warp.getBlockY());
        cfg.set(warpStr + ".z", warp.getBlockZ());
        List<Float> directions = new ArrayList<>();
        directions.add(warp.getYaw());
        directions.add(warp.getPitch());
        cfg.set(warpStr + ".direction", directions);
        safeFile(cfg);
    }

    public void delWarp(String warpStr) {
        YamlConfiguration cfg = loadCfg();
        cfg.set(warpStr.toLowerCase(), null);
        safeFile(cfg);
    }

    public Set<String> getWarpList() {
        return loadCfg().getKeys(false);
    }
}
