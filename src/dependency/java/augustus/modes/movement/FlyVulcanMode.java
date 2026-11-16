package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.ClientGlobals;
import client.event.EventBlockCollision;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import net.minecraft.block.BlockAir;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class FlyVulcanMode extends FlyModeHandler {
   public double value = 0.0;
   public float factor = 1.0F;
   public final TimeHelper timer = new TimeHelper();
   public int count2 = 0;

   @Override
   public void onPacket(EventPacket packet) {
      if (packet.getPacket() instanceof S08PacketPlayerPosLook) {
         packet.cancel();
         this.count2++;
      }
   }

   @Override
   public void onBlockCollision(EventBlockCollision event) {
      if (event.getBlock() instanceof BlockAir && (double)event.getBlockPos().getY() < this.value) {
         BlockPos blockPos = event.getBlockPos();
         AxisAlignedBB boundingBox = new AxisAlignedBB(
            (double)blockPos.getX(),
            (double)blockPos.getY(),
            (double)blockPos.getZ(),
            (double)(blockPos.getX() + 1),
            (double)blockPos.getY() + ClientGlobals.moduleManager.fly.settingVulcanCollisionY.getValue(),
            (double)(blockPos.getZ() + 1)
         );
         event.setBoundingBox(boundingBox);
      }
   }

   @Override
   public void processChar() {
   }

   @Override
   public void onMove(EventMove event) {
      if (this.count2 > 3) {
         if (super.fly.settingTimer.isEnabled()) {
            MinecraftAccessor.minecraft.getTimer().scaleFactor2 = this.factor;
         }

         if (MinecraftAccessor.minecraft
            .theWorld
            .getBlockState(
               new BlockPos(MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY, MinecraftAccessor.minecraft.thePlayer.posZ)
                  .down()
            )
            .getBlock()
            .isState18()) {
            if (!this.timer.hasReached(500L)) {
               this.factor = (float)super.fly.settingTimerSpeedEnd.getValue();
            }

            event.setState4(true);
         } else {
            this.timer.reset();
            this.count2 = 4;
         }
      } else {
         this.timer.reset();
      }
   }

   @Override
   public void updateState2() {
      this.value = MinecraftAccessor.minecraft.thePlayer.posY - 0.5;
      this.timer.reset();
      this.count2 = 0;
      this.factor = (float)super.fly.settingTimerSpeed.getValue();
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   public FlyVulcanMode(String text, Fly fly) {
      super(text, fly);
   }
}
