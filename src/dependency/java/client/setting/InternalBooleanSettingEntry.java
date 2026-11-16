package client.setting;

import client.util.SmoothWriter;
import client.util.TrajectoryPredictor;
import net.minecraft.entity.EntityLivingBaseEntry;
import net.minecraft.util.MathHelper;

public class InternalBooleanSettingEntry extends SmoothWriter {
   public BooleanSetting settingExpandBoundingBox;

   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      double[] valueArray = entityLivingBaseEntry.valueArray;
      if (!this.settingExpandBoundingBox.isEnabled()) {
         valueArray = entityLivingBaseEntry.valueArray3;
      }

      if (entityLivingBaseEntry.enabled) {
         double value = entityLivingBaseEntry.value2;
         double offset = TrajectoryPredictor.computeValue(valueArray, value);
         if (!(offset < 0.0) && !(offset > 1.0)) {
            return entityLivingBaseEntry.value2;
         } else {
            double offset2 = Math.abs(valueArray[0] - entityLivingBaseEntry.value2);
            double offset3 = Math.abs(valueArray[1] - entityLivingBaseEntry.value2);
            return offset2 > offset3 ? valueArray[1] : valueArray[0];
         }
      } else {
         double offset4 = TrajectoryPredictor.computeValue2(valueArray[0], valueArray[1], entityLivingBaseEntry.value2);
         if (!(offset4 < 0.0) && !(offset4 > 1.0)) {
            return entityLivingBaseEntry.value2;
         } else {
            double doubleItem = TrajectoryPredictor.getDouble4(MathHelper.getDouble4(valueArray[0]), entityLivingBaseEntry.value2);
            double doubleItem2 = TrajectoryPredictor.getDouble4(MathHelper.getDouble4(valueArray[1]), entityLivingBaseEntry.value2);
            return doubleItem > doubleItem2 ? valueArray[1] : valueArray[0];
         }
      }
   }

   public InternalBooleanSettingEntry(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "NearestVec", true);
      BooleanSetting setting = new BooleanSetting(booleanSettingProperty.getModule(), this.getSetting().getString() + "_expandbb", false, this.getSetting());
      String text = "Expand bounding box";
      this.settingExpandBoundingBox = (BooleanSetting)setting.getSetting(text);
   }
}
