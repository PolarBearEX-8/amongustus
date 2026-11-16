package net.minecraft.network.play.client;

import client.util.EnumHand;
import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class CAnimateHandPacket implements Packet {
   public EnumHand enumHand;

   public EnumHand getEnumHand() {
      return this.enumHand;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.enumHand = (EnumHand)packet.getEnum(EnumHand.class);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      INetHandlerPlayServer iNetHandlerPlayServer = (INetHandlerPlayServer)iNetHandler;
      this.processPacket(iNetHandlerPlayServer);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyEnum(this.enumHand);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyCAnimateHandPacket(this);
   }

   public CAnimateHandPacket() {
   }

   public CAnimateHandPacket(EnumHand enumHand2) {
      this.enumHand = enumHand2;
   }
}
