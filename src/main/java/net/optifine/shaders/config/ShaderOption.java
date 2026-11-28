package net.optifine.shaders.config;

import java.util.Arrays;
import java.util.List;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import net.optifine.util.StrUtils;

public abstract class ShaderOption {
   private String name = null;
   private String description = null;
   private String value = null;
   private String[] values = null;
   private String valueDefault = null;
   private String[] paths = null;
   private boolean enabled = true;
   private boolean visible = true;
   public static final String COLOR_GREEN = null;
   public static final String COLOR_RED = null;
   public static final String COLOR_BLUE = null;

   public void setDescription(String str) {
      this.description = str;
   }

   public boolean setValue(String str) {
      if (getIndex(str, this.values) < 0) {
         return false;
      } else {
         this.value = str;
         return true;
      }
   }

   public String getValueDefault() {
      return this.valueDefault;
   }

   public String getDescriptionText() {
      String str = Config.normalize(this.description);
      str = StrUtils.removePrefix(str, "//");
      return Shaders.translate("option." + this.getName() + ".comment", str);
   }

   public boolean matchesLine(String str) {
      return false;
   }

   public String getValue() {
      return this.value;
   }

   public void prevValue() {
      int index = getIndex(this.value, this.values);
      if (index >= 0) {
         index = (index - 1 + this.values.length) % this.values.length;
         this.value = this.values[index];
      }
   }

   public boolean isVisible() {
      return this.visible;
   }

   public void setEnabled(boolean flag) {
      this.enabled = flag;
   }

   public void setIndexNormalized(float f) {
      if (this.values.length > 1) {
         f = Config.limit3(f, 0.0F, 1.0F);
         int n = Math.round(f * (float)(this.values.length - 1));
         this.value = this.values[n];
      }
   }

   public String getName() {
      return this.name;
   }

   public boolean checkUsed() {
      return false;
   }

   public boolean isUsedInLine(String str) {
      return false;
   }

   public void resetValue() {
      this.value = this.valueDefault;
   }

   public String getValueText(String str) {
      return Shaders.translate("value." + this.name + "." + str, str);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean isChanged() {
      return !Config.equals(this.value, this.valueDefault);
   }

   public String[] getValues() {
      return (String[])this.values.clone();
   }

   public String[] getPaths() {
      return this.paths;
   }

   public String getDescription() {
      return this.description;
   }

   public String getNameText() {
      return Shaders.translate("option." + this.name, this.name);
   }

   private static int getIndex(String str, String[] strings) {
      for (int i = 0; i < strings.length; i++) {
         if (strings[i].equals(str)) {
            return i;
         }
      }

      return -1;
   }

   public String getSourceLine() {
      return null;
   }

   public void nextValue() {
      int index = getIndex(this.value, this.values);
      if (index >= 0) {
         index = (index + 1) % this.values.length;
         this.value = this.values[index];
      }
   }

   public boolean isValidValue(String str) {
      return getIndex(str, this.values) >= 0;
   }

   public float getIndexNormalized() {
      if (this.values.length <= 1) {
         return 0.0F;
      } else {
         int index = getIndex(this.value, this.values);
         return index < 0 ? 0.0F : 1.0F * (float)index / ((float)this.values.length - 1.0F);
      }
   }

   @Override
   public String toString() {
      return "" + this.name + ", value: " + this.value + ", valueDefault: " + this.valueDefault + ", paths: " + Config.arrayToString3(this.paths);
   }

   public String getValueColor(String str) {
      return "";
   }

   public void addPaths(String[] strings) {
      List list = Arrays.asList(this.paths);

      for (int i = 0; i < strings.length; i++) {
         String str = strings[i];
         if (!list.contains(str)) {
            this.paths = (String[])Config.addObjectToArray(this.paths, str);
         }
      }
   }

   public ShaderOption(String str, String str2, String str3, String[] strings, String str4, String str5) {
      this.name = str;
      this.description = str2;
      this.value = str3;
      this.values = strings;
      this.valueDefault = str4;
      if (str5 != null) {
         this.paths = new String[]{str5};
      }
   }

   public void setVisible(boolean flag) {
      this.visible = flag;
   }
}
