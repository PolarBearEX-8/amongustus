package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S28PacketEffect implements Packet {
   private int soundType;
   private BlockPos soundPos;
   private int soundData;
   private boolean serverWide;

   public int getSoundData() {
      return this.soundData;
   }

   public int getSoundType() {
      return this.soundType;
   }

   public BlockPos getSoundPos() {
      return this.soundPos;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeInt(this.soundType);
      buf.writeBlockPos(this.soundPos);
      buf.writeInt(this.soundData);
      buf.writeBoolean(this.serverWide);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.soundType = buf.readInt();
      this.soundPos = buf.readBlockPos();
      this.soundData = buf.readInt();
      this.serverWide = buf.readBoolean();
   }

   public boolean isSoundServerwide() {
      return this.serverWide;
   }

   public S28PacketEffect(int n, BlockPos pos, int n2, boolean flag) {
      this.soundType = n;
      this.soundPos = pos;
      this.soundData = n2;
      this.serverWide = flag;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S28PacketEffect() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEffect(this);
   }
}
