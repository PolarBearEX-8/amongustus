package client.util;

import net.minecraft.entity.EntityBlaze;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySmallfireball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class HitBoxCounter extends RotationPool {
   public EntityBlaze entityBlaze;
   public int count2;
   public int count3;

   @Override
   public void updateState3() {
      this.count2 = 0;
   }

   public HitBoxCounter(EntityBlaze entityBlaze2) {
      this.entityBlaze = entityBlaze2;
      this.setCount(3);
   }

   @Override
   public boolean isActive() {
      EntityLivingBase entityLivingBase = this.entityBlaze.getEntityLivingBase4();
      return entityLivingBase != null && entityLivingBase.canExecute();
   }

   @Override
   public void updateState() {
      this.entityBlaze.setEnabled6(false);
   }

   @Override
   public void updateState2() {
      this.count3--;
      EntityLivingBase entityLivingBase = this.entityBlaze.getEntityLivingBase4();
      double coordinate = this.entityBlaze.getCoordinate(entityLivingBase);
      if (coordinate < 4.0) {
         if (this.count3 <= 0) {
            this.count3 = 20;
            this.entityBlaze.matchesEntity4(entityLivingBase);
         }

         this.entityBlaze.getEntityPillagerAdapter().applyDouble(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, 1.0);
      } else if (coordinate < 256.0) {
         double offset = entityLivingBase.posX - this.entityBlaze.posX;
         double offset2 = entityLivingBase.getBoundingBox2().minY + (double)(entityLivingBase.height / 2.0F) - (this.entityBlaze.posY + (double)(this.entityBlaze.height / 2.0F));
         double offset3 = entityLivingBase.posZ - this.entityBlaze.posZ;
         if (this.count3 <= 0) {
            this.count2++;
            if (this.count2 == 1) {
               this.count3 = 60;
               this.entityBlaze.setEnabled6(true);
            } else if (this.count2 <= 4) {
               this.count3 = 6;
            } else {
               this.count3 = 100;
               this.count2 = 0;
               this.entityBlaze.setEnabled6(false);
            }

            if (this.count2 > 1) {
               float factor = MathHelper.sin(MathHelper.sqrt_double(coordinate)) * 0.5F;
               this.entityBlaze
                  .worldObj
                  .applyEntityPlayerByEntityPlayer(
                     (EntityPlayer)null, 1009, new BlockPos((int)this.entityBlaze.posX, (int)this.entityBlaze.posY, (int)this.entityBlaze.posZ), 0
                  );

               for (int index = 0; index < 1; index++) {
                  EntitySmallfireball entitySmallfireball = new EntitySmallfireball(
                     this.entityBlaze.worldObj,
                     this.entityBlaze,
                     offset + this.entityBlaze.getRandom().nextGaussian() * (double)factor,
                     offset2,
                     offset3 + this.entityBlaze.getRandom().nextGaussian() * (double)factor
                  );
                  entitySmallfireball.posY = this.entityBlaze.posY + (double)(this.entityBlaze.height / 2.0F) + 0.5;
                  this.entityBlaze.worldObj.matchesEntity(entitySmallfireball);
               }
            }
         }

         this.entityBlaze.getEntityRavagerAdapter().applyEntityByEntity(entityLivingBase, 10.0F, 10.0F);
      } else {
         this.entityBlaze.getPathfindManager().updateState4();
         this.entityBlaze.getEntityPillagerAdapter().applyDouble(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, 1.0);
      }

      super.updateState2();
   }
}
