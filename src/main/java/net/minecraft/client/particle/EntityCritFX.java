package net.minecraft.client.particle;

import net.minecraft.world.World;

public class EntityCritFX extends EntitySmokeFX {
   public EntityCritFX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, d4, d5, d6, 2.5F);
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityCritFX(world, d, d2, d3, d4, d5, d6);
      }
   }
}
