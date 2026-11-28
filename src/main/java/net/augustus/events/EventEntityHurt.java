package net.augustus.events;

import net.augustus.events.listeners.EntityHurtListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.entity.EntityLivingBase;

public class EventEntityHurt extends AbstractEvent<EntityHurtListener> {
   public EntityLivingBase entity;
   public static final int ID = 50;

   private void setEntity(EntityLivingBase entity2) {
      this.entity = entity2;
   }


   public EntityLivingBase getEntity() {
      return this.entity;
   }

   public static void reset(EventEntityHurt event, EntityLivingBase entity2) {
      event.setEntity(entity2);
   }

   @Override
   public void call(EntityHurtListener listener) {
      listener.onEntityHurt(this);
   }
}
