package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.MovementFactorProperty;
import client.util.CoreIntArrayEntry;
import client.util.DistanceQueue;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.StatCraftItemManager;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVine extends Block {
   public static final InternalImmutableSetEntry internalImmutableSetEntry5 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("up");
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("north");
   public static final InternalImmutableSetEntry internalImmutableSetEntry4 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("east");
   public static final InternalImmutableSetEntry internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("south");
   public static final InternalImmutableSetEntry internalImmutableSetEntry3 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("west");
   public static final InternalImmutableSetEntry[] internalImmutableSetEntryArray = new InternalImmutableSetEntry[]{
      internalImmutableSetEntry5, internalImmutableSetEntry, internalImmutableSetEntry2, internalImmutableSetEntry3, internalImmutableSetEntry4
   };

   private boolean matchesBlockByBlock(Block block) {
      return block.processAction14() && block.coreItemEntry.isActiveAsFlag();
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state && world.random.nextInt(4) == 0) {
         int count = 5;
         boolean flag = false;

         outerLoop:
         for (int index = -4; index <= 4; index++) {
            for (int index2 = -4; index2 <= 4; index2++) {
               for (int index3 = -1; index3 <= 1; index3++) {
                  if (world.getIBlockState(blockPos.getX() + index, blockPos.getY() + index3, blockPos.getZ() + index2).getBlock() == this) {
                     if (--count <= 0) {
                        flag = true;
                        break outerLoop;
                     }
                  }
               }
            }
         }

         EnumFacing enumFacing = EnumFacing.resolveEnumFacing(random);
         BlockPos blockPos2 = blockPos.up();
         if (enumFacing == EnumFacing.UP && blockPos.getY() < 255 && world.isAirBlock(blockPos2)) {
            if (!flag) {
               IBlockState blockState2 = blockState;

               for (EnumFacing enumFacing2 : (Iterable<EnumFacing>)(Iterable)(MovementFactorProperty.HORIZONTAL)) {
                  if (random.nextBoolean() || !this.matchesBlockByBlock(world.getBlockState(blockPos2.offset(enumFacing2)).getBlock())) {
                     blockState2 = blockState2.resolveIBlockState(resolveInternalImmutableSetEntry(enumFacing2), Boolean.FALSE);
                  }
               }

               if ((Boolean)blockState2.getComparable(internalImmutableSetEntry)
                  || (Boolean)blockState2.getComparable(internalImmutableSetEntry4)
                  || (Boolean)blockState2.getComparable(internalImmutableSetEntry2)
                  || (Boolean)blockState2.getComparable(internalImmutableSetEntry3)) {
                  world.matchesBlockPos(blockPos2, blockState2, 2);
               }
            }
         } else if (!enumFacing.getEnumFacingXType().isActiveAsFlag() || (Boolean)blockState.getComparable(resolveInternalImmutableSetEntry(enumFacing))) {
            if (blockPos.getY() > 1) {
               BlockPos blockPos3 = blockPos.down();
               IBlockState blockState3 = world.getBlockState(blockPos3);
               Block block = blockState3.getBlock();
               if (block.coreItemEntry == Material.coreItemEntry) {
                  IBlockState blockState4 = blockState;

                  for (EnumFacing enumFacing3 : (Iterable<EnumFacing>)(Iterable)(MovementFactorProperty.HORIZONTAL)) {
                     if (random.nextBoolean()) {
                        blockState4 = blockState4.resolveIBlockState(resolveInternalImmutableSetEntry(enumFacing3), Boolean.FALSE);
                     }
                  }

                  if ((Boolean)blockState4.getComparable(internalImmutableSetEntry)
                     || (Boolean)blockState4.getComparable(internalImmutableSetEntry4)
                     || (Boolean)blockState4.getComparable(internalImmutableSetEntry2)
                     || (Boolean)blockState4.getComparable(internalImmutableSetEntry3)) {
                     world.matchesBlockPos(blockPos3, blockState4, 2);
                  }
               } else if (block == this) {
                  Iterator iterator = MovementFactorProperty.HORIZONTAL.iterator();

                  while (iterator.hasNext()) {
                     InternalImmutableSetEntry internalImmutableSetEntry6 = resolveInternalImmutableSetEntry((EnumFacing)iterator.next());
                     if (random.nextBoolean() && (Boolean)blockState.getComparable(internalImmutableSetEntry6)) {
                        blockState3 = blockState3.resolveIBlockState(internalImmutableSetEntry6, Boolean.TRUE);
                     }
                  }

                  if ((Boolean)blockState3.getComparable(internalImmutableSetEntry)
                     || (Boolean)blockState3.getComparable(internalImmutableSetEntry4)
                     || (Boolean)blockState3.getComparable(internalImmutableSetEntry2)
                     || (Boolean)blockState3.getComparable(internalImmutableSetEntry3)) {
                     world.matchesBlockPos(blockPos3, blockState3, 2);
                  }
               }
            }
         } else if (!flag) {
            BlockPos blockPos4 = blockPos.offset(enumFacing);
            Block block2 = world.getBlockState(blockPos4).getBlock();
            if (block2.coreItemEntry == Material.coreItemEntry) {
               EnumFacing enumFacingAsEnumFacing = enumFacing.getEnumFacingAsEnumFacing();
               EnumFacing enumFacing4 = enumFacing.getEnumFacing2();
               boolean comparable = (Boolean)blockState.getComparable(resolveInternalImmutableSetEntry(enumFacingAsEnumFacing));
               boolean comparable2 = (Boolean)blockState.getComparable(resolveInternalImmutableSetEntry(enumFacing4));
               BlockPos blockPos5 = blockPos4.offset(enumFacingAsEnumFacing);
               BlockPos blockPos6 = blockPos4.offset(enumFacing4);
               if (comparable && this.matchesBlockByBlock(world.getBlockState(blockPos5).getBlock())) {
                  world.matchesBlockPos(blockPos4, this.getDefaultState().resolveIBlockState(resolveInternalImmutableSetEntry(enumFacingAsEnumFacing), Boolean.TRUE), 2);
               } else if (comparable2 && this.matchesBlockByBlock(world.getBlockState(blockPos6).getBlock())) {
                  world.matchesBlockPos(blockPos4, this.getDefaultState().resolveIBlockState(resolveInternalImmutableSetEntry(enumFacing4), Boolean.TRUE), 2);
               } else if (comparable && world.isAirBlock(blockPos5) && this.matchesBlockByBlock(world.getBlockState(blockPos.offset(enumFacingAsEnumFacing)).getBlock())) {
                  world.matchesBlockPos(
                     blockPos5, this.getDefaultState().resolveIBlockState(resolveInternalImmutableSetEntry(enumFacing.getEnumFacing5()), Boolean.TRUE), 2
                  );
               } else if (comparable2 && world.isAirBlock(blockPos6) && this.matchesBlockByBlock(world.getBlockState(blockPos.offset(enumFacing4)).getBlock())) {
                  world.matchesBlockPos(
                     blockPos6, this.getDefaultState().resolveIBlockState(resolveInternalImmutableSetEntry(enumFacing.getEnumFacing5()), Boolean.TRUE), 2
                  );
               } else if (this.matchesBlockByBlock(world.getBlockState(blockPos4.up()).getBlock())) {
                  world.matchesBlockPos(blockPos4, this.getDefaultState(), 2);
               }
            } else if (block2.coreItemEntry.isActive3() && block2.processAction14()) {
               world.matchesBlockPos(blockPos, blockState.resolveIBlockState(resolveInternalImmutableSetEntry(enumFacing), Boolean.TRUE), 2);
            }
         }
      }
   }

   @Override
   public void processAction2() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public int processAction2(IBlockState blockState) {
      return CoreIntArrayEntry.getCount();
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      float factor = 1.0F;
      float factor2 = 1.0F;
      float factor3 = 1.0F;
      float factor4 = 0.0F;
      float factor5 = 0.0F;
      float factor6 = 0.0F;
      boolean flag = false;
      if ((Boolean)iBlockAccess.getIBlockState(count, count2, count3).getComparable(internalImmutableSetEntry3)) {
         factor4 = Math.max(factor4, 0.0625F);
         factor = 0.0F;
         factor2 = 0.0F;
         factor5 = 1.0F;
         factor3 = 0.0F;
         factor6 = 1.0F;
         flag = true;
      }

      if ((Boolean)iBlockAccess.getIBlockState(count, count2, count3).getComparable(internalImmutableSetEntry4)) {
         factor = Math.min(factor, 0.9375F);
         factor4 = 1.0F;
         factor2 = 0.0F;
         factor5 = 1.0F;
         factor3 = 0.0F;
         factor6 = 1.0F;
         flag = true;
      }

      if ((Boolean)iBlockAccess.getIBlockState(count, count2, count3).getComparable(internalImmutableSetEntry)) {
         factor6 = Math.max(factor6, 0.0625F);
         factor3 = 0.0F;
         factor = 0.0F;
         factor4 = 1.0F;
         factor2 = 0.0F;
         factor5 = 1.0F;
         flag = true;
      }

      if ((Boolean)iBlockAccess.getIBlockState(count, count2, count3).getComparable(internalImmutableSetEntry2)) {
         factor3 = Math.min(factor3, 0.9375F);
         factor6 = 1.0F;
         factor = 0.0F;
         factor4 = 1.0F;
         factor2 = 0.0F;
         factor5 = 1.0F;
         flag = true;
      }

      if (!flag && this.matchesBlockByBlock(iBlockAccess.getIBlockState(count, count2 + 1, count3).getBlock())) {
         factor2 = Math.min(factor2, 0.9375F);
         factor5 = 1.0F;
         factor = 0.0F;
         factor4 = 1.0F;
         factor3 = 0.0F;
         factor6 = 1.0F;
      }

      this.setBlockBounds(factor, factor2, factor3, factor4, factor5, factor6);
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return blockState.resolveIBlockState(internalImmutableSetEntry5, iBlockAccess.getIBlockState(count, count2 + 1, count3).getBlock().isActive4());
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(internalImmutableSetEntry2, (count & 1) > 0)
         .resolveIBlockState(internalImmutableSetEntry3, (count & 2) > 0)
         .resolveIBlockState(internalImmutableSetEntry, (count & 4) > 0)
         .resolveIBlockState(internalImmutableSetEntry4, (count & 8) > 0);
   }

   public static InternalImmutableSetEntry resolveInternalImmutableSetEntry(EnumFacing enumFacing) {
      switch (DistanceQueue.countArray[enumFacing.ordinal()]) {
         case 1:
            return internalImmutableSetEntry5;
         case 2:
            return internalImmutableSetEntry;
         case 3:
            return internalImmutableSetEntry2;
         case 4:
            return internalImmutableSetEntry4;
         case 5:
            return internalImmutableSetEntry3;
         default:
            throw new IllegalArgumentException(enumFacing + " is an invalid choice");
      }
   }

   @Override
   public int processAction6() {
      return CoreIntArrayEntry.getCount();
   }

   @Override
   public boolean processAction6(World world, BlockPos blockPos) {
      return true;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.state && !this.matchesWorld(world, blockPos, blockState)) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return null;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      return iBlockAccess.resolveBiomeIDIsOutOfBoundsManager(blockPos).computeCount(blockPos);
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      IBlockState blockState = this.getDefaultState()
         .resolveIBlockState(internalImmutableSetEntry5, Boolean.FALSE)
         .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
         .resolveIBlockState(internalImmutableSetEntry4, Boolean.FALSE)
         .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
         .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE);
      return enumFacing.getEnumFacingXType().isActiveAsFlag() ? blockState.resolveIBlockState(resolveInternalImmutableSetEntry(enumFacing.getEnumFacing5()), Boolean.TRUE) : blockState;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, EnumFacing enumFacing) {
      switch (DistanceQueue.countArray[enumFacing.ordinal()]) {
         case 1:
            return this.matchesBlockByBlock(world.getBlockState(blockPos.up()).getBlock());
         case 2:
         case 3:
         case 4:
         case 5:
            return this.matchesBlockByBlock(world.getBlockState(blockPos.offset(enumFacing.getEnumFacing5())).getBlock());
         default:
            return false;
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(
         this,
         new IProperty[]{
            internalImmutableSetEntry5, internalImmutableSetEntry, internalImmutableSetEntry4, internalImmutableSetEntry2, internalImmutableSetEntry3
         }
      );
   }

   @Override
   public void applyWorld(World world, EntityPlayer player, BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
      if (!world.state && player.getHeldItem() != null && player.getHeldItem().getItem() == Items.shears) {
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray4[Block.getIdFromBlock(this)]);
         applyWorld(world, blockPos, new ItemStack(Blocks.block144, 1, 0));
      } else {
         super.applyWorld(world, player, blockPos, blockState, tileEntity);
      }
   }

   private boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      IBlockState blockState2 = blockState;

      for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable)(MovementFactorProperty.HORIZONTAL)) {
         InternalImmutableSetEntry internalImmutableSetEntry6 = resolveInternalImmutableSetEntry(enumFacing);
         if ((Boolean)blockState2.getComparable(internalImmutableSetEntry6) && !this.matchesBlockByBlock(world.getBlockState(blockPos.offset(enumFacing)).getBlock())) {
            IBlockState blockState3 = world.getBlockState(blockPos.up());
            if (blockState3.getBlock() != this || !(Boolean)blockState3.getComparable(internalImmutableSetEntry6)) {
               blockState2 = blockState2.resolveIBlockState(internalImmutableSetEntry6, Boolean.FALSE);
            }
         }
      }

      if (computeCount(blockState2) == 0) {
         return false;
      } else {
         if (blockState != blockState2) {
            world.matchesBlockPos(blockPos, blockState2, 2);
         }

         return true;
      }
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      byte byteValue = 0;
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
         byteValue |= 1;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry3)) {
         byteValue |= 2;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         byteValue |= 4;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry4)) {
         byteValue |= 8;
      }

      return byteValue;
   }

   public static int computeCount(IBlockState blockState) {
      int count = 0;

      for (InternalImmutableSetEntry internalImmutableSetEntry6 : internalImmutableSetEntryArray) {
         if ((Boolean)blockState.getComparable(internalImmutableSetEntry6)) {
            count++;
         }
      }

      return count;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockVine() {
      super(Material.material7);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(internalImmutableSetEntry5, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry4, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
      );
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
