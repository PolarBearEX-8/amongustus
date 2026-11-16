package client.util;

import java.util.Properties;
import org.apache.commons.lang3.ArrayUtils;

public class RootManager {
   public int count2 = 0;
   public String labelText = null;
   public String[] stringArray = null;
   public String labelText2 = null;
   public String[] stringArray2 = null;
   public int count = 0;

   public void applyProperties(Properties properties) {
      if (properties != null) {
         properties.setProperty(this.getLabelText(), this.getText());
      }
   }

   public boolean matchesProperties(Properties properties) {
      this.updateState();
      if (properties == null) {
         return false;
      } else {
         String property = properties.getProperty(this.labelText);
         return property == null ? false : this.matchesText(property);
      }
   }

   public void setEnabled(boolean enabled) {
      int count3 = this.stringArray.length - 1;
      this.count = BuildManager.computeCount(this.count, 0, count3);
      if (enabled) {
         this.count++;
         if (this.count > count3) {
            this.count = 0;
         }
      } else {
         this.count--;
         if (this.count < 0) {
            this.count = count3;
         }
      }
   }

   public void applyIndex(int count3) {
      this.count = count3;
      if (this.count < 0 || this.count >= this.stringArray.length) {
         this.count = this.count2;
      }
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public String getTextAsText() {
      return this.stringArray2[this.count];
   }

   public boolean matchesText(String text) {
      if (text == null) {
         this.count = this.count2;
         return false;
      } else {
         this.count = ArrayUtils.indexOf(this.stringArray, text);
         if (this.count >= 0 && this.count < this.stringArray.length) {
            return true;
         } else {
            this.count = this.count2;
            return false;
         }
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public void updateState() {
      this.count = this.count2;
   }

   public String getText() {
      return this.stringArray[this.count];
   }

   public RootManager(String text, String[] textArray, String text2, String[] textArray2, int count3) {
      this.labelText = text;
      this.stringArray = textArray;
      this.labelText2 = text2;
      this.stringArray2 = textArray2;
      this.count2 = count3;
      if (textArray.length != textArray2.length) {
         throw new IllegalArgumentException("Property and user values have different lengths: " + textArray.length + " != " + textArray2.length);
      } else if (count3 >= 0 && count3 < textArray.length) {
         this.count = count3;
      } else {
         throw new IllegalArgumentException("Invalid default value: " + count3);
      }
   }

   @Override
   public String toString() {
      return "" + this.labelText + "=" + this.getText() + " [" + BuildManager.resolveText(this.stringArray) + "], value: " + this.count;
   }
}
