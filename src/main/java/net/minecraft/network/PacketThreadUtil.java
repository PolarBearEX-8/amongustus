package net.minecraft.network;

import net.augustus.events.EventPacket;
import net.augustus.events.PacketStage;
import net.augustus.events.listeners.PacketListener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.IThreadListener;
import net.optifine.config.Config;

public class PacketThreadUtil {
   public static int lastDimensionId = Integer.MIN_VALUE;

   public static void clientPreProcessPacket(Packet packet) {
      if (packet instanceof S08PacketPlayerPosLook) {
         Config.getRenderGlobal().checkFirstWorldLoad();
      }

      if (packet instanceof S07PacketRespawn) {
         lastDimensionId = ((S07PacketRespawn)packet).getDimensionID();
      } else if (packet instanceof S01PacketJoinGame) {
         lastDimensionId = ((S01PacketJoinGame)packet).getDimension();
      } else {
         lastDimensionId = Integer.MIN_VALUE;
      }
   }

   public static void runOnMainThread(Runnable runnable) {
      if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         runnable.run();
      } else {
         Minecraft.getMinecraft().addScheduledTask(runnable);
      }
   }

   public static void runOnMainThreadImmediate(Runnable runnable) {
      if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         runnable.run();
      } else {
         Minecraft.getMinecraft().addScheduledTaskDirect(runnable);
      }
   }

   public static void checkThreadAndEnqueue(Packet packet, INetHandler netHandler, IThreadListener listener) throws ThreadQuickExitException {
      if (!listener.isCallingFromMinecraftThread()) {
         listener.addScheduledTask(() -> {
            if (listener.isCallingFromMinecraftThread()) {
               if (packet instanceof S02PacketChat) {
                  boolean flag = false;
               }

               if (PacketListener.postPacket(packet, PacketStage.READ).isCancelled()) {
                  return;
               }
            }

            clientPreProcessPacket(packet);
            packet.processPacket(netHandler);
         });
         throw ThreadQuickExitException.INSTANCE;
      } else {
         clientPreProcessPacket(packet);
      }
   }
}
