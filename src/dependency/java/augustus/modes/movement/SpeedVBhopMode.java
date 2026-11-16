package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedVBhopMode extends SpeedModeHandler {
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
      if (this.isActive()) {
         MinecraftAccessor.minecraft.thePlayer.motionY = (double)MinecraftAccessor.minecraft.thePlayer.getFloat6() * super.speed.settingHeight.getValue();
         if (super.speed.settingStrafe.isEnabled()) {
            MoveUtil.updateState();
         }
      } else if (this.isActiveAsFlag()) {
         MoveUtil.processFloat2((float)(0.1 * super.speed.settingVanillaSpeed.getValue()), super.speed.settingStrafe.isEnabled());
      }
   }

   public SpeedVBhopMode(String text, Speed speed) {
      super(text, speed);
   }
}
