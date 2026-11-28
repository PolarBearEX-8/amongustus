package net.augustus.settings;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.settings.property.BooleanProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.augustus.modules.Module;

public class MultiOptionSetting extends Setting {
   private final List optionProperties;
   private final ConcurrentHashMap optionWidthCache;
   private static final int SEPARATOR_WIDTH;
   private int renderedHeight;
   private final SelectableOption[] options;
   private int dragToggleState;
   private int labelWidth;

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      boolean flag = false;
      if (mouseButton == 0 || mouseButton == 1) {
         String str = this.getDisplayName() + ": ";
         float renderX2 = (float)this.renderX;
         int n = (int)(renderX2 + this.renderFont.getStringWidth(str));
         int renderY2 = this.renderY;

         for (int i = 0; i < this.options.length; i++) {
            SelectableOption selectableOption = this.options[i];
            if (selectableOption.isVisible()) {
               String name = selectableOption.getName();
               if (!name.isEmpty()) {
                  int stringWidth = (int)this.renderFont.getStringWidth(name);
                  renderX2 = (float)(n + stringWidth);
                  String str2;
                  if (i < this.options.length - 1) {
                     str2 = ", ";
                  } else {
                     str2 = "";
                  }

                  String str3 = str2;
                  if (renderX2 + this.renderFont.getStringWidth(str3) > (float)this.boundsWidth) {
                     renderY2 += (int)this.renderFont.getFontHeight() + 1;
                     renderX2 = (float)this.renderX;
                     n = (int)(renderX2 + this.renderFont.getStringWidth(str));
                  }

                  if (RenderUtil.isHovered((float)(n - 6), (float)renderY2, (float)(stringWidth + 12), (float)((int)(this.renderFont.getFontHeight() + 1.0F)), mouseX, mouseY)) {
                     this.dragToggleState = selectableOption.isSelected() ? 1 : 2;
                     selectableOption.setSelected(!selectableOption.isSelected());
                     this.notifyValueChanged();
                     this.notifyUserChanged();
                     flag = true;
                     break;
                  }

                  n += (int)this.renderFont.getStringWidth(name);
                  if (i < this.options.length - 1) {
                     String str4 = ", ";
                     n += (int)this.renderFont.getStringWidth(str4);
                  }
               }
            }
         }
      }

