package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.PacketBuffer;
import java.io.IOException;

public class S16PacketEntityLook extends S14PacketEntity {
   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      super.processAction(packet);
      this.byteFlag2 = packet.readByte();
      this.byteFlag5 = packet.readByte();
      this.enabled2 = packet.readBoolean();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      super.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      super.processAction2(packet);
      packet.writeByte(this.byteFlag2);
      packet.writeByte(this.byteFlag5);
      packet.writeBoolean(this.enabled2);
   }

   public S16PacketEntityLook(int count, byte byteValue, byte byteValue2, boolean flag) {
      super(count);
      this.byteFlag2 = byteValue;
      this.byteFlag5 = byteValue2;
      this.enabled = true;
      this.enabled2 = flag;
   }

   public S16PacketEntityLook() {
      this.enabled = true;
   }
}
