package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCarpet extends Block {
   public static final PropertyEnum COLOR;

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.setBlockBoundsFromMeta(0);
   }

   static {
      COLOR = PropertyEnum.create("color", EnumDyeColor.class);
   }

   public void setBlockBoundsFromMeta(int n) {
      float f = (float)(1 + ((byte)0)) / 16.0F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{COLOR});
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return facing == EnumFacing.UP ? true : super.shouldSideBeRendered(world, n, n2, n3, facing);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(n));
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((EnumDyeColor)state.getValue(COLOR)).getMapColor();
   }

   @Override
   public void setBlockBoundsForItemRender() {
      this.setBlockBoundsFromMeta(0);
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (int i = 0; i < 16; i++) {
         list.add(new ItemStack(item, 1, i));
      }
   }

   public BlockCarpet() {
      super(Material.carpet);
      this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setBlockBoundsFromMeta(0);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      this.checkForDrop(world, pos, state);
   }

   private boolean checkForDrop(World world, BlockPos pos, IBlockState state) {
      if (!this.canBlockStay(world, pos)) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   private boolean canBlockStay(World world, BlockPos pos) {
      return !world.isAirBlock(pos.down());
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) && this.canBlockStay(world, pos);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }
}
