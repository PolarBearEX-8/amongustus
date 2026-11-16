package client.util;

import java.io.IOException;

public class AdvancedPacketAdapter implements Packet {
   public long timestamp;

   public void applySmoothCalculator(SmoothCalculator smoothCalculator) {
      smoothCalculator.applyAdvancedPacketAdapter(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.timestamp = packet.readLong();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applySmoothCalculator((SmoothCalculator)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeLong(this.timestamp);
   }

   public AdvancedPacketAdapter() {
   }

   public AdvancedPacketAdapter(long value) {
      this.timestamp = value;
   }
}
