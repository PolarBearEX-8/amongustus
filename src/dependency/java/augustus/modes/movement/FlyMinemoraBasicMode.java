package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;

public class FlyMinemoraBasicMode extends FlyModeHandler {
   @Override
   public void processChar() {
      MinecraftAccessor.minecraft.thePlayer.motionY += 0.02F;
      if (MinecraftAccessor.minecraft.thePlayer.motionY < -0.16) {
         MinecraftAccessor.minecraft.thePlayer.motionY = 0.0;
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

   public FlyMinemoraBasicMode(String text, Fly fly) {
      super(text, fly);
   }
}
