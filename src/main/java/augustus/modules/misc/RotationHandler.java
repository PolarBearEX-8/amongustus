package augustus.modules.misc;

import augustus.modes.combat.AuraRenderMode;
import client.Client;
import client.ClientGlobals;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventBusManager;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.EventRotation;
import client.event.EventWorldLoad;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.RotationListener;
import client.event.UpdateStateListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.MoveUtil;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class RotationHandler
    extends Module
    implements WorldLoadListener,
    RotationListener,
    MoveListener,
    PacketListener,
    UpdateStateListener,
    AttackEntityListener {
    public ModeSetting settingRotationMode = new ModeSetting(this, "RotationMode", "Vanilla", new String[]{"Vanilla", "Tick"});
    public ModeSetting settingMouseSensMode = new ModeSetting(this, "MouseSensMode", "Advanced", new String[]{"Default", "Advanced"});
    public ModeSetting settingSmoothBackRotate = new ModeSetting(this, "SmoothBackRotate", "None", new String[]{"None", "Default", "New"});
    public RangeSetting settingYawSpeed;
    public RangeSetting settingPitchSpeed;
    public NumberSetting settingRotationSpeed;
    public RangeSetting settingSmoothBackRotateTickDelayAfterClosingInventory;
    public SettingGroup settingMovement;
    public BooleanSetting settingMoveFix;
    public ModeSetting settingSilentMoveFix;
    public BooleanSetting settingSilentMoveFixIntave;
    public BooleanSetting settingRandomizeDirections;
    public int tickDelay1;
    public int tickDelay;

    public RotationHandler() {
        super("RotationHandler", "DrehungsVerwalter", true, new Color(168, 145, 50), Category.MISC);
        this.settingYawSpeed = new RangeSetting(this, "YawSpeed", 18.0, 24.0, 0.0, 180.0, 0).getRangeSetting(15343, () -> this.isYawSpeedVisible()).getRangeSetting2();
        this.settingPitchSpeed = new RangeSetting(this, "PitchSpeed", 18.0, 24.0, 0.0, 180.0, 0).getRangeSetting(15343, () -> this.isPitchSpeedVisible()).getRangeSetting2();
        this.settingRotationSpeed = new NumberSetting(this, "RotationSpeed", 75.0, 0.0, 100.0, 0).getNumberSetting(() -> this.isRotationSpeedVisible());
        this.settingSmoothBackRotateTickDelayAfterClosingInventory = new RangeSetting(this, "SmoothBackRotateTickDelayAfterClosingInventory", 2.0, 4.0, 0.0, 10.0, 0)
            .getRangeSetting2().getRangeSetting(15343, () -> this.executeAction());
        this.settingMovement = new SettingGroup(this, "Movement", true);
        BooleanSetting booleanSetting = new BooleanSetting(this, "MoveFix", true, this.settingMovement);
        this.settingMoveFix = (BooleanSetting)booleanSetting.getSetting(() -> this.isMoveFixVisible());
        this.settingSilentMoveFix = new ModeSetting(
            this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"}, this.settingMovement
        ).getModeSetting(() -> this.isSilentMoveFixVisible());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "SilentMoveFixIntave", true, this.settingMovement);
        this.settingSilentMoveFixIntave = (BooleanSetting)booleanSetting2.getSetting(() -> this.isSilentMoveFixIntaveVisible());
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "RandomizeDirections", false, this.settingMovement);
        this.settingRandomizeDirections = (BooleanSetting)booleanSetting3.getSetting(() -> this.isRandomizeDirectionsVisible());
        this.tickDelay1 = 0;
        this.tickDelay = 0;
        this.setActive(true);
        EventBusManager.getDietrichEvents2().subscribe(28, this, -2);
        EventBusManager.getDietrichEvents2().subscribe(21, this, 20);
        EventBusManager.getDietrichEvents2().subscribe(26, this, 20);
        EventBusManager.getDietrichEvents2().subscribe(43, this, 20);
        EventBusManager.getDietrichEvents2().subscribe(1, this, 20);
        EventBusManager.getDietrichEvents2().subscribe(17, this, 20);
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

    @Override
    public void onRotation(EventRotation event) {
        if (!this.settingMoveFix.isEnabled()) {
            if (ClientGlobals.rotationManager.checkCondition()) {
                event.setScaleFactor(MoveUtil.getFloat2());
            }
        }
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (!this.settingMoveFix.isEnabled()) {
            if (ClientGlobals.rotationManager.checkCondition()) {
                event.setScaleFactor(MoveUtil.getFloat2());
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        ClientGlobals.moduleManager.killAura.auxiliaryArrayListEntry.onPacket(packet);
        ClientGlobals.moduleManager.autoClicker.auxiliaryArrayListEntry.onPacket(packet);
        ClientGlobals.moduleManager.killAura.onPacket2(packet);
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        ClientGlobals.moduleManager.killAura.auxiliaryArrayListEntry.onPacket2(packet);
        ClientGlobals.moduleManager.autoClicker.auxiliaryArrayListEntry.onPacket2(packet);
    }

    @Override
    public void onMove(EventMove event) {
        if (this.settingMoveFix.isEnabled() && ClientGlobals.rotationManager.checkCondition()) {
            AuraRenderMode auraRenderMode;
            if (this.settingSilentMoveFix.getSelected().equals("None")) {
                auraRenderMode = AuraRenderMode.NONE;
            } else {
                auraRenderMode = this.settingSilentMoveFix.getSelected().equals("Default") ? AuraRenderMode.DEFAULT : AuraRenderMode.ADVANCED;
            }

            event.setAuraRenderMode(auraRenderMode);
            if (!this.settingSilentMoveFix.getSelected().equals("None")) {
                boolean enabled = this.settingSilentMoveFixIntave.isEnabled();
                event.setState1(enabled);
                if (this.settingRandomizeDirections.isEnabled()) {
                    float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
                    float float2 = SettingContainer.settingProvider.getFloat2(20.0F, 30.0F);
                    byte state = (byte)(ThreadLocalRandom.current().nextBoolean() ? 1 : -1);
                    float value = scaleFactor1 + float2 * (float)state;
                    event.setScaleFactor(value);
                }
            }
        }

        if (ClientGlobals.rotationManager.isState()) {
            AuraRenderMode auraRenderMode2 = AuraRenderMode.DEFAULT;
            event.setAuraRenderMode(auraRenderMode2);
            event.setState1(true);
        }
    }

    @Override
    public void onPreUpdateState() {
    }

    private boolean isYawSpeedVisible() {
        return this.settingSmoothBackRotate.getSelected().equals("Default");
    }

    private boolean isRandomizeDirectionsVisible() {
        return !this.settingSilentMoveFix.isVisible() ? false : !this.settingSilentMoveFix.getSelected().equals("None");
    }

    public static void updateState() {
    }

    private boolean isSilentMoveFixIntaveVisible() {
        return !this.settingSilentMoveFix.isVisible() ? false : !this.settingSilentMoveFix.getSelected().equals("None");
    }

    private boolean executeAction() {
        return !this.settingSmoothBackRotate.getSelected().equals("None");
    }

    private boolean isPitchSpeedVisible() {
        return this.settingSmoothBackRotate.getSelected().equals("Default");
    }

    private boolean isMoveFixVisible() {
        return !this.settingSmoothBackRotate.getSelected().equals("None");
    }

    private boolean isRotationSpeedVisible() {
        return this.settingSmoothBackRotate.getSelected().equals("New");
    }

    private boolean isSilentMoveFixVisible() {
        return !this.settingMoveFix.isEnabled() ? false : this.settingMoveFix.isVisible();
    }
}
