package net.minecraft.block;

import client.setting.LocalOptionType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class BlockStainedHardenedClay extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("color", LocalOptionType.class);

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getMapColor();
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, LocalOptionType.resolveLocalOptionType(count));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (LocalOptionType localOptionType : LocalOptionType.values()) {
         list.add(new ItemStack(item, 1, localOptionType.getCount()));
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

   public BlockStainedHardenedClay(Material material) {
      super(material);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, LocalOptionType.WHITE));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
