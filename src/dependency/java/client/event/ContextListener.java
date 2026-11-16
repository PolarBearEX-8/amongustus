package client.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public interface ContextListener {
   void applyEntityPlayerByEntityPlayer(EntityPlayer player, String text, double posX, double posY, double posZ, float factor, float factor2);

   void applyEntityByEntity(Entity entity);

   void applyIndex(int count, int count2, int count3, int count4, int count5, int count6);

   void applyBlockPosByBlockPos(int count, BlockPos blockPos, int count2);

   void applyText(String text, double posX, double posY, double posZ, float factor, float factor2);

   void applyEntity3(Entity entity);

   void applyIndex(int count, int count2, int count3);

   void applyEntityPlayerByEntityPlayer(EntityPlayer player, int count, BlockPos blockPos, int count2);

   void applyBlockPos(int count, BlockPos blockPos, int count2);

   void applyText(String text, BlockPos blockPos);

   void applyIndexArray(int count, boolean flag, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray);

   void applyBlockPos(BlockPos blockPos);
}
