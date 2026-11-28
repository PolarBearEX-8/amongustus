package net.augustus.modules.modes.fly;

import net.augustus.ClientManagers;
import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.EventClick;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.utils.player.SilentSlotManager;
import java.util.OptionalInt;
import net.augustus.modules.movement.Fly;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class Polar2FlyMode extends FlyMode {
   private int waterBucketSlot = -1;
   private volatile boolean waitingWaterPickup = false;
   private volatile boolean shouldJumpAfterPickup = false;
   private boolean initializedOnGround = false;
   private boolean pendingFirstClick = false;
   private boolean pendingSecondClick = false;
   private boolean jumpedAfterPickup;
   private boolean delayIncomingPackets;
   private boolean landedWaitingGlide;
   private boolean flyingActive;
   private double startY;
   private volatile boolean waterPlacedDetected;
   private final TimeHelper initDelayTimer;
   private final TimeHelper secondClickTimer;
   private final TimeHelper pickupConfirmTimer;
   private final TimeHelper postLandTimer;
   private BlockPos waterPlacePos;

   public Polar2FlyMode(String str, Fly fly2) {
      super(str, fly2);
      this.jumpedAfterPickup = false;
      this.delayIncomingPackets = false;
      this.landedWaitingGlide = false;
      this.flyingActive = false;
      this.waterPlacedDetected = false;
      this.initDelayTimer = new TimeHelper();
      this.secondClickTimer = new TimeHelper();
      this.pickupConfirmTimer = new TimeHelper();
      this.postLandTimer = new TimeHelper();
      this.waterPlacePos = new BlockPos(0, 0, 0);
   }

   public void onEnable() {
      if (mc.thePlayer != null) {
         this.pendingSecondClick = false;
         this.shouldJumpAfterPickup = false;
         this.pendingFirstClick = true;
         this.jumpedAfterPickup = false;
         this.initializedOnGround = false;
         this.flyingActive = false;
         this.waterBucketSlot = -1;
         this.delayIncomingPackets = false;
         this.landedWaitingGlide = false;
         this.waterPlacedDetected = true;
         this.waitingWaterPickup = false;
      }
   }

   public void onDisable() {
   }

   public void onPreUpdate() {
   }

   public void onEarlyTick() {
      if (!this.initializedOnGround && mc.thePlayer.onGround) {
         this.startY = mc.thePlayer.posY;
         this.initializedOnGround = true;
         this.initDelayTimer.reset();
         this.waterPlacePos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
      }

      if (this.waterBucketSlot >= 0) {
         Fly fly2 = this.fly;
         float f = 90.0F;
         ClientManagers.rotationController.requestPitch(f, (byte)-90, fly2);
      }
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
   }

   public void onIncomingPacketDelay(EventPacketDelay event) {
      if (this.delayIncomingPackets) {
         event.setDelayed(true);
      }

      Packet packet = event.getPacket();
      if (packet instanceof S23PacketBlockChange) {
         S23PacketBlockChange packet2 = (S23PacketBlockChange)packet;
         this.onBlockStateChange(packet2.getBlockPosition(), packet2.getBlockState());
      }

      if (packet instanceof S22PacketMultiBlockChange) {

         for (S22PacketMultiBlockChange.BlockUpdateData blockUpdateData : ((S22PacketMultiBlockChange)packet).getChangedBlocks()) {
            this.onBlockStateChange(blockUpdateData.getPos(), blockUpdateData.getBlockState());
         }
      }
   }

   public void onMoveInput(EventMoveInput event) {
      if (this.flyingActive) {
if (this.postLandTimer.reached(325L)) {
event.setMoveForward(1.0F);
               if (this.postLandTimer.reached(425L)) {
                  event.setJump(true);
               } else {
                  event.setJump(false);
               }
} else {
event.setMoveForward(0.0F);
         event.setJump(false);
}
} else {
event.setMoveForward(0.0F);
         event.setJump(false);
}

      if (this.shouldJumpAfterPickup) {
         this.shouldJumpAfterPickup = false;
         event.setJump(true);
         this.jumpedAfterPickup = true;
      }
   }

   public void onClick(EventClick event) {
      if (this.initDelayTimer.reached(525L) && this.initializedOnGround && this.waterBucketSlot >= 0) {
         if (this.pendingFirstClick) {
            this.pendingFirstClick = false;
            event.addRightClicks((byte)1);
            this.secondClickTimer.reset();
            this.pendingSecondClick = true;
         } else if (this.pendingSecondClick) {
            if (this.secondClickTimer.reached(575L)) {
               this.pendingSecondClick = false;
               event.addRightClicks((byte)1);
               this.waitingWaterPickup = true;
            }
         }
      }

      if (this.pendingSecondClick && this.waterPlacedDetected && !this.delayIncomingPackets) {
         this.delayIncomingPackets = true;
      }

      if (this.jumpedAfterPickup && mc.thePlayer.onGround && mc.thePlayer.onGroundTicks > 5) {
         this.jumpedAfterPickup = false;
         this.delayIncomingPackets = false;
         this.landedWaitingGlide = true;
         this.postLandTimer.reset();
      }

      if (this.landedWaitingGlide) {
         if (this.postLandTimer.reached(325L)) {
            this.delayIncomingPackets = true;
            if (this.postLandTimer.reached(375L)) {
               this.flyingActive = true;
            }
         }
      }
   }

   public void onSilentSlot() {
      if (this.waterBucketSlot == -1 && this.initializedOnGround) {
         ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
         containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
         OptionalInt optionalInt = containerSlotQuery.filterItemExact(Items.water_bucket).findFirstSlotIndex();
         if (optionalInt.isPresent()) {
            this.waterBucketSlot = optionalInt.getAsInt() - 36;
            this.initDelayTimer.reset();
         } else {
            String str = "§cYou need a water bucket in your hotbar. Fly disabled.";
            this.fly.sendChatMessage(str);
            this.waterBucketSlot = -2;
            this.fly.setToggled(false);
         }
      }

      if (this.waterBucketSlot >= 0 && !this.flyingActive) {
         int waterBucketSlot2 = this.waterBucketSlot;
         SilentSlotManager.requestSlot(this.fly, (byte)-10, waterBucketSlot2, true);
      }
   }

   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      if (this.flyingActive) {
         Block block = event.getBlock();
         if ((block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockWeb) && (double)event.getBlockPos().getY() < this.startY) {
            BlockPos blockPos = event.getBlockPos();
            event.setBoundingBox(new AxisAlignedBB(
               (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)
            ));
         }
      }
   }

   private void onBlockStateChange(BlockPos pos, IBlockState state) {
      Block block = state.getBlock();
      if (pos.equals(this.waterPlacePos) && block instanceof BlockDynamicLiquid) {
         this.waterPlacedDetected = true;
      }

      if (this.waitingWaterPickup && pos.equals(this.waterPlacePos) && block instanceof BlockAir) {
         this.pickupConfirmTimer.reset();
         this.shouldJumpAfterPickup = true;
      }
   }

}
