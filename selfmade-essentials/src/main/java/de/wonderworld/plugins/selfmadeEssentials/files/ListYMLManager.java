package de.wonderworld.plugins.selfmadeEssentials.files;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fenmore on 22.11.2016.
 */
public class ListYMLManager {

    private File dirStats;

    public ListYMLManager(File dirStats, Essentials plugin) {
        this.dirStats = dirStats;
        for (String command : plugin.getDescription().getCommands().keySet())
            setCommand(command);
    }


    private File getListFile() {
        return new File(dirStats.toString() + "\\Commands.yml");
    }

    private YamlConfiguration loadCfg() {
        return YamlConfiguration.loadConfiguration(getListFile());
    }

    private void safeFile(YamlConfiguration cfg) {
        try {
            cfg.save(this.getListFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setCommand(String command) {
        YamlConfiguration cfg = loadCfg();
        if (cfg.get("actualCommands." + command) == null)
            cfg.set("actualCommands." + command + ".stage", "alpha");
        safeFile(cfg);
    }

    public Map<String, Map<String, Object>> getCommandMap() {
        YamlConfiguration cfg = loadCfg();
        Map<String, Map<String, Object>> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        ConfigurationSection section = cfg.getConfigurationSection("actualCommands");
        Map<String, Object> sectionValues = section.getValues(true);
        String command = "";
        for (String key : sectionValues.keySet()) {
            if (!key.contains(".")) {
                if (!command.equals(""))
                    map1.put(command, map2);
                command = key;
                map2 = new HashMap<>();
            } else {
                map2.put(key.substring(command.length() + 1), sectionValues.get(key));
            }
        }
        map1.put(command, map2);
        return map1;
    }

    public void setStage(String command, String stage) {
        YamlConfiguration cfg = loadCfg();
        cfg.set("actualCommands." + command + ".stage", stage);
        safeFile(cfg);
    }

    public void addComment(String comment, String command) {
        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList("actualCommands." + command + ".comments");
        if (list == null)
            list = new ArrayList<>();
        if (!list.contains(comment)) {
            list.add(comment);
            cfg.set("actualCommands." + command + ".comments", list);
            safeFile(cfg);
        }
    }

    public List<String> getComments(String command) {
        YamlConfiguration cfg = loadCfg();
        return cfg.getStringList("actualCommands." + command + ".comments");
    }
}
