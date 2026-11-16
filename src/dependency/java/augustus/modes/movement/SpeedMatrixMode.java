package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedMatrixMode extends SpeedModeHandler {
   @Override
   public void onMove(EventMove event) {
      event.setState(false);
   }

   @Override
   public void updateState2() {
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
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (MoveUtil.canExecute()) {
         if (MinecraftAccessor.minecraft.thePlayer.onGround && MinecraftAccessor.minecraft.thePlayer.motionY < 0.003) {
            MinecraftAccessor.minecraft.thePlayer.closeScreen();
            MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.0F;
         }

         if (MinecraftAccessor.minecraft.thePlayer.motionY > 0.003) {
            MinecraftAccessor.minecraft.thePlayer.motionX *= 1.0012F;
            MinecraftAccessor.minecraft.thePlayer.motionZ *= 1.0012F;
            MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.05F;
         }
      }
   }

   public SpeedMatrixMode(String text, Speed speed) {
      super(text, speed);
   }
}
