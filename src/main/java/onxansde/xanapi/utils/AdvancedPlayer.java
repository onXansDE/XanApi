package onxansde.xanapi.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import onxansde.xanapi.XanApi;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class AdvancedPlayer {
    //Attributes
    public Player player;

    //Constructor
    public AdvancedPlayer(Player player) {
        this.player = player;
    }

    //Functions
    public boolean isAdmin() {
        return hasPermission("xanapi.admin",true,false);
    }

    public boolean isAdminSilent() {
        return hasPermission("xanapi.admin",false,false);
    }

    public boolean hasPermission(String permission, boolean notify) {
        if(player.hasPermission(permission)) return true;
        if(notify) {
            sendMessage(XanApi.prefix + ChatColor.translateAlternateColorCodes('&',XanApi.config.getString("noperms")));
        }
        return false;
    }

    public boolean hasPermission(String permission) {
        if(player.hasPermission(permission)) return true;
        return false;
    }

    public boolean hasPermission(String permission, boolean notify, boolean allowop) {
        if(player.hasPermission(permission)) return true;
        if(allowop && player.isOp()) return true;
        if(notify) {
            sendMessage(XanApi.prefix + ChatColor.translateAlternateColorCodes('&',XanApi.config.getString("noperms")));
        }
        return false;
    }


    public void sendMessage(String message) {
        player.sendMessage(XanApi.prefix + message);
    }

    public void sendRawMessage(String message) {
        player.sendMessage(message);
    }


    public void sendActionBarMessage(String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    public boolean addItemToInv(ItemStack item) {
        ItemStack[] itemStacks = player.getInventory().getContents();
        for(ItemStack s : itemStacks) {
            if(s != null) {
                if(s.isSimilar(item)) {
                    int amount = item.getMaxStackSize() - s.getAmount();
                    if(amount >= item.getAmount()) {
                        player.getInventory().addItem(item);
                        return true;
                    }
                }
            }
        }
        int freeslot = player.getInventory().firstEmpty();
        if(freeslot != -1) {
            player.getInventory().setItem(freeslot, item);
            return true;
        }
        return false;
    }

    //STATIC
    public static ArrayList<AdvancedPlayer> list = new ArrayList<AdvancedPlayer>();

    public static AdvancedPlayer getPlayerByUuid(UUID uuid) {
        for (AdvancedPlayer ap : list) {
            if(ap.player.getUniqueId().compareTo(uuid) == 0) return ap;
        }
        return null;
    }

    public static AdvancedPlayer getPlayerByName(String name) {
        for(AdvancedPlayer ap : list) {
            if(ap.player.getDisplayName().equals(name)) return ap;
        }
        return null;
    }
}
