package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;
import net.minecraft.util.MathHelper;

public class BlockGlowstone extends Block {
   @Override
   public MapColor processAction3(IBlockState blockState) {
      return MapColor.mapColor20;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.glowstoneDust;
   }

   @Override
   public int processAction(Random random) {
      return 2 + random.nextInt(3);
   }

   @Override
   public int processAction(int count, Random random) {
      return MathHelper.getInt12(this.processAction(random) + random.nextInt(count + 1), 1, 4);
   }

   public BlockGlowstone(Material material) {
      super(material);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
