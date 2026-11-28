package net.augustus.clickgui;

import net.augustus.ClientManagers;
import net.augustus.config.ClientSettingsConfigFile;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.property.BooleanProperty;
import net.augustus.settings.property.IntegerProperty;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.ShaderRenderPass;
import java.awt.Color;
import java.io.IOException;
import net.augustus.modules.AbstractFeature;
import net.augustus.modules.render.ClickGui;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ClickGuiWindow extends AbstractFeature implements MC {
   private int dragStartMouseY;
   private boolean mouseDragging;
   private final int defaultWidth;
   private CustomFontUtil titleFont;
   private String title;
   private final IntegerProperty windowHeight;
   private int lastMouseY;
   private final BooleanProperty visible;
   private final IntegerProperty windowX;
   private final IntegerProperty width;
   private final IntegerProperty height;
   private final BooleanProperty fullScreen;
   private int lastMouseX;
   private final IntegerProperty x;
   private final IntegerProperty windowWidth;
   private int dragStartMouseX;
   public boolean closed;
   private final int defaultHeight;
   private boolean resizing;
   private int dragStartWindowX;
   private boolean draggingTitleBar;
   private final IntegerProperty windowY;
   private int dragStartWindowY;
   private final IntegerProperty y;
   public int cornerRadius;

   public void setHeight(int n) {
      IntegerProperty height2 = this.height;
      Integer num = n;
      height2.setValue(num);
   }

   public void setY(int n) {
      IntegerProperty integerProperty = this.y;
      Integer num = n;
      integerProperty.setValue(num);
   }

   public boolean isMouseWithinBounds(int n, int n2, int n3, int n4, int n5, int n6) {
      return n5 >= n && n6 >= n2 && n5 < n3 && n6 < n4 && this.isVisible();
   }

   public void setX(int n) {
      IntegerProperty integerProperty = this.x;
      Integer num = n;
      integerProperty.setValue(num);
   }

   public boolean isVisible() {
      return (Boolean)this.visible.getValue();
   }

   public int getWindowHeight() {
      return (Integer)this.windowHeight.getValue();
   }

   public boolean isFullScreen() {
      return (Boolean)this.fullScreen.getValue();
   }

   public int getWindowX() {
      return (Integer)this.windowX.getValue();
   }

   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   public void drawWindow(int n, int n2, float f) {
      if (this.isVisible()) {
         ScaledResolution sr = new ScaledResolution(mc);
         GL11.glPushMatrix();
         GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
         n *= sr.getScaleFactor();
         n2 *= sr.getScaleFactor();
         GL11.glPushMatrix();
         if (this.mouseDragging && !this.isFullScreen()) {
            if (this.draggingTitleBar) {
               this.setX(n - (this.dragStartMouseX - this.dragStartWindowX));
               this.setY(n2 - (this.dragStartMouseY - this.dragStartWindowY));
               this.setWindowX(this.getX());
               this.setWindowY(this.getY());
            }

            if (this.resizing) {
               this.setWidth(Math.max(550, n - this.getX()));
               this.setHeight(Math.max(250, n2 - this.getY()));
            }
         }

         ClickGui clickGui = ClientManagers.moduleManager.clickGui;
         n -= this.getX();
         n2 -= this.getY();
         GL11.glTranslatef((float)this.getX(), (float)this.getY(), 0.0F);
         BlurShadowManager.enableOneShotPass(ClientManagers.moduleManager.clickGui.clickGui.isBlurActive(), clickGui.clickGui.isShadowActive());
         int colorInt = clickGui.backgroundColor.getColorInt((byte)0);
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderBackgroundPass(colorInt, clickGui, shaderRenderPass));
         int colorInt2 = clickGui.headerColor.getColorInt((byte)0);
         if (!this.isFullScreen() && clickGui.backgroundColor.getAlpha() > 0) {
            RenderUtil.drawResizeCornerTriangle((float)(this.getWidth() - 20), (float)(this.getHeight() - 20), (float)this.getWidth(), (float)this.getHeight(), this.isFullScreen() ? 0.0F : (float)this.cornerRadius, (byte)1, ColorUtil.applyAlphaMask(colorInt2, 80));
         }

         int colorInt3 = clickGui.textColor.getColorInt((byte)0);
         int n8 = RenderUtil.adjustColorBrightness(colorInt3, 0.1);
         float width3 = (float)this.getWidth();
         float f8 = this.isFullScreen() ? 0.0F : (float)this.cornerRadius;
         RenderUtil.drawTopRoundedRect(0.0F, 0.0F, width3, 30.0F, f8, (byte)1, colorInt2);
         int n9 = this.getWidth() - 46;
         int n11 = this.isMouseOver(n9, (byte)0, (byte)46, (byte)30, n, n2) ? n8 : colorInt3;
         n9 = this.getWidth() - 92;
         int n13 = this.isMouseOver(n9, (byte)0, (byte)45, (byte)30, n, n2) ? n8 : colorInt3;
         n9 = this.getWidth() - 138;
         int n15 = this.isMouseOver(n9, (byte)0, (byte)45, (byte)30, n, n2) ? n8 : colorInt3;
         n9 = this.getWidth() - 46;
         if (this.isMouseOver(n9, (byte)0, (byte)46, (byte)30, n, n2)) {
            RenderUtil.drawTopRightRoundedRect((float)(this.getWidth() - 46), 0.0F, (float)this.getWidth(), 30.0F, (float)this.cornerRadius, 1, new Color(237, 66, 69));
         }

         int n17 = RenderUtil.adjustColorBrightness(colorInt2, (double)0.05F);
         n9 = this.getWidth() - 92;
         if (this.isMouseOver(n9, (byte)0, (byte)45, (byte)30, n, n2)) {
            RenderUtil.drawRect(this.getWidth() - 92, 0, this.getWidth() - 92 + 45, 30, n17);
         }

         n9 = this.getWidth() - 138;
         if (this.isMouseOver(n9, (byte)0, (byte)45, (byte)30, n, n2)) {
            RenderUtil.drawRect(this.getWidth() - 138, 0, this.getWidth() - 138 + 45, 30, n17);
         }

         RenderUtil.drawLine(this.getWidth() - 29, 10, this.getWidth() - 19, 20, 1.0F, n11);
         RenderUtil.drawLine(this.getWidth() - 19, 10, this.getWidth() - 29, 20, 1.0F, n11);
         RenderUtil.drawLine(this.getWidth() - 65, 10, this.getWidth() - 75, 10, 1.0F, n13);
         RenderUtil.drawLine(this.getWidth() - 65, 19, this.getWidth() - 75, 19, 1.0F, n13);
         RenderUtil.drawLine(this.getWidth() - 65, 10, this.getWidth() - 65, 19, 1.0F, n13);
         RenderUtil.drawLine(this.getWidth() - 74, 10, this.getWidth() - 74, 19, 1.0F, n13);
         RenderUtil.drawLine(this.getWidth() - 111, 15, this.getWidth() - 121, 15, 1.0F, n15);
         double d = 8.0;
         double d2 = 10.0;
         String title2 = this.title;
         this.titleFont.drawString(title2, d2, d, colorInt3);
         this.lastMouseX = n;
         this.lastMouseY = n2;
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }
   }

   public ClickGuiWindow(String str, int n, int n2, int n3, int n4) {
       super(ClientSettingsConfigFile.abstractFeature, str);
      this.lastMouseX = 0;
      this.lastMouseY = 0;
      this.dragStartMouseX = 0;
      this.dragStartMouseY = 0;
      this.dragStartWindowX = 0;
      this.dragStartWindowY = 0;
      this.x = new IntegerProperty(this, "X", 0);
      this.y = new IntegerProperty(this, "Y", 0);
      this.width = new IntegerProperty(this, "Width", 0);
      this.height = new IntegerProperty(this, "Height", 0);
      this.windowWidth = new IntegerProperty(this, "WindowWidth", 0);
      this.windowHeight = new IntegerProperty(this, "WindowHeight", 0);
      this.windowX = new IntegerProperty(this, "WindowX", 0);
      this.windowY = new IntegerProperty(this, "WindowY", 0);
      this.visible = new BooleanProperty(this, "Visible", false);
      this.fullScreen = new BooleanProperty(this, "FullScreen", false);
      this.mouseDragging = false;
      this.draggingTitleBar = false;
      this.resizing = false;
      this.closed = false;
      this.title = str;
      this.setWindowWidth(n3);
      this.setWindowHeight(n4);
      this.defaultWidth = n3;
      this.defaultHeight = n4;
      this.setWidth(this.getWindowWidth());
      this.setHeight(this.getWindowHeight());
      this.cornerRadius = 10;
      this.setWindowX((int)((float)n - (float)n3 / 2.0F));
      this.setWindowY((int)((float)n2 - (float)n4 / 2.0F));
      this.setX(this.getWindowX());
      this.setY(this.getWindowY());
      this.setFullScreen(false);
      this.titleFont = FontManager.esp32;
      this.openWindow();
   }

   public void setWidth(int n) {
      IntegerProperty width2 = this.width;
      Integer num = n;
      width2.setValue(num);
   }

   public int getY() {
      return (Integer)this.y.getValue();
   }

   public void setWindowWidth(int n) {
      IntegerProperty windowWidth2 = this.windowWidth;
      Integer num = n;
      windowWidth2.setValue(num);
   }

   public int getWindowWidth() {
      return (Integer)this.windowWidth.getValue();
   }

   public int getHeight() {
      return (Integer)this.height.getValue();
   }

   public int getX() {
      return (Integer)this.x.getValue();
   }

   public void closeWindow() {
      this.setVisible(false);
      this.setFullScreen(false);
      this.setWidth(this.getWindowWidth());
      this.setHeight(this.getWindowHeight());
      this.setX(this.getWindowX());
      this.setY(this.getWindowY());
   }

   public boolean isMouseOver(int n, int n2, int n3, int n4, int n5, int n6) {
      return n5 >= n && n6 >= n2 && n5 < n + n3 && n6 < n2 + n4 && this.isVisible();
   }

   private void renderBackgroundPass(int n, ClickGui clickGui, ShaderRenderPass shaderRenderPass) {
      if (shaderRenderPass != ShaderRenderPass.SHADOW) {
         float width2 = (float)this.getWidth();
         float height2 = (float)this.getHeight();
         float f = this.isFullScreen() ? 0.0F : (float)this.cornerRadius;
         float f5 = 0.0F;
         RenderUtil.drawRoundedRect(0.0F, f5, width2, height2, f, (byte)1, n);
      } else {
         float width3 = (float)this.getWidth();
         float height3 = (float)this.getHeight();
         float f7 = this.isFullScreen() ? 0.0F : (float)this.cornerRadius;
         int n3 = clickGui.clickGui.resolveShadowColor(ColorUtil.resolvePassColor(shaderRenderPass, n));
         float f11 = 0.0F;
         RenderUtil.drawRoundedRect(0.0F, f11, width3, height3, f7, (byte)1, n3);
      }
   }

   public void setWindowY(int n) {
      IntegerProperty windowY2 = this.windowY;
      Integer num = n;
      windowY2.setValue(num);
   }

   public void setWindowX(int n) {
      IntegerProperty windowX2 = this.windowX;
      Integer num = n;
      windowX2.setValue(num);
   }

   public void setWindowHeight(int n) {
      IntegerProperty windowHeight2 = this.windowHeight;
      Integer num = n;
      windowHeight2.setValue(num);
   }

   public int getWindowY() {
      return (Integer)this.windowY.getValue();
   }

   public void setVisible(boolean flag) {
      BooleanProperty visible2 = this.visible;
      Boolean bool = flag;
      visible2.setValue(bool);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      ScaledResolution sr = new ScaledResolution(mc);
      this.dragStartMouseX = mouseX * sr.getScaleFactor();
      this.dragStartMouseY = mouseY * sr.getScaleFactor();
      int x2 = this.getX();
      int y2 = this.getY();
      int n = this.getWidth() - 138;
      int dragStartMouseY2 = this.dragStartMouseY;
      this.draggingTitleBar = this.isMouseOver(x2, y2, n, (byte)30, this.dragStartMouseX, dragStartMouseY2);
      x2 = this.getX() + this.getWidth() - 15;
      y2 = this.getY() + this.getHeight() - 15;
      int dragStartMouseY3 = this.dragStartMouseY;
      this.resizing = this.isMouseOver(x2, y2, (byte)15, (byte)15, this.dragStartMouseX, dragStartMouseY3);
      this.dragStartWindowX = this.getX();
      this.dragStartWindowY = this.getY();
      GL11.glPushMatrix();
      GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
      mouseX *= sr.getScaleFactor();
      mouseY *= sr.getScaleFactor();
      GL11.glPushMatrix();
      mouseX -= this.getX();
      mouseY -= this.getY();
      GL11.glTranslatef((float)this.getX(), (float)this.getY(), 0.0F);
      int n7 = this.getWidth() - 46;
      if (this.isMouseOver(n7, (byte)0, (byte)46, (byte)30, mouseX, mouseY)) {
         RenderUtil.drawTopRightRoundedRect((float)(this.getWidth() - 46), 0.0F, (float)this.getWidth(), 30.0F, (float)this.cornerRadius, 1, new Color(232, 17, 35));
         this.closeWindow();
         this.closed = true;
         this.setFullScreen(false);
         ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE);
      }

      n7 = this.getWidth() - 92;
      if (this.isMouseOver(n7, (byte)0, (byte)45, (byte)30, mouseX, mouseY)) {
         RenderUtil.drawRect(this.getWidth() - 92, 0, this.getWidth() - 92 + 45, 30, 229, 229, 229);
         if (this.isFullScreen()) {
            this.setWidth(this.getWindowWidth());
            this.setHeight(this.getWindowHeight());
            this.setX(this.getWindowX());
            this.setY(this.getWindowY());
         } else {
            this.setWidth(mc.getFramebuffer().framebufferWidth);
            this.setHeight(mc.getFramebuffer().framebufferHeight);
            this.setX((byte)0);
            this.setY((byte)0);
         }

         this.setFullScreen(!this.isFullScreen());
         ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE);
      } else {
         n7 = this.getWidth() - 138;
         if (this.isMouseOver(n7, (byte)0, (byte)45, (byte)30, mouseX, mouseY)) {
            RenderUtil.drawRect(this.getWidth() - 138, 0, this.getWidth() - 138 + 45, 30, 229, 229, 229);
            this.closeWindow();
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE);
         }

         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }
   }

   public void handleKeyboardInput() throws IOException {
   }

   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      this.mouseDragging = true;
   }

   public void setFullScreen(boolean flag) {
      BooleanProperty fullScreen2 = this.fullScreen;
      Boolean bool = flag;
      fullScreen2.setValue(bool);
   }

   public int getWidth() {
      return (Integer)this.width.getValue();
   }

   public void resetWindow(String str) {
      this.setX((byte)0);
      this.setY((byte)0);
      this.setWidth((byte)0);
      this.setHeight((byte)0);
      this.mouseDragging = false;
      this.draggingTitleBar = false;
      this.resizing = false;
      this.setFullScreen(false);
      this.setVisible(false);
      this.closed = false;
      this.title = str;
      float framebufferWidth = (float)mc.getFramebuffer().framebufferWidth;
      float framebufferHeight = (float)mc.getFramebuffer().framebufferHeight;
      int n = (int)(framebufferWidth / 2.0F);
      int n2 = (int)(framebufferHeight / 2.0F);
      this.setWindowWidth(n);
      this.setWindowHeight(n2);
      this.setWidth(this.defaultWidth);
      this.setHeight(this.defaultHeight);
      this.cornerRadius = 10;
      this.setWindowX((int)((float)n - (float)this.getWidth() / 2.0F));
      this.setWindowY((int)((float)n2 - (float)this.getHeight() / 2.0F));
      this.setX(this.getWindowX());
      this.setY(this.getWindowY());
      this.setFullScreen(false);
      this.titleFont = FontManager.esp32;
      this.openWindow();
   }

   public void mouseReleased(int mouseX, int mouseY, int state) {
      this.mouseDragging = false;
      this.draggingTitleBar = false;
      if (this.resizing) {
         this.setWindowWidth(this.getWidth());
         this.setWindowHeight(this.getHeight());
      }

      this.resizing = false;
   }

   public void openWindow() {
      this.setVisible(true);
   }

   public void toggleWindow(String str) {
      if (this.closed) {
         this.resetWindow(str);
      } else {
         this.setVisible(!this.isVisible());
      }
   }




}
