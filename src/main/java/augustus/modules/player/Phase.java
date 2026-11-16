package augustus.modules.player;

import client.event.EventConnection;
import client.event.EventConnectionCallback;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPhaseListener;
import client.event.EventPreAction;
import client.event.EventPreListener;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.PhaseMode;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.WorldSettingsGameType;
import client.util.BooleanSupplier;
import client.util.MoveUtil;
import client.util.MovingObjectType;
import client.util.PlayerDiggingActionType;
import java.awt.Color;
import java.util.Iterator;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;

public class Phase extends Module implements EventPhaseListener, MoveListener, MoveEntityListener, TickListener, EventPreListener, EventConnectionCallback {
    public SettingGroup settingHorizontal;
    public BooleanSetting settingCustomizeHorizontalMovespeedInBlocks;
    public BooleanSetting settingUseSneakMotionInBlocks;
    public NumberSetting settingMovespeedInBlocks;
    public SettingGroup settingVertical;
    public BooleanSetting settingFallIfNoBlockIsUnderPlayer;
    public BooleanSetting settingUpAndDown;
    public NumberSetting settingUpAndDownSteps;
    public NumberSetting settingUpAndDownTickDelay;
    public BooleanSetting settingClipDownByBreaking;
    public NumberSetting settingPacketCount;
    public int tickDelay;
    public float motionMultiplier;
    public boolean enabled2;
    public boolean expanded2;

    public Phase() {
        super("Phase", "DurchWändeGehen", new Color(45, 196, 148), Category.PLAYER);
        this.settingHorizontal = new SettingGroup(this, "Horizontal");
        this.settingCustomizeHorizontalMovespeedInBlocks = new BooleanSetting(
        this, "Customize horizontal Movespeed in blocks", false, this.settingHorizontal
        );
        this.settingUseSneakMotionInBlocks = (BooleanSetting)new BooleanSetting(this, "Use sneak motion in blocks", false, this.settingHorizontal).getSetting(() -> this.settingCustomizeHorizontalMovespeedInBlocks.isEnabled());
        this.settingMovespeedInBlocks = new NumberSetting(this, "Movespeed in blocks", 0.1, 1.0E-4, 1.0, 4, this.settingHorizontal).getNumberSetting(() -> this.isMovespeedInBlocksVisible());
        this.settingVertical = new SettingGroup(this, "Vertical");
        this.settingFallIfNoBlockIsUnderPlayer = new BooleanSetting(this, "Fall if no block is under player", false, this.settingVertical);
        this.settingUpAndDown = new BooleanSetting(this, "UpAndDown", true, this.settingVertical);
        this.settingUpAndDownSteps = new NumberSetting(this, "UpAndDownSteps", 1.0, 0.0, 2.0, 2, this.settingVertical).getNumberSetting(() -> this.settingUpAndDown.isEnabled());
        this.settingUpAndDownTickDelay = new NumberSetting(this, "UpAndDownTickDelay", 5.0, 0.0, 20.0, 0, this.settingVertical).getNumberSetting(() -> this.settingUpAndDown.isEnabled());
        this.settingClipDownByBreaking = new BooleanSetting(this, "Clip down by breaking", false, this.settingVertical);
        this.settingPacketCount = new NumberSetting(this, "Packet count", 1.0, 1.0, 10.0, 0, this.settingVertical).getNumberSetting(() -> this.settingClipDownByBreaking.isEnabled());
        this.tickDelay = 0;
        this.motionMultiplier = 0.0F;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(27);
        this.registerEvent(21);
        this.registerEvent(20);
        this.registerEvent(40);
        this.registerEvent(59);
        this.registerEvent(60);
    }

