package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.entity.EntityLivingBase;

public class EventTargetEntity extends AbstractEvent {
   public static final int count = 50;
   public EntityLivingBase entityLivingBase;

   public EntityLivingBase getTargetEntity() {
      return this.entityLivingBase;
   }

   public void onTargetEntityCallback(EventTargetEntityCallback event) {
      event.onTargetEntity(this);
   }

   public void call(Object entry) {
      EventTargetEntityCallback event = (EventTargetEntityCallback)entry;
      this.onTargetEntityCallback(event);
   }

   public static void onTargetEntity(EventTargetEntity event, EntityLivingBase livingEntity) {
      event.setEntityLivingBase(livingEntity);
   }

   private void setEntityLivingBase(EntityLivingBase entityLivingBase2) {
      this.entityLivingBase = entityLivingBase2;
   }
}
