package net.minecraft.client.renderer.entity;

import com.google.common.collect.Lists;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.List;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.events.listeners.RenderEntityListener;
import net.augustus.Augustus;
import net.augustus.events.EventEntityColor;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.EntityColorListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.optifine.EmissiveTextures;
import net.optifine.config.Config;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.shaders.Shaders;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public abstract class RendererLivingEntity extends Render {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);
   public ModelBase mainModel;
   public FloatBuffer brightnessBuffer = GLAllocation.createDirectFloatBuffer(4);
   public List layerRenderers = Lists.newArrayList();
   public boolean renderOutlines = false;
   public static float NAME_TAG_RANGE = 64.0F;
   public static float NAME_TAG_RANGE_SNEAK = 32.0F;
   public EntityLivingBase renderEntity;
   public float renderLimbSwing;
   public float renderLimbSwingAmount;
   public float renderAgeInTicks;
   public float renderHeadYaw;
   public float renderHeadPitch;
   public float renderScaleFactor;
   public float renderPartialTicks;
   private boolean renderModelPushMatrix;
   private boolean renderLayersPushMatrix;
   public static final boolean ANIMATE_MODEL_LIVING = Boolean.getBoolean("animate.model.living");

   public float interpolateRotation(float f, float f2, float f3) {
      float f4 = f2 - f;

      while (f4 < -180.0F) {
         f4 += 360.0F;
      }

      while (f4 >= 180.0F) {
         f4 -= 360.0F;
      }

      return f + f3 * f4;
   }

   public List getLayerRenderers() {
      return this.layerRenderers;
   }

   public boolean removeLayer(LayerRenderer layerRenderer) {
      return this.layerRenderers.remove(layerRenderer);
   }

   public boolean setScoreTeamColor(EntityLivingBase entity) {
      int n = 16777215;
      if (entity instanceof EntityPlayer) {
         ScorePlayerTeam team = (ScorePlayerTeam)entity.getTeam();
         if (team != null) {
            String formatFromString = FontRenderer.getFormatFromString(team.getColorPrefix());
            if (formatFromString.length() >= 2) {
               n = this.getFontRendererFromRenderManager().getColorCode(formatFromString.charAt(1));
            }
         }
      }

      float f = (float)(n >> 16 & 0xFF) / 255.0F;
      float f2 = (float)(n >> 8 & 0xFF) / 255.0F;
      float f3 = (float)(n & 0xFF) / 255.0F;
      GlStateManager.disableLighting();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      float f4 = 1.0F;
      if (ClientManagers.moduleManager.esp.isToggled()) {
         if (ClientManagers.moduleManager.esp.vanillaHitColorBool.getValue() && entity.hurtTime > 0) {
            int colorInt = ClientManagers.moduleManager.esp.hitColor5.getColorInt((byte)0);
            f = ColorUtil.getRedFloat(colorInt);
            f2 = ColorUtil.getGreenFloat(colorInt);
            f3 = ColorUtil.getBlueFloat(colorInt);
         } else if (!ClientManagers.moduleManager.esp.vanillaTeamColor.getValue()) {
            int colorInt2 = ClientManagers.moduleManager.esp.vanillaColor.getColorInt((byte)0);
            f = ColorUtil.getRedFloat(colorInt2);
            f2 = ColorUtil.getGreenFloat(colorInt2);
            f3 = ColorUtil.getBlueFloat(colorInt2);
         }
      }

      GlStateManager.color(f, f2, f3, f4);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      return true;
   }

   public boolean setBrightness(EntityLivingBase entity, float f, boolean flag) {
      if (Config.isShaderPackLoaded()) {
         Shaders.setEntityColor(0.0F, 0.0F, 0.0F, 0.0F);
      }

      float f2 = 0.3F;
      float f3 = 0.0F;
      float f4 = 0.0F;
      EventEntityColor event = EntityColorListener.onEntityColor(entity, 1.0F, f4, f3, f2);
      int colorMultiplier = this.getColorMultiplier(entity, entity.getBrightness(f), f);
      boolean flag2 = (colorMultiplier >> 24 & 0xFF) > 0;
      boolean flag3 = entity.hurtTime > 0 || entity.deathTime > 0;
      if (!flag2 && !flag3 && !event.isOverrideColor()) {
         return false;
      } else if (!flag2 && !flag && !event.isOverrideColor()) {
         return false;
      } else {
         GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
         GlStateManager.enableTexture2D();
         GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
         GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
         GlStateManager.enableTexture2D();
         GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, OpenGlHelper.GL_INTERPOLATE);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE2_RGB, OpenGlHelper.GL_CONSTANT);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND2_RGB, 770);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
         ((Buffer)this.brightnessBuffer).position(0);
         if (!flag3 && !event.isOverrideColor()) {
            float f6 = (float)(colorMultiplier >> 24 & 0xFF) / 255.0F;
            float f7 = (float)(colorMultiplier >> 16 & 0xFF) / 255.0F;
            float f8 = (float)(colorMultiplier >> 8 & 0xFF) / 255.0F;
            float f9 = (float)(colorMultiplier & 0xFF) / 255.0F;
            this.brightnessBuffer.put(f7);
            this.brightnessBuffer.put(f8);
            this.brightnessBuffer.put(f9);
            this.brightnessBuffer.put(1.0F - f6);
            if (Config.isShaderPackLoaded()) {
               Shaders.setEntityColor(f7, f8, f9, 1.0F - f6);
            }
         } else {
            this.brightnessBuffer.put(event.getRed());
            this.brightnessBuffer.put(event.getGreen());
            this.brightnessBuffer.put(event.getBlue());
            this.brightnessBuffer.put(event.getAlpha());
            if (Config.isShaderPackLoaded()) {
               Shaders.setEntityColor(event.getRed(), event.getGreen(), event.getBlue(), event.getAlpha());
            }
         }

         ((Buffer)this.brightnessBuffer).flip();
         GL11.glTexEnv(8960, 8705, this.brightnessBuffer);
         GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
         GlStateManager.enableTexture2D();
         GlStateManager.bindTexture(TEXTURE_BRIGHTNESS.getGlTextureId());
         GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_PREVIOUS);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.lightmapTexUnit);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
         GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
         GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
         return true;
      }
   }

   public float[] setupModelPose(ModelBase modelBase, EntityLivingBase entity, float f, double d, double d2, double d3) {
      try {
         modelBase.isChild = entity.isChild();
         modelBase.swingProgress = this.getSwingProgress(entity, f);
         modelBase.isRiding = entity.isRiding();
         if (modelBase instanceof ModelBiped) {
            ModelBiped modelBiped = (ModelBiped)modelBase;
            modelBiped.isSneak = entity.isSneaking();
            ItemStack stack = entity instanceof EntityPlayerSP ? SilentSlotManager.heldItemStack : entity.getHeldItem();
            if (stack == null) {
               modelBiped.heldItemRight = 0;
            } else {
               modelBiped.heldItemRight = 1;
               if (entity instanceof EntityPlayer && ((EntityPlayer)entity).getItemInUseCount() > 0) {
                  EnumAction itemUseAction = stack.getItemUseAction();
                  if (itemUseAction == EnumAction.BLOCK) {
                     modelBiped.heldItemRight = 3;
                  } else if (itemUseAction == EnumAction.BOW) {
                     modelBiped.aimedBow = true;
                  }
               }
            }
         }

         float f2 = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, f);
         float f3 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, f);
         float f4 = f3 - f2;
         if (modelBase.isRiding && entity.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase ridingEntity = (EntityLivingBase)entity.ridingEntity;
            f2 = this.interpolateRotation(ridingEntity.prevRenderYawOffset, ridingEntity.renderYawOffset, f);
            f4 = f3 - f2;
            float f5 = MathHelper.wrapAngleTo180_float(f4);
            if (f5 < -85.0F) {
               f5 = -85.0F;
            }

            if (f5 >= 85.0F) {
               f5 = 85.0F;
            }

            f2 = f3 - f5;
            if (f5 * f5 > 2500.0F) {
               f2 += f5 * 0.2F;
            }
         }

         float f6;
         if (entity == this.renderManager.livingPlayer) {
            f6 = entity.prevRotationPitchHead + (entity.rotationPitchHead - entity.prevRotationPitchHead) * f;
         } else {
            f6 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f;
         }

         this.renderLivingAt(entity, d - this.renderManager.getRenderPosX(), d2 - this.renderManager.getRenderPosY(), d3 - this.renderManager.getRenderPosZ());
         float f7 = this.handleRotationFloat(entity, f);
         this.rotateCorpse(entity, f7, f2, f);
         GlStateManager.enableRescaleNormal();
         GlStateManager.scale(-1.0F, -1.0F, 1.0F);
         this.preRenderCallback(entity, f);
         float f8 = 0.0625F;
         GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
         float f9 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * f;
         float f10 = entity.limbSwing - entity.limbSwingAmount * (1.0F - f);
         if (entity.isChild()) {
            f10 *= 3.0F;
         }

         if (f9 > 1.0F) {
            f9 = 1.0F;
         }

         GlStateManager.enableAlpha();
         modelBase.setLivingAnimations(entity, f10, f9, f);
         modelBase.setRotationAngles(f10, f9, f7, f4, f6, 0.0625F, entity);
         return new float[]{f10, f9, f7, f4, f6, 0.0625F};
      } catch (Exception e) {
         LOGGER.error("Couldn't render entity", e);
         return new float[0];
      }
   }

   @Override
   public void renderName(Entity entity, double d, double d2, double d3) {
      this.renderName((EntityLivingBase)entity, d, d2, d3);
   }

   public void setRenderOutlines(boolean flag) {
      this.renderOutlines = flag;
   }

   public boolean addLayer(Object obj) {
      return this.layerRenderers.add((LayerRenderer)obj);
   }

   public void rotateCorpse(EntityLivingBase entity, float f, float f2, float f3) {
      GlStateManager.rotate(180.0F - f2, 0.0F, 1.0F, 0.0F);
      if (entity.deathTime > 0) {
         float f4 = ((float)entity.deathTime + f3 - 1.0F) / 20.0F * 1.6F;
         f4 = MathHelper.sqrt_float(f4);
         if (f4 > 1.0F) {
            f4 = 1.0F;
         }

         GlStateManager.rotate(f4 * this.getDeathMaxRotation(entity), 0.0F, 0.0F, 1.0F);
      } else {
         String str = EnumChatFormatting.getTextWithoutFormattingCodes(entity.getName());
         if (str != null
            && (str.equals("Dinnerbone") || str.equals("Grumm"))
            && (!(entity instanceof EntityPlayer) || ((EntityPlayer)entity).isWearing(EnumPlayerModelParts.CAPE))) {
            GlStateManager.translate(0.0F, entity.height + 0.1F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }
   }

   public boolean canRenderName(EntityLivingBase entity) {
      EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
      boolean flag = entity != thePlayer || ClientManagers.moduleManager.nameTags.isToggled() && ClientManagers.moduleManager.nameTags.myself.getValue();
      if (entity instanceof EntityPlayer && flag) {
         if (ClientManagers.moduleManager.antiBot.isBot(entity)) {
            return false;
         }

         Team team = entity.getTeam();
         Team team2 = thePlayer.getTeam();
         if (team != null) {
            if (ClientManagers.moduleManager.nameTags.isToggled() && ClientManagers.moduleManager.nameTags.invisibleTeam.getValue()) {
               return true;
            }

            Team.EnumVisible nameTagVisibility = team.getNameTagVisibility();
            switch (nameTagVisibility) {
               case ALWAYS:
                  return true;
               case NEVER:
                  return false;
               case HIDE_FOR_OTHER_TEAMS:
                  return team2 == null || team.isSameTeam(team2);
               case HIDE_FOR_OWN_TEAM:
                  return team2 == null || !team.isSameTeam(team2);
               default:
                  return true;
            }
         }
      }

      return Minecraft.isGuiEnabled()
         && flag
         && (!entity.isInvisibleToPlayer(thePlayer) || ClientManagers.moduleManager.nameTags.isToggled() && ClientManagers.moduleManager.nameTags.invisible.getValue())
         && entity.riddenByEntity == null;
   }

   @Override
   public boolean canRenderName(Entity entity) {
      return this.canRenderName((EntityLivingBase)entity);
   }

   public void renderLayers(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      for (int i = 0; i < this.layerRenderers.size(); i++) {
         LayerRenderer layerRenderer = (LayerRenderer)this.layerRenderers.get(i);
         boolean flag = this.setBrightness(entity, f3, layerRenderer.shouldCombineTextures());
         if (EmissiveTextures.isActive()) {
            EmissiveTextures.beginRender();
         }

         if (this.renderLayersPushMatrix) {
            GlStateManager.pushMatrix();
         }

         layerRenderer.doRenderLayer(entity, f, f2, f3, f4, f5, f6, f7);
         if (this.renderLayersPushMatrix) {
            GlStateManager.popMatrix();
         }

         if (EmissiveTextures.isActive()) {
            if (EmissiveTextures.hasEmissive()) {
               this.renderLayersPushMatrix = true;
               EmissiveTextures.beginRenderEmissive();
               GlStateManager.pushMatrix();
               layerRenderer.doRenderLayer(entity, f, f2, f3, f4, f5, f6, f7);
               GlStateManager.popMatrix();
               EmissiveTextures.endRenderEmissive();
            }

            EmissiveTextures.endRender();
         }

         if (flag) {
            this.unsetBrightness();
         }
      }
   }

   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      if (ClientManagers.moduleManager.chams.isToggled()) {
         ClientManagers.moduleManager.chams.beginEntityChams(entity);
      }

      if (ANIMATE_MODEL_LIVING) {
         entity.limbSwingAmount = 1.0F;
      }

      GlStateManager.pushMatrix();
      GlStateManager.disableCull();
      this.mainModel.swingProgress = this.getSwingProgress(entity, f2);
      this.mainModel.isRiding = entity.isRiding();
      this.mainModel.isChild = entity.isChild();

      try {
         GL11.glPushMatrix();
         float f3 = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, f2);
         float f4 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, f2);
         float f5 = f4 - f3;
         if (this.mainModel.isRiding && entity.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase ridingEntity = (EntityLivingBase)entity.ridingEntity;
            f3 = this.interpolateRotation(ridingEntity.prevRenderYawOffset, ridingEntity.renderYawOffset, f2);
            f5 = f4 - f3;
            float f6 = MathHelper.wrapAngleTo180_float(f5);
            if (f6 < -85.0F) {
               f6 = -85.0F;
            }

            if (f6 >= 85.0F) {
               f6 = 85.0F;
            }

            f3 = f4 - f6;
            if (f6 * f6 > 2500.0F) {
               f3 += f6 * 0.2F;
            }

            f5 = f4 - f3;
         }

         float f7;
         if (entity == this.renderManager.livingPlayer) {
            f7 = entity.prevRotationPitchHead + (entity.rotationPitchHead - entity.prevRotationPitchHead) * f2;
         } else {
            f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f2;
         }

         this.renderLivingAt(entity, d, d2, d3);
         float f8 = this.handleRotationFloat(entity, f2);
         this.rotateCorpse(entity, f8, f3, f2);
         GlStateManager.enableRescaleNormal();
         GlStateManager.scale(-1.0F, -1.0F, 1.0F);
         this.preRenderCallback(entity, f2);
         float f9 = 0.0625F;
         GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
         float f10 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * f2;
         float f11 = entity.limbSwing - entity.limbSwingAmount * (1.0F - f2);
         if (entity.isChild()) {
            f11 *= 3.0F;
         }

         if (f10 > 1.0F) {
            f10 = 1.0F;
         }

         GlStateManager.enableAlpha();
         this.mainModel.setLivingAnimations(entity, f11, f10, f2);
         this.mainModel.setRotationAngles(f11, f10, f8, f5, f7, 0.0625F, entity);
         if (CustomEntityModels.isActive()) {
            this.renderEntity = entity;
            this.renderLimbSwing = f11;
            this.renderLimbSwingAmount = f10;
            this.renderAgeInTicks = f8;
            this.renderHeadYaw = f5;
            this.renderHeadPitch = f7;
            this.renderScaleFactor = f9;
            this.renderPartialTicks = f2;
         }

         if (this.renderOutlines) {
            boolean flag = this.setScoreTeamColor(entity);
            this.renderModel(
               entity, f11, f10, f8, f5, f7, 0.0625F, ClientManagers.moduleManager.esp.isToggled() && ClientManagers.moduleManager.esp.isValidTargetEntity(entity), true
            );
            if (flag) {
               this.unsetScoreTeamColor();
            }
         } else {
            boolean flag2 = this.setDoRenderBrightness(entity, f2);
            if (EmissiveTextures.isActive()) {
               EmissiveTextures.beginRender();
            }

            if (this.renderModelPushMatrix) {
               GlStateManager.pushMatrix();
            }

            if (ClientManagers.moduleManager.chams.isToggled() && ClientManagers.moduleManager.chams.shouldRenderEntityChams(entity) && ClientManagers.moduleManager.chams.coloredEntityChams.getValue()) {
               if (Config.isShaderPackLoaded() && EntityRenderer.shaderProgramSwitchEnabled) {
                  Shaders.useProgram(Shaders.ProgramNone);
               }

               if (ClientManagers.moduleManager.chams.renderTextureEntity.getValue() && !ClientManagers.moduleManager.chams.onlyThroughWallsEntities.getValue()) {
                  this.renderModel(
                     entity, f11, f10, f8, f5, f7, 0.0625F, ClientManagers.moduleManager.antiInvisibles.isToggled() && ClientManagers.moduleManager.antiInvisibles.shouldRevealEntity(entity), true
                  );
               }

               boolean isLightingEnabled = GlStateManager.isLightingEnabled();
               boolean isDepthEnabled = GlStateManager.isDepthEnabled();
               boolean isTexture2DEnabled = GlStateManager.isTexture2DEnabled();
               GlStateManager.enableBlend();
               GlStateManager.tryBlendFuncSeparate(770, 771, 0, 0);
               GlStateManager.disableLighting();
               GlStateManager.disableDepth();
               RenderUtil.setGlColor(ClientManagers.moduleManager.chams.entityColor.getColorInt((byte)0));
               GlStateManager.disableTexture2D();
               this.renderModel(
                  entity, f11, f10, f8, f5, f7, 0.0625F, ClientManagers.moduleManager.antiInvisibles.isToggled() && ClientManagers.moduleManager.antiInvisibles.shouldRevealEntity(entity), false
               );
               GlStateManager.enableDepth();
               if (isLightingEnabled) {
                  GlStateManager.enableLighting();
               }

               if (!isDepthEnabled) {
                  GlStateManager.disableDepth();
               }

               if (isTexture2DEnabled) {
                  GlStateManager.enableTexture2D();
               }

               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               if (ClientManagers.moduleManager.chams.onlyThroughWallsEntities.getValue()) {
                  this.renderModel(
                     entity, f11, f10, f8, f5, f7, 0.0625F, ClientManagers.moduleManager.antiInvisibles.isToggled() && ClientManagers.moduleManager.antiInvisibles.shouldRevealEntity(entity), true
                  );
               }

               if (Config.isShaderPackLoaded() && EntityRenderer.shaderProgramSwitchEnabled) {
                  Shaders.useProgram(Shaders.activeProgram);
               }
            } else {
               this.renderModel(
                  entity, f11, f10, f8, f5, f7, 0.0625F, ClientManagers.moduleManager.antiInvisibles.isToggled() && ClientManagers.moduleManager.antiInvisibles.shouldRevealEntity(entity), true
               );
            }

            if (this.renderModelPushMatrix) {
               GlStateManager.popMatrix();
            }

            if (EmissiveTextures.isActive()) {
               if (EmissiveTextures.hasEmissive()) {
                  this.renderModelPushMatrix = true;
                  EmissiveTextures.beginRenderEmissive();
                  GlStateManager.pushMatrix();
                  this.renderModel(entity, f11, f10, f8, f5, f7, f9, false, true);
                  GlStateManager.popMatrix();
                  EmissiveTextures.endRenderEmissive();
               }

               EmissiveTextures.endRender();
            }

            if (flag2) {
               this.unsetBrightness();
            }

            GlStateManager.depthMask(true);
            if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator()) {
               this.renderLayers(entity, f11, f10, f2, f8, f5, f7, 0.0625F);
            }

            if (CustomEntityModels.isActive()) {
               this.renderEntity = null;
            }
         }

         GlStateManager.disableRescaleNormal();
         GL11.glPopMatrix();
      } catch (Exception e) {
         LOGGER.error("Couldn't render entity", e);
      }

      RenderEntityListener.onRenderEntity(entity, this, f2, d, d2, d3);
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.enableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GlStateManager.enableCull();
      GlStateManager.popMatrix();
      if (!this.renderOutlines) {
         super.doRender(entity, d, d2, d3, f, f2);
      }

      if (ClientManagers.moduleManager.chams.isToggled()) {
         ClientManagers.moduleManager.chams.endEntityChams();
      }
   }

   static {
      int[] textureData = TEXTURE_BRIGHTNESS.getTextureData();

      for (int i = 0; i < 256; i++) {
         textureData[i] = -1;
      }

      TEXTURE_BRIGHTNESS.updateDynamicTexture();
   }

   public RendererLivingEntity(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2);
      this.mainModel = modelBase;
      this.shadowSize = f;
      this.renderModelPushMatrix = this.mainModel instanceof ModelSpider;
   }

   public float getDeathMaxRotation(EntityLivingBase entity) {
      return 90.0F;
   }

   public boolean setDoRenderBrightness(EntityLivingBase entity, float f) {
      return this.setBrightness(entity, f, true);
   }

   public ModelBase getMainModel() {
      return this.mainModel;
   }

   public void renderEntityModel(EntityLivingBase entity, float f, double d, double d2, double d3, boolean flag) {
      try {
         this.mainModel.isChild = entity.isChild();
         this.mainModel.swingProgress = this.getSwingProgress(entity, f);
         this.mainModel.isRiding = entity.isRiding();
         if (this.mainModel instanceof ModelBiped) {
            ModelBiped mainModel2 = (ModelBiped)this.mainModel;
            mainModel2.isSneak = entity.isSneaking();
            ItemStack stack = entity instanceof EntityPlayerSP ? SilentSlotManager.heldItemStack : entity.getHeldItem();
            if (stack == null) {
               mainModel2.heldItemRight = 0;
            } else {
               mainModel2.heldItemRight = 1;
               if (entity instanceof EntityPlayer && ((EntityPlayer)entity).getItemInUseCount() > 0) {
                  EnumAction itemUseAction = stack.getItemUseAction();
                  if (itemUseAction == EnumAction.BLOCK) {
                     mainModel2.heldItemRight = 3;
                  } else if (itemUseAction == EnumAction.BOW) {
                     mainModel2.aimedBow = true;
                  }
               }
            }
         }

         GL11.glPushMatrix();
         float f2 = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, f);
         float f3 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, f);
         float f4 = f3 - f2;
         if (this.mainModel.isRiding && entity.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase ridingEntity = (EntityLivingBase)entity.ridingEntity;
            f2 = this.interpolateRotation(ridingEntity.prevRenderYawOffset, ridingEntity.renderYawOffset, f);
            f4 = f3 - f2;
            float f5 = MathHelper.wrapAngleTo180_float(f4);
            if (f5 < -85.0F) {
               f5 = -85.0F;
            }

            if (f5 >= 85.0F) {
               f5 = 85.0F;
            }

            f2 = f3 - f5;
            if (f5 * f5 > 2500.0F) {
               f2 += f5 * 0.2F;
            }
         }

         float f6;
         if (entity == this.renderManager.livingPlayer) {
            f6 = entity.prevRotationPitchHead + (entity.rotationPitchHead - entity.prevRotationPitchHead) * f;
         } else {
            f6 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f;
         }

         this.renderLivingAt(entity, d - this.renderManager.getRenderPosX(), d2 - this.renderManager.getRenderPosY(), d3 - this.renderManager.getRenderPosZ());
         float f7 = this.handleRotationFloat(entity, f);
         this.rotateCorpse(entity, f7, f2, f);
         GlStateManager.enableRescaleNormal();
         GlStateManager.scale(-1.0F, -1.0F, 1.0F);
         this.preRenderCallback(entity, f);
         float f8 = 0.0625F;
         GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
         float f9 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * f;
         float f10 = entity.limbSwing - entity.limbSwingAmount * (1.0F - f);
         if (entity.isChild()) {
            f10 *= 3.0F;
         }

         if (f9 > 1.0F) {
            f9 = 1.0F;
         }

         GlStateManager.enableAlpha();
         this.mainModel.setLivingAnimations(entity, f10, f9, f);
         this.mainModel.setRotationAngles(f10, f9, f7, f4, f6, 0.0625F, entity);
         if (this.renderOutlines) {
            boolean flag2 = this.setScoreTeamColor(entity);
            this.renderModel(entity, f10, f9, f7, f4, f6, 0.0625F, false, true);
            if (flag2) {
               this.unsetScoreTeamColor();
            }
         } else {
            boolean flag3 = this.setDoRenderBrightness(entity, f);
            this.renderModel(entity, f10, f9, f7, f4, f6, 0.0625F, false, true);
            if (flag3) {
               this.unsetBrightness();
            }

            GlStateManager.depthMask(true);
            if ((!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator()) && flag) {
               this.renderLayers(entity, f10, f9, f, f7, f4, f6, 0.0625F);
            }
         }

         GlStateManager.disableRescaleNormal();
         GL11.glPopMatrix();
      } catch (Exception e) {
         LOGGER.error("Couldn't render entity", e);
      }
   }

   public void transformHeldFull3DItemLayer() {
   }

   public int getColorMultiplier(EntityLivingBase entity, float f, float f2) {
      return 0;
   }

   public void preRenderCallback(EntityLivingBase entity, float f) {
   }

   public void unsetScoreTeamColor() {
      GlStateManager.enableLighting();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GlStateManager.enableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.enableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   public float getSwingProgress(EntityLivingBase entity, float f) {
      return entity.getSwingProgress(f);
   }

   public void unsetBrightness() {
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GlStateManager.enableTexture2D();
      GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_ALPHA, OpenGlHelper.GL_PRIMARY_COLOR);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_ALPHA, 770);
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, 5890);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, 5890);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
      GlStateManager.disableTexture2D();
      GlStateManager.bindTexture(0);
      GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, 5890);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
      GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, 5890);
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      if (Config.isShaderPackLoaded()) {
         Shaders.setEntityColor(0.0F, 0.0F, 0.0F, 0.0F);
      }
   }

   public void renderLivingAt(EntityLivingBase entity, double d, double d2, double d3) {
      GlStateManager.translate((float)d, (float)d2, (float)d3);
   }

   public void renderModel(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag, boolean flag2) {
      boolean flag3 = !entity.isInvisible();
      boolean flag4 = !flag3 && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer);
      if (flag3 || flag4 || flag) {
         if (!this.bindEntityTexture(entity)) {
            return;
         }

         if (flag4) {
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 0.15F);
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.alphaFunc(516, 0.003921569F);
         }

         this.mainModel.render(entity, f, f2, f3, f4, f5, f6, flag2);
         if (flag4) {
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.popMatrix();
            GlStateManager.depthMask(true);
         }
      }
   }

   public float handleRotationFloat(EntityLivingBase entity, float f) {
      return (float)entity.ticksExisted + f;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityLivingBase)entity, d, d2, d3, f, f2);
   }

   public void renderName(EntityLivingBase entity, double d, double d2, double d3) {
      if (this.canRenderName(entity)
         && (entity instanceof EntityPlayer ? !ClientManagers.moduleManager.antiStrike.shouldHidePlayerNameTags() : !ClientManagers.moduleManager.antiStrike.shouldHideOtherNameTags())
         && entity.isRenderNameTag()) {
         double distanceSqToEntity = entity.getDistanceSqToEntity(this.renderManager.livingPlayer);
         float f = entity.isSneaking() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;
         boolean flag = entity instanceof EntityPlayer && ClientManagers.moduleManager.nameTags.isToggled();
         if (distanceSqToEntity < (double)(f * f) || flag) {
            if (Config.isShaderPackLoaded() && EntityRenderer.shaderNameTagFix) {
               Shaders.useProgram(Shaders.ProgramNone);
            }

            String displayNameString = entity.getDisplayNameString();
            if (entity instanceof AbstractClientPlayer) {
               String name = ((EntityPlayer)entity).getGameProfile().getName();
               if (ClientManagers.moduleManager.midClick.isFriend(name, true) && ClientManagers.moduleManager.midClick.isToggled()) {
                  String str = StringUtils.replace(displayNameString, name, (ClientManagers.moduleManager.midClick.simple.getValue() ? "§l" : "§a§l") + name);
                  if (!ClientManagers.moduleManager.midClick.simple.getValue()) {
                     displayNameString = "§7[§2§lFriend§7]§9 " + str;
                  } else {
                     displayNameString = str;
                  }
               }

               if (ClientManagers.moduleManager.murdererFinder.isMurderer(name)) {
                  displayNameString = displayNameString + " §7[§4Murderer§7]§9";
               }
            }

            float f2 = 0.02666667F;
            GlStateManager.alphaFunc(516, 0.1F);
            if (entity.isSneaking() && !Augustus.getInstance().getModuleManager().nameTags.isToggled()) {
               FontRenderer fontRenderer = this.getFontRendererFromRenderManager();
               GlStateManager.pushMatrix();
               GlStateManager.translate((float)d, (float)d2 + entity.height + 0.5F - (entity.isChild() ? entity.height / 2.0F : 0.0F), (float)d3);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
               GlStateManager.scale(-0.02666667F, -0.02666667F, 0.02666667F);
               GlStateManager.translate(0.0F, 9.374999F, 0.0F);
               GlStateManager.disableLighting();
               GlStateManager.depthMask(false);
               GlStateManager.enableBlend();
               GlStateManager.disableTexture2D();
               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
               int n = fontRenderer.getStringWidth(displayNameString) / 2;
               Tessellator instance = Tessellator.getInstance();
               WorldRenderer worldRenderer = instance.getWorldRenderer();
               worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
               worldRenderer.pos((double)(-n - 1), -1.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               worldRenderer.pos((double)(-n - 1), 8.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               worldRenderer.pos((double)(n + 1), 8.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               worldRenderer.pos((double)(n + 1), -1.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               instance.draw();
               GlStateManager.enableTexture2D();
               GlStateManager.depthMask(true);
               fontRenderer.drawString(displayNameString, -n, 0, 553648127);
               GlStateManager.enableLighting();
               GlStateManager.disableBlend();
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.popMatrix();
            } else {
               this.renderOffsetLivingLabel(entity, d, d2 - (entity.isChild() ? (double)(entity.height / 2.0F) : 0.0), d3, displayNameString, distanceSqToEntity, false);
            }

            if (Config.isShaderPackLoaded() && EntityRenderer.shaderNameTagFix) {
               Shaders.useProgram(Shaders.activeProgram);
            }
         }
      }
   }

}
