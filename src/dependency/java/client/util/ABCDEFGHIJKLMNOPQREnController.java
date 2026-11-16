package client.util;

public class ABCDEFGHIJKLMNOPQREnController {
   public ABCDEFGHIJKLMNOPQREnum aBCDEFGHIJKLMNOPQREnum;
   public String labelText;

   public ABCDEFGHIJKLMNOPQREnController(ABCDEFGHIJKLMNOPQREnum aBCDEFGHIJKLMNOPQREnum2, String text) {
      this.aBCDEFGHIJKLMNOPQREnum = aBCDEFGHIJKLMNOPQREnum2;
      this.labelText = text;
   }

   @Override
   public String toString() {
      return this.labelText;
   }

   public ABCDEFGHIJKLMNOPQREnum getABCDEFGHIJKLMNOPQREnum() {
      return this.aBCDEFGHIJKLMNOPQREnum;
   }

   public String getLabelText() {
      return this.labelText;
   }
}
