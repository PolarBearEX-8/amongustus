package client.util;

public class ProviderStringEntry {
   public int count2;
   public String labelText3;
   public int count;
   public final String labelText2;
   public String labelText;

   public String getLabelText3() {
      return this.labelText3;
   }

   public void setLabelText(String labelText4) {
      this.labelText = labelText4;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public void setCount(int count3) {
      this.count = count3;
   }

   public int getCount2() {
      return this.count2;
   }

   public void setCount2(int count3) {
      this.count2 = count3;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void setLabelText3(String labelText4) {
      this.labelText3 = labelText4;
   }

   public int getCount() {
      return this.count;
   }

   public ProviderStringEntry(String text) {
      this.labelText2 = text;
      this.labelText3 = "";
      this.labelText = "";
      this.count = -1;
      this.count2 = -1;
   }

   public ProviderStringEntry(String text, String text2, String text3, int count3, int count4) {
      this.labelText2 = text;
      this.labelText3 = text2;
      this.labelText = text3;
      this.count = count3;
      this.count2 = count4;
   }

   public ProviderStringEntry() {
      this.labelText2 = "rizz";
      this.labelText3 = "User";
      this.labelText = "§9";
      this.count = 1;
      this.count2 = 0;
   }
}
