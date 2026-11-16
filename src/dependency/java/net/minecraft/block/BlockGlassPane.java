package net.minecraft.block;

import client.setting.BlockSolidType;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlassPane extends Block {
   public static final InternalImmutableSetEntry internalImmutableSetEntry;
   public static final InternalImmutableSetEntry internalImmutableSetEntry2;
   public static final InternalImmutableSetEntry internalImmutableSetEntry4;
   public static final InternalImmutableSetEntry internalImmutableSetEntry3;
   public static final AxisAlignedBB[] axisAlignedBBArray = new AxisAlignedBB[]{
      new AxisAlignedBB(0.4375, 0.0, 0.4375, 0.5625, 1.0, 0.5625),
      new AxisAlignedBB(0.4375, 0.0, 0.4375, 0.5625, 1.0, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.4375, 0.5625, 1.0, 0.5625),
      new AxisAlignedBB(0.0, 0.0, 0.4375, 0.5625, 1.0, 1.0),
      new AxisAlignedBB(0.4375, 0.0, 0.0, 0.5625, 1.0, 0.5625),
      new AxisAlignedBB(0.4375, 0.0, 0.0, 0.5625, 1.0, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 0.5625, 1.0, 0.5625),
      new AxisAlignedBB(0.0, 0.0, 0.0, 0.5625, 1.0, 1.0),
      new AxisAlignedBB(0.4375, 0.0, 0.4375, 1.0, 1.0, 0.5625),
      new AxisAlignedBB(0.4375, 0.0, 0.4375, 1.0, 1.0, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.4375, 1.0, 1.0, 0.5625),
      new AxisAlignedBB(0.0, 0.0, 0.4375, 1.0, 1.0, 1.0),
      new AxisAlignedBB(0.4375, 0.0, 0.0, 1.0, 1.0, 0.5625),
      new AxisAlignedBB(0.4375, 0.0, 0.0, 1.0, 1.0, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.5625),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)
   };
   public final boolean enabled;

   @Override
   public void processAction2() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT_MIPPED;
   }

   public final boolean matchesBlockByBlock(Block block) {
      return block.isState18()
         || block == this
         || block == Blocks.block68
         || block == Blocks.blockStainedGlass
         || block == Blocks.blockStainedGlassPane
         || block instanceof BlockGlassPane;
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
         IBlockState blockState = this.processAction(iBlockState, iBlockAccess, count, count2, count3);
         this.processAction(axisAlignedBBArray[computeCount(blockState)]);
      } else {
         float factor = 0.4375F;
         float factor2 = 0.5625F;
         float factor3 = 0.4375F;
         float factor4 = 0.5625F;
         boolean flag = this.matchesBlockByBlock(iBlockAccess.getIBlockState(count, count2, count3 - 1).getBlock());
         boolean flag2 = this.matchesBlockByBlock(iBlockAccess.getIBlockState(count, count2, count3 + 1).getBlock());
         boolean flag3 = this.matchesBlockByBlock(iBlockAccess.getIBlockState(count - 1, count2, count3).getBlock());
         boolean flag4 = this.matchesBlockByBlock(iBlockAccess.getIBlockState(count + 1, count2, count3).getBlock());
         if ((!flag3 || !flag4) && (flag3 || flag4 || flag || flag2)) {
            if (flag3) {
               factor = 0.0F;
            } else if (flag4) {
               factor2 = 1.0F;
            }
         } else {
            factor = 0.0F;
            factor2 = 1.0F;
         }

         if ((!flag || !flag2) && (flag3 || flag4 || flag || flag2)) {
            if (flag) {
               factor3 = 0.0F;
            } else if (flag2) {
               factor4 = 1.0F;
            }
         } else {
            factor3 = 0.0F;
            factor4 = 1.0F;
         }

         this.setBlockBounds(factor, 0.0F, factor3, factor2, 1.0F, factor4);
      }
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return blockState.resolveIBlockState(internalImmutableSetEntry, this.matchesBlockByBlock(iBlockAccess.getIBlockState(count, count2, count3 - 1).getBlock()))
         .resolveIBlockState(internalImmutableSetEntry4, this.matchesBlockByBlock(iBlockAccess.getIBlockState(count, count2, count3 + 1).getBlock()))
         .resolveIBlockState(internalImmutableSetEntry3, this.matchesBlockByBlock(iBlockAccess.getIBlockState(count - 1, count2, count3).getBlock()))
         .resolveIBlockState(internalImmutableSetEntry2, this.matchesBlockByBlock(iBlockAccess.getIBlockState(count + 1, count2, count3).getBlock()));
   }

   private static int computeCount(EnumFacing enumFacing) {
      return 1 << enumFacing.getIndexOffset();
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         IBlockState blockState2 = this.processAction(blockState, world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
         this.processAction(axisAlignedBBArray[0]);
         super.applyWorld(world, blockPos, blockState2, boundingBox, list, entity);
         if ((Boolean)blockState2.getComparable(internalImmutableSetEntry)) {
            this.processAction(axisAlignedBBArray[computeCount(EnumFacing.NORTH)]);
            super.applyWorld(world, blockPos, blockState2, boundingBox, list, entity);
         }

         if ((Boolean)blockState2.getComparable(internalImmutableSetEntry4)) {
            this.processAction(axisAlignedBBArray[computeCount(EnumFacing.SOUTH)]);
            super.applyWorld(world, blockPos, blockState2, boundingBox, list, entity);
         }

         if ((Boolean)blockState2.getComparable(internalImmutableSetEntry2)) {
            this.processAction(axisAlignedBBArray[computeCount(EnumFacing.EAST)]);
            super.applyWorld(world, blockPos, blockState2, boundingBox, list, entity);
         }

         if ((Boolean)blockState2.getComparable(internalImmutableSetEntry3)) {
            this.processAction(axisAlignedBBArray[computeCount(EnumFacing.WEST)]);
            super.applyWorld(world, blockPos, blockState2, boundingBox, list, entity);
         }
      } else {
         boolean flag = this.matchesBlockByBlock(world.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock());
         boolean flag2 = this.matchesBlockByBlock(world.getBlockState(blockPos.getBlockPos2()).getBlock());
         boolean flag3 = this.matchesBlockByBlock(world.getBlockState(blockPos.getBlockPos()).getBlock());
         boolean flag4 = this.matchesBlockByBlock(world.getBlockState(blockPos.getBlockPos3()).getBlock());
         if ((!flag3 || !flag4) && (flag3 || flag4 || flag || flag2)) {
            if (flag3) {
               this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
               super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
            } else if (flag4) {
               this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
               super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
            }
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
         }

         if ((!flag || !flag2) && (flag3 || flag4 || flag || flag2)) {
            if (flag) {
               this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
               super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
            } else if (flag2) {
               this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
               super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
            }
         } else {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
         }
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return !this.enabled ? null : super.processAction(blockState, random, count);
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return iBlockAccess.getIBlockState(count, count2, count3).getBlock() != this && super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(
         this, new IProperty[]{internalImmutableSetEntry, internalImmutableSetEntry2, internalImmutableSetEntry3, internalImmutableSetEntry4}
      );
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         IBlockState blockState2 = this.processAction(blockState, world, count, count2, count3);
         AxisAlignedBB boundingBox = axisAlignedBBArray[computeCount(blockState2)];
         return new AxisAlignedBB(
            (double)count + boundingBox.minX,
            (double)count2 + boundingBox.minY,
            (double)count3 + boundingBox.minZ,
            (double)count + boundingBox.maxX,
            (double)count2 + boundingBox.maxY,
            (double)count3 + boundingBox.maxZ
         );
      } else {
         return super.processAction(world, blockState, count, count2, count3);
      }
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return 0;
   }

   private static int computeCount(IBlockState blockState) {
      int count = 0;
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= computeCount(EnumFacing.NORTH);
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
         count |= computeCount(EnumFacing.EAST);
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry4)) {
         count |= computeCount(EnumFacing.SOUTH);
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry3)) {
         count |= computeCount(EnumFacing.WEST);
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

   public BlockGlassPane(Material material, boolean flag) {
      super(material);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry4, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
      );
      this.enabled = flag;
      this.processAction(ItemGroupManager.itemGroupManager8);
   }

   static {
      String[] textArray = new String[]{"south", "north", "west", "east"};
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
      internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[3]);
      internalImmutableSetEntry4 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[0]);
      internalImmutableSetEntry3 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[2]);
   }
}
