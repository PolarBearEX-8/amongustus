package client.util;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;

public class ThreadAdapter extends Thread {
   public final DiscordEventHandlers discordEventHandlers;
   public final DiscordRpcWorker discordRpcWorker;

   public ThreadAdapter(DiscordRpcWorker discordRpcWorker2, String text, DiscordEventHandlers event) {
      super(text);
      this.discordRpcWorker = discordRpcWorker2;
      this.discordEventHandlers = event;
   }

   @Override
   public void run() {
      while (DiscordRpcWorker.matchesDiscordRpcWorker(this.discordRpcWorker) || !DiscordRpcWorker.resolveTimeHelper(this.discordRpcWorker).hasReached(10000L)) {
         DiscordRPC.discordRunCallbacks();
         DiscordRPC.discordUpdateEventHandlers(this.discordEventHandlers);

         try {
            Thread.sleep(10L);
         } catch (InterruptedException interruptedException) {
         }
      }
   }

   @Override
   public void finalize() {
      DiscordRpcWorker.matchesDiscordRpcWorker(this.discordRpcWorker, false);
      DiscordRPC.discordShutdown();
   }
}
