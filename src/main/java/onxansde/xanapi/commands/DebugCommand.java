package onxansde.xanapi.commands;

import onxansde.xanapi.Logger;
import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(XanApi.instance.players.getPlayerByUuid(((Player)sender).getUniqueId()).hasPermission("xanapi.debug.toggle",true)) {
                Logger.get().debug = !Logger.get().debug;
            }
        }
        return false;
    }
}
