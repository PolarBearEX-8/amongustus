package client.gui;

import augustus.modules.misc.ClientSettings;
import client.Client;
import client.ClientGlobals;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.EasingFunction;
import client.util.KeybindAnimation;
import client.util.RandomUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class AuxiliaryTexturesGuiIconsPngManagerAdapter extends TexturesGuiIconsPngManager {
   public static final ResourceLocation resourceLocation4;
   public int count4;
   public int count5;
   public int count;
   public int count3;
   public String labelText;
   public int count2;
   public boolean enabled3;
   public boolean enabled2;
   public boolean enabled;
   public boolean enabled4;
   public final KeybindAnimation keybindAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
   public final KeybindAnimation keybindAnimation2 = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);

   public int getCount4() {
      return this.count4;
   }

   public void setCount4(int count6) {
      this.count4 = count6;
   }

   public void applySoundHandler(SoundHandler soundHandler) {
      ClientSettings.processShort();
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private int computeCount(Color color, double offset) {
      float[] hsbValues = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
      float factor = (float)((double)hsbValues[2] + offset);
      if ((double)hsbValues[2] + offset > 1.0) {
         factor = 1.0F;
      } else if ((double)hsbValues[2] + offset < 0.0) {
         factor = 0.0F;
      }

      Color color2 = new Color(Color.HSBtoRGB(hsbValues[0], hsbValues[1], factor));
      float factor2 = 1.0F - (float)this.keybindAnimation2.getDouble3();
      int count6 = RandomUtil.processAction((double)factor2, color.getRed(), color2.getRed());
      int count7 = RandomUtil.processAction((double)factor2, color.getGreen(), color2.getGreen());
      int count8 = RandomUtil.processAction((double)factor2, color.getBlue(), color2.getBlue());
      int count9 = RandomUtil.processAction((double)factor2, color.getAlpha(), color2.getAlpha());
      return new Color(count6, count7, count8, count9).getRGB();
   }

   public void applyIndex(int count6, int count7) {
   }

   public void setCount5(int count6) {
      this.count5 = count6;
   }

   public void applyMinecraftByMinecraft(Minecraft mc, int count6, int count7) {
      if (this.enabled2) {
         if (!((Module)ClientGlobals.moduleManager.invisMode).isToggled() && !this.enabled4) {
            FontRenderer fontRendererObj = mc.fontRendererObj;
            mc.getTextureManager().applyResourceLocation3(resourceLocation4);
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            boolean enabled5 = this.enabled;
            this.enabled = count6 >= this.count && count7 >= this.count3 && count6 < this.count + this.count4 && count7 < this.count3 + this.count5;
            if (enabled5 != this.enabled) {
               this.keybindAnimation.updateEasingFunction(100, EasingFunction.easeInSine, ((this.enabled) ? 1 : 0), false);
               this.keybindAnimation2.updateEasingFunction(100, EasingFunction.easeInSine, ((this.enabled) ? 1 : 0), false);
            }

            float factor = (float)this.count4 / 2.0F;
            float factor2 = (float)this.count5 / 2.0F;
            float factor3 = (float)this.count + factor;
            float factor4 = (float)this.count3 + factor2;
            GL11.glPushMatrix();
            float factor5 = (float)(1.0 - this.keybindAnimation.getDouble3() * 0.02F);
            GL11.glScaled((double)factor5, (double)factor5, 1.0);
            float factor6 = factor3 / factor5;
            float factor7 = factor4 / factor5;
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.glStateAction3(770, 771);
            if (ClientGlobals.moduleManager.clientSettings.settingRoundedButtons.isEnabled()) {
               RenderUtils.processAction(
                  factor6 - factor,
                  factor7 - factor2,
                  factor6 + factor,
                  factor7 + factor2,
                  (float)ClientGlobals.moduleManager.clientSettings.settingRoundFactor.getValue(),
                  1,
                  new Color(this.computeCount(Client.getClient().getColor(), -0.15))
               );
               GlStateManager.disableBlend();
            } else {
               RenderUtils.processDouble4(
                  (double)(factor6 - factor),
                  (double)(factor7 - factor2),
                  (double)(factor6 + factor),
                  (double)(factor7 + factor2),
                  this.computeCount(Client.getClient().getColor(), -0.15)
               );
            }

            GL11.glEnable(3042);
            GL11.glPopMatrix();
            this.applyMinecraft(mc, count6, count7);
            int count8 = 14737632;
            if (!this.enabled3) {
               count8 = 10526880;
            } else if (this.enabled) {
               count8 = 16777120;
            }

            applyFontRenderer4(fontRendererObj, this.labelText, this.count + this.count4 / 2, this.count3 + (this.count5 - 8) / 2, count8);
         } else {
            FontRenderer fontRendererObj2 = mc.fontRendererObj;
            mc.getTextureManager().applyResourceLocation3(resourceLocation4);
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            this.enabled = count6 >= this.count && count7 >= this.count3 && count6 < this.count + this.count4 && count7 < this.count3 + this.count5;
            int count9 = this.computeCount(this.enabled);
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.glStateAction3(770, 771);
            this.processAction(this.count, this.count3, 0, 46 + count9 * 20, this.count4 / 2, this.count5);
            this.processAction(this.count + this.count4 / 2, this.count3, 200 - this.count4 / 2, 46 + count9 * 20, this.count4 / 2, this.count5);
            this.applyMinecraft(mc, count6, count7);
            int count10 = 14737632;
            if (!this.enabled3) {
               count10 = 10526880;
            } else if (this.enabled) {
               count10 = 16777120;
            }

            applyFontRenderer4(fontRendererObj2, this.labelText, this.count + this.count4 / 2, this.count3 + (this.count5 - 8) / 2, count10);
         }
      }
   }

   public int getCount5() {
      return this.count5;
   }

   public boolean matchesMinecraft(Minecraft mc, int count6, int count7) {
      return this.enabled3 && this.enabled2 && count6 >= this.count && count7 >= this.count3 && count6 < this.count + this.count4 && count7 < this.count3 + this.count5;
   }

   public int computeCount(boolean flag) {
      byte byteValue = 1;
      if (!this.enabled3) {
         byteValue = 0;
      } else if (flag) {
         byteValue = 2;
      }

      return byteValue;
   }

   public void applyMinecraft(Minecraft mc, int count6, int count7) {
   }

   private int computeCountByColor(Color color, double offset) {
      float[] hsbValues = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
      float factor = (float)((double)hsbValues[2] + offset);
      if ((double)hsbValues[2] + offset > 1.0) {
         factor = 1.0F;
      } else if ((double)hsbValues[2] + offset < 0.0) {
         factor = 0.0F;
      }

      return Color.HSBtoRGB(hsbValues[0], hsbValues[1], factor);
   }

   public void applyIndexByIndex(int count6, int count7) {
   }

   public AuxiliaryTexturesGuiIconsPngManagerAdapter(int count6, int count7, int count8, int count9, int count10, String text) {
      this(count6, count7, count8, count9, count10, text, false);
   }

   public AuxiliaryTexturesGuiIconsPngManagerAdapter(int count6, int count7, int count8, int count9, int count10, String text, boolean flag) {
      this.count4 = 200;
      this.count5 = 20;
      this.enabled3 = true;
      this.enabled2 = true;
      this.count2 = count6;
      this.count = count7;
      this.count3 = count8;
      this.count4 = count9;
      this.count5 = count10;
      this.labelText = text;
      this.enabled4 = flag;
   }

   public AuxiliaryTexturesGuiIconsPngManagerAdapter(int count6, int count7, int count8, String text) {
      this(count6, count7, count8, 200, 20, text, false);
   }

   static {
      String text = "textures/gui/widgets.png";
      resourceLocation4 = new ResourceLocation(text);
   }
}
