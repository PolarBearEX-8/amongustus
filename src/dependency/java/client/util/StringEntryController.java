package client.util;

import client.setting.BooleanSetting;
import client.setting.BooleanSettingProperty;
import net.minecraft.entity.EntityLivingBaseEntry;
import net.minecraft.util.Vec3;

public class StringEntryController extends SmoothWriter {
   public BooleanSetting settingOnlyIfNotAimingAtACorner;

   @Override
   public double computeValue(EntityLivingBaseEntry entityLivingBaseEntry) {
      Vec3 vec3 = entityLivingBaseEntry.vec3.getVec311(entityLivingBaseEntry.vec4);
      if (entityLivingBaseEntry.enabled) {
         double offset = -Math.atan2(vec3.yCoord, vec3.sqrt()) / Math.PI * 180.0;
         return this.settingOnlyIfNotAimingAtACorner.isEnabled() && offset != 0.0 ? Double.NaN : offset;
      } else {
         double offset2 = Math.atan2(vec3.zCoord, vec3.xCoord) / Math.PI * 180.0 - 90.0;
         if (this.settingOnlyIfNotAimingAtACorner.isEnabled()) {
            return PlayerUtil.computeValueByDouble(offset2, 90.0) > 1.0E-4 ? offset2 : Double.NaN;
         } else {
            return offset2;
         }
      }
   }

   @Override
   public double computeValueByEntityLivingBase(EntityLivingBaseEntry entityLivingBaseEntry) {
      double offset = super.computeValueByEntityLivingBase(entityLivingBaseEntry);
      double offset2 = this.computeValue(entityLivingBaseEntry);
      return Double.isFinite(offset2) ? offset : 0.0;
   }

   public StringEntryController(BooleanSettingProperty booleanSettingProperty) {
      super(booleanSettingProperty, "BestVec", true);
      BooleanSetting setting = new BooleanSetting(booleanSettingProperty.getModule(), this.getSetting().getString() + "_onlyifnotaimingatcorner", false, this.getSetting());
      String text = "Only if not aiming at a corner";
      this.settingOnlyIfNotAimingAtACorner = (BooleanSetting)setting.getSetting(text);
   }
}
