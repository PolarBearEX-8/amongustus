package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventSlowDown;
import client.module.Module;
import client.util.RayTraceUtil;

public class SpeedTimeManipulationMode extends SpeedModeHandler {
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
   public void updateState3() {
      if (!((Module)ClientGlobals.moduleManager.timeManipulation).isToggled()) {
         RayTraceUtil.applyString("Please activate module Timemanipulation");
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onSlowDown(EventSlowDown event) {
      float factor = Math.max(0.1F, (float)ClientGlobals.moduleManager.speed.settingTimeManipulationTimer.getValue());
      event.setFactor(factor);
      int value = (int)ClientGlobals.moduleManager.speed.settingTimeManipulationTPTicks.getValue();
      event.processAction(value);
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedTimeManipulationMode(String text, Speed speed) {
      super(text, speed);
   }
}
