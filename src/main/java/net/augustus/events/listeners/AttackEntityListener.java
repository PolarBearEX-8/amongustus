package net.augustus.events.listeners;

import net.augustus.events.EventAttackEntity;
import net.augustus.events.EventManager;
import de.florianmichael.dietrichevents2.StateTypes;
import net.minecraft.entity.Entity;

public interface AttackEntityListener {
   EventAttackEntity ATTACK_ENTITY_EVENT = new EventAttackEntity();

   default void onPostAttackEntity() {
   }

   static void onAttackEntity(Entity entity, StateTypes stateTypes) {
      EventAttackEntity.reset(ATTACK_ENTITY_EVENT, entity, stateTypes);
      EventManager.getEventBus().post(1, ATTACK_ENTITY_EVENT);
   }

   void onAttackEntity(EventAttackEntity event);
}
