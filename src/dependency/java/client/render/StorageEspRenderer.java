package client.render;

import client.event.PreUpdateNotifier;
import client.util.EnchantmentManager;
import client.util.SmoothValidator;
import net.minecraft.util.DamageSource;

public final class StorageEspRenderer implements PreUpdateNotifier {
   public int count;
   public DamageSource damageSource;

   @Override
   public void applyEnchantmentManager(EnchantmentManager enchantmentManager, int count2) {
      this.count = this.count + enchantmentManager.computeCount(count2, this.damageSource);
   }

   public int getIndex(EnchantmentManager enchantmentManager, int count2) {
      return enchantmentManager.computeCount(count2, this.damageSource);
   }

   public StorageEspRenderer(SmoothValidator smoothValidator) {
      this();
   }

   private StorageEspRenderer() {
   }
}
