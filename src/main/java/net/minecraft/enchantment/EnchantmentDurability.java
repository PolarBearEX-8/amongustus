package net.minecraft.enchantment;

import java.util.Random;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDurability extends Enchantment {
   public static boolean negateDamage(ItemStack stack, int n, Random rand) {
      return stack.getItem() instanceof ItemArmor && rand.nextFloat() < 0.6F ? false : rand.nextInt(n + 1) > 0;
   }

   @Override
   public int getMaxLevel() {
      return 3;
   }

   @Override
   public int getMaxEnchantability(int n) {
      return super.getMinEnchantability(n) + 50;
   }

   @Override
   public boolean canApply(ItemStack stack) {
      return stack.isItemStackDamageable() ? true : super.canApply(stack);
   }

   @Override
   public int getMinEnchantability(int n) {
      return 5 + (n - 1) * 8;
   }

   public EnchantmentDurability(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.BREAKABLE);
      this.setName("durability");
   }

}
