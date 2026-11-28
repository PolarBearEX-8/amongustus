package net.minecraft.enchantment;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDigging extends Enchantment {
   public EnchantmentDigging(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.DIGGER);
      this.setName("digging");
   }

   @Override
   public boolean canApply(ItemStack stack) {
      return stack.getItem() == Items.shears ? true : super.canApply(stack);
   }

   @Override
   public int getMinEnchantability(int n) {
      return 1 + 10 * (n - 1);
   }

   @Override
   public int getMaxLevel() {
      return 5;
   }

   @Override
   public int getMaxEnchantability(int n) {
      return super.getMinEnchantability(n) + 50;
   }

}
