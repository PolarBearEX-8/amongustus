package net.minecraft.client.particle;

import net.augustus.Augustus;
import net.augustus.modules.render.AttackEffects;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCrit2FX extends EntityFX {
   public float oSize;

   public EntityCrit2FX(World world, double d, double d2, double d3, double d4, double d5, double d6, float f) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      this.motionX *= 0.10000000149011612;
      this.motionY *= 0.10000000149011612;
      this.motionZ *= 0.10000000149011612;
      this.motionX += d4 * 0.4;
      this.motionY += d5 * 0.4;
      this.motionZ += d6 * 0.4;
      this.particleRed = this.particleGreen = this.particleBlue = (float)(Math.random() * 0.30000001192092896 + 0.6000000238418579);
      this.particleScale *= 0.75F;
      this.particleScale *= f;
      this.oSize = this.particleScale;
      this.particleMaxAge = (int)(6.0 / (Math.random() * 0.8 + 0.6));
      this.particleMaxAge = (int)((float)this.particleMaxAge * f);
      this.noClip = false;
      this.setParticleTextureIndex(65);
      this.onUpdate();
   }

   public EntityCrit2FX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      this(world, d, d2, d3, d4, d5, d6, 1.0F);
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setDead();
      }

      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.particleGreen = (float)((double)this.particleGreen * 0.96);
      this.particleBlue = (float)((double)this.particleBlue * 0.9);
      this.motionX *= 0.699999988079071;
      this.motionY *= 0.699999988079071;
      this.motionZ *= 0.699999988079071;
      this.motionY -= 0.019999999552965164;
      if (this.onGround) {
         this.motionX *= 0.699999988079071;
         this.motionZ *= 0.699999988079071;
      }
   }

   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = ((float)this.particleAge + f) / (float)this.particleMaxAge * 32.0F;
      f7 = MathHelper.clamp_float(f7, 0.0F, 1.0F);
      this.particleScale = this.oSize * f7;
      super.renderParticle(worldRenderer, entity, f, f2, f3, f4, f5, f6);
   }


   public static class Factory implements IParticleFactory {

      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         EntityCrit2FX entityCrit2FX = new EntityCrit2FX(world, d, d2, d3, d4, d5, d6);
         AttackEffects attackEffects = Augustus.getInstance().getModuleManager().attackEffects;
         if (attackEffects.isToggled() && attackEffects.noColorFilterOnCrits.getValue()) {
            entityCrit2FX.setRBGColorF(1.0F, 1.0F, 1.0F);
         }

         return entityCrit2FX;
      }

   }

   public static class MagicFactory implements IParticleFactory {

      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         EntityCrit2FX entityCrit2FX = new EntityCrit2FX(world, d, d2, d3, d4, d5, d6);
         AttackEffects attackEffects = Augustus.getInstance().getModuleManager().attackEffects;
         if (attackEffects.isToggled() && attackEffects.noColorFilterOnCrits.getValue()) {
            entityCrit2FX.setRBGColorF(1.0F, 1.0F, 1.0F);
         } else {
            entityCrit2FX.setRBGColorF(entityCrit2FX.getRedColorF() * 0.3F, entityCrit2FX.getGreenColorF() * 0.8F, entityCrit2FX.getBlueColorF());
         }

         entityCrit2FX.nextTextureIndexX();
         return entityCrit2FX;
      }

   }
}
