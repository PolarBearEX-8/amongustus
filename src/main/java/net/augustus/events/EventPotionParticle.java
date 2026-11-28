package net.augustus.events;

import net.augustus.events.listeners.PotionParticleListener;
import de.florianmichael.dietrichevents2.CancellableEvent;
import net.minecraft.entity.Entity;

public class EventPotionParticle extends CancellableEvent<PotionParticleListener> {
   private Entity entity;
   public static final int ID = 65;


   public static void reset(EventPotionParticle event, Entity entity2) {
      event.reset(entity2);
   }

   @Override
   public void call(PotionParticleListener listener) {
      listener.onPotionParticle(this);
   }

   private void reset(Entity entity2) {
      this.entity = entity2;
      this.setCancelled(false);
   }

   public Entity getEntity() {
      return this.entity;
   }
}
