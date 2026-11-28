package net.augustus.modules.player;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.world.WorldSettings;
import java.awt.Color;
import java.util.List;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.BlockBreakListener;
import net.augustus.events.listeners.BlockPlaceListener;
import net.augustus.utils.MoveUtil;
import net.augustus.events.EventBlockPlace;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.NoClipListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.BlockPlaceStage;
import net.augustus.events.EventBlockBreak;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.EventMoveEntity;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;

public class Phase extends Module implements NoClipListener, MoveInputListener, MoveEntityListener, TickListener, BlockBreakListener, BlockPlaceListener {
   private final SettingGroup horizontal;
   private final BooleanValue customizeHorizontalMovespeedInBlocks;
   private final BooleanValue useSneakMotionInBlocks;
   private final DoubleValue movespeedInBlocks;
   private final SettingGroup vertical;
   private final BooleanValue fallIfNoBlockIsUnderPlayer;
   private final BooleanValue upAndDown;
   private final DoubleValue upwardsSteps;
   private final DoubleValue upwardsTickDelay;
   private final DoubleValue downwardsSteps;
   private final DoubleValue downwardsTickDelay;
   private final BooleanValue clipDownByBreaking;
   private final DoubleValue packetCount;
   private int verticalStepTicks;
   private float cachedYaw;
   private boolean insideBlock;
   private boolean clippedByBreaking;

   public Phase() {
       super("Phase", "DurchWändeGehen", new Color(45, 196, 148), Categorys.PLAYER);
      this.horizontal = new SettingGroup(this, "Horizontal");
      this.customizeHorizontalMovespeedInBlocks = new BooleanValue(this, "Customize horizontal Movespeed in blocks", false, this.horizontal);
      BooleanValue booleanValue = new BooleanValue(this, "Use sneak motion in blocks", false, this.horizontal);
      BooleanValue booleanValue2 = this.customizeHorizontalMovespeedInBlocks;
      BooleanValue booleanValue3 = booleanValue2;
      this.useSneakMotionInBlocks = (BooleanValue)booleanValue.withVisibility(() -> booleanValue3.getValue());
      this.movespeedInBlocks = new DoubleValue(this, "Movespeed in blocks", 0.1, 1.0E-4, 1.0, 4, this.horizontal).visibleWhen(() -> this.isMovespeedInBlocksVisible());
      this.vertical = new SettingGroup(this, "Vertical");
      this.fallIfNoBlockIsUnderPlayer = new BooleanValue(this, "Fall if no block is under player", false, this.vertical);
      this.upAndDown = new BooleanValue(this, "UpAndDown", true, this.vertical);
      DoubleValue upwardsStepsSetting = new DoubleValue(this, "UpwardsSteps", 1.0, 0.0, 2.0, 2, this.vertical);
      booleanValue2 = this.upAndDown;
      BooleanValue booleanValue4 = booleanValue2;
      this.upwardsSteps = upwardsStepsSetting.visibleWhen(() -> booleanValue4.getValue());
      DoubleValue upwardsTickDelaySetting = new DoubleValue(this, "UpwardsTickDelay", 5.0, 0.0, 20.0, 0, this.vertical);
      booleanValue2 = this.upAndDown;
      BooleanValue booleanValue5 = booleanValue2;
      this.upwardsTickDelay = upwardsTickDelaySetting.visibleWhen(() -> booleanValue5.getValue());
      DoubleValue downwardsStepsSetting = new DoubleValue(this, "DownwardsSteps", 1.0, 0.0, 2.0, 2, this.vertical);
      booleanValue2 = this.upAndDown;
      BooleanValue booleanValue6 = booleanValue2;
      this.downwardsSteps = downwardsStepsSetting.visibleWhen(() -> booleanValue6.getValue());
      DoubleValue downwardsTickDelaySetting = new DoubleValue(this, "DownwardsTickDelay", 5.0, 0.0, 20.0, 0, this.vertical);
      booleanValue2 = this.upAndDown;
      BooleanValue booleanValue7 = booleanValue2;
      this.downwardsTickDelay = downwardsTickDelaySetting.visibleWhen(() -> booleanValue7.getValue());
      this.clipDownByBreaking = new BooleanValue(this, "Clip down by breaking", false, this.vertical);
      DoubleValue packetCountSetting = new DoubleValue(this, "Packet count", 1.0, 1.0, 10.0, 0, this.vertical);
      booleanValue2 = this.clipDownByBreaking;
      BooleanValue booleanValue8 = booleanValue2;
      this.packetCount = packetCountSetting.visibleWhen(() -> booleanValue8.getValue());
      this.verticalStepTicks = 0;
      this.cachedYaw = 0.0F;
      this.clippedByBreaking = false;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      if (mc.playerController.getCurrentGameType() == WorldSettings.GameType.ADVENTURE) {
         mc.thePlayer.capabilities.allowEdit = false;
      }
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(27);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(59);
      this.toggleEventSubscription(60);
   }

