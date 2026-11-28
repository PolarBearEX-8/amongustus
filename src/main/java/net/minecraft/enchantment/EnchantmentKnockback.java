package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentKnockback extends Enchantment {
   @Override
   public int getMaxLevel() {
      return 2;
   }

   public EnchantmentKnockback(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.WEAPON);
      this.setName("knockback");
   }

   @Override
   public int getMaxEnchantability(int n) {
      return super.getMinEnchantability(n) + 50;
   }

   @Override
   public int getMinEnchantability(int n) {
      return 5 + 20 * (n - 1);
   }

}
