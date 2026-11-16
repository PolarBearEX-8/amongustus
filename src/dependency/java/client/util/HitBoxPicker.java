package client.util;

import net.minecraft.entity.EntityLivingBase;

public class HitBoxPicker extends PrimaryClientManagerAdapter {
   public HitBoxAdapter hitBoxAdapter;
   public EntityLivingBase entityLivingBase;
   public int count5;

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry.setEntityLivingBase(this.entityLivingBase);
      EntityLivingBase entityLivingBase2 = this.hitBoxAdapter.getEntityLivingBase7();
      if (entityLivingBase2 != null) {
         this.count5 = entityLivingBase2.getIndexOffset5();
      }

      super.updateState3();
   }

   public HitBoxPicker(HitBoxAdapter hitBoxAdapter2) {
      super(hitBoxAdapter2, false);
      this.hitBoxAdapter = hitBoxAdapter2;
      this.setCount(1);
   }

   @Override
   public boolean isActive() {
      if (!this.hitBoxAdapter.isActive32()) {
         return false;
      } else {
         EntityLivingBase entityLivingBase2 = this.hitBoxAdapter.getEntityLivingBase7();
         if (entityLivingBase2 == null) {
            return false;
         } else {
            this.entityLivingBase = entityLivingBase2.getTargetEntity();
            return entityLivingBase2.getIndexOffset5() != this.count5
               && this.matchesEntityLivingBase(this.entityLivingBase, false)
               && this.hitBoxAdapter.matchesEntityLivingBaseByEntityLivingBase(this.entityLivingBase, entityLivingBase2);
         }
      }
   }
}
