package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentWaterWorker extends Enchantment {
   public EnchantmentWaterWorker(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.ARMOR_HEAD);
      this.setName("waterWorker");
   }

   @Override
   public int getMaxEnchantability(int n) {
      return this.getMinEnchantability(n) + 40;
   }

   @Override
   public int getMaxLevel() {
      return 1;
   }

   @Override
   public int getMinEnchantability(int n) {
      return 1;
   }

}
