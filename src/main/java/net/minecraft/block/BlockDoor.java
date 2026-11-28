package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoor extends Block {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyBool OPEN = PropertyBool.create("open");
   public static final PropertyEnum HINGE = PropertyEnum.create("hinge", BlockDoor.EnumHingePosition.class);
   public static final PropertyBool POWERED = PropertyBool.create("powered");
   public static final PropertyEnum HALF = PropertyEnum.create("half", BlockDoor.EnumDoorHalf.class);

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return this.getItem();
   }

   public static boolean isOpen(int n) {
      return (n & 4) != 0;
   }

   private void setBoundBasedOnMeta(int n) {
      float f = 0.1875F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
      EnumFacing facing = getFacing(n);
      boolean isOpen = isOpen(n);
      boolean isHingeLeft = isHingeLeft(n);
      if (isOpen) {
         if (facing == EnumFacing.EAST) {
            if (!isHingeLeft) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            }
         } else if (facing == EnumFacing.SOUTH) {
            if (!isHingeLeft) {
               this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            }
         } else if (facing == EnumFacing.WEST) {
            if (!isHingeLeft) {
               this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            }
         } else if (facing == EnumFacing.NORTH) {
            if (!isHingeLeft) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      } else if (facing == EnumFacing.EAST) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
      } else if (facing == EnumFacing.SOUTH) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
      } else if (facing == EnumFacing.WEST) {
         this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else if (facing == EnumFacing.NORTH) {
         this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return pos.getY() >= 255
         ? false
         : World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) && super.canPlaceBlockAt(world, pos) && super.canPlaceBlockAt(world, pos.up());
   }

   @Override
   public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      BlockPos pos2 = pos.down();
      if (player.capabilities.isCreativeMode && state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER && world.getBlockState(pos2).getBlock() == this) {
         world.setBlockToAir(pos2);
      }
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal((this.getUnlocalizedName() + ".name").replaceAll("tile", "item"));
   }

   public void toggleDoor(World world, BlockPos pos, boolean flag) {
      IBlockState blockState2 = world.getBlockState(pos);
      if (blockState2.getBlock() == this) {
         BlockPos pos2 = blockState2.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
         IBlockState state = pos == pos2 ? blockState2 : world.getBlockState(pos2);
         if (state.getBlock() == this && (Boolean)state.getValue(OPEN) != flag) {
            world.setBlockState(pos2, state.withProperty(OPEN, flag), 2);
            world.markBlockRangeForRenderUpdate(pos2, pos);
            world.playAuxSFXAtEntity((EntityPlayer)null, flag ? 1003 : 1006, pos, 0);
         }
      }
   }

   public static int combineMetadata(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      int metaFromState = blockState2.getBlock().getMetaFromState(blockState2);
      boolean isTop = isTop(metaFromState);
      IBlockState blockState22 = world.getBlockState2(n, n2 - 1, n3);
      int metaFromState2 = blockState22.getBlock().getMetaFromState(blockState22);
      int n4 = isTop ? metaFromState2 : metaFromState;
      IBlockState blockState23 = world.getBlockState2(n, n2 + 1, n3);
      int metaFromState3 = blockState23.getBlock().getMetaFromState(blockState23);
      int n5 = isTop ? metaFromState : metaFromState3;
      boolean flag = (n5 & 1) != 0;
      boolean flag2 = (n5 & 2) != 0;
      return removeHalfBit(n4) | (isTop ? 8 : 0) | (flag ? 16 : 0) | (flag2 ? 32 : 0);
   }

   public static EnumFacing getFacing(int n) {
      return EnumFacing.getHorizontal(n & 3).rotateYCCW();
   }

   @Override
   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return isOpen(combineMetadata(world, n, n2, n3));
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      return super.getCollisionBoundingBox(world, state, n, n2, n3);
   }

   public static boolean isOpen(IBlockAccess world, BlockPos pos) {
      return isOpen(combineMetadata(world, pos));
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
         BlockPos pos2 = pos.down();
         IBlockState blockState2 = world.getBlockState(pos2);
         if (blockState2.getBlock() != this) {
            world.setBlockToAir(pos);
         } else if (block != this) {
            this.onNeighborBlockChange(world, pos2, blockState2, block);
         }
      } else {
         boolean flag = false;
         BlockPos pos3 = pos.up();
         IBlockState blockState3 = world.getBlockState(pos3);
         if (blockState3.getBlock() != this) {
            world.setBlockToAir(pos);
            flag = true;
         }

         if (!World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ())) {
            world.setBlockToAir(pos);
            flag = true;
            if (blockState3.getBlock() == this) {
               world.setBlockToAir(pos3);
            }
         }

         if (flag) {
            if (!world.isRemote) {
               this.dropBlockAsItem(world, pos, state, 0);
            }
         } else {
            boolean flag2 = world.isBlockPowered(pos) || world.isBlockPowered(pos3);
            if ((flag2 || block.canProvidePower()) && block != this && flag2 != (Boolean)blockState3.getValue(POWERED)) {
               world.setBlockState(pos3, blockState3.withProperty(POWERED, flag2), 2);
               if (flag2 != (Boolean)state.getValue(OPEN)) {
                  world.setBlockState(pos, state.withProperty(OPEN, flag2), 2);
                  world.markBlockRangeForRenderUpdate(pos, pos);
                  world.playAuxSFXAtEntity((EntityPlayer)null, flag2 ? 1003 : 1006, pos, 0);
               }
            }
         }
      }
   }

   @Override
   public MovingObjectPosition collisionRayTrace(World world, int n, int n2, int n3, double d, double d2, double d3, double d4, double d5, double d6) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      return super.collisionRayTrace(world, n, n2, n3, d, d2, d3, d4, d5, d6);
   }

   private Item getItem() {
      return this == Blocks.iron_door
         ? Items.iron_door
         : (
            this == Blocks.spruce_door
               ? Items.spruce_door
               : (
                  this == Blocks.birch_door
                     ? Items.birch_door
                     : (
                        this == Blocks.jungle_door
                           ? Items.jungle_door
                           : (this == Blocks.acacia_door ? Items.acacia_door : (this == Blocks.dark_oak_door ? Items.dark_oak_door : Items.oak_door))
                     )
               )
         );
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.setBoundBasedOnMeta(combineMetadata(world, n, n2, n3));
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (this.blockMaterial == Material.iron) {
         return true;
      } else {
         BlockPos pos2 = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
         return (pos.equals(pos2) ? state : world.getBlockState(pos2)).getBlock() == this;
      }
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (this.blockMaterial == Material.iron) {
         return true;
      } else {
         BlockPos pos2 = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
         IBlockState state2 = pos.equals(pos2) ? state : world.getBlockState(pos2);
         if (state2.getBlock() != this) {
            return false;
         } else {
            state = state2.cycleProperty(OPEN);
            world.setBlockState(pos2, state, 2);
            world.markBlockRangeForRenderUpdate(pos2, pos);
            world.playAuxSFXAtEntity(player, (Boolean)state.getValue(OPEN) ? 1003 : 1006, pos, 0);
            return true;
         }
      }
   }

   public static int removeHalfBit(int n) {
      return n & 7;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
         n |= 8;
         if (state.getValue(HINGE) == BlockDoor.EnumHingePosition.RIGHT) {
            n |= 1;
         }

         if ((Boolean)state.getValue(POWERED)) {
            n |= 2;
         }
      } else {
         n |= ((EnumFacing)state.getValue(FACING)).rotateY().getHorizontalIndex();
         if ((Boolean)state.getValue(OPEN)) {
            n |= 4;
         }
      }

      return n;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : this.getItem();
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      this.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
      return super.getSelectedBoundingBox(world, pos);
   }

   public static int combineMetadata(IBlockAccess world, BlockPos pos) {
      return combineMetadata(world, pos.getX(), pos.getY(), pos.getZ());
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER) {
         IBlockState blockState2 = world.getBlockState2(n, n2 + 1, n3);
         if (blockState2.getBlock() == this) {
            state = state.withProperty(HINGE, blockState2.getValue(HINGE)).withProperty(POWERED, blockState2.getValue(POWERED));
         }
      } else {
         IBlockState blockState22 = world.getBlockState2(n, n2 - 1, n3);
         if (blockState22.getBlock() == this) {
            state = state.withProperty(FACING, blockState22.getValue(FACING)).withProperty(OPEN, blockState22.getValue(OPEN));
         }
      }

      return state;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{HALF, FACING, OPEN, HINGE, POWERED});
   }

   public static EnumFacing getFacing(IBlockAccess world, BlockPos pos) {
      return getFacing(combineMetadata(world, pos));
   }

   @Override
   public int getMobilityFlag() {
      return 1;
   }

   public static boolean isTop(int n) {
      return (n & 8) != 0;
   }

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return isOpen(combineMetadata(world, pos));
   }

   public static boolean isHingeLeft(int n) {
      return (n & 16) != 0;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public BlockDoor(Material material) {
      super(material);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(OPEN, Boolean.FALSE)
            .withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT)
            .withProperty(POWERED, Boolean.FALSE)
            .withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER)
      );
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return (n & 8) > 0
         ? this.getDefaultState()
            .withProperty(HALF, BlockDoor.EnumDoorHalf.UPPER)
            .withProperty(HINGE, (n & 1) > 0 ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT)
            .withProperty(POWERED, (n & 2) > 0)
         : this.getDefaultState()
            .withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER)
            .withProperty(FACING, EnumFacing.getHorizontal(n & 3).rotateYCCW())
            .withProperty(OPEN, (n & 4) > 0);
   }


   public enum EnumDoorHalf implements IStringSerializable {
      UPPER,
      LOWER;

   @Override
      public String toString() {
         return this.getName();
      }

      @Override
      public String getName() {
         return this == UPPER ? "upper" : "lower";
      }
   }

   public enum EnumHingePosition implements IStringSerializable {
      LEFT,
      RIGHT;

   @Override
      public String toString() {
         return this.getName();
      }

      @Override
      public String getName() {
         return this == LEFT ? "left" : "right";
      }
   }
}
