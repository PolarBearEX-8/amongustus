package client.util;

public class AccountProfileController {
   public String labelText;
   public boolean enabled;
   public AccountProfile accountProfile;

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public AccountProfileController(boolean flag, String text) {
      this.enabled = flag;
      this.labelText = text;
   }

   public AccountProfile getAccountProfile() {
      return this.accountProfile;
   }
}
