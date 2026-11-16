package client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;

public final class InterpolationObserver extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Items.lavaBucket;
   }

   public InterpolationObserver(int count, String text) {
      super(count, text);
   }
}
