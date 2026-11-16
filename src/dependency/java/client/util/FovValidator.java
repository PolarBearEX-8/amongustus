package client.util;

import client.setting.OptionManager;

public class FovValidator {
   public String labelText;
   public OptionManager[] optionManagerArray;
   public int count;

   public FovValidator(String text, OptionManager[] optionManagerArray2, int count2) {
      this.labelText = text;
      this.optionManagerArray = optionManagerArray2;
      this.count = count2;
   }

   public OptionManager[] getOptionManagerArray() {
      return this.optionManagerArray;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }
}
