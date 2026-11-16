package augustus.modules.player;

import client.event.EventBlockCollision;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockCollisionListener;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class Teleport extends Module implements UpdateStateListener, PacketListener, NetworkPacketListener, BlockCollisionListener, MoveListener {
    public ModeSetting settingMode;
    public BooleanSetting settingAutoDisable;
    public TimeHelper timer;
    public TimeHelper timer2;
    public double[] coordOffsetArray;
    public boolean activeState2;
    public boolean activeState1;
    public boolean enabled2;
    public boolean expanded2;
    public int keyCode2;
    public boolean activeState;
    public int tickDelay;
    public boolean visible2;

    public Teleport() {
        super("Teleport", "Raumreise", new Color(141, 232, 5), Category.PLAYER);
        String[] textArray = new String[5];
        textArray[0] = "OldKarhu";
        textArray[1] = "OldVulcan";
        textArray[2] = "Vulcan";
        textArray[3] = "VulcanNew";
        textArray[4] = "Vanilla";
        this.settingMode = new ModeSetting(this, "Mode", "Vulcan", textArray);
        this.settingAutoDisable = new BooleanSetting(this, "AutoDisable", true);
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        double[] coordArray = new double[3];
        this.coordOffsetArray = coordArray;
        this.activeState1 = false;
        this.expanded2 = false;
        this.keyCode2 = 0;
        this.activeState = false;
        this.tickDelay = 0;
        this.visible2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(43);
        this.registerEvent(26);
        this.registerEvent(25);
        this.registerEvent(3);
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
        this.coordOffsetArray = new double[3];
        this.activeState2 = false;
        this.enabled2 = false;
    }

    @Override
    public void onPreDisable() {
        this.coordOffsetArray = new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
        this.activeState2 = false;
        this.activeState1 = false;
        this.enabled2 = false;
        this.activeState = false;
        this.tickDelay = 0;
        this.visible2 = false;
        this.expanded2 = false;
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (this.settingMode.getSelected().equals("OldVulcan")) {
            if (packet2 instanceof C03PacketPlayer) {
                if (this.activeState2) {
                    C03PacketPlayer c03PacketPlayer = (C03PacketPlayer)packet2;
                    c03PacketPlayer.setCoordinate1(this.coordOffsetArray[0]);
                    c03PacketPlayer.setCoordinate(this.coordOffsetArray[1]);
                    c03PacketPlayer.setCoordinate3(this.coordOffsetArray[2]);
                    minecraft.thePlayer.processDouble2(this.coordOffsetArray[0], this.coordOffsetArray[1], this.coordOffsetArray[2]);
                    minecraft.thePlayer.onGround = true;
                    if (!this.timer.hasReached(1000L)) {
                        return;
                    }

                    this.activeState2 = false;
                    this.activeState1 = true;
                    double[] coordArray = new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
                    this.coordOffsetArray = coordArray;
                }
            }
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if (this.settingMode.getSelected().equals("Vulcan")) {
            packet.setCancelled(true);
        }
    }

    @Override
    public void onBlockCollision(EventBlockCollision event) {
        String selected = this.settingMode.getSelected();
switch (selected) {
            case "Vulcan":
                boolean reached = this.timer2.hasReached(1500L);
                if (reached) {
                    return;
                } else if (!(event.getBlock() instanceof BlockAir)) {
                    return;
                } else {
                    int y = event.getBlockPos().getY();
                    int keyCode22 = this.keyCode2;
                    if (y >= keyCode22) {
                        return;
                    }

                    BlockPos blockPos = event.getBlockPos();
                    event.setBoundingBox(
                    new AxisAlignedBB(
                    (double)blockPos.getX(),
                    (double)blockPos.getY(),
                    (double)blockPos.getZ(),
                    (double)(blockPos.getX() + 1),
                    (double)(blockPos.getY() + 1),
                    (double)(blockPos.getZ() + 1)
                    )
                    );
                    return;
                }
            case "VulcanNew":
                if ((!this.activeState || this.visible2 || this.tickDelay >= 1) && !this.expanded2) {
                    return;
                } else if (!(event.getBlock() instanceof BlockAir)) {
                    return;
                } else {
                    int y2 = event.getBlockPos().getY();
                    int keyCode23 = this.keyCode2;
                    if (y2 >= keyCode23) {
                        return;
                    } else {
                        BlockPos blockPos2 = event.getBlockPos();
                        event.setBoundingBox(
                        new AxisAlignedBB(
                        (double)blockPos2.getX(),
                        (double)blockPos2.getY(),
                        (double)blockPos2.getZ(),
                        (double)(blockPos2.getX() + 1),
                        (double)(blockPos2.getY() + 1),
                        (double)(blockPos2.getZ() + 1)
                        )
                        );
                        return;
                    }
                }
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        String selected = this.settingMode.getSelected();
switch (selected) {
            case "OldVulcan":
                if (!(packet2 instanceof S08PacketPlayerPosLook)) {
                                return;
                            } else {
                                if (this.activeState2) {
                                    S08PacketPlayerPosLook s08PacketPlayerPosLook2 = (S08PacketPlayerPosLook)packet2;
                                    if (s08PacketPlayerPosLook2.getCoordinate() == this.coordOffsetArray[0]
                                    && s08PacketPlayerPosLook2.getCoordinate1() == this.coordOffsetArray[1]
                                    && s08PacketPlayerPosLook2.getCoordinate4() == this.coordOffsetArray[2]) {
                                        this.activeState2 = false;
                                        this.activeState1 = true;
                                        this.coordOffsetArray = new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
                                    }

                                    return;
                                }

                                return;
                            }
            case "VulcanNew":
                if (!this.activeState) {
                                return;
                            } else {
                                if (!(packet2 instanceof S08PacketPlayerPosLook)) {
                                    return;
                                }

                                packet.cancel();
                                if (this.tickDelay > 1 && !this.visible2) {
                                    minecraft.thePlayer.processDouble2(this.coordOffsetArray[0], this.coordOffsetArray[1] + 0.3, this.coordOffsetArray[2]);
                                    this.visible2 = true;
                                    this.keyCode2 = (int)minecraft.thePlayer.posY;
                                    this.tickDelay = 0;
                                    this.timer2.reset();
                                }

                                this.tickDelay++;
                                return;
                            }
            default:
                return;
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.settingMode.getSelected().equals("Vulcan") && !this.timer2.hasReached(1500L)) {
            event.setScaleFactor7(1.0F);
        } else if (this.enabled2 && !this.expanded2) {
            this.enabled2 = false;
            this.activeState1 = true;
            this.coordOffsetArray = new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
        }

        if (this.settingMode.getSelected().equals("VulcanNew")) {
            if (this.activeState) {
                if (!this.visible2 || this.expanded2) {
                    event.setScaleFactor7(1.0F);
                }

                if (this.activeState && !this.visible2 && this.tickDelay >= 1) {
                    event.setScaleFactor7(0.0F);
                    event.setScaleFactor2(0.0F);
                }

                if (this.tickDelay > 40) {
                    if (this.visible2) {
                        if (this.expanded2) {
                            this.activeState1 = true;
                            this.activeState = false;
                            this.visible2 = false;
                            this.tickDelay = 0;
                            this.expanded2 = false;
                            double[] coordArray = new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
                            this.coordOffsetArray = coordArray;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPostUpdateState() {
        this.applyString4(this.settingMode.getSelected());
        if (minecraft.gameSettings.keyBindAttack.isKeyDown()
        && minecraft.objectMouseOver != null
        && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK
        && this.coordOffsetArray[0] == Double.POSITIVE_INFINITY) {
            BlockPos blockPos = minecraft.objectMouseOver.getBlockPos();
            Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
            this.coordOffsetArray = new double[]{
                (double)minecraft.objectMouseOver.getBlockPos().getX() + 0.5,
                (double)minecraft.objectMouseOver.getBlockPos().getY() + block.getCoordOffset6(),
                (double)minecraft.objectMouseOver.getBlockPos().getZ() + 0.5
            };
            this.activeState2 = true;
            this.timer.reset();
        }

        if (this.settingMode.getSelected().equals("Vulcan") && minecraft.thePlayer.onGround && this.expanded2) {
            minecraft.thePlayer.processDouble2(minecraft.thePlayer.posX, minecraft.thePlayer.posY + 2.0, minecraft.thePlayer.posZ);
            this.expanded2 = false;
            this.timer2.reset();
            this.keyCode2 = (int)minecraft.thePlayer.posY;
        }

        if (this.settingMode.getSelected().equals("VulcanNew")
        && minecraft.thePlayer.onGround
        && this.visible2
        && !this.expanded2
        && this.timer2.hasReached(500L)) {
            this.expanded2 = true;
            minecraft.thePlayer.closeScreen();
            this.keyCode2 = (int)minecraft.thePlayer.posY + 1;
            this.tickDelay = 0;
        }

        if (this.activeState2) {
            String selected = this.settingMode.getSelected();
switch (selected) {
            case "OldKarhu":
                if (!minecraft.thePlayer.onGround) {
                        double offset = this.coordOffsetArray[0];
                        minecraft.thePlayer
                        .netHandlerPlayClient
                        .handlePacket(new C04PacketPlayerPosition(offset, this.coordOffsetArray[1] + 0.3, this.coordOffsetArray[2], true));
                        double offset2 = this.coordOffsetArray[0];
                        minecraft.thePlayer
                        .netHandlerPlayClient
                        .handlePacket(new C04PacketPlayerPosition(offset2, this.coordOffsetArray[1] + 0.3, this.coordOffsetArray[2], false));
                        this.activeState2 = false;
                        this.activeState1 = true;
                        this.coordOffsetArray = new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
                    }
                if (minecraft.thePlayer.onGround) {
                        minecraft.thePlayer.closeScreen();
                    }
                break;
            case "Vanilla":
                minecraft.thePlayer.processDouble2(this.coordOffsetArray[0], this.coordOffsetArray[1] + 0.3, this.coordOffsetArray[2]);
                double offset3 = this.coordOffsetArray[0];
                minecraft.thePlayer
                    .netHandlerPlayClient
                    .handlePacket(new C04PacketPlayerPosition(offset3, this.coordOffsetArray[1] + 0.3, this.coordOffsetArray[2], false));
                this.activeState2 = false;
                this.activeState1 = true;
                break;
            case "Vulcan":
                this.enabled2 = true;
                minecraft.thePlayer.processDouble2(this.coordOffsetArray[0], this.coordOffsetArray[1] + 1.0, this.coordOffsetArray[2]);
                this.activeState2 = false;
                this.expanded2 = true;
                break;
            case "VulcanNew":
                if (minecraft.thePlayer.onGround) {
                        minecraft.thePlayer.closeScreen();
                        this.activeState2 = false;
                        this.activeState = true;
                        this.keyCode2 = (int)minecraft.thePlayer.posY + 1;
                    }
        }
        }

        if (this.settingAutoDisable.isEnabled()) {
            if (this.activeState1) {
                this.onReset();
            }
        }
    }
}
