package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class S33PacketUpdateSign implements Packet {
   public World world;
   public BlockPos blockPos;
   public IChatComponent[] iChatComponentArray;

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.blockPos = packet.getBlockPos();
      this.iChatComponentArray = new IChatComponent[4];

      for (int index = 0; index < 4; index++) {
         this.iChatComponentArray[index] = packet.getChatComponent();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public IChatComponent[] getIChatComponentArray() {
      return this.iChatComponentArray;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyBlockPos(this.blockPos);

      for (int index = 0; index < 4; index++) {
         packet.setChatComponent(this.iChatComponentArray[index]);
      }
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS33PacketUpdateSign(this);
   }

   public S33PacketUpdateSign(World world2, BlockPos blockPos2, IChatComponent[] iChatComponentArray2) {
      this.world = world2;
      this.blockPos = blockPos2;
      this.iChatComponentArray = new IChatComponent[]{iChatComponentArray2[0], iChatComponentArray2[1], iChatComponentArray2[2], iChatComponentArray2[3]};
   }

   public S33PacketUpdateSign() {
   }
}
