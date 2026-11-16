package net.minecraft.block;

import client.event.PreMotionFilter;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import client.util.InternalPredicateAdapter;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMelonStem extends SecondaryBlockAdapter implements PreMotionFilter {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry;
   public static final AutoWalkPath autoWalkPath;
   public final Block block;

   @Override
   public void processAction2() {
      float factor = 0.125F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, 0.25F, 0.5F + factor);
   }

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      super.applyWorld3(world, blockPos, blockState, factor, count);
      if (!world.state) {
         Item item = this.getItem();
         if (item != null) {
            int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);

            for (int index = 0; index < 3; index++) {
               if (world.random.nextInt(15) <= comparable) {
                  applyWorld(world, blockPos, new ItemStack(item));
               }
            }
         }
      }
   }

   @Override
   public int processAction2(IBlockState blockState) {
      if (blockState.getBlock() != this) {
         return super.processAction2(blockState);
      } else {
         int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
         int count = comparable * 32;
         int count2 = 255 - comparable * 8;
         int count3 = comparable * 4;
         return count << 16 | count2 << 8 | count3;
      }
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      BlockPos blockPos2 = blockPos;
      super.updateTick(world, blockPos, blockState, random);
      if (world.computeCountByIndex(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()) >= 9) {
         float factor = BlockWheat.computeFactor(this, world, blockPos);
         if (random.nextInt((int)(25.0F / factor) + 1) == 0) {
            int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
            if (comparable < 7) {
               IBlockState blockState2 = blockState.resolveIBlockState(auxiliaryImmutableSetEntry, comparable + 1);
               world.matchesBlockPos(blockPos, blockState2, 2);
            } else {
               for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable<?>)MovementFactorProperty.HORIZONTAL) {
                  if (world.getBlockState(blockPos2.offset(enumFacing)).getBlock() == this.block) {
                     return;
                  }
               }

               blockPos2 = blockPos2.offset(MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random));
               Block block2 = world.getBlockState(blockPos2.down()).getBlock();
               if (world.getBlockState(blockPos2).getBlock().coreItemEntry == Material.coreItemEntry
                  && (block2 == Blocks.block7 || block2 == Blocks.block115 || block2 == Blocks.blockGrass)) {
                  world.matchesBlockPos(blockPos2, this.block.getDefaultState());
               }
            }
         }
      }
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState blockState2 = blockState.resolveIBlockState(autoWalkPath, EnumFacing.UP);

      for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable<?>)MovementFactorProperty.HORIZONTAL) {
         if (iBlockAccess.getIBlockState(count + enumFacing.getInt2(), count2 + enumFacing.getInt3(), count3 + enumFacing.getInt()).getBlock() == this.block) {
            blockState2 = blockState2.resolveIBlockState(autoWalkPath, enumFacing);
            break;
         }
      }

      return blockState2;
   }

   public void applyWorld5(World world, BlockPos blockPos, IBlockState blockState) {
      int count = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) + MathHelper.nextInt(world.random, 2, 5);
      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, Math.min(7, count)), 2);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.coordOffset6 = (double)((float)((Integer)iBlockAccess.getIBlockState(count, count2, count3).getComparable(auxiliaryImmutableSetEntry) * 2 + 2) / 16.0F);
      float factor = 0.125F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, (float)this.coordOffset6, 0.5F + factor);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return this.getItem();
   }

   @Override
   public boolean matchesBlockByBlock(Block block2) {
      return block2 == Blocks.block7;
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) != 7;
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      this.applyWorld5(world, blockPos, blockState);
   }

   @Override
   public boolean nextFloat(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      return true;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return null;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      return this.processAction2(iBlockAccess.getBlockState(blockPos));
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry, autoWalkPath});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   public Item getItem() {
      return this.block == Blocks.block146 ? Items.pumpkinSeeds : (this.block == Blocks.block67 ? Items.melonSeeds : null);
   }

   public BlockMelonStem(Block block2) {
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0).resolveIBlockState(autoWalkPath, EnumFacing.UP));
      this.block = block2;
      this.processAction(true);
      float factor = 0.125F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, 0.25F, 0.5F + factor);
      this.processAction((ItemGroupManager)null);
   }

   static {
      String[] textArray = new String[]{"facing", "age"};
      auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry(textArray[1], 0, 7);
      autoWalkPath = AutoWalkPath.resolveAutoWalkPath(textArray[0], new InternalPredicateAdapter());
   }
}
