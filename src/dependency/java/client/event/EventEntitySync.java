package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.entity.Entity;

public class EventEntitySync extends AbstractEvent {
   public Entity entity;
   public boolean enabled;
   public static final int count = 58;

   public Entity getEntityAsEntity() {
      return this.entity;
   }

   public static void onEntitySyncByEventEntitySync(EventEntitySync event, Entity entity2) {
      event.applyEntityByEntity(entity2);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void onEntitySyncCallback(EventEntitySyncCallback event) {
      event.onEntitySync(this);
   }

   public void setActive(boolean active) {
      this.enabled = active;
   }

   public void call(Object entry) {
      EventEntitySyncCallback event = (EventEntitySyncCallback)entry;
      this.onEntitySyncCallback(event);
   }

   private void applyEntityByEntity(Entity entity2) {
      this.entity = entity2;
      this.enabled = false;
   }

   public static boolean onEntitySync(EventEntitySync event) {
      return event.enabled;
   }
}
