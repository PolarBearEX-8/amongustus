package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.ModeSetting;
import client.util.BooleanSupplier;
import java.awt.Color;

public class CustomSkin extends Module {
    public ModeSetting settingSkinMode = new ModeSetting(this, "SkinMode", "default", new String[]{"default", "slim"});
    public ModeSetting settingCustomName;
    public static String[] stringArray;

    public CustomSkin() {
        super("CustomSkin", "KundenspezifischeHaut", new Color(67, 209, 174), Category.RENDER);
        this.settingCustomName = new ModeSetting(this, "CustomName", "Zlimuzin", new String[0]).getModeSetting(() -> canExecute5());
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        if (minecraft.thePlayer != null) {
            if (minecraft.thePlayer.getNetworkPlayerInfo() != null) {
                minecraft.thePlayer.getNetworkPlayerInfo().state6 = false;
                minecraft.thePlayer.getNetworkPlayerInfo().updateState();
            }
        }
    }

    @Override
    public void onPreDisable() {
        if (minecraft.thePlayer != null) {
            if (minecraft.thePlayer.getNetworkPlayerInfo() != null) {
                minecraft.thePlayer.getNetworkPlayerInfo().state6 = false;
                minecraft.thePlayer.getNetworkPlayerInfo().updateState();
            }
        }
    }

    public static String[] getStringArray() {
        return stringArray;
    }

    public static void setStringArray(String[] textArray) {
        stringArray = textArray;
    }

    private static boolean canExecute5() {
        return false;
    }
}
