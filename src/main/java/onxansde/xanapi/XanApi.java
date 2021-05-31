package onxansde.xanapi;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class XanApi extends JavaPlugin {

    public static XanApi instance;

    public static XanApi getInstance() {
        return instance;
    }

    public static String prefix = "§5XanApi §8> §7";

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic

        saveDefaultConfig();
        config = getConfig();
        prefix = config.getString("prefix");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
