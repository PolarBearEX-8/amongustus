package client.util;

import augustus.modules.movement.LongJump;
import client.event.EventMove;
import client.event.MoveListener;
import client.setting.ModeOptionSupplier;
import client.setting.SettingGroup;

public abstract class AuxiliaryMoveListenerAdapter extends ModeOptionSupplier implements MoveListener {
   @Override
   public void onMove(EventMove event) {
      float factor = 1.0F;
      event.setScaleFactor7(factor);
   }

   @Override
   public void updateState2() {
      byte byteValue = 21;
      this.applyIndex(byteValue);
   }

   public AuxiliaryMoveListenerAdapter(LongJump longJump, SettingGroup settingGroup, String text) {
      super(longJump, settingGroup, text);
   }
}
