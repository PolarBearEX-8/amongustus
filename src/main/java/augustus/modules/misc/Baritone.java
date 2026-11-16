package augustus.modules.misc;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class Baritone extends Module {
    public static String[] stringArray;

    public Baritone() {
        super("Baritone", "Der Bariton", new Color(82, 222, 124), Category.MISC);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    public static String[] getStringArray() {
        return stringArray;
    }

    public static void setStringArray(String[] textArray) {
        stringArray = textArray;
    }
}
