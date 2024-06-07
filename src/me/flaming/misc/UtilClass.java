package me.flaming.misc;

import me.flaming.PluginMain;
import me.flaming.utils.NBTEditor;

public class UtilClass {
    public static PluginMain main;
    public static void init(PluginMain main) {
        NBTEditor.main = main;
    }
}
