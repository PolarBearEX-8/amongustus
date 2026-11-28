package net.augustus.modules.movement;

import net.minecraft.network.play.client.C03PacketPlayer;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.ClientManagers;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWall;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class VClip extends Module implements TickListener, PacketListener {
   public final DoubleValue distance;
   public final BooleanValue smartDistance;
   public final DoubleValue maxSmartDistance;
   public final DoubleValue distanceToHeadBlock;
   public final BooleanValue intave;
   private boolean intaveClipping;
   private int intavePacketPhase;
   private int intaveClipTicks;
   private boolean receivedInitialSetback;

   public VClip() {
       super("VClip", "VKlammer", new Color(63, 255, 0, 255), Categorys.MOVEMENT);
      this.distance = new DoubleValue(this, "Distance", -6.0, -10.0, 10.0, 2).visibleWhen(() -> isManualDistanceVisible());
      this.smartDistance = new BooleanValue(this, "SmartDistance", true);
      DoubleValue maxSmartDistanceSetting = new DoubleValue(this, "MaxSmartDistance", -9.9, -10.0, -1.0, 2);
      BooleanValue smartDistance2 = this.smartDistance;
      BooleanValue booleanValue = smartDistance2;
      this.maxSmartDistance = maxSmartDistanceSetting.visibleWhen(() -> booleanValue.getValue());
      DoubleValue distanceToHeadBlockSetting = new DoubleValue(this, "DistanceToHeadBlock", 0.1, 0.0, 1.0, 2);
      smartDistance2 = this.smartDistance;
      BooleanValue booleanValue2 = smartDistance2;
      this.distanceToHeadBlock = distanceToHeadBlockSetting.visibleWhen(() -> booleanValue2.getValue());
      this.intave = new BooleanValue(this, "Intave", false);
      this.intaveClipping = false;
      this.intavePacketPhase = 0;
      this.intaveClipTicks = 0;
      this.receivedInitialSetback = false;
   }

   @Override
   public void onEnable() {
      if (!this.intaveClipping) {
         if (this.intave.getValue()) {
            this.startIntaveClip();
         } else {
            this.performDirectVClip();
            this.toggle();
         }
      }
   }

   @Override
   public void onDisable() {
      this.intaveClipping = false;
      this.receivedInitialSetback = false;
      this.intavePacketPhase = 0;
      this.intaveClipTicks = 0;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(26);
   }

   private void performDirectVClip() {
      if (mc.theWorld != null && mc.thePlayer != null) {
         if (!this.smartDistance.getValue()) {
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + this.distance.getValue(), mc.thePlayer.posZ);
         } else {
            double d = 0.0;

            for (int i = 1; i < 10; i++) {
               BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - (double)i, mc.thePlayer.posZ);
               if (BlockUtil.isSolidBlock(pos)) {
                  double d2 = mc.theWorld.getBlockState(pos).getBlock().getBlockBoundsMaxY() + (double)pos.getY();
                  d = Math.abs(mc.thePlayer.posY - d2);
                  break;
               }
            }

            double value = this.maxSmartDistance.getValue();

            for (int j = (int)(-1.0 - d); (double)j >= Math.min(value, -1.0); j--) {
               BlockPos pos2 = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY + (double)j, mc.thePlayer.posZ);
               if (!BlockUtil.hasCollisionBox(pos2) && !BlockUtil.hasCollisionBox(pos2.up())) {
                  double d3 = (double)j + 0.2 - d % 1.0 - Math.max(this.distanceToHeadBlock.getValue(), 0.0);
                  if (!(d3 < Math.min(value, -1.0))) {
                     mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + d3, mc.thePlayer.posZ);
                     return;
                  }
               }
            }

            this.sendChatMessage("No vclip possible");
         }
      }
   }

   @Override
   public void onEarlyTick() {
      if (this.intaveClipping) {
         this.intaveClipTicks++;
         if (this.intaveClipTicks == 1) {
            this.sendChatMessage("Started clipping");
         }

         if (this.intaveClipTicks >= 130) {
            this.sendChatMessage("Stop clipping");
            this.toggle();
         } else {
            for (int i = 0; i < 9; i++) {
               this.intavePacketPhase++;
               if (this.intavePacketPhase == 1) {
                  EntityPlayerSP thePlayer = mc.thePlayer;

                  for (int j = 0; j < 9; j++) {
                     mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(thePlayer.posX, thePlayer.posY + (double)j + 15.0, thePlayer.posZ, true));
                  }
               } else if (this.intavePacketPhase == 2) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 15.0, mc.thePlayer.posZ, true));
               } else if (this.intavePacketPhase >= 3) {
                  this.intavePacketPhase = 0;
               }
            }
         }
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.intaveClipping && event.getPacket() instanceof S08PacketPlayerPosLook) {
         if (((S08PacketPlayerPosLook)event.getPacket()).getY() > mc.thePlayer.posY + 0.5) {
            this.sendChatMessage("Toggle at " + this.intaveClipTicks);
            this.toggle();
         }

         if (this.receivedInitialSetback) {
            event.cancel();
            return;
         }

         this.receivedInitialSetback = true;
      }
   }

   private void startIntaveClip() {
      if (!this.intaveClipping) {
         if (this.hasNearbyClipBlock()) {
            this.intaveClipping = true;
            this.intavePacketPhase = 0;
         } else {
            this.sendChatMessage("Invalid clip position");
            this.toggle();
         }
      }
   }

   private boolean hasNearbyClipBlock() {
      EntityPlayerSP thePlayer = mc.thePlayer;

      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
               int n = MathHelper.floor_double(thePlayer.posX + (double)i);
               int n2 = MathHelper.floor_double(thePlayer.posY + (double)j);
               int n3 = MathHelper.floor_double(thePlayer.posZ + (double)k);
               IBlockState blockState2 = mc.theWorld.getBlockState2(n, n2, n3);
               Block block = blockState2.getBlock();
               if (block instanceof BlockPane
                  || block instanceof BlockFence
                  || block instanceof BlockWall
                  || block instanceof BlockDoor
                  || block instanceof BlockLadder
                  || block instanceof BlockVine
                  || block instanceof BlockReed
                  || block instanceof BlockSign
                  || block instanceof BlockWeb
                  || block instanceof BlockFire
                  || this.isClosedBottomTrapdoor(blockState2)
                  || block.getMaterial() == Material.water) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean isClosedBottomTrapdoor(IBlockState state) {
      return state.getBlock() instanceof BlockTrapDoor && !(Boolean)state.getValue(BlockTrapDoor.OPEN) && state.getValue(BlockTrapDoor.HALF) == BlockTrapDoor.DoorHalf.BOTTOM;
   }

   private static boolean isManualDistanceVisible() {
      return !ClientManagers.moduleManager.vClip.smartDistance.getValue();
   }

}
