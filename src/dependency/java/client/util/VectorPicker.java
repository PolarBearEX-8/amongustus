package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class VectorPicker extends EnchantmentManager {
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
      return 5 + 20 * (count - 1);
   }

   public VectorPicker(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.WEAPON);
      this.withLabelText("knockback");
   }

   static {
   }
}
