package onxansde.xanapi.commands;

import onxansde.xanapi.XanApi;
import onxansde.xanapi.menus.PlayerMenuUtil;
import onxansde.xanapi.utils.AdvancedPlayer;
import onxansde.xanapi.utils.ColorUtil;
import onxansde.xanapi.utils.Mysql.MySqlPlayerObject;
import onxansde.xanapi.utils.XanCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
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

            if(args.length == 1) {
                if(args[0].equals("colortestmenu")) {
                    new ColorTestMenu(new PlayerMenuUtil(player)).open();
                }
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
        if(args.length == 2) {
            if(args[0].equals("colortest")) {
                switch (args[1]) {
                    case "rgbstring": {
                        sender.sendMessage(ColorUtil.RgbString(XanApi.getInstance().rapidCounterUtil,"This is a test", 1f, 1f));
                    }
                    break;
                    case "rgbwave": {
                        sender.sendMessage(ColorUtil.RgbWaveString(XanApi.getInstance().rapidCounterUtil,"This is a test", 1f, 1f, true));
                    }
                    break;
                    case "customcycle": {
                        sender.sendMessage(ColorUtil.CustomCycleString(XanApi.getInstance().rapidCounterUtil,"This is a test", new Color(255, 0, 0), new Color(0, 0, 255)));
                    }
                    break;
                    case "customcyclewave": {
                        sender.sendMessage(ColorUtil.CustonWaveString(XanApi.getInstance().rapidCounterUtil,"This is a test", new Color(255, 0, 0), new Color(0, 0, 255), true));
                    }
                    break;
                }
            }
            if(args[0].equals("countertest")) {
                switch (args[1]) {
                    case "slow": {
                        sender.sendMessage("SlowCounter: " + XanApi.getInstance().slowCounterUtil.getStateScale() + " State:" +XanApi.getInstance().slowCounterUtil.state + "/" + XanApi.getInstance().slowCounterUtil.max);
                    }
                    break;
                    case "fast": {
                        sender.sendMessage("FastCounter: " + XanApi.getInstance().fastCounterUtil.getStateScale() + " State:" +XanApi.getInstance().fastCounterUtil.state + "/" + XanApi.getInstance().fastCounterUtil.max);
                    }
                    break;
                    case "rapid": {
                        sender.sendMessage("RapidCounter: " + XanApi.getInstance().rapidCounterUtil.getStateScale() + " State:" +XanApi.getInstance().rapidCounterUtil.state + "/" + XanApi.getInstance().rapidCounterUtil.max);
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
            list.add("colortestmenu");
            list.add("colortest");
            list.add("countertest");
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
                case "colortest":
                {
                    list.add("rgbstring");
                    list.add("rgbwave");
                    list.add("customcycle");
                    list.add("customcyclewave");
                }
                break;
                case "countertest":
                {
                    list.add("slow");
                    list.add("fast");
                    list.add("rapid");
                }
                break;
            }
        }
        return list;
    }
}
