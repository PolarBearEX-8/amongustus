package net.minecraft.block;

import client.setting.CustomOptionType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;

public class BlockStone extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", CustomOptionType.class);

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((CustomOptionType)blockState.getComparable(immutableSetEntry)).getMapColor();
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, CustomOptionType.resolveCustomOptionType(count));
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return blockState.getComparable(immutableSetEntry) == CustomOptionType.STONE ? Item.processAction2(Blocks.block65) : Item.processAction2(Blocks.stone);
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (CustomOptionType customOptionType : CustomOptionType.values()) {
         list.add(new ItemStack(item, 1, customOptionType.getCount()));
      }
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((CustomOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((CustomOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public String getText() {
      return I18n.getString2(this.getTextAsText() + "." + CustomOptionType.STONE.getLabelText2() + ".name");
   }

   public BlockStone() {
      super(Material.material4);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, CustomOptionType.STONE));
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
