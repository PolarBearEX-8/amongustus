package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S0APacketUseBed implements Packet {
   private int playerID;
   private BlockPos bedPos;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.playerID);
      buf.writeBlockPos(this.bedPos);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleUseBed(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.playerID = buf.readVarIntFromBuffer();
      this.bedPos = buf.readBlockPos();
   }

   public EntityPlayer getPlayer(World world) {
      return (EntityPlayer)world.getEntityByID(this.playerID);
   }

   public BlockPos getBedPosition() {
      return this.bedPos;
   }

   public S0APacketUseBed(EntityPlayer player, BlockPos pos) {
      this.playerID = player.getEntityId();
      this.bedPos = pos;
   }

   public S0APacketUseBed() {
   }
}
