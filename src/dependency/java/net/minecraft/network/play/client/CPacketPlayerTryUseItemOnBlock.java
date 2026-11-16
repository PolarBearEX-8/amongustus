package net.minecraft.network.play.client;

import client.util.EnumHand;
import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class CPacketPlayerTryUseItemOnBlock implements Packet {
   public float factor2;
   public BlockPos blockPos;
   public EnumFacing enumFacing;
   public float factor3;
   public EnumHand enumHand;
   public float factor;

   public float getFactor() {
      return this.factor;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public float getFactor2() {
      return this.factor2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.blockPos = packet.getBlockPos();
      this.enumFacing = (EnumFacing)packet.getEnum(EnumFacing.class);
      this.enumHand = (EnumHand)packet.getEnum(EnumHand.class);
      this.factor2 = packet.readFloat();
      this.factor3 = packet.readFloat();
      this.factor = packet.readFloat();
   }

   public EnumFacing getEnumFacing() {
      return this.enumFacing;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      INetHandlerPlayServer iNetHandlerPlayServer = (INetHandlerPlayServer)iNetHandler;
      this.processPacket(iNetHandlerPlayServer);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyBlockPos(this.blockPos);
      packet.applyEnum(this.enumFacing);
      packet.applyEnum(this.enumHand);
      packet.writeFloat(this.factor2);
      packet.writeFloat(this.factor3);
      packet.writeFloat(this.factor);
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyCPacketPlayerTryUseItemOnB(this);
   }

   public EnumHand getEnumHand() {
      return this.enumHand;
   }

   public CPacketPlayerTryUseItemOnBlock() {
   }

   public CPacketPlayerTryUseItemOnBlock(BlockPos blockPos2, EnumFacing enumFacing2, EnumHand enumHand2, float posX, float posY, float posZ) {
      this.blockPos = blockPos2;
      this.enumFacing = enumFacing2;
      this.enumHand = enumHand2;
      this.factor2 = posX;
      this.factor3 = posY;
      this.factor = posZ;
   }
}
