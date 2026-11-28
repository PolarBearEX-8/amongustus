package net.minecraft.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public interface IWorldAccess {
   void markBlockForUpdate(BlockPos pos);

   void onEntityAdded(Entity entity);

   void notifyLightSet(int n, int n2, int n3);

   void playRecord(String str, BlockPos pos);

   void sendBlockBreakProgress(int n, BlockPos pos, int n2);

   void playSound(String str, double d, double d2, double d3, float f, float f2);

   void markBlockRangeForRenderUpdate(int n, int n2, int n3, int n4, int n5, int n6);

   void broadcastSound(int n, BlockPos pos, int n2);

   void spawnParticle(int n, boolean flag, double d, double d2, double d3, double d4, double d5, double d6, int[] ints);

   void onEntityRemoved(Entity entity);

   void playAuxSFX(EntityPlayer player, int n, BlockPos pos, int n2);

   void playSoundToNearExcept(EntityPlayer player, String str, double d, double d2, double d3, float f, float f2);
}
