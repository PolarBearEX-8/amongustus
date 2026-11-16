package client.util;

public class TargetService implements BezierSerializer {
   @Override
   public int computeCount(String text, int count) {
      EnchantmentManager enchantmentManager = EnchantmentManager.get(text);
      return enchantmentManager == null ? count : enchantmentManager.indexOffset;
   }
}
