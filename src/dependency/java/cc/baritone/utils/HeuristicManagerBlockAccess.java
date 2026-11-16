package cc.baritone.utils;

import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.IBlockState;
import client.util.InfoManager;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public final class HeuristicManagerBlockAccess implements IBlockAccess {
   public final HeuristicManager heuristicManager;
   public final IBlockAccess iBlockAccess;

   @Override
   public int computeCount(BlockPos blockPos, EnumFacing enumFacing) {
      return 0;
   }

   @Override
   public boolean isEnabled() {
      return false;
   }

   @Override
   public boolean checkCondition(int count, int count2, int count3) {
      return this.heuristicManager.resolveIBlockState(count, count2, count3).getBlock().getMaterial() == Material.coreItemEntry;
   }

   @Override
   public boolean isAirBlock(BlockPos blockPos) {
      return this.heuristicManager.resolveIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ()).getBlock().getMaterial() == Material.coreItemEntry;
   }

   @Override
   public IBlockState getIBlockState(int count, int count2, int count3) {
      return this.heuristicManager.resolveIBlockState(count, count2, count3);
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(BlockPos blockPos) {
      return BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4;
   }

   @Override
   public IBlockState getBlockState(BlockPos blockPos) {
      return this.heuristicManager.resolveIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   @Nullable
   @Override
   public TileEntity resolveTileEntity(BlockPos blockPos) {
      return null;
   }

   @Override
   public int computeCount(int count, int count2, int count3, int count4) {
      return 0;
   }

   @Override
   public InfoManager getInfoManager() {
      return this.iBlockAccess.getInfoManager();
   }

   @Override
   public IBlockState getIBlockState2(double posX, double posY, double posZ) {
      return this.heuristicManager.resolveIBlockState((int)posX, (int)posY, (int)posZ);
   }

   public HeuristicManagerBlockAccess(HeuristicManager heuristicManager2, IBlockAccess iBlockAccess2) {
      this.heuristicManager = heuristicManager2;
      this.iBlockAccess = iBlockAccess2;
   }
}
