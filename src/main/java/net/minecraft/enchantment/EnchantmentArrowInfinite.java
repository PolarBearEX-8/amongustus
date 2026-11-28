package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentArrowInfinite extends Enchantment {
   @Override
   public int getMaxEnchantability(int n) {
      return 50;
   }

   @Override
   public int getMinEnchantability(int n) {
      return 20;
   }

   @Override
   public int getMaxLevel() {
      return 1;
   }

   public EnchantmentArrowInfinite(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.BOW);
      this.setName("arrowInfinite");
   }

}
