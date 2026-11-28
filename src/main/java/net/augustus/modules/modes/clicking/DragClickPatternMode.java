package net.augustus.modules.modes.clicking;

import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.modules.combat.helper.ClickModuleContext;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.utils.TickCounter;
import net.augustus.utils.combat.CombatOptimizationUtil;
import java.util.ArrayList;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovingObjectPosition;

public class DragClickPatternMode extends AbstractClickPatternMode {
   private final RandomizedRangeSettings dragCPSInAir;
   private final RandomizedRangeSettings dragCPSInAirRecalculateTickDelay;
   private final RandomizedRangeSettings maxCPS;
   private final RandomizedRangeSettings maxCPSRecalculateTickDelay;
   private final DoubleValue minStandardDeviation;
   private final ArrayList recentClickCounts;
   private final ActionRateAccumulator actionRateAccumulator;
   private final TickCounter airCpsRecalcCounter;
   private final TickCounter maxCpsRecalcCounter;

   public DragClickPatternMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "DragClick");
      this.recentClickCounts = new ArrayList();
      this.actionRateAccumulator = new ActionRateAccumulator(20, 0.0);
      this.airCpsRecalcCounter = new TickCounter();
      this.maxCpsRecalcCounter = new TickCounter();
      this.dragCPSInAir = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "DragCPSInAir", 4.0, 8.0, 0.0, 20.0, 0, doubleValue).withDisplayNamePrefix("CPSInAir")).withVisibility(() -> this.isSelected());
      this.dragCPSInAirRecalculateTickDelay = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(module, "DragCPSInAirRecalculateTickDelay", 10.0, 30.0, 0.0, 60.0, 0, doubleValue).withDisplayNamePrefix("CPSInAirRecalculateTickDelay")).withoutNotInARow().withVisibility(() -> this.isSelected());
      this.maxCPS = (RandomizedRangeSettings)new RandomizedRangeSettings(module, "MaxCPS", 18.0, 20.0, 1.0, 40.0, 0, doubleValue).withVisibility(() -> this.isSelected());
      this.maxCPSRecalculateTickDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(module, "MaxCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, doubleValue)).withoutNotInARow().withVisibility(() -> this.isSelected());
      this.minStandardDeviation = new DoubleValue(module, "MinStandardDeviation", 0.5, 0.0, 1.0, 2, doubleValue).visibleWhen(() -> this.isSelected());
   }

   @Override
   public int computeClicks(EventClick event) {
      MovingObjectPosition objectMouseOver = mc.objectMouseOver;
      int n;
      if (this.recentClickCounts.size() < 5) {
         n = 1;
      } else {
         if (this.maxCpsRecalcCounter.hasReached(this.maxCPSRecalculateTickDelay.getLastIntValue())) {
            this.maxCpsRecalcCounter.reset();
            this.maxCPSRecalculateTickDelay.nextSecureValue();
            this.maxCPS.nextSecureValue();
         }

         n = CombatOptimizationUtil.chooseOptimalClickCount(this.recentClickCounts, (byte)21, this.minStandardDeviation.getValue(), (double)this.maxCPS.getLastIntValue());
      }

      if (ClientManagers.moduleManager.smartClicking.isSuppressingClicks()) {
         n = 0;
      }

      if (objectMouseOver == null || objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
         if (this.airCpsRecalcCounter.hasReached(this.dragCPSInAirRecalculateTickDelay.getLastIntValue())) {
            this.airCpsRecalcCounter.reset();
            this.dragCPSInAirRecalculateTickDelay.nextSecureValue();
            this.dragCPSInAir.nextSecureValue();
         }

         n = this.actionRateAccumulator.accumulate(this.dragCPSInAir.getLastIntValue());
      } else if (n == 0) {
         if (((ClickModuleContext)this.parentModule).isCritFixEnabled() && mc.objectMouseOver != null) {
            boolean flag = ClientManagers.moduleManager.criticals.isToggled()
               || mc.thePlayer.fallDistance > 0.0F
                  && !mc.thePlayer.onGround
                  && !mc.thePlayer.isOnLadder()
                  && !mc.thePlayer.isInWater()
                  && !mc.thePlayer.isPotionActive(Potion.blindness)
                  && mc.thePlayer.ridingEntity == null
                  && mc.objectMouseOver.entityHit instanceof EntityLivingBase;
            if (!((ClickModuleContext)this.parentModule).hasLandedCrit() && flag) {
               n = 1;
            }
         }
      }

      this.recentClickCounts.add(n);

      while (this.recentClickCounts.size() > 21) {
         this.recentClickCounts.remove(0);
      }

      return n;
   }

   @Override
   public void onTick() {
      this.airCpsRecalcCounter.increment();
      this.maxCpsRecalcCounter.increment();
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
      this.recentClickCounts.clear();
   }

   @Override
   public void onDisable() {
      this.recentClickCounts.clear();
   }

}
