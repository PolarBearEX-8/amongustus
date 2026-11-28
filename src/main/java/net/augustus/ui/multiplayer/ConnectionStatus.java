package net.augustus.ui.multiplayer;

public enum ConnectionStatus {
   WAITING_FOR_RESPONSE("Waiting for response..."),

   ENCRYPTING("Encrypting..."),
   ENABLING_COMPRESSION("Enabling compression..."),
   SENDING_LOGIN_PACKETS("Sending login packets..."),
   VERIFYING_SESSION("Verifying Session..."),
   CONNECTING("Connecting..."),
   RESOLVING_SRV("Resolving SRV..."),
   RESOLVING_IP("Resolving IP..."),
   STARTING_NETTY_CONNECTION("Starting Netty Connection..."),
   SUCCESS("Success!");

private final String message;

   private ConnectionStatus(String str) {
      this.message = str;
   }

   public String getMessage() {
      return this.message;
   }
}
