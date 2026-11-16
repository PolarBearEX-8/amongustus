package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class FlyVerusFastMode extends FlyModeHandler {
   @Override
   public void processChar() {
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      double value = MoveUtil.getValue2();
      if (MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed
         && !MinecraftAccessor.minecraft.thePlayer.isCollidedVertically
         && MinecraftAccessor.minecraft.thePlayer.ticksExisted % 2 == 0) {
         MinecraftAccessor.minecraft.thePlayer.closeScreen();
      }

      if (!MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed && MinecraftAccessor.minecraft.thePlayer.motionY < -0.07840000152) {
         MinecraftAccessor.minecraft.thePlayer.motionY = -0.07840000152;
      }

      if (MoveUtil.canExecute()) {
         double offset = Math.toRadians((double)MinecraftAccessor.minecraft.thePlayer.rotationYaw);
         MinecraftAccessor.minecraft.thePlayer.motionX = -Math.sin(offset) * value;
         MinecraftAccessor.minecraft.thePlayer.motionZ = Math.cos(offset) * value;
         MoveUtil.processAction(0.01, true);
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
   }

   public FlyVerusFastMode(String text, Fly fly) {
      super(text, fly);
   }
}
