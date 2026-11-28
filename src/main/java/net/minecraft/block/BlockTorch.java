package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockTorch extends Block {
   public static final PropertyDirection FACING;

   @Override
   public IBlockState getStateFromMeta(int n) {
      IBlockState defaultState = this.getDefaultState();
      switch (n) {
         case 1:
            defaultState = defaultState.withProperty(FACING, EnumFacing.EAST);
            break;
         case 2:
            defaultState = defaultState.withProperty(FACING, EnumFacing.WEST);
            break;
         case 3:
            defaultState = defaultState.withProperty(FACING, EnumFacing.SOUTH);
            break;
         case 4:
            defaultState = defaultState.withProperty(FACING, EnumFacing.NORTH);
            break;
         case 5:
         default:
            defaultState = defaultState.withProperty(FACING, EnumFacing.UP);
      }

      return defaultState;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      switch (((EnumFacing)state.getValue(FACING))) {
         case EAST:
            n |= 1;
            break;
         case WEST:
            n |= 2;
            break;
         case SOUTH:
            n |= 3;
            break;
         case NORTH:
            n |= 4;
            break;
         case DOWN:
         case UP:
         default:
            n |= 5;
      }

      return n;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      if (this.canPlaceAt(world, pos, facing)) {
         return this.getDefaultState().withProperty(FACING, facing);
      } else {
         for (EnumFacing facing2 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            if (world.isBlockNormalCube(pos.offset(facing2.getOpposite()), true)) {
               return this.getDefaultState().withProperty(FACING, facing2);
            }
         }

         return this.getDefaultState();
      }
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      for (EnumFacing facing : (Iterable<EnumFacing>)(FACING.getAllowedValues())) {
         if (this.canPlaceAt(world, pos, facing)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      this.checkForDrop(world, pos, state);
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   private boolean canPlaceAt(World world, BlockPos pos, EnumFacing facing) {
      BlockPos pos2 = pos.offset(facing.getOpposite());
      return facing.getAxis().isHorizontal() && world.isBlockNormalCube(pos2, true) || facing.equals(EnumFacing.UP) && this.canPlaceOn(world, pos2);
   }

   static {
      FACING = PropertyDirection.create("facing", new BlockTorch.FilterPredicate());
   }

   public boolean checkForDrop(World world, BlockPos pos, IBlockState state) {
      if (state.getBlock() == this && this.canPlaceAt(world, pos, (EnumFacing)state.getValue(FACING))) {
         return true;
      } else {
         if (world.getBlockState(pos).getBlock() == this) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
         }

         return false;
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      this.onNeighborChangeInternal(world, pos, state);
   }

   private boolean canPlaceOn(World world, BlockPos pos) {
      if (World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY(), pos.getZ())) {
         return true;
      } else {
         Block block = world.getBlockState(pos).getBlock();
         return block instanceof BlockFence || block == Blocks.glass || block == Blocks.cobblestone_wall || block == Blocks.stained_glass;
      }
   }

   public BlockTorch() {
      super(Material.circuits);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING});
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      double d = (double)pos.getX() + 0.5;
      double d2 = (double)pos.getY() + 0.7;
      double d3 = (double)pos.getZ() + 0.5;
      double d4 = 0.22;
      double d5 = 0.27;
      if (value.getAxis().isHorizontal()) {
         EnumFacing opposite = value.getOpposite();
         world.spawnParticle(
            EnumParticleTypes.SMOKE_NORMAL,
            d + d5 * (double)opposite.getFrontOffsetX(),
            d2 + d4,
            d3 + d5 * (double)opposite.getFrontOffsetZ(),
            0.0,
            0.0,
            0.0,
            new int[0]
         );
         world.spawnParticle(
            EnumParticleTypes.FLAME,
            d + d5 * (double)opposite.getFrontOffsetX(),
            d2 + d4,
            d3 + d5 * (double)opposite.getFrontOffsetZ(),
            0.0,
            0.0,
            0.0,
            new int[0]
         );
      } else {
         world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
         world.spawnParticle(EnumParticleTypes.FLAME, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
      }
   }

   @Override
   public MovingObjectPosition collisionRayTrace(World world, int n, int n2, int n3, double d, double d2, double d3, double d4, double d5, double d6) {
      EnumFacing value = (EnumFacing)world.getBlockState2(n, n2, n3).getValue(FACING);
      float f = 0.15F;
      if (value == EnumFacing.EAST) {
         this.setBlockBounds(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
      } else if (value == EnumFacing.WEST) {
         this.setBlockBounds(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
      } else if (value == EnumFacing.SOUTH) {
         this.setBlockBounds(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
      } else if (value == EnumFacing.NORTH) {
         this.setBlockBounds(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
      } else {
         f = 0.1F;
         this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
      }

      return super.collisionRayTrace(world, n, n2, n3, d, d2, d3, d4, d5, d6);
   }

   public boolean onNeighborChangeInternal(World world, BlockPos pos, IBlockState state) {
      if (!this.checkForDrop(world, pos, state)) {
         return true;
      } else {
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         EnumFacing.Axis axis = value.getAxis();
         EnumFacing opposite = value.getOpposite();
         boolean flag = false;
         if (axis.isHorizontal() && !world.isBlockNormalCube(pos.offset(opposite), true)) {
            flag = true;
         } else if (axis.isVertical() && !this.canPlaceOn(world, pos.offset(opposite))) {
            flag = true;
         }

         if (flag) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
            return true;
         } else {
            return false;
         }
      }
   }

public static final class FilterPredicate implements Predicate {
      public boolean apply(EnumFacing facing) {
         return facing != EnumFacing.DOWN;
      }

      public boolean apply(Object obj) {
         return this.apply((EnumFacing)obj);
      }
   }

}
