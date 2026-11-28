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
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class PolarFlyMode extends FlyMode {
   private int waterBucketSlot = -1;
   private boolean blinkRequested = false;
   private boolean blinkActive = false;
   private boolean flyingActive = false;
   private final TimeHelper initDelayTimer = new TimeHelper();
   private final TimeHelper firstClickTimer = new TimeHelper();
   private final TimeHelper secondClickTimer = new TimeHelper();
   private final TimeHelper flyStartTimer = new TimeHelper();
   private boolean pendingFirstClick;
   private boolean pendingSecondClick;
   private boolean waitingStopBlink;
   private boolean waitingStartFly;
   private double startY;
   private boolean waitingStartBlink;
   private BlockPos originBlockPos = BlockPos.ORIGIN;
   private long enableTimestampMs;
   private boolean northWaterFlowed;
   private boolean northWaterCleared;
   private boolean southWaterFlowed;
   private boolean southWaterCleared;
   private boolean eastWaterFlowed;
   private boolean eastWaterCleared;
   private boolean westWaterFlowed;
   private boolean westWaterCleared;
   private boolean initializedOnGround = false;
   private final boolean debugLogging = false;

   public PolarFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      if (mc.thePlayer != null) {
         this.initializedOnGround = false;
         this.waterBucketSlot = -1;
         this.blinkRequested = false;
         this.flyingActive = false;
         this.pendingFirstClick = true;
         this.pendingSecondClick = true;
         this.waitingStopBlink = false;
         this.waitingStartFly = false;
         this.waitingStartBlink = false;
         this.enableTimestampMs = System.currentTimeMillis();
         EnumFacing north = EnumFacing.NORTH;
         this.northWaterFlowed = this.northWaterCleared = this.isAdjacentBlockSolid(this.originBlockPos, north);
         EnumFacing south = EnumFacing.SOUTH;
         this.southWaterFlowed = this.southWaterCleared = this.isAdjacentBlockSolid(this.originBlockPos, south);
         EnumFacing east = EnumFacing.EAST;
         this.eastWaterFlowed = this.eastWaterCleared = this.isAdjacentBlockSolid(this.originBlockPos, east);
         EnumFacing west = EnumFacing.WEST;
         this.westWaterFlowed = this.westWaterCleared = this.isAdjacentBlockSolid(this.originBlockPos, west);
      }
   }

   public void onDisable() {
   }

   public void onEarlyTick() {
      if (!this.initializedOnGround && mc.thePlayer.onGround) {
         if (this.hasClearAirAroundPlayer()) {
            this.initializedOnGround = true;
            this.originBlockPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.lastGroundY, mc.thePlayer.posZ);
            this.startY = mc.thePlayer.lastGroundY;
            this.initDelayTimer.reset();
         } else {
            this.fly.setToggled(false);
            String str = "§cInvalid starting pos for PolarFly. Please build two blocks up with air to all sites.";
            this.fly.sendChatMessage(str);
         }
      }

      if (this.waterBucketSlot >= 0) {
         Fly fly2 = this.fly;
         float f = 90.0F;
         ClientManagers.rotationController.requestPitch(f, (byte)-90, fly2);
      }
   }

   public void onPostUpdate() {
   }

   public void onClick(EventClick event) {
      if (this.initDelayTimer.reached(500L) && this.waterBucketSlot >= 0 && this.initializedOnGround) {
         if (this.pendingFirstClick) {
            event.addRightClicks((byte)1);
            this.pendingFirstClick = false;
            this.firstClickTimer.reset();
            this.waitingStartBlink = true;
            this.debugLog("Click 1 " + this.getElapsedMs());
         } else if (this.pendingSecondClick && this.hasWaterFlowedAllSides()) {
            event.addRightClicks((byte)1);
            this.secondClickTimer.reset();
            this.pendingSecondClick = false;
            this.waitingStopBlink = true;
            this.debugLog("Click 2 " + this.getElapsedMs());
         }
      }

      if (this.waitingStartBlink) {
         if (this.firstClickTimer.reached(25L)) {
            this.blinkRequested = true;
            this.waitingStartBlink = false;
            this.debugLog("Start blink " + this.getElapsedMs());
         }
      }

      if (this.waitingStopBlink) {
         if (this.secondClickTimer.reached(725L)) {
            this.blinkRequested = false;
            this.waitingStopBlink = false;
            this.debugLog("Stop blink " + this.getElapsedMs());
            this.flyStartTimer.reset();
            this.waitingStartFly = true;
         }
      }

      if (this.waitingStartFly) {
         if (this.flyStartTimer.reached(475L)) {
            this.blinkRequested = true;
            this.flyingActive = true;
            this.debugLog("Start fly " + this.getElapsedMs());
            this.waitingStartFly = false;
         }
      }
   }

   public void onPreUpdate() {
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
   }

   public void onSilentSlot() {
      if (this.waterBucketSlot == -1 && this.initializedOnGround) {
         ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
         containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
         OptionalInt optionalInt = containerSlotQuery.filterItemExact(Items.water_bucket).findFirstSlotIndex();
         if (optionalInt.isPresent()) {
            this.waterBucketSlot = optionalInt.getAsInt() - 36;
         } else {
            String str = "§cYou need a water bucket in your hotbar. Fly disabled.";
            this.fly.sendChatMessage(str);
            this.waterBucketSlot = -2;
            this.fly.setToggled(false);
         }
      }

      if (this.waterBucketSlot != -1 && this.pendingSecondClick) {
         int waterBucketSlot2 = this.waterBucketSlot;
         SilentSlotManager.requestSlot(this.fly, (byte)-10, waterBucketSlot2, true);
      }
   }

   public void onIncomingPacketDelay(EventPacketDelay event) {
      Packet packet = event.getPacket();
      if (this.blinkRequested) {
         this.blinkActive = true;
      }

      if (packet instanceof S23PacketBlockChange) {
         S23PacketBlockChange packet2 = (S23PacketBlockChange)packet;
         BlockPos blockPosition = packet2.getBlockPosition();
         if (this.isNearOrigin(blockPosition)) {
            IBlockState blockState = packet2.getBlockState();
            Block block = blockState.getBlock();
            if (block instanceof BlockLiquid) {
               float f = (float)((Integer)blockState.getValue(BlockLiquid.LEVEL)).intValue();
               this.debugLog("Value: " + f);
            }

            this.updateAdjacentWaterState(blockPosition, block);
            this.debugLog("Water flowing block " + blockPosition + " " + blockState.getBlock() + " " + this.getElapsedMs());
         }
      }

      if (packet instanceof S32PacketConfirmTransaction) {
         this.debugLog("Confirm transaction " + this.getElapsedMs());
      }

      if (packet instanceof S22PacketMultiBlockChange) {
         S22PacketMultiBlockChange.BlockUpdateData[] changedBlocks = ((S22PacketMultiBlockChange)packet).getChangedBlocks();
         boolean flag = false;

         for (S22PacketMultiBlockChange.BlockUpdateData blockUpdateData : changedBlocks) {
            BlockPos pos = blockUpdateData.getPos();
            if (this.isNearOrigin(pos)) {
               IBlockState blockState2 = blockUpdateData.getBlockState();
               Block block2 = blockState2.getBlock();
               if (block2 instanceof BlockLiquid) {
                  float f2 = (float)((Integer)blockState2.getValue(BlockLiquid.LEVEL)).intValue();
                  this.debugLog("Value: " + f2);
               }

               this.updateAdjacentWaterState(pos, block2);
               this.debugLog("Water flowing multi block " + pos + " " + blockState2.getBlock() + " " + this.getElapsedMs());
               flag = true;
            }
         }

         if (flag) {
            this.debugLog("Multi block END");
         }
      }

      if (event.getPacket() instanceof S08PacketPlayerPosLook) {
         this.fly.setToggled(false);
      }

      if (!this.blinkRequested) {
         this.blinkActive = false;
      }

      if (this.blinkActive) {
         event.setDelayed(true);
      }

      if (this.blinkRequested && packet instanceof S32PacketConfirmTransaction) {
         this.blinkActive = true;
      }
   }

   public void onMoveInput(EventMoveInput event) {
      if (this.flyingActive) {
         event.setMoveForward(1.0F);
      } else if (!this.pendingFirstClick && this.pendingSecondClick) {
         event.setMoveForward(0.0F);
         event.setMoveStrafe(0.0F);
      }
   }

   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      if (this.flyingActive) {
         Block block = event.getBlock();
         if ((block instanceof BlockAir || block instanceof BlockLiquid) && (double)event.getBlockPos().getY() < this.startY) {
            BlockPos blockPos = event.getBlockPos();
            event.setBoundingBox(new AxisAlignedBB(
               (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)
            ));
         }
      }
   }

   private void updateAdjacentWaterState(BlockPos pos, Block block) {
      if (this.originBlockPos.north().equals(pos) && block instanceof BlockStaticLiquid) {
         this.northWaterFlowed = true;
      }

      if (this.originBlockPos.south().equals(pos) && block instanceof BlockStaticLiquid) {
         this.southWaterFlowed = true;
      }

      if (this.originBlockPos.west().equals(pos) && block instanceof BlockStaticLiquid) {
         this.westWaterFlowed = true;
      }

      if (this.originBlockPos.east().equals(pos) && block instanceof BlockStaticLiquid) {
         this.eastWaterFlowed = true;
      }

      if (this.originBlockPos.north().equals(pos) && block instanceof BlockAir) {
         this.northWaterCleared = true;
      }

      if (this.originBlockPos.south().equals(pos) && block instanceof BlockAir) {
         this.southWaterCleared = true;
      }

      if (this.originBlockPos.west().equals(pos) && block instanceof BlockAir) {
         this.westWaterCleared = true;
      }

      if (this.originBlockPos.east().equals(pos) && block instanceof BlockAir) {
         this.eastWaterCleared = true;
      }
   }

   private boolean isNearOrigin(BlockPos pos) {
      if (this.flyingActive) {
         return false;
      } else {
         for (int i = -1; i <= 1; i++) {
            for (int j = -2; j <= 0; j++) {
               for (int k = -1; k <= 1; k++) {
                  int n = this.originBlockPos.getX() + i;
                  int n2 = this.originBlockPos.getY() + j;
                  int n3 = this.originBlockPos.getZ() + k;
                  if (n == pos.getX() && n2 == pos.getY() && n3 == pos.getZ()) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   private long getElapsedMs() {
      return System.currentTimeMillis() - this.enableTimestampMs;
   }

   private boolean hasWaterFlowedAllSides() {
      return this.northWaterFlowed && this.southWaterFlowed && this.westWaterFlowed && this.eastWaterFlowed;
   }

   private boolean hasWaterClearedAllSides() {
      return this.northWaterCleared && this.southWaterCleared && this.westWaterCleared && this.eastWaterCleared;
   }

   private void debugLog(String str) {
      if (this.debugLogging) {
      }
   }

   private boolean isAdjacentBlockSolid(BlockPos pos, EnumFacing facing) {
      return mc.theWorld
            .getBlockState2(pos.getX() + facing.getFrontOffsetX(), pos.getY() + facing.getFrontOffsetY(), pos.getZ() + facing.getFrontOffsetZ())
            .getBlock()
            .getMaterial()
         != Material.air;
   }

   private boolean hasClearAirAroundPlayer() {

      for (int i = -1; i <= 1; i++) {
         for (EnumFacing facing : EnumFacing.VALUES) {
            if (facing.getAxis().isHorizontal()) {
               double d = mc.thePlayer.posX + (double)facing.getFrontOffsetX();
               double d2 = mc.thePlayer.posY + (double)i;
               double d3 = mc.thePlayer.posZ + (double)facing.getFrontOffsetZ();
               if (mc.theWorld.getBlockState(d, d2, d3).getBlock().getMaterial() != Material.air) {
                  return false;
               }
            }
         }
      }

      return true;
   }

}
