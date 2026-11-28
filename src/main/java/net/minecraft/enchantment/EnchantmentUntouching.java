package net.minecraft.enchantment;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentUntouching extends Enchantment {
   @Override
   public int getMaxLevel() {
      return 1;
   }

   public EnchantmentUntouching(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.DIGGER);
      this.setName("untouching");
   }

   @Override
   public boolean canApplyTogether(Enchantment enchantment) {
      return super.canApplyTogether(enchantment) && enchantment.effectId != fortune.effectId;
   }

   @Override
   public int getMinEnchantability(int n) {
      return 15;
   }

   @Override
   public int getMaxEnchantability(int n) {
      return super.getMinEnchantability(n) + 50;
   }

   @Override
   public boolean canApply(ItemStack stack) {
      return stack.getItem() == Items.shears ? true : super.canApply(stack);
   }

}
