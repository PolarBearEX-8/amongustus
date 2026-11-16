package client.util;

import net.minecraft.entity.EntityPillagerAdapter;
import net.minecraft.entity.EntitySlime;
import net.minecraft.entity.SharedMonsterAttributes;

public class VirtualFloatEntry extends EntityPillagerAdapter {
   public float factor;
   public int count;
   public EntitySlime entitySlime;
   public boolean enabled2;

   @Override
   public void updateState() {
      this.entityMob.rotationYaw = this.computeFactor(this.entityMob.rotationYaw, this.factor, 30.0F);
      this.entityMob.scaleFactor12 = this.entityMob.rotationYaw;
      this.entityMob.scaleFactor8 = this.entityMob.rotationYaw;
      if (!this.enabled) {
         this.entityMob.setFactor30(0.0F);
      } else {
         this.enabled = false;
         if (this.entityMob.onGround) {
            this.entityMob.setScaleFactor6((float)(this.value * this.entityMob.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getDouble()));
            if (this.count-- <= 0) {
               this.count = this.entitySlime.nextIntAsIndex();
               if (this.enabled2) {
                  this.count /= 3;
               }

               this.entitySlime.getPrimaryItemEntry().updateState();
               if (this.entitySlime.isActive31()) {
                  this.entitySlime
                     .applyText(
                        this.entitySlime.getTextAsText(),
                        this.entitySlime.getFactor(),
                        ((this.entitySlime.getRandom().nextFloat() - this.entitySlime.getRandom().nextFloat()) * 0.2F + 1.0F) * 0.8F
                     );
               }
            } else {
               this.entitySlime.scaleFactor24 = this.entitySlime.scaleFactor3 = 0.0F;
               this.entityMob.setScaleFactor6(0.0F);
            }
         } else {
            this.entityMob.setScaleFactor6((float)(this.value * this.entityMob.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getDouble()));
         }
      }
   }

   public VirtualFloatEntry(EntitySlime entitySlime2) {
      super(entitySlime2);
      this.entitySlime = entitySlime2;
   }

   public void applyFloat(float factor2, boolean flag) {
      this.factor = factor2;
      this.enabled2 = flag;
   }

   public void applyDouble(double offset) {
      this.value = offset;
      this.enabled = true;
   }
}
