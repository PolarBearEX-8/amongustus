package augustus.modes.movement;

import augustus.modules.movement.FastLadder;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.MinecraftAccessor;
import client.util.SpecialAbstractEventAdapter;
import client.util.VectorSorter;
import net.minecraft.item.SecondaryItemCallback;

public class SpeedMotionMode extends VectorSorter implements SecondaryItemCallback {
   public final NumberSetting settingMotionY;
   public final NumberSetting settingTickDelay;

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
      if ((double)MinecraftAccessor.minecraft.thePlayer.ticksExisted % (this.settingTickDelay.getValue() + 1.0) == 0.0) {
         double value = this.settingMotionY.getValue();
         event.setValue(value);
      }
   }

   public SpeedMotionMode(FastLadder fastLadder, SettingGroup settingGroup) {
      super(fastLadder, settingGroup, "Motion");
      NumberSetting setting = new NumberSetting(super.module, "MotionMotionY", 0.4, 0.2, 2.0, 3, super.settingGroup);
      String text = "MotionY";
      setting = setting.getNumberSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingMotionY = setting.getNumberSetting(booleanSupplier);
      setting = new NumberSetting(super.module, "MotionTickDelay", 0.0, 0.0, 20.0, 0, super.settingGroup);
      String text2 = "TickDelay";
      setting = setting.getNumberSetting(text2);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingTickDelay = setting.getNumberSetting(booleanSupplier2);
   }
}
