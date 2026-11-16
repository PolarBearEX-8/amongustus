package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;

public class HitBoxDirector extends ExtendedClassEntry {
   public HitBoxDirector(AuxiliaryUUIDEntry auxiliaryUUIDEntry, Class clazz, int count, boolean flag, boolean flag2, Predicate predicate) {
      super(auxiliaryUUIDEntry, clazz, count, flag, flag2, predicate);
      this.predicate = new PredicateFilter(this, predicate, auxiliaryUUIDEntry);
   }

   public static boolean matchesHitBoxDirector(HitBoxDirector hitBoxDirector, EntityLivingBase livingEntity, boolean flag) {
      return hitBoxDirector.matchesEntityLivingBase(livingEntity, flag);
   }

   public static double computeValue(HitBoxDirector hitBoxDirector) {
      return hitBoxDirector.getValue();
   }
}
