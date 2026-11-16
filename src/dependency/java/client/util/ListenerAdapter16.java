package client.util;

import client.event.ContextListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;

public class ListenerAdapter16 implements ContextListener {
   public VanillaRunnable vanillaRunnable;
   public WorldServer worldServer;

   @Override
   public void applyBlockPos(BlockPos blockPos) {
      this.worldServer.getManagedLoggerEntry().applyBlockPos(blockPos);
   }

   @Override
   public void applyEntityPlayerByEntityPlayer(EntityPlayer player, int count, BlockPos blockPos, int count2) {
      this.vanillaRunnable
         .getMovingManager()
         .applyEntityPlayer(
            player,
            (double)blockPos.getX(),
            (double)blockPos.getY(),
            (double)blockPos.getZ(),
            64.0,
            this.worldServer.secondaryFloatArrayEntry.getCountAsIndex(),
            new S28PacketEffect(count, blockPos, count2, false)
         );
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
   }

   @Override
   public void applyText(String text, double posX, double posY, double posZ, float factor, float factor2) {
      this.vanillaRunnable
         .getMovingManager()
         .applyPacket(
            posX,
            posY,
            posZ,
            factor > 1.0F ? (double)(16.0F * factor) : 16.0,
            this.worldServer.secondaryFloatArrayEntry.getCountAsIndex(),
            new S29PacketSoundEffect(text, posX, posY, posZ, factor, factor2)
         );
   }

   public ListenerAdapter16(VanillaRunnable vanillaRunnable2, WorldServer worldServer2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.worldServer = worldServer2;
   }

   @Override
   public void applyBlockPosByBlockPos(int count, BlockPos blockPos, int count2) {
      this.vanillaRunnable.getMovingManager().applyPacket(new S28PacketEffect(count, blockPos, count2, true));
   }

   @Override
   public void applyEntityPlayerByEntityPlayer(EntityPlayer player, String text, double posX, double posY, double posZ, float factor, float factor2) {
      this.vanillaRunnable
         .getMovingManager()
         .applyEntityPlayer(
            player,
            posX,
            posY,
            posZ,
            factor > 1.0F ? (double)(16.0F * factor) : 16.0,
            this.worldServer.secondaryFloatArrayEntry.getCountAsIndex(),
            new S29PacketSoundEffect(text, posX, posY, posZ, factor, factor2)
         );
   }

   @Override
   public void applyText(String text, BlockPos blockPos) {
   }

   @Override
   public void applyEntity3(Entity entity) {
      this.worldServer.getSecondaryLoggerEntry().applyEntity(entity);
   }

   @Override
   public void applyIndexArray(int count, boolean flag, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray) {
   }

   @Override
   public void applyEntityByEntity(Entity entity) {
      this.worldServer.getSecondaryLoggerEntry().applyEntity2(entity);
      this.worldServer.getThePlayerNameManager().applyEntityByEntity(entity);
   }

   @Override
   public void applyBlockPos(int count, BlockPos blockPos, int count2) {
      for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Iterable<?>)this.vanillaRunnable.getMovingManager().getList()) {
         if (entityPlayerAdapter != null && entityPlayerAdapter.worldObj == this.worldServer && entityPlayerAdapter.getEntityId() != count) {
            double offset = (double)blockPos.getX() - entityPlayerAdapter.posX;
            double offset2 = (double)blockPos.getY() - entityPlayerAdapter.posY;
            double offset3 = (double)blockPos.getZ() - entityPlayerAdapter.posZ;
            if (offset * offset + offset2 * offset2 + offset3 * offset3 < 1024.0) {
               entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S25PacketBlockBreakAnim(count, blockPos, count2));
            }
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }
}
