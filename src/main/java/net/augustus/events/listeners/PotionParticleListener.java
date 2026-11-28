package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventPotionParticle;
import net.minecraft.entity.Entity;

public interface PotionParticleListener {
   EventPotionParticle POTION_PARTICLE_EVENT = new EventPotionParticle();

   static EventPotionParticle postPotionParticle(Entity entity) {
      EventPotionParticle.reset(POTION_PARTICLE_EVENT, entity);
      EventManager.getEventBus().post(65, POTION_PARTICLE_EVENT);
      return POTION_PARTICLE_EVENT;
   }

   void onPotionParticle(EventPotionParticle event);
}
