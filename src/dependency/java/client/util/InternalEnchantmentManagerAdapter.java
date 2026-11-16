package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class InternalEnchantmentManagerAdapter extends EnchantmentManager {
   @Override
   public int getCount() {
      return 5;
   }

   @Override
   public int computeCount(int count) {
      return this.computeCountByIndex(count) + 15;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 1 + (count - 1) * 10;
   }

   public InternalEnchantmentManagerAdapter(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.BOW);
      this.withLabelText("arrowDamage");
   }

   static {
   }
}
