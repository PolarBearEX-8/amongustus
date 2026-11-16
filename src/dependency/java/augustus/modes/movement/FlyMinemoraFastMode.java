package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;

public class FlyMinemoraFastMode extends FlyModeHandler {
   @Override
   public void processChar() {
      if (MinecraftAccessor.minecraft.thePlayer.motionY > 0.0) {
         MinecraftAccessor.minecraft.thePlayer.motionY += 0.02F;
      }

      if (MinecraftAccessor.minecraft.thePlayer.ticksExisted % 4 == 0 && MinecraftAccessor.minecraft.thePlayer.motionY < 0.0) {
         MinecraftAccessor.minecraft.thePlayer.motionY = 0.02;
         MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 0.6F;
      } else {
         MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 0.8F;
      }
   }

   @Override
   public void updateState2() {
      if (MinecraftAccessor.minecraft.thePlayer.onGround) {
         MinecraftAccessor.minecraft.thePlayer.closeScreen();
      }
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
   }

   public FlyMinemoraFastMode(String text, Fly fly) {
      super(text, fly);
   }
}
