package net.augustus.cosmetics.render;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.augustus.Augustus;
import net.augustus.cosmetics.CosmeticCategory;
import net.augustus.cosmetics.CosmeticMathHelper;
import net.augustus.cosmetics.CosmeticRenderState;
import net.augustus.cosmetics.CosmeticsConfig;
import net.augustus.cosmetics.MoreCosmeticsCore;
import net.augustus.cosmetics.model.AbstractCosmeticPart;
import net.augustus.cosmetics.model.AnimatedCosmeticPart;
import net.augustus.cosmetics.model.CosmeticAnimatable;
import net.augustus.cosmetics.model.CosmeticAnimationTrigger;
import net.augustus.cosmetics.model.CosmeticAxis;
import net.augustus.cosmetics.model.CosmeticBodyPart;
import net.augustus.cosmetics.model.CosmeticEffect;
import net.augustus.cosmetics.model.CosmeticEffectType;
import net.augustus.cosmetics.model.CosmeticItemModel;
import net.augustus.cosmetics.model.CosmeticModel;
import net.augustus.cosmetics.model.CosmeticSubModel;
import net.augustus.cosmetics.model.CosmeticTextureModel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.labymod.ModelData;
import net.labymod.SettingOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PlayerCosmeticRenderer extends AbstractCosmeticRenderer {
   public static final HashMap MODEL_TEXTURES = new HashMap();
   public static final float MODEL_SCALE = 0.0625F;
   public static final SettingOverlay DEFAULT_OVERLAY = new SettingOverlay();
   private CosmeticTextureManager cosmeticTextureManager;
   private LayerRenderer vanillaCapeLayer;
   public static final HashMap ITEM_STACK_CACHE = new HashMap();
   private boolean cloaksInitialized;
   private GeoModelRenderer geoModelRenderer;
   public static final MatrixTransformBridge MATRIX_BRIDGE = GlMatrixTransformBridge.getInstance();
   private CosmeticTextureManager cloakTextureManager;
   private long lastRenderTime;
   private CloakRenderer cloakRenderer;

   public void registerRenderLayers() {
      Map map = null;

      try {
         map = Minecraft.getMinecraft().getRenderManager().getSkinMap();

         for (String str : (Iterable<String>)(map.keySet())) {
            RenderPlayer renderPlayer = (RenderPlayer)map.get(str);
            List layerRenderers = renderPlayer.getLayerRenderers();
            layerRenderers.add(0, new CosmeticLayerRenderer(renderPlayer, this, str.equals("slim")));
            int n = -1;

            for (int i = 0; i < layerRenderers.size(); i++) {
               if (((LayerRenderer)layerRenderers.get(i)) instanceof LayerCape) {
                  n = i;
               }
            }

            if (n != -1) {
               this.vanillaCapeLayer = (LayerRenderer)layerRenderers.remove(n);
            }

            this.playerRenderLayers.put(str, layerRenderers);
         }
      } catch (Throwable e) {
         MoreCosmeticsCore.logError(e);
      }
   }

public float renderCosmetics(AbstractClientPlayer clientPlayer, float f, float f2, float f3, ModelPlayer modelPlayer, boolean flag, float f4) {
      CosmeticRenderState.ageInTicks = f3;
      CosmeticRenderState.partialTicks = f4;
      if (!clientPlayer.isInvisible() && !clientPlayer.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
         if (this.vanillaCapeLayer != null) {
            this.vanillaCapeLayer.doRenderLayer(clientPlayer, f, f2, f4, f3, 0.0F, 0.0F, 0.0625F);
         }

         float f5 = 0.0F;
         Augustus.getInstance().getModuleManager().cosmetics.applyPlayerCosmetics(clientPlayer);
         if (clientPlayer.hasCosmetics()) {
            if (clientPlayer != Minecraft.getMinecraft().thePlayer && Augustus.getInstance().getModuleManager().cosmetics.hideCosmeticsOfOtherUsers.getValue()) {
               return 0.0F;
            }

            try {
               double d = clientPlayer.prevChasingPosX + (clientPlayer.chasingPosX - clientPlayer.prevChasingPosX) * (double)f4 - (clientPlayer.prevPosX + (clientPlayer.posX - clientPlayer.prevPosX) * (double)f4);
               double d2 = clientPlayer.prevChasingPosY + (clientPlayer.chasingPosY - clientPlayer.prevChasingPosY) * (double)f4 - (clientPlayer.prevPosY + (clientPlayer.posY - clientPlayer.prevPosY) * (double)f4);
               double d3 = clientPlayer.prevChasingPosZ + (clientPlayer.chasingPosZ - clientPlayer.prevChasingPosZ) * (double)f4 - (clientPlayer.prevPosZ + (clientPlayer.posZ - clientPlayer.prevPosZ) * (double)f4);
               float f6 = clientPlayer.prevRenderYawOffset + (clientPlayer.renderYawOffset - clientPlayer.prevRenderYawOffset) * f4;
               double d4 = (double)CosmeticMathHelper.sin(f6 * 3.1415927F / 180.0F);
               double d5 = (double)(-CosmeticMathHelper.cos(f6 * 3.1415927F / 180.0F));
               float f7 = (float)d2 * 10.0F;
               f7 = CosmeticMathHelper.clampFloat(f7, -6.0F, 32.0F);
               float f8 = (float)(d * d4 + d3 * d5) * 100.0F;
               float f9 = CosmeticMathHelper.clampFloat(f8, 0.0F, 180.0F);
               float f10 = CosmeticMathHelper.clampFloat(f8, -180.0F, 0.0F);
               MATRIX_BRIDGE.push();
               if (clientPlayer.isSneaking()) {
                  float f11 = 0.0F;
                  float f12 = 0.2F;
                  float f13 = 0.0F;
                  MATRIX_BRIDGE.translate(f13, f12, f11);
               }

               for (Integer num : (Iterable<Integer>)(clientPlayer.getCosmetics().keySet())) {
                  ModelData modelData = (ModelData)clientPlayer.getCosmetics().get(num);
                  if (modelData != null && modelData.isActive()) {
                     CosmeticModel cosmeticModel = (CosmeticModel)this.modelLoader.getModelsById().get(num);
                     if (cosmeticModel != null) {
                        if (CosmeticsConfig.getInstance().hideWithArmor) {
                           int armorSlot = ((AbstractCosmeticPart)cosmeticModel).getBodyPart().getArmorSlot();
                           if (armorSlot != -1 && clientPlayer.getCurrentArmor(armorSlot) != null) {
                              continue;
                           }
                        }

                        float height = cosmeticModel.getHeight();
                        if (((AbstractCosmeticPart)cosmeticModel).getBodyPart() == CosmeticBodyPart.HEAD || ((AbstractCosmeticPart)cosmeticModel).getBodyPart() == CosmeticBodyPart.ABOVE_HEAD) {
                           height += modelData.y;
                        }

                        if (f5 < height) {
                           f5 = height;
                        }

                        this.renderCosmeticModel(clientPlayer, cosmeticModel, modelData, f, f2, f3, f9, f10, f7, modelPlayer, flag);
                     }
                  }
               }

               MATRIX_BRIDGE.pop();
            } catch (Throwable e) {
               MoreCosmeticsCore.logError(e);
            }
         }

         return f5;
      } else {
         return 0.0F;
      }
   }

   public void renderTextureOverlays(List list, ModelData modelData, ModelPlayer modelPlayer, float f, float f2, float f3, float f4, String str, boolean flag) {
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.enableBlend();

      for (CosmeticTextureModel cosmeticTextureModel : (Iterable<CosmeticTextureModel>)(list)) {
         if (flag != cosmeticTextureModel.isBoundToSubModel()) {
            SettingOverlay settingOverlay = (modelData.texture != null && modelData.texture.length > cosmeticTextureModel.getIndex()) ? modelData.texture[cosmeticTextureModel.getIndex()] : DEFAULT_OVERLAY;
            if (settingOverlay.visible) {
               String str2 = settingOverlay.url == null ? cosmeticTextureModel.getUrl() : settingOverlay.url;
               if (str2 != null && str2.length() >= 3) {
                  String str3 = str2 + str + cosmeticTextureModel.getIndex();
                  CosmeticBufferedTexture orLoadTexture = this.cosmeticTextureManager.getOrLoadTexture(str3, str2, cosmeticTextureModel.getMaskImage());
                  if (orLoadTexture != null) {
                     MATRIX_BRIDGE.push();
                     MatrixTransformBridge matrixTransformBridge = MATRIX_BRIDGE;
                     float f5 = this.applyBodyPartTransform((AbstractCosmeticPart)cosmeticTextureModel, modelPlayer, true, matrixTransformBridge);
                     MatrixTransformBridge matrixTransformBridge2 = MATRIX_BRIDGE;
                     this.applyPartTransform((AbstractCosmeticPart)cosmeticTextureModel, f5, settingOverlay, matrixTransformBridge2);
                     MatrixTransformBridge matrixTransformBridge3 = MATRIX_BRIDGE;
                     this.applyEffects((AnimatedCosmeticPart)cosmeticTextureModel, f, f2, f3, f4, settingOverlay, matrixTransformBridge3);
                     Minecraft.getMinecraft().getTextureManager().bindTexture(orLoadTexture.getTextureLocation());
                     float f6 = -0.5F;
                     float f7 = -0.5F;
                     float f8 = -0.5F;
                     MATRIX_BRIDGE.scale(f8, f7, f6);
                     int n = settingOverlay.color == 0 ? cosmeticTextureModel.getColor() : settingOverlay.color;
                     CosmeticOverlayDrawer.drawScaledOverlay(-0.5F, -0.5F, 256.0F, 256.0F, orLoadTexture.getAspectRatio(), 1.0F, cosmeticTextureModel.getOpacity(), n);
                     MATRIX_BRIDGE.pop();
                  }
               }
            }
         }
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableBlend();
   }

   public void renderCosmeticModel(
      AbstractClientPlayer clientPlayer,
      CosmeticModel cosmeticModel,
      ModelData modelData,
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      float f6,
      ModelPlayer modelPlayer,
      boolean flag
   ) {
      GlStateManager.enableAlpha();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      MATRIX_BRIDGE.push();
      MatrixTransformBridge matrixTransformBridge = MATRIX_BRIDGE;
      float f7 = this.applyBodyPartTransform((AbstractCosmeticPart)cosmeticModel, modelPlayer, false, matrixTransformBridge);
      float f8 = 180.0F;
      MATRIX_BRIDGE.rotateZ(f8);
      if (modelPlayer != null) {
         MatrixTransformBridge matrixTransformBridge2 = MATRIX_BRIDGE;
         this.applyPartTransform((AbstractCosmeticPart)cosmeticModel, f7, (SettingOverlay)modelData, matrixTransformBridge2);
      }

      if (modelData.resize) {
         MatrixTransformBridge matrixTransformBridge3 = MATRIX_BRIDGE;
         float f9;
         float f10 = f9 = cosmeticModel.getResizeFactor();
         matrixTransformBridge3.scale(cosmeticModel.getResizeFactor(), f10, f9);
      }

      float[] adjustment = cosmeticModel.getAdjustment();
      if (!flag && adjustment != null && adjustment.length > 5) {
         float f11 = 0.0F;
         if (cosmeticModel.getCategoryId() == CosmeticCategory.SHIELD.getId() && clientPlayer.getCurrentArmor(2) != null) {
            f11 = 0.05F;
         }

         float f12 = adjustment[0];
         float f13 = adjustment[1];
         float f14 = adjustment[2] + f11;
         float f15 = f13;
         MATRIX_BRIDGE.scale(f12, f15, f14);
         f13 = adjustment[3];
         float f17 = adjustment[4];
         float f18 = adjustment[5] - f11;
         float f20 = f13;
         MATRIX_BRIDGE.translate(f20, f17, f18);
      }

      MatrixTransformBridge matrixTransformBridge4 = MATRIX_BRIDGE;
      this.applyEffects((AnimatedCosmeticPart)cosmeticModel, f3, f4, f5, f6, (SettingOverlay)modelData, matrixTransformBridge4);
      if (cosmeticModel.hasTextureModels()) {
         this.renderTextureOverlays(cosmeticModel.getTextureModels(), modelData, modelPlayer, f3, f4, f5, f6, cosmeticModel.getName(), true);
      }

      if (cosmeticModel.hasItemModels() && clientPlayer != null) {
         this.renderItemModels(clientPlayer, cosmeticModel, modelData, modelPlayer, f3, f4, f5, f6);
      }

      Minecraft minecraft = Minecraft.getMinecraft();
      String textureName = cosmeticModel.getTextureName();
      if (textureName != null) {
         ResourceLocation location = (ResourceLocation)MODEL_TEXTURES.get(textureName);
         if (location == null) {
            MODEL_TEXTURES.put(textureName, location = new ResourceLocation("morecosmetics/" + textureName));
         }

         minecraft.getTextureManager().bindTexture(location);
      }

      MATRIX_BRIDGE.push();
      if (cosmeticModel.hasAnimation()) {
         if (cosmeticModel.getAnimatable().hasDefaultAnimation()) {
            CosmeticAnimationTrigger idle = CosmeticAnimationTrigger.IDLE;
            if (clientPlayer.lastTickPosX - clientPlayer.posX != 0.0 || clientPlayer.lastTickPosZ - clientPlayer.posZ != 0.0) {
               idle = CosmeticAnimationTrigger.MOVING;
            }

            if (clientPlayer.isSneaking()) {
               idle = CosmeticAnimationTrigger.SNEAKING;
            }

            cosmeticModel.getAnimatable().setTriggerAnimation(idle);
         }

         this.geoModelRenderer.updateAnimation(cosmeticModel.getId(), cosmeticModel.getAnimatable(), CosmeticRenderState.partialTicks);
      }

      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      this.geoModelRenderer.renderModel(cosmeticModel, this, cosmeticModel.getSubModels(), modelData, modelPlayer, f3, f4, f5, f6);
      GlStateManager.disableBlend();
      MATRIX_BRIDGE.pop();
      MATRIX_BRIDGE.pop();
   }

   public PlayerCosmeticRenderer() {
       super();
      this.cosmeticTextureManager = CosmeticTextureManager.getInstance();
      this.cloakTextureManager = new CosmeticTextureManager("mcloaks");
      this.cloakRenderer = new CloakRenderer();
      this.geoModelRenderer = new GeoModelRenderer();
   }

   private void transformToModelPart(ModelRenderer modelRenderer, boolean flag, MatrixTransformBridge matrixTransformBridge) {
      if (flag) {
         matrixTransformBridge.rotateZ(180.0F);
      }

      float f2 = modelRenderer.rotationPointX * 0.0625F;
      matrixTransformBridge.translate(f2, modelRenderer.rotationPointY * 0.0625F, modelRenderer.rotationPointZ * 0.0625F);
      float f7 = modelRenderer.rotateAngleX * 57.295776F;
      f2 = modelRenderer.rotateAngleY * 57.295776F;
      matrixTransformBridge.rotate(f7, f2, modelRenderer.rotateAngleZ * 57.295776F);
      if (flag) {
         matrixTransformBridge.rotateZ(180.0F);
      }
   }

   public void applyEffects(AnimatedCosmeticPart animatedCosmeticPart, float f, float f2, float f3, float f4, SettingOverlay settingOverlay, MatrixTransformBridge matrixTransformBridge) {
      if (animatedCosmeticPart.hasEffects() && settingOverlay.animation) {

         for (CosmeticEffectType cosmeticEffectType : (Iterable<CosmeticEffectType>)(animatedCosmeticPart.getEffects().keySet())) {
            CosmeticEffect cosmeticEffect = (CosmeticEffect)animatedCosmeticPart.getEffects().get(cosmeticEffectType);
            CosmeticAxis axis = cosmeticEffect.getAxis();
            float f5 = cosmeticEffect.getMultiplier() * settingOverlay.mulitply;
            switch (cosmeticEffectType) {
               case BOUNCE:
                  float f6 = f5 * CosmeticMathHelper.cos(f / 10.0F) / 20.0F;
                  matrixTransformBridge.translate((float)axis.x * f6, (float)axis.y * f6, (float)axis.z * f6);
                  break;
               case ROTATE:
                  float f12 = f5 * f;
                  matrixTransformBridge.rotate((float)axis.x * f12, (float)axis.y * f12, (float)axis.z * f12);
                  break;
               case FORWARD:
                  float f18 = f5 * (f2 / 3.0F);
                  matrixTransformBridge.rotate((float)axis.x * f18, (float)axis.y * f18, (float)axis.z * f18);
                  break;
               case BACKWARD:
                  float f24 = f5 * (f3 / 3.0F);
                  matrixTransformBridge.rotate((float)axis.x * f24, (float)axis.y * f24, (float)axis.z * f24);
                  break;
               case RESIZE:
                  float f30 = 1.0F + f5 * CosmeticMathHelper.cos(f / 10.0F) / 20.0F;
                  matrixTransformBridge.scale(f30, f30, f30);
               case MOTION:
            }
         }
      }
   }

   public float applyBodyPartTransform(AbstractCosmeticPart cosmeticPart, ModelPlayer modelPlayer, boolean flag, MatrixTransformBridge matrixTransformBridge) {
      float f = 0.0F;
      if (modelPlayer != null) {
         switch (cosmeticPart.getBodyPart()) {
            case FREE:
            default:
               break;
            case BODY:
               this.transformToModelPart(modelPlayer.bipedBody, flag, matrixTransformBridge);
               f = -0.3F;
               break;
            case HEAD:
               this.transformToModelPart(modelPlayer.bipedHead, flag, matrixTransformBridge);
               f = 0.5F;
               break;
            case ABOVE_HEAD:
               f = 0.75F;
               break;
            case RIGHT_ARM:
               this.transformToModelPart(modelPlayer.bipedRightArm, flag, matrixTransformBridge);
               f = -0.25F;
               break;
            case LEFT_ARM:
               this.transformToModelPart(modelPlayer.bipedLeftArm, flag, matrixTransformBridge);
               f = -0.25F;
               break;
            case RIGHT_LEG:
               this.transformToModelPart(modelPlayer.bipedRightLeg, flag, matrixTransformBridge);
               f = -0.35F;
               break;
            case LEFT_LEG:
               this.transformToModelPart(modelPlayer.bipedLeftLeg, flag, matrixTransformBridge);
               f = -0.35F;
         }
      }

      return f;
   }

   private void renderItemModels(AbstractClientPlayer clientPlayer, CosmeticModel cosmeticModel, ModelData modelData, ModelPlayer modelPlayer, float f, float f2, float f3, float f4) {

      for (int i = 0; i < cosmeticModel.getItemModels().size(); i++) {
         CosmeticItemModel cosmeticItemModel = (CosmeticItemModel)cosmeticModel.getItemModels().get(i);
         SettingOverlay settingOverlay = (modelData.item != null && modelData.item.length > i) ? modelData.item[i] : DEFAULT_OVERLAY;
         if (settingOverlay.visible) {
            int n = settingOverlay.id == 0 ? cosmeticItemModel.getItemId() : settingOverlay.id;
            ItemStack stack = (ItemStack)ITEM_STACK_CACHE.get(n);
            if (stack == null && !ITEM_STACK_CACHE.containsKey(cosmeticItemModel.getItemId())) {
               ITEM_STACK_CACHE.put(cosmeticItemModel.getItemId(), stack = new ItemStack(Item.getItemById(cosmeticItemModel.getItemId())));
            }

            if (stack != null) {
               MATRIX_BRIDGE.push();
               MatrixTransformBridge matrixTransformBridge = MATRIX_BRIDGE;
               float f5 = this.applyBodyPartTransform((AbstractCosmeticPart)cosmeticItemModel, modelPlayer, true, matrixTransformBridge);
               MatrixTransformBridge matrixTransformBridge2 = MATRIX_BRIDGE;
               this.applyPartTransform((AbstractCosmeticPart)cosmeticItemModel, f5, settingOverlay, matrixTransformBridge2);
               MatrixTransformBridge matrixTransformBridge3 = MATRIX_BRIDGE;
               this.applyEffects((AnimatedCosmeticPart)cosmeticItemModel, f, f2, f3, f4, settingOverlay, matrixTransformBridge3);
               float f6 = 0.5F;
               float f7 = 0.5F;
               float f8 = 0.5F;
               MATRIX_BRIDGE.scale(f8, f7, f6);
               Minecraft.getMinecraft().getItemRenderer().renderItem(clientPlayer, stack, ItemCameraTransforms.TransformType.FIXED);
               MATRIX_BRIDGE.pop();
            }
         }
      }
   }

   public void applyPartTransform(AbstractCosmeticPart cosmeticPart, float f, SettingOverlay settingOverlay, MatrixTransformBridge matrixTransformBridge) {
      matrixTransformBridge.translate(cosmeticPart.getOffsetX() + settingOverlay.x, cosmeticPart.getOffsetY() + f + settingOverlay.y, cosmeticPart.getOffsetZ() + settingOverlay.z);
      matrixTransformBridge.rotateY(cosmeticPart.getYaw() + settingOverlay.yaw);
      matrixTransformBridge.rotateX(cosmeticPart.getPitch() + settingOverlay.pitch);
      matrixTransformBridge.rotateZ(cosmeticPart.getRoll() + settingOverlay.roll);
      float f11 = cosmeticPart.getScale() * settingOverlay.scale;
      float f12;
      matrixTransformBridge.scale(f11, f12 = cosmeticPart.getScale() * settingOverlay.scale, f12);
   }

   private boolean setPlayerCapeTexture(UUID uuid, ResourceLocation location) {

      try {
         Minecraft.getMinecraft().getNetHandler().getPlayerInfo(uuid).setLocationCape(location);
         return true;
      } catch (Exception e) {
         MoreCosmeticsCore.logError(e);
         return false;
      }
   }

}
