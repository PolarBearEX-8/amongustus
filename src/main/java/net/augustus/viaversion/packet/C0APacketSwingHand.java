package net.augustus.viaversion.packet;

import net.minecraft.util.EnumHand;
import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0APacketSwingHand implements Packet {
   private EnumHand hand;

   public void processPacket2(INetHandlerPlayServer netHandlerPlayServer) {
      netHandlerPlayServer.processSwingHand(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket2((INetHandlerPlayServer)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.hand = (EnumHand)buf.readEnumValue(EnumHand.class);
   }

   public C0APacketSwingHand(EnumHand enumHand) {
      this.hand = enumHand;
   }

   public C0APacketSwingHand() {
   }

   public EnumHand getHand() {
      return this.hand;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.hand);
   }



}
