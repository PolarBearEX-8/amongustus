package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventNoClip;
public interface NoClipListener {
   EventNoClip NO_CLIP_EVENT = new EventNoClip();

   static void postNoClip() {
      EventManager.getEventBus().post(27, NO_CLIP_EVENT);
   }

   void onNoClip();
}
