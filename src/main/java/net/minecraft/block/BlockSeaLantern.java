package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

public class BlockSeaLantern extends Block {
   @Override
   public int quantityDropped(Random rand) {
      return 2 + rand.nextInt(2);
   }

   @Override
   public int quantityDroppedWithBonus(int n, Random rand) {
      return MathHelper.clamp_int(this.quantityDropped(rand) + rand.nextInt(n + 1), 1, 5);
   }

   @Override
   public boolean canSilkHarvest() {
      return true;
   }

   public BlockSeaLantern(Material material) {
      super(material);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return MapColor.quartzColor;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.prismarine_crystals;
   }
}
