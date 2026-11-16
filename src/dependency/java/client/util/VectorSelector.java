package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class VectorSelector extends EnchantmentManager {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public int computeCount(int count) {
      return super.computeCountByIndex(count) + 50;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 10 + 20 * (count - 1);
   }

   public VectorSelector(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.WEAPON);
      this.withLabelText("fire");
   }

   static {
   }
}
