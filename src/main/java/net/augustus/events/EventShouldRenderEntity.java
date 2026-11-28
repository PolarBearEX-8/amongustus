package net.augustus.events;

import net.augustus.events.listeners.ShouldRenderEntityListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.entity.Entity;

public class EventShouldRenderEntity extends AbstractEvent<ShouldRenderEntityListener> {
   public static final int ID = 58;
   private boolean shouldRender;
   private Entity entity;

   public static boolean isShouldRender(EventShouldRenderEntity event) {
      return event.shouldRender;
   }

   private void reset(Entity entity2) {
      this.entity = entity2;
      this.shouldRender = false;
   }

   @Override
   public void call(ShouldRenderEntityListener listener) {
      listener.onShouldRenderEntity(this);
   }

   public boolean shouldRender() {
      return this.shouldRender;
   }

   public static void reset(EventShouldRenderEntity event, Entity entity2) {
      event.reset(entity2);
   }

   public void setShouldRender(boolean flag) {
      this.shouldRender = flag;
   }

   public Entity getEntity() {
      return this.entity;
   }

}
