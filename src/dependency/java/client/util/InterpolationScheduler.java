package client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;

public final class InterpolationScheduler extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Items.goldenSword;
   }

   public InterpolationScheduler(int count, String text) {
      super(count, text);
   }
}
