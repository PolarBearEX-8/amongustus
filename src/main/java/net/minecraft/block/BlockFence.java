package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemLead;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFence extends Block {
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return false;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      boolean flag = this.canConnectTo(world, n, n2, n3 - 1);
      boolean flag2 = this.canConnectTo(world, n, n2, n3 + 1);
      boolean flag3 = this.canConnectTo(world, n - 1, n2, n3);
      boolean flag4 = this.canConnectTo(world, n + 1, n2, n3);
      float f = 0.375F;
      float f2 = 0.625F;
      float f3 = 0.375F;
      float f4 = 0.625F;
      if (flag) {
         f3 = 0.0F;
      }

      if (flag2) {
         f4 = 1.0F;
      }

      if (flag3) {
         f = 0.0F;
      }

      if (flag4) {
         f2 = 1.0F;
      }

      this.setBlockBounds(f, 0.0F, f3, f2, 1.0F, f4);
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return world.isRemote ? true : ItemLead.hasLeashedEntity(player, world, pos);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{NORTH, EAST, WEST, SOUTH});
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return world.isRemote ? true : ItemLead.attachToFence(player, world, pos);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return 0;
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      return state.withProperty(NORTH, this.canConnectTo(world, n, n2, n3 - 1))
         .withProperty(EAST, this.canConnectTo(world, n + 1, n2, n3))
         .withProperty(SOUTH, this.canConnectTo(world, n, n2, n3 + 1))
         .withProperty(WEST, this.canConnectTo(world, n - 1, n2, n3));
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      boolean flag = this.canConnectTo(world, pos.getX(), pos.getY(), pos.getZ() - 1);
      boolean flag2 = this.canConnectTo(world, pos.getX(), pos.getY(), pos.getZ() + 1);
      boolean flag3 = this.canConnectTo(world, pos.getX() - 1, pos.getY(), pos.getZ());
      boolean flag4 = this.canConnectTo(world, pos.getX() + 1, pos.getY(), pos.getZ());
      float f = 0.375F;
      float f2 = 0.625F;
      float f3 = 0.375F;
      float f4 = 0.625F;
      if (flag) {
         f3 = 0.0F;
      }

      if (flag2) {
         f4 = 1.0F;
      }

      if (flag || flag2) {
         this.setBlockBounds(f, 0.0F, f3, f2, 1.5F, f4);
         super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      }

      f3 = 0.375F;
      f4 = 0.625F;
      if (flag3) {
         f = 0.0F;
      }

      if (flag4) {
         f2 = 1.0F;
      }

      if (flag3 || flag4 || !flag && !flag2) {
         this.setBlockBounds(f, 0.0F, f3, f2, 1.5F, f4);
         super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      }

      if (flag) {
         f3 = 0.0F;
      }

      if (flag2) {
         f4 = 1.0F;
      }

      this.setBlockBounds(f, 0.0F, f3, f2, 1.0F, f4);
   }

   @Override
   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return false;
   }

   public BlockFence(Material material, MapColor mapColor) {
      super(material, mapColor);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(NORTH, Boolean.FALSE)
            .withProperty(EAST, Boolean.FALSE)
            .withProperty(SOUTH, Boolean.FALSE)
            .withProperty(WEST, Boolean.FALSE)
      );
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public BlockFence(Material material) {
      this(material, material.getMaterialMapColor());
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return true;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   public boolean canConnectTo(IBlockAccess world, int n, int n2, int n3) {
      Block block = world.getBlockState2(n, n2, n3).getBlock();
      return block == Blocks.barrier
         ? false
         : (
            (!(block instanceof BlockFence) || block.blockMaterial != this.blockMaterial) && !(block instanceof BlockFenceGate)
               ? (block.blockMaterial.isOpaque() && block.isFullCube() ? block.blockMaterial != Material.gourd : false)
               : true
         );
   }
}
