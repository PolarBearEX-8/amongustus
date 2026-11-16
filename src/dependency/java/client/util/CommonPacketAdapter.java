package client.util;

import java.io.IOException;

public class CommonPacketAdapter implements Packet {
   @Override
   public void processAction(PacketBuffer packet) throws IOException {
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applyRotationProfiler((RotationProfiler)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
   }

   public void applyRotationProfiler(RotationProfiler rotationProfiler) {
      rotationProfiler.applyCommonPacketAdapter(this);
   }
}
