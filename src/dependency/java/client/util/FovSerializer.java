package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;

public class FovSerializer implements Predicate {
   public EntityGuardian entityGuardian;

   public boolean apply(Object entry) {
      return this.matchesEntityLivingBase((EntityLivingBase)entry);
   }

   public FovSerializer(EntityGuardian entityGuardian2) {
      this.entityGuardian = entityGuardian2;
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      return (livingEntity instanceof EntityPlayer || livingEntity instanceof EntitySquid) && livingEntity.getCoordinate(this.entityGuardian) > 9.0;
   }
}
