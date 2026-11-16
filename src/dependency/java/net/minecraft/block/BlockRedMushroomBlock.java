package net.minecraft.block;

import client.setting.RenderOptionProperty;
import client.util.FovCalculator;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockRedMushroomBlock extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", RenderOptionProperty.class);
   public final Block block;

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Item.processAction2(this.block);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, RenderOptionProperty.resolveRenderOptionProperty(count));
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      switch (FovCalculator.countArray[((RenderOptionProperty)blockState.getComparable(immutableSetEntry)).ordinal()]) {
         case 1:
            return MapColor.mapColor22;
         case 2:
            return MapColor.mapColor20;
         case 3:
            return MapColor.mapColor20;
         default:
            return super.processAction3(blockState);
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(this.block);
   }

   @Override
   public int processAction(Random random) {
      return Math.max(0, random.nextInt(10) - 7);
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((RenderOptionProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockRedMushroomBlock(Material material, MapColor mapColor, Block block2) {
      super(material, mapColor);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, RenderOptionProperty.ALL_OUTSIDE));
      this.block = block2;
   }
}
