package onxansde.xanapi.events;

import onxansde.xanapi.Logger;
import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        AdvancedPlayer player = new AdvancedPlayer(e.getPlayer());
        player.loadMysqlObjectOnJoin();
        XanApi.getInstance().players.list.add(player);
        XanApi.getInstance().logger.log("Registered player " + e.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeave(PlayerQuitEvent e) {
        AdvancedPlayer player = XanApi.getInstance().players.getPlayerByUuid(e.getPlayer().getUniqueId());
        XanApi.getInstance().players.list.remove(player);
        XanApi.getInstance().logger.log("Unregistered player " + e.getPlayer().getName());
    }
}
