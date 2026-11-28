package net.minecraft.util;

import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class WeightedRandomFishable extends WeightedRandom.Item {
   private final ItemStack returnStack;
   private float maxDamagePercent;
   private boolean enchantable;

   public WeightedRandomFishable(ItemStack stack, int n) {
      super(n);
      this.returnStack = stack;
   }

   public WeightedRandomFishable setMaxDamagePercent(float f) {
      this.maxDamagePercent = f;
      return this;
   }

   public ItemStack getItemStack(Random rand) {
      ItemStack stack = this.returnStack.copy();
      if (this.maxDamagePercent > 0.0F) {
         int n = (int)(this.maxDamagePercent * (float)this.returnStack.getMaxDamage());
         int n2 = stack.getMaxDamage() - rand.nextInt(rand.nextInt(n) + 1);
         if (n2 > n) {
            n2 = n;
         }

         if (n2 < 1) {
            n2 = 1;
         }

         stack.setItemDamage(n2);
      }

      if (this.enchantable) {
         EnchantmentHelper.addRandomEnchantment(rand, stack, 30);
      }

      return stack;
   }

   public WeightedRandomFishable setEnchantable() {
      this.enchantable = true;
      return this;
   }
}
