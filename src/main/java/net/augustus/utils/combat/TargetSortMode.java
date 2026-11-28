package net.augustus.utils.combat;

import net.augustus.settings.EnumSettingOption;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.target.TargetPriorityMetrics;
import net.augustus.utils.target.TargetSortEvaluator;
import java.util.function.ToDoubleFunction;
import net.minecraft.entity.Entity;

public enum TargetSortMode implements EnumSettingOption {
   FOV("Fov", (rotation, entity, d, flag) -> createFovKey(rotation, entity, d, flag)),
   SHORT_AIM("ShortAim", (rotation, entity, d, flag) -> createShortAimKey(rotation, entity, d, flag)),
   SHORT_AIM_SWITCH("ShortAimSwitch", (rotation, entity, d, flag) -> {
      return obj -> scoreShortAimSwitch(rotation, d, (Entity)obj);
   }),
   ULTIMATE_TARGET("UltimateTarget", (rotation, entity, d, flag) -> createUltimateTargetKey(rotation, entity, d, flag)),
   SINGLE_TARGET("SingleTarget", (rotation, entity, d, flag) -> createSingleTargetKey(rotation, entity, d, flag)),
   HEALTH("Health", (rotation, entity, d, flag) -> {
      return obj -> TargetPriorityMetrics.healthScore((Entity)obj);
   }),
   DISTANCE("Distance", (rotation, entity, d, flag) -> createDistanceKey(rotation, entity, d, flag));
   public final TargetSortEvaluator evaluator;
   public final String displayName;

   @Override
   public String getDisplayName() {
      return this.displayName;
   }

   private static double scoreShortAimSwitch(Rotation rotation, double d, Entity entity) {
      return TargetPriorityMetrics.shortAimSwitchScore(entity, rotation, d);
   }

   public ToDoubleFunction createComparatorKey(Rotation rotation, Entity entity, double d, boolean flag) {
      return this.evaluator.createKeyExtractor(rotation, entity, d, flag);
   }

   private static ToDoubleFunction createDistanceKey(Rotation rotation, Entity entity, double d, boolean flag) {
      return obj -> TargetPriorityMetrics.distanceScore((Entity)obj);
   }

   private static double scoreShortAim(Rotation rotation, double d, Entity entity) {
      return TargetPriorityMetrics.shortAimScore(entity, rotation, d);
   }

   private TargetSortMode(String str, TargetSortEvaluator targetSortEvaluator) {
      this.displayName = str;
      this.evaluator = targetSortEvaluator;
   }

   private static ToDoubleFunction createShortAimKey(Rotation rotation, Entity entity, double d, boolean flag) {
      return obj -> scoreShortAim(rotation, d, (Entity)obj);
   }

   private static ToDoubleFunction createUltimateTargetKey(Rotation rotation, Entity entity, double d, boolean flag) {
      return obj -> scoreUltimateTarget(rotation, entity, d, flag, (Entity)obj);
   }

   private static ToDoubleFunction createSingleTargetKey(Rotation rotation, Entity entity, double d, boolean flag) {
      return obj -> scoreSingleTarget(entity, (Entity)obj);
   }

   private static double scoreUltimateTarget(Rotation rotation, Entity entity, double d, boolean flag, Entity entity2) {
      return TargetPriorityMetrics.ultimateTargetScore(entity2, rotation, entity, d, flag);
   }

   private static ToDoubleFunction createFovKey(Rotation rotation, Entity entity, double d, boolean flag) {
      return obj -> TargetPriorityMetrics.fovScore((Entity)obj);
   }

   private static double scoreSingleTarget(Entity entity, Entity entity2) {
      return TargetPriorityMetrics.singleTargetScore(entity2, entity);
   }

}
