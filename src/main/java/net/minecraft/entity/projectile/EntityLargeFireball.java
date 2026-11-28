package net.minecraft.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityLargeFireball extends EntityFireball {
   public int explosionPower = 1;

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      if (tag.hasKey("ExplosionPower", 99)) {
         this.explosionPower = tag.getInteger("ExplosionPower");
      }
   }

   public EntityLargeFireball(World world, EntityLivingBase entity, double d, double d2, double d3) {
      super(world, entity, d, d2, d3);
   }

   @Override
   public void onImpact(MovingObjectPosition hit) {
      if (!this.entityWorld.isRemote) {
         if (hit.entityHit != null) {
            hit.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 6.0F);
            this.applyEnchantments(this.shootingEntity, hit.entityHit);
         }

         boolean flag = this.entityWorld.getGameRules().getBoolean("mobGriefing");
         this.entityWorld.newExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)this.explosionPower, flag, flag);
         this.setDead();
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setInteger("ExplosionPower", this.explosionPower);
   }

   public EntityLargeFireball(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, d4, d5, d6);
   }

   public EntityLargeFireball(World world) {
      super(world);
   }
}
