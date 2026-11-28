package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventPacket;
import net.augustus.events.PacketStage;
import net.minecraft.network.Packet;

public interface PacketListener {
   default void onPacketReceive(EventPacket event) {
   }

   default void onPacketSend(EventPacket event) {
   }

   default void onPacketSendNettyDelay(EventPacket event) {
   }

   default void onPacketReceiveNetty(EventPacket event) {
   }

   default void onPacketSendNetty(EventPacket event) {
   }

   static EventPacket postPacket(Packet packet, PacketStage packetStage) {
      EventPacket event = new EventPacket(packet, packetStage);
      EventManager.getEventBus().post(26, event);
      return event;
   }
}
