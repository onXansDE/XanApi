package onxansde.xanapi;

import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import onxansde.xanapi.commands.DebugCommand;
import onxansde.xanapi.events.JoinLeave;
import onxansde.xanapi.events.MenuListener;
import onxansde.xanapi.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
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
    public ConfigManager configManager;
    public Broadcast broadcast;
    public LuckPerms perms;
    public PermUtils permUtils;
    public Economy economy;

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Commit
        instance = this;

        logger = new Logger();
        broadcast = new Broadcast();
        permUtils = new PermUtils();
        configManager = new ConfigManager();

        logger.log("Config setup...");
        reloadConfig();

        prefix = config.getString("prefix");

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            perms = provider.getProvider();
        } else {
            logger.log("LuckPerms Not found! Disabled!");
            getServer().getPluginManager().disablePlugin(this);
        }

        if(!setupEconomy()) {
            logger.log("Vault Not found! Disabled!");
            getServer().getPluginManager().disablePlugin(this);
        }

        Bukkit.getPluginManager().registerEvents(new JoinLeave(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(),this);

        registerCommand(new DebugCommand(),"debugtoggle",this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void reloadConfig() {
        configManager.setupModuleFolder("XanApi");
        configManager.saveDefaultFile("XanApi","config.yml",getResource("config.yml"));
        config = configManager.getFileAsConfig("XanApi","config.yml");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static void registerCommand(XanCommand command, String cmd, JavaPlugin plugin) {
        plugin.getCommand(cmd).setExecutor(command);
        plugin.getCommand(cmd).setTabCompleter(command);
    }
}
