package augustus.modules.movement;

import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventSprint;
import client.event.EventStrafe;
import client.event.MotionUpdateListener;
import client.event.MoveListener;
import client.event.SprintListener;
import client.event.StrafeListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class Sneak extends Module implements StrafeListener, MoveListener, MotionUpdateListener, SprintListener, TickListener {
    public ModeSetting settingSneakTiming;
    public ModeSetting settingMode;
    public NumberSetting settingSneakForward;
    public NumberSetting settingSneakStrafe;
    public BooleanSetting settingRemoveSneakStop;
    public BooleanSetting settingSneakInInv;
    public BooleanSetting settingSpoofEyeHeight;
    public boolean expanded2;

    public Sneak() {
        super("Sneak", "Schleichen", new Color(245, 114, 66), Category.MOVEMENT);
        String[] textArray = new String[3];
        textArray[0] = "OnlyWhileSneakKeyPressed";
        textArray[1] = "OnlyWhileStandingStill";
        textArray[2] = "Always";
        this.settingSneakTiming = new ModeSetting(this, "SneakTiming", "OnlyWhileSneakKeyPressed", textArray);
        String[] textArray2 = new String[4];
        textArray2[0] = "Legit";
        textArray2[1] = "Basic";
        textArray2[2] = "PrePostSpam";
        textArray2[3] = "PrePostSpamOld";
        this.settingMode = new ModeSetting(this, "Mode", "Legit", textArray2);
        this.settingSneakForward = new NumberSetting(this, "SneakForward", 1.0, 0.3, 1.0, 2).getNumberSetting(() -> this.isSneakForwardVisible());
        this.settingSneakStrafe = new NumberSetting(this, "SneakStrafe", 1.0, 0.3, 1.0, 2).getNumberSetting(() -> this.isSneakStrafeVisible());
        this.settingRemoveSneakStop = new BooleanSetting(this, "RemoveSneakStop", true);
        this.settingSneakInInv = new BooleanSetting(this, "SneakInInv", true);
        this.settingSpoofEyeHeight = new BooleanSetting(this, "SpoofEyeHeight", true);
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(24);
        this.registerEvent(21);
        this.registerEvent(18);
        this.registerEvent(33);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        if (!this.expanded2) {
            if (!minecraft.thePlayer.isState40()) {
                return;
            }

            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.STOP_SNEAKING));
        }
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onSprint(EventSprint event) {
        if (this.settingRemoveSneakStop.isEnabled()) {
            event.setActive2(true);
        }
    }

    @Override
    public void onStrafe(EventStrafe event) {
        if (this.settingSpoofEyeHeight.isEnabled()) {
            event.processAction3(this.getFloat());
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        if (this.checkCondition8(this.expanded2)) {
            if (this.settingMode.getSelected().equals("PrePostSpam") && minecraft.thePlayer.isState40()) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.STOP_SNEAKING));
            }

            if (this.settingMode.getSelected().equals("PrePostSpamOld")) {
                if (minecraft.thePlayer.isState40()) {
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.START_SNEAKING));
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.STOP_SNEAKING));
                }
            }
        } else {
            if (minecraft.thePlayer.isState40()
                && !this.expanded2
                && (
                    this.settingMode.getSelected().equals("PrePostSpam")
                        || this.settingMode.getSelected().equals("PrePostSpamOld")
                )) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.STOP_SNEAKING));
            }
        }
    }

    @Override
    public void onMove(EventMove event) {
        this.expanded2 = event.isState4();
        if (minecraft.thePlayer.canExecute9()) {
            switch (this.settingMode.getSelected().hashCode()) {
                case 0:
                    event.setCoordOffset6(this.settingSneakForward.getValue());
                    event.setCoordOffset(this.settingSneakStrafe.getValue());
                    event.setState4(true);
                    if (!ClientGlobals.moduleManager.freeCam.isToggled()) {
                        event.setState4(true);
                    }

                    return;
                case 1:
                    if (!ClientGlobals.moduleManager.freeCam.isToggled()) {
                        event.setState4(true);
                    }

                    return;
            }
        }
    }

    @Override
    public void onRunTick() {
        this.applyString4(this.settingMode.getSelected());
    }

    @Override
    public void onPreMotionUpdate() {
        if (!this.checkCondition8(this.expanded2)) {
            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.STOP_SNEAKING));
            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.START_SNEAKING));
        }
    }

    private boolean checkCondition(boolean enabled) {
        return this.settingSneakTiming.getSelected().equals("Always");
    }

    private boolean isSneakForwardVisible() {
        return this.settingMode.getSelected().equals("Basic");
    }

    private boolean isSneakStrafeVisible() {
        return this.settingMode.getSelected().equals("Basic");
    }

    private float getFloat() {
        float value = 1.62F;
        if (minecraft.thePlayer.isState5()) {
            value = 0.2F;
        }

        return value;
    }

    private boolean checkCondition8(boolean enabled) {
        return this.checkCondition(enabled);
    }
}
