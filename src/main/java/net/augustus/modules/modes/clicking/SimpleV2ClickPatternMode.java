package net.augustus.modules.modes.clicking;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.modules.combat.helper.ClickModuleContext;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.TickCounter;
import net.augustus.utils.TuplePair;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class SimpleV2ClickPatternMode extends AbstractClickPatternMode {
   private final RandomizedRangeSettings simpleV2StartCPS;
   private final RandomizedRangeSettings simpleV2DefaultCPS;
   private final RandomizedRangeSettings simpleV2StartCPSDuration;
   private final RandomizedRangeSettings simpleV2RecalculateDelay;
   private final RangeSetting simpleV2ClickDutyCycle;
   private final BooleanValue simpleV2DoubleClicks;
   private final BooleanValue simpleV2ExtraDoubleClickRandomize;
   private final SimpleClickPatternSimulator simpleClickPatternSimulator;
   private final TickCounter tickCounter;
   private final TimeHelper combatElapsedTimer;
   private final TimeHelper lastTargetAimTimer;

   public SimpleV2ClickPatternMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "SimpleV2");
      this.simpleClickPatternSimulator = new SimpleClickPatternSimulator();
      this.tickCounter = new TickCounter();
      this.combatElapsedTimer = new TimeHelper();
      this.lastTargetAimTimer = new TimeHelper();
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(module, "SimpleV2StartCPS", 15.0, 17.0, 0.0, 20.0, 0, doubleValue);
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("StartCPS");
      this.simpleV2StartCPS = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      randomizedRangeSettings = new RandomizedRangeSettings(module, "SimpleV2DefaultCPS", 10.0, 12.0, 0.0, 20.0, 0, doubleValue);
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("DefaultCPS");
      this.simpleV2DefaultCPS = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      randomizedRangeSettings = new RandomizedRangeSettings(module, "SimpleV2StartCPSDuration", 15.0, 20.0, 0.0, 40.0, 0, doubleValue);
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("StartCPSDuration");
      this.simpleV2StartCPSDuration = ((RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected())).withoutNotInARow();
      randomizedRangeSettings = new RandomizedRangeSettings(module, "SimpleV2RecalculateDelay", 4.0, 8.0, 0.0, 20.0, 0, doubleValue);
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("RecalculateDelay");
      this.simpleV2RecalculateDelay = ((RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected())).withoutNotInARow();
      this.simpleV2ClickDutyCycle = new RangeSetting(module, "SimpleV2ClickDutyCycle", 0.35, 0.65, 0.0, 1.0, 2, doubleValue).named("ClickDutyCycle").visibleWhen(() -> this.isSelected());
      this.simpleV2DoubleClicks = (BooleanValue)((BooleanValue)new BooleanValue(module, "SimpleV2DoubleClicks", true, doubleValue).withDisplayName("DoubleClicks")).withVisibility(() -> this.isDoubleClicksVisible());
      this.simpleV2ExtraDoubleClickRandomize = (BooleanValue)((BooleanValue)new BooleanValue(module, "SimpleV2ExtraDoubleClickRandomize", false, doubleValue).withDisplayName("ExtraDoubleClickRandomize")).withVisibility(() -> this.isExtraDoubleClickRandomizeVisible());
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      ((ClickModuleContext)this.parentModule).setHoldingAttack(false);
   }

   @Override
   public int computeClicks(EventClick event) {
      if (this.tickCounter.hasReached(this.simpleV2RecalculateDelay.getLastIntValue())) {
         this.simpleV2RecalculateDelay.nextValue();
         this.simpleV2StartCPS.nextValue(true);
         this.simpleV2DefaultCPS.nextValue(true);
         this.tickCounter.reset();
      }

      int n = MathHelper.clamp_int(this.computeDecayedCps((int)this.combatElapsedTimer.getTime()), 0, 40);
      TuplePair tuplePair = this.simpleClickPatternSimulator.simulateTick(n, this.simpleV2ClickDutyCycle.getMinValue(), this.simpleV2ClickDutyCycle.getMaxValue(), this.simpleV2DoubleClicks.getValue() && this.simpleV2DoubleClicks.isVisible(), this.simpleV2ExtraDoubleClickRandomize.getValue(), 0.76);
      int first = (Integer)tuplePair.getFirst();
      ClickModuleContext parentModule2 = (ClickModuleContext)this.parentModule;
      boolean second = (Boolean)tuplePair.getSecond();
      parentModule2.setHoldingAttack(second);
      first = ((ClickModuleContext)this.parentModule).applyHitReduction(first);
      if (ClientManagers.moduleManager.smartClicking.isSuppressingClicks() || first != first) {
         first = 0;
         ((ClickModuleContext)this.parentModule).setHoldingAttack(false);
      }

      return first;
   }

   @Override
   public void onTick() {
      this.tickCounter.increment();
      if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
         this.lastTargetAimTimer.reset();
      }

      if (this.lastTargetAimTimer.reached(175L)) {
         this.combatElapsedTimer.reset();
      }
   }

   @Override
   public boolean simulatesMousePress() {
      return true;
   }

   private int computeDecayedCps(int n) {
      int lastIntValue = this.simpleV2DefaultCPS.getLastIntValue();
      return (int)Math.round((double)lastIntValue + (double)(this.simpleV2StartCPS.getLastIntValue() - lastIntValue) * Math.exp((double)(-n) / (this.simpleV2StartCPSDuration.getLastValue() * 50.0)));
   }

   private boolean isExtraDoubleClickRandomizeVisible() {
      return this.isSelected() && this.simpleV2DoubleClicks.isVisible() && this.simpleV2DoubleClicks.getValue();
   }

   private boolean isDoubleClicksVisible() {
      if (this.isSelected()) {
         if (this.simpleV2StartCPS.getMaxValue() <= 16.0 && this.simpleV2StartCPS.getMinValue() >= 10.0) {
            if (this.simpleV2DefaultCPS.getMaxValue() <= 16.0 && this.simpleV2DefaultCPS.getMinValue() >= 10.0) {
               return true;
            }
         }
      }

      return false;
   }

}
