package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventRotation;
import client.util.KeyUtils;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedOldMinemoraMode extends SpeedModeHandler {
   public int count2 = 0;

   @Override
   public void onMove(EventMove event) {
      if (MoveUtil.canExecute()) {
         event.setState(true);
      }
   }

   @Override
   public void onRotation(EventRotation event) {
      this.count2++;
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
      this.count2 = 0;
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveStrafe == 0.0F
         && this.count2 > 1
         && KeyUtils.checkCondition(MinecraftAccessor.minecraft.gameSettings.keyBindJump.getKeyCode())) {
         MinecraftAccessor.minecraft.thePlayer.motionX *= 1.02;
         MinecraftAccessor.minecraft.thePlayer.motionZ *= 1.02;
      }

      double offset = event.getCoordOffset1() - 0.01;
      event.setCoordOffset1(offset);
      double offset2 = event.getCoordOffset2() * 1.0123F;
      event.setCoordOffset2(offset2);
      double offset3 = event.getCoordOffset() * 1.0123F;
      event.setCoordOffset(offset3);
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (MinecraftAccessor.minecraft.thePlayer.isCollidedHorizontally) {
         this.count2 = 0;
      } else if (!MinecraftAccessor.minecraft.thePlayer.onGround) {
         MinecraftAccessor.minecraft.thePlayer.motionY -= 0.02;
      }
   }

   public SpeedOldMinemoraMode(String text, Speed speed) {
      super(text, speed);
   }
}
