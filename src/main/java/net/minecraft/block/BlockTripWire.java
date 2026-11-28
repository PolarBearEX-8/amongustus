package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTripWire extends Block {
   public static final PropertyBool POWERED = PropertyBool.create("powered");
   public static final PropertyBool SUSPENDED = PropertyBool.create("suspended");
   public static final PropertyBool ATTACHED = PropertyBool.create("attached");
   public static final PropertyBool DISARMED = PropertyBool.create("disarmed");
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote && (Boolean)world.getBlockState(pos).getValue(POWERED)) {
         this.updateState(world, pos);
      }
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!world.isRemote && !(Boolean)state.getValue(POWERED)) {
         this.updateState(world, pos);
      }
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(POWERED, (n & 1) > 0)
         .withProperty(SUSPENDED, (n & 2) > 0)
         .withProperty(ATTACHED, (n & 4) > 0)
         .withProperty(DISARMED, (n & 8) > 0);
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.string;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{POWERED, SUSPENDED, ATTACHED, DISARMED, NORTH, EAST, WEST, SOUTH});
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      state = state.withProperty(SUSPENDED, !World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()));
      world.setBlockState(pos, state, 3);
      this.notifyHook(world, pos, state);
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      return state.withProperty(NORTH, isConnectedTo(world, n, n2, n3, state, EnumFacing.NORTH))
         .withProperty(EAST, isConnectedTo(world, n, n2, n3, state, EnumFacing.EAST))
         .withProperty(SOUTH, isConnectedTo(world, n, n2, n3, state, EnumFacing.SOUTH))
         .withProperty(WEST, isConnectedTo(world, n, n2, n3, state, EnumFacing.WEST));
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   @Override
   public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      boolean value = (Boolean)state.getValue(SUSPENDED);
      if (value != (!World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()))) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      byte b = 0;
      if ((Boolean)state.getValue(POWERED)) {
         b |= 1;
      }

      if ((Boolean)state.getValue(SUSPENDED)) {
         b |= 2;
      }

      if ((Boolean)state.getValue(ATTACHED)) {
         b |= 4;
      }

      if ((Boolean)state.getValue(DISARMED)) {
         b |= 8;
      }

      return b;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      boolean value = (Boolean)blockState2.getValue(ATTACHED);
      boolean value2 = (Boolean)blockState2.getValue(SUSPENDED);
      if (!value2) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.09375F, 1.0F);
      } else if (!value) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0625F, 0.0F, 1.0F, 0.15625F, 1.0F);
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public static boolean isConnectedTo(IBlockAccess world, int n, int n2, int n3, IBlockState state, EnumFacing facing) {
      n += facing.getFrontOffsetX();
      n2 += facing.getFrontOffsetY();
      n3 += facing.getFrontOffsetZ();
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      Block block = blockState2.getBlock();
      if (block == Blocks.tripwire_hook) {
         EnumFacing opposite = facing.getOpposite();
         return blockState2.getValue(BlockTripWireHook.FACING) == opposite;
      } else if (block == Blocks.tripwire) {
         boolean value = (Boolean)state.getValue(SUSPENDED);
         boolean value2 = (Boolean)blockState2.getValue(SUSPENDED);
         return value == value2;
      } else {
         return false;
      }
   }

   public BlockTripWire() {
      super(Material.circuits);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(POWERED, Boolean.FALSE)
            .withProperty(SUSPENDED, Boolean.FALSE)
            .withProperty(ATTACHED, Boolean.FALSE)
            .withProperty(DISARMED, Boolean.FALSE)
            .withProperty(NORTH, Boolean.FALSE)
            .withProperty(EAST, Boolean.FALSE)
            .withProperty(SOUTH, Boolean.FALSE)
            .withProperty(WEST, Boolean.FALSE)
      );
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.15625F, 1.0F);
      this.setTickRandomly(true);
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      this.notifyHook(world, pos, state.withProperty(POWERED, Boolean.TRUE));
   }

   private void notifyHook(World world, BlockPos pos, IBlockState state) {
      for (EnumFacing facing : new EnumFacing[]{EnumFacing.SOUTH, EnumFacing.WEST}) {
         for (int i = 1; i < 42; i++) {
            BlockPos pos2 = pos.offset(facing, i);
            IBlockState blockState2 = world.getBlockState(pos2);
            if (blockState2.getBlock() == Blocks.tripwire_hook) {
               if (blockState2.getValue(BlockTripWireHook.FACING) == facing.getOpposite()) {
                  Blocks.tripwire_hook.calculateState(world, pos2, blockState2, false, true, i, state);
               }
               break;
            }

            if (blockState2.getBlock() != Blocks.tripwire) {
               break;
            }
         }
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.string;
   }

   private void updateState(World world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos);
      boolean value = (Boolean)blockState2.getValue(POWERED);
      boolean flag = false;
      List list = world.getEntitiesWithinAABBExcludingEntity(
         (Entity)null,
         new AxisAlignedBB(
            (double)pos.getX() + this.minX,
            (double)pos.getY() + this.minY,
            (double)pos.getZ() + this.minZ,
            (double)pos.getX() + this.maxX,
            (double)pos.getY() + this.maxY,
            (double)pos.getZ() + this.maxZ
         )
      );
      if (!list.isEmpty()) {
         for (Entity entity : (Iterable<Entity>)(list)) {
            if (!entity.doesEntityNotTriggerPressurePlate()) {
               flag = true;
               break;
            }
         }
      }

      if (flag != value) {
         blockState2 = blockState2.withProperty(POWERED, flag);
         world.setBlockState(pos, blockState2, 3);
         this.notifyHook(world, pos, blockState2);
      }

      if (flag) {
         world.scheduleUpdate(pos, this, this.tickRate(world));
      }
   }

   @Override
   public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
         world.setBlockState(pos, state.withProperty(DISARMED, Boolean.TRUE), 4);
      }
   }
}
