package net.augustus.modules.modes.clicking;

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

public class SimpleClickPatternMode extends AbstractClickPatternMode {
   private final RandomizedRangeSettings simpleCPS;
   private final RandomizedRangeSettings simpleCPSRecalculateDelay;
   private final RangeSetting simpleClickDutyCycle;
   private final BooleanValue simpleDoubleClicks;
   private final BooleanValue simpleExtraDoubleClickRandomize;
   private final SimpleClickPatternSimulator simpleClickPatternSimulator;
   private final TickCounter tickCounter;

   public SimpleClickPatternMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "Simple");
      this.simpleClickPatternSimulator = new SimpleClickPatternSimulator();
      this.tickCounter = new TickCounter();
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(module, "SimpleCPS", 12.0, 16.0, 0.0, 40.0, 0, doubleValue);
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("CPS");
      this.simpleCPS = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      randomizedRangeSettings = (new RandomizedRangeSettings(module, "SimpleCPSRecalculateDelay", 3.0, 8.0, 0.0, 20.0, 0, doubleValue)).withoutNotInARow();
      randomizedRangeSettings = (RandomizedRangeSettings)randomizedRangeSettings.withDisplayNamePrefix("CPSRecalculateDelay");
      this.simpleCPSRecalculateDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> this.isSelected());
      this.simpleClickDutyCycle = new RangeSetting(module, "SimpleClickDutyCycle", 0.35, 0.65, 0.0, 1.0, 2, doubleValue).named("ClickDutyCycle").visibleWhen(() -> this.isSelected());
      this.simpleDoubleClicks = (BooleanValue)((BooleanValue)new BooleanValue(module, "SimpleDoubleClicks", true, doubleValue).withDisplayName("DoubleClicks")).withVisibility(() -> this.isDoubleClicksVisible());
      this.simpleExtraDoubleClickRandomize = (BooleanValue)((BooleanValue)new BooleanValue(module, "SimpleExtraDoubleClickRandomize", false, doubleValue).withDisplayName("ExtraDoubleClickRandomize")).withVisibility(() -> this.isExtraDoubleClickRandomizeVisible());
   }

   @Override
   public int computeClicks(EventClick event) {
      if (this.tickCounter.hasReached(this.simpleCPSRecalculateDelay.getLastIntValue())) {
         this.simpleCPSRecalculateDelay.nextValue();
         this.simpleCPS.nextValue();
         this.tickCounter.reset();
      }

      TuplePair tuplePair = this.simpleClickPatternSimulator.simulateTick(this.simpleCPS.getLastIntValue(), this.simpleClickDutyCycle.getMinValue(), this.simpleClickDutyCycle.getMaxValue(), this.simpleDoubleClicks.getValue() && this.simpleDoubleClicks.isVisible(), this.simpleExtraDoubleClickRandomize.getValue(), 0.76);
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
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public boolean simulatesMousePress() {
      return true;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      ((ClickModuleContext)this.parentModule).setHoldingAttack(false);
   }

   private boolean isExtraDoubleClickRandomizeVisible() {
      return this.isSelected() && this.simpleDoubleClicks.isVisible() && this.simpleDoubleClicks.getValue();
   }

   private boolean isDoubleClicksVisible() {
      if (this.isSelected()) {
         if (this.simpleCPS.getMaxValue() <= 16.0 && this.simpleCPS.getMinValue() >= 10.0) {
            return true;
         }
      }

      return false;
   }

}
