package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class VectorWriter extends EnchantmentManager {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public int computeCount(int count) {
      return 50;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 20;
   }

   public VectorWriter(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.BOW);
      this.withLabelText("arrowInfinite");
   }

   static {
   }
}
