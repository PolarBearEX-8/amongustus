package client.gui;

import client.font.FontFace;
import client.font.FontRegistry;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.ShaderOptions;
import client.util.MinecraftAccessor;
import client.util.MovementHandler;
import client.util.RandomUtil;
import java.awt.Color;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class NotificationEntry extends AbstractNotification {
   public final int entryIndex;

   private void renderShaderPass(ShaderOptions shaderOptions, RenderPass renderPass) {
      int entryIndex2 = this.entryIndex;
      int index = RandomUtil.getIndex(renderPass, entryIndex2);
      int index2 = shaderOptions.getIndex(renderPass, index);
      if (super.alpha < 0) {
         RenderUtils.processFloat14(
            (float)super.shadowColor,
            (float)super.colorCode,
            (float)(super.shadowColor + super.yPos),
            (float)(super.colorCode + super.width),
            8.0F,
            1,
            false,
            true,
            false,
            true,
            index2
         );
      } else {
         RenderUtils.processFloat14(
            (float)super.shadowColor,
            (float)super.colorCode,
            (float)(super.shadowColor + super.yPos),
            (float)(super.colorCode + super.width),
            8.0F,
            1,
            true,
            false,
            true,
            false,
            index2
         );
      }
   }

   @Override
   public void renderNotification(float factor, ShaderOptions shaderOptions) {
      super.renderNotification(factor, shaderOptions);
      ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
      GL11.glPushMatrix();
      GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
      String text = super.targetModule.getDescription() + " toggled";
      MovementHandler.processBoolean(shaderOptions.canExecute(), shaderOptions.canExecute2());
      MovementHandler.applyRunnableTask(false, renderPass -> this.renderShaderPass(shaderOptions, renderPass));
      int count = (int)((float)(super.colorCode + super.width / 2) - FontRegistry.fontFace10.getFontSize() / 2.0F);
      int count2 = (int)((float)(super.shadowColor + super.yPos / 2) - FontRegistry.fontFace10.getScaleFactor(text) / 2.0F);
      FontFace fontFace = FontRegistry.fontFace10;
      String description = super.targetModule.getDescription();
      double offset = (double)count2;
      double offset2 = (double)count;
      int rgb = Color.white.getRGB();
      double offset3 = offset2;
      double offset4 = offset;
      String text2 = description;
      fontFace.applyString6(text2, offset4, offset3, rgb, false);
      fontFace = FontRegistry.fontFace10;
      float factor2 = (float)count2;
      FontFace fontFace2 = FontRegistry.fontFace10;
      String text3 = super.targetModule.getDescription() + " ";
      offset = (double)(factor2 + fontFace2.getScaleFactor(text3));
      offset2 = (double)count;
      int count3 = super.targetModule.isToggled() ? Color.green.getRGB() : Color.red.getRGB();
      double offset5 = offset2;
      double offset6 = offset;
      String text4 = "toggled";
      fontFace.applyString6(text4, offset6, offset5, count3, false);
      GL11.glPopMatrix();
   }

   public NotificationEntry(int count, int count2, int count3, int count4, Module module, int count5, int count6, int count7) {
      super(count, count2, count3, count4, module, count5, count6);
      this.entryIndex = count7;
   }
}
