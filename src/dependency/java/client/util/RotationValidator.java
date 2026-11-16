package client.util;

import client.setting.DisablerUndefinedType;
import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public final class RotationValidator implements Predicate {
   public boolean matchesEntity(Entity entity) {
      return entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getUNDEAD() != DisablerUndefinedType.UNDEAD;
   }

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }
}
