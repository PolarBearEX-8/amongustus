package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventWorldColor;
import net.augustus.events.WorldColorType;
public interface WorldColorListener {
   EventWorldColor WORLD_COLOR_EVENT = new EventWorldColor();

   default void onWorldColor(EventWorldColor event) {
   }

   default void onFogColor(EventWorldColor event) {
   }

   static EventWorldColor onWorldColor(float f, float f2, float f3, WorldColorType worldColorType) {
      EventWorldColor.reset(WORLD_COLOR_EVENT, f, f2, f3, worldColorType);
      EventManager.getEventBus().post(44, WORLD_COLOR_EVENT);
      return WORLD_COLOR_EVENT;
   }
}
