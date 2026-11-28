package net.augustus.modules.modes.clicking;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.modules.combat.helper.ClickModuleContext;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.options.AdvancedClickHurtTimeOption;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;

public class AdvancedClickPatternMode extends AbstractClickPatternMode {
   private final RandomizedRangeSettings advancedCPS;
   private final RandomizedRangeSettings advancedCPSRecalculateDelay;
   private final BooleanValue spikes;
   private final DoubleValue spikeDelay;
   private final RandomizedRangeSettings spikeDuration;
   private final DoubleValue spikeMultiplier;
   private final BooleanValue drops;
   private final DoubleValue dropDelay;
   private final RandomizedRangeSettings dropDuration;
   private final DoubleValue dropMultiplier;
   private final BooleanValue reduceHitsInAir;
   private final RandomizedRangeSettings reduceRecalculateDelay;
   private final RandomizedRangeSettings reduceMultiplier;
   private final BooleanValue reduceHitsOnWall;
   private final RandomizedRangeSettings reduceRecalculateDelayOnWall;
   private final RandomizedRangeSettings reduceMultiplierOnWall;
   private final MultiOptionSetting hurttimesToHit;
   private final ActionRateAccumulator actionRateAccumulator;
   private final TimeHelper cpsRecalcTimer;
   private final TimeHelper dropTimer;
   private final TimeHelper spikeTimer;
   private final TimeHelper airReduceTimer;
   private final TimeHelper wallReduceTimer;
   private final AdvancedClickHurtTimeOption[] hurtTimeOptions;
   private long nextSpikeDelayMs;
   private long nextDropDelayMs;

