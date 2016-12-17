package de.wonderworld.plugins.selfmadeEssentials.files;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModYMLManager {

    private File getModFile() {
        return new File(Essentials.dirStats.toString() + "\\Moderator.yml");
    }

    private YamlConfiguration loadCfg() {
        return YamlConfiguration.loadConfiguration(getModFile());
    }

    private void safeFile(YamlConfiguration cfg) {
        try {
            cfg.save(getModFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * NUR GÜLTIG BEI EINFACHEN LISTEN
     */
    public void handlePlayerNameChange(String playerBefore, String playerNow) {
        YamlConfiguration cfg = loadCfg();
        for (String key : cfg.getKeys(false)) {
            if (cfg.getStringList(key).contains(playerBefore)) {
                List<String> list = cfg.getStringList(key);
                list.remove(playerBefore);
                list.add(playerNow);
                cfg.set(key, list);
            }
        }
    }


    public void toggleSocialSpy(String playerName) {
        YamlConfiguration cfg = loadCfg();
        List<String> socialSpyList = cfg.getStringList("socialspy");
        if (socialSpyList == null)
            socialSpyList = new ArrayList<>();
        if (socialSpyList.contains(playerName))
            socialSpyList.remove(playerName);
        else
            socialSpyList.add(playerName);
        cfg.set("socialspy", socialSpyList);
        safeFile(cfg);
    }

    public boolean socialSpyIsActive(String playerName) {
        YamlConfiguration cfg = loadCfg();
        List<String> socialSpyList = cfg.getStringList("socialspy");
        if (socialSpyList.contains(playerName))
            return true;
        else
            return false;
    }

    public List<String> getSocialSpyList() {
        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList("socialspy");
        if (list == null)
            list = new ArrayList<>();
        return list;
    }

    public List<String> getVanishVisibleList() {
        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList("vanishVisible");
        if (list == null)
            list = new ArrayList<>();
        return list;
    }

    public List<String> getVanishActiveList() {
        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList("vanish");
        if (list == null)
            list = new ArrayList<>();
        return list;
    }

    public void setVanish(String name) {
        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList("vanish");
        if (list == null)
            list = new ArrayList<>();
        list.add(name);
        cfg.set("vanish", list);
        safeFile(cfg);
    }

    public void remVanish(String name) {
        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList("vanish");
        if (list == null)
            return;
        list.remove(name);
        cfg.set("vanish", list);
        safeFile(cfg);
    }

    /*public void setVanishVisible(String name) {
        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList("vanishVisible");
        if(list == null)
            list = new ArrayList<>();
        list.add(name);
        cfg.set("vanishVisible", list);
        safeFile(cfg);
    }*/

    /*public void remVanishVisible(String name) {
        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList("vanishVisible");
        if(list == null)
            return;
        list.remove(name);
        cfg.set("vanishVisible", list);
        safeFile(cfg);
    }*/

    public boolean getGodmode(String name) {
        return false;
    }

    public void setGodmode(String name, boolean godmode) {

    }
}
