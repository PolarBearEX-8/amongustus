package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventNightVision;
import net.augustus.events.NightVisionPhase;
public interface NightVisionListener {
   EventNightVision NIGHT_VISION_EVENT = new EventNightVision();

   static EventNightVision onNightVision(boolean flag, NightVisionPhase nightVisionPhase) {
      EventNightVision.reset(NIGHT_VISION_EVENT, flag, nightVisionPhase);
      EventManager.getEventBus().post(22, NIGHT_VISION_EVENT);
      return NIGHT_VISION_EVENT;
   }

   void onNightVision(EventNightVision event);

   void onFogNightVision(EventNightVision event);
}
