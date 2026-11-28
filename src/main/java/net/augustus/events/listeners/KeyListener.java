package net.augustus.events.listeners;


import net.augustus.events.EventKey;
import net.augustus.events.EventManager;
import net.augustus.events.KeyInputSource;
public interface KeyListener {
   EventKey KEY_EVENT = new EventKey();

   default void onMouseKey(EventKey event) {
   }

   default void onToggleKey(EventKey event) {
   }

   static EventKey postKey(int n, boolean flag, boolean flag2, KeyInputSource keyInputSource) {
      EventKey.reset(KEY_EVENT, keyInputSource, n, flag, flag2);
      EventManager.getEventBus().post(14, KEY_EVENT);
      return KEY_EVENT;
   }

   default void onKeyboardKey(EventKey event) {
   }
}
