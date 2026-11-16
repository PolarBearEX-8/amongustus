package client.util;

import client.render.WorldRenderer;
import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.world.World;

public class HitBoxBridge extends TertiaryEntityAdapter {
   public Entity entity2;
   public int count15;
   public int count16;
   public AttackEffectsExplosionNormalType attackEffectsExplosionNormalType;
   public float factor13 = 0.0F;

   public HitBoxBridge(World world, Entity entity, AttackEffectsExplosionNormalType attackEffectsExplosionNormalType2) {
      super(world, entity.posX, entity.getBoundingBox2().minY + (double)(entity.height / 2.0F), entity.posZ, entity.motionX, entity.motionY, entity.motionZ);
      this.entity2 = entity;
      this.count16 = 3;
      this.attackEffectsExplosionNormalType = attackEffectsExplosionNormalType2;
      this.updateState();
   }

   @Override
   public void updateState() {
      for (int index = 0; index < 16; index++) {
         double offset = (double)(this.random.nextFloat() * 2.0F - 1.0F);
         double offset2 = (double)(this.random.nextFloat() * 2.0F - 1.0F);
         double offset3 = (double)(this.random.nextFloat() * 2.0F - 1.0F);
         if (offset * offset + offset2 * offset2 + offset3 * offset3 <= 1.0) {
            double offset4 = this.entity2.posX + offset * (double)this.entity2.width / 4.0;
            float factor = this.entity2.height / 2.0F;
            double offset5 = this.entity2.getBoundingBox2().minY + (double)(factor + this.factor13) + offset2 * (double)this.entity2.height / 4.0;
            double offset6 = this.entity2.posZ + offset3 * (double)this.entity2.width / 4.0;
            this.worldObj.applyAttackEffectsExplosionNorm(this.attackEffectsExplosionNormalType, false, offset4, offset5, offset6, offset, offset2 + 0.2, offset3, new int[0]);
         }
      }

      this.count15++;
      if (this.count15 >= this.count16) {
         this.updateState9();
      }
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
   }

   public HitBoxBridge(World world, Entity entity, AttackEffectsExplosionNormalType attackEffectsExplosionNormalType2, float factor) {
      super(world, entity.posX, entity.getBoundingBox2().minY + (double)(entity.height / 2.0F) + (double)factor, entity.posZ, entity.motionX, entity.motionY, entity.motionZ);
      this.entity2 = entity;
      this.count16 = 3;
      this.attackEffectsExplosionNormalType = attackEffectsExplosionNormalType2;
      this.factor13 = factor;
      this.updateState();
   }

   @Override
   public int getCount4() {
      return 3;
   }
}
