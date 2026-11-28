package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import java.util.Random;
import net.augustus.modules.render.ItemESP;
import net.augustus.modules.render.M2DItems;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class RenderEntityItem extends Render {
   private final RenderItem itemRenderer;
   private Random random = new Random();

   private int getModelCount(ItemStack stack) {
      byte b = 1;
      if (stack.stackSize > 48) {
         b = 5;
      } else if (stack.stackSize > 32) {
         b = 4;
      } else if (stack.stackSize > 16) {
         b = 3;
      } else if (stack.stackSize > 1) {
         b = 2;
      }

      return b;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityItem)entity, d, d2, d3, f, f2);
   }

   public void doRender(EntityItem entityItem, double d, double d2, double d3, float f, float f2) {
      if (ClientManagers.moduleManager.m2DItems.isToggled() && ClientManagers.moduleManager.m2DItems.remove3DItems.getValue() && entityItem.getEntityItem() != null) {
         if (ClientManagers.moduleManager.m2DItems.shouldRenderItem2D(entityItem.getEntityItem())) {
            return;
         }
      }

      ItemStack entityItem3 = entityItem.getEntityItem();
      this.random.setSeed(187L);
      boolean flag = false;
      if (this.bindEntityTexture(entityItem)) {
         this.renderManager.renderEngine.getTexture(this.getEntityTexture(entityItem)).setBlurMipmap(false, false);
         flag = true;
      }

      GlStateManager.enableRescaleNormal();
      GlStateManager.alphaFunc(516, 0.1F);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.pushMatrix();
      IBakedModel itemModel = this.itemRenderer.getItemModelMesher().getItemModel(entityItem3);
      int n;
      if (ClientManagers.moduleManager.itemPhysics.isToggled()) {
         n = ClientManagers.moduleManager.itemPhysics.transformDroppedItem(entityItem, d, d2, d3, f2, itemModel);
      } else {
         n = this.transformItemModel(entityItem, d, d2, d3, f2, itemModel);
      }

      for (int i = 0; i < n; i++) {
         if (itemModel.isGui3d()) {
            GlStateManager.pushMatrix();
            if (i > 0) {
               GlStateManager.translate((this.random.nextFloat() * 2.0F - 1.0F) * 0.15F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F);
            }

            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            itemModel.getItemCameraTransforms().applyTransform(ItemCameraTransforms.TransformType.GROUND);
            this.itemRenderer.renderItem(entityItem3, itemModel, true, false, false);
            if (ClientManagers.moduleManager.itemESP.isToggled() && ClientManagers.moduleManager.itemESP.mode.getSelectedValue().equals("Box") && entityItem.getEntityItem() != null) {
               if (ItemESP.isItemWhitelisted(entityItem.getEntityItem().getItem())) {
                  if (Config.isShaderPackLoaded() && EntityRenderer.shaderProgramSwitchEnabled) {
                     Shaders.useProgram(Shaders.ProgramNone);
                  }

                  GlStateManager.pushMatrix();
                  GL11.glPushMatrix();
                  float f6 = entityItem.width / 2.0F;
                  RenderUtil.begin3DRender();
                  RenderHelper.disableStandardItemLighting();
                  GL11.glScaled(2.0, 2.0, 2.0);
                  RenderUtil.setGlColor(ClientManagers.moduleManager.itemESP.boxColor.getColorInt((byte)0));
                  RenderUtil.drawFilledBox(0.0F - f6, entityItem.height / -2.0F, 0.0F - f6, 0.0F + f6, entityItem.height / 2.0F, 0.0F + f6);
                  RenderUtil.end3DRender();
                  GlStateManager.enableLighting();
                  GlStateManager.enableLight(0);
                  GlStateManager.enableLight(1);
                  GlStateManager.enableColorMaterial();
                  RenderUtil.setGlColorRgba(1, 1, 1, 1);
                  GL11.glPopMatrix();
                  GlStateManager.popMatrix();
                  if (Config.isShaderPackLoaded() && EntityRenderer.shaderProgramSwitchEnabled) {
                     Shaders.useProgram(Shaders.activeProgram);
                  }
               }
            }

            GlStateManager.popMatrix();
         } else {
            GlStateManager.pushMatrix();
            itemModel.getItemCameraTransforms().applyTransform(ItemCameraTransforms.TransformType.GROUND);
            this.itemRenderer.renderItem(entityItem3, itemModel, false, false, false);
            GlStateManager.popMatrix();
            float f7 = itemModel.getItemCameraTransforms().ground.scale.x;
            float f8 = itemModel.getItemCameraTransforms().ground.scale.y;
            float f9 = itemModel.getItemCameraTransforms().ground.scale.z;
            GlStateManager.translate(0.0F * f7, 0.0F * f8, 0.046875F * f9);
         }
      }

      if (!itemModel.isGui3d() && ClientManagers.moduleManager.itemESP.isToggled() && ClientManagers.moduleManager.itemESP.mode.getSelectedValue().equals("Box") && entityItem.getEntityItem() != null) {
         if (ItemESP.isItemWhitelisted(entityItem.getEntityItem().getItem())) {
            if (Config.isShaderPackLoaded()) {
               Shaders.useProgram(Shaders.ProgramNone);
            }

            float f10 = 0.046875F * itemModel.getItemCameraTransforms().ground.scale.z * (float)(-n);
            GlStateManager.translate(0.0F, 0.0F, f10 / 2.0F);
            GlStateManager.pushMatrix();
            GL11.glPushMatrix();
            float f11 = entityItem.width / 2.0F;
            RenderUtil.begin3DRender();
            RenderHelper.disableStandardItemLighting();
            GL11.glScaled(2.0, 2.0, 2.0);
            RenderUtil.setGlColor(ClientManagers.moduleManager.itemESP.boxColor.getColorInt((byte)0));
            RenderUtil.drawFilledBox(0.0F - f11, entityItem.height / -2.0F, 0.0F - f11, 0.0F + f11, entityItem.height / 2.0F, 0.0F + f11);
            RenderUtil.end3DRender();
            GlStateManager.enableLighting();
            GlStateManager.enableLight(0);
            GlStateManager.enableLight(1);
            GlStateManager.enableColorMaterial();
            RenderUtil.setGlColorRgba(1, 1, 1, 1);
            GL11.glPopMatrix();
            GlStateManager.popMatrix();
            if (Config.isShaderPackLoaded()) {
               Shaders.useProgram(Shaders.activeProgram);
            }
         }
      }

      GlStateManager.popMatrix();
      GlStateManager.disableRescaleNormal();
      GlStateManager.disableBlend();
      this.bindEntityTexture(entityItem);
      if (flag) {
         this.renderManager.renderEngine.getTexture(this.getEntityTexture(entityItem)).restoreLastBlurMipmap();
      }

      super.doRender(entityItem, d, d2, d3, f, f2);
   }

   public ResourceLocation getEntityTexture(EntityItem entityItem) {
      return TextureMap.locationBlocksTexture;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityItem)entity);
   }

   public RenderEntityItem(RenderManager renderManager2, RenderItem renderItem) {
      super(renderManager2);
      this.itemRenderer = renderItem;
      this.shadowSize = 0.15F;
      this.shadowOpaque = 0.75F;
   }

   private int transformItemModel(EntityItem entityItem, double d, double d2, double d3, float f, IBakedModel bakedModel) {
      ItemStack entityItem2 = entityItem.getEntityItem();
      if (entityItem2.getItem() == null) {
         return 0;
      } else {
         boolean isGui3d = bakedModel.isGui3d();
         int modelCount = this.getModelCount(entityItem2);
         float f2 = 0.25F;
         float f3 = MathHelper.sin(((float)entityItem.getAge() + f) / 10.0F + entityItem.hoverStart) * 0.1F + 0.1F;
         float f4 = bakedModel.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y;
         GlStateManager.translate((float)d, (float)d2 + f3 + 0.25F * f4, (float)d3);
         if (isGui3d || this.renderManager.options != null) {
            GlStateManager.rotate((((float)entityItem.getAge() + f) / 20.0F + entityItem.hoverStart) * 57.295776F, 0.0F, 1.0F, 0.0F);
         }

         if (!isGui3d) {
            GlStateManager.translate(-0.0F * (float)(modelCount - 1) * 0.5F, -0.0F * (float)(modelCount - 1) * 0.5F, -0.046875F * (float)(modelCount - 1) * 0.5F);
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         return modelCount;
      }
   }

   @Override
   public void renderName(Entity entity, double d, double d2, double d3) {
      this.renderName2((EntityItem)entity, d, d2, d3);
   }

   public void renderName2(EntityItem entityItem, double d, double d2, double d3) {
      if (Minecraft.isGuiEnabled()
         && entityItem.getEntityItem() != null
         && entityItem.getEntityItem().hasDisplayName()
         && ClientManagers.moduleManager.itemESP.isToggled()
         && ClientManagers.moduleManager.itemESP.renderCustomItemnames.getValue()) {
         if (ItemESP.isItemWhitelisted(entityItem.getEntityItem().getItem())) {
            if (ClientManagers.moduleManager.itemESP.applyNametagsRendering.getValue()) {
               this.renderVanillaNameTag(entityItem, entityItem.getEntityItem().getDisplayName(), d, d2, d3, 64, false);
            } else {
               this.renderLivingLabel(entityItem, entityItem.getEntityItem().getDisplayName(), d, d2, d3, 64);
            }
         }
      }
   }
}
