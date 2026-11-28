package net.augustus.cosmetics.render;

import net.augustus.cosmetics.CosmeticMathHelper;
import net.augustus.cosmetics.model.AbstractCosmeticPart;
import net.augustus.cosmetics.model.AnimatedCosmeticPart;
import net.augustus.cosmetics.model.CosmeticAnimatable;
import net.augustus.cosmetics.model.CosmeticModel;
import net.augustus.cosmetics.model.CosmeticSubModel;
import net.augustus.cosmetics.model.GeoAnimationEvent;
import net.augustus.cosmetics.model.GeoBone;
import net.augustus.cosmetics.model.GeoCube;
import net.augustus.cosmetics.model.GeoQuad;
import net.augustus.cosmetics.model.GeoVector3f;
import net.augustus.cosmetics.model.GeoVertex;
import java.util.ArrayList;
import java.util.List;
import net.labymod.ModelData;
import net.labymod.SettingOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GeoModelRenderer {
   private float ageInTicks;
   private ModelData modelData;
   private static final List EMPTY_EXTRA_DATA = new ArrayList();
   private ModelPlayer playerModel;
   public static GeoMatrixStack MATRIX_STACK;
   private float verticalMotion;
   private float backwardMotion;
   private PlayerCosmeticRenderer playerCosmeticRenderer;
   private CosmeticSubModel[] subModels;
   private CosmeticModel cosmeticModel;
   private float forwardMotion;

   public void updateAnimation(Integer num, CosmeticAnimatable cosmeticAnimatable, float f) {
      GeoAnimationEvent geoAnimationEvent = new GeoAnimationEvent(cosmeticAnimatable, 0.0F, 0.0F, f, false, EMPTY_EXTRA_DATA);
      cosmeticAnimatable.getAnimatedModel().setLivingAnimations2(cosmeticAnimatable, num, geoAnimationEvent);
   }

   private void renderCube(WorldRenderer worldRenderer, GeoCube geoCube, float f, float f2, float f3, float f4) {
      MATRIX_STACK.moveToPivot(geoCube);
      MATRIX_STACK.rotate(geoCube);
      MATRIX_STACK.moveBackFromPivot(geoCube);

      for (GeoQuad geoQuad : geoCube.quads) {
         MATRIX_STACK.transformNormal(geoQuad);
         GeoVector3f transformedNormal = geoQuad.transformedNormal;
         if ((geoCube.size.y == 0.0F || geoCube.size.z == 0.0F) && transformedNormal.getX() < 0.0F) {
            transformedNormal.x *= -1.0F;
         }

         if ((geoCube.size.x == 0.0F || geoCube.size.z == 0.0F) && transformedNormal.getY() < 0.0F) {
            transformedNormal.y *= -1.0F;
         }

         if ((geoCube.size.x == 0.0F || geoCube.size.y == 0.0F) && transformedNormal.getZ() < 0.0F) {
            transformedNormal.z *= -1.0F;
         }


         for (GeoVertex geoVertex : geoQuad.vertices) {
            MATRIX_STACK.transformVertex(geoVertex);
            GeoVector3f transformedPosition = geoVertex.transformedPosition;
            worldRenderer.pos((double)transformedPosition.getX(), (double)transformedPosition.getY(), (double)transformedPosition.getZ())
               .tex((double)geoVertex.textureU, (double)geoVertex.textureV)
               .color(f, f2, f3, f4)
               .normal(transformedNormal.getX(), transformedNormal.getY(), transformedNormal.getZ())
               .endVertex();
         }
      }
   }

   private void renderRecursively(GeoBone geoBone, float f, float f2, float f3, float f4) {
      int subModelIndex = geoBone.subModelIndex;
      if (subModelIndex != -1) {
         CosmeticSubModel cosmeticSubModel = this.subModels[subModelIndex];
         SettingOverlay settingOverlay = this.modelData.model[subModelIndex] != null ? this.modelData.model[subModelIndex] : PlayerCosmeticRenderer.DEFAULT_OVERLAY;
         if (!cosmeticSubModel.isVisible() || !settingOverlay.visible) {
            return;
         }

         MatrixTransformBridge matrixbridge = PlayerCosmeticRenderer.MATRIX_BRIDGE;
         matrixbridge.push();
         ModelPlayer playerModel2 = this.playerModel;
         float f5 = this.playerCosmeticRenderer.applyBodyPartTransform((AbstractCosmeticPart)cosmeticSubModel, playerModel2, true, matrixbridge);
         this.playerCosmeticRenderer.applyPartTransform((AbstractCosmeticPart)cosmeticSubModel, f5, settingOverlay, matrixbridge);
         float verticalMotion2 = this.verticalMotion;
         float backwardMotion2 = this.backwardMotion;
         float forwardMotion2 = this.forwardMotion;
         float ageInTicks2 = this.ageInTicks;
         this.playerCosmeticRenderer.applyEffects((AnimatedCosmeticPart)cosmeticSubModel, ageInTicks2, forwardMotion2, backwardMotion2, verticalMotion2, settingOverlay, matrixbridge);
         if (cosmeticSubModel.hasBoundTextures()) {
            PlayerCosmeticRenderer playerCosmeticRenderer2 = this.playerCosmeticRenderer;
            List boundTextures = cosmeticSubModel.getBoundTextures();
            ModelData modelData2 = this.modelData;
            float ageInTicks3 = this.ageInTicks;
            float forwardMotion3 = this.forwardMotion;
            float backwardMotion3 = this.backwardMotion;
            float verticalMotion3 = this.verticalMotion;
            String name = this.cosmeticModel.getName();
            playerCosmeticRenderer2.renderTextureOverlays(boundTextures, modelData2, (ModelPlayer)null, ageInTicks3, forwardMotion3, backwardMotion3, verticalMotion3, name, false);
            Minecraft.getMinecraft().getTextureManager().bindTexture((ResourceLocation)PlayerCosmeticRenderer.MODEL_TEXTURES.get(this.cosmeticModel.getTextureName()));
         }

         int n = settingOverlay.color != 0 ? settingOverlay.color : cosmeticSubModel.getDefaultColor();
         if (n == 1) {
            n = CosmeticRainbowColorTicker.currentRainbowColor;
         }

         f = (float)(n >> 16 & 0xFF) / 255.0F;
         f2 = (float)(n >> 8 & 0xFF) / 255.0F;
         f3 = (float)(n & 0xFF) / 255.0F;
         f4 = (float)(n >> 24 & 0xFF) / 255.0F;
         if (settingOverlay.illum) {
            GlStateManager.disableLighting();
            Minecraft.getMinecraft().entityRenderer.disableLightmap();
            f4 = (float)((int)((CosmeticMathHelper.sin(this.ageInTicks / 20.0F) + 1.0F) / 2.0F * 64.0F) + 192) / 255.0F;
         }
      }

      MATRIX_STACK.push();
      MATRIX_STACK.translate(geoBone);
      MATRIX_STACK.moveToPivot(geoBone);
      MATRIX_STACK.rotate(geoBone);
      MATRIX_STACK.scale(geoBone);
      MATRIX_STACK.moveBackFromPivot(geoBone);
      if (!geoBone.hidden) {
         WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

         for (GeoCube geoCube : (Iterable<GeoCube>)(geoBone.childCubes)) {
            MATRIX_STACK.push();
            this.renderCube(worldRenderer, geoCube, f, f2, f3, f4);
            MATRIX_STACK.pop();
         }

         Tessellator.getInstance().draw();

         for (GeoBone geoBone2 : (Iterable<GeoBone>)(geoBone.childBones)) {
            this.renderRecursively(geoBone2, f, f2, f3, f4);
         }
      }

      MATRIX_STACK.pop();
      if (subModelIndex != -1) {
         if ((this.modelData.model[subModelIndex] != null ? this.modelData.model[subModelIndex] : PlayerCosmeticRenderer.DEFAULT_OVERLAY).illum) {
            GlStateManager.enableLighting();
            Minecraft.getMinecraft().entityRenderer.enableLightmap();
         }

         PlayerCosmeticRenderer.MATRIX_BRIDGE.pop();
      }
   }

   public void renderModel(
      CosmeticModel cosmeticModel2, PlayerCosmeticRenderer playerCosmeticRenderer2, CosmeticSubModel[] cosmeticSubModels, ModelData modelData2, ModelPlayer modelPlayer, float f, float f2, float f3, float f4
   ) {
      this.playerCosmeticRenderer = playerCosmeticRenderer2;
      this.subModels = cosmeticSubModels;
      this.modelData = modelData2;
      this.cosmeticModel = cosmeticModel2;
      this.playerModel = modelPlayer;
      this.ageInTicks = f;
      this.forwardMotion = f2;
      this.backwardMotion = f3;
      this.verticalMotion = f4;
      GlStateManager.disableCull();
      GlStateManager.enableRescaleNormal();

      for (GeoBone geoBone : (Iterable<GeoBone>)(cosmeticModel2.getGeoModel().topLevelBones)) {
         float f5 = 1.0F;
         float f6 = 1.0F;
         float f7 = 1.0F;
         this.renderRecursively(geoBone, 1.0F, f7, f6, f5);
      }

      GlStateManager.disableRescaleNormal();
      GlStateManager.enableCull();
   }

   static {
      MATRIX_STACK = new GeoMatrixStack();
   }
}
