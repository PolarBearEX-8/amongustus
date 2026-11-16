package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;

public final class InterpolationCounter extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Item.processAction2(Blocks.block100);
   }

   public InterpolationCounter(int count, String text) {
      super(count, text);
   }
}
