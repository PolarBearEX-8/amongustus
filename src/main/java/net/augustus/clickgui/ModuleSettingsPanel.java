package net.augustus.clickgui;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.clickgui.components.TextFieldComponent;
import net.augustus.config.ClipboardConfigCodec;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.settings.DynamicSettingList;
import net.augustus.settings.Setting;
import net.augustus.ui.widgets.SmoothScrollBar;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.utils.input.KeyboardInputUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.FeatureBase;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;
import org.lwjgl.opengl.Display;

public class ModuleSettingsPanel {
   private int panelHeight;
   private int cornerRadius;
   public boolean horizontalScrollVisible = false;
   private boolean anySettingFocused;
   private int screenX;
   private int panelWidth;
   private Module currentModule;
   private CustomFontUtil settingFont;
   public boolean verticalScrollVisible = false;
   private final SmoothScrollBar horizontalScrollBar;
   private int screenY;
   private CustomFontUtil headerFont;
   private final SmoothScrollBar verticalScrollBar;

   public ModuleSettingsPanel() {
      this.currentModule = Augustus.getInstance().getModuleManager().velocity;
      this.anySettingFocused = false;
      this.settingFont = FontManager.consolas30;
      this.headerFont = FontManager.esp32;
      this.verticalScrollBar = new SmoothScrollBar();
      this.horizontalScrollBar = new SmoothScrollBar();
   }

   public void handleScrollBarClick(int n, int n2, int n3) throws IOException {
      this.verticalScrollBar.mouseClicked(n, n2, n3);
      this.horizontalScrollBar.mouseClicked(n, n2, n3);
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (RenderUtil.isHovered((float)(this.panelWidth - 60), 4.0F, 40.0F, 17.0F, mouseX, mouseY)) {
         for (Setting setting : (Iterable<Setting>)(Augustus.getInstance().getSettingManager().getSettings())) {
            if (setting.getParentModule() == this.currentModule) {
               setting.resetToDefault();
            }
         }
      } else {
         this.currentModule.handleSettingsMouseClick(mouseX, mouseY, mouseButton);
         int n = (int)(this.headerFont.getFontHeight() + 7.0F + this.settingFont.getFontHeight() + 9.0F);

         for (Setting setting2 : (Iterable<Setting>)(this.currentModule.getSettings())) {
            setting2.setCachedVisible(setting2.isVisible());
            if (setting2.isVisible() && setting2.isCachedVisible()) {
               if (!setting2.isVisualOnly() && this.currentModule.isLoadFromConfig() && !Augustus.getInstance().isConfigEditingEnabled()) {
               } else {
                  int n2 = n + this.currentModule.getScrollOffsetY();
                  if (n2 + setting2.getHeight() > -20 && n2 < this.panelHeight + 20) {
                     if (setting2.mouseClicked(mouseX, mouseY, mouseButton)) {
                        break;
                     }
                  }

                  n += setting2.getHeight();
               }
            }
         }
      }
   }

   public void drawPanel(int n, int n2, int n3, int n4, int n5, int n6, int n7, Module module, boolean flag) {
      if (module != null) {
         this.currentModule = module;
      }

      this.screenX = n5;
      this.screenY = n6;
      this.panelWidth = n3;
      this.panelHeight = n4;
      this.cornerRadius = n7;
      CustomFontUtil headerFont2 = this.headerFont;
      String str = module.getModuleName() + ":";
      int colorInt = ClientManagers.moduleManager.clickGui.hoverTextColor.getColorInt((byte)0);
      double d = 6.0;
      headerFont2.drawString(str, 0.0, d, colorInt);
      headerFont2 = this.settingFont;
      double d3 = (double)(n3 - 60);
      int hoveredColor = RenderUtil.getHoveredColor(ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0), 0.2, n, n2, n3 - 60, 4, 40, 17);
      double d4 = 4.0;
      headerFont2.drawString("Reset", d3, d4, hoveredColor);
      int n10 = module.renderSettingsHeader((byte)0, (int)(this.headerFont.getFontHeight() + 10.0F), n, n2, this.settingFont, flag);
      int n11 = (int)(this.headerFont.getFontHeight() + 7.0F + this.settingFont.getFontHeight() + 9.0F);
      RenderUtil.scissorRectScaled(
         (float)(this.screenX - 3), (float)(this.screenY + n11 - 5), (float)(this.screenX + n3), (float)(this.screenY + this.panelHeight), 1.0
      );
      int n12 = (int)(this.headerFont.getFontHeight() + 7.0F + this.settingFont.getFontHeight() + 9.0F);

      for (Setting setting : (Iterable<Setting>)(module.getSettings())) {
         setting.setCachedVisible(setting.isVisible());
         if (setting.isVisible() && setting.isCachedVisible() && (setting.isVisualOnly() || !module.isLoadFromConfig() || Augustus.getInstance().isConfigEditingEnabled())) {
            int n13 = n12 + module.getScrollOffsetY();
            boolean flag2 = n13 + setting.getHeight() > -20 && n13 < n4 + 20;
            setting.render(n, n2, module.getScrollOffsetX(), n12 + module.getScrollOffsetY(), n3, n4, this.settingFont, flag2);
            n12 += setting.getHeight();
            if (n10 < setting.getContentWidth()) {
               n10 = setting.getContentWidth();
            }
         }
      }

