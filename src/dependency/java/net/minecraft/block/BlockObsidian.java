package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;

public class BlockObsidian extends Block {
   @Override
   public MapColor processAction3(IBlockState blockState) {
      return MapColor.mapColor29;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(Blocks.block113);
   }

   public BlockObsidian() {
      super(Material.material4);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
