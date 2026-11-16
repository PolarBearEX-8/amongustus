package net.minecraft.block;

import client.setting.BlockSolidType;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTripwire extends Block {
   public static final InternalImmutableSetEntry internalImmutableSetEntry;
   public static final InternalImmutableSetEntry internalImmutableSetEntry7;
   public static final InternalImmutableSetEntry internalImmutableSetEntry5;
   public static final InternalImmutableSetEntry internalImmutableSetEntry4;
   public static final InternalImmutableSetEntry internalImmutableSetEntry3;
   public static final InternalImmutableSetEntry internalImmutableSetEntry2;
   public static final InternalImmutableSetEntry internalImmutableSetEntry6;
   public static final InternalImmutableSetEntry internalImmutableSetEntry8;

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
      if (!world.state && player.getHeldItem() != null && player.getHeldItem().getItem() == Items.shears) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry4, Boolean.TRUE), 4);
      }
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state && (Boolean)world.getBlockState(blockPos).getComparable(internalImmutableSetEntry)) {
         this.applyWorld2(world, blockPos);
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.TRANSLUCENT;
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      boolean comparable = (Boolean)iBlockState.getComparable(internalImmutableSetEntry5);
      if (!(Boolean)iBlockState.getComparable(internalImmutableSetEntry7)) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.09375F, 1.0F);
      } else if (!comparable) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0625F, 0.0F, 1.0F, 0.15625F, 1.0F);
      }
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return blockState.resolveIBlockState(internalImmutableSetEntry3, matchesIBlockAccess(iBlockAccess, count, count2, count3, blockState, EnumFacing.NORTH))
         .resolveIBlockState(internalImmutableSetEntry2, matchesIBlockAccess(iBlockAccess, count, count2, count3, blockState, EnumFacing.EAST))
         .resolveIBlockState(internalImmutableSetEntry6, matchesIBlockAccess(iBlockAccess, count, count2, count3, blockState, EnumFacing.SOUTH))
         .resolveIBlockState(internalImmutableSetEntry8, matchesIBlockAccess(iBlockAccess, count, count2, count3, blockState, EnumFacing.WEST));
   }

   @Override
   public void processAction2(World world, BlockPos blockPos, IBlockState blockState, Random random) {
   }

   private void applyWorld4(World world, BlockPos blockPos, IBlockState blockState) {
      for (EnumFacing enumFacing : new EnumFacing[]{EnumFacing.SOUTH, EnumFacing.WEST}) {
         for (int index = 1; index < 42; index++) {
            BlockPos blockPos2 = blockPos.getBlockPos(enumFacing, index);
            IBlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.getBlock() == Blocks.blockTripwireHook) {
               if (blockState2.getComparable(BlockTripwireHook.autoWalkPath) == enumFacing.getEnumFacing5()) {
                  Blocks.blockTripwireHook.applyWorldByWorld(world, blockPos2, blockState2, false, true, index, blockState);
               }
               break;
            }

            if (blockState2.getBlock() != Blocks.block6) {
               break;
            }
         }
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(internalImmutableSetEntry, (count & 1) > 0)
         .resolveIBlockState(internalImmutableSetEntry7, (count & 2) > 0)
         .resolveIBlockState(internalImmutableSetEntry5, (count & 4) > 0)
         .resolveIBlockState(internalImmutableSetEntry4, (count & 8) > 0);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.string;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      this.applyWorld4(world, blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE));
   }

   public static boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, IBlockState blockState, EnumFacing enumFacing) {
      int count4 = count + enumFacing.getInt2();
      int count5 = count2 + enumFacing.getInt3();
      int count6 = count3 + enumFacing.getInt();
      IBlockState iBlockState = iBlockAccess.getIBlockState(count4, count5, count6);
      Block block = iBlockState.getBlock();
      if (block == Blocks.blockTripwireHook) {
         EnumFacing enumFacing2 = enumFacing.getEnumFacing5();
         return iBlockState.getComparable(BlockTripwireHook.autoWalkPath) == enumFacing2;
      } else if (block == Blocks.block6) {
         boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry7);
         boolean comparable2 = (Boolean)iBlockState.getComparable(internalImmutableSetEntry7);
         return comparable == comparable2;
      } else {
         return false;
      }
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      if (!world.state && !(Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         this.applyWorld2(world, blockPos);
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry7);
      boolean flag = !World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
      if (comparable != flag) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.string;
   }

   private void applyWorld2(World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      boolean flag = false;
      List list = world.resolveListByEntity(
         (Entity)null,
         new AxisAlignedBB(
            (double)blockPos.getX() + this.coordOffset,
            (double)blockPos.getY() + this.coordOffset11,
            (double)blockPos.getZ() + this.coordOffset2,
            (double)blockPos.getX() + this.coordOffset9,
            (double)blockPos.getY() + this.coordOffset6,
            (double)blockPos.getZ() + this.coordOffset15
         )
      );
      if (!list.isEmpty()) {
         Iterator iterator = list.iterator();

         while (iterator.hasNext()) {
            if (!((Entity)iterator.next()).isActive10()) {
               flag = true;
               break;
            }
         }
      }

      if (flag != comparable) {
         IBlockState blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry, flag);
         world.matchesBlockPos(blockPos, blockState2, 3);
         this.applyWorld4(world, blockPos, blockState2);
      }

      if (flag) {
         world.applyBlockPos(blockPos, this, this.processAction(world));
      }
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      IBlockState blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry7, !World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()));
      world.matchesBlockPos(blockPos, blockState2, 3);
      this.applyWorld4(world, blockPos, blockState2);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(
         this,
         new IProperty[]{
            internalImmutableSetEntry,
            internalImmutableSetEntry7,
            internalImmutableSetEntry5,
            internalImmutableSetEntry4,
            internalImmutableSetEntry3,
            internalImmutableSetEntry2,
            internalImmutableSetEntry8,
            internalImmutableSetEntry6
         }
      );
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      byte byteValue = 0;
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         byteValue |= 1;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry7)) {
         byteValue |= 2;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry5)) {
         byteValue |= 4;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry4)) {
         byteValue |= 8;
      }

      return byteValue;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public BlockTripwire() {
      super(Material.material22);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry7, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry5, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry4, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry6, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry8, Boolean.FALSE)
      );
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.15625F, 1.0F);
      this.processAction(true);
   }

   static {
      String[] textArray = new String[]{"north", "suspended", "powered", "disarmed", "east", "west", "south", "attached"};
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[2]);
      internalImmutableSetEntry7 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
      internalImmutableSetEntry5 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[7]);
      internalImmutableSetEntry4 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[3]);
      internalImmutableSetEntry3 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[0]);
      internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[4]);
      internalImmutableSetEntry6 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[6]);
      internalImmutableSetEntry8 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[5]);
   }
}
