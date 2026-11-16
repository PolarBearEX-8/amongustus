package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroupManager;

public class BlockPackedIce extends Block {
   @Override
   public int processAction(Random random) {
      return 0;
   }

   public BlockPackedIce() {
      super(Material.material14);
      this.scaleFactor = 0.98F;
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
