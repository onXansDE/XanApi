package onxansde.xanapi.commands;

import onxansde.xanapi.menus.Menu;
import onxansde.xanapi.menus.PlayerMenuUtil;
import onxansde.xanapi.utils.AdvancedItem;
import org.bukkit.Material;

import java.awt.*;

public class ColorTestMenu extends Menu {

    public ColorTestMenu(PlayerMenuUtil playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Color Test";
    }

    @Override
    public int getSlots() {
        return 9*3;
    }


    @Override
    public void setMenuItems() {
        setRgbItem(11, AdvancedItem.makeAdvancedItem(Material.GREEN_WOOL,"RGBWave"));
        setCustomWaveItem(15, AdvancedItem.makeAdvancedItem(Material.GREEN_WOOL,"CustomWave"), new Color(255, 0, 0), new Color(0, 0, 255));
        setFillerGlass();
    }
}