      return flag;
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip(this.labelWidth);
   }

   public SelectableOption[] getOptions() {
      return this.options;
   }

   @Override
   public Setting withDisplayName(String str) {
      return this.named(str);
   }

   static {
      String str = ", ";
      SEPARATOR_WIDTH = (int)FontManager.consolas30.getStringWidth(str);
   }

   @Override
   public void resetToDefault() {

      for (BooleanProperty booleanProperty : (Iterable<BooleanProperty>)(this.optionProperties)) {
         booleanProperty.resetToDefault();
      }
   }

   public SelectableOption findOptionByName(SelectableOption[] selectableOptions, String str) {
      return Arrays.stream(selectableOptions).filter(selectableOption -> selectableOption.getName().equals(str)).findFirst().orElse(null);
   }

   @Override
   public Setting withVisibility(SettingVisibilityCondition condition) {
      return this.visibleWhen(condition);
   }

   private Boolean readOptionEnabled(SelectableOption[] selectableOptions, SelectableOption selectableOption) {
      return this.findOptionByName(selectableOptions, selectableOption.getName()).isSelected();
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      if (this.dragToggleState != 0) {
         if (clickedMouseButton == 0 || clickedMouseButton == 1) {
            String str = this.getDisplayName() + ": ";
            float renderX2 = (float)this.renderX;
            int n = (int)(renderX2 + this.renderFont.getStringWidth(str));
            int renderY2 = this.renderY;

            for (int i = 0; i < this.options.length; i++) {
               SelectableOption selectableOption = this.options[i];
               if (selectableOption.isVisible()) {
                  String name = selectableOption.getName();
                  if (!name.isEmpty()) {
                     int stringWidth = (int)this.renderFont.getStringWidth(name);
                     renderX2 = (float)(n + stringWidth);
                     String str2;
                     if (i < this.options.length - 1) {
                        str2 = ", ";
                     } else {
                        str2 = "";
                     }

                     String str3 = str2;
                     if (renderX2 + this.renderFont.getStringWidth(str3) > (float)this.boundsWidth) {
                        renderY2 += (int)this.renderFont.getFontHeight() + 1;
                        renderX2 = (float)this.renderX;
                        n = (int)(renderX2 + this.renderFont.getStringWidth(str));
                     }

                     if (RenderUtil.isHovered((float)(n - 6), (float)renderY2, (float)(stringWidth + 12), (float)((int)(this.renderFont.getFontHeight() + 1.0F)), mouseX, mouseY)
                        && selectableOption.isSelected() == (this.dragToggleState == 1)) {
                        selectableOption.setSelected(!selectableOption.isSelected());
                        this.notifyValueChanged();
                        this.notifyUserChanged();
                        break;
                     }

                     n += (int)this.renderFont.getStringWidth(name);
                     if (i < this.options.length - 1) {
                        String str4 = ", ";
                        n += (int)this.renderFont.getStringWidth(str4);
                     }
                  }
               }
            }
         }
      }
   }

   private MultiOptionSetting(String str, Module module, SettingGroup doubleValue, SelectableOption[] selectableOptions) {
       super(module, doubleValue, str);
      this.optionProperties = new ArrayList();
      this.labelWidth = -1;
      this.optionWidthCache = new ConcurrentHashMap();
      this.dragToggleState = 0;
      this.renderedHeight = 0;
      this.options = selectableOptions;

      for (SelectableOption selectableOption : this.options) {
         List optionProperties2 = this.optionProperties;
         BooleanProperty booleanProperty2 = (BooleanProperty)new BooleanProperty(this, selectableOption.getName(), selectableOption.isSelected()).withValueSupplier(() -> this.readOptionEnabled(selectableOptions, selectableOption));
         Consumer consumer = obj -> {
            SelectableOption selectableOption2 = this.findOptionByName(selectableOptions, selectableOption.getName());
            boolean flag = (Boolean)(obj);
            selectableOption2.setSelected(flag);
         };
         optionProperties2.add(booleanProperty2.withOnValueApplied(consumer));
      }
   }

   public MultiOptionSetting(Module module, String str, SelectableOption[] selectableOptions) {
       this(str, module, null, selectableOptions);
   }

   @Override
   public int getHeight() {
      return this.renderedHeight;
   }

   public MultiOptionSetting visibleWhen(SettingVisibilityCondition condition) {
      this.visibilityCondition = condition;
      return this;
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      this.dragToggleState = 0;
   }

   public MultiOptionSetting(Module module, String str, SelectableOption[] selectableOptions, SettingGroup doubleValue) {
       this(str, module, doubleValue, selectableOptions);
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      customFontUtil = FontManager.consolas30MultiMode;
      super.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
      if (flag) {
         String str = this.getDisplayName() + ": ";
         int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
         int colorInt2 = ClientManagers.moduleManager.clickGui.hoverTextColor.getColorInt((byte)0);
         double d = (double)n3;
         customFontUtil.drawString(str, d, (double)n4, colorInt);
         if (this.consumeLabelDirty(this.labelWidth == -1)) {
            this.labelWidth = (int)customFontUtil.getStringWidth(str);
            this.optionWidthCache.clear();

            for (SelectableOption selectableOption : this.options) {
               this.optionWidthCache.put(selectableOption.getName(), (int)customFontUtil.getStringWidth(selectableOption.getName()));
            }
         }

         int n7 = n3 + this.labelWidth;
         int n8 = n4;

         for (int i = 0; i < this.options.length; i++) {
            SelectableOption selectableOption2 = this.options[i];
            if (selectableOption2.isVisible()) {
               String name3 = selectableOption2.getName();
               if (!name3.isEmpty()) {
                  Integer num = (Integer)this.optionWidthCache.get(name3);
                  if (num != null) {
                     if (n7 + num + (i < this.options.length - 1 ? SEPARATOR_WIDTH : 0) > n5) {
                        n8 += (int)(customFontUtil.getFontHeight() + 1.0F);
                        n7 = n3 + this.labelWidth;
                     }

                     int n9 = selectableOption2.isSelected()
                        ? RenderUtil.getHoveredColor(colorInt2, 0.1294, n, n2, n7 - 6, n8, num + 12, (int)(customFontUtil.getFontHeight() + 1.0F))
                        : RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, n7 - 6, n8, num + 12, (int)(customFontUtil.getFontHeight() + 1.0F));
                     d = (double)n7;
                     customFontUtil.drawString(name3, d, (double)n8, n9);
                     n7 += num;
                     if (i < this.options.length - 1) {
                        d = (double)n7;
                        double d6 = (double)n8;
                        double d7 = d;
                        customFontUtil.drawString(", ", d7, d6, colorInt);
                        n7 += SEPARATOR_WIDTH;
                     }

                     if (i > 150) {
                        customFontUtil = FontManager.consolas30MultiOption;
                     }
                  }
               }
            }
         }

         this.renderedHeight = (int)((float)n8 + customFontUtil.getFontHeight() + 12.0F - (float)n4);
      }
   }

   public MultiOptionSetting named(String str) {
      this.setDisplayName(str);
      return this;
   }

}
