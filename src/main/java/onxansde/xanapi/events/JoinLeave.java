package onxansde.xanapi.events;

import onxansde.xanapi.Logger;
import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        AdvancedPlayer player = new AdvancedPlayer(e.getPlayer());

        XanApi.getInstance().players.list.add(player);
        XanApi.getInstance().logger.log("Registered player " + e.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        AdvancedPlayer player = XanApi.getInstance().players.getPlayerByUuid(e.getPlayer().getUniqueId());
        XanApi.getInstance().players.list.remove(player);
        XanApi.getInstance().logger.log("Unregistered player " + e.getPlayer().getName());
    }
}
