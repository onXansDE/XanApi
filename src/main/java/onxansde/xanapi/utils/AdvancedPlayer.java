package onxansde.xanapi.utils;

import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.node.types.PermissionNode;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.EconomyResponse;
import onxansde.xanapi.XanApi;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
            sendMessage(XanApi.instance.config.getString("messages.noperms"));
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
            sendMessage(XanApi.instance.config.getString("messages.noperms"));
        }
        return false;
    }

    public User getUser() {
        return XanApi.instance.perms.getPlayerAdapter(Player.class).getUser(player);
    }

    public Collection<Group> getGroups() {
        User user = getUser();
        return user.getInheritedGroups(user.getQueryOptions());
    }

    public List<String> getGroupStrings() {
        List<String> strings = new ArrayList<>();
        for(Group g : getGroups()) {
            strings.add(g.getName());
        }
        return strings;
    }

    public void addGroup(Group group) {
        User user = getUser();
        Node node = InheritanceNode.builder(group).build();
        user.data().add(node);
        XanApi.instance.perms.getUserManager().saveUser(user);
    }

    public boolean addGroup(String name) {
        Group group = XanApi.instance.perms.getGroupManager().getGroup(name);
        if(group == null) return false;
        addGroup(group);
        return true;
    }

    public void removeGroup(Group group) {
        User user = getUser();
        Node node = InheritanceNode.builder(group).build();
        user.data().remove(node);
        XanApi.instance.perms.getUserManager().saveUser(user);
    }

    public boolean removeGroup(String name) {
        Group group = XanApi.instance.perms.getGroupManager().getGroup(name);
        if(group == null) return false;
        removeGroup(group);
        return true;
    }

    public void addPermission(String permission) {
        User user = getUser();
        Node node = PermissionNode.builder(permission).build();
        user.data().add(node);
        XanApi.instance.perms.getUserManager().saveUser(user);
    }

    public int getBalance() {
        return (int) XanApi.instance.economy.getBalance(player);
    }

    public void depositMoney(int amount) {
        XanApi.instance.economy.depositPlayer(player, amount);
    }

    public boolean withdrawMoney(int amount) {
        EconomyResponse response = XanApi.instance.economy.withdrawPlayer(player, amount);
        return response.transactionSuccess();
    }

    public void removePermission(String permission) {
        User user = getUser();
        Node node = PermissionNode.builder(permission).build();
        user.data().remove(node);
        XanApi.instance.perms.getUserManager().saveUser(user);
    }



    public void sendMessage(String message) {
        player.sendMessage(XanApi.instance.prefix + message);
    }

    public void sendRawMessage(String message) {
        player.sendMessage(message);
    }


    public void sendActionBarMessage(String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    public void teleport(Location location) {
        player.teleport(location);
    }

    public void teleport(Entity entity) {
        player.teleport(entity.getLocation());
    }

    public Location getLocation() {
        return player.getLocation();
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
}
