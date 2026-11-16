package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class FlyCubeCraftMode extends FlyModeHandler {
   public int count2 = 0;
   public double value = 0.0;

   @Override
   public void processChar() {
      if (MoveUtil.canExecute()) {
         if ((double)this.count2 % super.fly.settingCubeCraftStopDuration.getValue() == 0.0) {
            MoveUtil.processFloat((float)super.fly.settingCubeCraftSpeed.getValue());
            if (MinecraftAccessor.minecraft.thePlayer.posY < this.value) {
               MinecraftAccessor.minecraft.thePlayer.motionY = 0.2;
            }
         } else {
            MoveUtil.processFloat(0.0F);
         }
      }
   }

   @Override
   public void updateState2() {
      this.value = MinecraftAccessor.minecraft.thePlayer.posY;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed) {
         if ((double)this.count2 % super.fly.settingCubeCraftStopDuration.getValue() == 0.0) {
            double offset = event.getCoordOffset1() + 2.0;
            event.setCoordOffset1(offset);
            this.value += 2.0;
         }
      } else if (MinecraftAccessor.minecraft.gameSettings.keyBindSneak.pressed) {
         if ((double)this.count2 % super.fly.settingCubeCraftStopDuration.getValue() == 0.0) {
            double offset2 = event.getCoordOffset1() - 2.0;
            event.setCoordOffset1(offset2);
            this.value -= 2.0;
         }
      } else if (!MoveUtil.canExecute()) {
         double offset3 = 0.0;
         event.setCoordOffset1(offset3);
      }

      if (!MoveUtil.canExecute()) {
         double offset4 = 0.0;
         event.setCoordOffset2(offset4);
         double offset5 = 0.0;
         event.setCoordOffset(offset5);
      }

      this.count2++;
   }

   @Override
   public void updateState3() {
      MinecraftAccessor.minecraft.thePlayer.motionX = 0.0;
      MinecraftAccessor.minecraft.thePlayer.motionZ = 0.0;
   }

   public FlyCubeCraftMode(String text, Fly fly) {
      super(text, fly);
   }
}
