package cc.baritone.utils;

import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import net.minecraft.client.gui.FontRenderer;

public class InternalTexturesGuiIco extends InternalTexturesGuiIconsPngManagerAdapter {
   public String labelText2 = "0";

   public InternalTexturesGuiIco(int count, FontRenderer fontRenderer, int count2, int count3, int count4, int count5) {
      super(count, fontRenderer, count2, count3, count4, count5);
      this.applyText("0");
   }

   public int getCountAsIndex() {
      try {
         return Integer.parseInt("0" + this.getLabelText());
      } catch (NumberFormatException numberFormatException) {
         numberFormatException.printStackTrace();
         return 0;
      }
   }

   @Override
   public String getLabelText() {
      String labelText = super.getLabelText();

      try {
         return String.valueOf(Integer.parseInt("0" + labelText));
      } catch (NumberFormatException numberFormatException) {
         this.applyText(this.labelText2);
         return this.labelText2;
      }
   }

   @Override
   public void updateState() {
      try {
         Integer.parseInt("0" + this.getLabelText());
         this.labelText2 = this.getLabelText();
      } catch (NumberFormatException numberFormatException) {
         this.applyText(this.labelText2);
      }

      super.updateState();
   }

   public void applyIndex6(int count) {
      String text = String.valueOf(count);
      this.labelText2 = text;
      this.applyText(text);
   }

   @Override
   public void applyText(String text) {
      String text2;
      try {
         text2 = String.valueOf(Integer.parseInt("0" + text));
      } catch (NumberFormatException numberFormatException) {
         text2 = this.labelText2;
      }

      super.applyText(text2);
      this.labelText2 = text2;
   }
}
