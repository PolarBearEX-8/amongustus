package client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;

public final class InterpolationWatcher extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Items.stick;
   }

   public InterpolationWatcher(int count, String text) {
      super(count, text);
   }
}
