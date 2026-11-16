package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;

public final class InterpolationDirector extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Item.processAction2(Blocks.blockTrappedChest);
   }

   public InterpolationDirector(int count, String text) {
      super(count, text);
   }
}
