package client.setting;

import client.util.SmoothWriter;
import net.minecraft.entity.EntityLivingBaseEntry;

public class AuxiliaryRangeSettingEntry extends SmoothWriter {
   public RangeSetting settingDelay;

   public RangeSetting getSettingDelay() {
      return this.settingDelay;
   }

   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      return entityLivingBaseEntry.value4;
   }

   public AuxiliaryRangeSettingEntry(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "Noob", true);
      RangeSetting setting = new RangeSetting(booleanSettingProperty.getModule(), this.getSetting().getString() + "_delay", 0.0, 0.0, 0.0, 3.0, 0, this.getSetting());
      String text = "Delay";
      this.settingDelay = setting.getRangeSetting(text);
   }
}
