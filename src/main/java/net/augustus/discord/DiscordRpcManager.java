package net.augustus.discord;

import net.augustus.Augustus;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.DiscordEventHandlers.Builder;
import net.augustus.utils.TimeHelper;

public class DiscordRpcManager {
   private boolean started;
   private boolean connected;
   public static String discordUserTag = "";
   private final TimeHelper timeHelper = new TimeHelper();
   private Thread callbackThread;
   private long startTimestamp;

   private void onReady(DiscordUser discordUser) {
      this.connected = true;
      if (discordUser != null) {
         discordUserTag = discordUser.userId + "###" + discordUser.username;
      }
   }

   public DiscordRpcManager() {
      this.connected = false;
      this.startTimestamp = System.currentTimeMillis();
      this.started = false;
      this.callbackThread = null;
   }

   public static boolean setConnected(DiscordRpcManager discordRpcManager, boolean flag) {
      return discordRpcManager.connected = flag;
   }

   public void shutdown() {
      if (this.connected) {
         this.connected = false;
         DiscordRPC.discordShutdown();
      }
   }

   public static TimeHelper getConnectTimer(DiscordRpcManager discordRpcManager) {
      return discordRpcManager.timeHelper;
   }

   public void start() {
      this.started = true;
      if (!DiscordRPC.isAvailable()) {
         return;
      }
      if (!this.connected) {
         if (this.callbackThread != null) {
            this.callbackThread.interrupt();
         }

         this.timeHelper.reset();
         try {
            DiscordEventHandlers handlers = new Builder()
               .setReadyEventHandler(this::onReady)
               .setErroredEventHandler((code, msg) -> {
                  System.err.println("Failed to load");
                  this.connected = false;
               })
               .setDisconnectedEventHandler((code, msg) -> {
                  System.err.println("Disconnected!");
                  this.connected = false;
               })
               .build();
            DiscordRPC.discordInitialize("944603218623733780", handlers, true);
            if (!DiscordRPC.isAvailable()) {
               return;
            }
            this.callbackThread = new DiscordRpcCallbackThread(this, "Discord RPC Callback", handlers);
            this.callbackThread.start();
         } catch (Throwable t) {
            this.connected = false;
         }
      }
   }

   public void updatePresence(String details, String state) {
      if (Augustus.getInstance().getModuleManager() == null) {
         return;
      }
      if (Augustus.getInstance().getModuleManager().noDiscordRPC.isToggled() || !this.connected) {
         return;
      }
      net.arikia.dev.drpc.DiscordRichPresence.Builder builder = new net.arikia.dev.drpc.DiscordRichPresence.Builder(state);
      builder.setBigImage("large", "");
      builder.setDetails(details);
      builder.setStartTimestamps(this.startTimestamp);
      DiscordRPC.discordUpdatePresence(builder.build());
   }


   public static boolean isConnected(DiscordRpcManager discordRpcManager) {
      return discordRpcManager.connected;
   }

}
