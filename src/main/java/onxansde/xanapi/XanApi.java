package onxansde.xanapi;

import net.luckperms.api.LuckPerms;
import onxansde.xanapi.commands.DebugCommand;
import onxansde.xanapi.events.JoinLeave;
import onxansde.xanapi.events.MenuListener;
import onxansde.xanapi.utils.Players;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
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
    public LuckPerms perms;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        logger = new Logger();

        saveDefaultConfig();
        config = getConfig();
        prefix = config.getString("prefix");

        getCommand("debugtoggle").setExecutor(new DebugCommand());

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            perms = provider.getProvider();
        }

        Bukkit.getPluginManager().registerEvents(new JoinLeave(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
