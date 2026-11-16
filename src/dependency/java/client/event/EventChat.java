package client.event;

import client.setting.RenderKeybindProperty;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventChat extends AbstractEvent {
   public static final int count = 2;
   public boolean enabled;
   public double value;
   public boolean enabled3;
   public boolean enabled2 = false;
   public RenderKeybindProperty renderKeybindProperty;

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public void setEnabled3(boolean enabled4) {
      this.enabled3 = enabled4;
   }

   public static void onChat(EventChat event, RenderKeybindProperty renderKeybindProperty2) {
      event.applyRenderKeybindProperty(renderKeybindProperty2);
   }

   public void processAction(double offset) {
      this.value = offset;
   }

   public void call(Object entry) {
      EventChatCallback event = (EventChatCallback)entry;
      this.onChatCallback(event);
   }

   public double getValue() {
      return this.value;
   }

   public void onChatCallback(EventChatCallback event) {
      if (this.renderKeybindProperty == RenderKeybindProperty.FIRST) {
         event.onChat2(this);
      } else {
         event.onChat(this);
      }
   }

   public void setActive(boolean active) {
      this.enabled2 = active;
   }

   private void applyRenderKeybindProperty(RenderKeybindProperty renderKeybindProperty2) {
      this.renderKeybindProperty = renderKeybindProperty2;
      this.enabled2 = false;
      this.enabled = false;
      this.value = 0.6;
   }

   public boolean canExecute() {
      return this.enabled3;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(boolean enabled4) {
      this.enabled = enabled4;
   }

   public EventChat() {
      this.enabled = false;
      this.value = 0.6;
   }
}
