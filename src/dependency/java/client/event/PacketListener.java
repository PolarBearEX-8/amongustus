package client.event;

import client.setting.SupplementalOptionType;
import client.util.Packet;

public interface PacketListener {
   default void onPacketReceive(EventPacket packet) {
   }

   static EventPacket resolveEventPacket(Packet packet, SupplementalOptionType supplementalOptionType) {
      EventPacket packet2 = new EventPacket(packet, supplementalOptionType);
      EventBusManager.getDietrichEvents2().post(26, packet2);
      return packet2;
   }

   default void onPacketSend(EventPacket packet) {
   }

   default void onPacketPostReceive(EventPacket packet) {
   }

   default void onPacket(EventPacket packet) {
   }

   default void onPacketPostSend(EventPacket packet) {
   }
}
