package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.PacketDelayDirection;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public interface PacketDelayListener {
   default void onOutgoingPacketDelay(EventPacketDelay event) {
   }

   static EventPacketDelay postOutgoingPacketDelay(Packet packet, Queue queue, ReentrantReadWriteLock reentrantReadWriteLock) {
      EventPacketDelay event = new EventPacketDelay(PacketDelayDirection.OUTGOING, packet, queue, reentrantReadWriteLock);
      EventManager.getEventBus().post(25, event);
      return event;
   }

   default void onIncomingPacketDelay(EventPacketDelay event) {
   }

   static EventPacketDelay postIncomingPacketDelay(Packet packet, INetHandler netHandler, EnumPacketDirection enumPacketDirection) {
      EventPacketDelay event = new EventPacketDelay(PacketDelayDirection.INCOMING, packet, netHandler, enumPacketDirection);
      EventManager.getEventBus().post(25, event);
      return event;
   }
}
