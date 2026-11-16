package client.util;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityVillager;
import net.minecraft.util.Vec3;

public class SpecialClientManagerAdapter extends RotationPool {
   public EntityVillager entityVillager;
   public EntityLivingBase entityLivingBase;
   public double value;
   public int count2;

   @Override
   public void updateState() {
      this.entityVillager.setEnabled29(false);
      this.entityLivingBase = null;
   }

   @Override
   public void updateState3() {
      if (this.entityLivingBase != null) {
         this.entityVillager.setEnabled29(true);
      }

      this.count2 = 1000;
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.count2 > 0;
   }

   @Override
   public void updateState2() {
      this.count2--;
      if (this.entityLivingBase != null) {
         if (this.entityVillager.getCoordinate(this.entityLivingBase) > 4.0) {
            this.entityVillager.getPathfindManager().matchesEntityByEntity(this.entityLivingBase, this.value);
         }
      } else if (this.entityVillager.getPathfindManager().isActive2()) {
         Vec3 vec = Vec3Entry2.resolveVec3(this.entityVillager, 16, 3);
         if (vec == null) {
            return;
         }

         this.entityVillager.getPathfindManager().checkDouble(vec.xCoord, vec.yCoord, vec.zCoord, this.value);
      }
   }

   @Override
   public boolean isActive() {
      if (this.entityVillager.getCount9() >= 0) {
         return false;
      } else if (this.entityVillager.getRandom().nextInt(400) != 0) {
         return false;
      } else {
         List list = this.entityVillager.worldObj.resolveList(EntityVillager.class, this.entityVillager.getBoundingBox2().offset(6.0, 3.0, 6.0));
         double offset = Double.MAX_VALUE;

         for (EntityVillager entityVillager2 : (Iterable<EntityVillager>)(Iterable<?>)list) {
            if (entityVillager2 != this.entityVillager && !entityVillager2.isEnabled29() && entityVillager2.getCount9() < 0) {
               double coordinate = entityVillager2.getCoordinate(this.entityVillager);
               if (coordinate <= offset) {
                  offset = coordinate;
                  this.entityLivingBase = entityVillager2;
               }
            }
         }

         return this.entityLivingBase != null || Vec3Entry2.resolveVec3(this.entityVillager, 16, 3) != null;
      }
   }

   public SpecialClientManagerAdapter(EntityVillager entityVillager2, double offset) {
      this.entityVillager = entityVillager2;
      this.value = offset;
      this.setCount(1);
   }
}
