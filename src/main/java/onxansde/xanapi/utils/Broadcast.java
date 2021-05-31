package onxansde.xanapi.utils;

public class Broadcast {

    public static void broadcastMessage(String message) {
        for(AdvancedPlayer player : AdvancedPlayer.list) {
            player.sendMessage(message);
        }
    }

    public static void broadcastActionBar(String message) {
        for(AdvancedPlayer player : AdvancedPlayer.list) {
            player.sendActionBarMessage(message);
        }
    }

    public static void broadcastRaw(String message) {
        for(AdvancedPlayer player : AdvancedPlayer.list) {
            player.sendRawMessage(message);
        }
    }

    public static void broadcastMessage(String message, String permission) {
        for(AdvancedPlayer player : AdvancedPlayer.list) {
            if(!player.hasPermission(permission)) continue;
            player.sendMessage(message);
        }
    }

    public static void broadcastActionBar(String message, String permission) {
        for(AdvancedPlayer player : AdvancedPlayer.list) {
            if(!player.hasPermission(permission)) continue;
            player.sendActionBarMessage(message);
        }
    }

    public static void broadcastRaw(String message, String permission) {
        for(AdvancedPlayer player : AdvancedPlayer.list) {
            if(!player.hasPermission(permission)) continue;
            player.sendRawMessage(message);
        }
    }

}
