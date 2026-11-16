package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class S24PacketBlockAction implements Packet {
   public BlockPos blockPos;
   public int count2;
   public int count;
   public Block block;

   public int getCount2() {
      return this.count2;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS24PacketBlockAction(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.blockPos = packet.getBlockPos();
      this.count2 = packet.readUnsignedByte();
      this.count = packet.readUnsignedByte();
      this.block = Block.processAction3(packet.getInt3() & 4095);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyBlockPos(this.blockPos);
      packet.writeByte(this.count2);
      packet.writeByte(this.count);
      packet.updateInt(Block.getIdFromBlock(this.block) & 4095);
   }

   public Block getBlock() {
      return this.block;
   }

   public S24PacketBlockAction(BlockPos blockPos2, Block block2, int count3, int count4) {
      this.blockPos = blockPos2;
      this.count2 = count3;
      this.count = count4;
      this.block = block2;
   }

   public S24PacketBlockAction() {
   }
}
