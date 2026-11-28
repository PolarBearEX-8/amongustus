package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class BlockChest extends BlockContainer {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public final int chestType;

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      int n = 0;
      BlockPos pos2 = pos.west();
      BlockPos pos3 = pos.east();
      BlockPos pos4 = pos.north();
      BlockPos pos5 = pos.south();
      if (world.getBlockState(pos2).getBlock() == this) {
         if (this.isDoubleChest(world, pos2)) {
            return false;
         }

         n++;
      }

      if (world.getBlockState(pos3).getBlock() == this) {
         if (this.isDoubleChest(world, pos3)) {
            return false;
         }

         n++;
      }

      if (world.getBlockState(pos4).getBlock() == this) {
         if (this.isDoubleChest(world, pos4)) {
            return false;
         }

         n++;
      }

      if (world.getBlockState(pos5).getBlock() == this) {
         if (this.isDoubleChest(world, pos5)) {
            return false;
         }

         n++;
      }

      return n <= 1;
   }

   private boolean isBelowSolidBlock(World world, BlockPos pos) {
      return world.getBlockState(pos.up()).getBlock().isNormalCube();
   }

   private boolean isDoubleChest(World world, BlockPos pos) {
      if (world.getBlockState(pos).getBlock() != this) {
         return false;
      } else {
         for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            if (world.getBlockState(pos.offset(facing)).getBlock() == this) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityChest();
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      this.checkForSurroundingChests(world, pos, state);

      for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         BlockPos pos2 = pos.offset(facing);
         IBlockState blockState2 = world.getBlockState(pos2);
         if (blockState2.getBlock() == this) {
            this.checkForSurroundingChests(world, pos2, blockState2);
         }
      }
   }

   public ILockableContainer getLockableContainer(World world, BlockPos pos) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (!(tileEntity instanceof TileEntityChest)) {
         return null;
      } else {
         Object obj = (TileEntityChest)tileEntity;
         if (this.isBlocked(world, pos)) {
            return null;
         } else {
            for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
               BlockPos pos2 = pos.offset(facing);
               if (world.getBlockState(pos2).getBlock() == this) {
                  if (this.isBlocked(world, pos2)) {
                     return null;
                  }

                  TileEntity tileEntity2 = world.getTileEntity(pos2);
                  if (tileEntity2 instanceof TileEntityChest) {
                     if (facing != EnumFacing.WEST && facing != EnumFacing.NORTH) {
                        obj = new InventoryLargeChest("container.chestDouble", (ILockableContainer)obj, (TileEntityChest)tileEntity2);
                     } else {
                        obj = new InventoryLargeChest("container.chestDouble", (TileEntityChest)tileEntity2, (ILockableContainer)obj);
                     }
                  }
               }
            }

            return (ILockableContainer)obj;
         }
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         ILockableContainer lockableContainer = this.getLockableContainer(world, pos);
         if (lockableContainer != null) {
            player.displayGUIChest(lockableContainer);
            if (this.chestType == 0) {
               player.triggerAchievement(StatList.chestOpened);
            } else if (this.chestType == 1) {
               player.triggerAchievement(StatList.trappedChestTriggered);
            }
         }
      }

      return true;
   }

   public BlockChest(int n) {
      super(Material.wood);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.chestType = n;
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING});
   }

   @Override
   public int getStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return facing == EnumFacing.UP ? this.getWeakPower(world, pos, state, facing) : 0;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      if (world.getBlockState2(n, n2, n3 - 1).getBlock() == this) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
      } else if (world.getBlockState2(n, n2, n3 + 1).getBlock() == this) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
      } else if (world.getBlockState2(n - 1, n2, n3).getBlock() == this) {
         this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      } else if (world.getBlockState2(n + 1, n2, n3).getBlock() == this) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
      } else {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState().withProperty(FACING, entity.getHorizontalFacing());
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      super.onNeighborBlockChange(world, pos, state, block);
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityChest) {
         tileEntity.updateContainingBlockInfo();
      }
   }

   private boolean isOcelotSittingOnChest(World world, BlockPos pos) {
      for (Entity entity : (Iterable<Entity>)(world.getEntitiesWithinAABB(
         EntityOcelot.class,
         new AxisAlignedBB(
            (double)pos.getX(),
            (double)(pos.getY() + 1),
            (double)pos.getZ(),
            (double)(pos.getX() + 1),
            (double)(pos.getY() + 2),
            (double)(pos.getZ() + 1)
         )
      ))) {
         if (((EntityOcelot)entity).isSitting()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean canProvidePower() {
      return this.chestType == 1;
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      EnumFacing opposite = EnumFacing.getHorizontal(MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5) & 3).getOpposite();
      state = state.withProperty(FACING, opposite);
      BlockPos pos2 = pos.north();
      BlockPos pos3 = pos.south();
      BlockPos pos4 = pos.west();
      BlockPos pos5 = pos.east();
      boolean flag = this == world.getBlockState(pos2).getBlock();
      boolean flag2 = this == world.getBlockState(pos3).getBlock();
      boolean flag3 = this == world.getBlockState(pos4).getBlock();
      boolean flag4 = this == world.getBlockState(pos5).getBlock();
      if (!flag && !flag2 && !flag3 && !flag4) {
         world.setBlockState(pos, state, 3);
      } else if (opposite.getAxis() != EnumFacing.Axis.X || !flag && !flag2) {
         if (opposite.getAxis() == EnumFacing.Axis.Z && (flag3 || flag4)) {
            if (flag3) {
               world.setBlockState(pos4, state, 3);
            } else {
               world.setBlockState(pos5, state, 3);
            }

            world.setBlockState(pos, state, 3);
         }
      } else {
         if (flag) {
            world.setBlockState(pos2, state, 3);
         } else {
            world.setBlockState(pos3, state, 3);
         }

         world.setBlockState(pos, state, 3);
      }

      if (stack.hasDisplayName()) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityChest) {
            ((TileEntityChest)tileEntity).setCustomName(stack.getDisplayName());
         }
      }
   }

   @Override
   public int getRenderType() {
      return 2;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof IInventory) {
         InventoryHelper.dropInventoryItems(world, pos, (IInventory)tileEntity);
         world.updateComparatorOutputLevel(pos, this);
      }

      super.breakBlock(world, pos, state);
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
   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      if (!this.canProvidePower()) {
         return 0;
      } else {
         int n = 0;
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityChest) {
            n = ((TileEntityChest)tileEntity).numPlayersUsing;
         }

         return MathHelper.clamp_int(n, 0, 15);
      }
   }

   public IBlockState correctFacing(World world, BlockPos pos, IBlockState state) {
      EnumFacing facing = null;

      for (EnumFacing facing2 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         IBlockState blockState2 = world.getBlockState(pos.offset(facing2));
         if (blockState2.getBlock() == this) {
            return state;
         }

         if (blockState2.getBlock().isFullBlock()) {
            if (facing != null) {
               facing = null;
               break;
            }

            facing = facing2;
         }
      }

      if (facing != null) {
         return state.withProperty(FACING, facing.getOpposite());
      } else {
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         if (world.getBlockState(pos.offset(value)).getBlock().isFullBlock()) {
            value = value.getOpposite();
         }

         if (world.getBlockState(pos.offset(value)).getBlock().isFullBlock()) {
            value = value.rotateY();
         }

         if (world.getBlockState(pos.offset(value)).getBlock().isFullBlock()) {
            value = value.getOpposite();
         }

         return state.withProperty(FACING, value);
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      return Container.calcRedstoneFromInventory(this.getLockableContainer(world, pos));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex();
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   private boolean isBlocked(World world, BlockPos pos) {
      return this.isBelowSolidBlock(world, pos) || this.isOcelotSittingOnChest(world, pos);
   }

   public IBlockState checkForSurroundingChests(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         IBlockState blockState2 = world.getBlockState(pos.north());
         IBlockState blockState3 = world.getBlockState(pos.south());
         IBlockState blockState4 = world.getBlockState(pos.west());
         IBlockState blockState5 = world.getBlockState(pos.east());
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         Block block = blockState2.getBlock();
         Block block2 = blockState3.getBlock();
         Block block3 = blockState4.getBlock();
         Block block4 = blockState5.getBlock();
         if (block != this && block2 != this) {
            boolean isFullBlock = block.isFullBlock();
            boolean isFullBlock2 = block2.isFullBlock();
            if (block3 == this || block4 == this) {
               BlockPos pos2 = block3 == this ? pos.west() : pos.east();
               IBlockState blockState6 = world.getBlockState(pos2.north());
               IBlockState blockState7 = world.getBlockState(pos2.south());
               value = EnumFacing.SOUTH;
               EnumFacing facing;
               if (block3 == this) {
                  facing = (EnumFacing)blockState4.getValue(FACING);
               } else {
                  facing = (EnumFacing)blockState5.getValue(FACING);
               }

               if (facing == EnumFacing.NORTH) {
                  value = EnumFacing.NORTH;
               }

               Block block5 = blockState6.getBlock();
               Block block6 = blockState7.getBlock();
               if ((isFullBlock || block5.isFullBlock()) && !isFullBlock2 && !block6.isFullBlock()) {
                  value = EnumFacing.SOUTH;
               }

               if ((isFullBlock2 || block6.isFullBlock()) && !isFullBlock && !block5.isFullBlock()) {
                  value = EnumFacing.NORTH;
               }
            }
         } else {
            BlockPos pos3 = block == this ? pos.north() : pos.south();
            IBlockState blockState8 = world.getBlockState(pos3.west());
            IBlockState blockState9 = world.getBlockState(pos3.east());
            value = EnumFacing.EAST;
            EnumFacing facing2;
            if (block == this) {
               facing2 = (EnumFacing)blockState2.getValue(FACING);
            } else {
               facing2 = (EnumFacing)blockState3.getValue(FACING);
            }

            if (facing2 == EnumFacing.WEST) {
               value = EnumFacing.WEST;
            }

            Block block7 = blockState8.getBlock();
            Block block8 = blockState9.getBlock();
            if ((block3.isFullBlock() || block7.isFullBlock()) && !block4.isFullBlock() && !block8.isFullBlock()) {
               value = EnumFacing.EAST;
            }

            if ((block4.isFullBlock() || block8.isFullBlock()) && !block3.isFullBlock() && !block7.isFullBlock()) {
               value = EnumFacing.WEST;
            }
         }

         state = state.withProperty(FACING, value);
         world.setBlockState(pos, state, 3);
      }

      return state;
   }
}
