package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventPlayerView;
public interface PlayerViewListener {
   EventPlayerView PLAYER_VIEW_EVENT = new EventPlayerView();

   void onPlayerView(EventPlayerView event);

   static EventPlayerView onPlayerView(float f, float f2, float f3) {
      EventPlayerView.reset(PLAYER_VIEW_EVENT, f, f2, f3);
      EventManager.getEventBus().post(63, PLAYER_VIEW_EVENT);
      return PLAYER_VIEW_EVENT;
   }
}
