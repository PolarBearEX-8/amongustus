package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventRotation;
import client.setting.SettingContainer;
import client.util.MoveUtil;
import net.minecraft.potion.Potion;

public class SpeedOldHypixelMode extends SpeedModeHandler {
   @Override
   public void onMove(EventMove event) {
      if (MoveUtil.canExecute()) {
         event.setState(true);
      }
   }

   @Override
   public void onRotation(EventRotation event) {
      super.onRotation(event);
      if (MoveUtil.canExecute() && minecraft.thePlayer.getPotionEffect(Potion.potion2) != null) {
         double value = MoveUtil.getValue2();
         double offset = 0.1;
         double offset2 = 0.09;
         MoveUtil.processFloat2((float)(value + (double)SettingContainer.settingProvider.getFloat3(offset2, offset)), false);
      }
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
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (minecraft.thePlayer.getPotionEffect(Potion.potion2) != null) {
         double offset = 0.01;
         double offset2 = 0.009;
         double[] offsetArray = MoveUtil.resolveDoubleArray((double)SettingContainer.settingProvider.getFloat3(offset2, offset), MoveUtil.computeFactor(false));
         double offset3 = event.getCoordOffset2() + offsetArray[0];
         event.setCoordOffset2(offset3);
         double offset4 = event.getCoordOffset() + offsetArray[1];
         event.setCoordOffset(offset4);
      }
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedOldHypixelMode(String text, Speed speed) {
      super(text, speed);
   }
}
