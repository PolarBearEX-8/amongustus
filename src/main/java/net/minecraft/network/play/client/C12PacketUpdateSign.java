package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class C12PacketUpdateSign implements Packet {
   private BlockPos pos;
   private IChatComponent[] lines;

   public BlockPos getPosition() {
      return this.pos;
   }

   public C12PacketUpdateSign() {
   }

   public C12PacketUpdateSign(BlockPos pos2, IChatComponent[] components) {
      this.pos = pos2;
      this.lines = new IChatComponent[]{components[0], components[1], components[2], components[3]};
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processUpdateSign(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.pos = buf.readBlockPos();
      this.lines = new IChatComponent[4];

      for (int i = 0; i < 4; i++) {
         IChatComponent component = IChatComponent.Serializer.jsonToComponent(buf.readStringFromBuffer(384));
         this.lines[i] = component;
      }
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBlockPos(this.pos);

      for (int i = 0; i < 4; i++) {
         buf.writeString(IChatComponent.Serializer.componentToJson(this.lines[i]));
      }
   }

   public IChatComponent[] getLines() {
      return this.lines;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }
}
