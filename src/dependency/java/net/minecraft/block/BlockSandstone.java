package net.minecraft.block;

import client.setting.InternalOptionType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class BlockSandstone extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("type", InternalOptionType.class);

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return MapColor.mapColor20;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, InternalOptionType.resolveInternalOptionType(count));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (InternalOptionType internalOptionType : InternalOptionType.values()) {
         list.add(new ItemStack(item, 1, internalOptionType.getCount()));
      }
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((InternalOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((InternalOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockSandstone() {
      super(Material.material4);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, InternalOptionType.DEFAULT));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
