package client.util;

import java.io.IOException;

public class ExtendedPacketHandler implements Packet {
   public long timestamp;

   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.timestamp = packet.readLong();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applyRotationProfiler((RotationProfiler)iNetHandler);
   }

   public void applyRotationProfiler(RotationProfiler rotationProfiler) {
      rotationProfiler.applyExtendedPacketHandler(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeLong(this.timestamp);
   }

   public ExtendedPacketHandler() {
   }

   public ExtendedPacketHandler(long value) {
      this.timestamp = value;
   }
}
