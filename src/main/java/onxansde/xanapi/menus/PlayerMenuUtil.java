package onxansde.xanapi.menus;

import onxansde.xanapi.utils.AdvancedPlayer;

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
