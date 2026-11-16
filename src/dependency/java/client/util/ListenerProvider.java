package client.util;

import client.event.PreUpdateNotifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public final class ListenerProvider implements PreUpdateNotifier {
   public EntityLivingBase entityLivingBase;
   public Entity entity;

   @Override
   public void applyEnchantmentManager(EnchantmentManager enchantmentManager, int count) {
      enchantmentManager.applyEntityLivingBase(this.entityLivingBase, this.entity, count);
   }

   public ListenerProvider(SmoothValidator smoothValidator) {
      this();
   }

   private ListenerProvider() {
   }
}
