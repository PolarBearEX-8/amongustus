package net.minecraft.block;

import java.util.List;
import java.util.Random;
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
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockStainedGlass extends BlockBreakable {
   public static final PropertyEnum COLOR;

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{COLOR});
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   public BlockStainedGlass(Material material) {
      super(material, false);
      this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(n));
   }

   static {
      COLOR = PropertyEnum.create("color", EnumDyeColor.class);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((EnumDyeColor)state.getValue(COLOR)).getMapColor();
   }

   @Override
   public boolean canSilkHarvest() {
      return true;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         BlockBeacon.updateColorAsync(world, pos);
      }
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         BlockBeacon.updateColorAsync(world, pos);
      }
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (EnumDyeColor enumDyeColor : EnumDyeColor.values()) {
         list.add(new ItemStack(item, 1, enumDyeColor.getMetadata()));
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
   }
}
