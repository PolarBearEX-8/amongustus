package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.CombatUtil;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class FlyVerusCombinedMode extends FlyModeHandler {
   public boolean enabled2 = false;
   public boolean enabled = false;

   @Override
   public void processChar() {
      if (!this.enabled && MinecraftAccessor.minecraft.thePlayer.onGround && MoveUtil.canExecute2()) {
         CombatUtil.updateState3();
         this.enabled = true;
      }

      if (MinecraftAccessor.minecraft.thePlayer.hurtTime != 0) {
         this.enabled2 = true;
      }

      if (!this.enabled2) {
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

      if (this.enabled2) {
         if (MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed) {
            MinecraftAccessor.minecraft.thePlayer.motionY = 1.5;
         } else if (MinecraftAccessor.minecraft.gameSettings.keyBindSneak.pressed) {
            MinecraftAccessor.minecraft.thePlayer.motionY = -1.5;
         } else {
            MinecraftAccessor.minecraft.thePlayer.motionY = 0.0;
         }
      }
   }

   @Override
   public void updateState2() {
      this.enabled2 = false;
      this.enabled = false;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (this.enabled2) {
         MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 0.3F;
         MoveUtil.processFloat((float)super.fly.settingVerusSpeedCombined.getValue());
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
      MoveUtil.processFloat(0.0F);
   }

   public FlyVerusCombinedMode(String text, Fly fly) {
      super(text, fly);
   }
}
