package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class ExtendedEnchantmentManagerAdapter extends EnchantmentManager {
   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public int computeCount(int count) {
      return this.computeCountByIndex(count) + 30;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 10 * count;
   }

   public ExtendedEnchantmentManagerAdapter(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.ARMOR_HEAD);
      this.withLabelText("oxygen");
   }

   static {
   }
}
