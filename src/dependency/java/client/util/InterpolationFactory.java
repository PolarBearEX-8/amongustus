package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;

public final class InterpolationFactory extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Item.processAction2(Blocks.block50);
   }

   public InterpolationFactory(int count, String text) {
      super(count, text);
   }
}
