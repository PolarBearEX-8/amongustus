package net.minecraft.block;

import client.event.PreMotionFilter;
import client.setting.BlockSolidType;
import client.setting.LocalOptionType;
import client.setting.MovementProperty;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import client.util.SplineWatcher;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCocoa extends BaseBlockAdapter implements PreMotionFilter {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("age", 0, 2);

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!this.matchesWorld(world, blockPos, blockState)) {
         this.applyWorld5(world, blockPos, blockState);
      } else if (world.random.nextInt(5) == 0) {
         int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
         if (comparable < 2) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, comparable + 1), 2);
         }
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
      byte byteValue = 1;
      if (comparable >= 2) {
         byteValue = 3;
      }

      for (int index = 0; index < byteValue; index++) {
         applyWorld(world, blockPos, new ItemStack(Items.dye, 1, LocalOptionType.BROWN.getCount2()));
      }
   }

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      return LocalOptionType.BROWN.getCount2();
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      EnumFacing comparable = (EnumFacing)iBlockState.getComparable(autoWalkPath);
      int comparable2 = (Integer)iBlockState.getComparable(auxiliaryImmutableSetEntry);
      int count4 = 4 + comparable2 * 2;
      int count5 = 5 + comparable2 * 2;
      float factor = (float)count4 / 2.0F;
      switch (SplineWatcher.countArray[comparable.ordinal()]) {
         case 1:
            this.setBlockBounds((8.0F - factor) / 16.0F, (12.0F - (float)count5) / 16.0F, (15.0F - (float)count4) / 16.0F, (8.0F + factor) / 16.0F, 0.75F, 0.9375F);
            break;
         case 2:
            this.setBlockBounds((8.0F - factor) / 16.0F, (12.0F - (float)count5) / 16.0F, 0.0625F, (8.0F + factor) / 16.0F, 0.75F, (1.0F + (float)count4) / 16.0F);
            break;
         case 3:
            this.setBlockBounds(0.0625F, (12.0F - (float)count5) / 16.0F, (8.0F - factor) / 16.0F, (1.0F + (float)count4) / 16.0F, 0.75F, (8.0F + factor) / 16.0F);
            break;
         case 4:
            this.setBlockBounds((15.0F - (float)count4) / 16.0F, (12.0F - (float)count5) / 16.0F, (8.0F - factor) / 16.0F, 0.9375F, 0.75F, (8.0F + factor) / 16.0F);
      }
   }

   @Override
   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      this.processAction(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      return super.processAction(world, blockPos);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      EnumFacing enumFacing = EnumFacing.resolveEnumFacing((double)livingEntity.rotationYaw);
      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(autoWalkPath, enumFacing), 2);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.dye;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, EnumFacing.resolveEnumFacing(count))
         .resolveIBlockState(auxiliaryImmutableSetEntry, (count & 15) >> 2);
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) < 2;
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) + 1), 2);
   }

   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      BlockPos blockPos2 = blockPos.offset((EnumFacing)blockState.getComparable(autoWalkPath));
      IBlockState blockState2 = world.getBlockState(blockPos2);
      return blockState2.getBlock() == Blocks.block140 && blockState2.getComparable(BlockPlanks.immutableSetEntry) == MovementProperty.JUNGLE;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!this.matchesWorld(world, blockPos, blockState)) {
         this.applyWorld5(world, blockPos, blockState);
      }
   }

   @Override
   public boolean nextFloat(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      return true;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      EnumFacing enumFacing2 = enumFacing;
      if (!enumFacing.getEnumFacingXType().isActiveAsFlag()) {
         enumFacing2 = EnumFacing.NORTH;
      }

      return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing2.getEnumFacing5()).resolveIBlockState(auxiliaryImmutableSetEntry, 0);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, auxiliaryImmutableSetEntry});
   }

   private void applyWorld5(World world, BlockPos blockPos, IBlockState blockState) {
      world.matchesBlockPos(blockPos, Blocks.air.getDefaultState(), 3);
      this.processAction(world, blockPos, blockState, 0);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset() | (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) << 2;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      this.processAction(world, count, count2, count3);
      return super.processAction(world, blockState, count, count2, count3);
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockCocoa() {
      super(Material.material24);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH).resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.processAction(true);
   }
}
