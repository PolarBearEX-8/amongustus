package augustus.modules.render;

import client.event.EventStrafe;
import client.event.StrafeListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import java.awt.Color;

public class Child extends Module implements StrafeListener {
    public BooleanSetting settingEditEyeHeight = new BooleanSetting(this, "EditEyeHeight", true);
    public BooleanSetting settingRemoveCapes = new BooleanSetting(this, "RemoveCapes", true);
    public static boolean activeState;

    public Child() {
        super("Child", "Kind", new Color(52, 217, 148), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(24, 3);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onStrafe(EventStrafe event) {
        if (this.settingEditEyeHeight.isEnabled()) {
            event.processAction3(0.85F);
        }
    }

    public static boolean canExecute5() {
        return !canExecute6();
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public static boolean canExecute6() {
        return activeState;
    }
}
