package net.augustus.modules.legit;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.ClientManagers;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.settings.options.RangeBuildSlotOption;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class Range extends Module implements LoadWorldListener, TickListener, AttackEntityListener {
   private final SettingGroup combat;
   private final StringValue rangeMode;
   private final DoubleValue rangeInternal;
   private final RangeSetting baseRange;
   private final DoubleValue chanceOfExtendedRange;
   private final BooleanValue onlyWhileSprinting;
   public final BooleanValue throughWalls;
   private final SettingGroup interaction;
   public final DoubleValue buildRange;
   public final MultiOptionSetting slotsToApplyBuildRange;
   private double currentRange;
   private boolean useExtendedRange;
   private EntityLivingBase lastAttackedTarget;
   private final TimeHelper timeHelper;
   private final ActionRateAccumulator actionRateAccumulator;

   public Range() {
       super("Range", "Distanz", new Color(65, 140, 35), Categorys.LEGIT);
      this.combat = new SettingGroup(this, "Combat", true);
      this.rangeMode = new StringValue(this, "RangeMode", "Default", new String[]{"Default", "Dual"}, this.combat);
      DoubleValue rangeInternalSetting = new DoubleValue(this, "RangeInternal", 3.0, 3.0, 4.5, 2, this.combat).named("Range");
      SettingVisibilityCondition condition = () -> {
         return this.rangeMode.getSelectedValue().equals("Default");
      };
      this.rangeInternal = rangeInternalSetting.visibleWhen(condition);
      this.baseRange = new RangeSetting(this, "BaseRange", "ExtendedRange", 3.0, 3.7, 3.0, 6.0, 2, this.combat).visibleWhen(() -> this.isBaseRangeVisible());
      this.chanceOfExtendedRange = new DoubleValue(this, "Chance of Extended Range", 50.0, 0.0, 100.0, 0, this.combat).visibleWhen(() -> this.isChanceOfExtendedRangeVisible());
      this.onlyWhileSprinting = new BooleanValue(this, "OnlyWhileSprinting", false, this.combat);
      this.throughWalls = new BooleanValue(this, "ThroughWalls", false, this.combat);
      this.interaction = new SettingGroup(this, "Interaction", true);
      this.buildRange = new DoubleValue(this, "BuildRange", 4.5, 4.5, 6.0, 2, this.interaction);
      this.slotsToApplyBuildRange = (MultiOptionSetting)(new MultiOptionSetting(this, "SlotsToApplyBuildRange", RangeBuildSlotOption.values(), this.interaction)).markVisualOnly();
      this.currentRange = 3.0;
      this.useExtendedRange = false;
      this.lastAttackedTarget = null;
      this.timeHelper = new TimeHelper();
      this.actionRateAccumulator = new ActionRateAccumulator(100, 0.0);
   }

   @Override
   public void onEnable() {
      this.currentRange = 3.0;
      this.useExtendedRange = false;
   }

   @Override
   public void onDisable() {
      this.lastAttackedTarget = null;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(1);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onTick() {
      this.setSuffix("" + this.currentRange);
      if (this.rangeMode.getSelectedValue().equals("Default")) {
         this.currentRange = this.rangeInternal.getValue();
      } else {
         if (this.timeHelper.reached(3000L)) {
            this.lastAttackedTarget = null;
         }

         if (this.lastAttackedTarget != null) {
            double d;
            if (this.useExtendedRange) {
               d = this.baseRange.getMaxValue();
            } else {
               d = this.baseRange.getMinValue();
            }

            this.currentRange = d;
            if (this.lastAttackedTarget.hurtTime == 9) {
               int n = MathHelper.clamp_int((int)this.chanceOfExtendedRange.getValue(), 0, 100);
               this.useExtendedRange = this.actionRateAccumulator.shouldTrigger(n);
            }
         }
      }
   }

   public boolean isCombatRangeActive() {
      return ClientManagers.moduleManager.range.isToggled() && (!ClientManagers.moduleManager.range.onlyWhileSprinting.getValue() || mc.thePlayer.isSprinting());
   }

   public double getCurrentRange() {
      return this.currentRange;
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (event.getTarget() instanceof EntityLivingBase) {
         this.lastAttackedTarget = (EntityLivingBase)event.getTarget();
         this.timeHelper.reset();
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.lastAttackedTarget = null;
   }

   public double getBuildRange(double d) {
      return this.isToggled() && RangeBuildSlotOption.isCurrentSlotAllowed() ? Math.max(this.buildRange.getValue(), 4.5) : d;
   }

   private boolean isChanceOfExtendedRangeVisible() {
      return this.rangeMode.getSelectedValue().equals("Dual");
   }

   private boolean isBaseRangeVisible() {
      return this.rangeMode.getSelectedValue().equals("Dual");
   }





}
