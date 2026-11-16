package client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;

public final class InterpolationTracker extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Items.apple;
   }

   public InterpolationTracker(int count, String text) {
      super(count, text);
   }
}
