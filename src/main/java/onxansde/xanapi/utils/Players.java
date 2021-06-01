package onxansde.xanapi.utils;

import java.util.ArrayList;
import java.util.UUID;

public class Players {
    //STATIC
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
}
