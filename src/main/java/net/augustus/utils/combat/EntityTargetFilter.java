package net.augustus.utils.combat;

import net.augustus.settings.options.EntityTargetFilterOption;
import net.augustus.utils.target.TargetConditionOperator;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EntityTargetFilter {
   private final EntityTargetFilterOption[] options;
   private Predicate predicate;

   private Predicate combineAnd(Predicate predicate2) {
      return this.predicate == null ? predicate2 : this.predicate.and(predicate2);
   }

   public Predicate getPredicate() {
      return this.predicate;
   }

   public boolean matches(Entity entity) {
      return this.predicate.test(entity) && (!(entity instanceof EntityPlayer) || !CombatTargetUtil.hasProtectionEffect((EntityPlayer)entity));
   }

   private Predicate combineOr(Predicate predicate2) {
      return this.predicate == null ? predicate2 : this.predicate.or(predicate2);
   }

   public EntityTargetFilterOption[] getOptions() {
      return this.options;
   }

   public EntityTargetFilter(EntityTargetFilterOption[] entityTargetFilterOptions) {
       super();
      this.options = entityTargetFilterOptions;

      for (EntityTargetFilterOption entityTargetFilterOption : entityTargetFilterOptions) {
         if (entityTargetFilterOption.operator == TargetConditionOperator.OR) {
            this.predicate = this.combineOr(obj -> entityTargetFilterOption.matches((Entity)obj));
         } else {
            this.predicate = this.combineAnd(obj2 -> entityTargetFilterOption.matches((Entity)obj2));
         }
      }
   }
}
