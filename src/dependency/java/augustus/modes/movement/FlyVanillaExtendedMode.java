package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class FlyVanillaExtendedMode extends FlyModeHandler {
   public boolean enabled = false;

   @Override
   public void processChar() {
   }

   @Override
   public void updateState2() {
      MinecraftAccessor.minecraft.thePlayer.invulnerableManager.state = false;
      this.enabled = false;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (MinecraftAccessor.minecraft.thePlayer.hurtTime != 0) {
         this.enabled = true;
      }

      if (this.enabled || !ClientGlobals.moduleManager.fly.settingWaitForDamage.isEnabled()) {
         if (MoveUtil.canExecute()) {
            MoveUtil.processFloat((float)(0.2 * super.fly.settingVanillaSpeed.getValue()));
         } else {
            MoveUtil.processFloat(0.0F);
         }

         if (!MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed && !MinecraftAccessor.minecraft.gameSettings.keyBindSneak.pressed) {
            MinecraftAccessor.minecraft.thePlayer.motionY = 0.0;
         } else {
            MinecraftAccessor.minecraft.thePlayer.motionY = MinecraftAccessor.minecraft.gameSettings.keyBindSneak.pressed
               ? -0.2 * super.fly.settingVanillaSpeed.getValue()
               : 0.2 * super.fly.settingVanillaSpeed.getValue();
         }
      }
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
      MinecraftAccessor.minecraft.thePlayer.motionX = 0.0;
      MinecraftAccessor.minecraft.thePlayer.motionZ = 0.0;
      MinecraftAccessor.minecraft.thePlayer.motionY = 0.0;
      MinecraftAccessor.minecraft.thePlayer.invulnerableManager.state = false;
   }

   public FlyVanillaExtendedMode(String text, Fly fly) {
      super(text, fly);
   }
}
