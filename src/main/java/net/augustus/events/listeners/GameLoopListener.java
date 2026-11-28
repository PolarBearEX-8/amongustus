package net.augustus.events.listeners;


import net.augustus.events.EventGameLoop;
import net.augustus.events.EventManager;
public interface GameLoopListener {
   EventGameLoop GAME_LOOP_EVENT = new EventGameLoop();

   void onGameLoop(EventGameLoop event);

   static EventGameLoop onGameLoop(int n, float f) {
      EventGameLoop.reset(GAME_LOOP_EVENT, n, f);
      EventManager.getEventBus().post(16, GAME_LOOP_EVENT);
      return GAME_LOOP_EVENT;
   }
}
