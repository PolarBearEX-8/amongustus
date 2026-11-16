package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedOldNCPMode extends SpeedModeHandler {
   @Override
   public void onMove(EventMove event) {
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void processAction() {
   }

   @Override
   public void processChar() {
      if (MoveUtil.canExecute()) {
         if (MinecraftAccessor.minecraft.thePlayer.onGround) {
            MinecraftAccessor.minecraft.thePlayer.closeScreen();
            MinecraftAccessor.minecraft.thePlayer.motionX *= 1.01;
            MinecraftAccessor.minecraft.thePlayer.motionZ *= 1.01;
            MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.022F);
         }

         MinecraftAccessor.minecraft.thePlayer.motionY -= 9.9999E-4;
         MoveUtil.updateState();
      } else {
         MinecraftAccessor.minecraft.thePlayer.motionX = 0.0;
         MinecraftAccessor.minecraft.thePlayer.motionZ = 0.0;
      }

      MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.0865F;
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedOldNCPMode(String text, Speed speed) {
      super(text, speed);
   }
}
