package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockFurnace extends BlockContainer {
   public static final PropertyDirection FACING;
   private final boolean isBurning;
   private static boolean keepInventory;

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      return Container.calcRedstone(world.getTileEntity(pos));
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(Blocks.furnace);
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (this.isBurning) {
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         double d = (double)pos.getX() + 0.5;
         double d2 = (double)pos.getY() + rand.nextDouble() * 6.0 / 16.0;
         double d3 = (double)pos.getZ() + 0.5;
         double d4 = 0.52;
         double d5 = rand.nextDouble() * 0.6 - 0.3;
         switch (value) {
            case WEST:
               world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d - d4, d2, d3 + d5, 0.0, 0.0, 0.0, new int[0]);
               world.spawnParticle(EnumParticleTypes.FLAME, d - d4, d2, d3 + d5, 0.0, 0.0, 0.0, new int[0]);
               break;
            case EAST:
               world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d + d4, d2, d3 + d5, 0.0, 0.0, 0.0, new int[0]);
               world.spawnParticle(EnumParticleTypes.FLAME, d + d4, d2, d3 + d5, 0.0, 0.0, 0.0, new int[0]);
               break;
            case NORTH:
               world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d + d5, d2, d3 - d4, 0.0, 0.0, 0.0, new int[0]);
               world.spawnParticle(EnumParticleTypes.FLAME, d + d5, d2, d3 - d4, 0.0, 0.0, 0.0, new int[0]);
               break;
            case SOUTH:
               world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d + d5, d2, d3 + d4, 0.0, 0.0, 0.0, new int[0]);
               world.spawnParticle(EnumParticleTypes.FLAME, d + d5, d2, d3 + d4, 0.0, 0.0, 0.0, new int[0]);
         }
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex();
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      world.setBlockState(pos, state.withProperty(FACING, entity.getHorizontalFacing().getOpposite()), 2);
      if (stack.hasDisplayName()) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityFurnace) {
            ((TileEntityFurnace)tileEntity).setCustomInventoryName(stack.getDisplayName());
         }
      }
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Item.getItemFromBlock(Blocks.furnace);
   }

   public BlockFurnace(boolean flag) {
      super(Material.rock);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.isBurning = flag;
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState().withProperty(FACING, entity.getHorizontalFacing().getOpposite());
   }

   @Override
   public IBlockState getStateForEntityRender(IBlockState state) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityFurnace) {
            player.displayGUIChest((TileEntityFurnace)tileEntity);
            player.triggerAchievement(StatList.furnaceInteraction);
         }
      }

      return true;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      if (!keepInventory) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityFurnace) {
            InventoryHelper.dropInventoryItems(world, pos, (TileEntityFurnace)tileEntity);
            world.updateComparatorOutputLevel(pos, this);
         }
      }

      super.breakBlock(world, pos, state);
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      this.setDefaultFacing(world, pos, state);
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   private void setDefaultFacing(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         Block block = world.getBlockState(pos.north()).getBlock();
         Block block2 = world.getBlockState(pos.south()).getBlock();
         Block block3 = world.getBlockState(pos.west()).getBlock();
         Block block4 = world.getBlockState(pos.east()).getBlock();
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         if (value == EnumFacing.NORTH && block.isFullBlock() && !block2.isFullBlock()) {
            value = EnumFacing.SOUTH;
         } else if (value == EnumFacing.SOUTH && block2.isFullBlock() && !block.isFullBlock()) {
            value = EnumFacing.NORTH;
         } else if (value == EnumFacing.WEST && block3.isFullBlock() && !block4.isFullBlock()) {
            value = EnumFacing.EAST;
         } else if (value == EnumFacing.EAST && block4.isFullBlock() && !block3.isFullBlock()) {
            value = EnumFacing.WEST;
         }

         world.setBlockState(pos, state.withProperty(FACING, value), 2);
      }
   }

   public static void setState(boolean flag, World world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos);
      TileEntity tileEntity = world.getTileEntity(pos);
      keepInventory = true;
      if (flag) {
         world.setBlockState(pos, Blocks.lit_furnace.getDefaultState().withProperty(FACING, blockState2.getValue(FACING)), 3);
         world.setBlockState(pos, Blocks.lit_furnace.getDefaultState().withProperty(FACING, blockState2.getValue(FACING)), 3);
      } else {
         world.setBlockState(pos, Blocks.furnace.getDefaultState().withProperty(FACING, blockState2.getValue(FACING)), 3);
         world.setBlockState(pos, Blocks.furnace.getDefaultState().withProperty(FACING, blockState2.getValue(FACING)), 3);
      }

      keepInventory = false;
      if (tileEntity != null) {
         tileEntity.validate();
         world.setTileEntity(pos, tileEntity);
      }
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityFurnace();
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
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING});
   }

}
