package onxansde.xanapi.events;

import onxansde.xanapi.utils.AdvancedPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        AdvancedPlayer player = new AdvancedPlayer(e.getPlayer());

        AdvancedPlayer.list.add(player);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        AdvancedPlayer player = AdvancedPlayer.getPlayerByUuid(e.getPlayer().getUniqueId());
        AdvancedPlayer.list.remove(player);
    }
}
