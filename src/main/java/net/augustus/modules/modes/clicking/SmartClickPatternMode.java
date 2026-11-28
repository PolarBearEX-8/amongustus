package net.augustus.modules.modes.clicking;

import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.modules.combat.helper.ClickModuleContext;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.utils.TickCounter;
import net.augustus.modules.Module;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

public class SmartClickPatternMode extends AbstractClickPatternMode {
   private final StringValue smartMode;
   private final StringValue smartClickMode;
   private final StringValue smartDoubleClickModeS;
   private final SettingGroup cpsSettings;
   private final SettingGroup inAir;
   private final RandomizedRangeSettings smartCPSInAir;
   private final RandomizedRangeSettings smartCPSInAirRecalculateTickDelay;
   private final RandomizedRangeSettings smartDCPSInAir;
   private final RandomizedRangeSettings dcpsInAirRecalculateTickDelay;
   private final SettingGroup onWall;
   private final RandomizedRangeSettings smartCPSOnWall;
   private final RandomizedRangeSettings smartCPSOnWallRecalculateTickDelay;
   private final RandomizedRangeSettings smartDCPSOnWall;
   private final RandomizedRangeSettings dcpsOnWallRecalculateTickDelay;
   private final SettingGroup onFirstHit;
   private final RandomizedRangeSettings smartCPSFirstHit;
   private final RandomizedRangeSettings smartCPSFirstHitRecalculateTickDelay;
   private final RandomizedRangeSettings smartDCPSFirstHit;
   private final RandomizedRangeSettings smartDCPSFirstHitRecalculateTickDelay;
   private final SettingGroup onTarget;
   private final RandomizedRangeSettings smartCPS;
   private final RandomizedRangeSettings cpsRecalculateTickDelay;
   private final RandomizedRangeSettings smartDCPS;
   private final RandomizedRangeSettings dcpsRecalculateTickDelay;
   private final TickCounter[] hurtTimeRecalcCounters;
   private final ActionRateAccumulator[] separateRateAccumulators;
   private final RandomizedRangeSettings[] hurtTimeRangeSettings;
   private final TickCounter airCpsRecalcCounter;
   private final TickCounter airDcpsRecalcCounter;
   private final TickCounter wallCpsRecalcCounter;
   private final TickCounter wallDcpsRecalcCounter;
   private final TickCounter firstHitCpsRecalcCounter;
   private final TickCounter firstHitDcpsRecalcCounter;
   private final TickCounter targetCpsRecalcCounter;
   private final TickCounter targetDcpsRecalcCounter;
   private final ActionRateAccumulator combinedClickAccumulator;
   private final ActionRateAccumulator combinedDoubleClickAccumulator;
   private int consecutiveTargetClickTicks;
   private int lastTargetHurtTime;

