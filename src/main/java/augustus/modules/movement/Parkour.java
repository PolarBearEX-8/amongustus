package augustus.modules.movement;

import augustus.modes.combat.AuraRenderMode;
import client.ClientGlobals;
import client.event.EventMove;
import client.event.MoveListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.RotationManager;
import client.setting.BooleanSetting;
import client.util.BooleanSupplier;
import client.util.KeyUtils;
import client.util.SimulatedPlayer;
import java.awt.Color;

public class Parkour extends Module implements MoveListener, TickListener {
    public BooleanSetting settingDiagonal = new BooleanSetting(this, "Diagonal", false);
    public BooleanSetting settingMouseSensi;

    public Parkour() {
        super("Parkour", "Parkur", new Color(245, 103, 167), Category.MOVEMENT);
        BooleanSetting booleanSetting = new BooleanSetting(this, "MouseSensi", true);
        this.settingMouseSensi = (BooleanSetting)booleanSetting.getSetting(this.settingDiagonal::isEnabled);
    }

    @Override
    public void onEnable() {
        this.registerEvent(21);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onMove(EventMove event) {
        if (this.canExecute5()) {
            AuraRenderMode auraRenderMode = AuraRenderMode.DEFAULT;
            event.setAuraRenderMode(auraRenderMode);
        }
    }

    @Override
    public void onPreTick() {
        byte state = ((byte)(this.settingDiagonal.isEnabled() ? 1 : 0));
        RotationManager rotationManager = ClientGlobals.rotationManager;
        float value;
        if (this.settingMouseSensi.isEnabled()) {
            float value2 = minecraft.thePlayer.rotationYaw - (float)(45 * state);
            RotationManager rotationManager2 = ClientGlobals.rotationManager;
            value = SimulatedPlayer.getFloat2(value2, rotationManager2.getRotation3().yaw);
        } else {
            value = minecraft.thePlayer.rotationYaw - (float)(45 * state);
        }

        rotationManager.applyModule2(value, 11, this);
    }

    private boolean checkCondition() {
        return minecraft.isIntegratedServerRunning();
    }

    private int getInt2() {
        return KeyUtils.getInt2();
    }

    private boolean canExecute5() {
        return this.checkCondition();
    }
}
