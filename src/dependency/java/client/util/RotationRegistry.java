package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class RotationRegistry extends EnchantmentManager {
   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public int computeCount(int count) {
      return this.computeCountByIndex(count) + 40;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 1;
   }

   public RotationRegistry(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.ARMOR_HEAD);
      this.withLabelText("waterWorker");
   }

   static {
   }
}
