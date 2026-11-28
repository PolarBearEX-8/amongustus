package net.augustus.modules.modes.targethud;

import net.augustus.render.RenderUtil;
import net.augustus.render.StencilUtil;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.ShaderRenderPass;
import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.render.TargetHud;
import net.augustus.settings.SettingGroup;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class MinecraftTargetHudMode extends AbstractTargetHudMode {
   private final ColorSetting backGroundColor;
   private final ColorSetting healthBackGroundColor;
   private final ColorSetting healthColor;
   private final ColorSetting textColor;

   public MinecraftTargetHudMode(TargetHud targetHud, SettingGroup doubleValue) {
      super(targetHud, doubleValue, "Minecraft", true);
      String str = this.getName() + "BackGroundColor";
      Color color = new Color(0, 0, 0, 180);
      String str2 = str;
      this.backGroundColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("BackGroundColor")).withVisibility(() -> this.isSelected());
      str = this.getName() + "HealthBackGroundColor";
      color = new Color(0, 0, 0);
      str2 = str;
      this.healthBackGroundColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("HealthBackGroundColor")).withVisibility(() -> this.isSelected());
      str = this.getName() + "HealthColor";
      color = new Color(255, 0, 0);
      str2 = str;
      this.healthColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("HealthColor")).withVisibility(() -> this.isSelected());
      str = this.getName() + "TextColor";
      color = new Color(255, 255, 255);
      str2 = str;
      this.textColor = (ColorSetting)((ColorSetting)new ColorSetting(targetHud, str2, color, doubleValue).withDisplayName("TextColor")).withVisibility(() -> this.isSelected());
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new MinecraftTargetHudMode((TargetHud)obj, (SettingGroup)obj2);
   }

   @Override
   public int getHalfWidth() {
      return 156;
   }

   @Override
   public int getHalfHeight() {
      return 56;
   }

   @Override
   public void drawHudContent(AbstractClientPlayer clientPlayer, NetworkPlayerInfo networkPlayerInfo, ScaledResolution sr) {
      boolean isUpsideDownPlayer = this.isUpsideDownPlayer(clientPlayer);
      int colorInt = this.backGroundColor.getColorInt((byte)0);
      BlurShadowManager.enableOneShotPass(((TargetHud)this.parentModule).blurShadowSettings.isBlurActive(), ((TargetHud)this.parentModule).blurShadowSettings.isShadowActive());
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> {
         float f = (float)(-this.getHalfWidth());
         int n;
         float f2 = (float)(-this.getHalfHeight());
         float halfWidth = (float)this.getHalfWidth();
         float halfHeight = (float)this.getHalfHeight();
         BlurShadowSettings blurShadowSettings = ((TargetHud)this.parentModule).blurShadowSettings;
         int n2 = ColorUtil.resolvePassColor(shaderRenderPass, colorInt);
         RenderUtil.drawRoundedRect(f, f2, halfWidth, halfHeight, 10.0F, 1, blurShadowSettings.resolvePassColor(shaderRenderPass, n2));
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (shaderRenderPass == ShaderRenderPass.NONE) {
            StencilUtil.initStencilToWrite();
            StencilUtil.beginStencilWrite();
            RenderUtil.drawRoundedRect(50.0F, -50.0F, 150.0F, 50.0F, 8.0F, 1, Color.white);
            StencilUtil.readStencilBuffer(1);
            this.drawPlayerHead(networkPlayerInfo.getLocationSkin(), clientPlayer, isUpsideDownPlayer, (byte)50, (byte)-50, (byte)100);
            StencilUtil.uninitStencilBuffer();
         }

         byte b5 = 2;
         if (shaderRenderPass == ShaderRenderPass.NONE) {
            GlStateManager.pushMatrix();
            GlStateManager.scale((float)b5, (float)b5, 1.0F);
            FontRenderer fontRendererObj = mc.fontRendererObj;
            String name2 = clientPlayer.getGameProfile().getName();
            n = -145 / b5;
            fontRendererObj.drawString(name2, n, -45 / b5, this.textColor.getColorInt((byte)0));
            GlStateManager.popMatrix();
         }

         this.renderHealthBar(clientPlayer, shaderRenderPass);
         this.renderEquipment(clientPlayer, shaderRenderPass, b5);
         BlurShadowManager.enableNormalPassOnce();
      });
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   private void renderHealthBar(AbstractClientPlayer clientPlayer, ShaderRenderPass shaderRenderPass) {
      short s = 190;
      if (shaderRenderPass == ShaderRenderPass.NONE) {
         this.updateHealthAnimation(clientPlayer, this.getPlayerHealth(clientPlayer));
      }

      float f = this.currentHealth / clientPlayer.getMaxHealth();
      if (shaderRenderPass == ShaderRenderPass.NONE) {
         float f2 = this.previousHealth / clientPlayer.getMaxHealth();
         this.healthAnimation.setBounds((float)s * f2, 0.0F, (float)s * f, 0.0F);
      }

      if (shaderRenderPass != ShaderRenderPass.BLUR
         && (float)s * f > -150.0F
         && mc.getCurrentServerData() != null
         && !mc.getCurrentServerData().serverIP.contains("gommehd.net")) {
         BlurShadowSettings blurShadowSettings = ((TargetHud)this.parentModule).blurShadowSettings;
         int colorInt = this.healthBackGroundColor.getColorInt((byte)0);
         RenderUtil.drawRoundedRect(-150.0F, -17.0F, 40.0F, 3.0F, 3.0F, 1, blurShadowSettings.resolvePassColor(shaderRenderPass, colorInt));
         float f3 = MathHelper.clamp_float(-150.0F + this.healthAnimation.updateX(), -150.0F, 40.0F);
         blurShadowSettings = ((TargetHud)this.parentModule).blurShadowSettings;
         int colorInt2 = this.healthColor.getColorInt((byte)0);
         RenderUtil.drawRoundedRect(-150.0F, -17.0F, f3, 3.0F, 3.0F, 1, blurShadowSettings.resolvePassColor(shaderRenderPass, colorInt2));
      }
   }

   private void renderEquipment(AbstractClientPlayer clientPlayer, ShaderRenderPass shaderRenderPass, int n) {
      if (shaderRenderPass != ShaderRenderPass.BLUR) {
         int n2 = -2;
         if (mc.getCurrentServerData() != null && !mc.getCurrentServerData().serverIP.contains("gommehd.net")) {
            n2 += 10;
         }

         GlStateManager.pushMatrix();
         GlStateManager.scale((float)n, (float)n, 1.0F);
         GlStateManager.pushMatrix();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         RenderHelper.enableGUIStandardItemLighting();
         if (clientPlayer.getCurrentArmor(3) != null) {
            RenderUtil.renderItemIntoGuiElevated(clientPlayer.getCurrentArmor(3), (byte)-77, n2, false);
         }

         if (clientPlayer.getCurrentArmor(2) != null) {
            RenderUtil.renderItemIntoGuiElevated(clientPlayer.getCurrentArmor(2), (byte)-59, n2, false);
         }

         if (clientPlayer.getCurrentArmor(1) != null) {
            RenderUtil.renderItemIntoGuiElevated(clientPlayer.getCurrentArmor(1), (byte)-41, n2, false);
         }

         if (clientPlayer.getCurrentArmor(0) != null) {
            RenderUtil.renderItemIntoGuiElevated(clientPlayer.getCurrentArmor(0), (byte)-23, n2, false);
         }

         if (clientPlayer.getCurrentEquippedItem() != null) {
            RenderUtil.renderItemIntoGuiElevated(clientPlayer.getCurrentEquippedItem(), (byte)0, n2 - 1, false);
         }

         GlStateManager.popMatrix();
         GlStateManager.popMatrix();
      }
   }

}
