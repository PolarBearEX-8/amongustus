package net.augustus.modules.modes.clicking.legacy;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.ClientManagers;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.RandomNumberGenerator;
import java.util.function.BiFunction;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;

public class LegacyAdvancedClickMode extends AbstractLegacyClickMode {
   private final RandomizedRangeSettings advancedCPS;
   private final RandomizedRangeSettings advancedCPSRecalculateDelay;
   private final BooleanValue advancedSpikes;
   private final DoubleValue advancedSpikeDelay;
   private final RandomizedRangeSettings advancedSpikeDuration;
   private final DoubleValue advancedSpikeMultiplier;
   private final BooleanValue advancedDrops;
   private final DoubleValue advancedDropDelay;
   private final RandomizedRangeSettings advancedDropDuration;
   private final DoubleValue advancedDropMultiplier;
   private final BooleanValue advancedReduceHitsInAir;
   private final RandomizedRangeSettings advancedReduceRecalculateDelay;
   private final RandomizedRangeSettings advancedReduceMultiplier;
   private final BooleanValue advancedReduceHitsOnWall;
   private final RandomizedRangeSettings advancedReduceRecalculateDelayOnWall;
   private final RandomizedRangeSettings advancedReduceMultiplierOnWall;
   private final ActionRateAccumulator actionRateAccumulator;
   private final TimeHelper cpsRecalcTimer;
   private final TimeHelper dropTimer;
   private final TimeHelper spikeTimer;
   private final TimeHelper airReduceTimer;
   private final TimeHelper wallReduceTimer;
   private long nextSpikeDelayMs;
   private long nextDropDelayMs;

