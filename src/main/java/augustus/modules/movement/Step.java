package augustus.modules.movement;

import client.Client;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventTickState;
import client.event.EventTickStateCallback;
import client.event.MotionUpdateListener;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.MinecraftTimer;
import client.util.MoveUtil;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.util.Vec3;

public class Step extends Module implements TickListener, MoveEntityListener, UpdateStateListener, MotionUpdateListener, EventTickStateCallback, MoveListener {
    public ModeSetting settingModes;
    public NumberSetting settingHeight;
    public NumberSetting settingDelay;
    public NumberSetting settingTimer;
    public BooleanSetting settingSlowDown;
    public TimeHelper timer;
    public TimeHelper timer2;
    public boolean expanded2;
    public boolean enabled2;
    public double reachDistance;
    public double reachDistance1;
    public int tickDelay;
    public int tickDelay2;
    public boolean activeState;

    public Step() {
        super("Step", "Schritt", new Color(129, 147, 164), Category.MOVEMENT);
        String[] textArray = new String[8];
        textArray[0] = "Vanilla";
        textArray[1] = "NCPPacket";
        textArray[2] = "Verus";
        textArray[3] = "Vulcan";
        textArray[4] = "Intave";
        textArray[5] = "IntaveTest";
        textArray[6] = "Legit";
        textArray[7] = "Hypixel";
        this.settingModes = new ModeSetting(this, "Modes", "Vanilla", textArray);
        this.settingHeight = new NumberSetting(this, "Height", 1.0, 1.0, 5.0, 2).getNumberSetting(() -> this.isHeightVisible());
        this.settingDelay = new NumberSetting(this, "Delay", 400.0, 0.0, 1000.0, 0).getNumberSetting(() -> this.isDelayVisible());
        this.settingTimer = new NumberSetting(this, "Timer", 0.3, 0.05, 1.0, 2).getNumberSetting(() -> this.isTimerVisible2());
        this.settingSlowDown = (BooleanSetting)new BooleanSetting(this, "SlowDown", true).getSetting(() -> this.isTimerVisible());
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.reachDistance = 0.0;
        this.reachDistance1 = 0.0;
        this.tickDelay = 0;
        this.tickDelay2 = 0;
        this.activeState = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(20);
        this.registerEvent(43);
        this.registerEvent(18);
        this.registerEvent(37);
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
    }

    @Override
    public void onPreDisable() {
        this.activeState = false;
    }

