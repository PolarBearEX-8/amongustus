package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C0CPacketInput implements Packet {
   public float factor;
   public float factor2;
   public boolean enabled;
   public boolean enabled2;

   public boolean isEnabled() {
      return this.enabled;
   }

   public float getFactor() {
      return this.factor;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.factor = packet.readFloat();
      this.factor2 = packet.readFloat();
      byte byteValue = packet.readByte();
      this.enabled = (byteValue & 1) > 0;
      this.enabled2 = (byteValue & 2) > 0;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC0CPacketInput(this);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeFloat(this.factor);
      packet.writeFloat(this.factor2);
      byte byteValue = 0;
      if (this.enabled) {
         byteValue = (byte)(byteValue | 1);
      }

      if (this.enabled2) {
         byteValue = (byte)(byteValue | 2);
      }

      packet.writeByte(byteValue);
   }

   public C0CPacketInput(float factor3, float factor4, boolean flag, boolean flag2) {
      this.factor = factor3;
      this.factor2 = factor4;
      this.enabled = flag;
      this.enabled2 = flag2;
   }

   public C0CPacketInput() {
   }
}
