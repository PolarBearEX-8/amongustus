package augustus.modules.render;

import client.Client;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.Setting;
import java.awt.Color;
import org.lwjgl.opengl.Display;

public class InvisMode extends Module {
    public BooleanSetting settingDisableCustomEscapeMenu = new BooleanSetting(this, "Disable custom escape menu", true);
    public BooleanSetting settingDisableCustomMainMenu = new BooleanSetting(this, "Disable custom main menu", false);
    public static Setting[] settingsArray;

    public InvisMode() {
        super("InvisMode", "UnsichtbarMode", new Color(3, 252, 227), Category.RENDER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        Display.setTitle(Client.getClient().getString4() + " " + Client.getClient().getString5());
        minecraft.setIngameFocus(false);
    }

    @Override
    public void onPreDisable() {
        Display.setTitle("Minecraft 1.8.9");
        minecraft.setIngameFocus(true);
    }

    public static Setting[] getSettingArray() {
        return settingsArray;
    }

    public boolean canExecute5() {
        return this.settingDisableCustomEscapeMenu.isEnabled();
    }

    public boolean checkCondition() {
        return this.settingDisableCustomMainMenu.isEnabled();
    }

    public static void setSettingArray(Setting[] settingArray2) {
        settingsArray = settingArray2;
    }
}
