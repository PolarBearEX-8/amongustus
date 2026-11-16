package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MoveUtil;

public class SpeedVOnGroundMode extends SpeedModeHandler {
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
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (this.isActiveAsFlag()) {
         MoveUtil.processFloat2((float)(0.1 * this.speed.settingVanillaSpeed.getValue()), this.speed.settingStrafe.isEnabled());
      }
   }

   public SpeedVOnGroundMode(String text, Speed speed) {
      super(text, speed);
   }
}
