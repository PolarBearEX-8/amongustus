package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedIntaveMode extends SpeedModeHandler {
   @Override
   public void onMove(EventMove event) {
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void processAction() {
      if (MinecraftAccessor.minecraft.thePlayer.onGround && MoveUtil.canExecute()) {
         if (MinecraftAccessor.minecraft.thePlayer.hurtTime == 0) {
            MoveUtil.processAction(0.22, false);
         }

         MinecraftAccessor.minecraft.thePlayer.motionY = 0.35F;
      }
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
   }

   public SpeedIntaveMode(String text, Speed speed) {
      super(text, speed);
   }
}
