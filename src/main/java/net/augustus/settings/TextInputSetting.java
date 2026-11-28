package net.augustus.settings;

import net.augustus.ClientManagers;
import net.augustus.clickgui.components.TextFieldComponent;
import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import net.augustus.settings.property.StringProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import net.augustus.modules.Module;
import net.augustus.modules.misc.Dummy;

public class TextInputSetting extends Setting {
   private final StringProperty n;
   private int labelWidth;
   private final TextFieldComponent textField;
   private long lastClickTimeMs;

   public TextInputSetting(Module module, String str, String str2, boolean flag, SettingGroup doubleValue) {
       super(module, doubleValue, str);
      this.labelWidth = -1;
      this.lastClickTimeMs = 0L;
      this.textField = new TextFieldComponent(str2, flag ? StringInputMode.HIDDEN : StringInputMode.DEFAULT);
      this.n = (StringProperty)new StringProperty(this, "N", str2, str2).withOnValueChanged(obj -> this.setTextFieldValue((String)obj));
      if (module != null && !(module instanceof Dummy)) {
         ClientManagers.settingManager.registerSetting(this);
         ArrayList list = doubleValue != null ? doubleValue.getChildren() : module.getSettings();
         list.removeIf(obj2 -> this.matchesSettingName((Setting)obj2));
         list.add(this);
      }
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      this.textField.mouseClicked(mouseX, mouseY, mouseButton);
      long currentTimeMs = System.currentTimeMillis();
      if (RenderUtil.isHovered((float)(this.renderX - 2), (float)this.renderY, this.renderFont.getStringWidth(this.textField.getText()) + 2.0F + (float)this.labelWidth, this.renderFont.getFontHeight() + 1.0F, mouseX, mouseY)) {
         if (this.lastClickTimeMs + 400L > currentTimeMs) {
            this.textField.setFocused(true);
         }

         this.lastClickTimeMs = currentTimeMs;
      }

      return false;
   }

   public TextInputSetting(Module module, String str, boolean flag) {
       this(module, str, "", flag);
   }

   private boolean matchesSettingName(Setting setting) {
      return setting.getName().equals(this.getName());
   }

   public void setValue(String value) {
      this.n.setValue(value);
      this.textField.setText(value);
   }

   @Override
   public void render(int n2, int n3, int n4, int n5, int n6, int n7, CustomFontUtil customFontUtil, boolean flag) {
      super.render(n2, n3, n4, n5, n6, n7, customFontUtil, flag);
      String str = this.getDisplayName() + ": ";
      if (this.consumeLabelDirty(this.labelWidth == -1)) {
         this.labelWidth = (int)customFontUtil.getStringWidth(str);
      }

      if (flag) {
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         customFontUtil.drawString(str, (double)n4, (double)n5, colorInt);
         int n8 = n4 - 2;
         String text = this.textField.getText();
         int hoveredColor = RenderUtil.getHoveredColor(
            colorInt, 0.2, n2, n3, n8, n5, (int)(customFontUtil.getStringWidth(text) + 2.0F + (float)this.labelWidth), (int)(customFontUtil.getFontHeight() + 1.0F)
         );
         int n10 = n4 + this.labelWidth;
         this.textField.drawTextField(n10, n5, n2, n3, customFontUtil, hoveredColor);
         this.n.setValue(this.textField.getText());
      }
   }

   public TextInputSetting(Module module, String str, String str2, SettingGroup doubleValue) {
       this(module, str, str2, false, doubleValue);
   }

   @Override
   public int getHeight() {
      return 27;
   }

   public TextInputSetting(Module module, String str, String str2, boolean flag) {
       this(module, str, str2, flag, null);
   }

   public TextInputSetting(Module module, String str, SettingGroup doubleValue) {
       this(module, str, "", false, doubleValue);
   }

   public TextInputSetting(Module module, String str, String str2) {
       this(module, str, str2, false, null);
   }

   public TextInputSetting(Module module, String str, boolean flag, SettingGroup doubleValue) {
       this(module, str, "", flag, doubleValue);
   }

   public TextInputSetting(Module module, String str) {
       this(module, str, "");
   }

   public String getValue() {
      return (String)this.n.getValue();
   }

   @Override
   public void resetToDefault() {
      this.n.resetToDefault();
      this.textField.setText(this.getValue());
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.textField.keyTyped(typedChar, keyCode);
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip(this.labelWidth);
   }

   public void setTextFieldValue(String textFieldValue) {
      this.textField.setText(textFieldValue);
   }

}
