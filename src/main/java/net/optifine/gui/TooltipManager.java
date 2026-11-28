package net.optifine.gui;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class TooltipManager {
   private GuiScreen guiScreen;
   private TooltipProvider tooltipProvider;
   private int lastMouseX = 0;
   private int lastMouseY = 0;
   private long mouseStillTime = 0L;

   public TooltipManager(GuiScreen screen, TooltipProvider tooltipProvider2) {
      this.guiScreen = screen;
      this.tooltipProvider = tooltipProvider2;
   }

   public void drawTooltips(int n, int n2, List list) {
      if (Math.abs(n - this.lastMouseX) <= 5 && Math.abs(n2 - this.lastMouseY) <= 5) {
         if (System.currentTimeMillis() >= this.mouseStillTime + (long)((short)700)) {
            GuiButton selectedButton = GuiScreenOF.getSelectedButton(n, n2, list);
            if (selectedButton != null) {
               Rectangle tooltipBounds = this.tooltipProvider.getTooltipBounds(this.guiScreen, n, n2);
               String[] tooltipLines = this.tooltipProvider.getTooltipLines(selectedButton, tooltipBounds.width);
               if (tooltipLines != null) {
                  if (tooltipLines.length > 8) {
                     tooltipLines = Arrays.copyOf(tooltipLines, 8);
                     tooltipLines[tooltipLines.length - 1] = tooltipLines[tooltipLines.length - 1] + " ...";
                  }

                  if (this.tooltipProvider.isRenderBorder()) {
                     this.drawRectBorder(tooltipBounds.x, tooltipBounds.y, tooltipBounds.x + tooltipBounds.width, tooltipBounds.y + tooltipBounds.height, -528449408);
                  }

                  Gui.drawRect(tooltipBounds.x, tooltipBounds.y, tooltipBounds.x + tooltipBounds.width, tooltipBounds.y + tooltipBounds.height, -536870912);

                  for (int i = 0; i < tooltipLines.length; i++) {
                     String str = tooltipLines[i];
                     int n4 = 14540253;
                     if (str.endsWith("!")) {
                        n4 = 16719904;
                     }

                     Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(str, (float)(tooltipBounds.x + 5), (float)(tooltipBounds.y + 5 + i * 11), n4);
                  }
               }
            }
         }
      } else {
         this.lastMouseX = n;
         this.lastMouseY = n2;
         this.mouseStillTime = System.currentTimeMillis();
      }
   }

   private void drawRectBorder(int n, int n2, int n3, int n4, int n5) {
      Gui.drawRect(n, n2 - 1, n3, n2, n5);
      Gui.drawRect(n, n4, n3, n4 + 1, n5);
      Gui.drawRect(n - 1, n2, n, n4, n5);
      Gui.drawRect(n3, n2, n3 + 1, n4, n5);
   }
}
