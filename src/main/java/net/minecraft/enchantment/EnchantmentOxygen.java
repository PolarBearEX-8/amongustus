package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentOxygen extends Enchantment {
   @Override
   public int getMaxEnchantability(int n) {
      return this.getMinEnchantability(n) + 30;
   }

   @Override
   public int getMaxLevel() {
      return 3;
   }

   @Override
   public int getMinEnchantability(int n) {
      return 10 * n;
   }

   public EnchantmentOxygen(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.ARMOR_HEAD);
      this.setName("oxygen");
   }

}
