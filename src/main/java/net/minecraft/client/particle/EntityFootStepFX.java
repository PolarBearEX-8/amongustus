package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFootStepFX extends EntityFX {
   private static final ResourceLocation FOOTPRINT_TEXTURE;
   private int footstepAge;
   private int footstepMaxAge;
   private TextureManager currentFootSteps;

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = ((float)this.footstepAge + f) / (float)this.footstepMaxAge;
      f7 *= f7;
      float f8 = 2.0F - f7 * 2.0F;
      if (f8 > 1.0F) {
         f8 = 1.0F;
      }

      f8 *= 0.2F;
      GlStateManager.disableLighting();
      float f9 = 0.125F;
      float f10 = (float)(this.posX - interpPosX);
      float f11 = (float)(this.posY - interpPosY);
      float f12 = (float)(this.posZ - interpPosZ);
      float lightBrightness = this.entityWorld.getLightBrightness(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
      this.currentFootSteps.bindTexture(FOOTPRINT_TEXTURE);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos((double)(f10 - 0.125F), (double)f11, (double)(f12 + 0.125F)).tex(0.0, 1.0).color(lightBrightness, lightBrightness, lightBrightness, f8).endVertex();
      worldRenderer.pos((double)(f10 + 0.125F), (double)f11, (double)(f12 + 0.125F)).tex(1.0, 1.0).color(lightBrightness, lightBrightness, lightBrightness, f8).endVertex();
      worldRenderer.pos((double)(f10 + 0.125F), (double)f11, (double)(f12 - 0.125F)).tex(1.0, 0.0).color(lightBrightness, lightBrightness, lightBrightness, f8).endVertex();
      worldRenderer.pos((double)(f10 - 0.125F), (double)f11, (double)(f12 - 0.125F)).tex(0.0, 0.0).color(lightBrightness, lightBrightness, lightBrightness, f8).endVertex();
      Tessellator.getInstance().draw();
      GlStateManager.disableBlend();
      GlStateManager.enableLighting();
   }

   @Override
   public void onUpdate() {
      this.footstepAge++;
      if (this.footstepAge == this.footstepMaxAge) {
         this.setDead();
      }
   }

   public EntityFootStepFX(TextureManager textureManager, World world, double d, double d2, double d3) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      this.currentFootSteps = textureManager;
      this.motionX = this.motionY = this.motionZ = 0.0;
      this.footstepMaxAge = 200;
   }

   static {
      String str = "textures/particle/footprint.png";
      FOOTPRINT_TEXTURE = new ResourceLocation(str);
   }

   @Override
   public int getFXLayer() {
      return 3;
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityFootStepFX(Minecraft.getMinecraft().getTextureManager(), world, d, d2, d3);
      }
   }
}
