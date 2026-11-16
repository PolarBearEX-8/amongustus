package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventRotation;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedOldMatrixMode extends SpeedModeHandler {
   @Override
   public void onMove(EventMove event) {
      if (MoveUtil.canExecute()) {
         event.setState(true);
      }
   }

   @Override
   public void onRotation(EventRotation event) {
      if (MoveUtil.canExecute()) {
         MoveUtil.processAction(0.02, false);
      } else {
         MoveUtil.processAction(0.002, false);
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
      if (MinecraftAccessor.minecraft.thePlayer.motionX != 0.0 || MinecraftAccessor.minecraft.thePlayer.motionZ != 0.0) {
         if (!MoveUtil.canExecute()) {
            MinecraftAccessor.minecraft.thePlayer.motionY -= 0.009999F;
            if (MinecraftAccessor.minecraft.thePlayer.onGround) {
               MoveUtil.processAction();
            } else if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveForward > 0.0F
               && MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveStrafe != 0.0F) {
               MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.02F);
            } else {
               MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.0208F);
            }
         }
      }
   }

   public SpeedOldMatrixMode(String text, Speed speed) {
      super(text, speed);
   }
}
