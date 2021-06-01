package onxansde.xanapi.events;

import onxansde.xanapi.menus.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {



    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) return;
        Player p = (Player) e.getWhoClicked();

        InventoryHolder holder = e.getClickedInventory().getHolder();
        if(holder instanceof Menu) {
            Menu menu = (Menu) holder;
            menu.handleMenuClick(e);
        }
    }

    @EventHandler
    public void onMenuDrag(InventoryDragEvent e) {
        if(e.getInventory() == null) return;

        Player p = (Player) e.getWhoClicked();

        InventoryHolder holder = e.getInventory().getHolder();

        if(holder instanceof Menu) {
            Menu menu = (Menu) holder;
            menu.handleMenuDrag(e);
        }
    }

    @EventHandler
    public void onMenuClose(InventoryCloseEvent e) {
        if(e.getInventory() == null) return;
        Player p = (Player) e.getPlayer();

        InventoryHolder holder = e.getInventory().getHolder();

        if(holder instanceof Menu) {
            Menu menu = (Menu) holder;
            menu.handleMenuClose(e);
        }
    }

    @EventHandler
    public void onItemMove(InventoryMoveItemEvent e) {
        if(e.getSource() == null) return;
        if(e.getDestination() == null) return;

        InventoryHolder holder = e.getDestination().getHolder();

        if(holder instanceof Menu) {
            Menu menu = (Menu) holder;
            menu.handleItemMove(e);
        }
    }
}