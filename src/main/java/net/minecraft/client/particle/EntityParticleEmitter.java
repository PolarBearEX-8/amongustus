package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityParticleEmitter extends EntityFX {
   private Entity attachedEntity;
   private int age;
   private int lifetime;
   private EnumParticleTypes particleTypes;
   private float yOffset = 0.0F;

   @Override
   public int getFXLayer() {
      return 3;
   }

   @Override
   public void onUpdate() {
      for (int i = 0; i < 16; i++) {
         double d = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
         double d2 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
         double d3 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
         if (d * d + d2 * d2 + d3 * d3 <= 1.0) {
            double d4 = this.attachedEntity.posX + d * (double)this.attachedEntity.width / 4.0;
            float f = this.attachedEntity.height / 2.0F;
            double d5 = this.attachedEntity.getEntityBoundingBox().minY + (double)(f + this.yOffset) + d2 * (double)this.attachedEntity.height / 4.0;
            double d6 = this.attachedEntity.posZ + d3 * (double)this.attachedEntity.width / 4.0;
            this.entityWorld.spawnParticle(this.particleTypes, false, d4, d5, d6, d, d2 + 0.2, d3, new int[0]);
         }
      }

      this.age++;
      if (this.age >= this.lifetime) {
         this.setDead();
      }
   }

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
   }

   public EntityParticleEmitter(World world, Entity entity, EnumParticleTypes enumParticleTypes) {
      super(world, entity.posX, entity.getEntityBoundingBox().minY + (double)(entity.height / 2.0F), entity.posZ, entity.motionX, entity.motionY, entity.motionZ);
      this.attachedEntity = entity;
      this.lifetime = 3;
      this.particleTypes = enumParticleTypes;
      this.onUpdate();
   }

   public EntityParticleEmitter(World world, Entity entity, EnumParticleTypes enumParticleTypes, float f) {
      super(
         world, entity.posX, entity.getEntityBoundingBox().minY + (double)(entity.height / 2.0F) + (double)f, entity.posZ, entity.motionX, entity.motionY, entity.motionZ
      );
      this.attachedEntity = entity;
      this.lifetime = 3;
      this.particleTypes = enumParticleTypes;
      this.yOffset = f;
      this.onUpdate();
   }
}
