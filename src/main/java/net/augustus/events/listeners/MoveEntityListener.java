package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.MoveEntityPhase;
public interface MoveEntityListener {
   EventMoveEntity MOVE_ENTITY_EVENT = new EventMoveEntity();

   default void onPreMoveEntity(EventMoveEntity event) {
   }

   static EventMoveEntity onMoveEntity(double d, double d2, double d3, MoveEntityPhase moveEntityPhase) {
      EventMoveEntity.reset(MOVE_ENTITY_EVENT, d, d2, d3, moveEntityPhase);
      EventManager.getEventBus().post(20, MOVE_ENTITY_EVENT);
      return MOVE_ENTITY_EVENT;
   }

   default void onPostMoveEntity(EventMoveEntity event) {
   }
}
