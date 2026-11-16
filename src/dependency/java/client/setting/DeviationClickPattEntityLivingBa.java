package client.setting;

import client.module.Module;
import client.util.MovingObjectType;
import java.security.SecureRandom;
import java.util.function.BiFunction;
import net.minecraft.entity.EntityLivingBase;

public class DeviationClickPattEntityLivingBa extends AbstractEntityLivingBaseModeOptionSupplier {
   public final RangeSetting settingMaxCPS;
   public final BooleanSetting settingDoubleClick;
   public final NumberSetting settingAirMultiplier;
   public final NumberSetting settingWallMultiplier;
   public long timestamp;

   public DeviationClickPattEntityLivingBa(Module module, SettingGroup settingGroup) {
      super(module, settingGroup, "DeviationClickPattern");
      RangeSetting rangeSetting = (new RangeSetting(this.module, "DeviationCPS", 13.0, 20.0, 0.0, 40.0, 0, this.settingGroup)).getRangeSetting2();
      String text = "MaxCPS";
      String text2 = "MinCPS";
      this.settingMaxCPS = rangeSetting.getRangeSetting(text2, text);
      BooleanSetting setting = new BooleanSetting(this.module, "DeviationDoubleClick", true, this.settingGroup);
      String text3 = "DoubleClick";
      this.settingDoubleClick = (BooleanSetting)setting.getSetting(text3);
      NumberSetting setting2 = new NumberSetting(this.module, "DeviationAirMultiplier", 0.4, 0.0, 1.0, 2, this.settingGroup);
      String text4 = "AirMultiplier";
      this.settingAirMultiplier = setting2.getNumberSetting(text4);
      NumberSetting setting3 = new NumberSetting(this.module, "DeviationWallMultiplier", 0.4, 0.0, 1.0, 2, this.settingGroup);
      String text5 = "WallMultiplier";
      this.settingWallMultiplier = setting3.getNumberSetting(text5);
      this.timestamp = 0L;
   }

   @Override
   public int computeCount(EntityLivingBase livingEntity) {
      float doubleItem = (float)this.settingMaxCPS.getDouble9();
      float doubleItem2 = (float)this.settingMaxCPS.getDouble2();
      float factor = (doubleItem + doubleItem2) / 2.0F;
      float factor2 = (doubleItem2 - doubleItem) / 6.0F;
      long timestamp2 = System.currentTimeMillis();

      int count;
      for (count = 0; this.timestamp < timestamp2; count++) {
         double offset = (double)((int)(1000.0F / (float)((double)factor + (double)factor2 * new SecureRandom().nextGaussian())));
         if (minecraft.objectMouseOver == null || minecraft.objectMouseOver.typeOfHit == MovingObjectType.MISS) {
            offset /= this.settingAirMultiplier.getValue();
         }

         if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            offset /= this.settingWallMultiplier.getValue();
         }

         this.timestamp = this.timestamp + Math.min((long)offset, 1000L);
      }

      if (!this.settingDoubleClick.isEnabled() && count > 1) {
         count = 1;
      }

      return count;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity) {
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new DeviationClickPattEntityLivingBa((Module)item, (SettingGroup)group));
   }
}
