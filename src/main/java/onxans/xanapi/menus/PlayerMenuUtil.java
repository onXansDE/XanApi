package onxans.xanapi.menus;

import onxans.xanapi.utils.AdvancedPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerMenuUtil {

    private AdvancedPlayer owner;

    //TODO IMPROVE API

    public PlayerMenuUtil(AdvancedPlayer p) {
        this.owner = p;
    }

    public AdvancedPlayer getOwner() {
        return owner;
    }
}
