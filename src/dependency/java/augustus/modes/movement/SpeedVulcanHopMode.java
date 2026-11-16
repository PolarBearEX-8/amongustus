package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedVulcanHopMode extends SpeedModeHandler {
   public int count2 = 0;

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
      if (MinecraftAccessor.minecraft.thePlayer.onGround) {
         if (MoveUtil.canExecute()) {
            MinecraftAccessor.minecraft.thePlayer.closeScreen();
            MoveUtil.applyDouble(MoveUtil.getValue() * 1.6);
            this.count2 = 0;
         }
      } else {
         this.count2++;
         if (this.count2 == 1) {
            MoveUtil.applyDouble(MoveUtil.getValue() * 1.16);
         }
      }
   }

   public SpeedVulcanHopMode(String text, Speed speed) {
      super(text, speed);
   }
}
