package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentFireAspect extends Enchantment {
   @Override
   public int getMinEnchantability(int n) {
      return 10 + 20 * (n - 1);
   }

   public EnchantmentFireAspect(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.WEAPON);
      this.setName("fire");
   }

   @Override
   public int getMaxLevel() {
      return 2;
   }

   @Override
   public int getMaxEnchantability(int n) {
      return super.getMinEnchantability(n) + 50;
   }

}
