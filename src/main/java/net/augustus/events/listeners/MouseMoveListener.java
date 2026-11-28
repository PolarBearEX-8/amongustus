package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventMouseMove;
import de.florianmichael.dietrichevents2.StateTypes;

public interface MouseMoveListener {
   EventMouseMove MOUSE_MOVE_EVENT = new EventMouseMove();

   void onMouseMove(EventMouseMove event);

   static EventMouseMove onMouseMove(int n, int n2, float f, StateTypes stateTypes) {
      EventMouseMove.reset(MOUSE_MOVE_EVENT, n, n2, f, stateTypes);
      EventManager.getEventBus().post(19, MOUSE_MOVE_EVENT);
      return MOUSE_MOVE_EVENT;
   }

   default void onPostMouseMove() {
   }
}
