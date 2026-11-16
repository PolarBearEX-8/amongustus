package net.minecraft.entity;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrownexpbottle extends EntityThrowable {
   @Override
   public void applyMovingObjectPosition(MovingObjectPosition hitResult) {
      if (!this.worldObj.state) {
         this.worldObj.applyBlockPosByBlockPos(2002, new BlockPos(this), 0);
         int count = 3 + this.worldObj.random.nextInt(5) + this.worldObj.random.nextInt(5);

         while (count > 0) {
            int count2 = EntityXporb.computeCount(count);
            count -= count2;
            this.worldObj.matchesEntity(new EntityXporb(this.worldObj, this.posX, this.posY, this.posZ, count2));
         }

         this.updateState9();
      }
   }

   @Override
   public float getFactor() {
      return -20.0F;
   }

   @Override
   public float getFactorAsFloat() {
      return 0.07F;
   }

   @Override
   public float getFactor2() {
      return 0.7F;
   }

   public EntityThrownexpbottle(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }

   public EntityThrownexpbottle(World world, EntityLivingBase livingEntity) {
      super(world, livingEntity);
   }

   public EntityThrownexpbottle(World world) {
      super(world);
   }
}
