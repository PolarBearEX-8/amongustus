package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class S33PacketUpdateSign implements Packet {
   private World world;
   private BlockPos blockPos;
   private IChatComponent[] lines;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.blockPos = buf.readBlockPos();
      this.lines = new IChatComponent[4];

      for (int i = 0; i < 4; i++) {
         this.lines[i] = buf.readChatComponent();
      }
   }

   public S33PacketUpdateSign() {
   }

   public S33PacketUpdateSign(World world2, BlockPos pos, IChatComponent[] components) {
      this.world = world2;
      this.blockPos = pos;
      this.lines = new IChatComponent[]{components[0], components[1], components[2], components[3]};
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBlockPos(this.blockPos);

      for (int i = 0; i < 4; i++) {
         buf.writeChatComponent(this.lines[i]);
      }
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public IChatComponent[] getLines() {
      return this.lines;
   }

   public BlockPos getPos() {
      return this.blockPos;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleUpdateSign(this);
   }
}
