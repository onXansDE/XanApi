package onxans.xanapi.utils;

import com.google.common.collect.Multimap;
import onxans.xanapi.XanApi;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class AdvancedItem implements java.io.Serializable {

    private ItemStack stack;


    public AdvancedItem(Material mat) {
        stack = new ItemStack(mat,1);
    }

    public AdvancedItem(ItemStack stack) {
        this.stack = stack.clone();
    }

    public ItemMeta getItemMeta() {
        return stack.getItemMeta();
    }

    public void setItemMeta(ItemMeta meta) {
        stack.setItemMeta(meta);
    }

    public ItemStack getItem() {
        ItemStack tempstack = stack.clone();
        return tempstack;
    }

    public String getDisplayName() {
        ItemMeta itemMeta = stack.getItemMeta();
        return itemMeta.getDisplayName();
    }

    public void setDisplayName(String displayName) {
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        stack.setItemMeta(itemMeta);
    }

    public int getAmount() {
        return stack.getAmount();
    }

    public void setAmount(int amount) {
        stack.setAmount(amount);
    }

    public void setMaterial(Material m) {
        stack.setType(m);
    }


    public boolean hasLore() {
        ItemMeta itemMeta = stack.getItemMeta();
        return itemMeta.hasLore();
    }

    public List<String> getLore() {
        ItemMeta itemMeta = stack.getItemMeta();
        if(itemMeta.hasLore())
            return itemMeta.getLore();
        return new ArrayList<String>();
    }

    public void setLore(List<String> strings) {
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setLore(strings);
        stack.setItemMeta(itemMeta);
    }

    public void setLore(String... strings) {
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setLore(Arrays.asList(strings));
        stack.setItemMeta(itemMeta);
    }

    public void addLore(String lore) {
        List<String> temp = getLore();
        temp.add(lore);
        setLore(temp);
    }

    public void setDataString(String key, String value) {
        ItemMeta itemMeta = stack.getItemMeta();
        NamespacedKey _key = new NamespacedKey(XanApi.getInstance(), key);
        itemMeta.getPersistentDataContainer().set(_key,PersistentDataType.STRING,value);
        stack.setItemMeta(itemMeta);
    }

    public void setDataInt(String key, int value) {
        ItemMeta itemMeta = stack.getItemMeta();
        NamespacedKey _key = new NamespacedKey(XanApi.getInstance(), key);
        itemMeta.getPersistentDataContainer().set(_key,PersistentDataType.INTEGER,value);
        stack.setItemMeta(itemMeta);
    }

    public void setDataDouble(String key, double value) {
        ItemMeta itemMeta = stack.getItemMeta();
        NamespacedKey _key = new NamespacedKey(XanApi.getInstance(), key);
        itemMeta.getPersistentDataContainer().set(_key,PersistentDataType.DOUBLE,value);
        stack.setItemMeta(itemMeta);
    }

    public String getDataString(String key) {
        ItemMeta itemMeta = stack.getItemMeta();
        NamespacedKey _key = new NamespacedKey(XanApi.getInstance(), key);
        return itemMeta.getPersistentDataContainer().get(_key,PersistentDataType.STRING);
    }

    public int getDataInt(String key) {
        ItemMeta itemMeta = stack.getItemMeta();
        NamespacedKey _key = new NamespacedKey(XanApi.getInstance(), key);
        return itemMeta.getPersistentDataContainer().get(_key,PersistentDataType.INTEGER);
    }

    public double getDataDoble(String key) {
        ItemMeta itemMeta = stack.getItemMeta();
        NamespacedKey _key = new NamespacedKey(XanApi.getInstance(), key);
        return itemMeta.getPersistentDataContainer().get(_key,PersistentDataType.DOUBLE);
    }

    public String getMaterialString() {
        return StringProccessing.SpaceWords(stack.getType().name());
    }

    public Set<ItemFlag> getItemFlags() {
        ItemMeta meta = stack.getItemMeta();
        return meta.getItemFlags();
    }

    public void setItemFlags(List<ItemFlag> flags) {
        ItemMeta itemMeta = stack.getItemMeta();
        Set<ItemFlag> flagSet = itemMeta.getItemFlags();
        for(ItemFlag flag : flagSet) {
            itemMeta.removeItemFlags(flag);
        }

        for(ItemFlag flag : flags) {
            itemMeta.addItemFlags(flag);
        }
        stack.setItemMeta(itemMeta);
    }

    public Multimap<Attribute, AttributeModifier> getAttributes() {
        ItemMeta itemMeta = stack.getItemMeta();
        return itemMeta.getAttributeModifiers();
    }

    public void resetAttributes() {
        ItemMeta meta = stack.getItemMeta();
        if(meta.getAttributeModifiers() == null) return;
        for(Attribute a : meta.getAttributeModifiers().keySet()) {
            meta.removeAttributeModifier(a);
        }
        stack.setItemMeta(meta);
    }

    public void addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        ItemMeta meta = stack.getItemMeta();
        meta.addAttributeModifier(attribute, modifier);
        stack.setItemMeta(meta);
    }

    public void resetEnchantments() {
        ItemMeta meta = stack.getItemMeta();
        for(Enchantment enchantment : meta.getEnchants().keySet()) {
            meta.removeEnchant(enchantment);
        }
    }

    public Map<Enchantment, Integer> getEnchantments() {
        ItemMeta itemMeta = stack.getItemMeta();
        return itemMeta.getEnchants();
    }

    public void addEnchantment(Enchantment enchantment, int level) {
        ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(enchantment,level,true);
        stack.setItemMeta(meta);
    }

    public void setEnchantments(Map<Enchantment, Integer> enchantments) {
        resetEnchantments();
        for(Enchantment enchantment : enchantments.keySet()) {
            addEnchantment(enchantment,enchantments.get(enchantment));
        }
    }

    public static String ToBase64(AdvancedItem item) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(item);
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static AdvancedItem FromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            AdvancedItem item = (AdvancedItem) dataInput.readObject();
            dataInput.close();
            return item;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

    public static boolean checkStaticItem(ItemStack i) {
        ItemMeta itemMeta = i.getItemMeta();
        NamespacedKey key = new NamespacedKey(XanApi.getInstance(), "static-item");
        return itemMeta.getPersistentDataContainer().has(key,PersistentDataType.INTEGER);
    }

    public static ItemStack getFillerItem() {
        Material type;
        ItemStack stack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE,1);
        ItemMeta im = stack.getItemMeta();
        Plugin plugin;
        NamespacedKey namespacedKey = new NamespacedKey(XanApi.getInstance(), "static-item");
        im.getPersistentDataContainer().set(namespacedKey,PersistentDataType.INTEGER,1);
        im.setDisplayName(" ");
        stack.setItemMeta(im);
        return stack;
    }

    public static ItemStack makeItem(Material material, String displayName, String... lore) {

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);

        itemMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemMeta);

        return item;
    }
}
