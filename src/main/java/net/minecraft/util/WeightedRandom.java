package net.minecraft.util;

import java.util.Collection;
import java.util.Random;

public class WeightedRandom {
   public static WeightedRandom.Item getRandomItem(Random rand, Collection items) {
      return getRandomItem(rand, items, getTotalWeight(items));
   }

   public static WeightedRandom.Item getRandomItem(Collection items, int n) {
      for (WeightedRandom.Item item : (Iterable<WeightedRandom.Item>)(items)) {
         n -= item.itemWeight;
         if (n < 0) {
            return item;
         }
      }

      return (WeightedRandom.Item)null;
   }

   public static WeightedRandom.Item getRandomItem(Random rand, Collection items, int n) {
      if (n <= 0) {
         throw new IllegalArgumentException();
      } else {
         return getRandomItem(items, rand.nextInt(n));
      }
   }

   public static int getTotalWeight(Collection items) {
      int n = 0;

      for (WeightedRandom.Item item : (Iterable<WeightedRandom.Item>)(items)) {
         n += item.itemWeight;
      }

      return n;
   }


   public static class Item {
      public int itemWeight;

      public Item(int n) {
         this.itemWeight = n;
      }
   }
}
