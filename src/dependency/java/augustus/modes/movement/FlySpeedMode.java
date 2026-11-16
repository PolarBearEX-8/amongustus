package augustus.modes.movement;

import augustus.modules.movement.Jesus;
import client.event.TickListener;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.CoreStringEntry;

public class FlySpeedMode extends CoreStringEntry implements TickListener {
   public final NumberSetting settingSpeed;
   public final BooleanSetting settingModifyMotionY;

   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 40;
      this.applyIndex(byteValue);
   }

   @Override
   public void onRunTick() {
      if (this.isActive()) {
         if (minecraft.thePlayer.isInWater()) {
            if (!minecraft.gameSettings.keyBindJump.isKeyDown() && !minecraft.gameSettings.keyBindSneak.isKeyDown() && this.settingModifyMotionY.isEnabled()) {
               minecraft.thePlayer.motionY = 0.0;
            }

            minecraft.thePlayer.applyFloat(minecraft.thePlayer.scaleFactor24, minecraft.thePlayer.scaleFactor3, (float)(this.settingSpeed.getValue() / 100.0));
         }
      }
   }

   public FlySpeedMode(Jesus jesus, SettingGroup settingGroup) {
        super(jesus, settingGroup, "Speed");
      
      NumberSetting setting = new NumberSetting(this.module, "SpeedSpeed", 1.0, 0.0, 20.0, 2, this.settingGroup);
      String text = "Speed";
      setting = setting.getNumberSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingSpeed = setting.getNumberSetting(booleanSupplier);
      BooleanSetting setting2 = new BooleanSetting(this.module, "SpeedModifyMotionY", true, this.settingGroup);
      String text2 = "ModifyMotionY";
      BooleanSetting setting3 = (BooleanSetting)setting2.getSetting(text2);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingModifyMotionY = (BooleanSetting)setting3.getSetting(booleanSupplier2);
   }
}
