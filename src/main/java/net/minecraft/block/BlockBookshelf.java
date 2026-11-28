package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockBookshelf extends Block {
   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.book;
   }

   @Override
   public int quantityDropped(Random rand) {
      return 3;
   }

   public BlockBookshelf() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }
}
