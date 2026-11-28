package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.ClientManagers;
import net.augustus.settings.ColorSetting;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class Coordinates extends Module implements RenderListener {
   private final DoubleValue posX;
   private final DoubleValue posY;
   private final DoubleValue scale;
   private final ColorSetting labelColor;
   private final ColorSetting valueColor;
   private final BooleanValue backGround;
   private final ColorSetting backgroundColor;
   private final BlurShadowSettings backGround2;

   public Coordinates() {
       super("Coordinates", "Koordinaten", new Color(209, 69, 81), Categorys.RENDER);
      this.posX = new DoubleValue(this, "PosX", 0.0, 0.0, 100.0, 2);
      this.posY = new DoubleValue(this, "PosY", 0.0, 0.0, 100.0, 2);
      this.scale = new DoubleValue(this, "Scale", 2.0, 1.0, 4.0, 1);
      this.labelColor = new ColorSetting(this, "Color1", new Color(85, 85, 85));
      this.valueColor = new ColorSetting(this, "Color2", new Color(-1));
      this.backGround = new BooleanValue(this, "BackGround", true);
      ColorSetting colorSetting = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100));
      BooleanValue backGround3 = this.backGround;
      BooleanValue booleanValue = backGround3;
      this.backgroundColor = (ColorSetting)colorSetting.withVisibility(() -> booleanValue.getValue());
      BlurShadowSettings blurShadowSettings2 = new BlurShadowSettings(this, "BackGround").withCustomShadowColor(Color.black);
      backGround3 = this.backGround;
      BooleanValue booleanValue2 = backGround3;
      this.backGround2 = (BlurShadowSettings)blurShadowSettings2.withVisibility(() -> booleanValue2.getValue());
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      int n;
      int n2;
      int n3;
      int n4;
      int n5;
      float f2;
      int n6;
      int n7;
      int n8;
      boolean flag;
      boolean flag2;
      n = ClientManagers.moduleManager.protector.isToggled()
            ? MathHelper.floor_double(mc.thePlayer.posX) + ClientManagers.moduleManager.protector.getRandomXOffset()
            : MathHelper.floor_double(mc.thePlayer.posX);
         n2 = MathHelper.floor_double(mc.thePlayer.posY);
         n3 = ClientManagers.moduleManager.protector.isToggled()
            ? MathHelper.floor_double(mc.thePlayer.posZ) + ClientManagers.moduleManager.protector.getRandomZOffset()
            : MathHelper.floor_double(mc.thePlayer.posZ);
         n4 = this.labelColor.getColorInt((byte)0);
         n5 = this.valueColor.getColorInt((byte)0);
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
         GlStateManager.scale(this.scale.getValue(), this.scale.getValue(), 1.0);
         double d = (double)mc.displayWidth / this.scale.getValue();
         double d2 = (double)mc.displayHeight / this.scale.getValue();
         int n9 = MathHelper.ceiling_double_int(d);
         int n10 = MathHelper.ceiling_double_int(d2);
         String str = "X: " + n + "  Y: " + n2 + "  Z: " + n3;
         f2 = (float)mc.fontRendererObj.getStringWidth(str);
         n6 = (int)((double)((float)n9 - f2) * (this.posX.getValue() / 100.0));
         n7 = (int)((double)(n10 - mc.fontRendererObj.FONT_HEIGHT) * (this.posY.getValue() / 100.0));
         n8 = this.backgroundColor.getColorInt((byte)0);
         flag = this.backGround.getValue() && this.backGround2.isBlurActive();
if (this.backGround.getValue()) {
if (this.backGround2.isShadowActive()) {
flag2 = true;
} else {
flag2 = false;
}
} else {
flag2 = false;
}

      BlurShadowManager.enableOneShotPass(flag, flag2);
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderCoordinatesPass(n6, n7, f2, n8, n, n2, n4, n5, n3, shaderRenderPass));
      GlStateManager.popMatrix();
      RenderUtil.resetGlColorState();
   }

   private void renderCoordinatesPass(int n, int n2, float f, int n3, int n4, int n5, int n6, int n7, int n8, ShaderRenderPass shaderRenderPass) {
      if (shaderRenderPass == ShaderRenderPass.NONE) {
         if (this.backGround.getValue()) {
            RenderUtil.drawRect(
               (double)(n - 1),
               (double)(n2 - 1),
               (double)((float)n + f + 1.0F),
               (double)(n2 + mc.fontRendererObj.FONT_HEIGHT),
               n3
            );
         }

         float stringWidth = (float)mc.fontRendererObj.getStringWidth("" + n4);
         float stringWidth2 = (float)mc.fontRendererObj.getStringWidth("" + n5);
         mc.fontRendererObj.drawStringWithShadow("X: ", (float)n, (float)n2, n6);
         mc.fontRendererObj.drawStringWithShadow("" + n4, (float)(n + 11), (float)n2, n7);
         mc.fontRendererObj.drawStringWithShadow("  Y: ", (float)(n + 11) + stringWidth, (float)n2, n6);
         mc.fontRendererObj.drawStringWithShadow("" + n5, (float)(n + 11) + stringWidth + 17.0F, (float)n2, n7);
         mc.fontRendererObj.drawStringWithShadow("  Z: ", (float)(n + 11) + stringWidth + 17.0F + stringWidth2, (float)n2, n6);
         mc.fontRendererObj.drawStringWithShadow("" + n8, (float)(n + 11) + stringWidth + 17.0F + stringWidth2 + 17.0F, (float)n2, n7);
      } else {
         RenderUtil.drawRect((double)(n - 1), (double)(n2 - 1), (double)((float)n + f + 1.0F), (double)(n2 + mc.fontRendererObj.FONT_HEIGHT), this.backGround2.resolveShadowColor(ColorUtil.resolvePassColor(shaderRenderPass, n3)));
      }
   }

}
