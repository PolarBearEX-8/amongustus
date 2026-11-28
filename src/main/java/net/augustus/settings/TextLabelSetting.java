package net.augustus.settings;

import net.augustus.ClientManagers;
import net.augustus.font.CustomFontUtil;
import net.augustus.modules.Module;

public class TextLabelSetting extends Setting {
   private int renderedHeight;
   private String text;

   public TextLabelSetting(Module module, SettingGroup doubleValue, String str) {
       super(module, doubleValue, str);
      this.renderedHeight = 27;
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      if (flag) {
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         double d = (double)n3;
         double d2 = (double)n4;
         customFontUtil.drawString(this.text, d, d2, colorInt);
      }
   }

   @Override
   public void resetToDefault() {
   }

   @Override
   public int getHeight() {
      return this.renderedHeight;
   }

   public void setText(String str) {
      this.text = str;
      int n = 1;

      for (int i = 0; i < str.length(); i++) {
         if (str.charAt(i) == '\n') {
            n++;
         }
      }

      this.renderedHeight = Math.max(n * 18, 27);
   }

   @Override
   public void renderTooltip() {
   }
}
