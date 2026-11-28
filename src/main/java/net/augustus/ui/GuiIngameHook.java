package net.augustus.ui;

import net.augustus.ClientManagers;
import net.augustus.font.CustomFontUtil;
import net.augustus.render.HudBlurCompositor;
import net.augustus.render.RenderPassTask;
import net.augustus.render.RenderUtil;
import net.augustus.utils.network.ServerPingUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.ShaderRenderPass;
import java.awt.Color;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import org.lwjgl.opengl.GL11;

public class GuiIngameHook extends GuiIngame implements MC {
   private boolean overlayFadeActive;
   private int overlayCounter;
   public static CustomFontUtil hudFont;
   public static float hudTopOffsetY = 0.0F;
   private ResourceLocation augustusTexture;
   public static float arrayListBottomY;
   private final ColorUtil colorUtil;
   private ResourceLocation augustusTexture2;

   @Override
   public void renderGameOverlay(float f) {
      ScaledResolution sr = new ScaledResolution(MC.mc);
      MC.mc.entityRenderer.setupOverlayRendering(sr);
      GlStateManager.enableBlend();
      if (Config.isVignetteEnabled()) {
         this.renderVignette(MC.mc.thePlayer.getBrightness(f), sr);
      }

      this.disableVignette = true;
      HudBlurCompositor.beginHudCapture();
      super.renderGameOverlay(f);
      this.disableVignette = false;

      try {
         if (ClientManagers.moduleManager.hotbar.isToggled() && ClientManagers.moduleManager.hotbar.armor.getValue()) {
            GL11.glPushMatrix();
            GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
            GL11.glScaled(ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), ClientManagers.moduleManager.hotbar.hotbarScale.getValue(), 1.0);
            GL11.glTranslated(0.0, (double)ClientManagers.moduleManager.hotbar.getInventoryHudYOffset(), 0.0);
            ScaledResolution sr2 = new ScaledResolution(
               (double)MC.mc.displayWidth * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
               (double)MC.mc.displayHeight * (1.0 / ClientManagers.moduleManager.hotbar.hotbarScale.getValue()),
               (int)ClientManagers.moduleManager.hotbar.hotbarScale.getValue()
            );
            this.drawArmorHud(sr2);
            GL11.glPopMatrix();
         }

         if (ClientManagers.moduleManager.oldNotification.isToggled()) {
            ClientManagers.moduleManager.oldNotification.renderNotifications(f);
         }

         if (ClientManagers.moduleManager.fovArrows.isToggled()) {
            BlurShadowManager.enableOneShotPass(ClientManagers.moduleManager.fovArrows.blurShadowSettings.isBlurActive(), ClientManagers.moduleManager.fovArrows.blurShadowSettings.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderNotificationsPass(shaderRenderPass));
         }
      } catch (Exception e) {
      }

