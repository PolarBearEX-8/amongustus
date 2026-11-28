package net.minecraft.client.gui;

import java.awt.Color;
import net.augustus.modules.Module;
import net.augustus.modules.misc.ClientSettings;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.Augustus;
import net.augustus.utils.animation.EasingType;
import net.augustus.utils.animation.AnimationUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiButton extends Gui {
   public static final ResourceLocation buttonTextures;
   public int width;
   public int height;
   public int xPosition;
   public int yPosition;
   public String displayString;
   public int id;
   public boolean enabled;
   public boolean visible;
   public boolean hovered;
   public boolean forceVanillaStyle;
   private final AnimationUtil hoverScaleAnimation = new AnimationUtil(0.0F, 0.0F, 0.0F, 0.0F);
   private final AnimationUtil hoverColorAnimation = new AnimationUtil(0.0F, 0.0F, 0.0F, 0.0F);

   private int interpolateHoverColor(int n, double d) {
      int n2 = this.darkenColor(n, d);
      float f = 1.0F - (float)this.hoverColorAnimation.getEasedProgress();
      int n3 = ColorUtil.interpolateChannel((double)f, ColorUtil.getRed(n), ColorUtil.getRed(n2));
      int n4 = ColorUtil.interpolateChannel((double)f, ColorUtil.getGreen(n), ColorUtil.getGreen(n2));
      int n5 = ColorUtil.interpolateChannel((double)f, ColorUtil.getBlue(n), ColorUtil.getBlue(n2));
      int n6 = ColorUtil.interpolateChannel((double)f, ColorUtil.getAlpha(n), ColorUtil.getAlpha(n2));
      return new Color(n3, n4, n5, n6).getRGB();
   }

   public int darkenColor(int n, double d) {
      return RenderUtil.adjustColorBrightness(n, d);
   }

   public int getButtonWidth() {
      return this.width;
   }

   public void drawButton(Minecraft mc, int n, int n2) {
      if (this.visible) {
         if (!((Module)ClientManagers.moduleManager.invisMode).isToggled() && !this.forceVanillaStyle) {
            FontRenderer fontRendererObj = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            boolean hovered2 = this.hovered;
            this.hovered = n >= this.xPosition && n2 >= this.yPosition && n < this.xPosition + this.width && n2 < this.yPosition + this.height;
            if (hovered2 != this.hovered) {
               this.hoverScaleAnimation.start(100, EasingType.easeInSine, this.hovered ? 1 : 0, false);
               this.hoverColorAnimation.start(100, EasingType.easeInSine, this.hovered ? 1 : 0, false);
            }

            float f = (float)this.width / 2.0F;
            float f2 = (float)this.height / 2.0F;
            float f3 = (float)this.xPosition + f;
            float f4 = (float)this.yPosition + f2;
            GL11.glPushMatrix();
            float f5 = (float)(1.0 - this.hoverScaleAnimation.getEasedProgress() * 0.019999999552965164);
            GL11.glScaled((double)f5, (double)f5, 1.0);
            f3 /= f5;
            f4 /= f5;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            if (ClientManagers.moduleManager.clientSettings.roundedButtons.getValue()) {
               RenderUtil.drawRoundedRect(
                  f3 - f,
                  f4 - f2,
                  f3 + f,
                  f4 + f2,
                  (float)ClientManagers.moduleManager.clientSettings.roundFactor.getValue(),
                  1,
                  new Color(this.interpolateHoverColor(Augustus.getInstance().getClientColor(), -0.15))
               );
               GlStateManager.enableBlend();
            } else {
               RenderUtil.drawRect(
                  (double)(f3 - f),
                  (double)(f4 - f2),
                  (double)(f3 + f),
                  (double)(f4 + f2),
                  this.interpolateHoverColor(Augustus.getInstance().getClientColor(), -0.15)
               );
            }

            GlStateManager.enableBlend();
            GL11.glPopMatrix();
            this.mouseDragged(mc, n, n2);
            int n3 = 14737632;
            if (!this.enabled) {
               n3 = 10526880;
            } else if (this.hovered) {
               n3 = 16777120;
            }

            drawCenteredString(fontRendererObj, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, n3);
         } else {
            FontRenderer fontRendererObj2 = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = n >= this.xPosition && n2 >= this.yPosition && n < this.xPosition + this.width && n2 < this.yPosition + this.height;
            int hoverState = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + hoverState * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + hoverState * 20, this.width / 2, this.height);
            this.mouseDragged(mc, n, n2);
            int n4 = 14737632;
            if (!this.enabled) {
               n4 = 10526880;
            } else if (this.hovered) {
               n4 = 16777120;
            }

            drawCenteredString(fontRendererObj2, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, n4);
         }
      }
   }

   public GuiButton(int n, int n2, int n3, String str) {
      this(n, n2, n3, 200, 20, str, false);
   }

   public void drawButtonForegroundLayer(int n, int n2) {
   }

   static {
      String str = "textures/gui/widgets.png";
      buttonTextures = new ResourceLocation(str);
   }

   public boolean isMouseOver() {
      return this.hovered;
   }

   public GuiButton(int n, int n2, int n3, int n4, int n5, String str, boolean flag) {
      this.width = 200;
      this.height = 20;
      this.enabled = true;
      this.visible = true;
      this.id = n;
      this.xPosition = n2;
      this.yPosition = n3;
      this.width = n4;
      this.height = n5;
      this.displayString = str;
      this.forceVanillaStyle = flag;
   }

   public void playPressSound(SoundHandler soundHandler) {
      ClientSettings.playButtonClickSound();
   }

   public int getHeight() {
      return this.height;
   }

   public GuiButton(int n, int n2, int n3, int n4, int n5, String str) {
      this(n, n2, n3, n4, n5, str, false);
   }

   public void mouseDragged(Minecraft mc, int n, int n2) {
   }

   public void setHeight(int n) {
      this.height = n;
   }

   public boolean mousePressed(Minecraft mc, int n, int n2) {
      return this.enabled && this.visible && n >= this.xPosition && n2 >= this.yPosition && n < this.xPosition + this.width && n2 < this.yPosition + this.height;
   }

   public void setWidth(int n) {
      this.width = n;
   }

   public void mouseReleased(int n, int n2) {
   }

   public int getHoverState(boolean flag) {
      byte b = 1;
      if (!this.enabled) {
         b = 0;
      } else if (flag) {
         b = 2;
      }

      return b;
   }

}
