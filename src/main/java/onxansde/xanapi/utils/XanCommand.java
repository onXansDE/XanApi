package onxansde.xanapi.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public interface XanCommand extends CommandExecutor, TabCompleter {

    @Override
    boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    @Override
    List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args);

    /**
     *
     * @param starting The String all names should start with
     * @return All Player's starting with specified String
     */
    static List<String> getPlayerList(String starting) {
        List<String> strings = new ArrayList<>();
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getName().startsWith(starting)) strings.add(player.getDisplayName());
        }
        return strings;
    }

    /**
     *
     * @return All Player's
     */
    static List<String> getPlayerList() {
        List<String> strings = new ArrayList<>();
        for(Player player : Bukkit.getOnlinePlayers()) {
            strings.add(player.getDisplayName());
        }
        return strings;
    }
}
