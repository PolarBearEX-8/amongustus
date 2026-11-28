package net.optifine.gui;

import java.awt.Rectangle;
import net.optifine.shaders.gui.GuiButtonEnumShaderOption;
import net.optifine.shaders.gui.GuiButtonDownloadShaders;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.optifine.shaders.config.EnumShaderOption;

public class TooltipProviderEnumShaderOptions implements TooltipProvider {
   @Override
   public boolean isRenderBorder() {
      return true;
   }

   private String[] getTooltipLines(EnumShaderOption enumShaderOption) {
      return TooltipProviderOptions.getTooltipLines(enumShaderOption.getResourceKey());
   }

   @Override
   public Rectangle getTooltipBounds(GuiScreen screen, int n, int n2) {
      int n3 = screen.width - 450;
      int n4 = 35;
      if (n3 < 10) {
         n3 = 10;
      }

      if (n2 <= n4 + 94) {
         n4 += 100;
      }

      int n5 = n3 + 150 + 150;
      int n6 = n4 + 84 + 10;
      return new Rectangle(n3, n4, n5 - n3, n6 - n4);
   }

   @Override
   public String[] getTooltipLines(GuiButton button, int n) {
      if (button instanceof GuiButtonDownloadShaders) {
         return TooltipProviderOptions.getTooltipLines("of.options.shaders.DOWNLOAD");
      } else if (!(button instanceof GuiButtonEnumShaderOption)) {
         return null;
      } else {
         return this.getTooltipLines(((GuiButtonEnumShaderOption)button).getEnumShaderOption());
      }
   }
}
