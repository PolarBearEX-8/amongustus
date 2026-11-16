package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedOldMineplexMode extends SpeedModeHandler {
   public int count2;
   public int count3;

   @Override
   public void onMove(EventMove event) {
   }

   @Override
   public void updateState2() {
      this.count3 = -2;
      this.count2 = 0;
   }

   @Override
   public void processAction() {
   }

   @Override
   public void processChar() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
      this.count3 = -2;
      this.count2 = 0;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (this.isActive()) {
         if (this.count3 < 0) {
            this.count3++;
         }

         if (MinecraftAccessor.minecraft.thePlayer.posY != (double)((int)MinecraftAccessor.minecraft.thePlayer.posY)) {
            this.count3 = -2;
         }

         MinecraftAccessor.minecraft.thePlayer.closeScreen();
         this.count2 = 0;
         MoveUtil.processDouble2(-0.2F, 0.0, MoveUtil.getFloat2(), true);
         MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.0F;
      } else if (this.isActiveAsFlag()) {
         if (MinecraftAccessor.minecraft.thePlayer.isCollidedHorizontally) {
            this.count3 = -2;
            return;
         }

         this.count2++;
         if (MinecraftAccessor.minecraft.thePlayer.motionY < -0.4) {
            return;
         }

         if (MinecraftAccessor.minecraft.thePlayer.motionY < 0.0) {
            MinecraftAccessor.minecraft.thePlayer.motionY += 0.033;
         }

         if (MinecraftAccessor.minecraft.thePlayer.motionY > 0.0) {
            MinecraftAccessor.minecraft.thePlayer.motionY += 0.03;
         }

         if (MinecraftAccessor.minecraft.thePlayer.scaleFactor24 == 0.0F) {
            MoveUtil.processDouble2((double)(0.61F - (float)this.count2 / 100.0F + (float)this.count3 / 7.0F), 0.0, MoveUtil.getFloat2(), true);
         } else {
            this.count3 = this.count2 < 1 ? this.count2 : 0;
            MoveUtil.processDouble2((double)(0.61F - (float)this.count2 / 100.0F + (float)this.count3 / 7.0F), 0.0, MoveUtil.getFloat2(), true);
         }
      }
   }

   public SpeedOldMineplexMode(String text, Speed speed) {
      super(text, speed);
   }
}
