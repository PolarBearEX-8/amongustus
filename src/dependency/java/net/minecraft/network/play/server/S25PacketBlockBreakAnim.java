package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.BlockPos;

public class S25PacketBlockBreakAnim implements Packet {
   public int count;
   public BlockPos blockPos;
   public int count2;

   public int getCount2() {
      return this.count2;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
      this.blockPos = packet.getBlockPos();
      this.count2 = packet.readUnsignedByte();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
      packet.applyBlockPos(this.blockPos);
      packet.writeByte(this.count2);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS25PacketBlockBreakAnim(this);
   }

   public S25PacketBlockBreakAnim() {
   }

   public S25PacketBlockBreakAnim(int count3, BlockPos blockPos2, int count4) {
      this.count = count3;
      this.blockPos = blockPos2;
      this.count2 = count4;
   }
}
