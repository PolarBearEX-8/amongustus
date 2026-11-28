package net.augustus.modules.modes.fly;

import net.augustus.ClientManagers;
import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.modules.movement.Fly;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.BlockAir;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class VulcanFlyMode extends FlyMode {
   public double collisionFloorY = 0.0;
   private float activeTimerSpeed = 1.0F;
   private final TimeHelper timeHelper = new TimeHelper();
   private int setbackCount = 0;

   public VulcanFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   public void onEnable() {
      this.collisionFloorY = mc.thePlayer.posY - 0.5;
      this.timeHelper.reset();
      this.setbackCount = 0;
      this.activeTimerSpeed = (float)this.fly.timerSpeed.getValue();
   }

   public void onDisable() {
   }

   public void onPreUpdate() {
   }

   public void onMotion(EventMotion event) {
   }

   public void onMoveEntity(EventMoveEntity event) {
   }

   public void onMoveInput(EventMoveInput event) {
      if (this.setbackCount > 3) {
         if (this.fly.timer.getValue()) {
            mc.getTimer().timerSpeed = this.activeTimerSpeed;
         }

         if (mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ).down()).getBlock().isFullBlock()) {
            if (!this.timeHelper.reached(500L)) {
               this.activeTimerSpeed = (float)this.fly.timerSpeedEnd.getValue();
            }

            event.setSneak(true);
         } else {
            this.timeHelper.reset();
            this.setbackCount = 4;
         }
      } else {
         this.timeHelper.reset();
      }
   }

   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      if (event.getBlock() instanceof BlockAir && (double)event.getBlockPos().getY() < this.collisionFloorY) {
         BlockPos blockPos = event.getBlockPos();
         AxisAlignedBB box = new AxisAlignedBB(
            (double)blockPos.getX(),
            (double)blockPos.getY(),
            (double)blockPos.getZ(),
            (double)(blockPos.getX() + 1),
            (double)blockPos.getY() + ClientManagers.moduleManager.fly.vulcanCollisionY.getValue(),
            (double)(blockPos.getZ() + 1)
         );
         event.setBoundingBox(box);
      }
   }

   public void onReadPacket(EventPacket event) {
      if (event.getPacket() instanceof S08PacketPlayerPosLook) {
         event.cancel();
         this.setbackCount++;
      }
   }
}
