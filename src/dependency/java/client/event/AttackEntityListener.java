package client.event;

import de.florianmichael.dietrichevents2.StateTypes;
import net.minecraft.entity.Entity;

public interface AttackEntityListener {
   EventAttackEntity eventAttackEntity = new EventAttackEntity();

   void onAttackEntity(EventAttackEntity event);

   static void applyEntity(int count, int count2, Entity entity, StateTypes stateTypes, char character) {
      EventAttackEntity.onAttackEntityByEventAttackEntit(eventAttackEntity, entity, stateTypes);
      EventBusManager.getDietrichEvents2().post(1, eventAttackEntity);
   }

   default void onPostAttackEntity() {
   }
}
