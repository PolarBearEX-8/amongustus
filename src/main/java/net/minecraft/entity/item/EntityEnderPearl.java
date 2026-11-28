package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnderPearl extends EntityThrowable {
   private EntityLivingBase pearlThrower;

   @Override
   public void onImpact(MovingObjectPosition hit) {
      EntityLivingBase thrower2 = this.getThrower();
      if (hit.entityHit != null) {
         if (hit.entityHit == this.pearlThrower) {
            return;
         }

         hit.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, thrower2), 0.0F);
      }

      for (int i = 0; i < 32; i++) {
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.PORTAL,
               this.posX,
               this.posY + this.rand.nextDouble() * 2.0,
               this.posZ,
               this.rand.nextGaussian(),
               0.0,
               this.rand.nextGaussian(),
               new int[0]
            );
      }

      if (!this.entityWorld.isRemote) {
         if (thrower2 instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)thrower2;
            if (entityPlayerMP.playerNetServerHandler.getNetworkManager().isChannelOpen() && entityPlayerMP.entityWorld == this.entityWorld && !entityPlayerMP.isPlayerSleeping()) {
               if (this.rand.nextFloat() < 0.05F && this.entityWorld.getGameRules().getBoolean("doMobSpawning")) {
                  EntityEndermite entityEndermite = new EntityEndermite(this.entityWorld);
                  entityEndermite.setSpawnedByPlayer(true);
                  entityEndermite.setLocationAndAngles(thrower2.posX, thrower2.posY, thrower2.posZ, thrower2.rotationYaw, thrower2.rotationPitch);
                  this.entityWorld.spawnEntityInWorld(entityEndermite);
               }

               if (thrower2.isRiding()) {
                  thrower2.mountEntity((Entity)null);
               }

               thrower2.setPositionAndUpdate(this.posX, this.posY, this.posZ);
               thrower2.fallDistance = 0.0F;
               thrower2.attackEntityFrom(DamageSource.fall, 5.0F);
            }
         } else if (thrower2 != null) {
            thrower2.setPositionAndUpdate(this.posX, this.posY, this.posZ);
            thrower2.fallDistance = 0.0F;
         }

         this.setDead();
      }
   }

   public EntityEnderPearl(World world) {
      super(world);
   }

   public EntityEnderPearl(World world, double d, double d2, double d3) {
      super(world, d, d2, d3);
   }

   public EntityEnderPearl(World world, EntityLivingBase entity) {
      super(world, entity);
      this.pearlThrower = entity;
   }

   @Override
   public void onUpdate() {
      EntityLivingBase thrower2 = this.getThrower();
      if (thrower2 instanceof EntityPlayer && !thrower2.isEntityAlive()) {
         this.setDead();
      } else {
         super.onUpdate();
      }
   }
}
