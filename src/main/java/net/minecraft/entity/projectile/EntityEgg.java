package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEgg extends EntityThrowable {
   @Override
   public void onImpact(MovingObjectPosition hit) {
      if (hit.entityHit != null) {
         hit.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
      }

      if (!this.entityWorld.isRemote && this.rand.nextInt(8) == 0) {
         byte b = 1;
         if (this.rand.nextInt(32) == 0) {
            b = 4;
         }

         for (int i = 0; i < b; i++) {
            EntityChicken entityChicken = new EntityChicken(this.entityWorld);
            entityChicken.setGrowingAge(-24000);
            entityChicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.entityWorld.spawnEntityInWorld(entityChicken);
         }
      }

      double d = 0.08;

      for (int j = 0; j < 8; j++) {
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.ITEM_CRACK,
               this.posX,
               this.posY,
               this.posZ,
               ((double)this.rand.nextFloat() - 0.5) * 0.08,
               ((double)this.rand.nextFloat() - 0.5) * 0.08,
               ((double)this.rand.nextFloat() - 0.5) * 0.08,
               new int[]{Item.getIdFromItem(Items.egg)}
            );
      }

      if (!this.entityWorld.isRemote) {
         this.setDead();
      }
   }

   public EntityEgg(World world, double d, double d2, double d3) {
      super(world, d, d2, d3);
   }

   public EntityEgg(World world, EntityLivingBase entity) {
      super(world, entity);
   }

   public EntityEgg(World world) {
      super(world);
   }
}
