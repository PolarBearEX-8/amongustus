package client.util;

import client.setting.BooleanSettingProperty;
import client.setting.RangeSetting;
import net.minecraft.entity.EntityLivingBaseEntry;

public class InternalStringEntryRegistry extends SmoothWriter {
   public RangeSetting settingDelay;

   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      return entityLivingBaseEntry.value3;
   }

   public RangeSetting getSettingDelay() {
      return this.settingDelay;
   }

   public InternalStringEntryRegistry(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "Future", true);
      RangeSetting setting = new RangeSetting(booleanSettingProperty.getModule(), "UNoise3FutureDelay", 0.0, 0.0, 0.0, 3.0, 0, this.getSetting());
      String text = "Delay";
      this.settingDelay = setting.getRangeSetting(text);
   }
}
