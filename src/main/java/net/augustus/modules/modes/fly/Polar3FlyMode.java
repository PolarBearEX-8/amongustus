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

public class Polar3FlyMode extends FlyMode {
   private int waterBucketSlot = -1;
   private volatile boolean waterPickupFlag = false;
   private boolean pendingJumpPhase = false;
   private boolean initializedOnGround = false;
   private boolean clickingWater;
   private volatile boolean pickupTriggered;
   private boolean waitingLand;
   private boolean delayIncomingPackets;
   private boolean postJumpStage;
   private boolean flyingActive;
   private double startY;
   private volatile boolean pendingBlinkStart;
   private final TimeHelper initDelayTimer;
   private final TimeHelper clickPhaseTimer;
   private final TimeHelper pickupTimer;
   private final TimeHelper blinkDelayTimer;
   private BlockPos waterPlacePos;
   private int jumpCycleCount;
   private int bucketUseCount;

   public Polar3FlyMode(String str, Fly fly2) {
      super(str, fly2);
      this.clickingWater = false;
      this.pickupTriggered = false;
      this.waitingLand = false;
      this.delayIncomingPackets = false;
      this.postJumpStage = false;
      this.flyingActive = false;
      this.pendingBlinkStart = false;
      this.initDelayTimer = new TimeHelper();
      this.clickPhaseTimer = new TimeHelper();
      this.pickupTimer = new TimeHelper();
      this.blinkDelayTimer = new TimeHelper();
      this.waterPlacePos = new BlockPos(0, 0, 0);
      this.jumpCycleCount = 0;
      this.bucketUseCount = 0;
   }

   public void onEnable() {
      if (mc.thePlayer != null) {
         this.pickupTriggered = false;
         this.pendingJumpPhase = true;
         this.clickingWater = false;
         this.waitingLand = false;
         this.initializedOnGround = false;
         this.flyingActive = false;
         this.waterBucketSlot = -1;
         this.delayIncomingPackets = false;
         this.postJumpStage = false;
         this.pendingBlinkStart = false;
         this.waterPickupFlag = false;
         this.jumpCycleCount = 0;
         this.bucketUseCount = 0;
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

      if (this.waterBucketSlot >= 0 && !this.flyingActive) {
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
         IBlockState blockState = packet2.getBlockState();
         this.onBlockStateChange(packet2.getBlockPosition(), blockState);
      }

      if (packet instanceof S22PacketMultiBlockChange) {

         for (S22PacketMultiBlockChange.BlockUpdateData blockUpdateData : ((S22PacketMultiBlockChange)packet).getChangedBlocks()) {
            IBlockState blockState2 = blockUpdateData.getBlockState();
            this.onBlockStateChange(blockUpdateData.getPos(), blockState2);
         }
      }
   }

   public void onMoveInput(EventMoveInput event) {
      if (this.flyingActive) {
         event.setMoveForward(1.0F);
         event.setJump(true);
      } else {
         event.setMoveForward(0.0F);
         event.setMoveStrafe(0.0F);
         event.setJump(false);
      }

      if (this.pendingJumpPhase) {
         if (this.initDelayTimer.reached(500L) && this.waterBucketSlot >= 0 && this.jumpCycleCount < 4) {
            event.setJump(true);
            this.clickingWater = true;
         }
      }
   }

   public void onClick(EventClick event) {
      if (mc.thePlayer.airTicks == 8) {
         this.jumpCycleCount++;
      }

      if (this.clickingWater && (this.jumpCycleCount < 4 || this.bucketUseCount % 2 != 0)) {
         event.addRightClicks((byte)1);
         event.setRightMouseDown(true);
         this.bucketUseCount++;
         if (mc.thePlayer.ticksExisted % 4 == 0 && this.jumpCycleCount < 4) {
            event.addRightClicks((byte)1);
            this.bucketUseCount++;
         }
      }

      if (this.jumpCycleCount >= 4 && mc.thePlayer.onGround && !this.flyingActive) {
         this.flyingActive = true;
         this.blinkDelayTimer.reset();
         this.pendingBlinkStart = true;
      }

      if (this.blinkDelayTimer.reached(500L) && this.pendingBlinkStart) {
         this.delayIncomingPackets = true;
         this.pendingBlinkStart = false;
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
   }

}
