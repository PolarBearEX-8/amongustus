package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.CombatUtil;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class FlyOldVerusMode extends FlyModeHandler {
   public boolean enabled2 = false;
   public boolean enabled = false;

   @Override
   public void processChar() {
      if (!this.enabled && (MinecraftAccessor.minecraft.thePlayer.onGround || !ClientGlobals.moduleManager.fly.settingOnlyOnGround.isEnabled())) {
         CombatUtil.updateState3();
         this.enabled = true;
      }

      if (MinecraftAccessor.minecraft.thePlayer.hurtTime != 0) {
         this.enabled2 = true;
      }

      if (!this.enabled2 && this.enabled) {
         MinecraftAccessor.minecraft.thePlayer.motionZ = 0.0;
         MinecraftAccessor.minecraft.thePlayer.motionX = 0.0;
         MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed = false;
      }

      if (this.enabled2) {
         MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 0.3F;
         if (MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed) {
            MinecraftAccessor.minecraft.thePlayer.motionY = 1.5;
         } else if (MinecraftAccessor.minecraft.gameSettings.keyBindSneak.pressed) {
            MinecraftAccessor.minecraft.thePlayer.motionY = -1.5;
         } else {
            MinecraftAccessor.minecraft.thePlayer.motionY = 0.0;
         }

         MinecraftAccessor.minecraft.thePlayer.onGround = true;
         MoveUtil.processFloat((float)super.fly.settingVerusSpeed.getValue());
      }
   }

   @Override
   public void updateState2() {
      this.enabled2 = false;
      this.enabled = false;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
      MoveUtil.processFloat(0.0F);
   }

   public FlyOldVerusMode(String text, Fly fly) {
      super(text, fly);
   }
}
