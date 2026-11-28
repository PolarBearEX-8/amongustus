package net.augustus.settings;

import net.augustus.ClientManagers;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.settings.property.StringProperty;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.augustus.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketThreadUtil;

public class NamedOptionSetting extends Setting {
   private EnumSettingOption selectedOption;
   private final ConcurrentHashMap optionWidthCache;
   private static final int SEPARATOR_WIDTH;
   private int labelWidth;
   private int renderedHeight;
   private final Map optionsByName;
   private final StringProperty s;
   private EnumSettingOption[] options;

   private EnumSettingOption findOptionByName(String str) {
      return (EnumSettingOption)this.optionsByName.get(str);
   }

   private void selectOptionByName(String str) {
      this.setSelectedOption(this.findOptionByName(str));
   }

   public NamedOptionSetting(Module module, String str, EnumSettingOption enumSettingOption, EnumSettingOption[] enumSettingOptions) {
       this(str, module, null, enumSettingOption, enumSettingOptions);
   }

   private void updateLabelWidth() {
      if (this.consumeLabelDirty(this.labelWidth == -1)) {
         this.labelWidth = (int)FontManager.consolas30Booleans.getStringWidth(this.getDisplayName() + ": ");
      }
   }

   private NamedOptionSetting(String str, Module module, SettingGroup doubleValue, EnumSettingOption enumSettingOption, EnumSettingOption[] enumSettingOptions) {
       super(module, doubleValue, str);
      this.optionsByName = new HashMap();
      this.s = (StringProperty)((StringProperty)new StringProperty(this, "S", "").withOnValueChanged(obj -> this.selectOptionByName((String)obj))).withValueSupplier(() -> this.selectedOption.getDisplayName());
      this.renderedHeight = 0;
      this.labelWidth = -1;
      this.optionWidthCache = new ConcurrentHashMap();
      this.selectedOption = enumSettingOption;
      StringProperty stringProperty3 = this.s;
      stringProperty3.setValue(enumSettingOption.getDisplayName());
      stringProperty3 = this.s;
      stringProperty3.setDefaultValue(enumSettingOption.getDisplayName());
      this.setOptions(enumSettingOptions);
   }

   private void applyOptionsOnMainThread(EnumSettingOption[] enumSettingOptions) {
      this.applyOptions(enumSettingOptions);
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {

      try {
         customFontUtil = FontManager.consolas30Booleans;
         super.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
         this.updateLabelWidth();
         if (flag) {
            String str = this.getDisplayName() + ": ";
            int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
            int colorInt2 = ClientManagers.moduleManager.clickGui.hoverTextColor.getColorInt((byte)0);
            double d = (double)n3;
            customFontUtil.drawString(str, d, (double)n4, colorInt);
            int n7 = n3 + this.labelWidth;
            int n8 = n4;
            int n9 = 0;

            for (EnumSettingOption enumSettingOption : this.getOptions()) {
               String displayName2 = enumSettingOption.getDisplayName();

               try {
                  Integer num = (Integer)this.optionWidthCache.get(displayName2);
                  if (num != null) {
                     if (n7 + num + (n9 < this.getOptions().length - 1 ? SEPARATOR_WIDTH : 0) > n5) {
                        n8 += (int)(customFontUtil.getFontHeight() + 1.0F);
                        n7 = n3 + this.labelWidth;
                     }

                     int n10 = enumSettingOption.equals(this.getSelectedOption())
                        ? RenderUtil.getHoveredColor(colorInt2, 0.1294, n, n2, n7 - 6, n8, num + 12, (int)(customFontUtil.getFontHeight() + 1.0F))
                        : RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, n7 - 6, n8, num + 12, (int)(customFontUtil.getFontHeight() + 1.0F));
                     boolean flag3 = n9 < this.getOptions().length - 1;
                     d = (double)n7;
                     customFontUtil.drawString(displayName2, d, (double)n8, n10);
                     n7 += num;
                     if (flag3) {
                        d = (double)n7;
                        double d6 = (double)n8;
                        double d7 = d;
                        customFontUtil.drawString(", ", d7, d6, colorInt);
                        n7 += SEPARATOR_WIDTH;
                     }

                     if (n9 > 150) {
                        customFontUtil = FontManager.consolas30Default;
                     }

                     n9++;
                  }
               } catch (Exception e) {
                  String str3 = this.getName() + " " + this.getParentModuleName() + " " + Arrays.toString((Object[])this.getOptions()) + " " + customFontUtil;
                  throw new RuntimeException(str3, e);
               }
            }

            this.renderedHeight = (int)((float)n8 + customFontUtil.getFontHeight() + 12.0F - (float)n4);
            this.contentWidth = 150;
         }
      } catch (Exception e2) {
         String str4 = this.getName() + " " + this.getParentModuleName() + " " + Arrays.toString((Object[])this.getOptions()) + " " + customFontUtil;
         throw new RuntimeException(str4, e2);
      }
   }

