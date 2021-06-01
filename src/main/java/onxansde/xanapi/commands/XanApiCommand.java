package onxansde.xanapi.commands;

import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedPlayer;
import onxansde.xanapi.utils.XanCommand;
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
        }
        return list;
    }
}
