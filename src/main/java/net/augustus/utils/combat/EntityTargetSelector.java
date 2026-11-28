package net.augustus.utils.combat;

import net.augustus.Augustus;
import net.augustus.utils.rotation.RotationUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityTargetSelector implements MC {
   private ToDoubleFunction sortKeyExtractor = null;
   private final List entities;
   private Predicate filter;

   public List toList() {
      ArrayList list = new ArrayList();
      this.forEach(list::add);
      return list;
   }

   public static EntityTargetSelector of(List list) {
      return new EntityTargetSelector(list);
   }

   public EntityTargetSelector filterFocus() {
      this.filter = this.filter.and(obj -> EntityTypeUtil.isFocusedTarget((Entity)obj));
      return this;
   }

   public EntityTargetSelector minDistance(double d) {
      this.filter = this.filter.and(obj -> {
         return (double)mc.thePlayer.getDistanceToEntityBox((Entity)obj) >= d;
      });
      return this;
   }

   private Stream streamEntities() {
      this.filter = this.filter.and(obj -> isNotProtectedPlayer((Entity)obj));
      return this.entities.stream();
   }

   public EntityTargetSelector minTicksExisted(int n) {
      this.filter = this.filter.and(obj -> {
         return ((Entity)obj).ticksExisted >= n;
      });
      return this;
   }

   public Optional findBest() {
      return this.sortKeyExtractor != null ? this.streamEntities().filter(this.filter).min(Comparator.comparingDouble(this.sortKeyExtractor)) : this.streamEntities().filter(this.filter).findFirst();
   }

   public EntityTargetSelector maxDistance(double d) {
      this.filter = this.filter.and(obj -> {
         return (double)mc.thePlayer.getDistanceToEntityBox((Entity)obj) <= d;
      });
      return this;
   }

   public EntityTargetSelector sortBy(ToDoubleFunction toDoubleFunction) {
      Objects.requireNonNull(toDoubleFunction, "sorter cannot be null");
      this.sortKeyExtractor = toDoubleFunction;
      return this;
   }

   public void forEach(Consumer consumer) {
      Objects.requireNonNull(consumer, "consumer cannot be null");
      if (this.sortKeyExtractor != null) {
         this.streamEntities().filter(this.filter).sorted(Comparator.comparingDouble(this.sortKeyExtractor)).forEach(consumer);
      } else {
         this.streamEntities().filter(this.filter).forEach(consumer);
      }
   }

   public EntityTargetSelector withinFov(double d, double d2) {
      this.filter = this.filter.and(obj -> isWithinEntityFov(d, d2, (Entity)obj));
      return this;
   }

   public EntityTargetSelector withTargetFilter(EntityTargetFilter entityTargetFilter) {
      Objects.requireNonNull(entityTargetFilter, "targets cannot be null");
      this.filter = this.filter.and(entityTargetFilter.getPredicate());
      return this;
   }

   public EntityTargetSelector filter(Predicate predicate) {
      Objects.requireNonNull(predicate, "filter cannot be null");
      this.filter = this.filter.and(predicate);
      return this;
   }

   public EntityTargetSelector throughWalls(boolean flag) {
      if (!flag) {
         this.filter = this.filter.and(obj -> mc.thePlayer.canEntityBeSeen((Entity)obj));
      }

      return this;
   }

   public EntityTargetSelector maxDeathTime(int n) {
      this.filter = this.filter.and(obj -> {
         return !(obj instanceof EntityLivingBase) || ((EntityLivingBase)obj).deathTime <= n && ((Entity)obj).ticksExisted > 1;
      });
      return this;
   }

   private static boolean isWithinHitboxFov(double d, double d2, Entity entity) {
      float[] angleDiffToEntityEyes = RotationUtil.getAngleDiffToEntityEyes(entity, Augustus.getInstance().getYawPitchHelper().realYaw, Augustus.getInstance().getYawPitchHelper().realPitch);
      return (double)Math.abs(angleDiffToEntityEyes[0]) <= d && (double)Math.abs(angleDiffToEntityEyes[1]) <= d2;
   }

   private static boolean isWithinEntityFov(double d, double d2, Entity entity) {
      float[] floats = RotationUtil.getAngleDiffToEntityNearestPoint(entity, Augustus.getInstance().getYawPitchHelper().realYaw, Augustus.getInstance().getYawPitchHelper().realPitch);
      return (double)Math.abs(floats[0]) <= d && (double)Math.abs(floats[1]) <= d2;
   }

   private static boolean isNotProtectedPlayer(Entity entity) {
      return !(entity instanceof EntityPlayer) || !CombatTargetUtil.hasProtectionEffect((EntityPlayer)entity);
   }

   public EntityTargetSelector withinHitboxFov(double d, double d2) {
      this.filter = this.filter.and(obj -> isWithinHitboxFov(d, d2, (Entity)obj));
      return this;
   }

   private EntityTargetSelector(List list) {
      Objects.requireNonNull(list, "entities cannot be null");
      this.entities = list;
      this.filter = obj -> true;
   }


}
