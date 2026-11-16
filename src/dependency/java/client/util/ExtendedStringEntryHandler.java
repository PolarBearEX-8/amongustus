package client.util;

import client.setting.BooleanSetting;
import client.setting.BooleanSettingProperty;
import net.minecraft.entity.EntityLivingBaseEntry;
import net.minecraft.util.MathHelper;

public class ExtendedStringEntryHandler extends SmoothWriter {
   public BooleanSetting settingExpandBoundingBox;

   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      double[] valueArray = entityLivingBaseEntry.valueArray;
      if (!this.settingExpandBoundingBox.isEnabled()) {
         valueArray = entityLivingBaseEntry.valueArray3;
      }

      if (entityLivingBaseEntry.enabled) {
         double value = entityLivingBaseEntry.value;
         double offset = TrajectoryPredictor.computeValue(valueArray, value);
         if (!(offset < 0.0) && !(offset > 1.0)) {
            return entityLivingBaseEntry.value;
         } else {
            double offset2 = Math.abs(valueArray[0] - entityLivingBaseEntry.value);
            double offset3 = Math.abs(valueArray[1] - entityLivingBaseEntry.value);
            return offset2 > offset3 ? valueArray[1] : valueArray[0];
         }
      } else {
         double offset4 = TrajectoryPredictor.computeValue2(valueArray[0], valueArray[1], entityLivingBaseEntry.value);
         if (!(offset4 < 0.0) && !(offset4 > 1.0)) {
            return entityLivingBaseEntry.value;
         } else {
            double doubleItem = TrajectoryPredictor.getDouble4(MathHelper.getDouble4(valueArray[0]), entityLivingBaseEntry.value);
            double doubleItem2 = TrajectoryPredictor.getDouble4(MathHelper.getDouble4(valueArray[1]), entityLivingBaseEntry.value);
            return doubleItem > doubleItem2 ? valueArray[1] : valueArray[0];
         }
      }
   }

   public ExtendedStringEntryHandler(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "NearestVec to current aimgoal", true);
      BooleanSetting setting = new BooleanSetting(booleanSettingProperty.getModule(), this.getSetting().getString() + "_expandbb", false, this.getSetting());
      String text = "Expand bounding box";
      this.settingExpandBoundingBox = (BooleanSetting)setting.getSetting(text);
   }
}
