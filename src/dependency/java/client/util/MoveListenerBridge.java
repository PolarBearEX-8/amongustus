package client.util;

import augustus.modules.movement.Jesus;
import client.event.EventMove;
import client.event.MoveListener;
import client.setting.SettingGroup;

public class MoveListenerBridge extends CoreStringEntry implements MoveListener {
   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   @Override
   public void onMove(EventMove event) {
      if ((minecraft.thePlayer.isInWater() || minecraft.thePlayer.isWet()) && !minecraft.gameSettings.keyBindSneak.isKeyDown()) {
         event.setState(true);
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 21;
      this.applyIndex(byteValue);
   }

   public MoveListenerBridge(Jesus jesus, SettingGroup settingGroup) {
      super(jesus, settingGroup, "Dolphin");
   }
}
