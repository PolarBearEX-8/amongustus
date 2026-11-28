package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentWaterWalker extends Enchantment {
   @Override
   public int getMaxEnchantability(int n) {
      return this.getMinEnchantability(n) + 15;
   }

   @Override
   public int getMinEnchantability(int n) {
      return n * 10;
   }

   @Override
   public int getMaxLevel() {
      return 3;
   }

   public EnchantmentWaterWalker(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.ARMOR_FEET);
      this.setName("waterWalker");
   }

}
