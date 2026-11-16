package client.setting;

public enum CombatOffsetProperty {
    ENABLING_COMPRESSION("Enabling compression..."),
    WAITING_FOR_RESPONSE("Waiting for response..."),
    STARTING_NETTY_CONNECTION("Starting Netty Connection..."),
    RESOLVING_SRV("Resolving SRV..."),
    SUCCESS("Success!"),
    VERIFYING_SESSION("Verifying Session..."),
    SENDING_LOGIN_PACKETS("Sending login packets..."),
    CONNECTING("Connecting..."),
    ENCRYPTING("Encrypting..."),
    RESOLVING_IP("Resolving IP...");

   public final String labelText2;

   public String getLabelText2() {
      return this.labelText2;
   }

   private CombatOffsetProperty(String text) {
      this.labelText2 = text;
   }


}
