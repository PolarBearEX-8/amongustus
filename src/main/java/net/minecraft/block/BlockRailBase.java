package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRailBase extends Block {
   public final boolean isPowered;

   @Override
   public int getMobilityFlag() {
      return 0;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.isRemote) {
         BlockRailBase.EnumRailDirection value = (BlockRailBase.EnumRailDirection)state.getValue(this.getShapeProperty());
         boolean flag = !World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ());
         if (value == BlockRailBase.EnumRailDirection.ASCENDING_EAST && !World.doesBlockHaveSolidTopSurface(world, pos.getX() + 1, pos.getY(), pos.getZ())) {
            flag = true;
         } else if (value == BlockRailBase.EnumRailDirection.ASCENDING_WEST && !World.doesBlockHaveSolidTopSurface(world, pos.getX() - 1, pos.getY(), pos.getZ())) {
            flag = true;
         } else if (value == BlockRailBase.EnumRailDirection.ASCENDING_NORTH && !World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY(), pos.getZ() - 1)) {
            flag = true;
         } else if (value == BlockRailBase.EnumRailDirection.ASCENDING_SOUTH && !World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY(), pos.getZ() + 1)) {
            flag = true;
         }

         if (flag) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
         } else {
            this.onNeighborChangedInternal(world, pos, state, block);
         }
      }
   }

   @Override
   public MovingObjectPosition collisionRayTrace(World world, int n, int n2, int n3, double d, double d2, double d3, double d4, double d5, double d6) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      return super.collisionRayTrace(world, n, n2, n3, d, d2, d3, d4, d5, d6);
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      super.breakBlock(world, pos, state);
      if (((BlockRailBase.EnumRailDirection)state.getValue(this.getShapeProperty())).isAscending()) {
         world.notifyNeighborsOfStateChange(pos.up(), this);
      }

      if (this.isPowered) {
         world.notifyNeighborsOfStateChange(pos, this);
         world.notifyNeighborsOfStateChange(pos.down(), this);
      }
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ());
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public BlockRailBase(boolean flag) {
      super(Material.circuits);
      this.isPowered = flag;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.setCreativeTab(CreativeTabs.tabTransport);
   }

   public static boolean isRailBlock(World world, BlockPos pos) {
      return isRailBlock(world.getBlockState(pos));
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      BlockRailBase.EnumRailDirection enumRailDirection = blockState2.getBlock() == this ? (BlockRailBase.EnumRailDirection)blockState2.getValue(this.getShapeProperty()) : null;
      if (enumRailDirection != null && enumRailDirection.isAscending()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      }
   }

   public abstract IProperty getShapeProperty();

   public void onNeighborChangedInternal(World world, BlockPos pos, IBlockState state, Block block) {
   }

   public static boolean isRailBlock(IBlockState state) {
      Block block = state.getBlock();
      return block == Blocks.rail || block == Blocks.golden_rail || block == Blocks.detector_rail || block == Blocks.activator_rail;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState updateDir(World world, BlockPos pos, IBlockState state, boolean flag) {
      return world.isRemote ? state : new BlockRailBase.Rail(this, world, pos, state).place(world.isBlockPowered(pos), flag).getBlockState();
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         state = this.updateDir(world, pos, state, true);
         if (this.isPowered) {
            this.onNeighborBlockChange(world, pos, state, this);
         }
      }
   }

public static class Rail {
      private final World world;
      private final BlockPos pos;
      private final BlockRailBase block;
      private final boolean isPowered;
      private final List connectedRails;
      private IBlockState state;
      public final BlockRailBase railBlock;

