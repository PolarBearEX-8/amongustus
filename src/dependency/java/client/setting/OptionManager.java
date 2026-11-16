package client.setting;

import client.util.BuildManager;
import client.util.PreEndHandManager;
import client.util.SplineCollector;
import java.util.Arrays;
import java.util.List;

public abstract class OptionManager {
   public String labelText = null;
   public String labelText3 = null;
   public String labelText2 = null;
   public String[] stringArray = null;
   public String labelText7 = null;
   public String[] stringArray2 = null;
   public boolean enabled = true;
   public boolean enabled2 = true;
   public static final String labelText6 = "§a";
   public static final String labelText4 = "§c";
   public static final String labelText5 = "§9";

   public boolean isActiveAsFlag() {
      return !BuildManager.matchesTarget(this.labelText2, this.labelText7);
   }

   public String[] getStringArray2() {
      return this.stringArray2;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public boolean matchesTextByText(String text) {
      return false;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   private static int computeCount(String text, String[] textArray) {
      for (int index = 0; index < textArray.length; index++) {
         if (textArray[index].equals(text)) {
            return index;
         }
      }

      return -1;
   }

   public String resolveText(String text) {
      return "";
   }

   public boolean matchesText(String text) {
      return false;
   }

   public void updateState() {
      int count = computeCount(this.labelText2, this.stringArray);
      if (count >= 0) {
         int count2 = (count + 1) % this.stringArray.length;
         this.labelText2 = this.stringArray[count2];
      }
   }

   public String getText2() {
      return null;
   }

   public String getLabelText7() {
      return this.labelText7;
   }

   public String getText() {
      String text = SplineCollector.resolveText2(BuildManager.resolveTextByText(this.labelText3), "//");
      return PreEndHandManager.get("option." + this.getLabelText() + ".comment", text);
   }

   public void setEnabled2(boolean enabled3) {
      this.enabled2 = enabled3;
   }

   public boolean matchesText2(String text) {
      return computeCount(text, this.stringArray) >= 0;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean matchesText3(String text) {
      if (computeCount(text, this.stringArray) < 0) {
         return false;
      } else {
         this.labelText2 = text;
         return true;
      }
   }

   public String[] getTextArray() {
      return (String[])this.stringArray.clone();
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public float getFactor() {
      if (this.stringArray.length <= 1) {
         return 0.0F;
      } else {
         int count = computeCount(this.labelText2, this.stringArray);
         return count < 0 ? 0.0F : 1.0F * (float)count / ((float)this.stringArray.length - 1.0F);
      }
   }

   public String getTextAsText() {
      return PreEndHandManager.get("option." + this.labelText, this.labelText);
   }

   public String resolveTextByText(String text) {
      return PreEndHandManager.get("value." + this.labelText + "." + text, text);
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void updateState3() {
      int count = computeCount(this.labelText2, this.stringArray);
      if (count >= 0) {
         int count2 = (count - 1 + this.stringArray.length) % this.stringArray.length;
         this.labelText2 = this.stringArray[count2];
      }
   }

   public void applyFloat(float factor) {
      if (this.stringArray.length > 1) {
         float factor2 = BuildManager.computeFactor(factor, 0.0F, 1.0F);
         int count = Math.round(factor2 * (float)(this.stringArray.length - 1));
         this.labelText2 = this.stringArray[count];
      }
   }

   public boolean isActive() {
      return false;
   }

   public void setLabelText3(String labelText8) {
      this.labelText3 = labelText8;
   }

   public void applyTextArray(String[] textArray) {
      List list = Arrays.asList(this.stringArray2);

      for (int index = 0; index < textArray.length; index++) {
         String text = textArray[index];
         if (!list.contains(text)) {
            this.stringArray2 = (String[])BuildManager.resolveTargetArrayByTargetArray(this.stringArray2, text);
         }
      }
   }

   public void updateState2() {
      this.labelText2 = this.labelText7;
   }

   public OptionManager(String text, String text2, String text3, String[] textArray, String text4, String text5) {
      this.labelText = text;
      this.labelText3 = text2;
      this.labelText2 = text3;
      this.stringArray = textArray;
      this.labelText7 = text4;
      if (text5 != null) {
         this.stringArray2 = new String[]{text5};
      }
   }

   @Override
   public String toString() {
      return ""
         + this.labelText
         + ", value: "
         + this.labelText2
         + ", valueDefault: "
         + this.labelText7
         + ", paths: "
         + BuildManager.resolveText(this.stringArray2);
   }
}
