package onxansde.xanapi.menus;

import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class Menu implements InventoryHolder {

    protected PlayerMenuUtil playerMenuUtility;
    protected Inventory inventory;
    protected ItemStack FILLER_GLASS = AdvancedItem.getFillerItem();
    protected ItemStack AIR = new ItemStack(Material.AIR);

    protected Menu parent = null;

    protected boolean overideparrent = false;

    public Menu(PlayerMenuUtil playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
    }

    public Menu(PlayerMenuUtil playerMenuUtility, Menu parent) {
        this.playerMenuUtility = playerMenuUtility;
        this.parent = parent;
    }

    public abstract String getMenuName();

    public abstract int getSlots();

    public void handleMenuClick(InventoryClickEvent e) {

    }

    public void handleMenuDrag(InventoryDragEvent e) {

    }

    public void handleMenuClose(InventoryCloseEvent e) {
        if(overideparrent) {
            overideparrent = false;
            return;
        }
        if(parent != null) {
            Bukkit.getScheduler().runTaskLater(XanApi.getInstance(),() -> {
                parent.open();
            },1);
        }

    }

    public void handleItemMove(InventoryMoveItemEvent e) {

    }


    //TODO ADD CALLBACKS

    //TODO IMPROVE API


    public abstract void setMenuItems();

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        this.setMenuItems();

        playerMenuUtility.getOwner().player.openInventory(inventory);
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void setFillerGlass(){
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null){
                inventory.setItem(i, FILLER_GLASS);
            }
        }
    }
}
