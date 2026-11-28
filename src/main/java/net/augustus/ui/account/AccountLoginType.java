package net.augustus.ui.account;

public enum AccountLoginType {
   MICROSOFT_ACCESS_TOKEN("Access Token"),
   MICROSOFT_CREDENTIALS("Credentials"),
   CRACKED("Cracked"),

   MICROSOFT_EXTERNAL_BROWSER("External browser"),
   MICROSOFT_DEVICE_CODE("Device code"),
   THE_ALTENING("TheAltening");

private final String displayName;

   public String getDisplayName() {
      return this.displayName;
   }

   private AccountLoginType(String str) {
      this.displayName = str;
   }
}
