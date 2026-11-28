package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.PositionImpl;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.RegistryDefaulted;
import net.minecraft.world.World;

public class BlockDispenser extends BlockContainer {
   public static final PropertyDirection FACING = PropertyDirection.create("facing");
   public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");
   public static final RegistryDefaulted dispenseBehaviorRegistry = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
   public Random rand = new Random();

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      boolean flag = world.isBlockPowered(pos) || world.isBlockPowered(pos.up());
      boolean value = (Boolean)state.getValue(TRIGGERED);
      if (flag && !value) {
         world.scheduleUpdate(pos, this, this.tickRate(world));
         world.setBlockState(pos, state.withProperty(TRIGGERED, Boolean.TRUE), 4);
      } else if (!flag && value) {
         world.setBlockState(pos, state.withProperty(TRIGGERED, Boolean.FALSE), 4);
      }
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      world.setBlockState(pos, state.withProperty(FACING, BlockPistonBase.getFacingFromEntity(world, pos, entity)), 2);
      if (stack.hasDisplayName()) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityDispenser) {
            ((TileEntityDispenser)tileEntity).setCustomName(stack.getDisplayName());
         }
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if ((Boolean)state.getValue(TRIGGERED)) {
         n |= 8;
      }

      return n;
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand2) {
      if (!world.isRemote) {
         this.dispense(world, pos);
      }
   }

   public void dispense(World world, BlockPos pos) {
      BlockSourceImpl blockSourceImpl = new BlockSourceImpl(world, pos);
      TileEntityDispenser blockTileEntity = (TileEntityDispenser)blockSourceImpl.getBlockTileEntity();
      if (blockTileEntity != null) {
         int dispenseSlot = blockTileEntity.getDispenseSlot();
         if (dispenseSlot < 0) {
            world.playAuxSFX(1001, pos, 0);
         } else {
            ItemStack stackInSlot = blockTileEntity.getStackInSlot(dispenseSlot);
            IBehaviorDispenseItem behavior = this.getBehavior(stackInSlot);
            if (behavior != IBehaviorDispenseItem.itemDispenseBehaviorProvider) {
               ItemStack stack = behavior.dispense(blockSourceImpl, stackInSlot);
               blockTileEntity.setInventorySlotContents(dispenseSlot, stack.stackSize <= 0 ? null : stack);
            }
         }
      }
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   public BlockDispenser() {
      super(Material.rock);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, Boolean.FALSE));
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   private void setDefaultDirection(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         boolean isFullBlock = world.getBlockState(pos.north()).getBlock().isFullBlock();
         boolean isFullBlock2 = world.getBlockState(pos.south()).getBlock().isFullBlock();
         if (value == EnumFacing.NORTH && isFullBlock && !isFullBlock2) {
            value = EnumFacing.SOUTH;
         } else if (value == EnumFacing.SOUTH && isFullBlock2 && !isFullBlock) {
            value = EnumFacing.NORTH;
         } else {
            boolean isFullBlock3 = world.getBlockState(pos.west()).getBlock().isFullBlock();
            boolean isFullBlock4 = world.getBlockState(pos.east()).getBlock().isFullBlock();
            if (value == EnumFacing.WEST && isFullBlock3 && !isFullBlock4) {
               value = EnumFacing.EAST;
            } else if (value == EnumFacing.EAST && isFullBlock4 && !isFullBlock3) {
               value = EnumFacing.WEST;
            }
         }

         world.setBlockState(pos, state.withProperty(FACING, value).withProperty(TRIGGERED, Boolean.FALSE), 2);
      }
   }

   @Override
   public IBlockState getStateForEntityRender(IBlockState state) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
   }

   @Override
   public int tickRate(World world) {
      return 4;
   }

   public IBehaviorDispenseItem getBehavior(ItemStack stack) {
      return (IBehaviorDispenseItem)dispenseBehaviorRegistry.getObject(stack == null ? null : stack.getItem());
   }

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      return Container.calcRedstone(world.getTileEntity(pos));
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      super.onBlockAdded(world, pos, state);
      this.setDefaultDirection(world, pos, state);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, TRIGGERED});
   }

   public static IPosition getDispensePosition(IBlockSource blockSource) {
      EnumFacing facing = getFacing(blockSource.getBlockMetadata());
      double d = blockSource.getX() + 0.7 * (double)facing.getFrontOffsetX();
      double d2 = blockSource.getY() + 0.7 * (double)facing.getFrontOffsetY();
      double d3 = blockSource.getZ() + 0.7 * (double)facing.getFrontOffsetZ();
      return new PositionImpl(d, d2, d3);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityDispenser) {
            player.displayGUIChest((TileEntityDispenser)tileEntity);
            if (tileEntity instanceof TileEntityDropper) {
               player.triggerAchievement(StatList.dropperInspected);
            } else {
               player.triggerAchievement(StatList.dispenserInspected);
            }
         }
      }

      return true;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState().withProperty(FACING, BlockPistonBase.getFacingFromEntity(world, pos, entity)).withProperty(TRIGGERED, Boolean.FALSE);
   }

   public static EnumFacing getFacing(int n) {
      return EnumFacing.getFront(n & 7);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityDispenser();
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityDispenser) {
         InventoryHelper.dropInventoryItems(world, pos, (TileEntityDispenser)tileEntity);
         world.updateComparatorOutputLevel(pos, this);
      }

      super.breakBlock(world, pos, state);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(FACING, getFacing(n)).withProperty(TRIGGERED, (n & 8) > 0);
   }
}
