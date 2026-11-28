package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventMoveInput;
import net.augustus.events.MoveInputStage;
public interface MoveInputListener {

   default void onPostMoveInput(EventMoveInput event) {
   }

   default void onPostJumpMoveInput(EventMoveInput event) {
   }

   void onPreMoveInput(EventMoveInput event);

   static EventMoveInput postMoveInput(float f, float f2, boolean flag, boolean flag2, MoveInputStage moveInputStage) {
      EventMoveInput event = new EventMoveInput(f, f2, flag, flag2, moveInputStage);
      EventManager.getEventBus().post(21, event);
      return event;
   }
}
