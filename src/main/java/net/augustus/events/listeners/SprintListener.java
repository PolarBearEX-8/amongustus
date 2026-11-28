package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventSprint;
import net.augustus.events.SprintEventType;
public interface SprintListener {
   EventSprint SPRINT_EVENT = new EventSprint();

   default void onJumpSprint(EventSprint event) {
   }

   static EventSprint postSprint(SprintEventType sprintEventType) {
      EventSprint.reset(SPRINT_EVENT, sprintEventType);
      EventManager.getEventBus().post(36, SPRINT_EVENT);
      return SPRINT_EVENT;
   }

   default void onMotionSprint(EventSprint event) {
   }

   default void onSprint(EventSprint event) {
   }
}
