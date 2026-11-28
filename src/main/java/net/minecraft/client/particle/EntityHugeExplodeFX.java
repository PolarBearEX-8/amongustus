package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityHugeExplodeFX extends EntityFX {
   private int timeSinceStart;
   private int maximumTime = 8;

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
   }

   @Override
   public void onUpdate() {
      for (int i = 0; i < 6; i++) {
         double d = this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0;
         double d2 = this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0;
         double d3 = this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0;
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.EXPLOSION_LARGE, d, d2, d3, (double)((float)this.timeSinceStart / (float)this.maximumTime), 0.0, 0.0, new int[0]
            );
      }

      this.timeSinceStart++;
      if (this.timeSinceStart == this.maximumTime) {
         this.setDead();
      }
   }

   public EntityHugeExplodeFX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
   }

   @Override
   public int getFXLayer() {
      return 1;
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityHugeExplodeFX(world, d, d2, d3, d4, d5, d6);
      }
   }
}
