package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;

public class SpeedKarhuMode extends SpeedModeHandler {
   @Override
   public void onMove(EventMove event) {
      event.setState(false);
   }

   @Override
   public void updateState() {
      if (MinecraftAccessor.minecraft.thePlayer.onGround) {
         MinecraftAccessor.minecraft.thePlayer.closeScreen();
         MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.009F;
      } else if (MinecraftAccessor.minecraft.thePlayer.motionY > 0.2101) {
         MinecraftAccessor.minecraft.thePlayer.motionY *= 0.87F;
      }
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
   public void updateState3() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedKarhuMode(String text, Speed speed) {
      super(text, speed);
   }
}
