package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.module.Module;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedCubeCraftMode extends SpeedModeHandler {
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
      if (MoveUtil.canExecute() && ClientGlobals.moduleManager.killAura.target == null && !((Module)ClientGlobals.moduleManager.longJump).isToggled()) {
         if (MinecraftAccessor.minecraft.thePlayer.isUsingItem()) {
            if (MinecraftAccessor.minecraft.thePlayer.isBlocking()) {
               MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.23F;
            } else if (MinecraftAccessor.minecraft.getTimer().scaleFactor2 > 1.0F) {
               MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.0F;
            }
         } else {
            MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.23F;
         }
      } else if (MinecraftAccessor.minecraft.getTimer().scaleFactor2 > 1.0F) {
         MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.0F;
      }
   }

   public SpeedCubeCraftMode(String text, Speed speed) {
      super(text, speed);
   }
}
