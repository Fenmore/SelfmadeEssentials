package de.wonderworld.plugins.selfmadeEssentials.files;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModYMLManager extends BaseYMLManager {

    @Override
    protected File getYMLFile() {
        return new File(Essentials.dirStats.toString() + "\\Moderator.yml");
    }

    /**
     * NUR GÜLTIG BEI EINFACHEN LISTEN
     */
    public void handlePlayerNameChange(String playerBefore, String playerNow) {

        YamlConfiguration cfg = loadCfg();
        cfg.getKeys(false)
                .stream()
                .filter(
                        key ->
                                cfg.getStringList(key).contains(playerBefore)
                )
                .forEach(
                        key -> {
                            List<String> list = cfg.getStringList(key);
                            list.remove(playerBefore);
                            list.add(playerNow);
                            cfg.set(key, list);
                        }
                );
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
        return socialSpyList.contains(playerName);
    }

    public List<String> getSocialSpyList() {
        YamlConfiguration cfg = loadCfg();
        List<String> list = cfg.getStringList("socialspy");
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
}