   @Override
   public void onNoClip() {
      this.insideBlock = this.isInsideBlock(mc.thePlayer.getEntityBoundingBox());
      if (this.insideBlock) {
         if (this.fallIfNoBlockIsUnderPlayer.getValue()) {
            if (!this.hasBlockUnderPlayer()) {
               return;
            }
         }

         mc.thePlayer.noClip = true;
         mc.thePlayer.motionY = 0.0;
         mc.thePlayer.onGround = true;
      }
   }

   private boolean hasBlockUnderPlayer() {
      AxisAlignedBB entityBoundingBox = mc.thePlayer.getEntityBoundingBox();
      entityBoundingBox = new AxisAlignedBB(entityBoundingBox.minX, entityBoundingBox.minY - 0.078, entityBoundingBox.minZ, entityBoundingBox.maxX, entityBoundingBox.maxY, entityBoundingBox.maxZ);

      for (AxisAlignedBB box : (Iterable<AxisAlignedBB>)(mc.theWorld.getCollidingBoundingBoxesCapped(mc.thePlayer, entityBoundingBox))) {
         if (box.maxY <= mc.thePlayer.posY && box.intersectsWith(entityBoundingBox)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void onEarlyTick() {
      this.clippedByBreaking = false;
      if (this.clipDownByBreaking.getValue()) {
         mc.thePlayer.capabilities.allowEdit = true;
         if (mc.playerController.isHittingBlock) {
            MovingObjectPosition objectMouseOver = mc.objectMouseOver;
            BlockPos blockPos = objectMouseOver.getBlockPos();
            if (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
               && Math.abs((double)blockPos.getY() - mc.thePlayer.posY) < 2.0
               && !mc.theWorld.getBlockState2(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()).getBlock().hasCollisionBox()) {
               EntityPlayerSP thePlayer = mc.thePlayer;
               thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, objectMouseOver.getBlockPos(), objectMouseOver.sideHit));

               for (int i = 0; (double)i < this.packetCount.getValue(); i++) {
                  thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(thePlayer.posX, thePlayer.posY, thePlayer.posZ, true));
               }

               this.clippedByBreaking = true;
               mc.playerController.setCurBlockDamageMP(1.0E-4F);
            }
         }
      }
   }

   private boolean isInsideBlock(AxisAlignedBB box) {

      for (AxisAlignedBB box2 : (Iterable<AxisAlignedBB>)(mc.theWorld.getCollidingBoundingBoxesCapped(mc.thePlayer, mc.thePlayer.getEntityBoundingBox()))) {
         if (box2.intersectsWith(box)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      this.verticalStepTicks++;
      if (!this.insideBlock || !this.upAndDown.getValue()) {
         this.verticalStepTicks = 0;
      } else if (event.isJump()) {
         if ((double)this.verticalStepTicks > this.upwardsTickDelay.getValue()) {
            mc.thePlayer.motionY = this.upwardsSteps.getValue();
            this.verticalStepTicks = 0;
         }
      } else if (event.isSneak()) {
         if ((double)this.verticalStepTicks > this.downwardsTickDelay.getValue()) {
            mc.thePlayer.motionY = -this.downwardsSteps.getValue();
            this.verticalStepTicks = 0;
         }
      } else {
         this.verticalStepTicks = 0;
      }

      if (this.insideBlock) {
         if (event.isSneak() && this.upAndDown.getValue()) {
            event.setSneak(false);
         }

         if (this.useSneakMotionInBlocks.getValue()) {
            event.setSneak(true);
         }

         if (event.isJump()) {
            event.setJump(false);
         }
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (mc.thePlayer.noClip && this.customizeHorizontalMovespeedInBlocks.getValue() && !this.useSneakMotionInBlocks.getValue()) {
         MoveUtil.setMotionWithOffsetAndSprint(this.movespeedInBlocks.getValue(), 0.0, mc.thePlayer.rotationYaw, false);
         event.setX(mc.thePlayer.motionX);
         event.setZ(mc.thePlayer.motionZ);
      }
   }

   @Override
   public void onBlockBreak(EventBlockBreak event) {
      if (this.clippedByBreaking || mc.playerController.getCurrentGameType() == WorldSettings.GameType.ADVENTURE) {
         event.setCancelled(true);
      }
   }

   @Override
   public void onPreBlockPlace(EventBlockPlace event) {
      if (event.getStage() == BlockPlaceStage.PRE && mc.thePlayer.capabilities.allowEdit && mc.playerController.getCurrentGameType() == WorldSettings.GameType.ADVENTURE) {
         IBlockState blockState = mc.theWorld.getBlockState(mc.objectMouseOver.getBlockPos());
         if (blockState.getBlock() != Blocks.trapdoor && (!(blockState.getBlock() instanceof BlockDoor) || blockState.getBlock() == Blocks.iron_door)) {
            event.setCancelled(true);
         }
      }
   }

   private boolean isMovespeedInBlocksVisible() {
      return this.customizeHorizontalMovespeedInBlocks.getValue() && !this.useSneakMotionInBlocks.getValue();
   }

}
