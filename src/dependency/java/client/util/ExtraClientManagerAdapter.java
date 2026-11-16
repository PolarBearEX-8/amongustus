package client.util;

import net.minecraft.entity.EntitySlime;

public class ExtraClientManagerAdapter extends RotationPool {
   public EntitySlime entitySlime;
   public float factor;
   public int count2;

   @Override
   public void updateState2() {
      if (--this.count2 <= 0) {
         this.count2 = 40 + this.entitySlime.getRandom().nextInt(60);
         this.factor = (float)this.entitySlime.getRandom().nextInt(360);
      }

      ((VirtualFloatEntry)this.entitySlime.getEntityPillagerAdapter()).applyFloat(this.factor, false);
   }

   public ExtraClientManagerAdapter(EntitySlime entitySlime2) {
      this.entitySlime = entitySlime2;
      this.setCount(2);
   }

   @Override
   public boolean isActive() {
      return this.entitySlime.getEntityLivingBase4() == null && (this.entitySlime.onGround || this.entitySlime.isInWater() || this.entitySlime.isWet());
   }
}
