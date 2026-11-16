package client.event;

import client.setting.CombatModeProperty;

public interface MoveInputListener {
   EventMoveInput eventMoveInput = new EventMoveInput();

   default void onMoveInput(EventMoveInput event) {
   }

   static EventMoveInput resolveEventMoveInput(CombatModeProperty combatModeProperty) {
      EventMoveInput.onMoveInput(eventMoveInput, combatModeProperty);
      EventBusManager.getDietrichEvents2().post(36, eventMoveInput);
      return eventMoveInput;
   }

   default void onPreMoveInput(EventMoveInput event) {
   }

   default void onPostMoveInput(EventMoveInput event) {
   }
}
