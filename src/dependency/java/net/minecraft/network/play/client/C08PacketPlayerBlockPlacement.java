package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class C08PacketPlayerBlockPlacement implements Packet {
   public static final BlockPos blockPos = new BlockPos(-1, -1, -1);
   public BlockPos blockPos2;
   public int entityId;
   public ItemStack itemStack;
   public float yawRotation2;
   public float yawRotation;
   public float yawRotation1;

   public void setItemStack(ItemStack itemStack2) {
      this.itemStack = itemStack2;
   }

   public void setEntityId(int entityId2) {
      this.entityId = entityId2;
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public float getYawRotation2() {
      return this.yawRotation2;
   }

   public void setYawRotation(float yawRotation3) {
      this.yawRotation = yawRotation3;
   }

   public float getYawRotation1() {
      return this.yawRotation1;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.blockPos2 = packet.getBlockPos();
      this.entityId = packet.readUnsignedByte();
      this.itemStack = packet.getItemStack();
      this.yawRotation2 = (float)packet.readUnsignedByte() / 16.0F;
      this.yawRotation = (float)packet.readUnsignedByte() / 16.0F;
      this.yawRotation1 = (float)packet.readUnsignedByte() / 16.0F;
   }

   public void setYawRotation1(float yawRotation3) {
      this.yawRotation1 = yawRotation3;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processAction((INetHandlerPlayServer)iNetHandler);
   }

   public int getEntityId() {
      return this.entityId;
   }

   public float getYawRotation() {
      return this.yawRotation;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyBlockPos(this.blockPos2);
      packet.writeByte(this.entityId);
      packet.applyItemStack(this.itemStack);
      packet.writeByte((int)(this.yawRotation2 * 16.0F));
      packet.writeByte((int)(this.yawRotation * 16.0F));
      packet.writeByte((int)(this.yawRotation1 * 16.0F));
   }

   public void setBlockPos2(BlockPos blockPos3) {
      this.blockPos2 = blockPos3;
   }

   public void processAction(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.handleC08PacketPlayerBlockPlacement(this);
   }

   public BlockPos getBlockPos2() {
      return this.blockPos2;
   }

   public void setYawRotation2(float yawRotation3) {
      this.yawRotation2 = yawRotation3;
   }

   public C08PacketPlayerBlockPlacement(BlockPos blockPos3, int count, ItemStack stack, float posX, float posY, float posZ) {
      this.blockPos2 = blockPos3;
      this.entityId = count;
      this.itemStack = stack != null ? stack.getItemStack() : null;
      this.yawRotation2 = posX;
      this.yawRotation = posY;
      this.yawRotation1 = posZ;
   }

   public C08PacketPlayerBlockPlacement() {
   }

   public C08PacketPlayerBlockPlacement(ItemStack stack) {
      this(blockPos, 255, stack, 0.0F, 0.0F, 0.0F);
   }
}
