package net.minecraft.entity;

import client.render.WorldRenderer;
import net.minecraft.client.renderer.EffectRenderer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityHuskAdapter extends TertiaryEntityAdapter {
   public int count15 = 160;
   public boolean enabled15;
   public boolean enabled14;
   public final EffectRenderer effectRenderer;
   public float factor17;
   public float factor15;
   public float factor14;
   public boolean enabled13;

   @Override
   public int computeCount(float factor) {
      return 15728880;
   }

   public void setEnabled14(boolean enabled) {
      this.enabled14 = enabled;
   }

   public void applyIndex6(int count) {
      this.factor17 = (float)((count & 0xFF0000) >> 16) / 255.0F;
      this.factor15 = (float)((count & 0xFF00) >> 8) / 255.0F;
      this.factor14 = (float)((count & 0xFF) >> 0) / 255.0F;
      this.enabled13 = true;
   }

   @Override
   public boolean isActive9() {
      return false;
   }

   @Override
   public float computeFactor(float factor) {
      return 1.0F;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      if (!this.enabled14 || this.count13 < this.count14 / 3 || (this.count13 + this.count14) / 3 % 2 == 0) {
         super.applyWorldRendererByWorldRenderer(worldRenderer, entity, posX, posX2, posX3, posX4, posY, posZ);
      }
   }

   @Override
   public AxisAlignedBB getAxisAlignedBB() {
      return null;
   }

   public void applyIndex7(int count) {
      float factor = (float)((count & 0xFF0000) >> 16) / 255.0F;
      float factor2 = (float)((count & 0xFF00) >> 8) / 255.0F;
      float factor3 = (float)((count & 0xFF) >> 0) / 255.0F;
      float factor4 = 1.0F;
      this.applyFloatByFloat(factor * factor4, factor2 * factor4, factor3 * factor4);
   }

   public void setEnabled15(boolean enabled) {
      this.enabled15 = enabled;
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.count13++ >= this.count14) {
         this.updateState9();
      }

      if (this.count13 > this.count14 / 2) {
         this.applyFloat(1.0F - ((float)this.count13 - (float)(this.count14 / 2)) / (float)this.count14);
         if (this.enabled13) {
            this.factor8 = this.factor8 + (this.factor17 - this.factor8) * 0.2F;
            this.factor11 = this.factor11 + (this.factor15 - this.factor11) * 0.2F;
            this.factor5 = this.factor5 + (this.factor14 - this.factor5) * 0.2F;
         }
      }

      this.applyIndex5(this.count15 + (7 - this.count13 * 8 / this.count14));
      this.motionY -= 0.004;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.91F;
      this.motionY *= 0.91F;
      this.motionZ *= 0.91F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }

      if (this.enabled15 && this.count13 < this.count14 / 2 && (this.count13 + this.count14) % 2 == 0) {
         EntityHuskAdapter entityHuskAdapter = new EntityHuskAdapter(this.worldObj, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0, this.effectRenderer);
         entityHuskAdapter.applyFloat(0.99F);
         entityHuskAdapter.applyFloatByFloat(this.factor8, this.factor11, this.factor5);
         entityHuskAdapter.count13 = entityHuskAdapter.count14 / 2;
         if (this.enabled13) {
            entityHuskAdapter.enabled13 = true;
            entityHuskAdapter.factor17 = this.factor17;
            entityHuskAdapter.factor15 = this.factor15;
            entityHuskAdapter.factor14 = this.factor14;
         }

         entityHuskAdapter.enabled14 = this.enabled14;
         this.effectRenderer.applyTertiaryEntityAdapter2(entityHuskAdapter);
      }
   }

   public EntityHuskAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, EffectRenderer effectRenderer2) {
      super(world, posX, posX2, posX3);
      this.motionX = posX4;
      this.motionY = posY;
      this.motionZ = posZ;
      this.effectRenderer = effectRenderer2;
      this.factor12 *= 0.75F;
      this.count14 = 48 + this.random.nextInt(12);
      this.noClip = false;
   }
}
