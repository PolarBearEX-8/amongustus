package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class FlyVerusBasicMode extends FlyModeHandler {
   public double value;

   @Override
   public void processChar() {
      if (MinecraftAccessor.minecraft.thePlayer.onGround) {
         this.value = MinecraftAccessor.minecraft.thePlayer.posY;
         MinecraftAccessor.minecraft.thePlayer.closeScreen();
      }

      if (MinecraftAccessor.minecraft.thePlayer.posY > this.value + 0.76F) {
         if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveForward > 0.0F) {
            MoveUtil.applyDouble(0.35, 45.0, MoveUtil.getFloat2());
         }

         MinecraftAccessor.minecraft.thePlayer.motionY = 0.42F;
         this.value = MinecraftAccessor.minecraft.thePlayer.posY;
      }
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
   }

   public FlyVerusBasicMode(String text, Fly fly) {
      super(text, fly);
   }
}
