package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockMelon extends Block {
   @Override
   public int quantityDropped(Random rand) {
      return 3 + rand.nextInt(5);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.melon;
   }

   @Override
   public int quantityDroppedWithBonus(int n, Random rand) {
      return Math.min(9, this.quantityDropped(rand) + rand.nextInt(1 + n));
   }

   public BlockMelon() {
      super(Material.gourd, MapColor.limeColor);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }
}
