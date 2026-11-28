package net.augustus.modules.modes.clicking;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.modules.combat.helper.ClickModuleContext;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.math.DefaultRandomGenerator;
import java.security.SecureRandom;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class DeviationClickPatternMode extends AbstractClickPatternMode {
   private final RandomizedRangeSettings deviationCPS;
   private final BooleanValue deviationDoubleClick;
   private final DoubleValue deviationAirMultiplier;
   private long nextClickTimeMs;

   public DeviationClickPatternMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "Deviation");
      this.nextClickTimeMs = 0L;
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(module, "DeviationCPS", 13.0, 20.0, 0.0, 40.0, 0, doubleValue)).withoutNotInARow();
      String str = "MaxCPS";
      this.deviationCPS = (RandomizedRangeSettings)randomizedRangeSettings.withRangeNames("MinCPS", str).withVisibility(() -> this.isSelected());
      this.deviationDoubleClick = (BooleanValue)((BooleanValue)new BooleanValue(module, "DeviationDoubleClick", true, doubleValue).withDisplayName("DoubleClick")).withVisibility(() -> this.isSelected());
      this.deviationAirMultiplier = new DoubleValue(module, "DeviationAirMultiplier", 0.4, 0.0, 1.0, 2, doubleValue).named("AirMultiplier").visibleWhen(() -> this.isSelected());
   }

   @Override
   public int computeClicks(EventClick event) {
      float minValue = (float)this.deviationCPS.getMinValue();
      float maxValue = (float)this.deviationCPS.getMaxValue();
      float f = (minValue + maxValue) / 2.0F;
      float f2 = (maxValue - minValue) / 6.0F;
      long currentTimeMs = System.currentTimeMillis();

      int n;
      for (n = 0; this.nextClickTimeMs < currentTimeMs; n++) {
         double d = (double)((int)(1000.0F / (float)((double)f + (double)f2 * new SecureRandom().nextGaussian())));
         if (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
            d /= this.deviationAirMultiplier.getValue();
         }

         this.nextClickTimeMs = this.nextClickTimeMs + Math.min((long)d, 1000L);
      }

      if (!this.deviationDoubleClick.getValue() && n > 1) {
         n = 1;
      }

      if (ClientManagers.moduleManager.smartClicking.isSuppressingClicks()) {
         n = 0;
      }

      return ((ClickModuleContext)this.parentModule).applyHitReduction(n);
   }

   @Override
   public void onTick() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
      this.nextClickTimeMs = System.currentTimeMillis();
   }

   @Override
   public void onDisable() {
   }

   public void resetNextClickTime() {
      this.nextClickTimeMs = System.currentTimeMillis() - (long)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)45);
   }

}
