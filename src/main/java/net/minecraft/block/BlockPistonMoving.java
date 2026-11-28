package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonMoving extends BlockContainer {
   public static final PropertyDirection FACING = BlockPistonExtension.FACING;
   public static final PropertyEnum TYPE = BlockPistonExtension.TYPE;

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return false;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return null;
   }

   private TileEntityPiston getTileEntity(IBlockAccess world, BlockPos pos) {
      TileEntity tileEntity = world.getTileEntity(pos);
      return tileEntity instanceof TileEntityPiston ? (TileEntityPiston)tileEntity : null;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public BlockPistonMoving() {
      super(Material.piston);
      this.setDefaultState(
         this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, BlockPistonExtension.EnumPistonType.DEFAULT)
      );
      this.setHardness(-1.0F);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return null;
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return !world.isRemote && world.getTileEntity(pos) == null;
   }

   public AxisAlignedBB getBoundingBox(World world, int n, int n2, int n3, IBlockState state, float f, EnumFacing facing) {
      if (state.getBlock() != this && state.getBlock().getMaterial() != Material.air) {
         AxisAlignedBB collisionBoundingBox = state.getBlock().getCollisionBoundingBox(world, state, n, n2, n3);
         if (collisionBoundingBox == null) {
            return null;
         } else {
            double minX2 = collisionBoundingBox.minX;
            double minY2 = collisionBoundingBox.minY;
            double minZ2 = collisionBoundingBox.minZ;
            double maxX2 = collisionBoundingBox.maxX;
            double maxY2 = collisionBoundingBox.maxY;
            double maxZ2 = collisionBoundingBox.maxZ;
            if (facing.getFrontOffsetX() < 0) {
               minX2 -= (double)((float)facing.getFrontOffsetX() * f);
            } else {
               maxX2 -= (double)((float)facing.getFrontOffsetX() * f);
            }

            if (facing.getFrontOffsetY() < 0) {
               minY2 -= (double)((float)facing.getFrontOffsetY() * f);
            } else {
               maxY2 -= (double)((float)facing.getFrontOffsetY() * f);
            }

            if (facing.getFrontOffsetZ() < 0) {
               minZ2 -= (double)((float)facing.getFrontOffsetZ() * f);
            } else {
               maxZ2 -= (double)((float)facing.getFrontOffsetZ() * f);
            }

            return new AxisAlignedBB(minX2, minY2, minZ2, maxX2, maxY2, maxZ2);
         }
      } else {
         return null;
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if (state.getValue(TYPE) == BlockPistonExtension.EnumPistonType.STICKY) {
         n |= 8;
      }

      return n;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(FACING, BlockPistonExtension.getFacing(n))
         .withProperty(TYPE, (n & 8) > 0 ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, TYPE});
   }

   @Override
   public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
      BlockPos pos2 = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
      IBlockState blockState2 = world.getBlockState(pos2);
      if (blockState2.getBlock() instanceof BlockPistonBase && (Boolean)blockState2.getValue(BlockPistonBase.EXTENDED)) {
         world.setBlockToAir(pos2);
      }
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.isRemote) {
         world.getTileEntity(pos);
      }
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityPiston) {
         ((TileEntityPiston)tileEntity).clearPistonTileEntity();
      } else {
         super.breakBlock(world, pos, state);
      }
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote && world.getTileEntity(pos) == null) {
         world.setBlockToAir(pos);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      if (!world.isRemote) {
         TileEntityPiston tileEntity = this.getTileEntity(world, pos);
         if (tileEntity != null) {
            IBlockState pistonState = tileEntity.getPistonState();
            pistonState.getBlock().dropBlockAsItem(world, pos, pistonState, 0);
         }
      }
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      TileEntityPiston tileEntity = this.getTileEntity(world, n, n2, n3);
      if (tileEntity != null) {
         Block block = tileEntity.getPistonState().getBlock();
         if (block == this || block.getMaterial() == Material.air) {
            return;
         }

         float progress = tileEntity.getProgress(0.0F);
         if (tileEntity.isExtending()) {
            progress = 1.0F - progress;
         }

         block.setBlockBoundsBasedOnState(world, n, n2, n3);
         if (block == Blocks.piston || block == Blocks.sticky_piston) {
            progress = 0.0F;
         }

         EnumFacing facing = tileEntity.getFacing();
         this.minX = block.getBlockBoundsMinX() - (double)((float)facing.getFrontOffsetX() * progress);
         this.minY = block.getBlockBoundsMinY() - (double)((float)facing.getFrontOffsetY() * progress);
         this.minZ = block.getBlockBoundsMinZ() - (double)((float)facing.getFrontOffsetZ() * progress);
         this.maxX = block.getBlockBoundsMaxX() - (double)((float)facing.getFrontOffsetX() * progress);
         this.maxY = block.getBlockBoundsMaxY() - (double)((float)facing.getFrontOffsetY() * progress);
         this.maxZ = block.getBlockBoundsMaxZ() - (double)((float)facing.getFrontOffsetZ() * progress);
      }
   }

   public static TileEntity newTileEntity(IBlockState state, EnumFacing facing, boolean flag, boolean flag2) {
      return new TileEntityPiston(state, facing, flag, flag2);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing) {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      TileEntityPiston tileEntity = this.getTileEntity(world, n, n2, n3);
      if (tileEntity == null) {
         return null;
      } else {
         float progress = tileEntity.getProgress(0.0F);
         if (tileEntity.isExtending()) {
            progress = 1.0F - progress;
         }

         return this.getBoundingBox(world, n, n2, n3, tileEntity.getPistonState(), progress, tileEntity.getFacing());
      }
   }

   public MovingObjectPosition collisionRayTrace(World world, BlockPos pos, Vec3 vec, Vec3 vec2) {
      return null;
   }

   private TileEntityPiston getTileEntity(IBlockAccess world, int n, int n2, int n3) {
      TileEntity tileEntity = world.getTileEntity(new BlockPos(n, n2, n3));
      return tileEntity instanceof TileEntityPiston ? (TileEntityPiston)tileEntity : null;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return null;
   }
}
