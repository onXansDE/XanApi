package onxansde.xanapi.commands;

import onxansde.xanapi.Logger;
import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedPlayer;
import onxansde.xanapi.utils.XanCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DebugCommand implements XanCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(XanApi.instance.players.getPlayer((Player) sender).hasPermission("xanapi.debug.toggle",true)) {
                if(XanApi.instance.logger.debug) {
                    XanApi.instance.logger.debug = false;
                    XanApi.instance.players.getPlayer((Player) sender).sendMessage("Debug deactivated!");
                } else {
                    XanApi.instance.logger.debug = true;
                    XanApi.instance.players.getPlayer((Player) sender).sendMessage("Debug activated!");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
