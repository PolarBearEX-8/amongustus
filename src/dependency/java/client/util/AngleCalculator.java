package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class AngleCalculator extends EnchantmentManager {
   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public int computeCount(int count) {
      return this.computeCountByIndex(count) + 15;
   }

   @Override
   public int computeCountByIndex(int count) {
      return count * 10;
   }

   public AngleCalculator(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.ARMOR_FEET);
      this.withLabelText("waterWalker");
   }

   static {
   }
}
