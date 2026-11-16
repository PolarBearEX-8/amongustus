package augustus.modes.movement;

import augustus.modules.combat.Velocity;
import client.event.UpdateStateListener;
import client.setting.ActionSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InternalStringArrayEntry;
import java.util.function.BiFunction;

public class JesusPushgroundMode extends InternalStringArrayEntry implements UpdateStateListener {
   public final ActionSetting actionSetting;

   @Override
   public void updateState() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new JesusPushgroundMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 43;
      this.applyIndex(byteValue);
   }

   @Override
   public void onPostUpdateState() {
      if (this.isActive()) {
         if ((double)minecraft.thePlayer.hurtTime >= this.actionSetting.getDouble3() && (double)minecraft.thePlayer.hurtTime <= this.actionSetting.getDouble6()) {
            minecraft.thePlayer.onGround = true;
         }
      }
   }

   public JesusPushgroundMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "PushGround");
      ActionSetting actionSetting2 = (new ActionSetting(this.module, "EndOnGroundPush", "StartOnGroundPush", 1.0, 10.0, 1.0, 10.0, 0, this.settingGroup))
         .getActionSetting();
      String text = "EndHurtTime";
      String text2 = "StartHurtTime";
      actionSetting2 = actionSetting2.getActionSetting(text2, text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.actionSetting = actionSetting2.getActionSetting(booleanSupplier);
   }
}
