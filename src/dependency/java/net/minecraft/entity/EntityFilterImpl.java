package net.minecraft.entity;

import client.util.SecondaryClientManager;
import com.google.common.base.Predicate;

public final class EntityFilterImpl implements Predicate {
   public final String labelText;
   public final boolean enabled;

   public boolean matchesEntity(Entity entity) {
      if (!(entity instanceof EntityLivingBase)) {
         return false;
      } else {
         SecondaryClientManager secondaryClientManager = ((EntityLivingBase)entity).getSecondaryClientManager();
         return (secondaryClientManager == null ? "" : secondaryClientManager.getLabelText2()).equals(this.labelText) != this.enabled;
      }
   }

   public EntityFilterImpl(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }
}
