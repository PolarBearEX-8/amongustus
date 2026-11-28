package net.augustus.ui.notification;

import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.ShaderRenderPass;
import java.awt.Color;
import net.augustus.modules.Module;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ToggleNotification extends AbstractNotification {
   private final int accentColor;

   @Override
   public void render(float f, BlurShadowSettings blurShadowSettings) {
      super.render(f, blurShadowSettings);
      ScaledResolution sr = new ScaledResolution(mc);
      GL11.glPushMatrix();
      GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
      String str = this.module.getModuleName() + " toggled";
      BlurShadowManager.enableOneShotPass(blurShadowSettings.isBlurActive(), blurShadowSettings.isShadowActive());
      BlurShadowManager.submitRenderTask(
         false,
         shaderRenderPass -> {
            int n2 = blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, this.accentColor));
            if (this.startX < 0) {
               RenderUtil.drawRoundedRect(
                  (float)this.currentX,
                  (float)this.currentY,
                  (float)(this.currentX + this.width),
                  (float)(this.currentY + this.height),
                  8.0F,
                  1,
                  false,
                  true,
                  false,
                  true,
                  n2
               );
            } else {
               RenderUtil.drawRoundedRect(
                  (float)this.currentX,
                  (float)this.currentY,
                  (float)(this.currentX + this.width),
                  (float)(this.currentY + this.height),
                  8.0F,
                  1,
                  true,
                  false,
                  true,
                  false,
                  n2
               );
            }
         }
      );
      int n3 = (int)((float)(this.currentY + this.height / 2) - FontManager.esp32.getFontHeight() / 2.0F);
      int n4 = (int)(((float)(this.currentX + this.width / 2)) - FontManager.esp32.getStringWidth(str) / 2.0F);
      CustomFontUtil esp32 = FontManager.esp32;
      String name2 = this.module.getModuleName();
      double d = (double)n4;
      double d2 = (double)n3;
      int rgb = Color.white.getRGB();
      esp32.drawString(name2, d, d2, rgb, false);
      CustomFontUtil esp322 = FontManager.esp32;
      d = (double)(((float)n4) + FontManager.esp32.getStringWidth(this.module.getModuleName() + " "));
      d2 = (double)n3;
      rgb = this.module.isToggled() ? Color.green.getRGB() : Color.red.getRGB();
      int n6 = rgb;
      double d5 = d2;
      double d6 = d;
      esp322.drawString("toggled", d6, d5, n6, false);
      GL11.glPopMatrix();
   }

   public ToggleNotification(int n, int n2, int n3, int n4, Module module2, int n5, int n6, int n7) {
       super(n, n2, n3, n4, module2, n5, n6);
      this.accentColor = n7;
   }

}
