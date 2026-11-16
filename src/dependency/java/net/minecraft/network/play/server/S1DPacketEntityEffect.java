package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.potion.PotionEffect;

public class S1DPacketEntityEffect implements Packet {
   public int count2;
   public byte byteFlag3;
   public byte byteFlag;
   public int count;
   public byte byteFlag2;

   public byte getByteFlag3() {
      return this.byteFlag3;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS1DPacketEntityEffect(this);
   }

   public boolean isActive() {
      return this.byteFlag2 != 0;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.getInt3();
      this.byteFlag3 = packet.readByte();
      this.byteFlag = packet.readByte();
      this.count = packet.getInt3();
      this.byteFlag2 = packet.readByte();
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public boolean isActiveAsFlag() {
      return this.count == 32767;
   }

   public int getIndex() {
      return this.count2;
   }

   public byte getByteFlag() {
      return this.byteFlag;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count2);
      packet.writeByte(this.byteFlag3);
      packet.writeByte(this.byteFlag);
      packet.updateInt(this.count);
      packet.writeByte(this.byteFlag2);
   }

   public S1DPacketEntityEffect(int count3, PotionEffect potionEffect) {
      this.count2 = count3;
      this.byteFlag3 = (byte)(potionEffect.getIndexOffset3() & 0xFF);
      this.byteFlag = (byte)(potionEffect.getIndexOffset2() & 0xFF);
      if (potionEffect.getIndexOffset() > 32767) {
         this.count = 32767;
      } else {
         this.count = potionEffect.getIndexOffset();
      }

      this.byteFlag2 = ((byte)((potionEffect.isState1()) ? 1 : 0));
   }

   public S1DPacketEntityEffect() {
   }
}
