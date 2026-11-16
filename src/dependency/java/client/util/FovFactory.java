package client.util;

public class FovFactory {
   public String labelText;
   public String labelText2;

   public String getText() {
      return "#define " + this.labelText + " " + this.labelText2;
   }

   @Override
   public String toString() {
      return this.getText();
   }

   public FovFactory(String text, String text2) {
      this.labelText = text;
      this.labelText2 = text2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public String getLabelText2() {
      return this.labelText2;
   }
}
