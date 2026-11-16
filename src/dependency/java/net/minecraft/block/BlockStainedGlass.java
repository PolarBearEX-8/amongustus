package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.LocalOptionType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockStainedGlass extends ExtendedBlockAdapter {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("color", LocalOptionType.class);

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.TRANSLUCENT;
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getMapColor();
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, LocalOptionType.resolveLocalOptionType(count));
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         BlockBeacon.submit(world, blockPos);
      }
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (LocalOptionType localOptionType : LocalOptionType.values()) {
         list.add(new ItemStack(item, 1, localOptionType.getCount()));
      }
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         BlockBeacon.submit(world, blockPos);
      }
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   public BlockStainedGlass(Material material) {
      super(material, false);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, LocalOptionType.WHITE));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
