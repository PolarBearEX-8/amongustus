package augustus.modes.movement;

import augustus.modules.movement.FastLadder;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.SpecialAbstractEventAdapter;
import client.util.VectorSorter;
import net.minecraft.item.SecondaryItemCallback;

public class SpeedVerusMode extends VectorSorter implements SecondaryItemCallback {
   public final NumberSetting settingMotionY;

   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 15;
      this.applyIndex(byteValue);
   }

   @Override
   public void applySpecialAbstractEventAdapte(SpecialAbstractEventAdapter event) {
      if (minecraft.thePlayer.ticksExisted % 3 != 0) {
         double value = this.settingMotionY.getValue();
         event.setValue(value);
      }
   }

   public SpeedVerusMode(FastLadder fastLadder, SettingGroup settingGroup) {
      super(fastLadder, settingGroup, "Verus");
      NumberSetting setting = new NumberSetting(this.module, "VerusMotionY", 0.5, 0.2, 2.0, 3, this.settingGroup);
      String text = "MotionY";
      setting = setting.getNumberSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingMotionY = setting.getNumberSetting(booleanSupplier);
   }
}
