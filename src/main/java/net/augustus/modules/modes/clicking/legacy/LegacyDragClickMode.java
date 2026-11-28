package net.augustus.modules.modes.clicking.legacy;

import net.augustus.ClientManagers;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.utils.TickCounter;
import net.augustus.utils.combat.CombatOptimizationUtil;
import java.util.ArrayList;
import java.util.function.BiFunction;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

public class LegacyDragClickMode extends AbstractLegacyClickMode {
   private final RandomizedRangeSettings dragCPSInAir;
   private final RandomizedRangeSettings dragCPSInAirRecalculateTickDelay;
   private final RandomizedRangeSettings dragMaxCPS;
   private final RandomizedRangeSettings dragMaxCPSRecalculateTickDelay;
   private final DoubleValue dragMinStandardDeviation;
   private final ActionRateAccumulator actionRateAccumulator;
   private final ArrayList recentClickCounts;
   private final TickCounter maxCpsRecalcCounter;
   private final TickCounter airCpsRecalcCounter;

   public LegacyDragClickMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "DragClick");
      this.dragCPSInAir = (RandomizedRangeSettings)new RandomizedRangeSettings(this.parentModule, "DragCPSInAir", 4.0, 8.0, 0.0, 20.0, 0, this.modeSetting).withDisplayNamePrefix("CPSInAir");
      RandomizedRangeSettings randomizedRangeSettings2 = new RandomizedRangeSettings(this.parentModule, "DragCPSInAirRecalculateTickDelay", 10.0, 30.0, 0.0, 60.0, 0, this.modeSetting);
      this.dragCPSInAirRecalculateTickDelay = ((RandomizedRangeSettings)randomizedRangeSettings2.withDisplayNamePrefix("CPSInAirRecalculateTickDelay")).withoutNotInARow();
      randomizedRangeSettings2 = new RandomizedRangeSettings(this.parentModule, "DragMaxCPS", 18.0, 20.0, 1.0, 40.0, 0, this.modeSetting);
      this.dragMaxCPS = (RandomizedRangeSettings)randomizedRangeSettings2.withDisplayNamePrefix("MaxCPS");
      randomizedRangeSettings2 = (new RandomizedRangeSettings(this.parentModule, "DragMaxCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.modeSetting)).withoutNotInARow();
      this.dragMaxCPSRecalculateTickDelay = (RandomizedRangeSettings)randomizedRangeSettings2.withDisplayNamePrefix("MaxCPSRecalculateTickDelay");
      this.dragMinStandardDeviation = new DoubleValue(this.parentModule, "DragMinStandardDeviation", 0.5, 0.0, 1.0, 2, this.modeSetting).named("MinStandardDeviation");
      this.actionRateAccumulator = new ActionRateAccumulator(20, 0.0);
      this.recentClickCounts = new ArrayList();
      this.maxCpsRecalcCounter = new TickCounter();
      this.airCpsRecalcCounter = new TickCounter();
   }

   @Override
   public int computeClicks(EntityLivingBase entity) {
      MovingObjectPosition objectMouseOver = mc.objectMouseOver;
      int n;
      if (this.recentClickCounts.size() < 5) {
         n = 1;
      } else {
         if (this.maxCpsRecalcCounter.hasReached(this.dragMaxCPSRecalculateTickDelay.getLastIntValue())) {
            this.maxCpsRecalcCounter.reset();
            this.dragMaxCPSRecalculateTickDelay.nextSecureValue();
            this.dragMaxCPS.nextSecureValue();
         }

         n = CombatOptimizationUtil.chooseOptimalClickCount(this.recentClickCounts, (byte)21, this.dragMinStandardDeviation.getValue(), (double)this.dragMaxCPS.getLastIntValue());
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
      }

      this.recentClickCounts.add(n);

      while (this.recentClickCounts.size() > 21) {
         this.recentClickCounts.remove(0);
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
      return (obj, obj2) -> new LegacyDragClickMode((Module)obj, (SettingGroup)obj2);
   }

}
