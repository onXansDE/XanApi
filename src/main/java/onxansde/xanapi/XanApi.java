package onxansde.xanapi;

import onxansde.xanapi.commands.DebugCommand;
import onxansde.xanapi.events.JoinLeave;
import onxansde.xanapi.events.MenuListener;
import onxansde.xanapi.utils.Players;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class XanApi extends JavaPlugin {

    public static XanApi instance;

    public static XanApi getInstance() {
        return instance;
    }

    public static String prefix = "§5XanApi §8> §7";

    public static FileConfiguration config;

    public Players players = new Players();
    public Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        logger = new Logger();

        saveDefaultConfig();
        config = getConfig();
        prefix = config.getString("prefix");

        getCommand("debug").setExecutor(new DebugCommand());

        Bukkit.getPluginManager().registerEvents(new JoinLeave(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
