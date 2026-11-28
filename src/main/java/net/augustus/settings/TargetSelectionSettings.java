package net.augustus.settings;

import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.utils.combat.TargetSortMode;
import net.augustus.utils.rotation.Rotation;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Module;
import net.minecraft.entity.Entity;

public class TargetSelectionSettings extends ModuleSettingGroupBase {
   private NamedOptionSetting targetSortingSetting;
   private BooleanValue switchOnlyToTargetsInRange;
   private final String namePrefix;
   private Entity currentTarget;
   private final EntityTargetFilter targetFilter;
   private final MultiOptionSetting targets;

   public TargetSelectionSettings(Module module, String str, EntityTargetFilter entityTargetFilter) {
       this(module, str, entityTargetFilter, null);
   }

   public TargetSelectionSettings withSorting() {
      NamedOptionSetting namedOptionSetting = (NamedOptionSetting)(new NamedOptionSetting(
            this.getParentModule(), this.namePrefix + "TargetSorting", TargetSortMode.DISTANCE, TargetSortMode.values(), this.getParentGroup()
         ))
         .markVisualOnly();
      this.targetSortingSetting = (NamedOptionSetting)namedOptionSetting.withDisplayName("TargetSorting");
      this.switchOnlyToTargetsInRange = (BooleanValue)((BooleanValue)((BooleanValue)((BooleanValue)(new BooleanValue(this.getParentModule(), this.namePrefix + "SwitchOnlyToTargetsInRange", false, this.getParentGroup())).markVisualOnly()).withVisibility(() -> this.isSwitchInRangeVisible())).withDisplayName("SwitchOnlyToTargetsInRange")).markVisualOnly();
      return this;
   }

   private boolean isSwitchInRangeVisible() {
      return this.targetSortingSetting.getSelectedOption() == TargetSortMode.ULTIMATE_TARGET && this.targetSortingSetting.isVisible();
   }

   public TargetSelectionSettings(Module module, EntityTargetFilter entityTargetFilter) {
       this(module, "", entityTargetFilter);
   }

   public void setCurrentTarget(Entity entity) {
      this.currentTarget = entity;
   }

   public ToDoubleFunction createTargetComparatorKey(TargetSortMode targetSortMode, Rotation rotation, double d) {
      if (targetSortMode != null) {
         return targetSortMode.createComparatorKey(rotation, this.currentTarget, d, this.switchOnlyToTargetsInRange.getValue());
      } else {
         return obj -> 100000.0;
      }
   }

   public EntityTargetFilter getTargetFilter() {
      return this.targetFilter;
   }

   public TargetSelectionSettings(Module module, String str, EntityTargetFilter entityTargetFilter, SettingGroup doubleValue) {
       super(module, doubleValue);
      this.targets = (MultiOptionSetting)new MultiOptionSetting(this.getParentModule(), str + "Targets", entityTargetFilter.getOptions(), doubleValue).named("Targets").markVisualOnly();
      this.namePrefix = str;
      this.targetFilter = entityTargetFilter;
   }

   public TargetSelectionSettings withTargetsDisplayName(String targetsDisplayName) {
      this.targets.named(targetsDisplayName);
      return this;
   }

   public ToDoubleFunction createTargetComparatorKey(Rotation rotation, double d) {
      if (this.targetSortingSetting == null) {
         throw new UnsupportedOperationException("Sorting not activated in the target selection");
      } else {
         return this.createTargetComparatorKey((TargetSortMode)this.targetSortingSetting.getSelectedOption(), rotation, d);
      }
   }

   public TargetSelectionSettings withVisibility(SettingVisibilityCondition condition) {
      if (this.targetSortingSetting != null) {
         this.targetSortingSetting.withVisibility(condition);
      }

      this.targets.visibleWhen(condition);
      return this;
   }

   public TargetSelectionSettings(Module module, EntityTargetFilter entityTargetFilter, SettingGroup doubleValue) {
       this(module, "", entityTargetFilter, doubleValue);
   }


}
