package net.augustus.modules.modes.clicking.legacy;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.settings.RandomizedRangeSettings;
import java.security.SecureRandom;
import java.util.function.BiFunction;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.EntityLivingBase;

public class LegacyDeviationClickMode extends AbstractLegacyClickMode {
   private final RandomizedRangeSettings deviationCPS;
   private final BooleanValue deviationDoubleClick;
   private final DoubleValue deviationAirMultiplier;
   private final DoubleValue deviationWallMultiplier;
   private long nextClickTimeMs;

   public LegacyDeviationClickMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "DeviationClickPattern");
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this.parentModule, "DeviationCPS", 13.0, 20.0, 0.0, 40.0, 0, this.modeSetting)).withoutNotInARow();
      String str = "MaxCPS";
      this.deviationCPS = randomizedRangeSettings.withRangeNames("MinCPS", str);
      this.deviationDoubleClick = (BooleanValue)new BooleanValue(this.parentModule, "DeviationDoubleClick", true, this.modeSetting).withDisplayName("DoubleClick");
      this.deviationAirMultiplier = new DoubleValue(this.parentModule, "DeviationAirMultiplier", 0.4, 0.0, 1.0, 2, this.modeSetting).named("AirMultiplier");
      this.deviationWallMultiplier = new DoubleValue(this.parentModule, "DeviationWallMultiplier", 0.4, 0.0, 1.0, 2, this.modeSetting).named("WallMultiplier");
      this.nextClickTimeMs = 0L;
   }

   @Override
   public int computeClicks(EntityLivingBase entity) {
      float minValue = (float)this.deviationCPS.getMinValue();
      float maxValue = (float)this.deviationCPS.getMaxValue();
      float f = (minValue + maxValue) / 2.0F;
      float f2 = (maxValue - minValue) / 6.0F;
      long currentTimeMs = System.currentTimeMillis();

      int n;
      for (n = 0; this.nextClickTimeMs < currentTimeMs; n++) {
         double d = (double)((int)(1000.0F / (float)((double)f + (double)f2 * new SecureRandom().nextGaussian())));
         if (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.MISS) {
            d /= this.deviationAirMultiplier.getValue();
         }

         if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            d /= this.deviationWallMultiplier.getValue();
         }

         this.nextClickTimeMs = this.nextClickTimeMs + Math.min((long)d, 1000L);
      }

      if (!this.deviationDoubleClick.getValue() && n > 1) {
         n = 1;
      }

      return n;
   }

   @Override
   public void onClickPerformed(EntityLivingBase entity) {
   }

   @Override
   public void onTickUpdate() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new LegacyDeviationClickMode((Module)obj, (SettingGroup)obj2);
   }

}
