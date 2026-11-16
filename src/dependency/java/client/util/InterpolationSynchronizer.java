package client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;

public final class InterpolationSynchronizer extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Items.redstone;
   }

   public InterpolationSynchronizer(int count, String text) {
      super(count, text);
   }
}
