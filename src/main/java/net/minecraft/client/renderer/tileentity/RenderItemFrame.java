package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class RenderItemFrame extends Render {
   private static final ResourceLocation mapBackgroundTextures = new ResourceLocation("textures/map/map_background.png");
   private final Minecraft mc = Minecraft.getMinecraft();
   private final ModelResourceLocation itemFrameModel = new ModelResourceLocation("item_frame", "normal");
   private final ModelResourceLocation mapModel = new ModelResourceLocation("item_frame", "map");
   private RenderItem itemRenderer;
   private static double itemRenderDistanceSq = 4096.0;

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityItemFrame)entity);
   }

   private boolean isRenderItem(EntityItemFrame entityItemFrame) {
      if (Shaders.isShadowPass) {
         return false;
      } else {
         if (!Config.zoomMode) {
            Entity renderViewEntity = this.mc.getRenderViewEntity();
            if (entityItemFrame.getDistanceSq(renderViewEntity.posX, renderViewEntity.posY, renderViewEntity.posZ) > itemRenderDistanceSq) {
               return false;
            }
         }

         return true;
      }
   }

   public void doRender(EntityItemFrame entityItemFrame, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      BlockPos hangingPosition = entityItemFrame.getHangingPosition();
      double d4 = (double)hangingPosition.getX() - entityItemFrame.posX + d;
      double d5 = (double)hangingPosition.getY() - entityItemFrame.posY + d2;
      double d6 = (double)hangingPosition.getZ() - entityItemFrame.posZ + d3;
      GlStateManager.translate(d4 + 0.5, d5 + 0.5, d6 + 0.5);
      GlStateManager.rotate(180.0F - entityItemFrame.rotationYaw, 0.0F, 1.0F, 0.0F);
      this.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      BlockRendererDispatcher blockRendererDispatcher = this.mc.getBlockRendererDispatcher();
      ModelManager modelManager = blockRendererDispatcher.getBlockModelShapes().getModelManager();
      IBakedModel bakedModel;
      if (entityItemFrame.getDisplayedItem() != null && entityItemFrame.getDisplayedItem().getItem() == Items.filled_map) {
         bakedModel = modelManager.getModel(this.mapModel);
      } else {
         bakedModel = modelManager.getModel(this.itemFrameModel);
      }

      GlStateManager.pushMatrix();
      GlStateManager.translate(-0.5F, -0.5F, -0.5F);
      blockRendererDispatcher.getBlockModelRenderer().renderModelBrightnessColor(bakedModel, 1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
      GlStateManager.translate(0.0F, 0.0F, 0.4375F);
      this.renderItem(entityItemFrame);
      GlStateManager.popMatrix();
      this.renderName(
         entityItemFrame,
         d + (double)((float)entityItemFrame.facingDirection.getFrontOffsetX() * 0.3F),
         d2 - 0.25,
         d3 + (double)((float)entityItemFrame.facingDirection.getFrontOffsetZ() * 0.3F)
      );
   }

   private void renderItem(EntityItemFrame entityItemFrame) {
      ItemStack displayedItem = entityItemFrame.getDisplayedItem();
      if (displayedItem != null) {
         if (!this.isRenderItem(entityItemFrame)) {
            return;
         }

         if (!Config.zoomMode) {
            EntityPlayerSP thePlayer = this.mc.thePlayer;
            if (entityItemFrame.getDistanceSq(thePlayer.posX, thePlayer.posY, thePlayer.posZ) > 4096.0) {
               return;
            }
         }

         EntityItem entityItem = new EntityItem(entityItemFrame.entityWorld, 0.0, 0.0, 0.0, displayedItem);
         Item item = entityItem.getEntityItem().getItem();
         entityItem.getEntityItem().stackSize = 1;
         entityItem.hoverStart = 0.0F;
         GlStateManager.pushMatrix();
         GlStateManager.disableLighting();
         int rotation = entityItemFrame.getRotation();
         if (item instanceof ItemMap) {
            rotation = rotation % 4 * 2;
         }

         GlStateManager.rotate((float)rotation * 360.0F / 8.0F, 0.0F, 0.0F, 1.0F);
         if (item instanceof ItemMap) {
            this.renderManager.renderEngine.bindTexture(mapBackgroundTextures);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            float f = 0.0078125F;
            GlStateManager.scale(f, f, f);
            GlStateManager.translate(-64.0F, -64.0F, 0.0F);
            MapData mapData = Items.filled_map.getMapData(entityItem.getEntityItem(), entityItemFrame.entityWorld);
            GlStateManager.translate(0.0F, 0.0F, -1.0F);
            if (mapData != null) {
               this.mc.entityRenderer.getMapItemRenderer().renderMap(mapData, true);
            }
         } else {
            TextureAtlasSprite textureAtlasSprite = null;
            if (item == Items.compass) {
               textureAtlasSprite = this.mc.getTextureMapBlocks().getAtlasSprite(TextureCompass.locationSprite);
               this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
               if (textureAtlasSprite instanceof TextureCompass) {
                  TextureCompass textureCompass = (TextureCompass)textureAtlasSprite;
                  double currentAngle = textureCompass.currentAngle;
                  double angleDelta = textureCompass.angleDelta;
                  textureCompass.currentAngle = 0.0;
                  textureCompass.angleDelta = 0.0;
                  textureCompass.updateCompass(
                     entityItemFrame.entityWorld,
                     entityItemFrame.posX,
                     entityItemFrame.posZ,
                     (double)MathHelper.wrapAngleTo180_float((float)(180 + entityItemFrame.facingDirection.getHorizontalIndex() * 90)),
                     false,
                     true
                  );
                  textureCompass.currentAngle = currentAngle;
                  textureCompass.angleDelta = angleDelta;
               } else {
                  textureAtlasSprite = null;
               }
            }

            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            if (!this.itemRenderer.shouldRenderItemIn3D(entityItem.getEntityItem()) || item instanceof ItemSkull) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }

            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            this.itemRenderer.renderItem(entityItem.getEntityItem(), ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();
            if (textureAtlasSprite != null && textureAtlasSprite.getFrameCount() > 0) {
               textureAtlasSprite.updateAnimation();
            }
         }

         GlStateManager.enableLighting();
         GlStateManager.popMatrix();
      }
   }

   public ResourceLocation getEntityTexture(EntityItemFrame entityItemFrame) {
      return null;
   }

   @Override
   public void renderName(Entity entity, double d, double d2, double d3) {
      this.renderName((EntityItemFrame)entity, d, d2, d3);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityItemFrame)entity, d, d2, d3, f, f2);
   }

   public void renderName(EntityItemFrame entityItemFrame, double d, double d2, double d3) {
      if (Minecraft.isGuiEnabled() && entityItemFrame.getDisplayedItem() != null && entityItemFrame.getDisplayedItem().hasDisplayName() && this.renderManager.pointedEntity == entityItemFrame) {
         float f = 1.6F;
         float f2 = 0.016666668F * f;
         double distanceSqToEntity = entityItemFrame.getDistanceSqToEntity(this.renderManager.livingPlayer);
         float f3 = entityItemFrame.isSneaking() ? 32.0F : 64.0F;
         if (distanceSqToEntity < (double)(f3 * f3)) {
            String displayName = entityItemFrame.getDisplayedItem().getDisplayName();
            if (entityItemFrame.isSneaking()) {
               FontRenderer fontRenderer = this.getFontRendererFromRenderManager();
               GlStateManager.pushMatrix();
               GlStateManager.translate((float)d + 0.0F, (float)d2 + entityItemFrame.height + 0.5F, (float)d3);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
               GlStateManager.scale(-f2, -f2, f2);
               GlStateManager.disableLighting();
               GlStateManager.translate(0.0F, 0.25F / f2, 0.0F);
               GlStateManager.depthMask(false);
               GlStateManager.enableBlend();
               GlStateManager.blendFunc(770, 771);
               Tessellator instance = Tessellator.getInstance();
               WorldRenderer worldRenderer = instance.getWorldRenderer();
               int n = fontRenderer.getStringWidth(displayName) / 2;
               GlStateManager.disableTexture2D();
               worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
               worldRenderer.pos((double)(-n - 1), -1.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               worldRenderer.pos((double)(-n - 1), 8.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               worldRenderer.pos((double)(n + 1), 8.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               worldRenderer.pos((double)(n + 1), -1.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               instance.draw();
               GlStateManager.enableTexture2D();
               GlStateManager.depthMask(true);
               fontRenderer.drawString(displayName, -fontRenderer.getStringWidth(displayName) / 2, 0, 553648127);
               GlStateManager.enableLighting();
               GlStateManager.disableBlend();
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.popMatrix();
            } else {
               this.renderLivingLabel(entityItemFrame, displayName, d, d2, d3, 64);
            }
         }
      }
   }

   public static void updateItemRenderDistance() {
      Minecraft mc2 = Config.getMc();
      double d = (double)Config.limit3(mc2.gameSettings.gammaSetting, 1.0F, 120.0F);
      double d2 = Math.max(6.0 * (double)mc2.displayHeight / d, 16.0);
      itemRenderDistanceSq = d2 * d2;
   }

   public RenderItemFrame(RenderManager renderManager2, RenderItem renderItem) {
      super(renderManager2);
      this.itemRenderer = renderItem;
   }
}
