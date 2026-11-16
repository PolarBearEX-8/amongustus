package client.util;

import client.event.EnhancedListener;
import client.event.PacketListener;
import client.setting.SupplementalOptionType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class ClientManager {
   public static int count = Integer.MIN_VALUE;

   public static synchronized void applyRunnable(Runnable task) {
      Minecraft.getMinecraft().resolveListenableFuture(task);
   }

   public static void applyPacket(Packet packet) {
      if (packet instanceof S08PacketPlayerPosLook) {
         BuildManager.getRenderGlobalAsRenderGlobal().updateState19();
      }

      if (packet instanceof S07PacketRespawn) {
         count = ((S07PacketRespawn)packet).getEntityId();
      } else if (packet instanceof S01PacketJoinGame) {
         count = ((S01PacketJoinGame)packet).getCount3();
      } else {
         count = Integer.MIN_VALUE;
      }
   }

   public static void handlePacket2(Packet packet, INetHandler iNetHandler, EnhancedListener enhancedListener) throws RuntimeExceptionAdapter {
      if (!enhancedListener.isSingleplayer()) {
         enhancedListener.resolveListenableFuture(() -> applyEnhancedListener(enhancedListener, packet, iNetHandler));
         throw RuntimeExceptionAdapter.runtimeExceptionAdapter;
      } else {
         applyPacket(packet);
      }
   }

   public static synchronized void applyRunnable2(Runnable task) {
      Minecraft.getMinecraft().resolveListenableFutureByRunnable(task);
   }

   private static void applyEnhancedListener(EnhancedListener enhancedListener, Packet packet, INetHandler iNetHandler) {
      if (enhancedListener.isSingleplayer()) {
         boolean flag = packet instanceof S02PacketChat;
         SupplementalOptionType supplementalOptionType = SupplementalOptionType.READ;
         if (PacketListener.resolveEventPacket(packet, supplementalOptionType).isCancelled()) {
            return;
         }
      }

      applyPacket(packet);
      packet.processAction(iNetHandler);
   }
}
