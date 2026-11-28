package net.augustus.clickgui;

import net.augustus.ClientManagers;
import net.augustus.clickgui.components.TextFieldComponent;
import net.augustus.events.listeners.GuiScaleListener;
import net.augustus.events.listeners.RenderGuiBackgroundListener;
import net.augustus.render.RenderUtil;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.ui.UiIconResources;
import net.augustus.ui.config.ConfigSystemPanel;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.render.GlStateSaver;
import net.augustus.utils.render.ShaderRenderPass;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class ClickGuiScreen extends GuiScreen {
   private int centerX;
   private final GlStateSaver glStateSaver;
   private final MainClickGuiWindow mainWindow;
   private int centerY;
   private int framebufferHeight;
   private ClickGuiWindow activeWindow;
   private int framebufferWidth;
   public static boolean configEditable = true;
   private final ConfigSystemPanel configPanel;
   public static boolean protectedConfigCreator = false;

   private boolean isMouseOver(int n, int n2, int n3, int n4, int n5, int n6) {
      return n5 >= n && n6 >= n2 && n5 < n + n3 && n6 < n2 + n4;
   }

   @Override
   public void handleKeyboardInput() throws IOException {
      this.mainWindow.handleKeyboardInput();
      this.configPanel.handleKeyboardInput();
      super.handleKeyboardInput();
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if ((!this.mainWindow.selectedModule.isBindingKey() || keyCode != 1) && (keyCode != 28 && keyCode != 156 && keyCode != 1 || !this.mainWindow.getSettingsPanel().isAnySettingFocused())) {
         super.keyTyped(typedChar, keyCode);
      }

      this.mainWindow.keyTyped(typedChar, keyCode);
      this.configPanel.keyTyped(typedChar, keyCode);
   }

   public ConfigSystemPanel getConfigPanel() {
      return this.configPanel;
   }

   public MainClickGuiWindow getMainWindow() {
      return this.mainWindow;
   }

   @Override
   public void initGui() {
      super.initGui();
      this.allowUserInput = true;
      if (this.mainWindow.getX() < 0 || this.mainWindow.getY() < 0 || this.configPanel.getX() < 0 || this.configPanel.getY() < 0) {
         this.mainWindow.resetWindow(this.mainWindow.getName());
         this.configPanel.resetWindow(this.configPanel.getName());
         this.configPanel.setVisible(false);
      }

      this.configPanel.onPanelOpened();
      this.activeWindow = null;
      GuiScreen.NO_TOGGLE_SCREENS.add(Minecraft.getMinecraft().currentScreen);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.glStateSaver.setAlpha(false);
      TextFieldComponent.anyFieldFocused = false;
      BlurShadowManager.disableGuiLayer();
      ScaledResolution sr = new ScaledResolution(this.mc);
      if (this.mainWindow.isVisible() || this.configPanel.isVisible()) {
         RenderGuiBackgroundListener.postRenderGuiBackground(this);
      }

      GlStateManager.pushMatrix();
      GlStateManager.scale(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
      int n = mouseX;
      int n2 = mouseY;
      mouseX *= sr.getScaleFactor();
      mouseY *= sr.getScaleFactor();
      this.framebufferWidth = this.mc.getFramebuffer().framebufferWidth;
      this.framebufferHeight = this.mc.getFramebuffer().framebufferHeight;
      this.centerX = (int)((float)this.framebufferWidth / 2.0F);
      this.centerY = (int)((float)this.framebufferHeight / 2.0F);
      byte b = 6;
      if (!this.mainWindow.isFullScreen() && !this.configPanel.isFullScreen()) {
         BlurShadowManager.enableOneShotPass(ClientManagers.moduleManager.clickGui.clickGui.isBlurActive(), ClientManagers.moduleManager.clickGui.clickGui.isShadowActive());
         Color color = new Color(40, 39, 39, MathHelper.clamp_int((int)ClientManagers.moduleManager.clickGui.alpha.getValue(), 0, 255));
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderDockBackgroundPass(b, shaderRenderPass));
         if (this.isMouseOver(this.centerX - 43 - b, (byte)0, (byte)42, 37 + b, mouseX, mouseY)) {
            int x = this.configPanel.getX();
            int y = this.configPanel.getY();
            int width2 = this.configPanel.getWidth();
            if (!this.isMouseOver(x, y, width2, this.configPanel.getHeight(), mouseX, mouseY) || !this.configPanel.isVisible()) {
               int x2 = this.mainWindow.getX();
               y = this.mainWindow.getY();
               width2 = this.mainWindow.getWidth();
               if (!this.isMouseOver(x2, y, width2, this.mainWindow.getHeight(), mouseX, mouseY) || !this.mainWindow.isVisible()) {
                  RenderUtil.drawRoundedRect(
                     (float)(this.centerX - 43 - b),
                     0.0F,
                     (float)(this.centerX - 1),
                     (float)(37 + b),
                     (float)b,
                     1,
                     false,
                     false,
                     true,
                     false,
                     new Color(237, 66, 69, MathHelper.clamp_int((int)ClientManagers.moduleManager.clickGui.alpha.getValue(), 0, 255))
                  );
               }
            }
         }

         if (this.isMouseOver(this.centerX + 1, (byte)0, (byte)42, 37 + b, mouseX, mouseY)) {
            int x3 = this.configPanel.getX();
            int y2 = this.configPanel.getY();
            int width3 = this.configPanel.getWidth();
            if (!this.isMouseOver(x3, y2, width3, this.configPanel.getHeight(), mouseX, mouseY) || !this.configPanel.isVisible()) {
               int x4 = this.mainWindow.getX();
               y2 = this.mainWindow.getY();
               width3 = this.mainWindow.getWidth();
               if (!this.isMouseOver(x4, y2, width3, this.mainWindow.getHeight(), mouseX, mouseY) || !this.mainWindow.isVisible()) {
                  RenderUtil.drawRoundedRect(
                     (float)(this.centerX + 1),
                     0.0F,
                     (float)(this.centerX + 43 + b),
                     (float)(37 + b),
                     (float)b,
                     1,
                     false,
                     false,
                     false,
                     true,
                     new Color(237, 66, 69, MathHelper.clamp_int((int)ClientManagers.moduleManager.clickGui.alpha.getValue(), 0, 255))
                  );
               }
            }
         }

         if (ClientManagers.moduleManager.clickGui.outline.getValue()) {
            RenderUtil.drawRoundedRectOutline(
               (float)(this.centerX - 43 - b),
               (float)(-b),
               (float)(this.centerX + 43 + b),
               (float)(37 + b),
               (float)b,
               1,
               1,
               color
            );
         }

         int n21 = this.centerX - 36 - 7;
         double d = 1.0;
         UiIconResources.drawIcon(UiIconResources.AUGUSTUS_TEXTURE3, n21, (byte)2, (byte)36, (byte)36, d);
         int n23 = this.centerX + 7;
         double d2 = 1.0;
         UiIconResources.drawIcon(UiIconResources.AUGUSTUS_TEXTURE4, n23, (byte)2, (byte)36, (byte)36, d2);
      }

      GlStateManager.popMatrix();
      if (this.activeWindow != null) {
         if (this.activeWindow.equals(this.mainWindow)) {
            if (!this.mainWindow.isFullScreen()) {
               this.configPanel.drawWindow(n, n2, partialTicks);
            }

            this.mainWindow.drawWindow(n, n2, partialTicks);
         } else {
            if (!this.configPanel.isFullScreen()) {
               this.mainWindow.drawWindow(n, n2, partialTicks);
            }

            this.configPanel.drawWindow(n, n2, partialTicks);
         }
      } else {
         this.configPanel.drawWindow(n, n2, partialTicks);
         this.mainWindow.drawWindow(n, n2, partialTicks);
      }

      BlurShadowManager.enableGuiLayer();
      this.glStateSaver.restoreAlpha();
      super.drawScreen(n, n2, partialTicks);
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      this.mainWindow.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      this.configPanel.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
   }

   private void renderDockBackgroundPass(int n, ShaderRenderPass shaderRenderPass) {
      if (shaderRenderPass == ShaderRenderPass.SHADOW) {
         RenderUtil.drawRoundedRect((float)(this.centerX - 43 - n), (float)(-n), (float)(this.centerX + 43 + n), (float)(37 + n), (float)n, 1, ClientManagers.moduleManager.clickGui.clickGui.resolveShadowColor(Color.black.getRGB()));
      } else {
         RenderUtil.drawRoundedRect(
            (float)(this.centerX - 43 - n), (float)(-n), (float)(this.centerX + 43 + n), (float)(37 + n), (float)n, 1, new Color(40, 39, 39)
         );
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      super.actionPerformed(button);
      if (button.id == 1) {
         GuiScaleListener.postGuiScale();
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      int n = mouseX;
      int n2 = mouseY;
      ScaledResolution sr = new ScaledResolution(this.mc);
      mouseX *= sr.getScaleFactor();
      mouseY *= sr.getScaleFactor();
      if (this.activeWindow == null) {
         if (RenderUtil.isHovered(
            (float)this.mainWindow.getX(), (float)this.mainWindow.getY(), (float)this.mainWindow.getWidth(), (float)this.mainWindow.getHeight(), mouseX, mouseY
         )) {
            this.activeWindow = this.mainWindow;
         } else if (RenderUtil.isHovered(
            (float)this.configPanel.getX(), (float)this.configPanel.getY(), (float)this.configPanel.getWidth(), (float)this.configPanel.getHeight(), mouseX, mouseY
         )) {
            this.activeWindow = this.configPanel;
         }
      } else if (this.activeWindow.equals(this.mainWindow)) {
         if (!RenderUtil.isHovered(
               (float)this.mainWindow.getX(), (float)this.mainWindow.getY(), (float)this.mainWindow.getWidth(), (float)this.mainWindow.getHeight(), mouseX, mouseY
            )
            && RenderUtil.isHovered(
               (float)this.configPanel.getX(), (float)this.configPanel.getY(), (float)this.configPanel.getWidth(), (float)this.configPanel.getHeight(), mouseX, mouseY
            )) {
            this.activeWindow = this.configPanel;
         }
      } else if (this.activeWindow.equals(this.configPanel)
         && !RenderUtil.isHovered(
            (float)this.configPanel.getX(), (float)this.configPanel.getY(), (float)this.configPanel.getWidth(), (float)this.configPanel.getHeight(), mouseX, mouseY
         )
         && RenderUtil.isHovered(
            (float)this.mainWindow.getX(), (float)this.mainWindow.getY(), (float)this.mainWindow.getWidth(), (float)this.mainWindow.getHeight(), mouseX, mouseY
         )) {
         this.activeWindow = this.mainWindow;
      }

      if (!this.mainWindow.isVisible() && this.configPanel.isVisible()) {
         this.activeWindow = this.configPanel;
      }

      if (!this.configPanel.isVisible() && this.mainWindow.isVisible()) {
         this.activeWindow = this.mainWindow;
      }

      if (this.activeWindow != null) {
         if (this.activeWindow.equals(this.mainWindow)) {
            this.mainWindow.mouseClicked(n, n2, mouseButton);
         } else {
            this.configPanel.mouseClicked(n, n2, mouseButton);
         }
      }

      if (mouseButton == 0 && (!this.configPanel.isFullScreen() || !this.configPanel.isVisible()) && (!this.mainWindow.isFullScreen() || !this.mainWindow.isVisible())) {
         byte b = 6;
         if (this.isMouseOver(this.centerX - 43 - b, (byte)0, (byte)42, 37 + b, mouseX, mouseY)) {
            int x = this.configPanel.getX();
            int y = this.configPanel.getY();
            int width2 = this.configPanel.getWidth();
            if (!this.isMouseOver(x, y, width2, this.configPanel.getHeight(), mouseX, mouseY) || !this.configPanel.isVisible()) {
               int x2 = this.mainWindow.getX();
               y = this.mainWindow.getY();
               width2 = this.mainWindow.getWidth();
               if (!this.isMouseOver(x2, y, width2, this.mainWindow.getHeight(), mouseX, mouseY) || !this.mainWindow.isVisible()) {
                  if (!this.mainWindow.isVisible() && this.mainWindow.closed) {
                     this.activeWindow = this.mainWindow;
                  }

                  String str = "ClickGui";
                  this.mainWindow.toggleWindow(str);
                  this.activeWindow = this.mainWindow;
                  ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE);
               }
            }
         }

         if (this.isMouseOver(this.centerX + 1, (byte)0, (byte)42, 37 + b, mouseX, mouseY)) {
            int x3 = this.configPanel.getX();
            int y2 = this.configPanel.getY();
            int width3 = this.configPanel.getWidth();
            if (!this.isMouseOver(x3, y2, width3, this.configPanel.getHeight(), mouseX, mouseY) || !this.configPanel.isVisible()) {
               int x4 = this.mainWindow.getX();
               y2 = this.mainWindow.getY();
               width3 = this.mainWindow.getWidth();
               if (!this.isMouseOver(x4, y2, width3, this.mainWindow.getHeight(), mouseX, mouseY) || !this.mainWindow.isVisible()) {
                  if (!this.configPanel.isVisible() && this.configPanel.closed) {
                     this.activeWindow = this.configPanel;
                  }

                  String str2 = "Configs";
                  this.configPanel.toggleWindow(str2);
                  this.activeWindow = this.configPanel;
                  ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE);
               }
            }
         }
      }
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.mainWindow.updatePanel();
   }

   public ClickGuiScreen() {
       super();
      this.mainWindow = new MainClickGuiWindow("ClickGui", -1, -1, 1154, 680);
      this.configPanel = (new ConfigSystemPanel("Configs", -1, -1, 872, 380)).initPanel();
      this.glStateSaver = GlStateSaver.create();
      this.activeWindow = null;
      this.framebufferWidth = 0;
      this.framebufferHeight = 0;
      this.centerX = 0;
      this.centerY = 0;
   }

   public void setMc(Minecraft mc2) {
      this.mc = mc2;
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      this.mainWindow.mouseReleased(mouseX, mouseY, state);
      this.configPanel.mouseReleased(mouseX, mouseY, state);
      super.mouseReleased(mouseX, mouseY, state);
   }

   @Override
   public void onGuiClosed() {
      this.mainWindow.onClose();
      super.onGuiClosed();
   }

}
