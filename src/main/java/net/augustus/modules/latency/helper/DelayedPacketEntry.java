package net.augustus.modules.latency.helper;

import net.minecraft.network.Packet;

public class DelayedPacketEntry {
   private final Packet packet;
   private final long queuedTimestampMs;

   public DelayedPacketEntry(Packet packet2) {
      this.packet = packet2;
      this.queuedTimestampMs = System.currentTimeMillis();
   }

   public Packet getPacket() {
      return this.packet;
   }

   public long getQueuedTimestampMs() {
      return this.queuedTimestampMs;
   }

   public boolean isExpired(long l) {
      return System.currentTimeMillis() > this.queuedTimestampMs + l;
   }



}
