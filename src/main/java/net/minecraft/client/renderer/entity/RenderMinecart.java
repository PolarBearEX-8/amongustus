package net.minecraft.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderMinecart extends Render {
   private static final ResourceLocation minecartTextures = new ResourceLocation("textures/entity/minecart.png");
   public ModelBase modelMinecart = new ModelMinecart();

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityMinecart)entity, d, d2, d3, f, f2);
   }

   public RenderMinecart(RenderManager renderManager2) {
      super(renderManager2);
      this.shadowSize = 0.5F;
   }

   public void renderCartContents(EntityMinecart entityMinecart, float f, IBlockState state) {
      GlStateManager.pushMatrix();
      Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(state, entityMinecart.getBrightness(f));
      GlStateManager.popMatrix();
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityMinecart)entity);
   }

   public ResourceLocation getEntityTexture(EntityMinecart entityMinecart) {
      return minecartTextures;
   }

   public void doRender(EntityMinecart entityMinecart, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      this.bindEntityTexture(entityMinecart);
      long l = (long)entityMinecart.getEntityId() * 493286711L;
      l = l * l * 4392167121L + l * 98761L;
      GlStateManager.translate((((float)(l >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F, (((float)(l >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F, (((float)(l >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F);
      double d4 = entityMinecart.lastTickPosX + (entityMinecart.posX - entityMinecart.lastTickPosX) * (double)f2;
      double d5 = entityMinecart.lastTickPosY + (entityMinecart.posY - entityMinecart.lastTickPosY) * (double)f2;
      double d6 = entityMinecart.lastTickPosZ + (entityMinecart.posZ - entityMinecart.lastTickPosZ) * (double)f2;
      double d7 = 0.3F;
      Vec3 pos = entityMinecart.getPos(d4, d5, d6);
      float f6 = entityMinecart.prevRotationPitch + (entityMinecart.rotationPitch - entityMinecart.prevRotationPitch) * f2;
      if (pos != null) {
         Vec3 posOffset = entityMinecart.getPosOffset(d4, d5, d6, d7);
         Vec3 posOffset2 = entityMinecart.getPosOffset(d4, d5, d6, -d7);
         if (posOffset == null) {
            posOffset = pos;
         }

         if (posOffset2 == null) {
            posOffset2 = pos;
         }

         d += pos.xCoord - d4;
         d2 += (posOffset.yCoord + posOffset2.yCoord) / 2.0 - d5;
         d3 += pos.zCoord - d6;
         Vec3 vec = posOffset2.addVector(-posOffset.xCoord, -posOffset.yCoord, -posOffset.zCoord);
         if (vec.lengthVector() != 0.0) {
            vec = vec.normalize();
            f = (float)(Math.atan2(vec.zCoord, vec.xCoord) * 180.0 / Math.PI);
            f6 = (float)(Math.atan(vec.yCoord) * 73.0);
         }
      }

      GlStateManager.translate((float)d, (float)d2 + 0.375F, (float)d3);
      GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-f6, 0.0F, 0.0F, 1.0F);
      float f7 = (float)entityMinecart.getRollingAmplitude() - f2;
      float f8 = entityMinecart.getDamage() - f2;
      if (f8 < 0.0F) {
         f8 = 0.0F;
      }

      if (f7 > 0.0F) {
         GlStateManager.rotate(MathHelper.sin(f7) * f7 * f8 / 10.0F * (float)entityMinecart.getRollingDirection(), 1.0F, 0.0F, 0.0F);
      }

      int displayTileOffset = entityMinecart.getDisplayTileOffset();
      IBlockState displayTile = entityMinecart.getDisplayTile();
      if (displayTile.getBlock().getRenderType() != -1) {
         GlStateManager.pushMatrix();
         this.bindTexture(TextureMap.locationBlocksTexture);
         float f9 = 0.75F;
         GlStateManager.scale(f9, f9, f9);
         GlStateManager.translate(-0.5F, (float)(displayTileOffset - 8) / 16.0F, 0.5F);
         this.renderCartContents(entityMinecart, f2, displayTile);
         GlStateManager.popMatrix();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.bindEntityTexture(entityMinecart);
      }

      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      this.modelMinecart.render(entityMinecart, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, true);
      GlStateManager.popMatrix();
      super.doRender(entityMinecart, d, d2, d3, f, f2);
   }
}