    @Override
    public void updateState2() {
        Vec3 startPos = new Vec3(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ);
        switch (this.settingModes.getSelected()) {
            case "Vanilla":
                if (minecraft.thePlayer.getBoundingBox2().minY - startPos.yCoord > 0.6 && this.expanded2 && this.settingTimer.getValue() != 1.0) {
                    minecraft.getTimer().scaleFactor2 = (float)this.settingTimer.getValue();
                    this.enabled2 = true;
                }
                break;
            case "NCPPacket":
                if (minecraft.thePlayer.getBoundingBox2().minY - startPos.yCoord > 0.6 && this.expanded2) {
                    double stepDiff = minecraft.thePlayer.getBoundingBox2().minY - startPos.yCoord;
                    int packetCount = 2;
                    if (stepDiff == 1.0) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.42, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.75, startPos.zCoord, false));
                        packetCount = 3;
                    } else if (stepDiff < 0.65) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.35, startPos.zCoord, false));
                    } else if (stepDiff < 0.76) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.42, startPos.zCoord, false));
                    } else if (stepDiff < 0.878) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.42, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.73, startPos.zCoord, false));
                        packetCount = 3;
                    } else if (stepDiff < 1.26) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.425, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.779, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 1.06, startPos.zCoord, false));
                        packetCount = 4;
                    } else if (stepDiff < 1.6) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.425, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.779, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 1.0538, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 1.265, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 1.3, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 1.25, startPos.zCoord, false));
                        packetCount = 7;
                    } else if (stepDiff < 2.1) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.425, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.82, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.7, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 0.595, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 1.01, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 1.37, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 1.657, startPos.zCoord, false));
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(startPos.xCoord, startPos.yCoord + 1.87, startPos.zCoord, false));
                        packetCount = 10;
                    }

                    if (this.settingTimer.getValue() != 1.0) {
                        minecraft.getTimer().scaleFactor2 = (float)Math.max(
                            stepDiff == 1.0 ? this.settingTimer.getValue() : this.settingTimer.getValue() * 3.43333 / (double)packetCount, 0.1
                        );
                        this.enabled2 = true;
                    }
                }
                break;
            case "Verus":
                if ((double)minecraft.thePlayer.stepHeight <= 0.6 || minecraft.thePlayer.getBoundingBox2().minY - startPos.yCoord <= 0.6) {
                    return;
                }

                minecraft.getTimer().scaleFactor2 = 0.5F;
                minecraft.thePlayer.netHandlerPlayClient.handlePacket2(
                    new C04PacketPlayerPosition(minecraft.thePlayer.posX, minecraft.thePlayer.posY + 0.5, minecraft.thePlayer.posZ, true)
                );
                this.enabled2 = true;
                break;
            case "Vulcan":
                if ((double)minecraft.thePlayer.stepHeight <= 0.6 || minecraft.thePlayer.getBoundingBox2().minY - startPos.yCoord <= 0.6) {
                    return;
                }

                minecraft.getTimer().scaleFactor2 = 0.5F;
                minecraft.thePlayer.netHandlerPlayClient.handlePacket2(
                    new C04PacketPlayerPosition(minecraft.thePlayer.posX, minecraft.thePlayer.posY + 0.5, minecraft.thePlayer.posZ, true)
                );
                this.enabled2 = true;
                this.tickDelay = 0;
                break;
        }
    }

    @Override
    public void onMoveEntity(EventMoveEntity event) {
        int index = minecraft.thePlayer.onGround ? this.tickDelay2 + 1 : 0;
        this.tickDelay2 = index;
        if (this.settingModes.getSelected().equals("Legit")) {
            AutoWalk autoWalk = Client.getClient().getModuleManager().autoWalk;
            float rotationYaw = minecraft.thePlayer.rotationYaw;
            if (autoWalk.isToggled()
                && autoWalk.settingPathFinding.isEnabled()
                && autoWalk.settingMovementmode.getSelected().equalsIgnoreCase("silent")
                && System.currentTimeMillis() - autoWalk.timestampMillis2 < 100L) {
                minecraft.thePlayer.rotationYaw = autoWalk.motionMultiplier;
            }

            float value = (float)(minecraft.thePlayer.posX - this.reachDistance) * 1.6F;
            minecraft.thePlayer.processFloat7(value, (float)(minecraft.thePlayer.posZ - this.reachDistance1) * 1.6F);
            minecraft.thePlayer.rotationYaw = rotationYaw;
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        int index = this.tickDelay + 1;
        this.tickDelay = index;
        int index2 = this.settingModes.getSelected().hashCode();
        if (index2 == 0) {
            this.timer.reset();
            this.expanded2 = true;
            double value = this.settingHeight.getValue();
            if ((this.checkCondition(value) ? 1 : 0) > 0) {
                MoveUtil.processAction(0.11, false);
                minecraft.thePlayer.stepHeight = 0.6F;
                minecraft.thePlayer.stepHeight = 0.6F;
            }
        }
    }

    @Override
    public void onTickState(EventTickState event) {
        if (this.enabled2) {
            minecraft.getTimer().scaleFactor2 = 1.0F;
            this.timer2.reset();
            this.enabled2 = false;
        }

        String selected = this.settingModes.getSelected();
switch (selected) {
            case "Vanilla":
            case "NCPPacket":
                float height = (float)this.settingHeight.getValue();
                event.processAction(height);
                TimeHelper timer4 = this.timer;
                long time3 = (long)this.settingDelay.getValue();
                if (!timer4.hasReached(time3)) {
                                event.processAction(0.6F);
                                this.expanded2 = false;
                                return;
                            }
                this.expanded2 = true;
                this.timer.reset();
                return;
            default:
                return;
        }
    }

    @Override
    public void onMove(EventMove event) {
        if ((this.settingModes.getSelected().equals("Vulcan") ? 1 : 0) < 5) {
            event.setState(false);
        }
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        this.reachDistance = minecraft.thePlayer.posX;
        this.reachDistance1 = minecraft.thePlayer.posZ;
    }

    @Override
    public void onPostUpdateState() {
        this.applyString4(this.settingModes.getSelected());
        if (this.settingModes.getSelected().equals("Hypixel")) {
            boolean enabled = false;
            boolean completed2 = false;
            boolean completed = false;
            boolean enabled3 = false;
            if (minecraft.thePlayer.onGround && this.checkCondition(1.0)) {
                if (MoveUtil.canExecute()) {
                    minecraft.thePlayer.closeScreen();
                    this.activeState = true;
                    return;
                }
                if (minecraft.thePlayer.indexOffset41 == 3 && this.activeState) {
                    minecraft.thePlayer.motionY = 0.0;
                    return;
                }
                if (minecraft.thePlayer.indexOffset41 != 4) {
                    completed = true;
                }
                if (!completed) {
                    enabled3 = this.activeState;
                }
            } else if (minecraft.thePlayer.indexOffset41 == 3) {
                if (this.activeState) {
                    minecraft.thePlayer.motionY = 0.0;
                    return;
                }
                if (minecraft.thePlayer.indexOffset41 != 4) {
                    enabled = minecraft.thePlayer.onGround;
                    completed2 = true;
                }
                if (!completed2) {
                    enabled3 = this.activeState;
                }
            } else {
                if (minecraft.thePlayer.indexOffset41 != 4) {
                    enabled = minecraft.thePlayer.onGround;
                    completed2 = true;
                }
                if (!completed2) {
                    enabled3 = this.activeState;
                }
            }
            if (!completed2) {
                if (!completed) {
                    if (enabled3) {
                        minecraft.thePlayer.motionY = 0.0;
                        MoveUtil.processFloat2(0.4F, false);
                        if (this.settingSlowDown.isEnabled()) {
                            return;
                        }

                        this.activeState = false;
                        return;
                    }
                }
                boolean onGround = minecraft.thePlayer.onGround;
                if (!onGround) {
                    return;
                }
                if (!this.activeState) {
                    return;
                }
                if (!this.settingSlowDown.isEnabled()) {
                    return;
                }
                MoveUtil.processFloat2(0.2F, false);
                this.activeState = false;
                return;
            }


            if (enabled) {
                if (this.activeState) {
                    if (this.settingSlowDown.isEnabled()) {
                        MoveUtil.processFloat2(0.2F, false);
                        this.activeState = false;
                    }
                }
            }
        }
    }

    @Override
    public void onTick() {
    }

    private boolean isDelayVisible() {
        return this.settingModes.getSelected().equals("NCPPacket");
    }

    private boolean isTimerVisible() {
        return this.settingModes.getSelected().equals("Hypixel");
    }

    private boolean isHeightVisible() {
        return this.settingModes.getSelected().equals("Verus");
    }

    private boolean checkCondition(double offset) {
        return minecraft.thePlayer.isCollidedHorizontally;
    }

    private boolean isTimerVisible2() {
        return this.settingModes.getSelected().equals("NCPPacket");
    }
}
