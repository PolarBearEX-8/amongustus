package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

public class GuiLabel extends Gui {
   public int width = 200;
   public int height = 20;
   public int x;
   public int y;
   private List labels;
   public int id;
   private boolean centered;
   public boolean visible = true;
   private boolean labelBgEnabled;
   private int textColor;
   private int backColor;
   private int ulColor;
   private int brColor;
   private FontRenderer fontRenderer;
   private int border;

   public void addLine(String str) {
      this.labels.add(I18n.format(str, new Object[0]));
   }

   public GuiLabel setCentered() {
      this.centered = true;
      return this;
   }

   public void drawLabelBackground(Minecraft mc, int n, int n2) {
      if (this.labelBgEnabled) {
         int n3 = this.width + this.border * 2;
         int n4 = this.height + this.border * 2;
         int n5 = this.x - this.border;
         int n6 = this.y - this.border;
         drawRect(n5, n6, n5 + n3, n6 + n4, this.backColor);
         this.drawHorizontalLine(n5, n5 + n3, n6, this.ulColor);
         this.drawHorizontalLine(n5, n5 + n3, n6 + n4, this.brColor);
         this.drawVerticalLine(n5, n6, n6 + n4, this.ulColor);
         this.drawVerticalLine(n5 + n3, n6, n6 + n4, this.brColor);
      }
   }

   public void drawLabel(Minecraft mc, int n, int n2) {
      if (this.visible) {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         this.drawLabelBackground(mc, n, n2);
         int n4 = (this.y + this.height / 2 + this.border / 2) - this.labels.size() * 10 / 2;

         for (int i = 0; i < this.labels.size(); i++) {
            if (this.centered) {
               drawCenteredString(this.fontRenderer, (String)this.labels.get(i), this.x + this.width / 2, n4 + i * 10, this.textColor);
            } else {
               this.drawString(this.fontRenderer, (String)this.labels.get(i), this.x, n4 + i * 10, this.textColor);
            }
         }
      }
   }

   public GuiLabel(FontRenderer fontRenderer2, int n, int n2, int n3, int n4, int n5, int n6) {
      this.fontRenderer = fontRenderer2;
      this.id = n;
      this.x = n2;
      this.y = n3;
      this.width = n4;
      this.height = n5;
      this.labels = Lists.newArrayList();
      this.centered = false;
      this.labelBgEnabled = false;
      this.textColor = n6;
      this.backColor = -1;
      this.ulColor = -1;
      this.brColor = -1;
      this.border = 0;
   }
}
