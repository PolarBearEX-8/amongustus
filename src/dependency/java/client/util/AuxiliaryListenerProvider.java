package client.util;

import client.event.PreUpdateNotifier;
import client.setting.DisablerUndefinedType;

public final class AuxiliaryListenerProvider implements PreUpdateNotifier {
   public float factor;
   public DisablerUndefinedType disablerUndefinedType;

   @Override
   public void applyEnchantmentManager(EnchantmentManager enchantmentManager, int count) {
      this.factor = this.factor + enchantmentManager.computeFactor(count, this.disablerUndefinedType);
   }

   public AuxiliaryListenerProvider(SmoothValidator smoothValidator) {
      this();
   }

   private AuxiliaryListenerProvider() {
   }
}
