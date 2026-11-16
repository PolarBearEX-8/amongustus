package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;
import net.minecraft.entity.Entity;

public class EventAttackEntity extends AbstractEvent {
   public static final int count = 1;
   public Entity entity;
   public StateTypes stateTypes;

   public Entity getEntity() {
      return this.entity;
   }

   public static void onAttackEntityByEventAttackEntit(EventAttackEntity event, Entity entity2, StateTypes stateTypes2) {
      event.applyEntityByEntity(entity2, stateTypes2);
   }

   public void call(Object entry) {
      AttackEntityListener attackEntityListener = (AttackEntityListener)entry;
      this.applyAttackEntityListener(attackEntityListener);
   }

   private void applyEntityByEntity(Entity entity2, StateTypes stateTypes2) {
      this.stateTypes = stateTypes2;
      this.entity = entity2;
   }

   public void applyAttackEntityListener(AttackEntityListener attackEntityListener) {
      if (this.stateTypes == StateTypes.PRE) {
         attackEntityListener.onAttackEntity(this);
      } else {
         attackEntityListener.onPostAttackEntity();
      }
   }
}
