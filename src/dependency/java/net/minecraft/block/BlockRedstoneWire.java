package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BlockSolidType;
import client.setting.HandlerOptionType;
import client.setting.MovementFactorProperty;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.VectorRanker;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneWire extends Block {
   public static final ImmutableSetEntry immutableSetEntry3;
   public static final ImmutableSetEntry immutableSetEntry2;
   public static final ImmutableSetEntry immutableSetEntry4;
   public static final ImmutableSetEntry immutableSetEntry;
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry;
   public final Set set = Sets.newHashSet();
   public boolean enabled = true;

   private int computeCountByWorld(World world, BlockPos blockPos, int count) {
      if (world.getBlockState(blockPos).getBlock() != this) {
         return count;
      } else {
         int comparable = (Integer)world.getBlockState(blockPos).getComparable(auxiliaryImmutableSetEntry);
         return comparable > count ? comparable : count;
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState blockState2 = blockState.resolveIBlockState(immutableSetEntry, this.resolveHandlerOptionType(iBlockAccess, count, count2, count3, EnumFacing.WEST));
      blockState2 = blockState2.resolveIBlockState(immutableSetEntry2, this.resolveHandlerOptionType(iBlockAccess, count, count2, count3, EnumFacing.EAST));
      blockState2 = blockState2.resolveIBlockState(immutableSetEntry3, this.resolveHandlerOptionType(iBlockAccess, count, count2, count3, EnumFacing.NORTH));
      return blockState2.resolveIBlockState(immutableSetEntry4, this.resolveHandlerOptionType(iBlockAccess, count, count2, count3, EnumFacing.SOUTH));
   }

   public static boolean matchesIBlockState(IBlockState blockState) {
      return matchesIBlockState(blockState, null);
   }

   @Override
   public boolean processAction7() {
      return this.enabled;
   }

   private int computeCount(int count) {
      float factor = (float)count / 15.0F;
      float factor2 = factor * 0.6F + 0.4F;
      if (count == 0) {
         factor2 = 0.3F;
      }

      float factor3 = factor * factor * 0.7F - 0.5F;
      float factor4 = factor * factor * 0.6F - 0.7F;
      if (factor3 < 0.0F) {
         factor3 = 0.0F;
      }

      if (factor4 < 0.0F) {
         factor4 = 0.0F;
      }

      int intItem = MathHelper.getInt12((int)(factor2 * 255.0F), 0, 255);
      int intItem2 = MathHelper.getInt12((int)(factor3 * 255.0F), 0, 255);
      int intItem3 = MathHelper.getInt12((int)(factor4 * 255.0F), 0, 255);
      return 0xFF000000 | intItem << 16 | intItem2 << 8 | intItem3;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   private IBlockState resolveIBlockStateByWorld(World world, BlockPos blockPos, BlockPos blockPos2, IBlockState blockState) {
      IBlockState blockState2 = blockState;
      int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
      int count = this.computeCountByWorld(world, blockPos2, 0);
      this.enabled = false;
      int count2 = world.computeCount(blockPos);
      this.enabled = true;
      if (count2 > 0 && count2 > count - 1) {
         count = count2;
      }

      int count3 = 0;

      for (EnumFacing enumFacing : (Iterable<EnumFacing>) (Iterable) MovementFactorProperty.HORIZONTAL) {
         BlockPos blockPos3 = blockPos.offset(enumFacing);
         boolean flag = blockPos3.getX() != blockPos2.getX() || blockPos3.getZ() != blockPos2.getZ();
         if (flag) {
            count3 = this.computeCountByWorld(world, blockPos3, count3);
         }

         if (world.getBlockState(blockPos3).getBlock().isActive2() && !world.getBlockState(blockPos.up()).getBlock().isActive2()) {
            if (flag && blockPos.getY() >= blockPos2.getY()) {
               count3 = this.computeCountByWorld(world, blockPos3.up(), count3);
            }
         } else if (!world.getBlockState(blockPos3).getBlock().isActive2() && flag && blockPos.getY() <= blockPos2.getY()) {
            count3 = this.computeCountByWorld(world, blockPos3.down(), count3);
         }
      }

      if (count3 > count) {
         count = count3 - 1;
      } else if (count > 0) {
         count--;
      } else {
         count = 0;
      }

      if (count2 > count - 1) {
         count = count2;
      }

      if (comparable != count) {
         blockState2 = blockState.resolveIBlockState(auxiliaryImmutableSetEntry, count);
         if (world.getBlockState(blockPos) == blockState) {
            world.matchesBlockPos(blockPos, blockState2, 2);
         }

         this.set.add(blockPos);

         for (EnumFacing enumFacing2 : EnumFacing.getEntries()) {
            this.set.add(blockPos.offset(enumFacing2));
         }
      }

      return blockState2;
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.redstone;
   }

   public static boolean matchesIBlockState(IBlockState blockState, EnumFacing enumFacing) {
      Block block = blockState.getBlock();
      if (block == Blocks.blockRedstoneWire) {
         return true;
      } else if (Blocks.blockPoweredRepeater.matchesBlock2(block)) {
         EnumFacing comparable = (EnumFacing)blockState.getComparable(BlockPoweredRepeater.autoWalkPath);
         return comparable == enumFacing || comparable.getEnumFacing5() == enumFacing;
      } else {
         return block.processAction7() && enumFacing != null;
      }
   }

   private void applyWorld2(World world, BlockPos blockPos) {
      if (world.getBlockState(blockPos).getBlock() == this) {
         world.applyBlockPosByBlockPos(blockPos, this);

         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            world.applyBlockPosByBlockPos(blockPos.offset(enumFacing), this);
         }
      }
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      super.onBlockAdded(world, blockPos, blockState);
      if (!world.state) {
         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            world.applyBlockPosByBlockPos(blockPos.offset(enumFacing), this);
         }

         this.resolveIBlockStateByWorld(world, blockPos, blockState);

         for (EnumFacing enumFacing2 : (Iterable<EnumFacing>) (Iterable) MovementFactorProperty.HORIZONTAL) {
            this.applyWorld2(world, blockPos.offset(enumFacing2));
         }

         for (EnumFacing enumFacing3 : (Iterable<EnumFacing>) (Iterable) MovementFactorProperty.HORIZONTAL) {
            BlockPos blockPos2 = blockPos.offset(enumFacing3);
            if (world.getBlockState(blockPos2).getBlock().isActive2()) {
               this.applyWorld2(world, blockPos2.up());
            } else {
               this.applyWorld2(world, blockPos2.down());
            }
         }
      }
   }

   private boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing enumFacing) {
      BlockPos blockPos2 = blockPos.offset(enumFacing);
      IBlockState blockState = iBlockAccess.getBlockState(blockPos2);
      Block block = blockState.getBlock();
      boolean active = block.isActive2();
      return !iBlockAccess.getBlockState(blockPos.up()).getBlock().isActive2() && active && matchesIBlockAccessByIBlockAccess(iBlockAccess, blockPos2.up())
         || matchesIBlockState(blockState, enumFacing)
         || block == Blocks.blockPoweredRepeater2 && blockState.getComparable(VectorRanker.autoWalkPath) == enumFacing
         || !active && matchesIBlockAccessByIBlockAccess(iBlockAccess, blockPos2.down());
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      if (!this.enabled) {
         return 0;
      } else {
         int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
         if (comparable == 0) {
            return 0;
         } else if (enumFacing == EnumFacing.UP) {
            return comparable;
         } else {
            EnumSet enumSet = EnumSet.noneOf(EnumFacing.class);

            for (EnumFacing enumFacing2 : (Iterable<EnumFacing>) (Iterable) MovementFactorProperty.HORIZONTAL) {
               if (this.matchesIBlockAccess(iBlockAccess, blockPos, enumFacing2)) {
                  enumSet.add(enumFacing2);
               }
            }

            if (enumFacing.getEnumFacingXType().isActiveAsFlag() && enumSet.isEmpty()) {
               return comparable;
            } else {
               return enumSet.contains(enumFacing) && !enumSet.contains(enumFacing.getEnumFacing2()) && !enumSet.contains(enumFacing.getEnumFacingAsEnumFacing()) ? comparable : 0;
            }
         }
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.redstone;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.state) {
         if (this.processAction7(world, blockPos)) {
            this.resolveIBlockStateByWorld(world, blockPos, blockState);
         } else {
            this.processAction(world, blockPos, blockState, 0);
            world.matchesBlockPos8(blockPos);
         }
      }
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      IBlockState blockState = iBlockAccess.getBlockState(blockPos);
      return blockState.getBlock() != this ? super.processAction(iBlockAccess, blockPos, count) : this.computeCount((Integer)blockState.getComparable(auxiliaryImmutableSetEntry));
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()) || world.getBlockState(blockPos.down()).getBlock() == Blocks.block77;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         this.resolveIBlockStateByWorld(world, blockPos, blockState);

         for (EnumFacing enumFacing : (Iterable<EnumFacing>) (Iterable) MovementFactorProperty.VERTICAL) {
            world.applyBlockPosByBlockPos(blockPos.offset(enumFacing), this);
         }

         for (EnumFacing enumFacing2 : (Iterable<EnumFacing>) (Iterable) MovementFactorProperty.HORIZONTAL) {
            this.applyWorld2(world, blockPos.offset(enumFacing2));
         }

         for (EnumFacing enumFacing3 : (Iterable<EnumFacing>) (Iterable) MovementFactorProperty.HORIZONTAL) {
            BlockPos blockPos2 = blockPos.offset(enumFacing3);
            if (world.getBlockState(blockPos2).getBlock().isActive2()) {
               this.applyWorld2(world, blockPos2.up());
            } else {
               this.applyWorld2(world, blockPos2.down());
            }
         }
      }
   }

   private IBlockState resolveIBlockStateByWorld(World world, BlockPos blockPos, IBlockState blockState) {
      IBlockState blockState2 = this.resolveIBlockStateByWorld(world, blockPos, blockPos, blockState);
      ArrayList list = Lists.newArrayList(this.set);
      this.set.clear();

      for (BlockPos blockPos2 : (Iterable<BlockPos>) (Iterable) list) {
         world.applyBlockPosByBlockPos(blockPos2, this);
      }

      return blockState2;
   }

   public static boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return matchesIBlockState(iBlockAccess.getBlockState(blockPos));
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry3, immutableSetEntry2, immutableSetEntry4, immutableSetEntry, auxiliaryImmutableSetEntry});
   }

   @Override
   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return !this.enabled ? 0 : this.processAction(iBlockAccess, blockPos, blockState, enumFacing);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
      if (comparable != 0) {
         double offset = (double)blockPos.getX() + 0.5 + ((double)random.nextFloat() - 0.5) * 0.2;
         double offset2 = (double)((float)blockPos.getY() + 0.0625F);
         double offset3 = (double)blockPos.getZ() + 0.5 + ((double)random.nextFloat() - 0.5) * 0.2;
         float factor = (float)comparable / 15.0F;
         float factor2 = factor * 0.6F + 0.4F;
         float factor3 = Math.max(0.0F, factor * factor * 0.7F - 0.5F);
         float factor4 = Math.max(0.0F, factor * factor * 0.6F - 0.7F);
         world.applyAttackEffectsExplosionNorm(
            AttackEffectsExplosionNormalType.REDSTONE, offset, offset2, offset3, (double)factor2, (double)factor3, (double)factor4, new int[0]
         );
      }
   }

   private HandlerOptionType resolveHandlerOptionType(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      int count4 = count + enumFacing.getInt2();
      int count5 = count2 + enumFacing.getInt3();
      int count6 = count3 + enumFacing.getInt();
      Block block = iBlockAccess.getIBlockState(count4, count5, count6).getBlock();
      if (!matchesIBlockState(iBlockAccess.getIBlockState(count4, count5, count6), enumFacing)
         && (block.isActive4() || !matchesIBlockState(iBlockAccess.getIBlockState(count4, count5 - 1, count6)))) {
         return !iBlockAccess.getIBlockState(count, count2 + 1, count3).getBlock().isActive4()
               && block.isActive4()
               && matchesIBlockState(iBlockAccess.getIBlockState(count4, count5 + 1, count6))
            ? HandlerOptionType.UP
            : HandlerOptionType.NONE;
      } else {
         return HandlerOptionType.SIDE;
      }
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

   public BlockRedstoneWire() {
      super(Material.material22);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(immutableSetEntry3, HandlerOptionType.NONE)
            .resolveIBlockState(immutableSetEntry2, HandlerOptionType.NONE)
            .resolveIBlockState(immutableSetEntry4, HandlerOptionType.NONE)
            .resolveIBlockState(immutableSetEntry, HandlerOptionType.NONE)
            .resolveIBlockState(auxiliaryImmutableSetEntry, 0)
      );
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
   }

   static {
      String[] textArray = new String[]{"west", "power", "south", "east", "north"};
      immutableSetEntry3 = ImmutableSetEntry.resolveImmutableSetEntry(textArray[4], HandlerOptionType.class);
      immutableSetEntry2 = ImmutableSetEntry.resolveImmutableSetEntry(textArray[3], HandlerOptionType.class);
      immutableSetEntry4 = ImmutableSetEntry.resolveImmutableSetEntry(textArray[2], HandlerOptionType.class);
      immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry(textArray[0], HandlerOptionType.class);
      auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry(textArray[1], 0, 15);
   }
}
