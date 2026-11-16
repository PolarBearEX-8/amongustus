package augustus.modules.movement;

import augustus.modes.movement.FlyIntavenewMode;
import augustus.modes.movement.FlyJumpMode;
import augustus.modes.movement.FlySpeedMode;
import augustus.modes.movement.JesusAACMode;
import augustus.modes.movement.JesusMatrixMode;
import augustus.modes.movement.JesusNcpMode;
import augustus.modes.movement.JesusSpartanMode;
import client.ClientGlobals;
import client.event.TickListener;
import client.event.TriFunction;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.DynamicModeSetting;
import client.setting.ModeSetting;
import client.setting.SettingGroup;
import client.util.MoveListenerBridge;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.function.BiFunction;

public class Jesus extends Module implements TickListener {
    public DynamicModeSetting settingMode;
    public BooleanSetting settingDisableAtSneakJump;
    public ModeSetting settingIgnoreFlowingWaterForce;
    public TimeHelper timer;
    public static boolean activeState;

    public Jesus() {
        super("Jesus", "JesusChristus", new Color(39, 103, 146), Category.MOVEMENT);
        this.settingMode = new DynamicModeSetting(
            this,
            "Mode",
            "Solid",
            new BiFunction[]{
                (object, object2) -> new JesusAACMode((Jesus)object, (SettingGroup)object2),
                (object3, object4) -> new FlySpeedMode((Jesus)object3, (SettingGroup)object4),
                (object5, object6) -> new FlyJumpMode((Jesus)object5, (SettingGroup)object6),
                (object7, object8) -> new JesusSpartanMode((Jesus)object7, (SettingGroup)object8),
                (object9, object10) -> new MoveListenerBridge((Jesus)object9, (SettingGroup)object10),
                (object11, object12) -> new JesusNcpMode((Jesus)object11, (SettingGroup)object12),
                (object13, object14) -> new FlyIntavenewMode((Jesus)object13, (SettingGroup)object14)
            }
        ).getDynamicModeSetting((object15, object16, object17) -> new JesusMatrixMode((Jesus)object15, (SettingGroup)object16, (String)object17));
        this.settingDisableAtSneakJump = new BooleanSetting(this, "DisableAtSneak/Jump", false);
        this.settingIgnoreFlowingWaterForce = new ModeSetting(
            this, "IgnoreFlowingWaterForce", "Never", new String[]{"Never", "OnlyAtMovement", "OnlyWhileStanding", "Everytime"}
        );
        this.timer = new TimeHelper();
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRunTick() {
        this.applyString4(this.settingMode.getSelected());
    }

    public boolean canExecute5() {
        return ClientGlobals.moduleManager.jesus.settingDisableAtSneakJump.isEnabled();
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public static boolean canExecute7() {
        return !canExecute8();
    }

    public boolean canExecute6() {
        return this.isToggled();
    }

    public static boolean canExecute8() {
        return activeState;
    }
}