      private void connectTo(Rail rail) {
         this.connectedRails.add(rail.pos);
         BlockPos pos2 = this.pos.north();
         BlockPos pos3 = this.pos.south();
         BlockPos pos4 = this.pos.west();
         BlockPos pos5 = this.pos.east();
         boolean isConnectedTo = this.isConnectedTo(pos2);
         boolean isConnectedTo2 = this.isConnectedTo(pos3);
         boolean isConnectedTo3 = this.isConnectedTo(pos4);
         boolean isConnectedTo4 = this.isConnectedTo(pos5);
         BlockRailBase.EnumRailDirection enumRailDirection = null;
         if (isConnectedTo || isConnectedTo2) {
            enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         if (isConnectedTo3 || isConnectedTo4) {
            enumRailDirection = BlockRailBase.EnumRailDirection.EAST_WEST;
         }

         if (!this.isPowered) {
            if (isConnectedTo2 && isConnectedTo4 && !isConnectedTo && !isConnectedTo3) {
               enumRailDirection = BlockRailBase.EnumRailDirection.SOUTH_EAST;
            }

            if (isConnectedTo2 && isConnectedTo3 && !isConnectedTo && !isConnectedTo4) {
               enumRailDirection = BlockRailBase.EnumRailDirection.SOUTH_WEST;
            }

            if (isConnectedTo && isConnectedTo3 && !isConnectedTo2 && !isConnectedTo4) {
               enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_WEST;
            }

            if (isConnectedTo && isConnectedTo4 && !isConnectedTo2 && !isConnectedTo3) {
               enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_EAST;
            }
         }

         if (enumRailDirection == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
            if (BlockRailBase.isRailBlock(this.world, pos2.up())) {
               enumRailDirection = BlockRailBase.EnumRailDirection.ASCENDING_NORTH;
            }

            if (BlockRailBase.isRailBlock(this.world, pos3.up())) {
               enumRailDirection = BlockRailBase.EnumRailDirection.ASCENDING_SOUTH;
            }
         }

         if (enumRailDirection == BlockRailBase.EnumRailDirection.EAST_WEST) {
            if (BlockRailBase.isRailBlock(this.world, pos5.up())) {
               enumRailDirection = BlockRailBase.EnumRailDirection.ASCENDING_EAST;
            }

            if (BlockRailBase.isRailBlock(this.world, pos4.up())) {
               enumRailDirection = BlockRailBase.EnumRailDirection.ASCENDING_WEST;
            }
         }

         if (enumRailDirection == null) {
            enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         this.state = this.state.withProperty(this.block.getShapeProperty(), enumRailDirection);
         this.world.setBlockState(this.pos, this.state, 3);
      }

      private boolean canConnectTo(Rail rail) {
         return this.isConnectedToRail(rail) || this.connectedRails.size() != 2;
      }

      private boolean isConnectedToRail(Rail rail) {
         return this.isConnectedTo(rail.pos);
      }

      public Rail(BlockRailBase blockRailBase, World world2, BlockPos pos2, IBlockState state2) {
         this.railBlock = blockRailBase;
         this.connectedRails = Lists.newArrayList();
         this.world = world2;
         this.pos = pos2;
         this.state = state2;
         this.block = (BlockRailBase)state2.getBlock();
         BlockRailBase.EnumRailDirection value = (BlockRailBase.EnumRailDirection)state2.getValue(blockRailBase.getShapeProperty());
         this.isPowered = this.block.isPowered;
         this.updateConnectedRails(value);
      }

      private boolean hasRailAt(BlockPos pos2) {
         return BlockRailBase.isRailBlock(this.world, pos2)
            || BlockRailBase.isRailBlock(this.world, pos2.up())
            || BlockRailBase.isRailBlock(this.world, pos2.down());
      }

      public IBlockState getBlockState() {
         return this.state;
      }

      private boolean isConnectedTo(BlockPos pos2) {
         for (int i = 0; i < this.connectedRails.size(); i++) {
            BlockPos pos3 = (BlockPos)this.connectedRails.get(i);
            if (pos3.getX() == pos2.getX() && pos3.getZ() == pos2.getZ()) {
               return true;
            }
         }

         return false;
      }

      private void updateConnectedRails(BlockRailBase.EnumRailDirection enumRailDirection) {
         this.connectedRails.clear();
         switch (enumRailDirection) {
            case NORTH_SOUTH:
               this.connectedRails.add(this.pos.north());
               this.connectedRails.add(this.pos.south());
               break;
            case EAST_WEST:
               this.connectedRails.add(this.pos.west());
               this.connectedRails.add(this.pos.east());
               break;
            case ASCENDING_EAST:
               this.connectedRails.add(this.pos.west());
               this.connectedRails.add(this.pos.east().up());
               break;
            case ASCENDING_WEST:
               this.connectedRails.add(this.pos.west().up());
               this.connectedRails.add(this.pos.east());
               break;
            case ASCENDING_NORTH:
               this.connectedRails.add(this.pos.north().up());
               this.connectedRails.add(this.pos.south());
               break;
            case ASCENDING_SOUTH:
               this.connectedRails.add(this.pos.north());
               this.connectedRails.add(this.pos.south().up());
               break;
            case SOUTH_EAST:
               this.connectedRails.add(this.pos.east());
               this.connectedRails.add(this.pos.south());
               break;
            case SOUTH_WEST:
               this.connectedRails.add(this.pos.west());
               this.connectedRails.add(this.pos.south());
               break;
            case NORTH_WEST:
               this.connectedRails.add(this.pos.west());
               this.connectedRails.add(this.pos.north());
               break;
            case NORTH_EAST:
               this.connectedRails.add(this.pos.east());
               this.connectedRails.add(this.pos.north());
         }
      }

      public Rail place(boolean flag, boolean flag2) {
         BlockPos pos2 = this.pos.north();
         BlockPos pos3 = this.pos.south();
         BlockPos pos4 = this.pos.west();
         BlockPos pos5 = this.pos.east();
         boolean flag3 = this.hasNeighborRail(pos2);
         boolean flag4 = this.hasNeighborRail(pos3);
         boolean flag5 = this.hasNeighborRail(pos4);
         boolean flag6 = this.hasNeighborRail(pos5);
         BlockRailBase.EnumRailDirection enumRailDirection = null;
         if ((flag3 || flag4) && !flag5 && !flag6) {
            enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         if ((flag5 || flag6) && !flag3 && !flag4) {
            enumRailDirection = BlockRailBase.EnumRailDirection.EAST_WEST;
         }

         if (!this.isPowered) {
            if (flag4 && flag6 && !flag3 && !flag5) {
               enumRailDirection = BlockRailBase.EnumRailDirection.SOUTH_EAST;
            }

            if (flag4 && flag5 && !flag3 && !flag6) {
               enumRailDirection = BlockRailBase.EnumRailDirection.SOUTH_WEST;
            }

            if (flag3 && flag5 && !flag4 && !flag6) {
               enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_WEST;
            }

            if (flag3 && flag6 && !flag4 && !flag5) {
               enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_EAST;
            }
         }

         if (enumRailDirection == null) {
            if (flag3 || flag4) {
               enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
            }

            if (flag5 || flag6) {
               enumRailDirection = BlockRailBase.EnumRailDirection.EAST_WEST;
            }

            if (!this.isPowered) {
               if (flag) {
                  if (flag4 && flag6) {
                     enumRailDirection = BlockRailBase.EnumRailDirection.SOUTH_EAST;
                  }

                  if (flag5 && flag4) {
                     enumRailDirection = BlockRailBase.EnumRailDirection.SOUTH_WEST;
                  }

                  if (flag6 && flag3) {
                     enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_EAST;
                  }

                  if (flag3 && flag5) {
                     enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_WEST;
                  }
               } else {
                  if (flag3 && flag5) {
                     enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_WEST;
                  }

                  if (flag6 && flag3) {
                     enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_EAST;
                  }

                  if (flag5 && flag4) {
                     enumRailDirection = BlockRailBase.EnumRailDirection.SOUTH_WEST;
                  }

                  if (flag4 && flag6) {
                     enumRailDirection = BlockRailBase.EnumRailDirection.SOUTH_EAST;
                  }
               }
            }
         }

         if (enumRailDirection == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
            if (BlockRailBase.isRailBlock(this.world, pos2.up())) {
               enumRailDirection = BlockRailBase.EnumRailDirection.ASCENDING_NORTH;
            }

            if (BlockRailBase.isRailBlock(this.world, pos3.up())) {
               enumRailDirection = BlockRailBase.EnumRailDirection.ASCENDING_SOUTH;
            }
         }

         if (enumRailDirection == BlockRailBase.EnumRailDirection.EAST_WEST) {
            if (BlockRailBase.isRailBlock(this.world, pos5.up())) {
               enumRailDirection = BlockRailBase.EnumRailDirection.ASCENDING_EAST;
            }

            if (BlockRailBase.isRailBlock(this.world, pos4.up())) {
               enumRailDirection = BlockRailBase.EnumRailDirection.ASCENDING_WEST;
            }
         }

         if (enumRailDirection == null) {
            enumRailDirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         this.updateConnectedRails(enumRailDirection);
         this.state = this.state.withProperty(this.block.getShapeProperty(), enumRailDirection);
         if (flag2 || this.world.getBlockState(this.pos) != this.state) {
            this.world.setBlockState(this.pos, this.state, 3);

            for (int i = 0; i < this.connectedRails.size(); i++) {
               Rail rail = this.findRailAt((BlockPos)this.connectedRails.get(i));
               if (rail != null) {
                  rail.removeSoftConnections();
                  if (rail.canConnectTo(this)) {
                     rail.connectTo(this);
                  }
               }
            }
         }

         return this;
      }

      private Rail findRailAt(BlockPos pos2) {
         IBlockState blockState = this.world.getBlockState(pos2);
         if (BlockRailBase.isRailBlock(blockState)) {
            BlockRailBase railBlock2 = this.railBlock;
            return new Rail(railBlock2, this.world, pos2, blockState);
         } else {
            BlockPos pos3 = pos2.up();
            blockState = this.world.getBlockState(pos3);
            if (BlockRailBase.isRailBlock(blockState)) {
               BlockRailBase railBlock3 = this.railBlock;
               return new Rail(railBlock3, this.world, pos3, blockState);
            } else {
               pos3 = pos2.down();
               blockState = this.world.getBlockState(pos3);
               Rail rail;
               if (BlockRailBase.isRailBlock(blockState)) {
                  BlockRailBase railBlock4 = this.railBlock;
                  rail = new Rail(railBlock4, this.world, pos3, blockState);
               } else {
                  rail = null;
               }

               return rail;
            }
         }
      }

      private boolean hasNeighborRail(BlockPos pos2) {
         Rail rail = this.findRailAt(pos2);
         if (rail == null) {
            return false;
         } else {
            rail.removeSoftConnections();
            return rail.canConnectTo(this);
         }
      }

      private void removeSoftConnections() {
         for (int i = 0; i < this.connectedRails.size(); i++) {
            Rail rail = this.findRailAt((BlockPos)this.connectedRails.get(i));
            if (rail != null && rail.isConnectedToRail(this)) {
               this.connectedRails.set(i, rail.pos);
            } else {
               this.connectedRails.remove(i--);
            }
         }
      }

      public int countAdjacentRails() {
         int n = 0;

         for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            if (this.hasRailAt(this.pos.offset(facing))) {
               n++;
            }
         }

         return n;
      }
   }

   public enum EnumRailDirection implements IStringSerializable {
      NORTH_SOUTH(0, "north_south"),
      EAST_WEST(1, "east_west"),
      ASCENDING_EAST(2, "ascending_east"),
      ASCENDING_WEST(3, "ascending_west"),
      ASCENDING_NORTH(4, "ascending_north"),
      ASCENDING_SOUTH(5, "ascending_south"),
      SOUTH_EAST(6, "south_east"),
      SOUTH_WEST(7, "south_west"),
      NORTH_WEST(8, "north_west"),
      NORTH_EAST(9, "north_east");

   private static final EnumRailDirection[] META_LOOKUP = new EnumRailDirection[values().length];
      private final int meta;
      private final String name;
   @Override
      public String getName() {
         return this.name;
      }

      static {
         for (EnumRailDirection enumRailDirection : values()) {
            META_LOOKUP[enumRailDirection.getMetadata()] = enumRailDirection;
         }
      }

      public boolean isAscending() {
         return this == ASCENDING_NORTH || this == ASCENDING_EAST || this == ASCENDING_SOUTH || this == ASCENDING_WEST;
      }

      public int getMetadata() {
         return this.meta;
      }

      @Override
      public String toString() {
         return this.name;
      }

      private EnumRailDirection(int n, String str) {
         this.meta = n;
         this.name = str;
      }

      public static EnumRailDirection byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         return META_LOOKUP[n];
      }
   }
}
