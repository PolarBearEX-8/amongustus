package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventSound;
public interface SoundListener {
   EventSound SOUND_EVENT = new EventSound();

   static void onSound(double d, double d2, double d3, String str) {
      EventSound.reset(SOUND_EVENT, d, d2, d3, str);
      EventManager.getEventBus().post(35, SOUND_EVENT);
   }

   void onSound(EventSound event);
}
