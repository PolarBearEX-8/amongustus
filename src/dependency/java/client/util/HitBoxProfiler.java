package client.util;

import net.minecraft.entity.EntityFireball;
import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class HitBoxProfiler extends RotationPool {
   public EntityGhast entityGhast;
   public int count2;

   @Override
   public void updateState() {
      this.entityGhast.setEnabled6(false);
   }

   @Override
   public void updateState2() {
      EntityLivingBase entityLivingBase = this.entityGhast.getEntityLivingBase4();
      double offset = 64.0;
      if (entityLivingBase.getCoordinate(this.entityGhast) < offset * offset && this.entityGhast.checkEntity4(entityLivingBase)) {
         World world = this.entityGhast.worldObj;
         this.count2++;
         if (this.count2 == 10) {
            world.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1007, new BlockPos(this.entityGhast), 0);
         }

         if (this.count2 == 20) {
            double offset2 = 4.0;
            Vec3 vec3 = this.entityGhast.getVec36(1.0F);
            double offset3 = entityLivingBase.posX - (this.entityGhast.posX + vec3.xCoord * offset2);
            double offset4 = entityLivingBase.getBoundingBox2().minY
               + (double)(entityLivingBase.height / 2.0F)
               - (0.5 + this.entityGhast.posY + (double)(this.entityGhast.height / 2.0F));
            double offset5 = entityLivingBase.posZ - (this.entityGhast.posZ + vec3.zCoord * offset2);
            world.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1008, new BlockPos(this.entityGhast), 0);
            EntityFireball entityFireball = new EntityFireball(world, this.entityGhast, offset3, offset4, offset5);
            entityFireball.count16 = this.entityGhast.getCount29();
            entityFireball.posX = this.entityGhast.posX + vec3.xCoord * offset2;
            entityFireball.posY = this.entityGhast.posY + (double)(this.entityGhast.height / 2.0F) + 0.5;
            entityFireball.posZ = this.entityGhast.posZ + vec3.zCoord * offset2;
            world.matchesEntity(entityFireball);
            this.count2 = -40;
         }
      } else if (this.count2 > 0) {
         this.count2--;
      }

      this.entityGhast.setEnabled6(this.count2 > 10);
   }

   @Override
   public boolean isActive() {
      return this.entityGhast.getEntityLivingBase4() != null;
   }

   public HitBoxProfiler(EntityGhast entityGhast2) {
      this.entityGhast = entityGhast2;
   }

   @Override
   public void updateState3() {
      this.count2 = 0;
   }
}