      for (Setting setting2 : (Iterable<Setting>)(module.getSettings())) {
         if (setting2.isVisible() && setting2.isCachedVisible() && (setting2.isVisualOnly() || !module.isLoadFromConfig() || Augustus.getInstance().isConfigEditingEnabled())) {
            setting2.renderTooltip();
         }
      }

      this.anySettingFocused = false;
      this.checkFocusedSettings(module.getSettings());
      RenderUtil.scissorRectScaled((float)this.screenX, (float)(this.screenY + n11 - 5), (float)(this.screenX + n3), (float)(this.screenY + this.panelHeight), 1.0);
      int colorInt2 = ClientManagers.moduleManager.clickGui.headerColor.getColorInt((byte)0);
      this.verticalScrollVisible = this.verticalScrollBar.drawVerticalBar(n3 - 10, n11, n3, n4, n11 - module.getScrollOffsetY(), n12, colorInt2);
      SmoothScrollBar horizontalScrollBar2 = this.horizontalScrollBar;
      int n20 = n4 - 10;
      int n21 = -module.getScrollOffsetX();
      this.horizontalScrollVisible = horizontalScrollBar2.drawHorizontalBar((byte)0, n20, n3, n4, n21, n10, colorInt2);
      if (this.horizontalScrollVisible) {
         SmoothScrollBar verticalScrollBar3 = this.verticalScrollBar;
         n21 = module.getScrollOffsetY();
         module.setScrollOffsetY(verticalScrollBar3.updateScrollOffset(n12, n11, n4, n21, (byte)0, (byte)0, n3, n4 - 50, n, n2));
      } else {
         SmoothScrollBar verticalScrollBar4 = this.verticalScrollBar;
         n21 = module.getScrollOffsetY();
         module.setScrollOffsetY(verticalScrollBar4.updateScrollOffset(n12, n11, n4, n21, (byte)0, (byte)0, n3, n4, n, n2));
      }

      if (this.verticalScrollVisible) {
         SmoothScrollBar horizontalScrollBar3 = this.horizontalScrollBar;
         n21 = module.getScrollOffsetX();
         module.setScrollOffsetX(horizontalScrollBar3.updateScrollOffset(n10, (byte)0, n3, n21, (byte)0, n4 - 50, n3, n4, n, n2));
      } else {
         SmoothScrollBar horizontalScrollBar4 = this.horizontalScrollBar;
         n21 = module.getScrollOffsetX();
         module.setScrollOffsetX(horizontalScrollBar4.updateScrollOffset(n10, (byte)0, n3, n21, (byte)0, (byte)0, n3, n4, n, n2));
      }
   }

   public boolean isAnySettingFocused() {
      return this.anySettingFocused;
   }

   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

      for (Setting setting : (Iterable<Setting>)(this.currentModule.getSettings())) {
         if (setting.isVisible() && setting.isCachedVisible()) {
            setting.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
         }
      }
   }

   public void keyTyped(char typedChar, int keyCode) throws IOException {
      this.currentModule.handleSettingsKeyTyped(typedChar, keyCode);
      if (this.currentModule != null && KeyboardInputUtil.isCtrlKeyCombo(keyCode) && Display.isActive()) {
         if (!Augustus.getInstance().isConfigEditingEnabled()) {
         } else if (!TextFieldComponent.anyFieldFocused) {
            switch (keyCode) {
               case 45:
               case 46:
                  if (!ClipboardConfigCodec.exportModule(this.currentModule)) {
                     ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE6);
                     ChatUtil.sendChat("Failed to export " + ((FeatureBase)this.currentModule).getName() + " to clipboard");
                  }
                  break;
               case 47:
                  if (!ClipboardConfigCodec.importModule(this.currentModule)) {
                     ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE6);
                     ChatUtil.sendChat("Failed to import " + ((FeatureBase)this.currentModule).getName() + " to clipboard");
                  }
            }
         }
      }

      for (Setting setting : (Iterable<Setting>)(this.currentModule.getSettings())) {
         setting.keyTyped(typedChar, keyCode);
      }
   }

   public void mouseReleased(int mouseX, int mouseY, int state) {
      this.verticalScrollBar.mouseReleased(mouseX, mouseY, state);
      this.horizontalScrollBar.mouseReleased(mouseX, mouseY, state);

      for (Setting setting : (Iterable<Setting>)(this.currentModule.getSettings())) {
         if (setting.isVisible() && setting.isCachedVisible()) {
            setting.mouseReleased(mouseX, mouseY, state);
         }
      }
   }

   public void onClose() {

      for (Setting setting : (Iterable<Setting>)(this.currentModule.getSettings())) {
         if (setting.isVisible() && setting.isCachedVisible()) {
            setting.onGuiClosed();
         }
      }
   }

   private void checkFocusedSettings(List list) {

      for (Setting setting : (Iterable<Setting>)(list)) {
         if (this.anySettingFocused) {
            return;
         }

         if (setting.isVisible()) {
            if (setting.isFocused()) {
               this.anySettingFocused = true;
               return;
            }

            if (setting instanceof SettingGroup || setting instanceof DynamicSettingList) {
               if (setting instanceof SettingGroup) {
                  this.checkFocusedSettings(((SettingGroup)setting).getChildren());
               } else {
                  this.checkFocusedSettings(((DynamicSettingList)setting).getEntries());
               }
            }
         }
      }
   }

}
