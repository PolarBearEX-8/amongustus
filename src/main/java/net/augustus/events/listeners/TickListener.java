package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventTick;
import net.augustus.events.TickPhase;
public interface TickListener {
   EventTick TICK_EVENT = new EventTick();

   static void postTick(TickPhase tickPhase) {
      EventTick.reset(TICK_EVENT, tickPhase);
      EventManager.getEventBus().post(40, TICK_EVENT);
   }

   default void onTick() {
   }

   default void onPreTick() {
   }

   default void onEarlyTick() {
   }

   default void onPostTick() {
   }

   default void onLateTick() {
   }
}
