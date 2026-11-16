package cc.baritone.utils;

public class MessageRegistrationNameManager {
   public final String labelText;
   public final AltListener altListener;
   public final CORERECOMMENDEDManager cORERECOMMENDEDManager;

   public MessageRegistrationNameManager(String text, AltListener altListener2, CORERECOMMENDEDManager cORERECOMMENDEDManager2) {
      this.labelText = text;
      this.altListener = altListener2;
      this.cORERECOMMENDEDManager = cORERECOMMENDEDManager2;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other == null) {
         return false;
      } else if (!(other instanceof MessageRegistrationNameManager)) {
         return false;
      } else {
         MessageRegistrationNameManager messageRegistrationNameManager = (MessageRegistrationNameManager)other;
         if (this.labelText == null) {
            if (messageRegistrationNameManager.labelText != null) {
               return false;
            }
         } else if (!this.labelText.equals(messageRegistrationNameManager.labelText)) {
            return false;
         }

         if (this.cORERECOMMENDEDManager == null) {
            if (messageRegistrationNameManager.cORERECOMMENDEDManager != null) {
               return false;
            }
         } else if (!this.cORERECOMMENDEDManager.equals(messageRegistrationNameManager.cORERECOMMENDEDManager)) {
            return false;
         }

         if (this.altListener == null) {
            if (messageRegistrationNameManager.altListener != null) {
               return false;
            }
         } else if (!this.altListener.equals(messageRegistrationNameManager.altListener)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "MessageRegistration [name=" + this.labelText + ", type=" + this.altListener + ", category=" + this.cORERECOMMENDEDManager + "]";
   }

   @Override
   public int hashCode() {
      int count = 31 + (this.labelText == null ? 0 : this.labelText.hashCode());
      int count2 = 31 * count + (this.cORERECOMMENDEDManager == null ? 0 : this.cORERECOMMENDEDManager.hashCode());
      return 31 * count2 + (this.altListener == null ? 0 : this.altListener.hashCode());
   }
}
