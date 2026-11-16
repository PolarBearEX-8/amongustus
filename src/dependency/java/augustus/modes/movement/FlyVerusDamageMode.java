package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class FlyVerusDamageMode extends FlyGroundCollisionMode {
   @Override
   public void processChar() {
   }

   @Override
   public void updateState2() {
      super.value = MinecraftAccessor.minecraft.thePlayer.posY - 0.5;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (MoveUtil.canExecute() && !MinecraftAccessor.minecraft.thePlayer.onGround) {
         MoveUtil.processAction(0.0083, true);
      }

      MoveUtil.updateState();
   }

   @Override
   public void updateState3() {
   }

   public FlyVerusDamageMode(String text, Fly fly) {
      super(text, fly);
   }
}
