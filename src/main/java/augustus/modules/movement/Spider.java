package augustus.modules.movement;

import client.event.EventMotionUpdate;
import client.event.MotionUpdateListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.MinecraftTimer;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;

public class Spider extends Module implements TickListener, MotionUpdateListener {
    public ModeSetting settingMode;
    public SettingGroup settingModeValues;
    public NumberSetting settingCustomMotion;
    public NumberSetting settingTickDelay;
    public NumberSetting settingMotionToJump;
    public BooleanSetting settingCustomMotionAtJump;
    public NumberSetting settingJumpMotion;
    public BooleanSetting settingGroundPacket;
    public NumberSetting settingPositionSpeed;
    public NumberSetting settingTickDelayPosition;
    public NumberSetting settingTimer;

    public Spider() {
        super("Spider", "Spinne", new Color(168, 127, 50), Category.MOVEMENT);
        String[] textArray = new String[4];
        textArray[0] = "Motion";
        textArray[1] = "Jump";
        textArray[2] = "Position";
        textArray[3] = "Verus";
        this.settingMode = new ModeSetting(this, "Mode", "Jump", textArray);
        this.settingModeValues = new SettingGroup(this, "ModeValues", true).getSettingGroup(() -> this.isModeValuesVisible());
        this.settingCustomMotion = new NumberSetting(this, "CustomMotion", 0.3, 0.0, 2.0, 3, this.settingModeValues).getNumberSetting(() -> this.isCustomMotionVisible());
        this.settingTickDelay = new NumberSetting(this, "TickDelay", 0.0, 0.0, 20.0, 0, this.settingModeValues).getNumberSetting(() -> this.isTickDelayVisible());
        this.settingMotionToJump = new NumberSetting(this, "MotionToJump", -0.2, -0.42, 0.42, 2, this.settingModeValues).getNumberSetting(() -> this.isMotionToJumpVisible());
        this.settingCustomMotionAtJump = (BooleanSetting)new BooleanSetting(this, "CustomMotionAtJump", false, this.settingModeValues).getSetting(() -> this.isCustomMotionAtJumpVisible());
        this.settingJumpMotion = new NumberSetting(this, "JumpMotion", 0.42, 0.0, 2.0, 3, this.settingModeValues).getNumberSetting(() -> this.isJumpMotionVisible());
        this.settingGroundPacket = (BooleanSetting)new BooleanSetting(this, "GroundPacket", false, this.settingModeValues).getSetting(() -> this.isGroundPacketVisible());
        this.settingPositionSpeed = new NumberSetting(this, "PositionSpeed", 0.4, 0.2, 2.0, 3, this.settingModeValues).getNumberSetting("Speed").getNumberSetting(() -> this.isPositionSpeedVisible());
        this.settingTickDelayPosition = new NumberSetting(this, "TickDelayPosition", 0.0, 0.0, 20.0, 0, this.settingModeValues).getNumberSetting("TickDelay").getNumberSetting(() -> this.isTickDelayPositionVisible());
        this.settingTimer = new NumberSetting(this, "Timer", 1.0, 0.1, 2.0, 2);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(18);
    }

    @Override
    public void onDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        Spider spider;
        if (this.canExecute5()) {
            MinecraftTimer timer = minecraft.getTimer();
            float value = (float)Math.max(0.1, this.settingTimer.getValue());
            timer.scaleFactor2 = value;
            spider = this;
        } else {
            minecraft.getTimer().scaleFactor2 = 1.0F;
            spider = this;
        }

        if (spider.canExecute5()) {
            String selected = this.settingMode.getSelected();
switch (selected) {
            case "Verus":
                boolean isCollidedHorizontally = minecraft.thePlayer.isCollidedHorizontally;
                if (!isCollidedHorizontally) {
                        return;
                    }
                if (minecraft.thePlayer.ticksExisted % 4 != 0) {
                        minecraft.thePlayer.motionY = 0.5;
                    }
                break;
            case "Position":
                double ticksExisted = (double)minecraft.thePlayer.ticksExisted;
                if (ticksExisted % (this.settingTickDelayPosition.getValue() + 1.0) == 0.0) {
                        NetHandlerPlayClient netHandlerPlayClient = minecraft.thePlayer.netHandlerPlayClient;
                        double posX = minecraft.thePlayer.posX;
                        double offset = minecraft.thePlayer.posY + (double)((float)this.settingPositionSpeed.getValue());
                        netHandlerPlayClient.handlePacket(
                        new C06PacketPlayerPosLook(posX, offset, minecraft.thePlayer.posZ, minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch, false)
                        );
                        double posX2 = minecraft.thePlayer.posX;
                        double offset2 = minecraft.thePlayer.posY + (double)((float)this.settingPositionSpeed.getValue());
                        minecraft.thePlayer.processDouble2(posX2, offset2, minecraft.thePlayer.posZ);
                    }
                return;
            default:
                return;
        }
        }
    }

    @Override
    public void onRunTick() {
        String selected = this.settingMode.getSelected();
        this.applyString4(selected);
        if (this.canExecute5()) {
            String selected2 = this.settingMode.getSelected();
switch (selected2) {
            case "Motion":
                double ticksExisted = (double)minecraft.thePlayer.ticksExisted;
                if (ticksExisted % (this.settingTickDelay.getValue() + 1.0) == 0.0) {
                        minecraft.thePlayer.motionY = (double)((float)this.settingCustomMotion.getValue());
                        return;
                    }
                break;
            case "Jump":
                if (minecraft.thePlayer.onGround) {
                        minecraft.thePlayer.closeScreen();
                        return;
                    }
                if (minecraft.thePlayer.motionY >= this.settingMotionToJump.getValue()) {
                        return;
                    }
                if (this.settingCustomMotionAtJump.isEnabled()) {
                        minecraft.thePlayer.motionY = this.settingJumpMotion.getValue();
                    } else {
                        minecraft.thePlayer.closeScreen();
                    }
                if (this.settingGroundPacket.isEnabled()) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C03PacketPlayer(true));
                    }
        }
        }
    }

    private boolean isCustomMotionAtJumpVisible() {
        return this.settingMode.getSelected().equals("Jump");
    }

    private boolean isTickDelayVisible() {
        return this.settingMode.getSelected().equals("Motion");
    }

    private boolean isCustomMotionVisible() {
        return this.settingMode.getSelected().equals("Motion");
    }

    private boolean isJumpMotionVisible() {
        return this.settingMode.getSelected().equals("Jump");
    }

    private boolean isModeValuesVisible() {
        return this.settingMode.getSelected().equals("Verus");
    }

    private boolean isPositionSpeedVisible() {
        return this.settingMode.getSelected().equals("Position");
    }

    private boolean isGroundPacketVisible() {
        return this.settingMode.getSelected().equals("Jump");
    }

    private boolean isTickDelayPositionVisible() {
        return this.settingMode.getSelected().equals("Position");
    }

    private boolean isMotionToJumpVisible() {
        return this.settingMode.getSelected().equals("Jump");
    }

    private boolean canExecute5() {
        return minecraft.thePlayer.isCollidedHorizontally;
    }
}
