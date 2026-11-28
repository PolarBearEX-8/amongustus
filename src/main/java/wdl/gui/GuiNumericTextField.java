package wdl.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class GuiNumericTextField extends GuiTextField {
   private String lastSafeText = "0";

   @Override
   public void setText(String str) {
      String str2;
      try {
         str2 = String.valueOf(Integer.parseInt("0" + str));
      } catch (NumberFormatException e) {
         str2 = this.lastSafeText;
      }

      super.setText(str2);
      this.lastSafeText = str2;
   }

   public int getValue() {
      try {
         return Integer.parseInt("0" + this.getText());
      } catch (NumberFormatException e) {
         e.printStackTrace();
         return 0;
      }
   }

   @Override
   public String getText() {
      String text2 = super.getText();

      try {
         return String.valueOf(Integer.parseInt("0" + text2));
      } catch (NumberFormatException e) {
         this.setText(this.lastSafeText);
         return this.lastSafeText;
      }
   }

   @Override
   public void drawTextBox() {
      try {
         Integer.parseInt("0" + this.getText());
         this.lastSafeText = this.getText();
      } catch (NumberFormatException e) {
         this.setText(this.lastSafeText);
      }

      super.drawTextBox();
   }

   public void setValue(int n) {
      String str = String.valueOf(n);
      this.lastSafeText = str;
      this.setText(str);
   }

   public GuiNumericTextField(int n, FontRenderer fontRenderer, int n2, int n3, int n4, int n5) {
      super(n, fontRenderer, n2, n3, n4, n5);
      this.setText("0");
   }
}
