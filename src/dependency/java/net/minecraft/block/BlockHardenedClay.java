package net.minecraft.block;

import client.util.IBlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroupManager;

public class BlockHardenedClay extends Block {
   @Override
   public MapColor processAction3(IBlockState blockState) {
      return MapColor.mapColor36;
   }

   public BlockHardenedClay() {
      super(Material.material4);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
