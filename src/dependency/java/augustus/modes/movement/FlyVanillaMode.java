package augustus.modes.movement;

import augustus.modules.movement.HighJump;
import client.event.EventMotionUpdate;
import client.event.MotionUpdateListener;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BaseUpdateStateListenerAdapter;
import client.util.BooleanSupplier;
import java.util.function.BiFunction;

public class FlyVanillaMode extends BaseUpdateStateListenerAdapter implements MotionUpdateListener {
   public final NumberSetting settingHeight;

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (minecraft.thePlayer.onGround) {
         minecraft.thePlayer.motionY = this.settingHeight.getValue();
      }
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new FlyVanillaMode((HighJump)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 18;
      this.applyIndex(byteValue);
   }

   public FlyVanillaMode(HighJump highJump, SettingGroup settingGroup) {
      super(highJump, settingGroup, "Vanilla");
      NumberSetting setting = new NumberSetting(this.module, "VanillaHeight", 1.0, 0.42, 5.0, 2, this.settingGroup);
      String text = "Height";
      setting = setting.getNumberSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingHeight = setting.getNumberSetting(booleanSupplier);
   }
}