   public int getSelectedIndex() {
      return Arrays.asList(this.options).indexOf(this.getSelectedOption());
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip(this.labelWidth);
   }

   public EnumSettingOption[] getOptions() {
      return this.options;
   }

   @Override
   public void resetToDefault() {
      this.setSelectedOption(this.findOptionByName((String)this.s.getDefaultValue()));
   }

   @Override
   public void randomize() {
      if (!this.getDisplayName().contains("Point selection mode")) {
         this.setSelectedOption(this.options[ThreadLocalRandom.current().nextInt(this.options.length)]);
      }
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      boolean flag = false;
      if (mouseButton == 0 || mouseButton == 1) {
         int n = this.renderX + this.labelWidth;
         int renderY2 = this.renderY;
         int n2 = 0;

         for (EnumSettingOption enumSettingOption : this.getOptions()) {
            String displayName2 = enumSettingOption.getDisplayName();
            Integer num = (Integer)this.optionWidthCache.get(displayName2);
            if (num != null) {
               if (n + num + (n2 < this.getOptions().length - 1 ? SEPARATOR_WIDTH : 0) > this.boundsWidth) {
                  renderY2 += (int)(this.renderFont.getFontHeight() + 1.0F);
                  n = this.renderX + this.labelWidth;
               }

               if (RenderUtil.isHovered((float)(n - 6), (float)renderY2, (float)(num + 12), (float)((int)(this.renderFont.getFontHeight() + 1.0F)), mouseX, mouseY)) {
                  if (!enumSettingOption.equals(this.selectedOption)) {
                     this.notifyUserChanged();
                  }

                  this.setSelectedOption(enumSettingOption);
                  flag = true;
                  break;
               }

               n += num;
               if (n2 < this.getOptions().length - 1) {
                  n += SEPARATOR_WIDTH;
               }

               n2++;
            }
         }
      }

      return flag;
   }

   @Override
   public int getHeight() {
      return this.renderedHeight;
   }

   public EnumSettingOption getSelectedOption() {
      return this.selectedOption;
   }

   public NamedOptionSetting(Module module, String str, EnumSettingOption enumSettingOption, EnumSettingOption[] enumSettingOptions, SettingGroup doubleValue) {
       this(str, module, doubleValue, enumSettingOption, enumSettingOptions);

      for (EnumSettingOption enumSettingOption2 : enumSettingOptions) {
         this.optionsByName.put(enumSettingOption2.getDisplayName(), enumSettingOption2);
      }
   }

   private void applyOptions(EnumSettingOption[] enumSettingOptions) {
      this.options = enumSettingOptions;
      this.optionWidthCache.clear();

      for (EnumSettingOption enumSettingOption : enumSettingOptions) {
         if (enumSettingOption != null) {
            String displayName2 = enumSettingOption.getDisplayName();
            if (displayName2 != null) {
               this.optionWidthCache.put(displayName2, (int)FontManager.consolas30Booleans.getStringWidth(displayName2));
            }
         }
      }
   }

   static {
      String str = ", ";
      SEPARATOR_WIDTH = (int)FontManager.consolas30Booleans.getStringWidth(str);
   }

   public void setSelectedOption(EnumSettingOption enumSettingOption) {
      if (enumSettingOption != null) {
         if (!enumSettingOption.equals(this.getSelectedOption())) {
            this.notifyValueChanged();
         }

         this.selectedOption = enumSettingOption;
         this.s.setValue(enumSettingOption.getDisplayName());
      }
   }

   public void setOptions(EnumSettingOption[] enumSettingOptions) {
      if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         this.applyOptions(enumSettingOptions);
      } else {
         PacketThreadUtil.runOnMainThreadImmediate(() -> this.applyOptionsOnMainThread(enumSettingOptions));
      }
   }

}
