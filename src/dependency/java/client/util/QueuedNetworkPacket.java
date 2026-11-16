package client.util;

import io.netty.util.concurrent.GenericFutureListener;

public class QueuedNetworkPacket {
   public final Packet packet;
   public final GenericFutureListener[] genericFutureListenerArray;

   public static Packet resolvePacket(QueuedNetworkPacket packet2) {
      return packet2.packet;
   }

   public QueuedNetworkPacket(Packet packet2, GenericFutureListener[] genericFutureListenerArray2) {
      this.packet = packet2;
      this.genericFutureListenerArray = genericFutureListenerArray2;
   }

   public Packet getPacket() {
      return this.packet;
   }

   public static GenericFutureListener[] resolveGenericFutureListenerArray(QueuedNetworkPacket packet2) {
      return packet2.genericFutureListenerArray;
   }
}
