package net.augustus.settings;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import net.augustus.modules.Module;
import net.augustus.font.CustomFontUtil;
import net.augustus.ClientManagers;
import net.augustus.font.FontManager;
import net.augustus.settings.property.StringProperty;
import net.augustus.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketThreadUtil;

public class StringValue extends Setting {
   private final ConcurrentHashMap optionWidthCache;
   private int labelWidth;
   private final StringProperty s;
   private static final int SEPARATOR_WIDTH;
   private int renderedHeight;
   private String[] options;

   public String getSelectedValue() {
      return (String)this.s.getValue();
   }

   @Override
   public int getHeight() {
      return this.renderedHeight;
   }

   public StringValue(Module module, String str, String str2, String[] strings, SettingGroup doubleValue) {
       this(str, module, doubleValue, str2, strings);
   }

   public void setOptions(String[] strings) {
      if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         this.applyOptions(strings);
      } else {
         PacketThreadUtil.runOnMainThreadImmediate(() -> {
            this.applyOptions(strings);
         });
      }
   }

   private void updateLabelWidth() {
      if (this.consumeLabelDirty(this.labelWidth == -1)) {
         this.labelWidth = (int)FontManager.consolas30Booleans.getStringWidth(this.getDisplayName() + ": ");
      }
   }

   public StringValue(Module module, String str, String str2, String[] strings) {
       this(str, module, null, str2, strings);
   }

   @Override
   public void resetToDefault() {
      this.setSelectedValue((String)this.s.getDefaultValue());
   }

   public void setSelectedValue(String selectedValue) {
      if (!selectedValue.equals(this.getSelectedValue())) {
         this.notifyValueChanged();
      }

      this.s.setValue(selectedValue);
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

            for (String str2 : this.getOptions()) {
               try {
                  Integer num = (Integer)this.optionWidthCache.get(str2);
                  if (num != null) {
                     if (n7 + num + (n9 < this.getOptions().length - 1 ? SEPARATOR_WIDTH : 0) > n5) {
                        n8 += (int)(customFontUtil.getFontHeight() + 1.0F);
                        n7 = n3 + this.labelWidth;
                     }

                     int n10 = str2.equals(this.getSelectedValue())
                        ? RenderUtil.getHoveredColor(colorInt2, 0.1294, n, n2, n7 - 6, n8, num + 12, (int)(customFontUtil.getFontHeight() + 1.0F))
                        : RenderUtil.getHoveredColor(colorInt, 0.2, n, n2, n7 - 6, n8, num + 12, (int)(customFontUtil.getFontHeight() + 1.0F));
                     boolean flag3 = n9 < this.getOptions().length - 1;
                     d = (double)n7;
                     customFontUtil.drawString(str2, d, (double)n8, n10);
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
                  String str4 = this.getName() + " " + this.getParentModuleName() + " " + Arrays.toString((Object[])this.getOptions()) + " " + customFontUtil;
                  throw new RuntimeException(str4, e);
               }
            }

            this.renderedHeight = (int)((float)n8 + customFontUtil.getFontHeight() + 12.0F - (float)n4);
            this.contentWidth = 150;
         }
      } catch (Exception e2) {
         String str5 = this.getName() + " " + this.getParentModuleName() + " " + Arrays.toString((Object[])this.getOptions()) + " " + customFontUtil;
         throw new RuntimeException(str5, e2);
      }
   }

   @Override
   public void randomize() {
      if (!this.getDisplayName().contains("Point selection mode")) {
         this.setSelectedValue(this.options[ThreadLocalRandom.current().nextInt(this.options.length)]);
      }
   }

   private StringValue(String str, Module module, SettingGroup doubleValue, String str2, String[] strings) {
       super(module, doubleValue, str);
      this.s = (StringProperty)new StringProperty(this, "S", "").withOnValueChanged(obj -> this.setSelectedValue((String)obj));
      this.renderedHeight = 0;
      this.labelWidth = -1;
      this.optionWidthCache = new ConcurrentHashMap();
      this.s.setValue(str2);
      this.s.setDefaultValue(str2);
      this.setOptions(strings);
   }

   static {
      String str = ", ";
      SEPARATOR_WIDTH = (int)FontManager.consolas30Booleans.getStringWidth(str);
   }

   public int getSelectedIndex() {
      return Arrays.asList(this.options).indexOf(this.getSelectedValue());
   }

   public String[] getOptions() {
      return this.options;
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip(this.labelWidth);
   }

   private void applyOptions(String[] strings) {
      this.options = strings;
      this.optionWidthCache.clear();

      for (String str : strings) {
         if (str != null) {
            this.optionWidthCache.put(str, (int)FontManager.consolas30Booleans.getStringWidth(str));
         }
      }
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      boolean flag = false;
      if (mouseButton == 0 || mouseButton == 1) {
         int n = this.renderX + this.labelWidth;
         int renderY2 = this.renderY;
         int n2 = 0;

         for (String str : this.getOptions()) {
            Integer num = (Integer)this.optionWidthCache.get(str);
            if (num != null) {
               if (n + num + (n2 < this.getOptions().length - 1 ? SEPARATOR_WIDTH : 0) > this.boundsWidth) {
                  renderY2 += (int)(this.renderFont.getFontHeight() + 1.0F);
                  n = this.renderX + this.labelWidth;
               }

               if (RenderUtil.isHovered((float)(n - 6), (float)renderY2, (float)(num + 12), (float)((int)(this.renderFont.getFontHeight() + 1.0F)), mouseX, mouseY)) {
                  this.notifyUserChanged();
                  this.setSelectedValue(str);
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

}
