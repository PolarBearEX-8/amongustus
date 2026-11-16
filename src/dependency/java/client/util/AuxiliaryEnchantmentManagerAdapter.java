package client.util;

import client.setting.EnhancedOptionType;
import java.util.Random;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryEnchantmentManagerAdapter extends EnchantmentManager {
   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public int computeCount(int count) {
      return super.computeCountByIndex(count) + 50;
   }

   @Override
   public boolean matchesItemStack(ItemStack stack) {
      return stack.isActive3() ? true : super.matchesItemStack(stack);
   }

   public static boolean matchesItemStack(ItemStack stack, int count, Random random) {
      return stack.getItem() instanceof ItemGoldenBoots && random.nextFloat() < 0.6F ? false : random.nextInt(count + 1) > 0;
   }

   @Override
   public int computeCountByIndex(int count) {
      return 5 + (count - 1) * 8;
   }

   public AuxiliaryEnchantmentManagerAdapter(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.BREAKABLE);
      this.withLabelText("durability");
   }

   static {
   }
}
