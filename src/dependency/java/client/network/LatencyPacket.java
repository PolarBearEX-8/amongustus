package client.network;

import client.util.INetHandler;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class LatencyPacket implements Packet {
   public boolean enabled2;
   public boolean enabled = false;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
   }

   public LatencyPacket() {
      this.enabled2 = false;
   }

   public LatencyPacket(boolean flag) {
      this.enabled2 = false;
      this.enabled = flag;
   }
}