   public AdvancedClickPatternMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "Advanced");
      this.actionRateAccumulator = new ActionRateAccumulator(20, 0.0);
      this.cpsRecalcTimer = new TimeHelper();
      this.dropTimer = new TimeHelper();
      this.spikeTimer = new TimeHelper();
      this.airReduceTimer = new TimeHelper();
      this.wallReduceTimer = new TimeHelper();
      this.hurtTimeOptions = new AdvancedClickHurtTimeOption[11];
      this.nextSpikeDelayMs = 2000L;
      this.nextDropDelayMs = 2000L;
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(module, "AdvancedCPS", 20.0, 20.0, 0.0, 40.0, 0, doubleValue)).withoutNotInARow();
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("CPS");
      this.advancedCPS = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      randomizedRangeSettings = (new RandomizedRangeSettings(module, "AdvancedCPSRecalculateDelay", 200.0, 600.0, 0.0, 4000.0, 0, doubleValue)).withoutNotInARow();
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("CPSRecalculateDelay");
      this.advancedCPSRecalculateDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      this.spikes = (BooleanValue)new BooleanValue(module, "Spikes", false, doubleValue).withVisibility(() -> this.isSelected());
      this.spikeDelay = new DoubleValue(module, "SpikeDelay", 15000.0, 0.0, 20000.0, 0, doubleValue).visibleWhen(() -> this.isSpikeDelayVisible());
      randomizedRangeSettings = (new RandomizedRangeSettings(module, "SpikeDuration", 500.0, 1000.0, 0.0, 2000.0, 0, doubleValue)).withoutNotInARow();
      this.spikeDuration = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSpikeDurationVisible());
      this.spikeMultiplier = new DoubleValue(module, "SpikeMultiplier", 1.5, 1.0, 3.0, 2, doubleValue).visibleWhen(() -> this.isSpikeMultiplierVisible());
      this.drops = (BooleanValue)new BooleanValue(module, "Drops", false, doubleValue).withVisibility(() -> this.isSelected());
      this.dropDelay = new DoubleValue(module, "DropDelay", 7000.0, 0.0, 20000.0, 0, doubleValue).visibleWhen(() -> this.isDropDelayVisible());
      randomizedRangeSettings = (new RandomizedRangeSettings(module, "DropDuration", 500.0, 1000.0, 0.0, 2000.0, 0, doubleValue)).withoutNotInARow();
      this.dropDuration = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isDropDurationVisible());
      this.dropMultiplier = new DoubleValue(module, "DropMultiplier", 0.5, 0.0, 1.0, 2, doubleValue).visibleWhen(() -> this.isDropMultiplierVisible());
      this.reduceHitsInAir = (BooleanValue)new BooleanValue(module, "ReduceHitsInAir", true, doubleValue).withVisibility(() -> this.isSelected());
      randomizedRangeSettings = (new RandomizedRangeSettings(module, "ReduceRecalculateDelay", 100.0, 200.0, 0.0, 1000.0, 0, doubleValue)).withoutNotInARow();
      this.reduceRecalculateDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isAirReduceDelayVisible());
      randomizedRangeSettings = (new RandomizedRangeSettings(module, "ReduceMultiplier", 0.4, 0.4, 0.0, 1.0, 2, doubleValue)).withoutNotInARow();
      this.reduceMultiplier = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isAirReduceMultiplierVisible());
      this.reduceHitsOnWall = (BooleanValue)new BooleanValue(module, "ReduceHitsOnWall", false, doubleValue).withVisibility(() -> this.isSelected());
      this.reduceRecalculateDelayOnWall = (RandomizedRangeSettings)(new RandomizedRangeSettings(module, "ReduceRecalculateDelayOnWall", 100.0, 200.0, 0.0, 1000.0, 0, doubleValue)).withoutNotInARow().withVisibility(() -> this.isWallReduceDelayVisible());
      RandomizedRangeSettings randomizedRangeSettings3 = (new RandomizedRangeSettings(module, "ReduceMultiplierOnWall", 0.4, 0.4, 0.0, 1.0, 2, doubleValue)).withoutNotInARow();
      SettingVisibilityCondition condition16 = () -> {
         return this.isSelected() && this.reduceHitsOnWall.getValue();
      };
      this.reduceMultiplierOnWall = (RandomizedRangeSettings)randomizedRangeSettings3.withVisibility(condition16);
      this.initHurtTimeOptions();
      this.hurttimesToHit = new MultiOptionSetting(module, "HurttimesToHit", this.hurtTimeOptions, doubleValue).visibleWhen(() -> this.isSelected());
   }

   @Override
   public int computeClicks(EventClick event) {
      long l;
      TimeHelper cpsRecalcTimer2 = this.cpsRecalcTimer;
      if (cpsRecalcTimer2.reached((long)this.advancedCPSRecalculateDelay.getLastIntValue())) {
         this.advancedCPS.nextSecureValue();
         this.advancedCPSRecalculateDelay.nextValue();
         this.cpsRecalcTimer.reset();
      }

      int lastIntValue2 = this.advancedCPS.getLastIntValue();
      if (this.spikes.getValue()) {
         int value = (int)this.spikeDelay.getValue();
         long nextSpikeDelayMs2 = this.nextSpikeDelayMs;
         if (this.spikeTimer.reached(nextSpikeDelayMs2)) {
            lastIntValue2 = (int)Math.round((double)this.advancedCPS.getLastIntValue() * this.spikeMultiplier.getValue());
            cpsRecalcTimer2 = this.spikeTimer;
            l = this.nextSpikeDelayMs;
            RandomNumberGenerator instance = DefaultRandomGenerator.instance;
            int minValue = (int)this.spikeDuration.getMinValue();
            if (cpsRecalcTimer2.reached(l + (long)instance.nextSecureInt(minValue, (int)this.spikeDuration.getMaxValue()))) {
               RandomNumberGenerator instance2 = DefaultRandomGenerator.instance;
               int n3 = Math.max(0, value - 100);
               minValue = value + 100;
               this.nextSpikeDelayMs = (long)instance2.nextSecureInt(n3, minValue);
               this.spikeTimer.reset();
            }
         }
      }

      if (this.drops.getValue()) {
         int value2 = (int)this.dropDelay.getValue();
         long nextDropDelayMs2 = this.nextDropDelayMs;
         if (this.dropTimer.reached(nextDropDelayMs2)) {
            lastIntValue2 = (int)Math.round((double)this.advancedCPS.getLastIntValue() * this.dropMultiplier.getValue());
            cpsRecalcTimer2 = this.dropTimer;
            l = this.nextDropDelayMs;
            RandomNumberGenerator instance3 = DefaultRandomGenerator.instance;
            int minValue2 = (int)this.dropDuration.getMinValue();
            if (cpsRecalcTimer2.reached(l + (long)instance3.nextSecureInt(minValue2, (int)this.dropDuration.getMaxValue()))) {
               RandomNumberGenerator instance4 = DefaultRandomGenerator.instance;
               int n8 = Math.max(0, value2 - 100);
               minValue2 = value2 + 100;
               this.nextDropDelayMs = (long)instance4.nextSecureInt(n8, minValue2);
               this.dropTimer.reset();
            }
         }
      }

      if (this.reduceHitsInAir.getValue() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.MISS) {
         cpsRecalcTimer2 = this.airReduceTimer;
         if (cpsRecalcTimer2.reached((long)this.reduceRecalculateDelay.getLastIntValue())) {
            this.reduceRecalculateDelay.nextValue();
            this.reduceMultiplier.nextValue();
            this.airReduceTimer.reset();
         }

         lastIntValue2 = (int)Math.round((double)this.advancedCPS.getLastIntValue() * this.reduceMultiplier.getLastValue());
      }

      if (this.reduceHitsOnWall.getValue() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
         cpsRecalcTimer2 = this.wallReduceTimer;
         if (cpsRecalcTimer2.reached((long)this.reduceRecalculateDelayOnWall.getLastIntValue())) {
            this.reduceRecalculateDelayOnWall.nextValue();
            this.reduceMultiplierOnWall.nextValue();
            this.wallReduceTimer.reset();
         }

         lastIntValue2 = (int)Math.round((double)this.advancedCPS.getLastIntValue() * this.reduceMultiplierOnWall.getLastValue());
      }

      if (ClientManagers.moduleManager.smartClicking.isSuppressingClicks()) {
         return 0;
      } else {
         int n11 = ((ClickModuleContext)this.parentModule).getTarget() != null ? ((ClickModuleContext)this.parentModule).getTarget().hurtTime : 11;
         if (n11 < 11 && n11 > -1 && !this.hurtTimeOptions[n11].selected) {
            return 0;
         } else {
            return ((ClickModuleContext)this.parentModule).applyHitReduction(this.actionRateAccumulator.accumulate(lastIntValue2));
         }
      }
   }

   private void initHurtTimeOptions() {

      for (int i = 0; i < this.hurtTimeOptions.length; i++) {
         this.hurtTimeOptions[i] = new AdvancedClickHurtTimeOption(i);
      }
   }

   @Override
   public void onTick() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
      this.nextSpikeDelayMs = 2000L;
      this.nextDropDelayMs = 2000L;
   }

   @Override
   public void onDisable() {
   }

   private boolean isWallReduceDelayVisible() {
      return this.isSelected() && this.reduceHitsOnWall.getValue();
   }

   private boolean isAirReduceMultiplierVisible() {
      return this.isSelected() && this.reduceHitsInAir.getValue();
   }

   private boolean isAirReduceDelayVisible() {
      return this.isSelected() && this.reduceHitsInAir.getValue();
   }

   private boolean isDropMultiplierVisible() {
      return this.isSelected() && this.drops.getValue();
   }

   private boolean isDropDurationVisible() {
      return this.isSelected() && this.drops.getValue();
   }

   private boolean isDropDelayVisible() {
      return this.isSelected() && this.drops.getValue();
   }

   private boolean isSpikeMultiplierVisible() {
      return this.isSelected() && this.spikes.getValue();
   }

   private boolean isSpikeDurationVisible() {
      return this.isSelected() && this.spikes.getValue();
   }

   private boolean isSpikeDelayVisible() {
      return this.isSelected() && this.spikes.getValue();
   }

}
