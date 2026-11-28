package net.augustus.ui.account;

public class AccountAuthStatus {
   private String statusMessage;
   private boolean authenticated;
   private AccountEntry accountEntry;

   public String getStatusMessage() {
      return this.statusMessage;
   }

   public void setAuthenticated(boolean flag) {
      this.authenticated = flag;
   }

   public boolean isAuthenticated() {
      return this.authenticated;
   }

   public void setStatusMessage(String str) {
      this.statusMessage = str;
   }

   public AccountAuthStatus(boolean flag, String str) {
      this.authenticated = flag;
      this.statusMessage = str;
   }

   public AccountEntry getAccountEntry() {
      return this.accountEntry;
   }
}
