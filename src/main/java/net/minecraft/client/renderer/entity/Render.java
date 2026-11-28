package net.minecraft.client.renderer.entity;

import java.awt.Color;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.font.IFontRenderer;
import net.augustus.events.listeners.ShouldRenderEntityListener;
import net.augustus.ClientManagers;
import net.optifine.entity.model.IEntityRenderer;
import net.augustus.render.RenderUtil;
import net.augustus.font.FormattedUnicodeFontRenderer;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public abstract class Render implements IEntityRenderer {
   private static final ResourceLocation shadowTextures = new ResourceLocation("textures/misc/shadow.png");
   public final RenderManager renderManager;
   public float shadowSize;
   public float shadowOpaque = 1.0F;
   private Class entityClass = null;
   private ResourceLocation locationTextureCustom = null;

   public void doRenderShadowAndFire(Entity entity, double d, double d2, double d3, float f, float f2) {
      if (this.renderManager.options != null) {
         if (this.renderManager.options.entityShadows && this.shadowSize > 0.0F && !entity.isInvisible() && this.renderManager.isRenderShadow()) {
            double distanceToCamera = this.renderManager.getDistanceToCamera(entity.posX, entity.posY, entity.posZ);
            float f3 = (float)((1.0 - distanceToCamera / 256.0) * (double)this.shadowOpaque);
            if (f3 > 0.0F) {
               this.renderShadow(entity, d, d2, d3, f3, f2);
            }
         }

         if (entity.canRenderOnFire() && (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator())) {
            this.renderEntityOnFire(entity, d, d2, d3, f2);
         }
      }
   }

   @Override
   public void setEntityClass(Class clazz) {
      this.entityClass = clazz;
   }

   private int renderItemEnchantments(ItemStack stack, int n, int n2) {
      int n3 = 0;
      Object customFontRenderer = ClientManagers.moduleManager.nameTags.customFontRenderer;
      if (customFontRenderer == null) {
         customFontRenderer = MC.mc.fontRendererObj;
      }

      float f = ((IFontRenderer)customFontRenderer).getFontHeight() + 1.0F;

      for (int n4 : (Iterable<Integer>)(ClientManagers.moduleManager.nameTags.enchantmentAbbreviations.keySet())) {
         int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(n4, stack);
         if (enchantmentLevel > 0) {
            String str = (String)ClientManagers.moduleManager.nameTags.enchantmentAbbreviations.get(n4) + enchantmentLevel;
            float f2 = (float)n;
            float f3 = (float)n2 - f * (float)n3;
            int rgb = Color.white.getRGB();
            ((IFontRenderer)customFontRenderer).drawString(str, f2, f3, rgb);
            n3++;
         }
      }

      return n3;
   }

   public void renderScaledNameTagPass(Entity entity, String str, double d, double d2, double d3, double d4, int n, ShaderRenderPass shaderRenderPass, boolean flag) {
      double d5 = this.renderManager.getRenderPosX() - (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)MC.mc.getTimer().renderPartialTicks);
      double d6 = this.renderManager.getRenderPosY() - (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)MC.mc.getTimer().renderPartialTicks);
      double d7 = this.renderManager.getRenderPosZ() - (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)MC.mc.getTimer().renderPartialTicks);
      double d8 = d5 * d5 + d6 * d6 + d7 * d7;
      if (d8 <= d * d) {
         FontRenderer fontRenderer = this.getFontRendererFromRenderManager();
         float value = (float)ClientManagers.moduleManager.nameTags.scale.getValue();
         float f = (float)((double)(0.016666668F * value) + Math.sqrt(d8) / 850.0);
         GlStateManager.pushMatrix();
         GlStateManager.translate(
            (double)((float)d2 + 0.0F),
            (double)((float)d3 + entity.height + 0.5F + f * 8.0F - 0.016666668F * value * 8.0F) + ClientManagers.moduleManager.nameTags.height.getValue(),
            (double)((float)d4)
         );
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(
            Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? -this.renderManager.playerViewX : this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F
         );
         GlStateManager.scale(-f, -f, f);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         byte b = 0;
         if (str.equals("deadmau5")) {
            b = -10;
         }

         int n2 = fontRenderer.getStringWidth(str) / 2;
         GlStateManager.disableTexture2D();
         if (n == 1
            && (
               shaderRenderPass == ShaderRenderPass.BLUR || shaderRenderPass == ShaderRenderPass.SHADOW && !ClientManagers.moduleManager.nameTags.shadowFont.getValue() || !ClientManagers.moduleManager.nameTags.transparent.getValue()
            )) {
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos((double)(-n2 - 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(-n2 - 1), 8.5 + (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(n2 + 1), 8.5 + (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(n2 + 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            instance.draw();
         }

         if (n == 2
            && (shaderRenderPass != ShaderRenderPass.NONE && !ClientManagers.moduleManager.nameTags.shadowFont.getValue() || !ClientManagers.moduleManager.nameTags.transparent.getValue())
            && ClientManagers.moduleManager.nameTags.outline.getValue()) {
            GL11.glLineWidth(1.0E-4F);
            worldRenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos((double)(-n2 - 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(n2 + 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(-n2 - 1), 8.5 + (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(n2 + 1), 8.5 + (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(-n2 - 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(-n2 - 1), 8.5 + (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(n2 + 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(n2 + 1), 8.5 + (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            instance.draw();
         }

         GlStateManager.enableTexture2D();
         if (n == 2) {
            fontRenderer.drawString(str, (float)(-fontRenderer.getStringWidth(str)) / 2.0F, (float)b, ClientManagers.moduleManager.nameTags.throughWalls.getValue() ? -1 : 553648127, false);
            if (flag) {
               this.renderNameTagInfo(entity);
            }
         }

         GlStateManager.pushMatrix();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.disableLighting();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         if (n == 2 && flag) {
            this.renderNameTagArmor(entity);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         GlStateManager.popMatrix();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         if (n == 2) {
            fontRenderer.drawString(str, (float)(-fontRenderer.getStringWidth(str)) / 2.0F, (float)b, -1, false);
            if (flag) {
               this.renderNameTagInfo(entity);
            }
         }

         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   public void renderName(Entity entity, double d, double d2, double d3) {
      if (this.canRenderName(entity)
         && (entity instanceof EntityPlayer ? !ClientManagers.moduleManager.antiStrike.shouldHidePlayerNameTags() : !ClientManagers.moduleManager.antiStrike.shouldHideOtherNameTags())
         && entity.isRenderNameTag()) {
         this.renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), d, d2, d3, 64);
      }
   }

   public static void renderOffsetAABB(AxisAlignedBB box, double d, double d2, double d3) {
      GlStateManager.disableTexture2D();
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      worldRenderer.setTranslation(d, d2, d3);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_NORMAL);
      worldRenderer.pos(box.minX, box.maxY, box.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
      worldRenderer.pos(box.minX, box.minY, box.minZ).normal(0.0F, 0.0F, -1.0F).endVertex();
      worldRenderer.pos(box.minX, box.minY, box.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
      worldRenderer.pos(box.minX, box.maxY, box.maxZ).normal(0.0F, 0.0F, 1.0F).endVertex();
      worldRenderer.pos(box.minX, box.minY, box.minZ).normal(0.0F, -1.0F, 0.0F).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.minZ).normal(0.0F, -1.0F, 0.0F).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.maxZ).normal(0.0F, -1.0F, 0.0F).endVertex();
      worldRenderer.pos(box.minX, box.minY, box.maxZ).normal(0.0F, -1.0F, 0.0F).endVertex();
      worldRenderer.pos(box.minX, box.maxY, box.maxZ).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.maxZ).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.minZ).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(box.minX, box.maxY, box.minZ).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(box.minX, box.minY, box.maxZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
      worldRenderer.pos(box.minX, box.maxY, box.maxZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
      worldRenderer.pos(box.minX, box.maxY, box.minZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
      worldRenderer.pos(box.minX, box.minY, box.minZ).normal(-1.0F, 0.0F, 0.0F).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.minZ).normal(1.0F, 0.0F, 0.0F).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.minZ).normal(1.0F, 0.0F, 0.0F).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.maxZ).normal(1.0F, 0.0F, 0.0F).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.maxZ).normal(1.0F, 0.0F, 0.0F).endVertex();
      instance.draw();
      worldRenderer.setTranslation(0.0, 0.0, 0.0);
      GlStateManager.enableTexture2D();
   }

   public void renderCustomNameTag(Entity entity, String str, double d, double d2, double d3, int n, boolean flag) {
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.disableDepth();
      GL11.glNormal3d(0.0, 1.0, 0.0);
      GlStateManager.disableLighting();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableTexture2D();
      boolean flag2 = ClientManagers.moduleManager.nameTags.blur.getValue() && ClientManagers.moduleManager.blur.isToggled() && !Config.isShaderPackLoaded() && BlurShadowManager.isShadersCompatible();
      BlurShadowManager.enableOneShotPass(flag2, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
      FormattedUnicodeFontRenderer customFontRenderer = ClientManagers.moduleManager.nameTags.customFontRenderer;
      if (customFontRenderer != null) {
         String str2 = MC.mc.fontRendererObj.applyTextFilters(str);
         if (flag2) {
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderCustomFontNameTagPass(entity, customFontRenderer, str2, (double)n, d, d2, d3, 1, shaderRenderPass, flag));
            if (ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled()) {
               BlurShadowManager.enableShadowOnce();
               BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> this.renderCustomFontNameTagPass(entity, customFontRenderer, str2, (double)n, d, d2, d3, 2, shaderRenderPass2, flag));
            }

            BlurShadowManager.enqueuePostBlurTask(shaderRenderPass3 -> this.renderCustomFontNameTagPass(entity, customFontRenderer, str2, (double)n, d, d2, d3, 2, shaderRenderPass3, flag));
         } else {
            BlurShadowManager.submitRenderTask(false, shaderRenderPass4 -> this.renderCustomFontNameTag(entity, customFontRenderer, str2, (double)n, d, d2, d3, shaderRenderPass4, flag));
         }
      } else if (flag2) {
         BlurShadowManager.submitRenderTask(false, shaderRenderPass5 -> this.renderScaledNameTagPass(entity, str, (double)n, d, d2, d3, 1, shaderRenderPass5, flag));
         if (ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled()) {
            BlurShadowManager.enableShadowOnce();
            BlurShadowManager.submitRenderTask(false, shaderRenderPass6 -> this.renderScaledNameTagPass(entity, str, (double)n, d, d2, d3, 2, shaderRenderPass6, flag));
         }

         BlurShadowManager.enqueuePostBlurTask(shaderRenderPass7 -> this.renderScaledNameTagPass(entity, str, (double)n, d, d2, d3, 2, shaderRenderPass7, flag));
      } else {
         BlurShadowManager.submitRenderTask(false, shaderRenderPass8 -> this.renderScaledNameTag(entity, str, (double)n, d, d2, d3, shaderRenderPass8, flag));
      }

      GL11.glPopMatrix();
   }

   public void renderCustomFontNameTag(Entity entity, FormattedUnicodeFontRenderer renderer, String str, double d, double d2, double d3, double d4, ShaderRenderPass shaderRenderPass, boolean flag) {
      double d5 = this.renderManager.getRenderPosX() - (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)MC.mc.getTimer().renderPartialTicks);
      double d6 = this.renderManager.getRenderPosY() - (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)MC.mc.getTimer().renderPartialTicks);
      double d7 = this.renderManager.getRenderPosZ() - (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)MC.mc.getTimer().renderPartialTicks);
      double d8 = d5 * d5 + d6 * d6 + d7 * d7;
      if (d8 <= d * d) {
         float value = (float)ClientManagers.moduleManager.nameTags.scale.getValue();
         float f = (float)((double)(0.016666668F * value) + Math.sqrt(d8) / 850.0);
         GlStateManager.pushMatrix();
         GlStateManager.translate(
            (double)((float)d2 + 0.0F),
            (double)((float)d3 + entity.height + 0.5F + f * 8.0F - 0.016666668F * value * 8.0F) + ClientManagers.moduleManager.nameTags.height.getValue(),
            (double)((float)d4)
         );
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(
            Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? -this.renderManager.playerViewX : this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F
         );
         GlStateManager.scale(-f, -f, f);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         byte b = 0;
         if (str.equals("deadmau5")) {
            b = -10;
         }

         float f2 = renderer.getStringWidthFloat(str) / 2.0F;
         float fontHeight = renderer.getFontHeight();
         GlStateManager.disableTexture2D();
         if (shaderRenderPass != ShaderRenderPass.NONE && !ClientManagers.moduleManager.nameTags.shadowFont.getValue() || !ClientManagers.moduleManager.nameTags.transparent.getValue()) {
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos((double)(-f2 - 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(-f2 - 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            instance.draw();
            if (ClientManagers.moduleManager.nameTags.outline.getValue()) {
               GL11.glLineWidth(1.0E-4F);
               worldRenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);
               worldRenderer.pos((double)(-f2 - 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(f2 + 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(-f2 - 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(f2 + 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(-f2 - 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(-f2 - 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(f2 + 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(f2 + 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               instance.draw();
            }
         }

         GlStateManager.enableTexture2D();
         float f3 = -f2;
         float f4 = (float)b;
         renderer.drawString(str, f3, f4, ClientManagers.moduleManager.nameTags.throughWalls.getValue() ? -1 : 553648127, false);
         if (flag) {
            this.renderNameTagInfo(entity);
         }

         GlStateManager.pushMatrix();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.disableLighting();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         if (flag) {
            this.renderNameTagArmor(entity);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         GlStateManager.popMatrix();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         f3 = -f2;
         f4 = (float)b;
         renderer.drawString(str, f3, f4, (byte)-1, false);
         if (flag) {
            this.renderNameTagInfo(entity);
         }

         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.renderName(entity, d, d2, d3);
   }

   public FontRenderer getFontRendererFromRenderManager() {
      return this.renderManager.getFontRenderer();
   }

   public boolean canRenderName(Entity entity) {
      return entity.getAlwaysRenderNameTagForRender() && entity.hasCustomName();
   }

   public abstract ResourceLocation getEntityTexture(Entity entity);

   private void renderNameTagInfo(Entity entity) {
      int n = 0;
      if (entity instanceof EntityPlayer && ClientManagers.moduleManager.nameTags.armorEnchantments.getValue() && ClientManagers.moduleManager.nameTags.armor.getValue()) {
         EntityPlayer player = (EntityPlayer)entity;
         int n2 = this.renderItemEnchantments(player.getCurrentEquippedItem(), -45, -30);
         if (n2 > n) {
            n = n2;
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         n2 = this.renderItemEnchantments(player.getCurrentArmor(3), -27, -30);
         if (n2 > n) {
            n = n2;
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         n2 = this.renderItemEnchantments(player.getCurrentArmor(2), -9, -30);
         if (n2 > n) {
            n = n2;
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         n2 = this.renderItemEnchantments(player.getCurrentArmor(1), 9, -30);
         if (n2 > n) {
            n = n2;
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         n2 = this.renderItemEnchantments(player.getCurrentArmor(0), 27, -30);
         if (n2 > n) {
            n = n2;
         }
      }

      if (entity instanceof EntityPlayer && ClientManagers.moduleManager.nameTags.potionEffects.getValue()) {
         double d = 0.75;
         int n3 = -50;
         int n4 = (int)((double)(n * -10) / d - (double)(ClientManagers.moduleManager.nameTags.armor.getValue() ? 58 : 30));
         GlStateManager.scale(d, d, 1.0);

         for (PotionEffect potionEffect : (Iterable<PotionEffect>)(((EntityPlayer)entity).getActivePotionEffects())) {
            if (potionEffect.getDuration() > 0) {
               Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               String str = I18n.format(potion.getName(), new Object[0]);
               if (potionEffect.getAmplifier() == 1) {
                  str = str + " " + I18n.format("enchantment.level.2", new Object[0]);
               } else if (potionEffect.getAmplifier() == 2) {
                  str = str + " " + I18n.format("enchantment.level.3", new Object[0]);
               } else if (potionEffect.getAmplifier() == 3) {
                  str = str + " " + I18n.format("enchantment.level.4", new Object[0]);
               }

               n3 = -((MC.mc.fontRendererObj.getStringWidth(str) + 23) / 2);
               if (potion.hasStatusIcon()) {
                  MC.mc.getTextureManager().bindTexture(GuiInventory.inventoryBackground);
                  int statusIconIndex = potion.getStatusIconIndex();
                  MC.mc.ingameGUI.drawTexturedModalRect(n3, n4 + 7, 0 + statusIconIndex % 8 * 18, 198 + statusIconIndex / 8 * 18, 18, 18);
               }

               MC.mc.fontRendererObj.drawStringWithShadow(str, (float)(n3 + 10 + 12), (float)(n4 + 6), 16777215);
               String durationString = Potion.getDurationString(potionEffect);
               MC.mc.fontRendererObj.drawStringWithShadow(durationString, (float)(n3 + 10 + 12), (float)(n4 + 6 + 10), 8355711);
               n4 -= (byte)25;
            }
         }

         GlStateManager.scale(1.0 / d, 1.0 / d, 1.0);
      }
   }

   public void bindTexture(ResourceLocation location) {
      this.renderManager.renderEngine.bindTexture(location);
   }

   public void renderLivingLabel(Entity entity, String str, double d, double d2, double d3, int n) {
      if (entity.getDistanceSqToEntity(this.renderManager.livingPlayer) <= (double)(n * n)) {
         FontRenderer fontRenderer = this.getFontRendererFromRenderManager();
         float f = 1.6F;
         float f2 = 0.016666668F * f;
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)d + 0.0F, (float)d2 + entity.height + 0.5F, (float)d3);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
         GlStateManager.scale(-f2, -f2, f2);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         byte b = 0;
         if (str.equals("deadmau5")) {
            b = -10;
         }

         int n2 = fontRenderer.getStringWidth(str) / 2;
         if (Config.isShaderPackLoaded()) {
            Shaders.disableTexture2D();
         }

         GlStateManager.disableTexture2D();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
         worldRenderer.pos((double)(-n2 - 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
         worldRenderer.pos((double)(-n2 - 1), (double)(8 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
         worldRenderer.pos((double)(n2 + 1), (double)(8 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
         worldRenderer.pos((double)(n2 + 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
         instance.draw();
         if (Config.isShaderPackLoaded()) {
            Shaders.enableTexture2D();
         }

         GlStateManager.enableTexture2D();
         fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, b, 553648127);
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, b, -1);
         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   private void renderNameTagArmor(Entity entity) {
      if (entity instanceof EntityPlayer && ClientManagers.moduleManager.nameTags.armor.getValue()) {
         GL11.glEnable(32823);
         GL11.glPolygonOffset(1.0F, -1100000.0F);
         float zLevel = MC.mc.getRenderItem().zLevel;
         MC.mc.getRenderItem().zLevel = -100.0F;
         EntityPlayer player = (EntityPlayer)entity;
         boolean value = ClientManagers.moduleManager.nameTags.armorDurability.getValue();
         if (player.getCurrentEquippedItem() != null) {
            RenderUtil.renderItemIntoGui(player.getCurrentEquippedItem(), (byte)-45, (byte)-20, value);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         if (player.getCurrentArmor(3) != null) {
            RenderUtil.renderItemIntoGui(player.getCurrentArmor(3), (byte)-27, (byte)-20, value);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         if (player.getCurrentArmor(2) != null) {
            RenderUtil.renderItemIntoGui(player.getCurrentArmor(2), (byte)-9, (byte)-20, value);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         if (player.getCurrentArmor(1) != null) {
            RenderUtil.renderItemIntoGui(player.getCurrentArmor(1), (byte)9, (byte)-20, value);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         if (player.getCurrentArmor(0) != null) {
            RenderUtil.renderItemIntoGui(player.getCurrentArmor(0), (byte)27, (byte)-20, value);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         MC.mc.getRenderItem().zLevel = zLevel;
         if (GL11.glIsEnabled(32823)) {
            GL11.glDisable(32823);
            GL11.glPolygonOffset(1.0F, 1100000.0F);
         }
      }
   }

   public void renderVanillaNameTag(Entity entity, String str, double d, double d2, double d3, int n, boolean flag) {
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.disableDepth();
      GL11.glNormal3d(0.0, 1.0, 0.0);
      GlStateManager.disableLighting();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableTexture2D();
      BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderVanillaNameTagPass(entity, str, d, d2, d3, n, shaderRenderPass, flag));
      GL11.glPopMatrix();
   }

   public void renderOffsetLivingLabel(Entity entity, double d, double d2, double d3, String str, double d4, boolean flag) {
      boolean flag2 = false;
      if (ClientManagers.moduleManager.freelook.isToggled()) {
         GlStateManager.disableCull();
         flag2 = true;
      }

      if (ClientManagers.moduleManager.nameTags.isToggled()
         && ClientManagers.moduleManager.nameTags.isRenderableEntity(entity)
         && (!ClientManagers.moduleManager.nameTags.ignoreTeammates.getValue() || !ClientManagers.moduleManager.teams.isToggled() || !ClientManagers.moduleManager.teams.isTeammate(entity))) {
         if (entity instanceof EntityPlayer && flag) {
            if (!ClientManagers.moduleManager.nameTags.healthMode.getSelectedValue().equals("None") && str != null && !str.equals("§r§r")) {
               StringBuilder sb = new StringBuilder().append(str).append(" §c");
               EntityPlayer player = (EntityPlayer)entity;
               str = sb.append((int)ClientManagers.moduleManager.nameTags.getPlayerHealth(player)).append("❤").toString();
            }

            if (ClientManagers.moduleManager.nameTags.isToggled() && ClientManagers.moduleManager.nameTags.ping.getValue()) {
               AbstractClientPlayer clientPlayer = (AbstractClientPlayer)entity;
               int playerPing = ClientManagers.moduleManager.nameTags.getPlayerPing(clientPlayer);
               if (playerPing >= 0) {
                  str = str + "§6 " + playerPing + "ms";
               }
            }
         }

         switch (ClientManagers.moduleManager.nameTags.mode.getSelectedValue()) {
            case "Vanilla":
               this.renderVanillaNameTag(entity, str, d, d2, d3, (int)ClientManagers.moduleManager.nameTags.maximumRenderDistance.getValue(), flag);
               break;
            case "Custom":
               this.renderCustomNameTag(entity, str, d, d2, d3, (int)ClientManagers.moduleManager.nameTags.maximumRenderDistance.getValue(), flag);
         }
      } else {
         this.renderLivingLabel(entity, str, d, d2, d3, 64);
      }

      if (flag2) {
         GlStateManager.enableCull();
      }
   }

   public static void setModelBipedMain(RenderBiped renderBiped, ModelBiped modelBiped) {
      renderBiped.modelBipedMain = modelBiped;
   }

   private void renderShadow(Entity entity, double d, double d2, double d3, float f, float f2) {
      if (!Config.isShaderPackLoaded() || !Shaders.shouldSkipDefaultShadow) {
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         this.renderManager.renderEngine.bindTexture(shadowTextures);
         World worldFromRenderManager = this.getWorldFromRenderManager();
         GlStateManager.depthMask(false);
         float shadowSize2 = this.shadowSize;
         if (entity instanceof EntityLiving) {
            EntityLiving entityLiving = (EntityLiving)entity;
            shadowSize2 *= entityLiving.getRenderSizeModifier();
            if (entityLiving.isChild()) {
               shadowSize2 *= 0.5F;
            }
         }

         double d4 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f2;
         double d5 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f2;
         double d6 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f2;
         int n = MathHelper.floor_double(d4 - (double)shadowSize2);
         int n2 = MathHelper.floor_double(d4 + (double)shadowSize2);
         int n3 = MathHelper.floor_double(d5 - (double)shadowSize2);
         int n4 = MathHelper.floor_double(d5);
         int n5 = MathHelper.floor_double(d6 - (double)shadowSize2);
         int n6 = MathHelper.floor_double(d6 + (double)shadowSize2);
         double d7 = d - d4;
         double d8 = d2 - d5;
         double d9 = d3 - d6;
         Tessellator instance = Tessellator.getInstance();
         instance.getWorldRenderer().begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

         for (BlockPos pos : (Iterable<BlockPos>)(BlockPos.getAllInBoxMutable(new BlockPos(n, n3, n5), new BlockPos(n2, n4, n6)))) {
            Block block = worldFromRenderManager.getBlockState2(pos.getX(), pos.getY() - 1, pos.getZ()).getBlock();
            if (block.getRenderType() != -1 && worldFromRenderManager.getLight(pos.getX(), pos.getY(), pos.getZ()) > 3) {
               this.renderShadowSingle(block, d, d2, d3, pos, f, shadowSize2, d7, d8, d9);
            }
         }

         instance.draw();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
      }
   }

   public void renderMultipass(Entity entity, double d, double d2, double d3, float f, float f2) {
   }

   public boolean shouldRender(Entity entity, ICamera camera, double d, double d2, double d3) {
      if (ShouldRenderEntityListener.postShouldRenderEntity(entity)) {
         return false;
      } else {
         AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
         if (entityBoundingBox.hasNaN() || entityBoundingBox.getAverageEdgeLength() == 0.0) {
            entityBoundingBox = new AxisAlignedBB(entity.posX - 2.0, entity.posY - 2.0, entity.posZ - 2.0, entity.posX + 2.0, entity.posY + 2.0, entity.posZ + 2.0);
         }

         return entity.isInRangeToRender3d(d, d2, d3) && (entity.ignoreFrustumCheck || camera.isBoundingBoxInFrustum(entityBoundingBox));
      }
   }

   public boolean isMultipass() {
      return false;
   }

   @Override
   public Class getEntityClass() {
      return this.entityClass;
   }

   public void renderVanillaNameTagPass(Entity entity, String str, double d, double d2, double d3, int n, ShaderRenderPass shaderRenderPass, boolean flag) {
      if (entity.getDistanceSqToEntity(this.renderManager.livingPlayer) <= (double)(n * n)) {
         FontRenderer fontRenderer = this.getFontRendererFromRenderManager();
         float value = (float)ClientManagers.moduleManager.nameTags.scale.getValue();
         float f = 0.016666668F * value;
         GlStateManager.pushMatrix();
         GlStateManager.translate(
            (double)((float)d + 0.0F), (double)((float)d2 + entity.height + 0.5F) + ClientManagers.moduleManager.nameTags.height.getValue(), (double)((float)d3)
         );
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(
            Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? -this.renderManager.playerViewX : this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F
         );
         GlStateManager.scale(-f, -f, f);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         byte b = 0;
         if (str.equals("deadmau5")) {
            b = -10;
         }

         int n2 = fontRenderer.getStringWidth(str) / 2;
         GlStateManager.disableTexture2D();
         if (shaderRenderPass != ShaderRenderPass.NONE && !ClientManagers.moduleManager.nameTags.shadowFont.getValue() || !ClientManagers.moduleManager.nameTags.transparent.getValue()) {
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos((double)(-n2 - 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldRenderer.pos((double)(-n2 - 1), (double)(8 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldRenderer.pos((double)(n2 + 1), (double)(8 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldRenderer.pos((double)(n2 + 1), (double)(-1 + b), 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            instance.draw();
         }

         GlStateManager.enableTexture2D();
         fontRenderer.drawString(str, (float)(-fontRenderer.getStringWidth(str)) / 2.0F, (float)b, ClientManagers.moduleManager.nameTags.throughWalls.getValue() ? -1 : 553648127, false);
         if (flag) {
            this.renderNameTagInfo(entity);
         }

         GlStateManager.pushMatrix();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.disableLighting();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         if (flag) {
            this.renderNameTagArmor(entity);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         GlStateManager.popMatrix();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         fontRenderer.drawString(str, (float)(-fontRenderer.getStringWidth(str)) / 2.0F, (float)b, -1, false);
         if (flag) {
            this.renderNameTagInfo(entity);
         }

         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   private void renderShadowSingle(Block block, double d, double d2, double d3, BlockPos pos, float f, float f2, double d4, double d5, double d6) {
      if (block.isFullCube()) {
         WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
         double d7 = ((double)f - (d2 - ((double)pos.getY() + d5)) / 2.0)
            * 0.5
            * (double)this.getWorldFromRenderManager().getLightBrightness(pos.getX(), pos.getY(), pos.getZ());
         if (d7 >= 0.0) {
            if (d7 > 1.0) {
               d7 = 1.0;
            }

            double d8 = (double)pos.getX() + block.getBlockBoundsMinX() + d4;
            double d9 = (double)pos.getX() + block.getBlockBoundsMaxX() + d4;
            double d10 = (double)pos.getY() + block.getBlockBoundsMinY() + d5 + 0.015625;
            double d11 = (double)pos.getZ() + block.getBlockBoundsMinZ() + d6;
            double d12 = (double)pos.getZ() + block.getBlockBoundsMaxZ() + d6;
            float f3 = (float)((d - d8) / 2.0 / (double)f2 + 0.5);
            float f4 = (float)((d - d9) / 2.0 / (double)f2 + 0.5);
            float f5 = (float)((d3 - d11) / 2.0 / (double)f2 + 0.5);
            float f6 = (float)((d3 - d12) / 2.0 / (double)f2 + 0.5);
            worldRenderer.pos(d8, d10, d11).tex((double)f3, (double)f5).color(1.0F, 1.0F, 1.0F, (float)d7).endVertex();
            worldRenderer.pos(d8, d10, d12).tex((double)f3, (double)f6).color(1.0F, 1.0F, 1.0F, (float)d7).endVertex();
            worldRenderer.pos(d9, d10, d12).tex((double)f4, (double)f6).color(1.0F, 1.0F, 1.0F, (float)d7).endVertex();
            worldRenderer.pos(d9, d10, d11).tex((double)f4, (double)f5).color(1.0F, 1.0F, 1.0F, (float)d7).endVertex();
         }
      }
   }

   public void renderCustomFontNameTagPass(Entity entity, FormattedUnicodeFontRenderer renderer, String str, double d, double d2, double d3, double d4, int n, ShaderRenderPass shaderRenderPass, boolean flag) {
      double d5 = this.renderManager.getRenderPosX() - (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)MC.mc.getTimer().renderPartialTicks);
      double d6 = this.renderManager.getRenderPosY() - (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)MC.mc.getTimer().renderPartialTicks);
      double d7 = this.renderManager.getRenderPosZ() - (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)MC.mc.getTimer().renderPartialTicks);
      double d8 = d5 * d5 + d6 * d6 + d7 * d7;
      if (d8 <= d * d) {
         float value = (float)ClientManagers.moduleManager.nameTags.scale.getValue();
         float f = (float)((double)(0.016666668F * value) + Math.sqrt(d8) / 850.0);
         GlStateManager.pushMatrix();
         GlStateManager.translate(
            (double)((float)d2 + 0.0F),
            (double)((float)d3 + entity.height + 0.5F + f * 8.0F - 0.016666668F * value * 8.0F) + ClientManagers.moduleManager.nameTags.height.getValue(),
            (double)((float)d4)
         );
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(
            Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? -this.renderManager.playerViewX : this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F
         );
         GlStateManager.scale(-f, -f, f);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         byte b = 0;
         if (str.equals("deadmau5")) {
            b = -10;
         }

         float f2 = renderer.getStringWidthFloat(str) / 2.0F;
         float fontHeight = renderer.getFontHeight();
         GlStateManager.disableTexture2D();
         if (n == 1
            && (
               shaderRenderPass == ShaderRenderPass.BLUR || shaderRenderPass == ShaderRenderPass.SHADOW && !ClientManagers.moduleManager.nameTags.shadowFont.getValue() || !ClientManagers.moduleManager.nameTags.transparent.getValue()
            )) {
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos((double)(-f2 - 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(-f2 - 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            instance.draw();
         }

         if (n == 2
            && (shaderRenderPass != ShaderRenderPass.NONE && !ClientManagers.moduleManager.nameTags.shadowFont.getValue() || !ClientManagers.moduleManager.nameTags.transparent.getValue())
            && ClientManagers.moduleManager.nameTags.outline.getValue()) {
            GL11.glLineWidth(1.0E-4F);
            worldRenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos((double)(-f2 - 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(-f2 - 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(-f2 - 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(-f2 - 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)b, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)((float)b + fontHeight), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
            instance.draw();
         }

         GlStateManager.enableTexture2D();
         if (n == 2) {
            renderer.drawString(str, -f2, (float)b, ClientManagers.moduleManager.nameTags.throughWalls.getValue() ? -1 : 553648127, false);
            if (flag) {
               this.renderNameTagInfo(entity);
            }
         }

         GlStateManager.pushMatrix();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.disableLighting();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         if (n == 2 && flag) {
            this.renderNameTagArmor(entity);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         GlStateManager.popMatrix();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         if (n == 2) {
            renderer.drawString(str, -f2, (float)b, (byte)-1, false);
            if (flag) {
               this.renderNameTagInfo(entity);
            }
         }

         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   public void renderScaledNameTag(Entity entity, String str, double d, double d2, double d3, double d4, ShaderRenderPass shaderRenderPass, boolean flag) {
      double d5 = this.renderManager.getRenderPosX() - (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)MC.mc.getTimer().renderPartialTicks);
      double d6 = this.renderManager.getRenderPosY() - (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)MC.mc.getTimer().renderPartialTicks);
      double d7 = this.renderManager.getRenderPosZ() - (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)MC.mc.getTimer().renderPartialTicks);
      double d8 = d5 * d5 + d6 * d6 + d7 * d7;
      if (d8 <= d * d) {
         FontRenderer fontRenderer = this.getFontRendererFromRenderManager();
         float value = (float)ClientManagers.moduleManager.nameTags.scale.getValue();
         float f = (float)((double)(0.016666668F * value) + Math.sqrt(d8) / 850.0);
         GlStateManager.pushMatrix();
         GlStateManager.translate(
            (double)((float)d2 + 0.0F),
            (double)((float)d3 + entity.height + 0.5F + f * 8.0F - 0.016666668F * value * 8.0F) + ClientManagers.moduleManager.nameTags.height.getValue(),
            (double)((float)d4)
         );
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(
            Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? -this.renderManager.playerViewX : this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F
         );
         GlStateManager.scale(-f, -f, f);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         byte b = 0;
         if (str.equals("deadmau5")) {
            b = -10;
         }

         float f2 = (float)fontRenderer.getStringWidth(str) / 2.0F;
         GlStateManager.disableTexture2D();
         if (shaderRenderPass != ShaderRenderPass.NONE && !ClientManagers.moduleManager.nameTags.shadowFont.getValue() || !ClientManagers.moduleManager.nameTags.transparent.getValue()) {
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos((double)(-f2 - 1.0F), (double)(b - 1), 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(-f2 - 1.0F), (double)b + 8.5, 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)b + 8.5, 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            worldRenderer.pos((double)(f2 + 1.0F), (double)(b - 1), 0.0).color(0.0F, 0.0F, 0.0F, 0.5F).endVertex();
            instance.draw();
            if (ClientManagers.moduleManager.nameTags.outline.getValue()) {
               GL11.glLineWidth(1.0E-4F);
               worldRenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);
               worldRenderer.pos((double)(-f2 - 1.0F), (double)(b - 1), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(f2 + 1.0F), (double)(b - 1), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(-f2 - 1.0F), (double)b + 8.5, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(f2 + 1.0F), (double)b + 8.5, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(-f2 - 1.0F), (double)(b - 1), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(-f2 - 1.0F), (double)b + 8.5, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(f2 + 1.0F), (double)(b - 1), 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               worldRenderer.pos((double)(f2 + 1.0F), (double)b + 8.5, 0.0).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
               instance.draw();
            }
         }

         GlStateManager.enableTexture2D();
         fontRenderer.drawString(str, -f2, (float)b, ClientManagers.moduleManager.nameTags.throughWalls.getValue() ? -1 : 553648127, false);
         if (flag) {
            this.renderNameTagInfo(entity);
         }

         GlStateManager.pushMatrix();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.disableLighting();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         if (flag) {
            this.renderNameTagArmor(entity);
         }

         BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.nameTags.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
         GlStateManager.popMatrix();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         fontRenderer.drawString(str, -f2, (float)b, -1, false);
         if (flag) {
            this.renderNameTagInfo(entity);
         }

         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   public RenderManager getRenderManager() {
      return this.renderManager;
   }

   public boolean bindEntityTexture(Entity entity) {
      ResourceLocation entityTexture = this.getEntityTexture(entity);
      if (this.locationTextureCustom != null) {
         entityTexture = this.locationTextureCustom;
      }

      if (entityTexture == null) {
         return false;
      } else {
         this.bindTexture(entityTexture);
         return true;
      }
   }

   public Render(RenderManager renderManager2) {
      this.renderManager = renderManager2;
   }

   private World getWorldFromRenderManager() {
      return this.renderManager.worldObj;
   }

   @Override
   public ResourceLocation getLocationTextureCustom() {
      return this.locationTextureCustom;
   }

   private void renderEntityOnFire(Entity entity, double d, double d2, double d3, float f) {
      GlStateManager.disableLighting();
      TextureMap textureMapBlocks = Minecraft.getMinecraft().getTextureMapBlocks();
      TextureAtlasSprite atlasSprite = textureMapBlocks.getAtlasSprite("minecraft:blocks/fire_layer_0");
      TextureAtlasSprite atlasSprite2 = textureMapBlocks.getAtlasSprite("minecraft:blocks/fire_layer_1");
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d, (float)d2, (float)d3);
      float f2 = entity.width * 1.4F;
      GlStateManager.scale(f2, f2, f2);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      float f3 = 0.5F;
      float f4 = 0.0F;
      float f5 = entity.height / f2;
      float f6 = (float)(entity.posY - entity.getEntityBoundingBox().minY);
      GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, 0.0F, -0.3F + (float)((int)f5) * 0.02F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      float f7 = 0.0F;
      int n = 0;
      boolean isMultiTexture = Config.isMultiTexture();
      if (isMultiTexture) {
         worldRenderer.setBlockLayer(EnumWorldBlockLayer.SOLID);
      }

      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);

      while (f5 > 0.0F) {
         TextureAtlasSprite textureAtlasSprite = n % 2 == 0 ? atlasSprite : atlasSprite2;
         worldRenderer.setSprite(textureAtlasSprite);
         this.bindTexture(TextureMap.locationBlocksTexture);
         float minU = textureAtlasSprite.getMinU();
         float minV = textureAtlasSprite.getMinV();
         float maxU = textureAtlasSprite.getMaxU();
         float maxV = textureAtlasSprite.getMaxV();
         if (n / 2 % 2 == 0) {
            float f8 = maxU;
            maxU = minU;
            minU = f8;
         }

         worldRenderer.pos((double)(f3 - f4), (double)(0.0F - f6), (double)f7).tex((double)maxU, (double)maxV).endVertex();
         worldRenderer.pos((double)(-f3 - f4), (double)(0.0F - f6), (double)f7).tex((double)minU, (double)maxV).endVertex();
         worldRenderer.pos((double)(-f3 - f4), (double)(1.4F - f6), (double)f7).tex((double)minU, (double)minV).endVertex();
         worldRenderer.pos((double)(f3 - f4), (double)(1.4F - f6), (double)f7).tex((double)maxU, (double)minV).endVertex();
         f5 -= 0.45F;
         f6 -= 0.45F;
         f3 *= 0.9F;
         f7 += 0.03F;
         n++;
      }

      instance.draw();
      if (isMultiTexture) {
         worldRenderer.setBlockLayer((EnumWorldBlockLayer)null);
         GlStateManager.bindCurrentTexture();
      }

      GlStateManager.popMatrix();
      GlStateManager.enableLighting();
   }

   @Override
   public void setLocationTextureCustom(ResourceLocation location) {
      this.locationTextureCustom = location;
   }
}
