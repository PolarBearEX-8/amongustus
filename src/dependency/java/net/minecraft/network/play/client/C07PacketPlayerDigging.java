package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.PlayerDiggingActionType;
import java.io.IOException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class C07PacketPlayerDigging implements Packet {
   public BlockPos blockPos;
   public EnumFacing enumFacing;
   public PlayerDiggingActionType playerDiggingActionType;

   public EnumFacing getEnumFacing() {
      return this.enumFacing;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.playerDiggingActionType = (PlayerDiggingActionType)packet.getEnum(PlayerDiggingActionType.class);
      this.blockPos = packet.getBlockPos();
      this.enumFacing = EnumFacing.getEnumFacing6(packet.readUnsignedByte());
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processAction((INetHandlerPlayServer)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyEnum(this.playerDiggingActionType);
      packet.applyBlockPos(this.blockPos);
      packet.writeByte(this.enumFacing.getIndexOffset1());
   }

   public void processAction(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.handleC07PacketPlayerDigging(this);
   }

   public PlayerDiggingActionType getPlayerDiggingActionType() {
      return this.playerDiggingActionType;
   }

   public C07PacketPlayerDigging(PlayerDiggingActionType playerDiggingActionType2, BlockPos blockPos2, EnumFacing enumFacing2) {
      this.playerDiggingActionType = playerDiggingActionType2;
      this.blockPos = blockPos2;
      this.enumFacing = enumFacing2;
   }

   public C07PacketPlayerDigging() {
   }
}
