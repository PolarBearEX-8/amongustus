package client.util;

import client.setting.ClientSettingsShowTextType;
import net.minecraft.util.IChatComponent;

public class HoverEventActionManager {
   public final ClientSettingsShowTextType clientSettingsShowTextType;
   public final IChatComponent iChatComponent;

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public ClientSettingsShowTextType getClientSettingsShowTextType() {
      return this.clientSettingsShowTextType;
   }

   public HoverEventActionManager(ClientSettingsShowTextType clientSettingsShowTextType2, IChatComponent iChatComponent2) {
      this.clientSettingsShowTextType = clientSettingsShowTextType2;
      this.iChatComponent = iChatComponent2;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         HoverEventActionManager event = (HoverEventActionManager)other;
         if (this.clientSettingsShowTextType != event.clientSettingsShowTextType) {
            return false;
         } else {
            if (this.iChatComponent != null) {
               if (!this.iChatComponent.equals(event.iChatComponent)) {
                  return false;
               }
            } else if (event.iChatComponent != null) {
               return false;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "HoverEvent{action=" + this.clientSettingsShowTextType + ", value='" + this.iChatComponent + '\'' + '}';
   }

   @Override
   public int hashCode() {
      int hash = this.clientSettingsShowTextType.hashCode();
      return 31 * hash + (this.iChatComponent != null ? this.iChatComponent.hashCode() : 0);
   }
}
