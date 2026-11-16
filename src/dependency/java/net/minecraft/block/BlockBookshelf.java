package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;

public class BlockBookshelf extends Block {
   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.book;
   }

   @Override
   public int processAction(Random random) {
      return 3;
   }

   public BlockBookshelf() {
      super(Material.material);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
