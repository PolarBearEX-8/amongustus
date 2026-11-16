package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S0APacketUseBed implements Packet {
   public int count;
   public BlockPos blockPos;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
      this.blockPos = packet.getBlockPos();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS0APacketUseBed(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
      packet.applyBlockPos(this.blockPos);
   }

   public EntityPlayer resolveEntityPlayer(World world) {
      return (EntityPlayer)world.getEntityByID(this.count);
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public S0APacketUseBed(EntityPlayer player, BlockPos blockPos2) {
      this.count = player.getEntityId();
      this.blockPos = blockPos2;
   }

   public S0APacketUseBed() {
   }
}
