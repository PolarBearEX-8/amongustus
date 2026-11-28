package net.augustus.clickgui;

import net.augustus.ClientManagers;
import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import java.util.List;
import net.augustus.modules.Categorys;

public class CategoryTabButton {
   public int left;
   private int scrollOffset;
   private boolean hovered;
   public int right;
   private int y;
   public int bottom;
   public int width;
   private int x;
   private final Categorys categorys;
   public int height;
   private int paddingY;
   public CustomFontUtil font;
   public int top;
   private final int textWidth;
   private int paddingX;

   public Categorys getCategorys() {
      return this.categorys;
   }

   public void setScrollOffset(int n) {
      this.scrollOffset = n;
   }

   public int getPaddingY() {
      return this.paddingY;
   }

   public List getModules() {
      return this.categorys.getModules();
   }

   public int getScrollOffset() {
      return this.scrollOffset;
   }

   public CategoryTabButton(int n, int n2, int n3, int n4, Categorys categorys2, CustomFontUtil customFontUtil) {
       super();
      this.scrollOffset = 0;
      this.font = customFontUtil;
      this.x = n;
      this.y = n2;
      this.paddingX = n3;
      this.paddingY = n4;
      this.left = this.x - n3;
      this.right = (int)(((float)this.x) + this.font.getStringWidth(categorys2.name()) + (float)n3);
      this.top = this.y - n4 + 3;
      this.bottom = (int)((float)this.y + this.font.getFontHeight() + (float)n4);
      this.width = this.right - this.left;
      this.height = this.bottom - this.top;
      this.categorys = categorys2;
      this.textWidth = (int)customFontUtil.getStringWidth(categorys2.name());
   }

   public void drawTab(int n, int n2, int n3, int n4, boolean flag) {
      this.x = n3;
      this.y = n4;
      this.left = this.x - this.paddingX;
      this.right = (int)(((float)this.x) + this.font.getStringWidth(this.categorys.name()) + (float)this.paddingX);
      this.top = this.y - this.paddingY + 3;
      this.bottom = (int)((float)this.y + this.font.getFontHeight() + (float)this.paddingY);
      int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
      this.font.drawString(this.categorys.toString(), (double)this.x, (double)this.y, colorInt);
      int height2 = this.height;
      int width2 = this.width;
      int top2 = this.top;
      if (this.isMouseOver(this.left, top2, width2, height2, n, n2) && !flag) {
         RenderUtil.drawLine((double)this.x, (double)((float)this.y + this.font.getFontHeight()), (double)(((float)this.x) + this.font.getStringWidth(this.categorys.name())), (double)((float)n4 + this.font.getFontHeight()), 2.0F, colorInt);
      }
   }

   public int getPaddingX() {
      return this.paddingX;
   }

   public int getTextWidth() {
      return this.textWidth;
   }

   public boolean isMouseOver(int n, int n2, int n3, int n4, int n5, int n6) {
      return n5 >= n && n6 >= n2 && n5 < n + n3 && n6 < n2 + n4;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }
}
