package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentArrowDamage extends Enchantment {
   @Override
   public int getMinEnchantability(int n) {
      return 1 + (n - 1) * 10;
   }

   @Override
   public int getMaxLevel() {
      return 5;
   }

   public EnchantmentArrowDamage(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.BOW);
      this.setName("arrowDamage");
   }

   @Override
   public int getMaxEnchantability(int n) {
      return this.getMinEnchantability(n) + 15;
   }

}
