package net.minecraft.entity;

import client.render.WorldRenderer;
import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.world.World;

public class EntityWardenAdapter extends TertiaryEntityAdapter {
   public int count15;
   public int count16 = 8;

   @Override
   public int getCount4() {
      return 1;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
   }

   @Override
   public void updateState() {
      for (int index = 0; index < 6; index++) {
         double offset = this.posX + (this.random.nextDouble() - this.random.nextDouble()) * 4.0;
         double offset2 = this.posY + (this.random.nextDouble() - this.random.nextDouble()) * 4.0;
         double offset3 = this.posZ + (this.random.nextDouble() - this.random.nextDouble()) * 4.0;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.EXPLOSION_LARGE, offset, offset2, offset3, (double)((float)this.count15 / (float)this.count16), 0.0, 0.0, new int[0]
            );
      }

      this.count15++;
      if (this.count15 == this.count16) {
         this.updateState9();
      }
   }

   public EntityWardenAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, 0.0, 0.0, 0.0);
   }
}
