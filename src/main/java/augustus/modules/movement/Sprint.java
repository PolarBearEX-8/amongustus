package augustus.modules.movement;

import augustus.modes.combat.AuraRenderMode;
import client.ClientGlobals;
import client.event.EventMove;
import client.event.EventMoveInput;
import client.event.MoveInputListener;
import client.event.MoveListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.RotationManager;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.util.BooleanSupplier;
import client.util.KeyUtils;
import client.util.MoveUtil;
import client.util.SimulatedPlayer;
import java.awt.Color;
import net.minecraft.client.Minecraft;

public class Sprint extends Module implements MoveInputListener, MoveListener, TickListener {
    public ModeSetting settingMode = new ModeSetting(
    this, "Mode", "Legit", new String[]{"Legit", "LegitRotationAllDirection", "AllDirection", "AllDirectionMotion"}
    );
    public BooleanSetting settingIgnoreCollisions = new BooleanSetting(this, "IgnoreCollisions", true);
    public BooleanSetting settingHungerSprint = new BooleanSetting(this, "HungerSprint", true);
    public BooleanSetting settingHungerSprintOnlyOnGround;
    public BooleanSetting settingJumpSprint;

    public Sprint() {
        super("Sprint", "Sprinten", new Color(170, 181, 182, 255), Category.MOVEMENT);
        BooleanSetting booleanSetting = new BooleanSetting(this, "HungerSprintOnlyOnGround", true);
        this.settingHungerSprintOnlyOnGround = (BooleanSetting)booleanSetting.getSetting(this.settingHungerSprint::isEnabled);
        this.settingJumpSprint = new BooleanSetting(this, "JumpSprint", false);
    }

    @Override
    public void onEnable() {
        this.registerEvent(36, -4);
        this.registerEvent(21, 10);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onMoveInput(EventMoveInput event) {
        if (this.settingJumpSprint.isEnabled() && MoveUtil.canExecute()) {
            event.setActive(true);
        }

        if (this.settingHungerSprint.isEnabled()) {
            if ((float)minecraft.thePlayer.getFoodLevelManager().getIndex() <= 6.0F) {
                if (this.settingHungerSprintOnlyOnGround.isEnabled()) {
                    event.setActive3(false);
                }
            }
        }
    }

    @Override
    public void onPreMoveInput(EventMoveInput event) {
        if (this.settingMode.getSelected().equals("AllDirectionMotion")) {
            event.setActive4(true);
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (minecraft.thePlayer.isRiding()
        && ClientGlobals.moduleManager.scaffold.isToggled()
        && ClientGlobals.moduleManager.scaffold.settingMode.getSelected().equals("None")) {
            event.setScaleFactor7(0.0F);
        }

        if (this.canExecute5()) {
            if (ClientGlobals.rotationManager.checkModule(this)) {
                if (event.getScaleFactor7() <= 0.0F || event.getScaleFactor2() != 0.0F) {
                    AuraRenderMode auraRenderMode = AuraRenderMode.DEFAULT;
                    event.setAuraRenderMode(auraRenderMode);
                }
            }
        }
    }

    @Override
    public void onPostMoveInput(EventMoveInput event) {
        event.setState(true);
        if (this.settingMode.getSelected().equals("AllDirection")) {
            event.setActive2(true);
        }
    }

    @Override
    public void onPreTick() {
        byte state = ((byte)(this.canExecute5() ? 1 : 0));
        int int2 = KeyUtils.getInt2();
        float value = (float)state;
        float value2 = (float)int2;
        double double7 = MoveUtil.getDouble7(value, value2, minecraft.thePlayer.rotationYaw);
        RotationManager rotationManager = ClientGlobals.rotationManager;
        float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
        float value3 = (float)Math.toDegrees(double7);
        float float9 = SimulatedPlayer.getFloat9(yaw, value3, 180.0F);
        RotationManager rotationManager2 = ClientGlobals.rotationManager;
        float float2 = SimulatedPlayer.getFloat2(float9, rotationManager2.getRotation3().yaw);
        rotationManager.applyModule2(float2, 11, this);
    }

    private boolean canExecute5() {
        return minecraft.isIntegratedServerRunning();
    }

    public static void updateState() {
    }
}
