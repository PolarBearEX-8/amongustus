package client.util;

import client.setting.ClientSettingsOpenUrlType;

public class ClickEventActionManager {
   public final ClientSettingsOpenUrlType clientSettingsOpenUrlType;
   public final String labelText;

   public ClientSettingsOpenUrlType getClientSettingsOpenUrlType() {
      return this.clientSettingsOpenUrlType;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public ClickEventActionManager(ClientSettingsOpenUrlType clientSettingsOpenUrlType2, String text) {
      this.clientSettingsOpenUrlType = clientSettingsOpenUrlType2;
      this.labelText = text;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         ClickEventActionManager event = (ClickEventActionManager)other;
         if (this.clientSettingsOpenUrlType != event.clientSettingsOpenUrlType) {
            return false;
         } else {
            if (this.labelText != null) {
               if (!this.labelText.equals(event.labelText)) {
                  return false;
               }
            } else if (event.labelText != null) {
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
      return "ClickEvent{action=" + this.clientSettingsOpenUrlType + ", value='" + this.labelText + '\'' + '}';
   }

   @Override
   public int hashCode() {
      int hash = this.clientSettingsOpenUrlType.hashCode();
      return 31 * hash + (this.labelText != null ? this.labelText.hashCode() : 0);
   }
}
