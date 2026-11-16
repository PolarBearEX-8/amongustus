package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.util.ResourceLocation;

public class VectorValidator extends EnchantmentManager {
   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public boolean matchesEnchantmentManager(EnchantmentManager enchantmentManager) {
      return super.matchesEnchantmentManager(enchantmentManager) && enchantmentManager.indexOffset != enchantmentManager4.indexOffset;
   }

   @Override
   public int computeCount(int count) {
      return super.computeCountByIndex(count) + 50;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 15 + (count - 1) * 9;
   }

   public VectorValidator(int count, ResourceLocation location, int count2, EnhancedOptionType enhancedOptionType) {
      super(count, location, count2, enhancedOptionType);
      if (enhancedOptionType == EnhancedOptionType.DIGGER) {
         this.withLabelText("lootBonusDigger");
      } else if (enhancedOptionType == EnhancedOptionType.FISHING_ROD) {
         this.withLabelText("lootBonusFishing");
      } else {
         this.withLabelText("lootBonus");
      }
   }

   static {
   }
}
