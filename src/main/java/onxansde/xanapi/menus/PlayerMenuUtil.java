package onxansde.xanapi.menus;

import onxansde.xanapi.utils.AdvancedPlayer;
import org.bukkit.entity.Player;

public class PlayerMenuUtil {

    private AdvancedPlayer owner;

    //TODO IMPROVE API

    public PlayerMenuUtil(AdvancedPlayer p) {
        this.owner = p;
    }

    public AdvancedPlayer getOwner() {
        return owner;
    }

    public Player getPlayer() {
        return owner.player;
    }
}
