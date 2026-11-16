package augustus.modules.combat;

import augustus.modes.combat.CriticalsJumpMode;
import augustus.modes.combat.CriticalsPacketMode;
import augustus.modes.movement.StepCustompacketMode;
import augustus.modes.movement.StepLagMode;
import augustus.modes.player.NoFallNogroundMode;
import augustus.modes.player.NoFallPacketeditMode;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.DynamicModeSetting;
import client.util.BooleanSupplier;
import java.awt.Color;
import java.util.function.BiFunction;

public class Criticals extends Module {
    public DynamicModeSetting settingMode;
    public BooleanSetting settingOnlyWhileJumpKeyPressed;
    public BooleanSetting settingOnlyWhileKillAuraTarget;
    public BooleanSetting settingCriticalsInAir;
    public BooleanSetting settingCriticalsOnGround;
    public static int tickDelay;

    public Criticals() {
        super("Criticals", "KritischeTreffer", Color.RED, Category.COMBAT);
        BiFunction[] biFunctionArray = new BiFunction[]{
            NoFallNogroundMode.getBiFunction(),
            CriticalsPacketMode.getBiFunction(),
            StepCustompacketMode.getBiFunction(),
            NoFallPacketeditMode.getBiFunction(),
            CriticalsJumpMode.getBiFunction(),
            StepLagMode.getBiFunction()
        };
        this.settingMode = new DynamicModeSetting(this, "Mode", "NoGround", biFunctionArray);
        BooleanSetting booleanSetting = new BooleanSetting(this, "OnlyWhileJumpKeyPressed", false);
        this.settingOnlyWhileJumpKeyPressed = (BooleanSetting)booleanSetting.getSetting(() -> this.isModeVisible4());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "onlyWhileKillAuraTarget", false);
        this.settingOnlyWhileKillAuraTarget = (BooleanSetting)booleanSetting2.getSetting(() -> this.isModeVisible2());
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "CriticalsInAir", true);
        this.settingCriticalsInAir = (BooleanSetting)booleanSetting3.getSetting(() -> this.isModeVisible3());
        BooleanSetting booleanSetting4 = new BooleanSetting(this, "CriticalsOnGround", true);
        this.settingCriticalsOnGround = (BooleanSetting)booleanSetting4.getSetting(() -> this.isModeVisible());
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

    private boolean isModeVisible() {
        return !this.settingMode.getSelected().equals("Lag");
    }

    public static int getIndex() {
        return tickDelay;
    }

    private boolean isModeVisible2() {
        return !this.settingMode.getSelected().equals("Lag");
    }

    public static int getInt2() {
        return getIndex() == 0 ? 93 : 0;
    }

    public static void processAction(int index) {
        tickDelay = index;
    }

    private boolean isModeVisible3() {
        return !this.settingMode.getSelected().equals("Lag");
    }

    private boolean isModeVisible4() {
        return !this.settingMode.getSelected().equals("Lag");
    }
}
