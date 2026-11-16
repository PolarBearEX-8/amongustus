package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedSentryMode extends SpeedModeHandler {
   public int count2 = 0;

   @Override
   public void onMove(EventMove event) {
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void processAction() {
      MoveUtil.updateState();
   }

   @Override
   public void processChar() {
      if (MoveUtil.canExecute()) {
         this.count2++;
         if (MinecraftAccessor.minecraft.thePlayer.onGround) {
            MinecraftAccessor.minecraft.thePlayer.motionY = 0.4;
            MoveUtil.processAction(0.2, false);
            this.count2 = 0;
         }
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (event.getCoordOffset1() < 0.0) {
         double offset = event.getCoordOffset1() - 0.08F;
         event.setCoordOffset1(offset);
      }
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedSentryMode(String text, Speed speed) {
      super(text, speed);
   }
}
