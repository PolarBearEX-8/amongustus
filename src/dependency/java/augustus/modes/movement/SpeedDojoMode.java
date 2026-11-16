package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedDojoMode extends SpeedModeHandler {
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
      if (MoveUtil.canExecute()) {
         if (MinecraftAccessor.minecraft.thePlayer.onGround) {
            MinecraftAccessor.minecraft.thePlayer.closeScreen();
            MoveUtil.processFloat((float)(ClientGlobals.moduleManager.speed.settingDojoSpeed.getValue() * MoveUtil.getValue2()));
         } else {
            MoveUtil.processFloat((float)MoveUtil.getValue2());
         }
      }
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

   public SpeedDojoMode(String text, Speed speed) {
      super(text, speed);
   }
}