   public LegacyAdvancedClickMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "Advanced");
      this.advancedCPS = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "AdvancedCPS", 20.0, 20.0, 0.0, 40.0, 0, this.modeSetting)).withoutNotInARow().withDisplayNamePrefix("CPS");
      this.advancedCPSRecalculateDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "AdvancedCPSRecalculateDelay", 200.0, 600.0, 0.0, 4000.0, 0, this.modeSetting)).withoutNotInARow().withDisplayNamePrefix("CPSRecalculateDelay");
      this.advancedSpikes = (BooleanValue)new BooleanValue(this.parentModule, "AdvancedSpikes", false, this.modeSetting).withDisplayName("Spikes");
      DoubleValue advancedSpikeDelaySetting = new DoubleValue(this.parentModule, "AdvancedSpikeDelay", 15000.0, 0.0, 20000.0, 0, this.modeSetting).named("SpikeDelay");
      BooleanValue advancedSpikes2 = this.advancedSpikes;
      BooleanValue booleanValue2 = advancedSpikes2;
      this.advancedSpikeDelay = advancedSpikeDelaySetting.visibleWhen(() -> booleanValue2.getValue());
      RandomizedRangeSettings randomizedRangeSettings4 = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "AdvancedSpikeDuration", 500.0, 1000.0, 0.0, 2000.0, 0, this.modeSetting)).withoutNotInARow().withDisplayNamePrefix("SpikeDuration");
      advancedSpikes2 = this.advancedSpikes;
      BooleanValue booleanValue3 = advancedSpikes2;
      this.advancedSpikeDuration = (RandomizedRangeSettings)randomizedRangeSettings4.withVisibility(() -> booleanValue3.getValue());
      DoubleValue advancedSpikeMultiplier2 = new DoubleValue(this.parentModule, "AdvancedSpikeMultiplier", 1.5, 1.0, 3.0, 2, this.modeSetting).named("SpikeMultiplier");
      advancedSpikes2 = this.advancedSpikes;
      BooleanValue booleanValue4 = advancedSpikes2;
      this.advancedSpikeMultiplier = advancedSpikeMultiplier2.visibleWhen(() -> booleanValue4.getValue());
      this.advancedDrops = (BooleanValue)new BooleanValue(this.parentModule, "AdvancedDrops", false, this.modeSetting).withDisplayName("Drops");
      DoubleValue advancedDropDelaySetting = new DoubleValue(this.parentModule, "AdvancedDropDelay", 7000.0, 0.0, 20000.0, 0, this.modeSetting).named("DropDelay");
      advancedSpikes2 = this.advancedDrops;
      BooleanValue booleanValue6 = advancedSpikes2;
      this.advancedDropDelay = advancedDropDelaySetting.visibleWhen(() -> booleanValue6.getValue());
      RandomizedRangeSettings randomizedRangeSettings6 = ((RandomizedRangeSettings)new RandomizedRangeSettings(this.parentModule, "AdvancedDropDuration", 500.0, 1000.0, 0.0, 2000.0, 0, this.modeSetting).withDisplayNamePrefix("DropDuration")).withoutNotInARow();
      advancedSpikes2 = this.advancedDrops;
      BooleanValue booleanValue7 = advancedSpikes2;
      this.advancedDropDuration = (RandomizedRangeSettings)randomizedRangeSettings6.withVisibility(() -> booleanValue7.getValue());
      DoubleValue advancedDropMultiplier2 = new DoubleValue(this.parentModule, "AdvancedDropMultiplier", 0.5, 0.0, 1.0, 2, this.modeSetting).named("DropMultiplier");
      advancedSpikes2 = this.advancedDrops;
      BooleanValue booleanValue8 = advancedSpikes2;
      this.advancedDropMultiplier = advancedDropMultiplier2.visibleWhen(() -> booleanValue8.getValue());
      this.advancedReduceHitsInAir = (BooleanValue)new BooleanValue(this.parentModule, "AdvancedReduceHitsInAir", true, this.modeSetting).withDisplayName("ReduceHitsInAir");
      RandomizedRangeSettings randomizedRangeSettings8 = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "AdvancedReduceRecalculateDelay", 100.0, 200.0, 0.0, 1000.0, 0, this.modeSetting)).withoutNotInARow().withDisplayNamePrefix("ReduceRecalculateDelay");
      advancedSpikes2 = this.advancedReduceHitsInAir;
      BooleanValue booleanValue10 = advancedSpikes2;
      this.advancedReduceRecalculateDelay = (RandomizedRangeSettings)randomizedRangeSettings8.withVisibility(() -> booleanValue10.getValue());
      RandomizedRangeSettings randomizedRangeSettings10 = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "AdvancedReduceMultiplier", 0.4, 0.4, 0.0, 1.0, 2, this.modeSetting)).withoutNotInARow().withDisplayNamePrefix("ReduceMultiplier");
      advancedSpikes2 = this.advancedReduceHitsInAir;
      BooleanValue booleanValue11 = advancedSpikes2;
      this.advancedReduceMultiplier = (RandomizedRangeSettings)randomizedRangeSettings10.withVisibility(() -> booleanValue11.getValue());
      this.advancedReduceHitsOnWall = (BooleanValue)new BooleanValue(this.parentModule, "AdvancedReduceHitsOnWall", false, this.modeSetting).withDisplayName("ReduceHitsOnWall");
      RandomizedRangeSettings randomizedRangeSettings12 = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "AdvancedReduceRecalculateDelayOnWall", 100.0, 200.0, 0.0, 1000.0, 0, this.modeSetting))
         .withoutNotInARow().withDisplayNamePrefix("ReduceRecalculateDelayOnWall");
      BooleanValue booleanValue13 = this.advancedReduceHitsOnWall;
      BooleanValue booleanValue14 = booleanValue13;
      this.advancedReduceRecalculateDelayOnWall = (RandomizedRangeSettings)randomizedRangeSettings12.withVisibility(() -> booleanValue14.getValue());
      RandomizedRangeSettings randomizedRangeSettings14 = (RandomizedRangeSettings)(new RandomizedRangeSettings(this.parentModule, "AdvancedReduceMultiplierOnWall", 0.4, 0.4, 0.0, 1.0, 2, this.modeSetting)).withoutNotInARow().withDisplayNamePrefix("ReduceMultiplierOnWall");
      booleanValue13 = this.advancedReduceHitsOnWall;
      BooleanValue booleanValue15 = booleanValue13;
      this.advancedReduceMultiplierOnWall = (RandomizedRangeSettings)randomizedRangeSettings14.withVisibility(() -> booleanValue15.getValue());
      this.actionRateAccumulator = new ActionRateAccumulator(20, 0.0);
      this.cpsRecalcTimer = new TimeHelper();
      this.dropTimer = new TimeHelper();
      this.spikeTimer = new TimeHelper();
      this.airReduceTimer = new TimeHelper();
      this.wallReduceTimer = new TimeHelper();
      this.nextSpikeDelayMs = 0L;
      this.nextDropDelayMs = 0L;
   }

   @Override
   public int computeClicks(EntityLivingBase entity) {
      long l;
      TimeHelper cpsRecalcTimer2 = this.cpsRecalcTimer;
      if (cpsRecalcTimer2.reached((long)this.advancedCPSRecalculateDelay.getLastIntValue())) {
         this.advancedCPS.nextSecureValue();
         this.advancedCPSRecalculateDelay.nextValue();
         this.cpsRecalcTimer.reset();
      }

      int lastIntValue2 = this.advancedCPS.getLastIntValue();
      if (this.advancedSpikes.getValue()) {
         int value = (int)this.advancedSpikeDelay.getValue();
         long nextSpikeDelayMs2 = this.nextSpikeDelayMs;
         if (this.spikeTimer.reached(nextSpikeDelayMs2)) {
            lastIntValue2 = (int)Math.round((double)this.advancedCPS.getLastIntValue() * this.advancedSpikeMultiplier.getValue());
            cpsRecalcTimer2 = this.spikeTimer;
            l = this.nextSpikeDelayMs;
            RandomNumberGenerator instance = DefaultRandomGenerator.instance;
            int minValue = (int)this.advancedSpikeDuration.getMinValue();
            if (cpsRecalcTimer2.reached(l + (long)instance.nextSecureInt(minValue, (int)this.advancedSpikeDuration.getMaxValue()))) {
               RandomNumberGenerator instance2 = DefaultRandomGenerator.instance;
               int n3 = Math.max(0, value - 100);
               minValue = value + 100;
               this.nextSpikeDelayMs = (long)instance2.nextSecureInt(n3, minValue);
               this.spikeTimer.reset();
            }
         }
      }

      if (this.advancedDrops.getValue()) {
         int value2 = (int)this.advancedDropDelay.getValue();
         long nextDropDelayMs2 = this.nextDropDelayMs;
         if (this.dropTimer.reached(nextDropDelayMs2)) {
            lastIntValue2 = (int)Math.round((double)this.advancedCPS.getLastIntValue() * this.advancedDropMultiplier.getValue());
            cpsRecalcTimer2 = this.dropTimer;
            l = this.nextDropDelayMs;
            RandomNumberGenerator instance3 = DefaultRandomGenerator.instance;
            int minValue2 = (int)this.advancedDropDuration.getMinValue();
            if (cpsRecalcTimer2.reached(l + (long)instance3.nextSecureInt(minValue2, (int)this.advancedDropDuration.getMaxValue()))) {
               RandomNumberGenerator instance4 = DefaultRandomGenerator.instance;
               int n8 = Math.max(0, value2 - 100);
               minValue2 = value2 + 100;
               this.nextDropDelayMs = (long)instance4.nextSecureInt(n8, minValue2);
               this.dropTimer.reset();
            }
         }
      }

      if (this.advancedReduceHitsInAir.getValue() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.MISS) {
         cpsRecalcTimer2 = this.airReduceTimer;
         if (cpsRecalcTimer2.reached((long)this.advancedReduceRecalculateDelay.getLastIntValue())) {
            this.advancedReduceRecalculateDelay.nextValue();
            this.advancedReduceMultiplier.nextValue();
            this.airReduceTimer.reset();
         }

         lastIntValue2 = (int)Math.round((double)this.advancedCPS.getLastIntValue() * this.advancedReduceMultiplier.getLastValue());
      }

      if (this.advancedReduceHitsOnWall.getValue() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
         cpsRecalcTimer2 = this.wallReduceTimer;
         if (cpsRecalcTimer2.reached((long)this.advancedReduceRecalculateDelayOnWall.getLastIntValue())) {
            this.advancedReduceRecalculateDelayOnWall.nextValue();
            this.advancedReduceMultiplierOnWall.nextValue();
            this.wallReduceTimer.reset();
         }

         lastIntValue2 = (int)Math.round((double)this.advancedCPS.getLastIntValue() * this.advancedReduceMultiplierOnWall.getLastValue());
      }

      return ClientManagers.moduleManager.smartClicking.isSuppressingClicks() ? 0 : this.actionRateAccumulator.accumulate(lastIntValue2);
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
      return (obj, obj2) -> new LegacyAdvancedClickMode((Module)obj, (SettingGroup)obj2);
   }

}
