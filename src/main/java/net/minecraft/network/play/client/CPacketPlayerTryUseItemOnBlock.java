package net.minecraft.network.play.client;

import net.minecraft.util.EnumHand;
import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class CPacketPlayerTryUseItemOnBlock implements Packet {
   private float facingX;
   private EnumFacing placedBlockDirection;
   private float facingY;
   private float facingZ;
   private EnumHand hand;
   private BlockPos position;

   public float getFacingX() {
      return this.facingX;
   }

   public float getFacingY() {
      return this.facingY;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.position = buf.readBlockPos();
      this.placedBlockDirection = (EnumFacing)buf.readEnumValue(EnumFacing.class);
      this.hand = (EnumHand)buf.readEnumValue(EnumHand.class);
      this.facingX = buf.readFloat();
      this.facingY = buf.readFloat();
      this.facingZ = buf.readFloat();
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBlockPos(this.position);
      buf.writeEnumValue(this.placedBlockDirection);
      buf.writeEnumValue(this.hand);
      buf.writeFloat(this.facingX);
      buf.writeFloat(this.facingY);
      buf.writeFloat(this.facingZ);
   }

   public CPacketPlayerTryUseItemOnBlock() {
   }

   public EnumHand getHand() {
      return this.hand;
   }

   public CPacketPlayerTryUseItemOnBlock(BlockPos pos, EnumFacing facing, EnumHand enumHand, float f, float f2, float f3) {
      this.position = pos;
      this.placedBlockDirection = facing;
      this.hand = enumHand;
      this.facingX = f;
      this.facingY = f2;
      this.facingZ = f3;
   }

   public EnumFacing getPlacedBlockDirection() {
      return this.placedBlockDirection;
   }

   public float getFacingZ() {
      return this.facingZ;
   }

   public void processPacket2(INetHandlerPlayServer netHandlerPlayServer) {
      netHandlerPlayServer.processTryUseItemOnBlock(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket2((INetHandlerPlayServer)handler);
   }

   public BlockPos getPosition() {
      return this.position;
   }
}
