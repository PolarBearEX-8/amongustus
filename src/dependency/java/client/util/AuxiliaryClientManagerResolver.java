package client.util;

public class AuxiliaryClientManagerResolver extends DampenerSorter {
   public final EnchantmentManager enchantmentManager;
   public final int count2;

   public AuxiliaryClientManagerResolver(EnchantmentManager enchantmentManager2, int count) {
      super(enchantmentManager2.getCountAsIndex());
      this.enchantmentManager = enchantmentManager2;
      this.count2 = count;
   }
}
