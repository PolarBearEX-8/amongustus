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
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockStainedGlassPane extends BlockPane {
   public static final PropertyEnum COLOR;

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (int i = 0; i < EnumDyeColor.values().length; i++) {
         list.add(new ItemStack(item, 1, i));
      }
   }

   static {
      COLOR = PropertyEnum.create("color", EnumDyeColor.class);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{NORTH, EAST, WEST, SOUTH, COLOR});
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         BlockBeacon.updateColorAsync(world, pos);
      }
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((EnumDyeColor)state.getValue(COLOR)).getMapColor();
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(n));
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         BlockBeacon.updateColorAsync(world, pos);
      }
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
   }

   public BlockStainedGlassPane() {
      super(Material.glass, false);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(NORTH, Boolean.FALSE)
            .withProperty(EAST, Boolean.FALSE)
            .withProperty(SOUTH, Boolean.FALSE)
            .withProperty(WEST, Boolean.FALSE)
            .withProperty(COLOR, EnumDyeColor.WHITE)
      );
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }
}
