package onxansde.xanapi.modules;

import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedPlayer;

public class Broadcast {

    public void broadcastMessage(String message) {
        for(AdvancedPlayer player : XanApi.getInstance().players.list) {
            player.sendMessage(message);
        }
    }

    public void broadcastActionBar(String message) {
        for(AdvancedPlayer player : XanApi.getInstance().players.list) {
            player.sendActionBarMessage(message);
        }
    }

    public void broadcastRaw(String message) {
        for(AdvancedPlayer player : XanApi.getInstance().players.list) {
            player.sendRawMessage(message);
        }
    }

    public void broadcastMessage(String message, String permission) {
        for(AdvancedPlayer player : XanApi.getInstance().players.list) {
            if(!player.hasPermission(permission)) continue;
            player.sendMessage(message);
        }
    }

    public void broadcastActionBar(String message, String permission) {
        for(AdvancedPlayer player : XanApi.getInstance().players.list) {
            if(!player.hasPermission(permission)) continue;
            player.sendActionBarMessage(message);
        }
    }

    public void broadcastRaw(String message, String permission) {
        for(AdvancedPlayer player : XanApi.getInstance().players.list) {
            if(!player.hasPermission(permission)) continue;
            player.sendRawMessage(message);
        }
    }

}
