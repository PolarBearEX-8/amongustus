package augustus.modules.movement;

import augustus.modes.movement.FastLadderModeFactory;
import augustus.modes.movement.SpeedMotionMode;
import augustus.modes.movement.SpeedPositionMode;
import augustus.modes.movement.SpeedVerusMode;
import augustus.modes.movement.SpeedVulcanMode;
import client.event.EventMotionUpdate;
import client.event.MotionUpdateListener;
import client.event.TriFunction;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.DynamicModeSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import java.awt.Color;
import java.util.function.BiFunction;

public class FastLadder extends Module implements UpdateStateListener, MotionUpdateListener {
    public DynamicModeSetting settingModes;
    public NumberSetting settingTimer;
    public BooleanSetting settingStopOnEnd;
    public boolean expanded2;
    public boolean enabled2;

    public FastLadder() {
        super("FastLadder", "Schnellleiter", new Color(166, 168, 50), Category.MOVEMENT);
        this.settingModes = new DynamicModeSetting(
            this,
            "Modes",
            "Motion",
            new BiFunction[]{
                (object, object2) -> new SpeedMotionMode((FastLadder)object, (SettingGroup)object2),
                (object3, object4) -> new SpeedPositionMode((FastLadder)object3, (SettingGroup)object4),
                (object5, object6) -> new SpeedVerusMode((FastLadder)object5, (SettingGroup)object6),
                (object7, object8) -> new SpeedVulcanMode((FastLadder)object7, (SettingGroup)object8)
            }
        ).getDynamicModeSetting((object9, object10, object11) -> new FastLadderModeFactory((FastLadder)object9, (SettingGroup)object10, (String)object11));
        this.settingTimer = new NumberSetting(this, "Timer", 1.0, 0.1, 2.0, 2);
        this.settingStopOnEnd = new BooleanSetting(this, "StopOnEnd", true);
    }

    @Override
    public void onEnable() {
        this.registerEvent(43);
        this.registerEvent(18);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = false;
        this.enabled2 = false;
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        if (this.checkCondition()) {
            minecraft.getTimer().scaleFactor2 = 1.0F;
            this.enabled2 = false;
        }
    }

    @Override
    public void onPostUpdateState() {
        this.applyString4(this.settingModes.getSelected());
        this.expanded2 = minecraft.thePlayer.canExecute9();
    }

    public boolean checkCondition() {
        return minecraft.thePlayer.canExecute9();
    }
}
