package client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;

public final class InterpolationSystem extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Items.potion;
   }

   public InterpolationSystem(int count, String text) {
      super(count, text);
   }
}
