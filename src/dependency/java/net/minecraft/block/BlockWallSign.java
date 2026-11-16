package net.minecraft.block;

import client.setting.EnumFacingXType;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.GeometrySerializer;
import client.util.IBlockState;
import client.util.VClipCollisionFilter;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWallSign extends VClipCollisionFilter {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL);

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      EnumFacing comparable = (EnumFacing)iBlockAccess.getIBlockState(count, count2, count3).getComparable(autoWalkPath);
      float factor = 0.28125F;
      float factor2 = 0.78125F;
      float factor3 = 0.0F;
      float factor4 = 1.0F;
      float factor5 = 0.125F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      switch (GeometrySerializer.countArray[comparable.ordinal()]) {
         case 1:
            this.setBlockBounds(factor3, factor, 1.0F - factor5, factor4, factor2, 1.0F);
            break;
         case 2:
            this.setBlockBounds(factor3, factor, 0.0F, factor4, factor2, factor5);
            break;
         case 3:
            this.setBlockBounds(1.0F - factor5, factor, factor3, 1.0F, factor2, factor4);
            break;
         case 4:
            this.setBlockBounds(0.0F, factor, factor3, factor5, factor2, factor4);
      }
   }

   @Override
   public IBlockState processAction(int count) {
      EnumFacing enumFacing = EnumFacing.getEnumFacing6(count);
      if (enumFacing.getEnumFacingXType() == EnumFacingXType.Y) {
         enumFacing = EnumFacing.NORTH;
      }

      return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      if (!world.getBlockState(blockPos.offset(comparable.getEnumFacing5())).getBlock().getMaterial().canExecute2()) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
      }

      super.processAction(world, blockPos, blockState, block);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
   }

   public BlockWallSign() {
      this.setIBlockState(this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH));
   }
}
