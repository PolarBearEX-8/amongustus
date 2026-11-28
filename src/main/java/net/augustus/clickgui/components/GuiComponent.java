package net.augustus.clickgui.components;

import net.augustus.font.CustomFontUtil;
import java.io.IOException;

public class GuiComponent {
   public int x;
   public int mouseY;
   public int mouseX;
   public int y;
   public CustomFontUtil font;

   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   public void mouseReleased(int mouseX2, int mouseY2, int state) {
   }

   public int getY() {
      return this.y;
   }

   public void setFont(CustomFontUtil customFontUtil) {
      this.font = customFontUtil;
   }

   public void setX(int n) {
      this.x = n;
   }

   public void onClose() {
   }

   public void setMouseX(int n) {
      this.mouseX = n;
   }

   public int getX() {
      return this.x;
   }

   public int getMouseX() {
      return this.mouseX;
   }

   public void updateLayout(int n, int n2, int n3, int n4, CustomFontUtil customFontUtil) {
      this.x = n;
      this.y = n2;
      this.mouseX = n3;
      this.mouseY = n4;
      this.font = customFontUtil;
   }

   public CustomFontUtil getFont() {
      return this.font;
   }

   public void setMouseY(int n) {
      this.mouseY = n;
   }

   public void mouseClickMove(int mouseX2, int mouseY2, int clickedMouseButton, long timeSinceLastClick) {
   }

   public void setY(int n) {
      this.y = n;
   }

   public void mouseClicked(int mouseX2, int mouseY2, int mouseButton) throws IOException {
   }

   public int getMouseY() {
      return this.mouseY;
   }



}
