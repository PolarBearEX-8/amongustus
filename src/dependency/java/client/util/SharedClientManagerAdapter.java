package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class SharedClientManagerAdapter extends RotationPool {
   public EntityHorse entityHorse;
   public double value;
   public double value4;
   public double value2;
   public double value3;

   @Override
   public void updateState3() {
      this.entityHorse.getPathfindManager().checkDouble(this.value4, this.value2, this.value3, this.value);
   }

   @Override
   public void updateState2() {
      if (this.entityHorse.getRandom().nextInt(50) == 0) {
         if (this.entityHorse.entity instanceof EntityPlayer) {
            int count = this.entityHorse.getCount33();
            int count2 = this.entityHorse.getCount10();
            if (count2 > 0 && this.entityHorse.getRandom().nextInt(count2) < count) {
               this.entityHorse.matchesEntityPlayer12((EntityPlayer)this.entityHorse.entity);
               this.entityHorse.worldObj.applyEntity(this.entityHorse, (byte)7);
               return;
            }

            this.entityHorse.computeCountByIndex(5);
         }

         this.entityHorse.entity.setEntity8((Entity)null);
         this.entityHorse.entity = null;
         this.entityHorse.updateState59();
         this.entityHorse.worldObj.applyEntity(this.entityHorse, (byte)6);
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.entityHorse.getPathfindManager().isActive2() && this.entityHorse.entity != null;
   }

   @Override
   public boolean isActive() {
      if (!this.entityHorse.isActive43() && this.entityHorse.entity != null) {
         Vec3 vec = Vec3Entry2.resolveVec3(this.entityHorse, 5, 4);
         if (vec == null) {
            return false;
         } else {
            this.value4 = vec.xCoord;
            this.value2 = vec.yCoord;
            this.value3 = vec.zCoord;
            return true;
         }
      } else {
         return false;
      }
   }

   public SharedClientManagerAdapter(EntityHorse entityHorse2, double offset) {
      this.entityHorse = entityHorse2;
      this.value = offset;
      this.setCount(1);
   }
}