      HudBlurCompositor.compositeHud(sr, false, () -> this.renderVanillaCrosshair(sr));
   }

   private static void renderHelmetSlot(int n, int n2, boolean flag, ShaderRenderPass shaderRenderPass) {
      if (MC.mc.thePlayer.getCurrentArmor(3) != null) {
         RenderUtil.renderItemIntoGuiElevated(MC.mc.thePlayer.getCurrentArmor(3), n - 81, n2, flag);
      }
   }

   public static void drawHudStats(int n, int n2) {
      if (ClientManagers.moduleManager.hotbar.debugInformation.getValue()) {
         FontRenderer fontRendererObj = MC.mc.fontRendererObj;
         int n3 = ClientManagers.moduleManager.hotbar.otherPingCalculation.getValue()
            ? ServerPingUtil.getPing()
            : (
               MC.mc.getNetHandler() != null && MC.mc.getNetHandler().getPlayerInfo(MC.mc.thePlayer.getUniqueID()) != null
                  ? MC.mc.getNetHandler().getPlayerInfo(MC.mc.thePlayer.getUniqueID()).getResponseTime()
                  : 0
            );
         int n4 = ClientManagers.moduleManager.protector.isToggled()
            ? MathHelper.floor_double(MC.mc.thePlayer.posX) + ClientManagers.moduleManager.protector.getRandomXOffset()
            : MathHelper.floor_double(MC.mc.thePlayer.posX);
         int n5 = MathHelper.floor_double(MC.mc.thePlayer.posY);
         int n6 = ClientManagers.moduleManager.protector.isToggled()
            ? MathHelper.floor_double(MC.mc.thePlayer.posZ) + ClientManagers.moduleManager.protector.getRandomZOffset()
            : MathHelper.floor_double(MC.mc.thePlayer.posZ);
         float f = 15.0F;
         String str = "§fFPS: §7" + Minecraft.getDebugFPS() + "   §fPing: §7" + n3 + "   §fCPS: §7" + ClientManagers.moduleManager.hotbar.cps;
         if ((float)n / 2.0F - 91.0F > (float)(fontRendererObj.getStringWidth(str) + 6) + f) {
            fontRendererObj.drawStringNoColorFilter(str, (int)(6.0F + f), n2 - 20, Color.white.getRGB());
         } else {
            fontRendererObj.drawStringNoColorFilter("§fFPS: §7" + Minecraft.getDebugFPS() + "   §fPing: §7" + n3, (int)(6.0F + f), n2 - 20, Color.white.getRGB());
         }

         String str2 = "§fX: §7" + n4 + "  §fY: §7" + n5 + "  §fZ: §7" + n6 + "  §fBPS: §7" + ClientManagers.moduleManager.hotbar.blocksPerSecond;
         if ((float)n / 2.0F - 91.0F > (float)(fontRendererObj.getStringWidth(str2) + 6) + f) {
            fontRendererObj.drawStringNoColorFilter(str2, (int)(6.0F + f), n2 - 9, Color.white.getRGB());
         } else {
            fontRendererObj.drawStringNoColorFilter("§fX: §7" + n4 + "  §fY: §7" + n5 + "  §fZ: §7" + n6, (int)(6.0F + f), n2 - 9, Color.white.getRGB());
         }

         if (!ClientManagers.moduleManager.protector.isToggled() || !ClientManagers.moduleManager.protector.time.getValue()) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            String str3 = DateFormat.getDateInstance(2).format(gregorianCalendar.getTime());
            String str4 = DateFormat.getTimeInstance(3).format(gregorianCalendar.getTime());
            float stringWidth = (float)fontRendererObj.getStringWidth(str3);
            fontRendererObj.drawStringWithShadowOffset(str3, (float)(n - 10) - stringWidth, (float)(n2 - 1 - fontRendererObj.FONT_HEIGHT), -570425345, true, 0.5F);
            fontRendererObj.drawStringWithShadowOffset(
               str4,
               (float)((double)(n - 10) - (double)fontRendererObj.getStringWidth(str4) * 0.5 - (double)stringWidth * 0.5),
               (float)(n2 - 11 - fontRendererObj.FONT_HEIGHT),
               -570425345,
               true,
               0.5F
            );
         }
      }
   }

   private void renderVanillaCrosshair(ScaledResolution sr) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      MC.mc.getTextureManager().bindTexture(icons);
      GlStateManager.enableBlend();
      if (this.showCrosshair() && (MC.mc.gameSettings.thirdPersonView < 1 || ClientManagers.moduleManager.crosshair.isToggled() && ClientManagers.moduleManager.crosshair.thirdPerson.getValue())) {
         GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
         GlStateManager.enableAlpha();
         if (!ClientManagers.moduleManager.crosshair.isToggled() || ClientManagers.moduleManager.crosshair.defaultCrossHair.getValue()) {
            GL11.glPushMatrix();
            int scaledWidth = sr.getScaledWidth();
            int scaledHeight = sr.getScaledHeight();
            if (ClientManagers.moduleManager.crosshair.isToggled()) {
               GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
               GL11.glScaled(ClientManagers.moduleManager.crosshair.scale.getValue(), ClientManagers.moduleManager.crosshair.scale.getValue(), 1.0);
               ScaledResolution sr2 = new ScaledResolution(
                  (double)MC.mc.displayWidth * (1.0 / ClientManagers.moduleManager.crosshair.scale.getValue()),
                  (double)MC.mc.displayHeight * (1.0 / ClientManagers.moduleManager.crosshair.scale.getValue()),
                  (int)ClientManagers.moduleManager.crosshair.scale.getValue()
               );
               scaledWidth = sr2.getScaledWidth();
               scaledHeight = sr2.getScaledHeight();
            }

            this.drawTexturedModalRect(scaledWidth / 2 - 7, scaledHeight / 2 - 7, 0, 0, 16, 16);
            GL11.glPopMatrix();
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F);
      }
   }

   public static void drawCustomCrosshair(ScaledResolution sr) {
      GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
      double d = (double)MC.mc.getFramebuffer().framebufferWidth / 2.0;
      double d2 = (double)MC.mc.getFramebuffer().framebufferHeight / 2.0;
      int value = (int)ClientManagers.moduleManager.crosshair.width.getValue();
      int value2 = (int)ClientManagers.moduleManager.crosshair.length.getValue();
      int value3 = (int)ClientManagers.moduleManager.crosshair.margin.getValue();
      int colorInt = ClientManagers.moduleManager.crosshair.color2.getColorInt((byte)0);
      if (value % 2 == 0) {
         value /= 2;
         RenderUtil.drawRect(
            d - (double)value, d2 + (double)value3 + (double)value2, d + (double)value, d2 + (double)value3, colorInt
         );
         if (!ClientManagers.moduleManager.crosshair.tStyle.getValue()) {
            RenderUtil.drawRect(
               d - (double)value, d2 - (double)value3 - (double)value2, d + (double)value, d2 - (double)value3, colorInt
            );
         }

         RenderUtil.drawRect(
            d + (double)value3, d2 + (double)value, d + (double)value3 + (double)value2, d2 - (double)value, colorInt
         );
         RenderUtil.drawRect(
            d - (double)value3 - (double)value2, d2 + (double)value, d - (double)value3, d2 - (double)value, colorInt
         );
         if (ClientManagers.moduleManager.crosshair.dot.getValue()) {
            RenderUtil.drawRect(d - (double)value, d2 + (double)value, d + (double)value, d2 - (double)value, colorInt);
         }
      } else {
         value = (value - 1) / 2;
         double d3 = d - 1.0 - (double)value;
         double d4 = d2 + (double)value3 + (double)value2;
         double d5 = d + (double)value;
         double d6 = d2 + (double)value3 + (double)(value3 > 0 ? -1 : 0);
         double d7 = d5;
         double d8 = d4;
         RenderUtil.drawRect(d3, d8, d7, d6, colorInt);
         if (!ClientManagers.moduleManager.crosshair.tStyle.getValue()) {
            d3 = d - 1.0 - (double)value;
            double d9 = d2 - (double)value3 - (double)value2 - 1.0;
            d5 = d + (double)value;
            d6 = d2 - (double)value3;
            d7 = d5;
            d8 = d9;
            RenderUtil.drawRect(d3, d8, d7, d6, colorInt);
         }

         RenderUtil.drawRect(
            d + (double)value3 + (double)(value3 > 0 ? -1 : 0),
            d2 + (double)value,
            d + (double)value3 + (double)value2,
            d2 - (double)value - 1.0,
            colorInt
         );
         RenderUtil.drawRect(
            d - (double)value3 - (double)value2 - 1.0,
            d2 + (double)value,
            d - (double)value3,
            d2 - (double)value - 1.0,
            colorInt
         );
         if (ClientManagers.moduleManager.crosshair.dot.getValue()) {
            RenderUtil.drawRect(
               d - 1.0 - (double)value, d2 + (double)value, d + (double)value, d2 - 1.0 - (double)value, colorInt
            );
         }
      }

      GL11.glScaled((double)sr.getScaleFactor(), (double)sr.getScaleFactor(), 1.0);
   }

   private static void renderLeggingsSlot(int n, int n2, boolean flag, ShaderRenderPass shaderRenderPass) {
      if (MC.mc.thePlayer.getCurrentArmor(1) != null) {
         RenderUtil.renderItemIntoGuiElevated(MC.mc.thePlayer.getCurrentArmor(1), n - 41, n2, flag);
      }
   }

   private void drawArmorHud(ScaledResolution sr) {
      GlStateManager.enableTexture2D();
      int n = sr.getScaledHeight() - 56;
      int n2 = sr.getScaledWidth() / 2 + 91;
      boolean value = ClientManagers.moduleManager.hotbar.armorDurability.getValue();
      BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.hotbar.armorShadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderHelmetSlot(n2, n, value, shaderRenderPass));
      BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.hotbar.armorShadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
      BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> renderChestplateSlot(n2, n, value, shaderRenderPass2));
      BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.hotbar.armorShadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
      BlurShadowManager.submitRenderTask(false, shaderRenderPass3 -> renderLeggingsSlot(n2, n, value, shaderRenderPass3));
      BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.hotbar.armorShadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
      BlurShadowManager.submitRenderTask(false, shaderRenderPass4 -> {
         if (MC.mc.thePlayer.getCurrentArmor(0) != null) {
            RenderUtil.renderItemIntoGuiElevated(MC.mc.thePlayer.getCurrentArmor(0), n2 - 21, n, value);
         }
      });
   }


   @Override
   public void updateTick() {
      super.updateTick();

      try {
         if (ClientManagers.moduleManager.fovArrows.isToggled()) {
            ClientManagers.moduleManager.fovArrows.updateTargetPlayers();
         }
      } catch (Exception e) {
      }
   }

   public GuiIngameHook(Minecraft mc2) {
       super(mc2);
      this.overlayFadeActive = false;
      this.overlayCounter = 0;
      this.colorUtil = new ColorUtil();
      this.augustusTexture2 = null;
      this.augustusTexture = null;
      this.augustusTexture2 = new ResourceLocation("augustus", "pictures/augustus.png");
      this.augustusTexture = new ResourceLocation("augustus", "pictures/amogus.png");
      hudFont = new CustomFontUtil("Verdana", 16);
   }

   private static void renderNotificationsPass(ShaderRenderPass shaderRenderPass) {
      ClientManagers.moduleManager.fovArrows.renderArrowsPass(shaderRenderPass);
   }

   private static void renderChestplateSlot(int n, int n2, boolean flag, ShaderRenderPass shaderRenderPass) {
      if (MC.mc.thePlayer.getCurrentArmor(2) != null) {
         RenderUtil.renderItemIntoGuiElevated(MC.mc.thePlayer.getCurrentArmor(2), n - 61, n2, flag);
      }
   }

}
