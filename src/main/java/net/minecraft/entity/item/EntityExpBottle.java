package net.minecraft.entity.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityExpBottle extends EntityThrowable {
   @Override
   public float getInaccuracy() {
      return -20.0F;
   }

   public EntityExpBottle(World world) {
      super(world);
   }

   public EntityExpBottle(World world, EntityLivingBase entity) {
      super(world, entity);
   }

   public EntityExpBottle(World world, double d, double d2, double d3) {
      super(world, d, d2, d3);
   }

   @Override
   public void onImpact(MovingObjectPosition hit) {
      if (!this.entityWorld.isRemote) {
         this.entityWorld.playAuxSFX(2002, new BlockPos(this), 0);
         int n = 3 + this.entityWorld.rand.nextInt(5) + this.entityWorld.rand.nextInt(5);

         while (n > 0) {
            int xpSplit = EntityXPOrb.getXPSplit(n);
            n -= xpSplit;
            this.entityWorld.spawnEntityInWorld(new EntityXPOrb(this.entityWorld, this.posX, this.posY, this.posZ, xpSplit));
         }

         this.setDead();
      }
   }

   @Override
   public float getVelocity() {
      return 0.7F;
   }

   @Override
   public float getGravityVelocity() {
      return 0.07F;
   }
}
