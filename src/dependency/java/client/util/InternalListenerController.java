package client.util;

import client.event.PreUpdateNotifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public final class InternalListenerController implements PreUpdateNotifier {
   public EntityLivingBase entityLivingBase;
   public Entity entity;

   @Override
   public void applyEnchantmentManager(EnchantmentManager enchantmentManager, int count) {
      enchantmentManager.applyEntityLivingBase2(this.entityLivingBase, this.entity, count);
   }

   private InternalListenerController() {
   }

   public InternalListenerController(SmoothValidator smoothValidator) {
      this();
   }
}
