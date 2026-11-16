package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MoveUtil;

public class FlyBedwarsPracticeMode extends FlyModeHandler {
   @Override
   public void processChar() {
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      minecraft.thePlayer.motionY = 0.0;
      MoveUtil.processFloat(0.3F);
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
   }

   public FlyBedwarsPracticeMode(String text, Fly fly) {
      super(text, fly);
   }
}
