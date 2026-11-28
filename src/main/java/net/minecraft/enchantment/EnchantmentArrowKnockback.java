package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentArrowKnockback extends Enchantment {
   public EnchantmentArrowKnockback(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.BOW);
      this.setName("arrowKnockback");
   }

   @Override
   public int getMaxEnchantability(int n) {
      return this.getMinEnchantability(n) + 25;
   }

   @Override
   public int getMaxLevel() {
      return 2;
   }

   @Override
   public int getMinEnchantability(int n) {
      return 12 + (n - 1) * 20;
   }

}
