package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.BlockPos;

public class S28PacketEffect implements Packet {
   public int count;
   public BlockPos blockPos;
   public int count2;
   public boolean enabled;

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public int getCount() {
      return this.count;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS28PacketEffect(this);
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readInt();
      this.blockPos = packet.getBlockPos();
      this.count2 = packet.readInt();
      this.enabled = packet.readBoolean();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeInt(this.count);
      packet.applyBlockPos(this.blockPos);
      packet.writeInt(this.count2);
      packet.writeBoolean(this.enabled);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public S28PacketEffect(int count3, BlockPos blockPos2, int count4, boolean flag) {
      this.count = count3;
      this.blockPos = blockPos2;
      this.count2 = count4;
      this.enabled = flag;
   }

   public S28PacketEffect() {
   }
}
