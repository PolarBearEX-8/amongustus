package augustus.modules.movement;

import augustus.modes.movement.MovementRecordSnapshot;
import augustus.modes.movement.MovementReplayMode;
import client.module.Category;
import client.module.Module;
import client.setting.DynamicModeSetting;
import client.setting.SettingGroup;
import client.util.OnlineConfigsManager;
import java.awt.Color;
import java.io.File;
import java.util.function.BiFunction;

public class MovementRecorder extends Module {
    public static String displayName2 = OnlineConfigsManager.text + File.separator + "movement_recordings";
    public DynamicModeSetting settingMode;

    public MovementRecorder() {
        super("MovementRecorder", "MovementAufzeichner", Color.GREEN, Category.MOVEMENT);
        BiFunction[] biFunctionArray = new BiFunction[2];
        BiFunction biFunction = (object, object2) -> new MovementRecordSnapshot((MovementRecorder)object, (SettingGroup)object2);
        biFunctionArray[0] = biFunction;
        BiFunction biFunction2 = (object3, object4) -> new MovementReplayMode((MovementRecorder)object3, (SettingGroup)object4);
        biFunctionArray[1] = biFunction2;
        this.settingMode = new DynamicModeSetting(this, "Mode", "Record", biFunctionArray);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
    }

    @Override
    public void onPreDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
    }
}
