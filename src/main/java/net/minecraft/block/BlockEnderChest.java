package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockEnderChest extends BlockContainer {
   public static final PropertyDirection FACING;

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(Blocks.obsidian);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING});
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
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState().withProperty(FACING, entity.getHorizontalFacing().getOpposite());
   }

   public BlockEnderChest() {
      super(Material.rock);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
   }

   @Override
   public boolean canSilkHarvest() {
      return true;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityEnderChest();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      InventoryEnderChest inventoryEnderChest = player.getInventoryEnderChest();
      TileEntity tileEntity = world.getTileEntity(pos);
      if (inventoryEnderChest == null || !(tileEntity instanceof TileEntityEnderChest)) {
         return true;
      } else if (world.getBlockState(pos.up()).getBlock().isNormalCube()) {
         return true;
      } else if (world.isRemote) {
         return true;
      } else {
         inventoryEnderChest.setChestTileEntity((TileEntityEnderChest)tileEntity);
         player.displayGUIChest(inventoryEnderChest);
         player.triggerAchievement(StatList.enderchestOpened);
         return true;
      }
   }

   @Override
   public int quantityDropped(Random rand) {
      return 8;
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      for (int i = 0; i < 3; i++) {
         int n = rand.nextInt(2) * 2 - 1;
         int n2 = rand.nextInt(2) * 2 - 1;
         double d = (double)pos.getX() + 0.5 + 0.25 * (double)n;
         double d2 = (double)((float)pos.getY() + rand.nextFloat());
         double d3 = (double)pos.getZ() + 0.5 + 0.25 * (double)n2;
         double d4 = (double)(rand.nextFloat() * (float)n);
         double d5 = ((double)rand.nextFloat() - 0.5) * 0.125;
         double d6 = (double)(rand.nextFloat() * (float)n2);
         world.spawnParticle(EnumParticleTypes.PORTAL, d, d2, d3, d4, d5, d6, new int[0]);
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getRenderType() {
      return 2;
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      world.setBlockState(pos, state.withProperty(FACING, entity.getHorizontalFacing().getOpposite()), 2);
   }

   static {
      FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex();
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }
}
