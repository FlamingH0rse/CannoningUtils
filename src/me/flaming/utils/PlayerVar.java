package me.flaming.utils;

import me.flaming.misc.UtilClass;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerVar extends UtilClass {
    public static <T, Z> void set(Player player, String key, PersistentDataType<T, Z> type, Z value) {
        NamespacedKey namespacedKey = new NamespacedKey(main, key);
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        dataContainer.set(namespacedKey, type, value);
    }

    public static void set(Player player, String key, String value) {
        set(player, key, PersistentDataType.STRING, value);
    }

    public static void set(Player player, String key, int value) {
        set(player, key, PersistentDataType.INTEGER, value);
    }

    public static void set(Player player, String key, double value) {
        set(player, key, PersistentDataType.DOUBLE, value);
    }

    public static void set(Player player, String key, long value) {
        set(player, key, PersistentDataType.LONG, value);
    }

    public static void set(Player player, String key, byte value) {
        set(player, key, PersistentDataType.BYTE, value);
    }

    public static void set(Player player, String key, float value) {
        set(player, key, PersistentDataType.FLOAT, value);
    }

    public static void set(Player player, String key, boolean value) {
        set(player, key, PersistentDataType.BOOLEAN, value);
    }

    public static <T, Z> Z get(Player player, String key, PersistentDataType<T, Z> type, Z defaultValue) {
        NamespacedKey namespacedKey = new NamespacedKey(main, key);
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        return dataContainer.getOrDefault(namespacedKey, type, defaultValue);
    }

    public static String getString(Player player, String key, String defaultValue) {
        return get(player, key, PersistentDataType.STRING, defaultValue);
    }

    public static int getInt(Player player, String key, int defaultValue) {
        return get(player, key, PersistentDataType.INTEGER, defaultValue);
    }

    public static double getDouble(Player player, String key, double defaultValue) {
        return get(player, key, PersistentDataType.DOUBLE, defaultValue);
    }

    public static long getLong(Player player, String key, long defaultValue) {
        return get(player, key, PersistentDataType.LONG, defaultValue);
    }

    public static byte getByte(Player player, String key, byte defaultValue) {
        return get(player, key, PersistentDataType.BYTE, defaultValue);
    }

    public static float getFloat(Player player, String key, float defaultValue) {
        return get(player, key, PersistentDataType.FLOAT, defaultValue);
    }

    public static boolean getBoolean(Player player, String key, boolean defaultValue) {
        return get(player, key, PersistentDataType.BOOLEAN, defaultValue);
    }
}
