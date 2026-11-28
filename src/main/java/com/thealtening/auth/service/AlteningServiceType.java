package com.thealtening.auth.service;

public enum AlteningServiceType {
   MOJANG("https://authserver.mojang.com/", "https://sessionserver.mojang.com/"),
   THEALTENING("http://authserver.thealtening.com/", "http://sessionserver.thealtening.com/");

private final String authServer;
   private final String sessionServer;

   private AlteningServiceType(String str, String str2) {
      this.authServer = str;
      this.sessionServer = str2;
   }

   public String getSessionServer() {
      return this.sessionServer;
   }

   public String getAuthServer() {
      return this.authServer;
   }
}
