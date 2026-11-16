package net.minecraft.block;

import client.setting.SystemOptionType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class BlockPrismarine extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", SystemOptionType.class);
   public static final int count3 = SystemOptionType.ROUGH.getCount();
   public static final int count2 = SystemOptionType.BRICKS.getCount();
   public static final int count = SystemOptionType.DARK.getCount();

   @Override
   public IBlockState processAction(int count4) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, SystemOptionType.resolveSystemOptionType(count4));
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return blockState.getComparable(immutableSetEntry) == SystemOptionType.ROUGH ? MapColor.mapColor24 : MapColor.mapColor;
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      list.add(new ItemStack(item, 1, count3));
      list.add(new ItemStack(item, 1, count2));
      list.add(new ItemStack(item, 1, count));
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((SystemOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((SystemOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public String getText() {
      return I18n.getString2(this.getTextAsText() + "." + SystemOptionType.ROUGH.getLabelText2() + ".name");
   }

   public BlockPrismarine() {
      super(Material.material4);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, SystemOptionType.ROUGH));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
