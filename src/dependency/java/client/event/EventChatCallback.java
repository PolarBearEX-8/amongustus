package client.event;

import client.setting.RenderKeybindProperty;

public interface EventChatCallback {
   EventChat eventChat = new EventChat();

   static EventChat resolveEventChat(RenderKeybindProperty renderKeybindProperty) {
      return resolveEventChat(renderKeybindProperty, false);
   }

   static EventChat resolveEventChat(RenderKeybindProperty renderKeybindProperty, boolean flag) {
      EventChat.onChat(eventChat, renderKeybindProperty);
      eventChat.setEnabled3(flag);
      EventBusManager.getDietrichEvents2().post(2, eventChat);
      return eventChat;
   }

   default void onChat(EventChat event) {
   }

   default void onChat2(EventChat event) {
   }
}
