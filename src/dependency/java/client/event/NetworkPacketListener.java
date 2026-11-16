package client.event;

import client.setting.AdvancedOptionType;
import client.setting.EventPacketServerboundType;
import client.util.INetHandler;
import client.util.Packet;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public interface NetworkPacketListener {
   default void onNetworkPacket(EventSendPacket packet) {
   }

   static EventSendPacket onPacketServerboundType(Packet packet, INetHandler iNetHandler, EventPacketServerboundType packet2) {
      EventSendPacket packet3 = new EventSendPacket(AdvancedOptionType.INCOMING, packet, iNetHandler, packet2);
      EventBusManager.getDietrichEvents2().post(25, packet3);
      return packet3;
   }

   static EventSendPacket resolveEventSendPacket(Packet packet, Queue queue, ReentrantReadWriteLock reentrantReadWriteLock) {
      EventSendPacket packet2 = new EventSendPacket(AdvancedOptionType.OUTGOING, packet, queue, reentrantReadWriteLock);
      EventBusManager.getDietrichEvents2().post(25, packet2);
      return packet2;
   }

   default void onPostNetworkPacket(EventSendPacket packet) {
   }
}
