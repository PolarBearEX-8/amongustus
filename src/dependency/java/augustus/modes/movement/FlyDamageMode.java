package augustus.modes.movement;

import augustus.modules.movement.HighJump;
import client.event.EventMotionUpdate;
import client.event.MotionUpdateListener;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BaseUpdateStateListenerAdapter;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import java.util.function.BiFunction;

public class FlyDamageMode extends BaseUpdateStateListenerAdapter implements MotionUpdateListener {
   public final NumberSetting settingHeight;

   @Override
   public void updateState() {
      super.updateState();
      if (minecraft.thePlayer != null) {
         CombatUtil.updateState3();
      }
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (minecraft.thePlayer.onGround && minecraft.thePlayer.hurtTime != 0) {
         minecraft.thePlayer.motionY = this.settingHeight.getValue();
         this.count2 = 1;
      } else {
         this.count2 = 0;
      }
   }

   @Override
   public void clear() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new FlyDamageMode((HighJump)item, (SettingGroup)group));
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 18;
      this.applyIndex(byteValue);
   }

   public FlyDamageMode(HighJump highJump, SettingGroup settingGroup) {
      super(highJump, settingGroup, "Damage");
      NumberSetting setting = new NumberSetting(this.module, "DamageHeight", 1.0, 0.42, 5.0, 2, this.settingGroup);
      String text = "Height";
      setting = setting.getNumberSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingHeight = setting.getNumberSetting(booleanSupplier);
   }
}
