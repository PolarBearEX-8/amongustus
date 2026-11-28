package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentLootBonus extends Enchantment {
   @Override
   public int getMaxLevel() {
      return 3;
   }

   @Override
   public boolean canApplyTogether(Enchantment enchantment) {
      return super.canApplyTogether(enchantment) && enchantment.effectId != silkTouch.effectId;
   }

   @Override
   public int getMinEnchantability(int n) {
      return 15 + (n - 1) * 9;
   }

   public EnchantmentLootBonus(int n, ResourceLocation location, int n2, EnumEnchantmentType enumEnchantmentType) {
      super(n, location, n2, enumEnchantmentType);
      if (enumEnchantmentType == EnumEnchantmentType.DIGGER) {
         this.setName("lootBonusDigger");
      } else if (enumEnchantmentType == EnumEnchantmentType.FISHING_ROD) {
         this.setName("lootBonusFishing");
      } else {
         this.setName("lootBonus");
      }
   }

   @Override
   public int getMaxEnchantability(int n) {
      return super.getMinEnchantability(n) + 50;
   }

}
