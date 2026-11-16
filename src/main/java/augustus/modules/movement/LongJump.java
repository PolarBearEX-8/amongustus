package augustus.modules.movement;

import augustus.modes.movement.LongJumpIntaveBoatMode;
import augustus.modes.movement.FlyNcpMode;
import augustus.modes.movement.LongJumpIntaveMode;
import augustus.modes.movement.LongJumpMatrixMode;
import augustus.modes.movement.LongJumpModeFactory;
import augustus.modes.movement.LongJumpNcpMode;
import client.event.EventMotionUpdate;
import client.event.MotionUpdateListener;
import client.event.TriFunction;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.DynamicModeSetting;
import client.util.BooleanSupplier;
import java.awt.Color;
import java.util.function.BiFunction;

public class LongJump extends Module implements MotionUpdateListener {
    public DynamicModeSetting settingModes;
    public BooleanSetting settingAutoDisable;
    public int keyCode2;
    public boolean expanded2;
    public static int tickDelay;

    public LongJump() {
        super("LongJump", "Weitsprung", new Color(243, 45, 134), Category.MOVEMENT);
        BiFunction[] biFunctionArray = new BiFunction[]{
            FlyNcpMode.getBiFunction(),
            LongJumpNcpMode.getBiFunction(),
            LongJumpIntaveMode.getBiFunction(),
            LongJumpMatrixMode.getBiFunction(),
            LongJumpIntaveBoatMode.getBiFunction()
        };
        DynamicModeSetting dynamicModeSetting = new DynamicModeSetting(this, "Modes", "NCP", biFunctionArray);
        TriFunction altListener = LongJumpModeFactory.getAltListener();
        this.settingModes = dynamicModeSetting.getDynamicModeSetting(altListener);
        BooleanSetting booleanSetting = new BooleanSetting(this, "AutoDisable", true);
        this.settingAutoDisable = (BooleanSetting)booleanSetting.getSetting(() -> this.isModesVisible());
        this.keyCode2 = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(18);
    }

    @Override
    public void onDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
    }

    @Override
    public void onPreDisable() {
        this.keyCode2 = 0;
        this.expanded2 = true;
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        this.applyString4(this.settingModes.getSelected());
        if (!this.settingModes.getSelected().equals("IntaveBoat")) {
            this.expanded2 = true;
        }
    }

    private boolean isModesVisible() {
        return this.settingModes.getSelected().equals("IntaveBoat");
    }

    public static int getIndex() {
        return tickDelay;
    }

    public static int getInt2() {
        return getIndex() == 0 ? 118 : 0;
    }

    public static void processAction(int index) {
        tickDelay = index;
    }
}
