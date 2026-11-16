package client.util;

import java.io.IOException;

public class StandardPacketAdapter implements Packet {
   public int count;

   public void applyINetHandlerExtension(INetHandlerExtension iNetHandlerExtension) {
      iNetHandlerExtension.applyStandardPacketAdapter(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applyINetHandlerExtension((INetHandlerExtension)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
   }

   public int getCount() {
      return this.count;
   }

   public StandardPacketAdapter(int count2) {
      this.count = count2;
   }

   public StandardPacketAdapter() {
   }
}
