package onxansde.xanapi.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Players {
    public ArrayList<AdvancedPlayer> list = new ArrayList<AdvancedPlayer>();

    public AdvancedPlayer getPlayerByUuid(UUID uuid) {
        for (AdvancedPlayer ap : list) {
            if(ap.player.getUniqueId().compareTo(uuid) == 0) return ap;
        }
        return null;
    }

    public AdvancedPlayer getPlayerByName(String name) {
        for(AdvancedPlayer ap : list) {
            if(ap.player.getDisplayName().equals(name)) return ap;
        }
        return null;
    }

    public AdvancedPlayer getPlayer(Player player) {
        for(AdvancedPlayer ap : list) {
            if(ap.player == player) return ap;
        }
        return null;
    }
}
