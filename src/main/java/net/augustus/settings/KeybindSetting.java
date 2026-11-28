package net.augustus.settings;

import net.augustus.ClientManagers;
import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import net.augustus.settings.property.IntegerProperty;
import java.io.IOException;
import net.augustus.modules.Module;
import org.lwjgl.input.Keyboard;

public class KeybindSetting extends Setting {
   private int boxY;
   private final IntegerProperty k;
   private int boxX;
   private int boxWidth;
   private boolean listeningForKey;
   private int boxHeight;

   public KeybindSetting visibleWhen(SettingVisibilityCondition condition) {
      this.visibilityCondition = condition;
      return this;
   }

   @Override
   public Setting withVisibility(SettingVisibilityCondition condition) {
      return this.visibleWhen(condition);
   }

   public void setKeyCode(int keyCode) {
      IntegerProperty integerProperty = this.k;
      Integer num = keyCode;
      integerProperty.setValue(num);
   }

   public KeybindSetting(Module module, String str) {
       super(module, null, str);
      this.k = new IntegerProperty(this, "K", 0);
   }

   public int getKeyCode() {
      return (Integer)this.k.getValue();
   }

   @Override
   public int getHeight() {
      return 27;
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      boolean flag = false;
      if (!RenderUtil.isHovered((float)this.boxX, (float)this.boxY, (float)this.boxWidth, (float)this.boxHeight, mouseX, mouseY) || this.listeningForKey || mouseButton != 0 && mouseButton != 1) {
         if (this.listeningForKey) {
            int n = this.boxWidth + 20;
            int boxHeight2 = this.boxHeight;
            int boxY2 = this.boxY;
            if (this.isPointInBounds(this.boxX, boxY2, n, boxHeight2, mouseX, mouseY) && (mouseButton == 0 || mouseButton == 1) || mouseButton > 1) {
               this.setKeyCode(mouseButton + 10000);
            }

            this.listeningForKey = false;
            this.setFocused(false);
         }
      } else {
         this.listeningForKey = true;
         this.setFocused(true);
         flag = true;
      }

      return flag;
   }

   @Override
   public void resetToDefault() {
      this.k.resetToDefault();
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (this.listeningForKey) {
         IntegerProperty integerProperty = this.k;
         Integer num = keyCode == 1 ? -1 : keyCode;
         integerProperty.setValue(num);
         this.listeningForKey = false;
         this.setFocused(false);
      }
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip((int)this.renderFont.getStringWidth(this.getName() + ": "));
   }

   private boolean isPointInBounds(int n, int n2, int n3, int n4, int n5, int n6) {
      return n5 >= n && n6 >= n2 && n5 < n + n3 && n6 < n2 + n4;
   }

   public KeybindSetting(Module module, String str, SettingGroup doubleValue) {
       super(module, doubleValue, str);
      this.k = new IntegerProperty(this, "K", 0);
   }

   @Override
   public Setting withDisplayName(String str) {
      return this.named(str);
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      super.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
      if (flag) {
         String str = this.getDisplayName()
            + ": "
            + (
               this.listeningForKey
                  ? "..."
                  : (this.getKeyCode() >= 10000 ? "Mouse" + (this.getKeyCode() - 10000) : Keyboard.getKeyName(Math.max(this.getKeyCode(), 0)))
            );
         this.boxX = n3 - 1;
         this.boxY = n4;
         this.boxHeight = (int)customFontUtil.getFontHeight() + 1;
         this.boxWidth = (int)customFontUtil.getStringWidth(str) + 4;
         customFontUtil.drawString(str, (double)n3, (double)n4, RenderUtil.getHoveredColor(ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0), 0.2, n, n2, this.boxX, this.boxY, this.boxWidth, this.boxHeight));
      }
   }

   public KeybindSetting named(String str) {
      this.setDisplayName(str);
      return this;
   }

}
