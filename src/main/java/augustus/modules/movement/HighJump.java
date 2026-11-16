package augustus.modules.movement;

import augustus.modes.movement.FlyDamageMode;
import augustus.modes.movement.FlyVanillaMode;
import augustus.modes.movement.HighJumpModeFactory;
import client.event.EventMotionUpdate;
import client.event.MotionUpdateListener;
import client.event.TriFunction;
import client.module.Category;
import client.module.Module;
import client.setting.DynamicModeSetting;
import client.util.CombatUtil;
import java.awt.Color;
import java.util.function.BiFunction;

public class HighJump extends Module implements MotionUpdateListener {
    public DynamicModeSetting settingModes;
    public int keyCode2;

    public HighJump() {
        super("HighJump", "Hochsprung", new Color(131, 184, 222), Category.MOVEMENT);
        BiFunction[] biFunctionArray = new BiFunction[]{FlyVanillaMode.getBiFunction(), FlyDamageMode.getBiFunction()};
        DynamicModeSetting dynamicModeSetting = new DynamicModeSetting(this, "Modes", "Vanilla", biFunctionArray);
        TriFunction altListener = HighJumpModeFactory.getAltListener();
        this.settingModes = dynamicModeSetting.getDynamicModeSetting(altListener);
        this.keyCode2 = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(18);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        if (this.settingModes.getSelected().equals("Damage")) {
            CombatUtil.updateState3();
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        this.applyString4(this.settingModes.getSelected());
    }
}
