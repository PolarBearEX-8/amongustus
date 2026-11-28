package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTrapDoor extends Block {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyBool OPEN = PropertyBool.create("open");
   public static final PropertyEnum HALF = PropertyEnum.create("half", BlockTrapDoor.DoorHalf.class);

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, OPEN, HALF});
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing) {
      return !facing.getAxis().isVertical() && isValidSupportBlock(world.getBlockState(pos.offset(facing.getOpposite())).getBlock());
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.isRemote) {
         if (!isValidSupportBlock(world.getBlockState(pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite())).getBlock())) {
            world.setBlockToAir(pos);
            this.dropBlockAsItem(world, pos, state, 0);
         } else {
            boolean isBlockPowered = world.isBlockPowered(pos);
            if (isBlockPowered || block.canProvidePower()) {
               boolean value = (Boolean)state.getValue(OPEN);
               if (value != isBlockPowered) {
                  world.setBlockState(pos, state.withProperty(OPEN, isBlockPowered), 2);
                  world.playAuxSFXAtEntity((EntityPlayer)null, isBlockPowered ? 1003 : 1006, pos, 0);
               }
            }
         }
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= getMetaForFacing((EnumFacing)state.getValue(FACING));
      if ((Boolean)state.getValue(OPEN)) {
         n |= 4;
      }

      if (state.getValue(HALF) == BlockTrapDoor.DoorHalf.TOP) {
         n |= 8;
      }

      return n;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (this.blockMaterial != Material.iron) {
         state = state.cycleProperty(OPEN);
         world.setBlockState(pos, state, 2);
         world.playAuxSFXAtEntity(player, (Boolean)state.getValue(OPEN) ? 1003 : 1006, pos, 0);
      }

      return true;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(FACING, getFacing(n))
         .withProperty(OPEN, (n & 4) != 0)
         .withProperty(HALF, (n & 8) == 0 ? BlockTrapDoor.DoorHalf.BOTTOM : BlockTrapDoor.DoorHalf.TOP);
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      IBlockState defaultState = this.getDefaultState();
      if (facing.getAxis().isHorizontal()) {
         defaultState = defaultState.withProperty(FACING, facing).withProperty(OPEN, Boolean.FALSE);
         defaultState = defaultState.withProperty(HALF, f2 > 0.5F ? BlockTrapDoor.DoorHalf.TOP : BlockTrapDoor.DoorHalf.BOTTOM);
      }

      return defaultState;
   }

   public static int getMetaForFacing(EnumFacing facing) {
      switch (facing) {
         case NORTH:
            return 0;
         case SOUTH:
            return 1;
         case WEST:
            return 2;
         case EAST:
         default:
            return 3;
      }
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      return super.getCollisionBoundingBox(world, state, n, n2, n3);
   }

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return !(Boolean)world.getBlockState(pos).getValue(OPEN);
   }

   @Override
   public void setBlockBoundsForItemRender() {
      float f = 0.1875F;
      this.setBlockBounds(0.0F, 0.40625F, 0.0F, 1.0F, 0.59375F, 1.0F);
   }

   @Override
   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return !(Boolean)world.getBlockState2(n, n2, n3).getValue(OPEN);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   private static boolean isValidSupportBlock(Block block) {
      return block.blockMaterial.isOpaque() && block.isFullCube() || block == Blocks.glowstone || block instanceof BlockSlab || block instanceof BlockStairs;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      this.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
      return super.getSelectedBoundingBox(world, pos);
   }

   @Override
   public MovingObjectPosition collisionRayTrace(World world, int n, int n2, int n3, double d, double d2, double d3, double d4, double d5, double d6) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      return super.collisionRayTrace(world, n, n2, n3, d, d2, d3, d4, d5, d6);
   }

   public void setBounds(IBlockState state) {
      if (state.getBlock() == this) {
         boolean flag = state.getValue(HALF) == BlockTrapDoor.DoorHalf.TOP;
         Boolean value = (Boolean)state.getValue(OPEN);
         EnumFacing value2 = (EnumFacing)state.getValue(FACING);
         float f = 0.1875F;
         if (flag) {
            this.setBlockBounds(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1875F, 1.0F);
         }

         if (value) {
            if (value2 == EnumFacing.NORTH) {
               this.setBlockBounds(0.0F, 0.0F, 0.8125F, 1.0F, 1.0F, 1.0F);
            }

            if (value2 == EnumFacing.SOUTH) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.1875F);
            }

            if (value2 == EnumFacing.WEST) {
               this.setBlockBounds(0.8125F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }

            if (value2 == EnumFacing.EAST) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.1875F, 1.0F, 1.0F);
            }
         }
      }
   }

   public BlockTrapDoor(Material material) {
      super(material);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(OPEN, Boolean.FALSE)
            .withProperty(HALF, BlockTrapDoor.DoorHalf.BOTTOM)
      );
      float f = 0.5F;
      float f2 = 1.0F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.setBounds(world.getBlockState2(n, n2, n3));
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public static EnumFacing getFacing(int n) {
      switch (n & 3) {
         case 0:
            return EnumFacing.NORTH;
         case 1:
            return EnumFacing.SOUTH;
         case 2:
            return EnumFacing.WEST;
         case 3:
         default:
            return EnumFacing.EAST;
      }
   }

public enum DoorHalf implements IStringSerializable {
      TOP("top"),
      BOTTOM("bottom");

   private final String name;
   @Override
      public String toString() {
         return this.name;
      }

      @Override
      public String getName() {
         return this.name;
      }

      private DoorHalf(String str) {
         this.name = str;
      }
   }
}
