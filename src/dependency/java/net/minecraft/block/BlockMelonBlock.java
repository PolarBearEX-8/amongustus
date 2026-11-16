package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;

public class BlockMelonBlock extends Block {
   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.melon;
   }

   @Override
   public int processAction(Random random) {
      return 3 + random.nextInt(5);
   }

   @Override
   public int processAction(int count, Random random) {
      return Math.min(9, this.processAction(random) + random.nextInt(1 + count));
   }

   public BlockMelonBlock() {
      super(Material.material13, MapColor.mapColor14);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
