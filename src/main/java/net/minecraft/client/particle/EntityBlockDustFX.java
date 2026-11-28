package net.minecraft.client.particle;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class EntityBlockDustFX extends EntityDiggingFX {
   public EntityBlockDustFX(World world, double d, double d2, double d3, double d4, double d5, double d6, IBlockState state) {
      super(world, d, d2, d3, d4, d5, d6, state);
      this.motionX = d4;
      this.motionY = d5;
      this.motionZ = d6;
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         IBlockState stateById = Block.getStateById(ints[0]);
         return stateById.getBlock().getRenderType() == -1 ? null : new EntityBlockDustFX(world, d, d2, d3, d4, d5, d6, stateById).init();
      }
   }
}
