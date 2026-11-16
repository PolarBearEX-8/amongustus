package client.util;

import client.event.PostRenderNotifier;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.MathHelper;

public class TargetAccumulator implements PostRenderNotifier {
   @Override
   public void applyAuxiliaryArrayListAdapter(AuxiliaryArrayListAdapter auxiliaryArrayListAdapter, Random random) {
      EnchantmentManager enchantmentManager = EnchantmentManager.enchantmentManagerArray2[random.nextInt(EnchantmentManager.enchantmentManagerArray2.length)];
      int count = MathHelper.nextInt(random, enchantmentManager.getCount2(), enchantmentManager.getCount());
      ItemStack stack = Items.enchantedBook.resolveItemStack(new AuxiliaryClientManagerResolver(enchantmentManager, count));
      int count2 = 2 + random.nextInt(5 + count * 10) + 3 * count;
      if (count2 > 64) {
         count2 = 64;
      }

      auxiliaryArrayListAdapter.add(new DistanceBuffer(new ItemStack(Items.book), new ItemStack(Items.emerald, count2), stack));
   }
}
