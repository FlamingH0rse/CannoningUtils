package me.flaming.utils;

import me.flaming.misc.UtilClass;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils extends UtilClass {
    public static String getColored(String str) {
        String coloredStr = ChatColor.translateAlternateColorCodes('&', str);

        Pattern hexRegex = Pattern.compile("<##[a-zA-Z0-9]*>");
        Matcher matches = hexRegex.matcher(coloredStr);

        while (matches.find()) {
            String hexCode = "#" + matches.group().replaceAll("<##", "").replaceAll(">", "");
            coloredStr = coloredStr.replaceAll(matches.group(), net.md_5.bungee.api.ChatColor.of(hexCode).toString());
        }
        return coloredStr;
    }
    public static void send (Player player, String message) {
        String coloredStr = ChatColor.translateAlternateColorCodes('&', message);

        Pattern hexRegex = Pattern.compile("<##[a-zA-Z0-9]*>");
        Matcher matches = hexRegex.matcher(coloredStr);

        while (matches.find()) {
            String hexCode = "#" + matches.group().replaceAll("<##", "").replaceAll(">", "");
            coloredStr = coloredStr.replaceAll(matches.group(), net.md_5.bungee.api.ChatColor.of(hexCode).toString());
        }
        player.sendMessage(coloredStr);
    }
    public static void send (Player player, String message, ChatMessageType type) {
        String coloredStr = ChatColor.translateAlternateColorCodes('&', message);

        Pattern hexRegex = Pattern.compile("<##[a-zA-Z0-9]*>");
        Matcher matches = hexRegex.matcher(coloredStr);

        while (matches.find()) {
            String hexCode = "#" + matches.group().replaceAll("<##", "").replaceAll(">", "");
            coloredStr = coloredStr.replaceAll(matches.group(), net.md_5.bungee.api.ChatColor.of(hexCode).toString());
        }
        player.spigot().sendMessage(type, TextComponent.fromLegacyText(coloredStr));
    }
    public static void sendLines (Player player, List<String> messages) {
        for (String message : messages) {
            send(player, message);
        }
    }
    public static void sendLines (Player player, List<String> messages, ChatMessageType type) {
        for (String message : messages) {
            send(player, message, type);
        }
    }
    public static void log (String message) {
        main.getLogger().info(getColored(message));
    }
    public static void logLines (List<String> messages) {
        for (String message : messages) {
            log(message);
        }
    }
}