   public SmartClickPatternMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "Smart");
      this.hurtTimeRecalcCounters = new TickCounter[22];
      this.separateRateAccumulators = new ActionRateAccumulator[28];
      this.hurtTimeRangeSettings = new RandomizedRangeSettings[44];
      this.airCpsRecalcCounter = new TickCounter();
      this.airDcpsRecalcCounter = new TickCounter();
      this.wallCpsRecalcCounter = new TickCounter();
      this.wallDcpsRecalcCounter = new TickCounter();
      this.firstHitCpsRecalcCounter = new TickCounter();
      this.firstHitDcpsRecalcCounter = new TickCounter();
      this.targetCpsRecalcCounter = new TickCounter();
      this.targetDcpsRecalcCounter = new TickCounter();
      this.combinedClickAccumulator = new ActionRateAccumulator(20, 0.0);
      this.combinedDoubleClickAccumulator = new ActionRateAccumulator(20, 5.0);
      this.consecutiveTargetClickTicks = 0;
      this.lastTargetHurtTime = 0;
      StringValue setting = new StringValue(module, "SmartMode", "Recommended", new String[]{"Recommended", "Overengineered"}, doubleValue);
      setting = (StringValue)setting.withDisplayName("Mode");
      this.smartMode = (StringValue)setting.withVisibility(() -> this.isSelected());
      setting = new StringValue(module, "SmartClickMode", "Combined", new String[]{"Combined", "Default", "Separate"}, doubleValue);
      setting = (StringValue)setting.withDisplayName("ClickMode");
      this.smartClickMode = (StringValue)setting.withVisibility(() -> this.isSelected());
      setting = new StringValue(module, "SmartDoubleClickModeS", "Default", new String[]{"Combined", "Default", "Separate"}, doubleValue);
      setting = (StringValue)setting.withDisplayName("DoubleClickMode");
      this.smartDoubleClickModeS = (StringValue)setting.withVisibility(() -> this.isSelected());
      this.cpsSettings = new SettingGroup(module, "CPS Settings", true, doubleValue).visibleWhen(() -> this.isSelected());
      this.inAir = new SettingGroup(module, "In Air", true, this.cpsSettings).visibleWhen(() -> this.isSelected());
      this.smartCPSInAir = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "SmartCPSInAir", 4.0, 8.0, 0.0, 20.0, 0, this.inAir).withDisplayNamePrefix("CPSInAir")).withVisibility(() -> this.isSelected());
      this.smartCPSInAirRecalculateTickDelay = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "SmartCPSInAirRecalculateTickDelay", 3.0, 8.0, 0.0, 60.0, 0, this.inAir).withDisplayNamePrefix("CPSInAirRecalculateTickDelay")).withoutNotInARow().withVisibility(() -> this.isSelected());
      this.smartDCPSInAir = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "SmartDCPSInAir", 0.0, 0.0, 0.0, 20.0, 0, this.inAir).withDisplayNamePrefix("DoubleClicksPerSecondInAir")).withVisibility(() -> this.isSelected());
      this.dcpsInAirRecalculateTickDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(module, "DCPSInAirRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.inAir)).withoutNotInARow().withVisibility(() -> this.isSelected());
      this.onWall = new SettingGroup(module, "On wall", true, this.cpsSettings).visibleWhen(() -> this.isSelected());
      this.smartCPSOnWall = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "SmartCPSOnWall", 8.0, 12.0, 0.0, 20.0, 0, this.onWall).withDisplayNamePrefix("CPSOnWall")).withVisibility(() -> this.isSelected());
      this.smartCPSOnWallRecalculateTickDelay = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "SmartCPSOnWallRecalculateTickDelay", 3.0, 8.0, 0.0, 60.0, 0, this.onWall).withDisplayNamePrefix("CPSOnWallRecalculateTickDelay")).withoutNotInARow().withVisibility(() -> this.isSelected());
      this.smartDCPSOnWall = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "SmartDCPSOnWall", 2.0, 4.0, 0.0, 20.0, 0, this.onWall).withDisplayNamePrefix("DCPSOnWall")).withVisibility(() -> this.isSelected());
      this.dcpsOnWallRecalculateTickDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(module, "DCPSOnWallRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.onWall)).withoutNotInARow().withVisibility(() -> this.isSelected());
      this.onFirstHit = new SettingGroup(module, "On first Hit", true, this.cpsSettings).visibleWhen(() -> this.isSelected());
      this.smartCPSFirstHit = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "SmartCPSFirstHit", 20.0, 20.0, 0.0, 40.0, 0, this.onFirstHit).withDisplayNamePrefix("CPSFirstHit")).withVisibility(() -> this.isSelected());
      this.smartCPSFirstHitRecalculateTickDelay = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "SmartCPSFirstHitRecalculateTickDelay", 3.0, 8.0, 0.0, 60.0, 0, this.onFirstHit).withDisplayNamePrefix("CPSFirstHitRecalculateTickDelay")).withoutNotInARow().withVisibility(() -> this.isSelected());
      RandomizedRangeSettings randomizedRangeSettings19 = new RandomizedRangeSettings(module, "SmartDCPSFirstHit", 4.0, 5.0, 0.0, 20.0, 0, this.onFirstHit);
      randomizedRangeSettings19 = (RandomizedRangeSettings)randomizedRangeSettings19.withDisplayNamePrefix("DoubleClicksPerSecondFirstHit");
      this.smartDCPSFirstHit = (RandomizedRangeSettings)randomizedRangeSettings19.withVisibility(() -> this.isFirstHitDcpsVisible());
      randomizedRangeSettings19 = new RandomizedRangeSettings(module, "SmartDCPSFirstHitRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.onFirstHit);
      randomizedRangeSettings19 = ((RandomizedRangeSettings)randomizedRangeSettings19.withDisplayNamePrefix("DCPSFirstHitRecalculateTickDelay")).withoutNotInARow();
      this.smartDCPSFirstHitRecalculateTickDelay = (RandomizedRangeSettings)randomizedRangeSettings19.withVisibility(() -> this.isFirstHitDcpsRecalcDelayVisible());
      this.onTarget = new SettingGroup(module, "On Target", true, this.cpsSettings).visibleWhen(() -> this.isOnTargetGroupVisible());
      randomizedRangeSettings19 = new RandomizedRangeSettings(module, "SmartCPS", 17.0, 19.0, 1.0, 40.0, 0, this.onTarget);
      randomizedRangeSettings19 = (RandomizedRangeSettings)randomizedRangeSettings19.withDisplayNamePrefix("CPS");
      SettingVisibilityCondition condition21 = () -> {
         return this.isSelected() && this.smartMode.getSelectedValue().equals("Recommended");
      };
      this.smartCPS = (RandomizedRangeSettings)randomizedRangeSettings19.withVisibility(condition21);
      randomizedRangeSettings19 = (new RandomizedRangeSettings(module, "CPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.onTarget)).withoutNotInARow();
      this.cpsRecalculateTickDelay = (RandomizedRangeSettings)randomizedRangeSettings19.withVisibility(() -> this.isRecommendedCpsRecalcDelayVisible());
      randomizedRangeSettings19 = new RandomizedRangeSettings(module, "SmartDCPS", 4.0, 5.0, 0.0, 20.0, 0, this.onTarget);
      randomizedRangeSettings19 = (RandomizedRangeSettings)randomizedRangeSettings19.withDisplayNamePrefix("DoubleClicksPerSecond");
      this.smartDCPS = (RandomizedRangeSettings)randomizedRangeSettings19.withVisibility(() -> this.isRecommendedDcpsVisible());
      randomizedRangeSettings19 = (new RandomizedRangeSettings(module, "DCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.onTarget)).withoutNotInARow();
      this.dcpsRecalculateTickDelay = (RandomizedRangeSettings)randomizedRangeSettings19.withVisibility(() -> this.isRecommendedDcpsRecalcDelayVisible());
      this.initHurtTimeSettings(module);
   }

   @Override
   public int computeClicks(EventClick event) {
      int n = 0;
      MovingObjectPosition objectMouseOver = mc.objectMouseOver;
      boolean flag = objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && objectMouseOver.entityHit instanceof EntityLivingBase;
      this.updateRecalcTimers();
      EntityLivingBase target = ((ClickModuleContext)this.parentModule).getTarget();
      if (flag) {
         int n2 = target != null ? target.hurtTime : 0;
         if (ClientManagers.moduleManager.smartClicking.isSuppressingClicks()) {
            n = 0;
         } else if (n2 != this.lastTargetHurtTime) {
            n = this.getClickAccumulator(n2).accumulate(((ClickModuleContext)this.parentModule).applyHitReduction(this.getCpsForHurtTimeIndex(n2 * 4)));
            if (n > 0) {
               ActionRateAccumulator doubleClickAccumulator = this.getDoubleClickAccumulator(n2 + 11);
               int n7 = ((ClickModuleContext)this.parentModule).applyHitReduction(this.getDcpsForHurtTimeIndex(n2 * 4 + 2));
               n += doubleClickAccumulator.accumulate(n7);
            }
         } else {
            TickCounter tickCounter = this.firstHitCpsRecalcCounter;
            if (tickCounter.hasReached(this.smartCPSFirstHitRecalculateTickDelay.getLastIntValue())) {
               this.firstHitCpsRecalcCounter.reset();
               this.smartCPSFirstHitRecalculateTickDelay.nextSecureValue();
               this.smartCPSFirstHit.nextSecureValue();
            }

            tickCounter = this.firstHitDcpsRecalcCounter;
            if (tickCounter.hasReached(this.smartDCPSFirstHitRecalculateTickDelay.getLastIntValue())) {
               this.firstHitDcpsRecalcCounter.reset();
               this.smartDCPSFirstHitRecalculateTickDelay.nextSecureValue();
               this.smartDCPSFirstHit.nextSecureValue();
            }

            n = this.getClickAccumulator((byte)22).accumulate(((ClickModuleContext)this.parentModule).applyHitReduction(this.smartCPSFirstHit.getLastIntValue()));
            if (n > 0) {
               ActionRateAccumulator doubleClickAccumulator2 = this.getDoubleClickAccumulator((byte)23);
               int n10 = ((ClickModuleContext)this.parentModule).applyHitReduction(this.smartMode.getSelectedValue().equals("Recommended") ? this.smartDCPS.getLastIntValue() : this.smartDCPSFirstHit.getLastIntValue());
               n += doubleClickAccumulator2.accumulate(n10);
            }
         }

         this.consecutiveTargetClickTicks = n > 0 ? ++this.consecutiveTargetClickTicks : 0;
      } else if (objectMouseOver != null && objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.MISS) {
         TickCounter wallCpsRecalcCounter2 = this.wallCpsRecalcCounter;
         if (wallCpsRecalcCounter2.hasReached(this.smartCPSOnWallRecalculateTickDelay.getLastIntValue())) {
            this.wallCpsRecalcCounter.reset();
            this.smartCPSOnWallRecalculateTickDelay.nextSecureValue();
            this.smartCPSOnWall.nextSecureValue();
         }

         wallCpsRecalcCounter2 = this.wallDcpsRecalcCounter;
         if (wallCpsRecalcCounter2.hasReached(this.dcpsOnWallRecalculateTickDelay.getLastIntValue())) {
            this.wallDcpsRecalcCounter.reset();
            this.dcpsOnWallRecalculateTickDelay.nextSecureValue();
            this.smartDCPSOnWall.nextSecureValue();
         }

         n = this.getClickAccumulator((byte)26).accumulate(this.smartCPSOnWall.getLastIntValue());
         if (n > 0) {
            ActionRateAccumulator doubleClickAccumulator3 = this.getDoubleClickAccumulator((byte)27);
            int lastIntValue7 = this.smartDCPSOnWall.getLastIntValue();
            n += doubleClickAccumulator3.accumulate(lastIntValue7);
         }
      } else {
         TickCounter airCpsRecalcCounter2 = this.airCpsRecalcCounter;
         if (airCpsRecalcCounter2.hasReached(this.smartCPSInAirRecalculateTickDelay.getLastIntValue())) {
            this.airCpsRecalcCounter.reset();
            this.smartCPSInAirRecalculateTickDelay.nextSecureValue();
            this.smartCPSInAir.nextSecureValue();
         }

         airCpsRecalcCounter2 = this.airDcpsRecalcCounter;
         if (airCpsRecalcCounter2.hasReached(this.dcpsInAirRecalculateTickDelay.getLastIntValue())) {
            this.airDcpsRecalcCounter.reset();
            this.dcpsInAirRecalculateTickDelay.nextSecureValue();
            this.smartDCPSInAir.nextSecureValue();
         }

         n = this.getClickAccumulator((byte)24).accumulate(this.smartCPSInAir.getLastIntValue());
         if (n > 0) {
            ActionRateAccumulator doubleClickAccumulator4 = this.getDoubleClickAccumulator((byte)25);
            int lastIntValue11 = this.smartDCPSInAir.getLastIntValue();
            n += doubleClickAccumulator4.accumulate(lastIntValue11);
         }
      }

      if (target != null) {
         this.lastTargetHurtTime = target.hurtTime;
      }

      if (ClientManagers.moduleManager.smartClicking.isSuppressingClicks()) {
         n = 0;
      }

      return n;
   }

   private int getCpsForHurtTimeIndex(int n) {
      return this.smartMode.getSelectedValue().equals("Recommended") ? this.smartCPS.getLastIntValue() : this.hurtTimeRangeSettings[n].getLastIntValue();
   }

   private int getDcpsForHurtTimeIndex(int n) {
      return this.smartMode.getSelectedValue().equals("Recommended") ? this.smartDCPS.getLastIntValue() : this.hurtTimeRangeSettings[n].getLastIntValue();
   }

   private ActionRateAccumulator getClickAccumulator(int n) {
      switch (this.smartClickMode.getSelectedValue()) {
         case "Combined":
            return this.combinedClickAccumulator;
         case "Default":
            return n < 22 ? this.combinedClickAccumulator : this.separateRateAccumulators[n];
         case "Separate":
            return this.separateRateAccumulators[n];
         default:
            return this.combinedClickAccumulator;
      }
   }

   private ActionRateAccumulator getDoubleClickAccumulator(int n) {
      switch (this.smartDoubleClickModeS.getSelectedValue()) {
         case "Combined":
            return this.combinedDoubleClickAccumulator;
         case "Default":
            return n < 22 ? this.combinedDoubleClickAccumulator : this.separateRateAccumulators[n];
         case "Separate":
            return this.separateRateAccumulators[n];
         default:
            return this.combinedDoubleClickAccumulator;
      }
   }

   private void updateRecalcTimers() {
      if (this.smartMode.getSelectedValue().equals("Recommended")) {
         TickCounter targetCpsRecalcCounter2 = this.targetCpsRecalcCounter;
         if (targetCpsRecalcCounter2.hasReached(this.cpsRecalculateTickDelay.getLastIntValue())) {
            this.targetCpsRecalcCounter.reset();
            this.cpsRecalculateTickDelay.nextSecureValue();
            this.smartCPS.nextSecureValue();
         }

         targetCpsRecalcCounter2 = this.targetDcpsRecalcCounter;
         if (targetCpsRecalcCounter2.hasReached(this.dcpsRecalculateTickDelay.getLastIntValue())) {
            this.targetDcpsRecalcCounter.reset();
            this.dcpsRecalculateTickDelay.nextSecureValue();
            this.smartDCPS.nextSecureValue();
         }
      } else {
         for (int i = 0; i < 22; i++) {
            int n = i * 2;
            if (this.hurtTimeRecalcCounters[i].hasReached(this.hurtTimeRangeSettings[n].getLastIntValue())) {
               this.hurtTimeRecalcCounters[i].reset();
               this.hurtTimeRangeSettings[n + 1].nextSecureValue();
               this.hurtTimeRangeSettings[n].nextSecureValue();
            }
         }
      }
   }

   @Override
   public void onTick() {
      TickCounter[] hurtTimeRecalcCounters2 = this.hurtTimeRecalcCounters;
      int length = hurtTimeRecalcCounters2.length;

      for (int i = 0; i < length; i++) {
         hurtTimeRecalcCounters2[i].increment();
      }

      this.airCpsRecalcCounter.increment();
      this.airDcpsRecalcCounter.increment();
      this.wallCpsRecalcCounter.increment();
      this.wallDcpsRecalcCounter.increment();
      this.firstHitCpsRecalcCounter.increment();
      this.firstHitDcpsRecalcCounter.increment();
      this.targetCpsRecalcCounter.increment();
      this.targetDcpsRecalcCounter.increment();
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
      this.consecutiveTargetClickTicks = 0;
      this.lastTargetHurtTime = 0;
   }

   @Override
   public void onDisable() {
      this.consecutiveTargetClickTicks = 0;
      this.lastTargetHurtTime = 0;
   }

   private void initHurtTimeSettings(Module module) {

      for (int i = 0; i < this.hurtTimeRecalcCounters.length; i++) {
         this.hurtTimeRecalcCounters[i] = new TickCounter();
      }

      for (int j = 0; j < this.separateRateAccumulators.length; j++) {
         this.separateRateAccumulators[j] = new ActionRateAccumulator(20, (double)j * 1.2 % 20.0);
      }

      for (int k = 0; k < 11; k++) {
         SettingGroup settingGroup = new SettingGroup(module, "HurtTime " + k, this.cpsSettings).visibleWhen(() -> this.isHurtTimeGroupVisible());
         RandomizedRangeSettings[] hurtTimeRangeSettings2 = this.hurtTimeRangeSettings;
         int n = k * 4;
         RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(module, "SmartCPSHurt" + k, 17.0, 19.0, 1.0, 40.0, 0, settingGroup);
         randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("CPS");
         hurtTimeRangeSettings2[n] = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isHurtCpsVisible());
         RandomizedRangeSettings[] hurtTimeRangeSettings3 = this.hurtTimeRangeSettings;
         n = k * 4 + 1;
         randomizedRangeSettings = new RandomizedRangeSettings(module, "Hurt" + k + "SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, settingGroup);
         randomizedRangeSettings = ((RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("CPSRecalculateTickDelay")).withoutNotInARow();
         hurtTimeRangeSettings3[n] = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isHurtCpsRecalcDelayVisible());
         RandomizedRangeSettings[] hurtTimeRangeSettings4 = this.hurtTimeRangeSettings;
         n = k * 4 + 2;
         randomizedRangeSettings = new RandomizedRangeSettings(module, "SmartDCPSHurt" + k, 4.0, 5.0, 0.0, 20.0, 0, settingGroup);
         randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("DoubleClicksPerSecond");
         hurtTimeRangeSettings4[n] = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isHurtDcpsVisible());
         RandomizedRangeSettings[] hurtTimeRangeSettings5 = this.hurtTimeRangeSettings;
         n = k * 4 + 3;
         randomizedRangeSettings = new RandomizedRangeSettings(module, "DCPSRecalculateTickDelayHurt" + k, 3.0, 8.0, 1.0, 40.0, 0, settingGroup);
         randomizedRangeSettings = ((RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("DoubleClicksPerSecondRecalculateTickDelay")).withoutNotInARow();
         hurtTimeRangeSettings5[n] = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isHurtDcpsRecalcDelayVisible());
      }
   }

   private boolean isHurtDcpsRecalcDelayVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Overengineered");
   }

   private boolean isHurtDcpsVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Overengineered");
   }

   private boolean isHurtCpsRecalcDelayVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Overengineered");
   }

   private boolean isHurtCpsVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Overengineered");
   }

   private boolean isHurtTimeGroupVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Overengineered");
   }

   private boolean isRecommendedDcpsRecalcDelayVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Recommended");
   }

   private boolean isRecommendedDcpsVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Recommended");
   }

   private boolean isRecommendedCpsRecalcDelayVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Recommended");
   }

   private boolean isOnTargetGroupVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Recommended");
   }

   private boolean isFirstHitDcpsRecalcDelayVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Overengineered");
   }

   private boolean isFirstHitDcpsVisible() {
      return this.isSelected() && this.smartMode.getSelectedValue().equals("Overengineered");
   }

}
