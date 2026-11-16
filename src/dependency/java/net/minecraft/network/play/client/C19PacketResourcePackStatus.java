package net.minecraft.network.play.client;

import client.setting.DisablerSuccessfullyLoadedType;
import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C19PacketResourcePackStatus implements Packet {
   public String labelText;
   public DisablerSuccessfullyLoadedType disablerSuccessfullyLoadedType;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveTextByIndex(40);
      this.disablerSuccessfullyLoadedType = (DisablerSuccessfullyLoadedType)packet.getEnum(DisablerSuccessfullyLoadedType.class);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC19PacketResourcePackStatu(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
      packet.applyEnum(this.disablerSuccessfullyLoadedType);
   }

   public C19PacketResourcePackStatus(String text, DisablerSuccessfullyLoadedType disablerSuccessfullyLoadedType2) {
      if (text.length() > 40) {
         text = text.substring(0, 40);
      }

      this.labelText = text;
      this.disablerSuccessfullyLoadedType = disablerSuccessfullyLoadedType2;
   }

   public C19PacketResourcePackStatus() {
   }
}
