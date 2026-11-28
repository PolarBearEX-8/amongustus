package net.minecraft.entity.ai;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class EntityAITradePlayer extends EntityAIBase {
   private EntityVillager villager;

   public EntityAITradePlayer(EntityVillager entityVillager) {
      this.villager = entityVillager;
      this.setMutexBits(5);
   }

   @Override
   public void startExecuting() {
      this.villager.getNavigator().clearPathEntity();
   }

   @Override
   public boolean shouldExecute() {
      if (!this.villager.isEntityAlive()) {
         return false;
      } else if (this.villager.isInWater()) {
         return false;
      } else if (!this.villager.onGround) {
         return false;
      } else if (this.villager.velocityChanged) {
         return false;
      } else {
         EntityPlayer customer = this.villager.getCustomer();
         return customer == null ? false : (this.villager.getDistanceSqToEntity(customer) > 16.0 ? false : customer.openContainer instanceof Container);
      }
   }

   @Override
   public void resetTask() {
      this.villager.setCustomer((EntityPlayer)null);
   }
}
