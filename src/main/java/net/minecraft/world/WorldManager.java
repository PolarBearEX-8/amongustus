package net.minecraft.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class WorldManager implements IWorldAccess {
   private MinecraftServer mcServer;
   private WorldServer theWorldServer;

   @Override
   public void markBlockForUpdate(BlockPos pos) {
      this.theWorldServer.getPlayerManager().markBlockForUpdate(pos);
   }

   @Override
   public void playSoundToNearExcept(EntityPlayer player, String str, double d, double d2, double d3, float f, float f2) {
      this.mcServer
         .getConfigurationManager()
         .sendToAllNearExcept(
            player,
            d,
            d2,
            d3,
            f > 1.0F ? (double)(16.0F * f) : 16.0,
            this.theWorldServer.provider.getDimensionId(),
            new S29PacketSoundEffect(str, d, d2, d3, f, f2)
         );
   }

   @Override
   public void onEntityAdded(Entity entity) {
      this.theWorldServer.getEntityTracker().trackEntity(entity);
   }

   @Override
   public void playAuxSFX(EntityPlayer player, int n, BlockPos pos, int n2) {
      this.mcServer
         .getConfigurationManager()
         .sendToAllNearExcept(
            player,
            (double)pos.getX(),
            (double)pos.getY(),
            (double)pos.getZ(),
            64.0,
            this.theWorldServer.provider.getDimensionId(),
            new S28PacketEffect(n, pos, n2, false)
         );
   }

   @Override
   public void markBlockRangeForRenderUpdate(int n, int n2, int n3, int n4, int n5, int n6) {
   }

   @Override
   public void sendBlockBreakProgress(int n, BlockPos pos, int n2) {
      for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(this.mcServer.getConfigurationManager().getPlayerList())) {
         if (entityPlayerMP != null && entityPlayerMP.entityWorld == this.theWorldServer && entityPlayerMP.getEntityId() != n) {
            double d = (double)pos.getX() - entityPlayerMP.posX;
            double d2 = (double)pos.getY() - entityPlayerMP.posY;
            double d3 = (double)pos.getZ() - entityPlayerMP.posZ;
            if (d * d + d2 * d2 + d3 * d3 < 1024.0) {
               entityPlayerMP.playerNetServerHandler.sendPacket(new S25PacketBlockBreakAnim(n, pos, n2));
            }
         }
      }
   }

   @Override
   public void broadcastSound(int n, BlockPos pos, int n2) {
      this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S28PacketEffect(n, pos, n2, true));
   }

   @Override
   public void onEntityRemoved(Entity entity) {
      this.theWorldServer.getEntityTracker().untrackEntity(entity);
      this.theWorldServer.getScoreboard().removeEntity(entity);
   }

   @Override
   public void playSound(String str, double d, double d2, double d3, float f, float f2) {
      this.mcServer
         .getConfigurationManager()
         .sendToAllNear(
            d,
            d2,
            d3,
            f > 1.0F ? (double)(16.0F * f) : 16.0,
            this.theWorldServer.provider.getDimensionId(),
            new S29PacketSoundEffect(str, d, d2, d3, f, f2)
         );
   }

   public WorldManager(MinecraftServer minecraftServer, WorldServer world) {
      this.mcServer = minecraftServer;
      this.theWorldServer = world;
   }

   @Override
   public void spawnParticle(int n, boolean flag, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
   }

   @Override
   public void notifyLightSet(int n, int n2, int n3) {
   }

   @Override
   public void playRecord(String str, BlockPos pos) {
   }
}
