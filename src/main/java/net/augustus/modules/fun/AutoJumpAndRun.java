package net.augustus.modules.fun;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.utils.rotation.RotationController;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.StringValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

public class AutoJumpAndRun extends Module implements TickListener, MoveInputListener {
   public final StringValue mode;
   private final TimeHelper timeHelper;
   private BlockPos nextWoolBlock;
   private BlockPos currentStandingBlock;
   private boolean wasAirborne;

   public AutoJumpAndRun() {
       super("AutoJumpAndRun", "AutoSpringenUndLaufen", new Color(119, 148, 61), Categorys.FUN);
      this.mode = new StringValue(this, "Mode", "Gomme", new String[]{"Gomme"});
      this.timeHelper = new TimeHelper();
      this.nextWoolBlock = null;
      this.currentStandingBlock = null;
   }

   public void onEnable() {
      this.timeHelper.reset();
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(21);
   }

   public void onEarlyTick() {
      if (mc.thePlayer.onGround) {
         BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ);
         this.currentStandingBlock = mc.theWorld.getBlockState(pos).getBlock().isFullBlock() ? pos : this.currentStandingBlock;
         if (this.currentStandingBlock == null) {
            return;
         }

         if (this.wasAirborne) {
            this.timeHelper.reset();
         }

         this.nextWoolBlock = this.findNearbyWoolBlock(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ));
         if (this.nextWoolBlock != null) {
            float f;
            if (this.timeHelper.reached(1000L)) {
               double d = (double)this.nextWoolBlock.getX() + 0.5;
               double d2 = (double)this.nextWoolBlock.getY() + 0.5;
               double d3 = (double)this.nextWoolBlock.getZ() + 0.5;
               Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
               float f2 = 180.0F;
               f = RotationUtil.getRotationsToPos(d, d2, d3, previousRotation, 180.0F, f2, false).yaw;
            } else {
               double d4 = (double)this.currentStandingBlock.getX() + 0.5;
               double d5 = (double)this.currentStandingBlock.getY() + 0.5;
               double d6 = (double)this.currentStandingBlock.getZ() + 0.5;
               Rotation previousRotation2 = ClientManagers.rotationController.getPreviousRotation();
               float f4 = 180.0F;
               f = RotationUtil.getRotationsToPos(d4, d5, d6, previousRotation2, 180.0F, f4, false).yaw;
            }

            ClientManagers.rotationController.requestYaw(f, (byte)10, this);
         } else {
            ClientManagers.rotationController.requestYaw(ClientManagers.rotationController.getPreviousRotation().yaw, (byte)10, this);
         }

         this.wasAirborne = false;
      } else {
         this.wasAirborne = true;
         ClientManagers.rotationController.requestYaw(ClientManagers.rotationController.getPreviousRotation().yaw, (byte)10, this);
      }
   }

   public void onPreMoveInput(EventMoveInput event) {
      if (mc.isNotInGui() && mc.thePlayer != null && this.nextWoolBlock != null && this.currentStandingBlock != null) {
         if (this.timeHelper.reached(2000L)) {
            Block block = mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ)).getBlock();
            double d = 1.29;
            double d2 = 0.29;
            double posX = mc.thePlayer.posX;
            double posZ = mc.thePlayer.posZ;
            posX += (mc.thePlayer.posX - mc.thePlayer.prevPosX) * 0.0;
            posZ += (mc.thePlayer.posZ - mc.thePlayer.prevPosZ) * 0.0;
            double d3 = (double)this.currentStandingBlock.getX() + d;
            double d4 = (double)this.currentStandingBlock.getX() - d2;
            double d5 = (double)this.currentStandingBlock.getZ() + d;
            double d6 = (double)this.currentStandingBlock.getZ() - d2;
            if (mc.thePlayer.getDistance((double)this.nextWoolBlock.getX(), (double)this.nextWoolBlock.getY(), (double)this.nextWoolBlock.getZ()) > 2.6) {
               if (posX > d3 || posX < d4 || posZ > d5 || posZ < d6) {
                  event.setJump(true);
               }
            } else if (!block.isFullBlock()) {
               event.setJump(true);
            }

            event.setMoveForward(1.0F);

            for (int i = 1; i < 4; i++) {
               BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - (double)i, mc.thePlayer.posZ);
               if (BlockUtil.isSolidBlock(pos) && pos.equalsPos(this.nextWoolBlock)) {
                  event.setMoveForward(-1.0F);
                  if (mc.thePlayer.onGround) {
                     event.setSneak(true);
                  }
               }
            }
         } else {
            if (!this.timeHelper.reached(1000L)) {
               event.setMoveForward(1.0F);
               event.setSneak(true);
            }
         }
      }
   }

   private BlockPos findNearbyWoolBlock(BlockPos pos) {

      for (int i = -6; i <= 6; i++) {
         for (int j = 0; j <= 1; j++) {
            for (int k = -6; k <= 6; k++) {
               int n = pos.getX() + i;
               int n2 = pos.getY() + j;
               int n3 = pos.getZ() + k;
               if (BlockUtil.isSolidBlock((double)n, (double)n2, (double)n3)) {
                  Block block = mc.theWorld.getBlockState2(n, n2, n3).getBlock();
                  Block block2 = mc.theWorld.getBlockState2(n, n2 + 1, n3).getBlock();
                  if (block.equals(Blocks.wool) && !block2.isFullBlock()) {
                     return pos.add(i, j, k);
                  }
               }
            }
         }
      }

      return null;
   }


}
