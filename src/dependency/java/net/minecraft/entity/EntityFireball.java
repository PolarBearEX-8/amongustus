package net.minecraft.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFireball extends net.minecraft.entity.projectile.EntityFireball {
   public int count16 = 1;

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("ExplosionPower", 99)) {
         this.count16 = tagCompound.computeCount("ExplosionPower");
      }
   }

   @Override
   public void applyMovingObjectPosition(MovingObjectPosition hitResult) {
      if (!this.worldObj.state) {
         if (hitResult.entityHit != null) {
            hitResult.entityHit
               .matchesDamageSource(DamageSource.resolveDamageSource((net.minecraft.entity.projectile.EntityFireball)this, this.entityLivingBase), 6.0F);
            this.applyEntityLivingBase(this.entityLivingBase, hitResult.entityHit);
         }

         boolean flag = this.worldObj.getDoFireTickManager().matchesText("mobGriefing");
         this.worldObj.resolveBlockBreakState((Entity)null, this.posX, this.posY, this.posZ, (float)this.count16, flag, flag);
         this.updateState9();
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("ExplosionPower", this.count16);
   }

   public EntityFireball(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
   }

   public EntityFireball(World world) {
      super(world);
   }

   public EntityFireball(World world, EntityLivingBase livingEntity, double posX, double posY, double posZ) {
      super(world, livingEntity, posX, posY, posZ);
   }
}
