package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.IChatComponent;

public class C12PacketUpdateSign implements Packet {
   public BlockPos blockPos;
   public IChatComponent[] iChatComponentArray;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.blockPos = packet.getBlockPos();
      this.iChatComponentArray = new IChatComponent[4];

      for (int index = 0; index < 4; index++) {
         IChatComponent iChatComponent = ChatComponentSerializer.parseChatComponent(packet.resolveTextByIndex(384));
         this.iChatComponentArray[index] = iChatComponent;
      }
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyBlockPos(this.blockPos);

      for (int index = 0; index < 4; index++) {
         String text = ChatComponentSerializer.toJson(this.iChatComponentArray[index]);
         packet.getPacketBuffer(text);
      }
   }

   public IChatComponent[] getIChatComponentArray() {
      return this.iChatComponentArray;
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC12PacketUpdateSign(this);
   }

   public C12PacketUpdateSign() {
   }

   public C12PacketUpdateSign(BlockPos blockPos2, IChatComponent[] iChatComponentArray2) {
      this.blockPos = blockPos2;
      this.iChatComponentArray = new IChatComponent[]{iChatComponentArray2[0], iChatComponentArray2[1], iChatComponentArray2[2], iChatComponentArray2[3]};
   }
}
