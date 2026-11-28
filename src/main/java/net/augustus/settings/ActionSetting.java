package net.augustus.settings;

import net.augustus.ClientManagers;
import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import java.io.IOException;
import net.augustus.modules.Module;

public class ActionSetting extends Setting {
   private long lastClickTimeMs;
   private int labelWidth;
   private int prefixWidth;
   private final Runnable action;

   public ActionSetting(Module module, String str, Runnable runnable, SettingGroup doubleValue) {
       this(str, module, doubleValue, runnable);
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      super.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
      if (this.prefixWidth == -1) {
         this.prefixWidth = (int)customFontUtil.getStringWidth("Action: ");
      }

      String displayName2 = this.getDisplayName();
      if (this.consumeLabelDirty(this.labelWidth == -1)) {
         this.labelWidth = (int)customFontUtil.getStringWidth(displayName2);
      }

      this.contentWidth = this.labelWidth + 20;
      if (flag) {
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         double d = (double)n3;
         double d2 = (double)n4;
         double d3 = d;
         customFontUtil.drawString("Action: ", d3, d2, colorInt);
         d = (double)(n3 + this.prefixWidth);
         customFontUtil.drawString(displayName2, d, (double)n4, RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, n3 + this.prefixWidth - 8, n4 - 4, this.labelWidth + 16, (int)(customFontUtil.getFontHeight() + 5.0F)));
      }
   }

   @Override
   public void resetToDefault() {
   }

   @Override
   public int getHeight() {
      return 27;
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (mouseButton == 0
         && RenderUtil.isHovered(
            (float)(this.renderX + this.prefixWidth - 8), (float)(this.renderY - 4), (float)(this.renderX + this.labelWidth + 16), this.renderFont.getFontHeight() + 5.0F, mouseX, mouseY
         )) {
         long currentTimeMs = System.currentTimeMillis();
         if (currentTimeMs - this.lastClickTimeMs > 50L) {
            this.lastClickTimeMs = currentTimeMs;
            this.action.run();
            return true;
         }
      }

      return false;
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip(this.labelWidth);
   }

   public ActionSetting(Module module, String str, Runnable runnable) {
       this(str, module, null, runnable);
   }

   private ActionSetting(String str, Module module, SettingGroup doubleValue, Runnable runnable) {
       super(module, doubleValue, str);
      this.labelWidth = -1;
      this.prefixWidth = -1;
      this.lastClickTimeMs = 0L;
      this.action = runnable;
   }


}
