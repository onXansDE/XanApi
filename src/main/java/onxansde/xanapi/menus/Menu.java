package onxansde.xanapi.menus;

import onxansde.xanapi.XanApi;
import onxansde.xanapi.utils.AdvancedItem;
import onxansde.xanapi.utils.AdvancedPlayer;
import onxansde.xanapi.utils.ColorUtil;
import onxansde.xanapi.utils.CounterUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public abstract class Menu implements InventoryHolder {

    protected PlayerMenuUtil playerMenuUtility;
    protected Inventory inventory;
    protected ItemStack FILLER_GLASS = AdvancedItem.getFillerItem();
    protected ItemStack AIR = new ItemStack(Material.AIR);
    protected HashMap<String, WaveItemInfo> waveitems = new HashMap<>();
    protected BukkitTask updateTimer;
    protected CounterUtil counterUtil;

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
        updateTimer.cancel();
    }

    public void handleItemMove(InventoryMoveItemEvent e) {

    }


    //TODO ADD CALLBACKS

    //TODO IMPROVE API


    public void setRgbItem(int index, AdvancedItem stack) {
        stack.setDataString("rgbitem",stack.getDisplayName());
        ItemStack s = stack.getItem();
        inventory.setItem(index, s);
    }

    public void setCustomWaveItem(int index, AdvancedItem stack, Color color1, Color color2) {
        stack.setDataString("waveitem",stack.getDisplayName());
        ItemStack s = stack.getItem();
        waveitems.put(stack.getDisplayName(),new WaveItemInfo(color1, color2));
        inventory.setItem(index, s);
    }

    public abstract void setMenuItems();

    public void open() {
        counterUtil = new CounterUtil(300,5,1);
        counterUtil.startTimer();
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        this.setMenuItems();

        playerMenuUtility.getOwner().player.openInventory(inventory);
        startUpdateTimer();
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

    public void startUpdateTimer() {
        XanApi.getInstance().logger.log("Started Update Timer");
        updateTimer = Bukkit.getScheduler().runTaskTimer(XanApi.getInstance(), () -> {
            for(ItemStack stack : inventory.getContents()) {
                AdvancedItem item = new AdvancedItem(stack);
                String name = item.getDataString("rgbitem");
                if(name != null) {
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ColorUtil.RgbWaveString(counterUtil, name, 1f, 1f, true));
                    stack.setItemMeta(meta);
                }
                String name1 = item.getDataString("waveitem");
                if(name1 != null) {
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ColorUtil.CustonWaveString(counterUtil, name1, waveitems.get(name1).color1, waveitems.get(name1).color2, true));
                    stack.setItemMeta(meta);
                }
            }
        }, 0, 1);
        if(updateTimer != null)
            XanApi.getInstance().logger.log("Started Update Timer");
    }

    class WaveItemInfo {
        public Color color1;
        public Color color2;

        public WaveItemInfo(Color color1, Color color2) {
            this.color1 = color1;
            this.color2 = color2;
        }
    }
}
