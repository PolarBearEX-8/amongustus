package client.util;

public class CloakEnabledController {
   public String labelText;
   public boolean enabled;

   public void booleanValue(Boolean flag) {
      if (this.enabled = flag) {
         this.labelText = this.labelText == null ? "https://cl.cosmeticsmod.com/textures/cloaks/cosmeticsmod.png" : this.labelText;
      }
   }

   @Override
   public int hashCode() {
      int count = 31 + (this.enabled ? 1231 : 1237);
      return 31 * count + (this.labelText == null ? 0 : this.labelText.hashCode());
   }

   @Override
   public String toString() {
      return "Cloak [enabled=" + this.enabled + ", url=" + this.labelText + "]";
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other == null) {
         return false;
      } else if (this.getClass() != other.getClass()) {
         return false;
      } else {
         CloakEnabledController cloakEnabledController = (CloakEnabledController)other;
         if (this.enabled != cloakEnabledController.enabled) {
            return false;
         } else {
            if (this.labelText == null) {
               if (cloakEnabledController.labelText != null) {
                  return false;
               }
            } else if (!this.labelText.equals(cloakEnabledController.labelText)) {
               return false;
            }

            return true;
         }
      }
   }

   public void applyCloakEnabledController(CloakEnabledController cloakEnabledController) {
      this.labelText = cloakEnabledController.labelText;
      this.enabled = cloakEnabledController.enabled;
   }

   public void applyText(String text) {
      this.labelText = text;
      this.enabled = text != null;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean isActive() {
      return this.enabled && this.labelText != null;
   }

   public String getLabelText() {
      return this.labelText;
   }
}
