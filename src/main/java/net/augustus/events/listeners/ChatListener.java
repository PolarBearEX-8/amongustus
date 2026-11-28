package net.augustus.events.listeners;


import net.augustus.events.EventChat;
import net.augustus.events.EventManager;
public interface ChatListener {
   EventChat CHAT_EVENT = new EventChat();

   void onChat(EventChat event);

   static EventChat postChat(String str) {
      EventChat.reset(CHAT_EVENT, str);
      EventManager.getEventBus().post(5, CHAT_EVENT);
      return CHAT_EVENT;
   }
}
