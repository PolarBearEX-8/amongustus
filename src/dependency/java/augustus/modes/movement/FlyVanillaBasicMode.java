package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;

public class FlyVanillaBasicMode extends FlyModeHandler {
   public float factor;
   public boolean enabled = false;

   @Override
   public void processChar() {
      if (MinecraftAccessor.minecraft.thePlayer.hurtTime != 0) {
         this.enabled = true;
      }

      if (this.enabled || !ClientGlobals.moduleManager.fly.settingWaitForDamage.isEnabled()) {
         MinecraftAccessor.minecraft.thePlayer.invulnerableManager.state = true;
         MinecraftAccessor.minecraft.thePlayer.invulnerableManager.setFactor((float)super.fly.settingVanillaSpeed.getValue() / 4.0F);
      }
   }

   @Override
   public void updateState2() {
      this.factor = MinecraftAccessor.minecraft.thePlayer.invulnerableManager.getFactor();
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
      MinecraftAccessor.minecraft.thePlayer.motionX = 0.0;
      MinecraftAccessor.minecraft.thePlayer.motionZ = 0.0;
      MinecraftAccessor.minecraft.thePlayer.invulnerableManager.setFactor(this.factor);
      MinecraftAccessor.minecraft.thePlayer.invulnerableManager.state = false;
   }

   public FlyVanillaBasicMode(String text, Fly fly) {
      super(text, fly);
   }
}
