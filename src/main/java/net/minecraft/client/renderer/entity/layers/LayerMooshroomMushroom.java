package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderMooshroom;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;

public class LayerMooshroomMushroom implements LayerRenderer {
   private final RenderMooshroom mooshroomRenderer;
   private ModelRenderer modelRendererMushroom;
   private static final ResourceLocation MUSHROOM_RED_TEXTURE = new ResourceLocation("textures/entity/cow/mushroom_red.png");
   private static boolean hasTextureMushroom = false;

   public LayerMooshroomMushroom(RenderMooshroom renderMooshroom) {
      this.mooshroomRenderer = renderMooshroom;
      this.modelRendererMushroom = new ModelRenderer(this.mooshroomRenderer.mainModel);
      this.modelRendererMushroom.setTextureSize(16, 16);
      this.modelRendererMushroom.rotationPointX = -6.0F;
      this.modelRendererMushroom.rotationPointZ = -8.0F;
      this.modelRendererMushroom.rotateAngleY = (float) (Math.PI / 4);
      int[][] intsArr = new int[][]{null, null, {16, 16, 0, 0}, {16, 16, 0, 0}, null, null};
      this.modelRendererMushroom.addBox(intsArr, 0.0F, 0.0F, 10.0F, 20.0F, 16.0F, 0.0F, 0.0F);
      int[][] intsArr2 = new int[][]{null, null, null, null, {16, 16, 0, 0}, {16, 16, 0, 0}};
      this.modelRendererMushroom.addBox(intsArr2, 10.0F, 0.0F, 0.0F, 0.0F, 16.0F, 20.0F, 0.0F);
   }

   public void doRenderLayer(EntityMooshroom entityMooshroom, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (!entityMooshroom.isChild() && !entityMooshroom.isInvisible()) {
         BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
         if (hasTextureMushroom) {
            this.mooshroomRenderer.bindTexture(MUSHROOM_RED_TEXTURE);
         } else {
            this.mooshroomRenderer.bindTexture(TextureMap.locationBlocksTexture);
         }

         GlStateManager.enableCull();
         GlStateManager.cullFace(1028);
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0F, -1.0F, 1.0F);
         GlStateManager.translate(0.2F, 0.35F, 0.5F);
         GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.pushMatrix();
         GlStateManager.translate(-0.5F, -0.5F, 0.5F);
         if (hasTextureMushroom) {
            this.modelRendererMushroom.render(0.0625F);
         } else {
            blockRendererDispatcher.renderBlockBrightness(Blocks.red_mushroom.getDefaultState(), 1.0F);
         }

         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.1F, 0.0F, -0.6F);
         GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(-0.5F, -0.5F, 0.5F);
         if (hasTextureMushroom) {
            this.modelRendererMushroom.render(0.0625F);
         } else {
            blockRendererDispatcher.renderBlockBrightness(Blocks.red_mushroom.getDefaultState(), 1.0F);
         }

         GlStateManager.popMatrix();
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         ((ModelQuadruped)this.mooshroomRenderer.getMainModel()).head.postRender(0.0625F);
         GlStateManager.scale(1.0F, -1.0F, 1.0F);
         GlStateManager.translate(0.0F, 0.7F, -0.2F);
         GlStateManager.rotate(12.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(-0.5F, -0.5F, 0.5F);
         if (hasTextureMushroom) {
            this.modelRendererMushroom.render(0.0625F);
         } else {
            blockRendererDispatcher.renderBlockBrightness(Blocks.red_mushroom.getDefaultState(), 1.0F);
         }

         GlStateManager.popMatrix();
         GlStateManager.cullFace(1029);
         GlStateManager.disableCull();
      }
   }

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntityMooshroom)entity, f, f2, f3, f4, f5, f6, f7);
   }

   public static void update() {
      hasTextureMushroom = Config.hasResource(MUSHROOM_RED_TEXTURE);
   }
}
