package client.util;

import client.setting.BlockSolidType;
import client.setting.EnumFacingXType;
import java.util.Random;
import net.minecraft.block.BaseBlockAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class VectorRanker extends BaseBlockAdapter {
   public final boolean enabled;

   public int computeCount(World world, BlockPos blockPos, IBlockState blockState) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      BlockPos blockPos2 = blockPos.offset(comparable);
      int count = world.computeCountByBlockPos(blockPos2, comparable);
      if (count >= 15) {
         return count;
      } else {
         IBlockState blockState2 = world.getBlockState(blockPos2);
         return Math.max(count, blockState2.getBlock() == Blocks.blockRedstoneWire ? (Integer)blockState2.getComparable(BlockRedstoneWire.auxiliaryImmutableSetEntry) : 0);
      }
   }

   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      return this.computeCount(world, blockPos, blockState) > 0;
   }

   public boolean matchesBlock2(Block block) {
      return block == this.resolveIBlockStateByIBlockState(this.getDefaultState()).getBlock()
         || block == this.resolveIBlockState(this.getDefaultState()).getBlock();
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!this.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState)) {
         boolean flag = this.matchesWorld(world, blockPos, blockState);
         if (this.enabled && !flag) {
            world.matchesBlockPos(blockPos, this.resolveIBlockState(blockState), 2);
         } else if (!this.enabled) {
            world.matchesBlockPos(blockPos, this.resolveIBlockStateByIBlockState(blockState), 2);
            if (!flag) {
               world.applyBlockPos2(blockPos, this.resolveIBlockStateByIBlockState(blockState).getBlock(), this.computeCountByIBlockState(blockState), -1);
            }
         }
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public void breakBlock(World world, BlockPos blockPos, IBlockState blockState) {
      if (this.enabled) {
         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            world.applyBlockPosByBlockPos(blockPos.offset(enumFacing), this);
         }
      }

      super.breakBlock(world, blockPos, blockState);
   }

   public boolean matchesBlockByBlock(Block block) {
      return block.processAction7();
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      if (this.matchesWorld(world, blockPos, blockState)) {
         world.applyBlockPos(blockPos, this, 1);
      }
   }

   public int computeCountByIBlockState(IBlockState blockState) {
      return this.computeCount(blockState);
   }

   @Override
   public void processAction2(World world, BlockPos blockPos, IBlockState blockState, Random random) {
   }

   @Override
   public boolean processAction7() {
      return true;
   }

   public abstract IBlockState resolveIBlockState(IBlockState blockState);

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (this.matchesWorld4(world, blockPos)) {
         this.applyWorld5(world, blockPos, blockState);
      } else {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);

         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            world.applyBlockPosByBlockPos(blockPos.offset(enumFacing), this);
         }
      }
   }

   public int computeCount(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState) {
      return 15;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return !this.matchesIBlockState(blockState) ? 0 : (blockState.getComparable(autoWalkPath) == enumFacing ? this.computeCount(iBlockAccess, blockPos, blockState) : 0);
   }

   public int computeCount(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      Block block = iBlockState.getBlock();
      return this.matchesBlockByBlock(block)
         ? (
            block == Blocks.blockRedstoneWire
               ? (Integer)iBlockState.getComparable(BlockRedstoneWire.auxiliaryImmutableSetEntry)
               : iBlockAccess.computeCount(new BlockPos(count, count2, count3), enumFacing)
         )
         : 0;
   }

   public boolean matchesWorld4(World world, BlockPos blockPos) {
      return World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()) ? super.processAction7(world, blockPos) : false;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2().getEnumFacing5());
   }

   public boolean matchesWorld2(World world, BlockPos blockPos, IBlockState blockState) {
      EnumFacing enumFacing = ((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacing5();
      BlockPos blockPos2 = blockPos.offset(enumFacing);
      return matchesBlock3(world.getBlockState(blockPos2).getBlock()) ? world.getBlockState(blockPos2).getComparable(autoWalkPath) != enumFacing : false;
   }

   public abstract int computeCount(IBlockState blockState);

   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, IBlockState blockState) {
      return false;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return enumFacing.getEnumFacingXType() != EnumFacingXType.Y;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      this.applyWorld7(world, blockPos, blockState);
   }

   public static boolean matchesBlock3(Block block) {
      return Blocks.blockPoweredRepeater.matchesBlock2(block) || Blocks.blockPoweredComparator2.matchesBlock2(block);
   }

   @Override
   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return this.processAction(iBlockAccess, blockPos, blockState, enumFacing);
   }

   public boolean matchesIBlockState(IBlockState blockState) {
      return this.enabled;
   }

   @Override
   public boolean matchesBlock(Block block) {
      return this.matchesBlock2(block);
   }

   public void applyWorld5(World world, BlockPos blockPos, IBlockState blockState) {
      if (!this.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState)) {
         boolean flag = this.matchesWorld(world, blockPos, blockState);
         if ((this.enabled && !flag || !this.enabled && flag) && !world.contains(blockPos, this)) {
            byte byteValue = -1;
            if (this.matchesWorld2(world, blockPos, blockState)) {
               byteValue = -3;
            } else if (this.enabled) {
               byteValue = -2;
            }

            world.applyBlockPos2(blockPos, this, this.computeCount(blockState), byteValue);
         }
      }
   }

   public void applyWorld7(World world, BlockPos blockPos, IBlockState blockState) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      BlockPos blockPos2 = blockPos.offset(comparable.getEnumFacing5());
      world.applyBlockPos2(blockPos2, this);
      world.applyBlockPos(blockPos2, this, comparable);
   }

   public abstract IBlockState resolveIBlockStateByIBlockState(IBlockState blockState);

   public int computeCount(IBlockAccess iBlockAccess, int count, int count2, int count3, IBlockState blockState) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      EnumFacing enumFacingAsEnumFacing = comparable.getEnumFacingAsEnumFacing();
      EnumFacing enumFacing = comparable.getEnumFacing2();
      return Math.max(
         this.computeCount(iBlockAccess, count + enumFacingAsEnumFacing.getInt2(), count2 + enumFacingAsEnumFacing.getInt3(), count3 + enumFacingAsEnumFacing.getInt(), enumFacingAsEnumFacing),
         this.computeCount(iBlockAccess, count + enumFacing.getInt2(), count2 + enumFacing.getInt3(), count3 + enumFacing.getInt(), enumFacing)
      );
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public VectorRanker(boolean flag) {
      super(Material.material22);
      this.enabled = flag;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
   }
}
