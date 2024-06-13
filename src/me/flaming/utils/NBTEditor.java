package me.flaming.utils;

import me.flaming.misc.UtilClass;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class NBTEditor extends UtilClass {


    public static void rename (ItemStack item, String name) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ColorUtils.getColored(name));
        item.setItemMeta(itemMeta);
    }

    public static void enchant(ItemStack item, Enchantment enchantment, int level, boolean ignoreLevelRestriction, boolean hideEnchantment) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
        if (hideEnchantment) itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
    }

    public static <T, Z> void set(ItemStack item, String key, PersistentDataType<T, Z> type, Z value) {
        NamespacedKey namespacedKey = new NamespacedKey(main, key);
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        dataContainer.set(namespacedKey, type, value);
        item.setItemMeta(itemMeta);
    }

    public static void set(ItemStack item, String key, String value) {
        set(item, key, PersistentDataType.STRING, value);
    }

    public static void set(ItemStack item, String key, int value) {
        set(item, key, PersistentDataType.INTEGER, value);
    }

    public static void set(ItemStack item, String key, double value) {
        set(item, key, PersistentDataType.DOUBLE, value);
    }

    public static void set(ItemStack item, String key, long value) {
        set(item, key, PersistentDataType.LONG, value);
    }

    public static void set(ItemStack item, String key, byte value) {
        set(item, key, PersistentDataType.BYTE, value);
    }

    public static void set(ItemStack item, String key, float value) {
        set(item, key, PersistentDataType.FLOAT, value);
    }

    public static void set(ItemStack item, String key, boolean value) {
        set(item, key, PersistentDataType.BOOLEAN, value);
    }

    public static <T, Z> Z get(ItemStack item, String key, PersistentDataType<T, Z> type, Z defaultValue) {
        NamespacedKey namespacedKey = new NamespacedKey(main, key);
        PersistentDataContainer dataContainer = item.getItemMeta().getPersistentDataContainer();
        return dataContainer.getOrDefault(namespacedKey, type, defaultValue);
    }

    public static String getString(ItemStack item, String key, String defaultValue) {
        return get(item, key, PersistentDataType.STRING, defaultValue);
    }

    public static int getInt(ItemStack item, String key, int defaultValue) {
        return get(item, key, PersistentDataType.INTEGER, defaultValue);
    }

    public static double getDouble(ItemStack item, String key, double defaultValue) {
        return get(item, key, PersistentDataType.DOUBLE, defaultValue);
    }

    public static long getLong(ItemStack item, String key, long defaultValue) {
        return get(item, key, PersistentDataType.LONG, defaultValue);
    }

    public static byte getByte(ItemStack item, String key, byte defaultValue) {
        return get(item, key, PersistentDataType.BYTE, defaultValue);
    }

    public static float getFloat(ItemStack item, String key, float defaultValue) {
        return get(item, key, PersistentDataType.FLOAT, defaultValue);
    }

    public static boolean getBoolean(ItemStack item, String key, boolean defaultValue) {
        return get(item, key, PersistentDataType.BOOLEAN, defaultValue);
    }
}
