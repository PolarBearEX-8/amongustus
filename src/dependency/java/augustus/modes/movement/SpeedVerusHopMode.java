package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class SpeedVerusHopMode extends SpeedModeHandler {
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
      double offset = 0.292F;
      MoveUtil.applyDoubleByDouble(offset);
      if (super.speed.settingDamageBoost.isEnabled()
         && MinecraftAccessor.minecraft.thePlayer.hurtTime != 0
         && MinecraftAccessor.minecraft.thePlayer.fallDistance < 3.0F) {
         MoveUtil.applyDoubleByDouble((double)((float)super.speed.settingDMGSpeed.getValue()));
      } else {
         double offset2 = 0.292F;
         MoveUtil.applyDoubleByDouble(offset2);
      }

      if (this.isActive()) {
         MinecraftAccessor.minecraft.thePlayer.closeScreen();
      } else {
         MinecraftAccessor.minecraft.thePlayer.scaleFactor10 = 0.1F;
      }
   }

   public SpeedVerusHopMode(String text, Speed speed) {
      super(text, speed);
   }
}
