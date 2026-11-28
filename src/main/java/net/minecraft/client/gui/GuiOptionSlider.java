package net.minecraft.client.gui;

import net.minecraft.client.settings.GameSettings;
import java.awt.Color;
import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class GuiOptionSlider extends GuiButton {
   private float sliderValue = 1.0F;
   public boolean dragging;
   private GameSettings.Options options;
   private final float minValue;
   private final float maxValue;

   public GuiOptionSlider(int n, int n2, int n3, GameSettings.Options options2, float f, float f2) {
      super(n, n2, n3, 150, 20, "");
      this.options = options2;
      this.minValue = f;
      this.maxValue = f2;
      Minecraft minecraft = Minecraft.getMinecraft();
      this.sliderValue = options2.normalizeValue(minecraft.gameSettings.getOptionFloatValue(options2));
      this.displayString = minecraft.gameSettings.getKeyBinding(options2);
   }

   @Override
   public boolean mousePressed(Minecraft mc, int n, int n2) {
      if (super.mousePressed(mc, n, n2)) {
         this.sliderValue = (float)(n - (this.xPosition + 4)) / (float)(this.width - 8);
         this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
         mc.gameSettings.setOptionFloatValue(this.options, this.options.denormalizeValue(this.sliderValue));
         this.displayString = mc.gameSettings.getKeyBinding(this.options);
         this.dragging = true;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int getHoverState(boolean flag) {
      return 0;
   }

   public GuiOptionSlider(int n, int n2, int n3, GameSettings.Options options2) {
      this(n, n2, n3, options2, 0.0F, 1.0F);
   }

   @Override
   public void mouseDragged(Minecraft mc, int n, int n2) {
      if (this.visible) {
         if (this.dragging) {
            this.sliderValue = (float)(n - (this.xPosition + 4)) / (float)(this.width - 8);
            this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
            float f = this.options.denormalizeValue(this.sliderValue);
            mc.gameSettings.setOptionFloatValue(this.options, f);
            this.sliderValue = this.options.normalizeValue(f);
            this.displayString = mc.gameSettings.getKeyBinding(this.options);
         }

         if (!((Module)ClientManagers.moduleManager.invisMode).isToggled() && !this.forceVanillaStyle) {
            int n3 = this.xPosition + (int)(this.sliderValue * (float)(this.width - 8));
            int yPosition2 = this.yPosition;
            byte b = 8;
            byte b2 = 20;
            int n4 = this.darkenColor(Augustus.getInstance().getClientColor(), -0.3);
            if (ClientManagers.moduleManager.clientSettings.roundedButtons.getValue()) {
               RenderUtil.drawRoundedRect(
                  (float)n3,
                  (float)yPosition2,
                  (float)(n3 + b),
                  (float)(yPosition2 + b2),
                  (float)ClientManagers.moduleManager.clientSettings.roundFactor.getValue(),
                  1,
                  new Color(n4)
               );
               GlStateManager.enableBlend();
            } else {
               RenderUtil.drawRect(n3, yPosition2, n3 + b, yPosition2 + b2, n4);
            }
         } else {
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
         }
      }
   }

   @Override
   public void mouseReleased(int n, int n2) {
      this.dragging = false;
   }
}
