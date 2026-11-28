package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHopper extends BlockContainer {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", new BlockHopper.FilterPredicate());
   public static final PropertyBool ENABLED = PropertyBool.create("enabled");

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      float f = 0.125F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityHopper) {
         InventoryHelper.dropInventoryItems(world, pos, (TileEntityHopper)tileEntity);
         world.updateComparatorOutputLevel(pos, this);
      }

      super.breakBlock(world, pos, state);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   public static boolean isEnabled(int n) {
      return (n & 8) != 8;
   }

   public BlockHopper() {
      super(Material.iron, MapColor.stoneColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN).withProperty(ENABLED, Boolean.TRUE));
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if (!(Boolean)state.getValue(ENABLED)) {
         n |= 8;
      }

      return n;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return true;
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   public static EnumFacing getFacing(int n) {
      return EnumFacing.getFront(n & 7);
   }

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      return Container.calcRedstone(world.getTileEntity(pos));
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      this.updateState(world, pos, state);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      this.updateState(world, pos, state);
   }

   private void updateState(World world, BlockPos pos, IBlockState state) {
      boolean flag = !world.isBlockPowered(pos);
      if (flag != (Boolean)state.getValue(ENABLED)) {
         world.setBlockState(pos, state.withProperty(ENABLED, flag), 4);
      }
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(FACING, getFacing(n)).withProperty(ENABLED, isEnabled(n));
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      EnumFacing opposite = facing.getOpposite();
      if (opposite == EnumFacing.UP) {
         opposite = EnumFacing.DOWN;
      }

      return this.getDefaultState().withProperty(FACING, opposite).withProperty(ENABLED, Boolean.TRUE);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, ENABLED});
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityHopper) {
            player.displayGUIChest((TileEntityHopper)tileEntity);
            player.triggerAchievement(StatList.hopperInspected);
         }
      }

      return true;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityHopper();
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      super.onBlockPlacedBy(world, pos, state, entity, stack);
      if (stack.hasDisplayName()) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityHopper) {
            ((TileEntityHopper)tileEntity).setCustomName(stack.getDisplayName());
         }
      }
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }


   public static final class FilterPredicate implements Predicate {
      public boolean apply(EnumFacing facing) {
         return facing != EnumFacing.UP;
      }

      public boolean apply(Object obj) {
         return this.apply((EnumFacing)obj);
      }
   }
}
