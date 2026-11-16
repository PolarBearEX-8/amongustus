package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class VectorFilter extends EnchantmentManager {
   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public int computeCount(int count) {
      return super.computeCountByIndex(count) + 50;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 15 + (count - 1) * 9;
   }

   public VectorFilter(int count, ResourceLocation location, int count2, EnhancedOptionType enhancedOptionType) {
      super(count, location, count2, enhancedOptionType);
      this.withLabelText("fishingSpeed");
   }

   static {
   }
}
