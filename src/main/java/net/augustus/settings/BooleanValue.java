package net.augustus.settings;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import net.augustus.modules.Module;
import net.augustus.font.CustomFontUtil;
import net.augustus.ClientManagers;
import net.augustus.settings.property.BooleanProperty;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;

public class BooleanValue extends Setting {
   private static final int FALSE_LABEL_WIDTH = 40;
   private int labelWidth;
   private final BooleanProperty b;
   private static final int TRUE_LABEL_WIDTH = 32;

   private BooleanValue(String str, Module module, SettingGroup doubleValue, boolean flag) {
       super(module, doubleValue, str);
      this.b = (BooleanProperty)new BooleanProperty(this, "B", false).withOnValueChanged(obj -> this.setValue((boolean)obj));
      this.labelWidth = -1;
      BooleanProperty booleanProperty2 = this.b;
      Boolean bool = flag;
      booleanProperty2.setValue(bool);
      booleanProperty2 = this.b;
      Boolean bool3 = flag;
      booleanProperty2.setDefaultValue(bool3);
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      customFontUtil = FontManager.consolas30BooleanValue;
      super.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
      String str = this.getDisplayName() + ": ";
      if (this.consumeLabelDirty(this.labelWidth == -1)) {
         this.labelWidth = (int)customFontUtil.getStringWidth(str);
      }

      if (flag) {
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         double d = (double)n3;
         customFontUtil.drawString(str, d, (double)n4, colorInt);
         String str2 = String.valueOf(this.getValue());
         float f = this.getValue() ? 32.0F : 40.0F;
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 771);
         d = (double)(n3 + this.labelWidth);
         double d4 = (double)n4;
         int n7;
         if (this.getValue()) {
            n7 = ClientManagers.moduleManager.clickGui.enabledColor.getColorInt((byte)0);
         } else {
            n7 = ClientManagers.moduleManager.clickGui.disabledColor.getColorInt((byte)0);
         }

         customFontUtil.drawString(str2, d, d4, RenderUtil.getHoveredColor(n7, 0.16, n, n2, n3 + this.labelWidth - 8, n4 - 4, 60, (int)(customFontUtil.getFontHeight() + 5.0F)));
         this.contentWidth = (int)((float)this.labelWidth + f + 20.0F);
      }
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip(this.labelWidth);
   }

   public void setDefaultValue(boolean defaultValue) {
      BooleanProperty booleanProperty = this.b;
      Boolean bool = defaultValue;
      booleanProperty.setDefaultValue(bool);
   }

   public boolean getValue() {
      return (Boolean)this.b.getValue();
   }

   public BooleanValue(Module module, String str, boolean flag, SettingGroup doubleValue) {
       this(str, module, doubleValue, flag);
   }

   public BooleanValue(Module module, String str, boolean flag) {
       this(str, module, null, flag);
   }

   public BooleanValue(String str, boolean flag) {
       this(str, null, null, flag);
   }

   @Override
   public void resetToDefault() {
      boolean defaultValue = (Boolean)this.b.getDefaultValue();
      this.setValue(defaultValue);
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (mouseButton == 0
         && RenderUtil.isHovered((float)(this.renderX + this.labelWidth - 8), (float)(this.renderY - 4), 60.0F, (float)((int)this.renderFont.getFontHeight() + 5), mouseX, mouseY)) {
         this.setValue(!this.getValue());
         return true;
      } else {
         return false;
      }
   }

   public void setValue(boolean value) {
      if (this.getValue() != value) {
         this.notifyValueChanged();
      }

      BooleanProperty booleanProperty = this.b;
      Boolean bool = value;
      booleanProperty.setValue(bool);
   }

   @Override
   public int getHeight() {
      return 27;
   }

   @Override
   public void randomize() {
      this.setValue(ThreadLocalRandom.current().nextBoolean());
   }

}
