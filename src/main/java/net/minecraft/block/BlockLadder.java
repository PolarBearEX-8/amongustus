package net.minecraft.block;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLadder extends Block {
   public static final PropertyDirection FACING;

   public boolean canBlockStay(World world, BlockPos pos, EnumFacing facing) {
      return world.getBlockState(pos.offset(facing.getOpposite())).getBlock().isNormalCube();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING});
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      return super.getCollisionBoundingBox(world, state, n, n2, n3);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      if (blockState2.getBlock() == this) {
         if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            float f = 0.125F;
            switch (((EnumFacing)blockState2.getValue(FACING))) {
               case NORTH:
                  this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                  break;
               case SOUTH:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                  break;
               case WEST:
                  this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case EAST:
               default:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            }
         } else {
            switch (((EnumFacing)blockState2.getValue(FACING))) {
               case NORTH:
                  this.setBlockBounds2(0.0, 0.0, 0.8125, 1.0, 1.0, 1.0);
                  break;
               case SOUTH:
                  this.setBlockBounds2(0.0, 0.0, 0.0, 1.0, 1.0, 0.1875);
                  break;
               case WEST:
                  this.setBlockBounds2(0.8125, 0.0, 0.0, 1.0, 1.0, 1.0);
                  break;
               case EAST:
               default:
                  this.setBlockBounds2(0.0, 0.0, 0.0, 0.1875, 1.0, 1.0);
            }
         }
      }
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      this.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
      return super.getSelectedBoundingBox(world, pos);
   }

   public BlockLadder() {
      super(Material.circuits);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      if (facing.getAxis().isHorizontal() && this.canBlockStay(world, pos, facing)) {
         return this.getDefaultState().withProperty(FACING, facing);
      } else {
         for (EnumFacing facing2 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            if (this.canBlockStay(world, pos, facing2)) {
               return this.getDefaultState().withProperty(FACING, facing2);
            }
         }

         return this.getDefaultState();
      }
   }

   static {
      FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      EnumFacing front = EnumFacing.getFront(n);
      if (front.getAxis() == EnumFacing.Axis.Y) {
         front = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, front);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!this.canBlockStay(world, pos, (EnumFacing)state.getValue(FACING))) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
      }

      super.onNeighborBlockChange(world, pos, state, block);
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos.west()).getBlock().isNormalCube()
         || world.getBlockState(pos.east()).getBlock().isNormalCube()
         || world.getBlockState(pos.north()).getBlock().isNormalCube()
         || world.getBlockState(pos.south()).getBlock().isNormalCube();
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex();
   }

}
