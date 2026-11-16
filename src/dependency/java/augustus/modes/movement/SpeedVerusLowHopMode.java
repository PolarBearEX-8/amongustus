package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.module.Module;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedVerusLowHopMode extends SpeedModeHandler {
   public boolean enabled = false;

   @Override
   public void onMove(EventMove event) {
      if (MoveUtil.canExecute()) {
         event.setState(false);
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
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (MoveUtil.canExecute()) {
         if (((Module)ClientGlobals.moduleManager.step).isToggled()
            && (!ClientGlobals.moduleManager.step.timer2.hasReached(500L) || MinecraftAccessor.minecraft.thePlayer.isCollidedHorizontally)) {
            return;
         }

         if (MinecraftAccessor.minecraft.thePlayer.onGround) {
            MinecraftAccessor.minecraft.thePlayer.closeScreen();
            this.enabled = true;
         } else if (this.enabled) {
            if (!MinecraftAccessor.minecraft.thePlayer.isCollidedHorizontally) {
               MinecraftAccessor.minecraft.thePlayer.motionY = -0.0784000015258789;
            }

            this.enabled = false;
         }

         MoveUtil.applyDouble(0.34);
      } else {
         MinecraftAccessor.minecraft.thePlayer.motionX = MinecraftAccessor.minecraft.thePlayer.motionZ = 0.0;
      }
   }

   public SpeedVerusLowHopMode(String text, Speed speed) {
      super(text, speed);
   }
}
