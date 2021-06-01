package onxansde.xanapi.commands;

import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedPlayer;
import onxansde.xanapi.utils.Mysql.MySqlPlayerObject;
import onxansde.xanapi.utils.XanCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class XanApiCommand implements XanCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            AdvancedPlayer player = XanApi.getInstance().players.getPlayer((Player) sender);
            if(!player.hasPermission("xanapi.command",true)) {
                return false;
            }
        }

        if(args.length == 1) {
            if(args[0].equals("reload")) {
                XanApi.instance.reloadConfig();
                sender.sendMessage(XanApi.instance.prefix + "Die Config wurde neu geladen");
            }
            if(args[0].equals("debug")) {
                if(XanApi.instance.logger.debug) {
                    XanApi.instance.logger.debug = false;
                    sender.sendMessage(XanApi.getInstance().prefix + "Debug deactivated!");
                } else {
                    XanApi.instance.logger.debug = true;
                    sender.sendMessage(XanApi.getInstance().prefix + "Debug activated!");
                }
            }
            if(args[0].equals("mysql")) {
                sender.sendMessage(XanApi.getInstance().prefix + "§aLoading...");
                Bukkit.getScheduler().runTaskLaterAsynchronously(XanApi.getInstance(),() -> {
                    XanApi.getInstance().mySqlUtil.updateInfos();
                    XanApi.getInstance().mySqlUtil.sendMySqlStatus(sender);
                }, 30);
            }
        }

        if(args.length == 3) {
            if(args[0].equals("mysql")) {
                switch (args[1]) {
                    case "playeruid": {
                        sender.sendMessage(XanApi.getInstance().prefix + "§aLoading...");
                        Bukkit.getScheduler().runTaskLaterAsynchronously(XanApi.getInstance(),() -> {
                            MySqlPlayerObject object = XanApi.getInstance().mySqlUtil.getMySqlPlayerByUid(Integer.parseInt(args[2]));
                            if(object != null) {
                                object.sendPlayerInfoMessage(sender);
                            } else {
                                sender.sendMessage(XanApi.getInstance().prefix + "Player not in database");
                            }
                        },25);
                    }
                    break;
                    case "playeruuid": {
                        sender.sendMessage(XanApi.getInstance().prefix + "§aLoading...");
                        Bukkit.getScheduler().runTaskLaterAsynchronously(XanApi.getInstance(),() -> {
                            MySqlPlayerObject object = XanApi.getInstance().mySqlUtil.getMySqlPlayerByUUID(args[2]);
                            if(object != null) {
                                object.sendPlayerInfoMessage(sender);
                            } else {
                                sender.sendMessage(XanApi.getInstance().prefix + "Player not in database");
                            }
                        },25);
                    }
                    break;
                    case "playername": {
                        sender.sendMessage(XanApi.getInstance().prefix + "§aLoading...");
                        Bukkit.getScheduler().runTaskLaterAsynchronously(XanApi.getInstance(),() -> {
                            MySqlPlayerObject object = XanApi.getInstance().mySqlUtil.getMySqlPlayerByName(args[2]);
                            if(object != null) {
                                object.sendPlayerInfoMessage(sender);
                            } else {
                                sender.sendMessage(XanApi.getInstance().prefix + "Player not in database");
                            }
                        },25);
                    }
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!sender.hasPermission("xanapi.command")) {
            return null;
        }
        List<String> list = new ArrayList<>();
        if(args.length == 1) {
            list.add("reload");
            list.add("debug");
            list.add("mysql");
        }
        if(args.length == 2) {
            switch (args[0]) {
                case "mysql":
                {
                    list.add("playeruid");
                    list.add("playername");
                    list.add("playeruuid");
                }
                    break;
            }
        }
        return list;
    }
}
