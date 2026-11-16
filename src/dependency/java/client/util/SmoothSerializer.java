package client.util;

import client.setting.BooleanSettingProperty;
import net.minecraft.entity.EntityLivingBaseEntry;

public class SmoothSerializer extends SmoothWriter {
   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      double offset = 0.5;
      return TrajectoryPredictor.getCoordinate(entityLivingBaseEntry.valueArray, offset);
   }

   public SmoothSerializer(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "Mid");
   }
}
