package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class EnchantmentManagerAdapter extends EnchantmentManager {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public int computeCount(int count) {
      return this.computeCountByIndex(count) + 25;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 12 + (count - 1) * 20;
   }

   public EnchantmentManagerAdapter(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.BOW);
      this.withLabelText("arrowKnockback");
   }

   static {
   }
}
