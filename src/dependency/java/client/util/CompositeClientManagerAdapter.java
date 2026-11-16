package client.util;

import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class CompositeClientManagerAdapter extends RotationPool {
   public EntityVillager entityVillager;

   @Override
   public void updateState() {
      this.entityVillager.setEntityPlayer2((EntityPlayer)null);
   }

   @Override
   public void updateState3() {
      this.entityVillager.getPathfindManager().updateState4();
   }

   @Override
   public boolean isActive() {
      if (!this.entityVillager.canExecute()) {
         return false;
      } else if (this.entityVillager.isInWater()) {
         return false;
      } else if (!this.entityVillager.onGround) {
         return false;
      } else if (this.entityVillager.enabled8) {
         return false;
      } else {
         EntityPlayer entityPlayer = this.entityVillager.getEntityPlayer2();
         return entityPlayer == null ? false : (this.entityVillager.getCoordinate(entityPlayer) > 16.0 ? false : entityPlayer.container2 instanceof Container);
      }
   }

   public CompositeClientManagerAdapter(EntityVillager entityVillager2) {
      this.entityVillager = entityVillager2;
      this.setCount(5);
   }
}
