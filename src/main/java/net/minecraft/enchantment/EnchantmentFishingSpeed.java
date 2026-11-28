package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentFishingSpeed extends Enchantment {
   @Override
   public int getMinEnchantability(int n) {
      return 15 + (n - 1) * 9;
   }

   @Override
   public int getMaxLevel() {
      return 3;
   }

   public EnchantmentFishingSpeed(int n, ResourceLocation location, int n2, EnumEnchantmentType enumEnchantmentType) {
      super(n, location, n2, enumEnchantmentType);
      this.setName("fishingSpeed");
   }

   @Override
   public int getMaxEnchantability(int n) {
      return super.getMinEnchantability(n) + 50;
   }

}
