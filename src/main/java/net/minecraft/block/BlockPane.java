package net.minecraft.block;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import java.util.Random;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPane extends Block {
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");
   public static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[]{
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
   private final boolean canDrop;

   @Override
   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         IBlockState blockState2 = world.getBlockState2(n, n2, n3);
         blockState2 = this.getActualState(blockState2, world, n, n2, n3);
         this.setBlockBounds(AABB_BY_INDEX[getBoundingBoxIndex2(blockState2)]);
      } else {
         float f = 0.4375F;
         float f2 = 0.5625F;
         float f3 = 0.4375F;
         float f4 = 0.5625F;
         boolean flag = this.canPaneConnectToBlock(world.getBlockState2(n, n2, n3 - 1).getBlock());
         boolean flag2 = this.canPaneConnectToBlock(world.getBlockState2(n, n2, n3 + 1).getBlock());
         boolean flag3 = this.canPaneConnectToBlock(world.getBlockState2(n - 1, n2, n3).getBlock());
         boolean flag4 = this.canPaneConnectToBlock(world.getBlockState2(n + 1, n2, n3).getBlock());
         if ((!flag3 || !flag4) && (flag3 || flag4 || flag || flag2)) {
            if (flag3) {
               f = 0.0F;
            } else if (flag4) {
               f2 = 1.0F;
            }
         } else {
            f = 0.0F;
            f2 = 1.0F;
         }

         if ((!flag || !flag2) && (flag3 || flag4 || flag || flag2)) {
            if (flag) {
               f3 = 0.0F;
            } else if (flag2) {
               f4 = 1.0F;
            }
         } else {
            f3 = 0.0F;
            f4 = 1.0F;
         }

         this.setBlockBounds(f, 0.0F, f3, f2, 1.0F, f4);
      }
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return world.getBlockState2(n, n2, n3).getBlock() != this && super.shouldSideBeRendered(world, n, n2, n3, facing);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return !this.canDrop ? null : super.getItemDropped(state, rand, n);
   }

   public final boolean canPaneConnectToBlock(Block block) {
      return block.isFullBlock()
         || block == this
         || block == Blocks.glass
         || block == Blocks.stained_glass
         || block == Blocks.stained_glass_pane
         || block instanceof BlockPane;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{NORTH, EAST, WEST, SOUTH});
   }

   private static int getBoundingBoxIndex2(IBlockState state) {
      int n = 0;
      if ((Boolean)state.getValue(NORTH)) {
         n |= getBoundingBoxIndex(EnumFacing.NORTH);
      }

      if ((Boolean)state.getValue(EAST)) {
         n |= getBoundingBoxIndex(EnumFacing.EAST);
      }

      if ((Boolean)state.getValue(SOUTH)) {
         n |= getBoundingBoxIndex(EnumFacing.SOUTH);
      }

      if ((Boolean)state.getValue(WEST)) {
         n |= getBoundingBoxIndex(EnumFacing.WEST);
      }

      return n;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return 0;
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         state = this.getActualState(state, world, pos.getX(), pos.getY(), pos.getZ());
         this.setBlockBounds(AABB_BY_INDEX[0]);
         super.addCollisionBoxesToList(world, pos, state, box, list, entity);
         if ((Boolean)state.getValue(NORTH)) {
            this.setBlockBounds(AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.NORTH)]);
            super.addCollisionBoxesToList(world, pos, state, box, list, entity);
         }

         if ((Boolean)state.getValue(SOUTH)) {
            this.setBlockBounds(AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.SOUTH)]);
            super.addCollisionBoxesToList(world, pos, state, box, list, entity);
         }

         if ((Boolean)state.getValue(EAST)) {
            this.setBlockBounds(AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.EAST)]);
            super.addCollisionBoxesToList(world, pos, state, box, list, entity);
         }

         if ((Boolean)state.getValue(WEST)) {
            this.setBlockBounds(AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.WEST)]);
            super.addCollisionBoxesToList(world, pos, state, box, list, entity);
         }
      } else {
         boolean flag = this.canPaneConnectToBlock(world.getBlockState(pos.north()).getBlock());
         boolean flag2 = this.canPaneConnectToBlock(world.getBlockState(pos.south()).getBlock());
         boolean flag3 = this.canPaneConnectToBlock(world.getBlockState(pos.west()).getBlock());
         boolean flag4 = this.canPaneConnectToBlock(world.getBlockState(pos.east()).getBlock());
         if ((!flag3 || !flag4) && (flag3 || flag4 || flag || flag2)) {
            if (flag3) {
               this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
               super.addCollisionBoxesToList(world, pos, state, box, list, entity);
            } else if (flag4) {
               this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
               super.addCollisionBoxesToList(world, pos, state, box, list, entity);
            }
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(world, pos, state, box, list, entity);
         }

         if ((!flag || !flag2) && (flag3 || flag4 || flag || flag2)) {
            if (flag) {
               this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
               super.addCollisionBoxesToList(world, pos, state, box, list, entity);
            } else if (flag2) {
               this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
               super.addCollisionBoxesToList(world, pos, state, box, list, entity);
            }
         } else {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(world, pos, state, box, list, entity);
         }
      }
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         state = this.getActualState(state, world, n, n2, n3);
         AxisAlignedBB box = AABB_BY_INDEX[getBoundingBoxIndex2(state)];
         return new AxisAlignedBB(
            (double)n + box.minX,
            (double)n2 + box.minY,
            (double)n3 + box.minZ,
            (double)n + box.maxX,
            (double)n2 + box.maxY,
            (double)n3 + box.maxZ
         );
      } else {
         return super.getCollisionBoundingBox(world, state, n, n2, n3);
      }
   }

   @Override
   public boolean canSilkHarvest() {
      return true;
   }

   private static int getBoundingBoxIndex(EnumFacing facing) {
      return 1 << facing.getHorizontalIndex();
   }

   public BlockPane(Material material, boolean flag) {
      super(material);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(NORTH, Boolean.FALSE)
            .withProperty(EAST, Boolean.FALSE)
            .withProperty(SOUTH, Boolean.FALSE)
            .withProperty(WEST, Boolean.FALSE)
      );
      this.canDrop = flag;
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      return state.withProperty(NORTH, this.canPaneConnectToBlock(world.getBlockState2(n, n2, n3 - 1).getBlock()))
         .withProperty(SOUTH, this.canPaneConnectToBlock(world.getBlockState2(n, n2, n3 + 1).getBlock()))
         .withProperty(WEST, this.canPaneConnectToBlock(world.getBlockState2(n - 1, n2, n3).getBlock()))
         .withProperty(EAST, this.canPaneConnectToBlock(world.getBlockState2(n + 1, n2, n3).getBlock()));
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }
}
