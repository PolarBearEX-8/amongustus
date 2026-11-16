package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.PacketBuffer;
import java.io.IOException;

public class S17PacketEntityLookMove extends S14PacketEntity {
   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      super.processAction(packet);
      this.byteFlag = packet.readByte();
      this.byteFlag3 = packet.readByte();
      this.byteFlag4 = packet.readByte();
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
      packet.writeByte(this.byteFlag);
      packet.writeByte(this.byteFlag3);
      packet.writeByte(this.byteFlag4);
      packet.writeByte(this.byteFlag2);
      packet.writeByte(this.byteFlag5);
      packet.writeBoolean(this.enabled2);
   }

   public S17PacketEntityLookMove() {
      this.enabled = true;
   }

   public S17PacketEntityLookMove(int count, byte byteValue, byte byteValue2, byte byteValue3, byte byteValue4, byte byteValue5, boolean flag) {
      super(count);
      this.byteFlag = byteValue;
      this.byteFlag3 = byteValue2;
      this.byteFlag4 = byteValue3;
      this.byteFlag2 = byteValue4;
      this.byteFlag5 = byteValue5;
      this.enabled2 = flag;
      this.enabled = true;
   }
}
