package baritone.utils;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;

public final class BlockStateInterfaceAccessWrapper implements IBlockAccess {
   private final BlockStateInterface bsi;
   private final IBlockAccess world;

   @Override
   public boolean isAirBlock(int n, int n2, int n3) {
      return this.bsi.get0(n, n2, n3).getBlock().getMaterial() == Material.air;
   }

   @Override
   public boolean isAirBlock(BlockPos pos) {
      return this.bsi.get0(pos.getX(), pos.getY(), pos.getZ()).getBlock().getMaterial() == Material.air;
   }

   @Override
   public IBlockState getBlockState(double d, double d2, double d3) {
      return this.bsi.get0((int)d, (int)d2, (int)d3);
   }

   @Override
   public int getStrongPower(BlockPos pos, EnumFacing facing) {
      return 0;
   }

   @Override
   public WorldType getWorldType() {
      return this.world.getWorldType();
   }

   public BlockStateInterfaceAccessWrapper(BlockStateInterface blockStateInterface, IBlockAccess world2) {
      this.bsi = blockStateInterface;
      this.world = world2;
   }

   @Override
   public boolean extendedLevelsInChunkCache() {
      return false;
   }

   @Override
   public int getCombinedLight(int n, int n2, int n3, int n4) {
      return 0;
   }

   @Override
   public BiomeGenBase getBiomeGenForCoords(BlockPos pos) {
      return BiomeGenBase.forest;
   }

   @Override
   public IBlockState getBlockState2(int n, int n2, int n3) {
      return this.bsi.get0(n, n2, n3);
   }

   @Override
   public IBlockState getBlockState(BlockPos pos) {
      return this.bsi.get0(pos.getX(), pos.getY(), pos.getZ());
   }

   @Override
   public TileEntity getTileEntity(BlockPos pos) {
      return null;
   }
}
