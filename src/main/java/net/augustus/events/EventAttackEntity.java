package net.augustus.events;

import net.augustus.events.listeners.AttackEntityListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;
import net.minecraft.entity.Entity;

public class EventAttackEntity extends AbstractEvent<AttackEntityListener> {
   public static final int ID = 1;
   private StateTypes state;
   private Entity target;


   public Entity getTarget() {
      return this.target;
   }

   @Override
   public void call(AttackEntityListener listener) {
      if (this.state == StateTypes.PRE) {
         listener.onAttackEntity(this);
      } else {
         listener.onPostAttackEntity();
      }
   }

   public static void reset(EventAttackEntity event, Entity entity, StateTypes stateTypes) {
      event.reset(entity, stateTypes);
   }

   private void reset(Entity entity, StateTypes stateTypes) {
      this.state = stateTypes;
      this.target = entity;
   }
}
