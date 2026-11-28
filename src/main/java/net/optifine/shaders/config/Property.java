package net.optifine.shaders.config;

import java.util.Properties;
import net.optifine.config.Config;
import org.apache.commons.lang3.ArrayUtils;

public class Property {
   private int defaultValue = 0;
   private String propertyName = null;
   private String[] propertyValues = null;
   private String userName = null;
   private String[] userValues = null;
   private int value = 0;

   public String getPropertyName() {
      return this.propertyName;
   }

   public void setValue(int n) {
      this.value = n;
      if (this.value < 0 || this.value >= this.propertyValues.length) {
         this.value = this.defaultValue;
      }
   }

   public String getUserName() {
      return this.userName;
   }

   @Override
   public String toString() {
      return "" + this.propertyName + "=" + this.getPropertyValue() + " [" + Config.arrayToString3(this.propertyValues) + "], value: " + this.value;
   }

   public void saveTo(Properties properties) {
      if (properties != null) {
         properties.setProperty(this.getPropertyName(), this.getPropertyValue());
      }
   }

   public String getPropertyValue() {
      return this.propertyValues[this.value];
   }

   public int getValue() {
      return this.value;
   }

   public boolean loadFrom(Properties properties) {
      this.resetValue();
      if (properties == null) {
         return false;
      } else {
         String property = properties.getProperty(this.propertyName);
         return property == null ? false : this.setPropertyValue(property);
      }
   }

   public void resetValue() {
      this.value = this.defaultValue;
   }

   public boolean setPropertyValue(String str) {
      if (str == null) {
         this.value = this.defaultValue;
         return false;
      } else {
         this.value = ArrayUtils.indexOf(this.propertyValues, str);
         if (this.value >= 0 && this.value < this.propertyValues.length) {
            return true;
         } else {
            this.value = this.defaultValue;
            return false;
         }
      }
   }

   public String getUserValue() {
      return this.userValues[this.value];
   }

   public Property(String str, String[] strings, String str2, String[] strings2, int n) {
      this.propertyName = str;
      this.propertyValues = strings;
      this.userName = str2;
      this.userValues = strings2;
      this.defaultValue = n;
      if (strings.length != strings2.length) {
         throw new IllegalArgumentException("Property and user values have different lengths: " + strings.length + " != " + strings2.length);
      } else if (n >= 0 && n < strings.length) {
         this.value = n;
      } else {
         throw new IllegalArgumentException("Invalid default value: " + n);
      }
   }

   public void nextValue(boolean flag) {
      byte b = 0;
      int n = this.propertyValues.length - 1;
      this.value = Config.limit(this.value, b, n);
      if (flag) {
         this.value++;
         if (this.value > n) {
            this.value = b;
         }
      } else {
         this.value--;
         if (this.value < b) {
            this.value = n;
         }
      }
   }
}
