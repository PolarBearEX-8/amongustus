package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class S10PacketSpawnPainting implements Packet {
   private int entityID;
   private BlockPos position;
   private EnumFacing facing;
   private String title;

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSpawnPainting(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityID);
      buf.writeString(this.title);
      buf.writeBlockPos(this.position);
      buf.writeByte(this.facing.getHorizontalIndex());
   }

   public S10PacketSpawnPainting() {
   }

   public int getEntityID() {
      return this.entityID;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityID = buf.readVarIntFromBuffer();
      this.title = buf.readStringFromBuffer(EntityPainting.EnumArt.MAX_NAME_LENGTH);
      this.position = buf.readBlockPos();
      this.facing = EnumFacing.getHorizontal(buf.readUnsignedByte());
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   public S10PacketSpawnPainting(EntityPainting entityPainting) {
      this.entityID = entityPainting.getEntityId();
      this.position = entityPainting.getHangingPosition();
      this.facing = entityPainting.facingDirection;
      this.title = entityPainting.art.title;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public String getTitle() {
      return this.title;
   }
}
