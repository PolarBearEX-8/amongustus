package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class VectorChecker extends EnchantmentManager {
   @Override
   public int getCount() {
      return 5;
   }

   @Override
   public int computeCount(int count) {
      return super.computeCountByIndex(count) + 50;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 1 + 10 * (count - 1);
   }

   @Override
   public boolean matchesItemStack(ItemStack stack) {
      return stack.getItem() == Items.shears ? true : super.matchesItemStack(stack);
   }

   public VectorChecker(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.DIGGER);
      this.withLabelText("digging");
   }

   static {
   }
}
