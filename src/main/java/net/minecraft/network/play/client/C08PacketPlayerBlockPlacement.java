package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;

public class C08PacketPlayerBlockPlacement implements Packet {
   public static final BlockPos INVALID_BLOCK_POS = new BlockPos(-1, -1, -1);
   private BlockPos position;
   private int placedBlockDirection;
   private ItemStack stack;
   private float facingX;
   private float facingY;
   private float facingZ;

   public void setFacingY(float f) {
      this.facingY = f;
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public C08PacketPlayerBlockPlacement(ItemStack stack2) {
      this(INVALID_BLOCK_POS, 255, stack2, 0.0F, 0.0F, 0.0F);
   }

   public void setFacingX(float f) {
      this.facingX = f;
   }

   public ItemStack getStack() {
      return this.stack;
   }

   public void setStack(ItemStack stack2) {
      this.stack = stack2;
   }

   public void setFacingZ(float f) {
      this.facingZ = f;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBlockPos(this.position);
      buf.writeByte(this.placedBlockDirection);
      buf.writeItemStackToBuffer(this.stack);
      buf.writeByte((int)(this.facingX * 16.0F));
      buf.writeByte((int)(this.facingY * 16.0F));
      buf.writeByte((int)(this.facingZ * 16.0F));
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.position = buf.readBlockPos();
      this.placedBlockDirection = buf.readUnsignedByte();
      this.stack = buf.readItemStackFromBuffer();
      this.facingX = (float)buf.readUnsignedByte() / 16.0F;
      this.facingY = (float)buf.readUnsignedByte() / 16.0F;
      this.facingZ = (float)buf.readUnsignedByte() / 16.0F;
   }

   public void setPosition(BlockPos pos) {
      this.position = pos;
   }

   public void setPlacedBlockDirection(int n) {
      this.placedBlockDirection = n;
   }

   public float getPlacedBlockOffsetY() {
      return this.facingY;
   }

   public float getPlacedBlockOffsetZ() {
      return this.facingZ;
   }

   public int getPlacedBlockDirection() {
      return this.placedBlockDirection;
   }

   public float getPlacedBlockOffsetX() {
      return this.facingX;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public C08PacketPlayerBlockPlacement(BlockPos pos, int n, ItemStack stack2, float f, float f2, float f3) {
      this.position = pos;
      this.placedBlockDirection = n;
      this.stack = stack2 != null ? stack2.copy() : null;
      this.facingX = f;
      this.facingY = f2;
      this.facingZ = f3;
   }

   public C08PacketPlayerBlockPlacement() {
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processPlayerBlockPlacement(this);
   }
}
