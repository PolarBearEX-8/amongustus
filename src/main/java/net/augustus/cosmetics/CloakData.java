package net.augustus.cosmetics;


public class CloakData {
   private String url;
   private boolean enabled;

   public boolean hasActiveCloak() {
      return this.enabled && this.url != null;
   }

   public void setUrl(String str) {
      this.url = str;
      this.enabled = str != null;
   }

   public void copyFrom(CloakData cloakData) {
      this.url = cloakData.url;
      this.enabled = cloakData.enabled;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public int hashCode() {
      int n = 1;
      n = 31 * n + (this.enabled ? 1231 : 1237);
      return 31 * n + (this.url == null ? 0 : this.url.hashCode());
   }

   public void setEnabled(Boolean bool) {
      if (this.enabled = bool) {
         this.url = this.url == null ? "https://cl.cosmeticsmod.com/textures/cloaks/cosmeticsmod.png" : this.url;
      }
   }

   @Override
   public String toString() {
      return "Cloak [enabled=" + this.enabled + ", url=" + this.url + "]";
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         CloakData cloakData = (CloakData)obj;
         if (this.enabled != cloakData.enabled) {
            return false;
         } else {
            if (this.url == null) {
               if (cloakData.url != null) {
                  return false;
               }
            } else if (!this.url.equals(cloakData.url)) {
               return false;
            }

            return true;
         }
      }
   }

   public String getUrl() {
      return this.url;
   }



}
