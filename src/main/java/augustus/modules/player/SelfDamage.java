package augustus.modules.player;

import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;

public class SelfDamage extends Module implements UpdateStateListener, PacketListener, NetworkPacketListener {
    public ModeSetting settingModes;
    public NumberSetting settingSetPositionYAdd;
    public NumberSetting settingMotion;
    public NumberSetting settingLoops;
    public NumberSetting settingYAddInLoopFirst;
    public BooleanSetting settingSecondPacket;
    public NumberSetting settingYAddInLoopSecond;
    public BooleanSetting settingFinishPacket;
    public NumberSetting settingYAddFinish;
    public BooleanSetting settingOnlyOnGround;
    public BooleanSetting settingRepeat;
    public NumberSetting settingRepeatTicks;
    public int tickDelay;
    public boolean expanded2;
    public int keyCode2;

    public SelfDamage() {
        super("SelfDamage", "SelbstSchaden", new Color(235, 64, 52), Category.PLAYER);
        String[] textArray = new String[7];
        textArray[0] = "Motion";
        textArray[1] = "Packet1";
        textArray[2] = "Packet2";
        textArray[3] = "Custom";
        textArray[4] = "Fake";
        textArray[5] = "SetPosition";
        textArray[6] = "3Jump";
        this.settingModes = new ModeSetting(this, "Modes", "Motion", textArray);
        this.settingSetPositionYAdd = new NumberSetting(this, "SetPositionYAdd", 3.1, -4.0, 4.0, 4).getNumberSetting("YAdd").getNumberSetting(() -> this.isSetPositionYAddVisible());
        this.settingMotion = new NumberSetting(this, "Motion", 5.0, 0.0, 10.0, 2).getNumberSetting(() -> this.isMotionVisible());
        this.settingLoops = new NumberSetting(this, "Loops", 51.0, 1.0, 100.0, 0).getNumberSetting(() -> this.isLoopsVisible());
        this.settingYAddInLoopFirst = new NumberSetting(this, "YAddInLoopFirst", 0.02, 0.0, 1.0, 4).getNumberSetting(() -> this.isYAddInLoopFirstVisible());
        this.settingSecondPacket = (BooleanSetting)new BooleanSetting(this, "SecondPacket", true).getSetting(() -> this.isYAddInLoopFirstVisible2());
        this.settingYAddInLoopSecond = new NumberSetting(this, "YAddInLoopSecond", 0.02, 0.0, 1.0, 4).getNumberSetting(() -> this.isYAddInLoopSecondVisible2());
        this.settingFinishPacket = (BooleanSetting)new BooleanSetting(this, "FinishPacket", true).getSetting(() -> this.isYAddInLoopSecondVisible());
        this.settingYAddFinish = new NumberSetting(this, "YAddFinish", 0.0, 0.0, 1.0, 4).getNumberSetting(() -> this.isYAddFinishVisible());
        this.settingOnlyOnGround = (BooleanSetting)new BooleanSetting(this, "OnlyOnGround", false).getSetting(() -> this.isYAddFinishVisible3());
        this.settingRepeat = (BooleanSetting)new BooleanSetting(this, "Repeat", false).getSetting(() -> this.isYAddFinishVisible2());
        this.settingRepeatTicks = new NumberSetting(this, "RepeatTicks", 140.0, 0.0, 200.0, 0).getNumberSetting(() -> this.isRepeatTicksVisible());
        this.tickDelay = 0;
        this.expanded2 = false;
        this.keyCode2 = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(43);
        this.registerEvent(26);
        this.registerEvent(25);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = false;
        this.tickDelay = 0;
        this.keyCode2 = 0;
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        if ((this.settingModes.getSelected().equals("3Jump") ? 1 : 0) < 4) {
            ((C03PacketPlayer)packet.getPacket()).setOnGround(false);
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if ((this.settingModes.getSelected().equals("3Jump") ? 1 : 0) < 4) {
            packet.setCancelled(true);
        }
    }

    @Override
    public void onPreUpdateState() {
        String selected = this.settingModes.getSelected();
        this.applyString4(selected);
        if (this.expanded2) {
            if (this.settingModes.getSelected().equals("3Jump")) {
                if (minecraft.thePlayer == null) {
                    return;
                }

                if (this.settingOnlyOnGround.isEnabled() && !minecraft.thePlayer.onGround && !this.settingModes.getSelected().equals("3Jump")) {
                    return;
                }

                this.updateState();
                this.expanded2 = true;
            }

            if (this.expanded2) {
                if (!this.settingModes.getSelected().equals("3Jump")) {
                    if (this.settingRepeat.isEnabled()) {
                        double tickDelay2 = (double)this.tickDelay;
                        if (tickDelay2 > this.settingRepeatTicks.getValue()) {
                            this.tickDelay = 0;
                            this.updateState();
                        }
                    }

                    int index = this.tickDelay + 1;
                    this.tickDelay = index;
                }
            }
        } else if (minecraft.thePlayer != null) {
            boolean enabled;
            if (this.settingOnlyOnGround.isEnabled() && !minecraft.thePlayer.onGround) {
                if (!this.settingModes.getSelected().equals("3Jump")) {
                    return;
                }

                this.updateState();
                this.expanded2 = true;
                enabled = this.expanded2;
            } else {
                this.updateState();
                this.expanded2 = true;
                enabled = this.expanded2;
            }

            if (enabled) {
                if (!this.settingModes.getSelected().equals("3Jump")) {
                    if (this.settingRepeat.isEnabled()) {
                        double tickDelay3 = (double)this.tickDelay;
                        if (tickDelay3 > this.settingRepeatTicks.getValue()) {
                            this.tickDelay = 0;
                            this.updateState();
                        }
                    }

                    int index2 = this.tickDelay + 1;
                    this.tickDelay = index2;
                }
            }
        }
    }

    private void updateState() {
        String selected = this.settingModes.getSelected();
switch (selected) {
            case "Motion":
                minecraft.thePlayer.motionY = (double)((float)this.settingMotion.getValue());
                return;
            case "Packet1":
                CombatUtil.updateState3();
                return;
            case "Packet2":
                CombatUtil.updateState();
                return;
            case "Fake":
                minecraft.thePlayer.processByte((byte)2);
                return;
            case "Custom":
                int index = 0;
                while (true) {
                    double offset = (double)index;
                    if (!(offset < this.settingLoops.getValue())) {
                        boolean enabled = this.settingFinishPacket.isEnabled();
                        if (!enabled) {
                            return;
                        }

                        NetHandlerPlayClient netHandlerPlayClient = minecraft.thePlayer.netHandlerPlayClient;
                        double posX = minecraft.thePlayer.posX;
                        double offset2 = minecraft.thePlayer.posY + this.settingYAddFinish.getValue();
                        netHandlerPlayClient.handlePacket2(new C04PacketPlayerPosition(posX, offset2, minecraft.thePlayer.posZ, true));
                        return;
                    }

                    NetHandlerPlayClient netHandlerPlayClient2 = minecraft.thePlayer.netHandlerPlayClient;
                    double posX2 = minecraft.thePlayer.posX;
                    double offset3 = minecraft.thePlayer.posY + this.settingYAddInLoopFirst.getValue();
                    netHandlerPlayClient2.handlePacket2(new C04PacketPlayerPosition(posX2, offset3, minecraft.thePlayer.posZ, false));
                    if (this.settingSecondPacket.isEnabled()) {
                        NetHandlerPlayClient netHandlerPlayClient3 = minecraft.thePlayer.netHandlerPlayClient;
                        double posX3 = minecraft.thePlayer.posX;
                        double offset4 = minecraft.thePlayer.posY + this.settingYAddInLoopSecond.getValue();
                        netHandlerPlayClient3.handlePacket2(new C04PacketPlayerPosition(posX3, offset4, minecraft.thePlayer.posZ, false));
                    }

                    index++;
                }
            case "SetPosition":
                double posX4 = minecraft.thePlayer.posX;
                double offset5 = minecraft.thePlayer.posY + this.settingSetPositionYAdd.getValue();
                minecraft.thePlayer.processDouble2(posX4, offset5, minecraft.thePlayer.posZ);
                return;
            case "3Jump":
                boolean onGround = minecraft.thePlayer.onGround;
                if (!onGround) {
                    return;
                }
                if (this.keyCode2 < 3) {
                    minecraft.thePlayer.closeScreen();
                    CombatUtil.updateState2();
                }
                int index2 = this.keyCode2 + 1;
                this.keyCode2 = index2;
                return;
        }
    }

    private boolean isLoopsVisible() {
        return this.settingModes.getSelected().equals("Custom");
    }

    private boolean isSetPositionYAddVisible() {
        return this.settingModes.getSelected().equals("SetPosition");
    }

    private boolean isYAddInLoopFirstVisible() {
        return this.settingModes.getSelected().equals("Custom");
    }

    private boolean isYAddInLoopSecondVisible() {
        return this.settingModes.getSelected().equals("Custom");
    }

    private boolean isYAddFinishVisible() {
        return this.settingModes.getSelected().equals("Custom");
    }

    private boolean isMotionVisible() {
        return this.settingModes.getSelected().equals("Motion");
    }

    private boolean isYAddFinishVisible2() {
        return this.settingModes.getSelected().equals("3Jump");
    }

    private boolean isRepeatTicksVisible() {
        return this.settingRepeat.isEnabled();
    }

    private boolean isYAddFinishVisible3() {
        return this.settingModes.getSelected().equals("3Jump");
    }

    private boolean isYAddInLoopFirstVisible2() {
        return this.settingModes.getSelected().equals("Custom");
    }

    private boolean isYAddInLoopSecondVisible2() {
        return this.settingModes.getSelected().equals("Custom");
    }
}
