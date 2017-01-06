package de.wonderworld.plugins.selfmadeEssentials.files;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 05.01.2017.
 */
public abstract class BaseYMLManager {

    protected abstract File getYMLFile();

    protected YamlConfiguration loadCfg() {
        return YamlConfiguration.loadConfiguration(getYMLFile());
    }

    protected void safeFile(YamlConfiguration cfg) {
        try {
            cfg.save(getYMLFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isVariableActive(String playerName, String variableName) {

        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList(variableName);

        return list != null && list.contains(playerName);
    }

    public void toggleVariable(String playerName, String variableName) {

            YamlConfiguration cfg = loadCfg();
            List<String> list = cfg.getStringList(variableName);

            if (list == null) {
                list = new ArrayList<>();
                list.add(playerName);
            } else {
                if (list.contains(playerName))
                    list.remove(playerName);
                else {
                    list.add(playerName);
                }
            }

            cfg.set(variableName, list);
            safeFile(cfg);
    }
}
