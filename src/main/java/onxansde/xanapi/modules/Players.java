package onxansde.xanapi.modules;

import onxansde.xanapi.utils.AdvancedPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
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
            if(ap.getName().equals(name)) return ap;
        }
        return null;
    }

    public AdvancedPlayer getPlayer(Player player) {
        for(AdvancedPlayer ap : list) {
            if(ap.player == player) return ap;
        }
        return null;
    }

    public List<String> getPlayerNames() {
        List<String> stringList = new ArrayList<>();
        for(AdvancedPlayer ap : list) {
            stringList.add(ap.getName());
        }
        return stringList;
    }
}