    @Override
    public void onDisable() {
        minecraft.thePlayer.invulnerableManager.state1 = false;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onConnection(EventConnection event) {
        if (event.getPhaseMode() == PhaseMode.PRE) {
            if (minecraft.theWorld.getBlockState(minecraft.objectMouseOver.getBlockPos()).getBlock() != Blocks.ironDoor) {
                return;
            }

            event.setCancelled(true);
        }
    }

    @Override
    public void onMove(EventMove event) {
        this.tickDelay++;
        if (this.enabled2
        && this.settingUpAndDown.isEnabled()
        && (double)this.tickDelay >= this.settingUpAndDownTickDelay.getValue()) {
            if (event.isState()) {
                minecraft.thePlayer.motionY = this.settingUpAndDownSteps.getValue();
            } else if (event.isState4()) {
                minecraft.thePlayer.motionY = -this.settingUpAndDownSteps.getValue();
            }

            if (event.isState4() || event.isState()) {
                this.tickDelay = 0;
            }
        }

        if (this.enabled2) {
            if (event.isState4() && this.settingUpAndDown.isEnabled()) {
                event.setState4(false);
            }

            if (this.settingUseSneakMotionInBlocks.isEnabled()) {
                event.setState4(true);
            }

            if (event.isState()) {
                event.setState(false);
            }
        }
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        if (!minecraft.thePlayer.noClip) {
            MoveUtil.processDouble2(
            this.settingMovespeedInBlocks.getValue(), 0.0, minecraft.thePlayer.rotationYaw, false
            );
            event.setCoordOffset2(minecraft.thePlayer.motionX);
            event.setCoordOffset(minecraft.thePlayer.motionZ);
        }
    }

    @Override
    public void onPreAction(EventPreAction event) {
        if (this.expanded2 || minecraft.playerController.getWorldSettingsGameType() == WorldSettingsGameType.ADVENTURE) {
            event.setCancelled(true);
        }
    }

    @Override
    public void onPreTick() {
        this.expanded2 = false;
        if (this.settingClipDownByBreaking.isEnabled()) {
            minecraft.thePlayer.invulnerableManager.state1 = true;
            if (minecraft.playerController.state) {
                MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
                BlockPos blockPos = objectMouseOver.getBlockPos();
                if (objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                    double y = (double)blockPos.getY();
                    if (Math.abs(y - minecraft.thePlayer.posY) >= 2.0) {
                        return;
                    }

                    if (minecraft.theWorld.getIBlockState(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()).getBlock().processAction17()) {
                        return;
                    }

                    EntityPlayerSP player = minecraft.thePlayer;
                    player.netHandlerPlayClient
                    .handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.STOP_DESTROY_BLOCK, objectMouseOver.getBlockPos(), objectMouseOver.sideHit));
                    int index = 0;

                    while (true) {
                        double offset = (double)index;
                        if (!(offset < this.settingPacketCount.getValue())) {
                            this.expanded2 = true;
                            minecraft.playerController.setScaleFactor(1.0E-4F);
                            break;
                        }

                        player.netHandlerPlayClient.handlePacket(new C04PacketPlayerPosition(player.posX, player.posY, player.posZ, true));
                        index++;
                    }
                }
            }
        }
    }

    @Override
    public void updateState3() {
        this.enabled2 = this.checkAxisAlignedBB(minecraft.thePlayer.getBoundingBox2());
        if (this.enabled2) {
            minecraft.thePlayer.noClip = true;
            minecraft.thePlayer.motionY = 0.0;
            minecraft.thePlayer.onGround = true;
        }
    }

    private boolean checkAxisAlignedBB(AxisAlignedBB boundingBox) {
        Iterator iterator = minecraft.theWorld.getCollidingBoundingBoxes(minecraft.thePlayer, minecraft.thePlayer.getBoundingBox2()).iterator();

        while (iterator.hasNext()) {
            if (((AxisAlignedBB)iterator.next()).intersectsWith(boundingBox)) {
                return true;
            }
        }

        return false;
    }

    private boolean isMovespeedInBlocksVisible() {
        return this.settingCustomizeHorizontalMovespeedInBlocks.isEnabled();
    }

    private boolean canExecute5() {
        AxisAlignedBB boundingBox2 = minecraft.thePlayer.getBoundingBox2();
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(boundingBox2.minX, boundingBox2.minY - 0.078, boundingBox2.minZ, boundingBox2.maxX, boundingBox2.maxY, boundingBox2.maxZ);
        Iterator iterator = minecraft.theWorld.getCollidingBoundingBoxes(minecraft.thePlayer, axisAlignedBB).iterator();
        int index;
        if (iterator.hasNext()) {
            double offset = ((AxisAlignedBB)iterator.next()).maxY - minecraft.thePlayer.posY;
            index = offset == 0.0 ? 0 : (offset < 0.0 ? -1 : 1);
        } else {
            index = 0;
        }

        return index != 0;
    }
}
