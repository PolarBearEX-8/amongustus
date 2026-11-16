package client.util;

import client.event.PostMotionProcessor;

public class CoreListenerAdapter implements PostMotionProcessor {
   public String labelText3;
   public String labelText2;
   public String labelText;

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      String labelText4 = this.labelText3;
      byteBufHandler.applyText(labelText4);
      String labelText5 = this.labelText;
      byteBufHandler.applyText(labelText5);
      String labelText6 = this.labelText2;
      byteBufHandler.applyText(labelText6);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText3 = byteBufHandler.getText();
      this.labelText = byteBufHandler.getText();
      this.labelText2 = byteBufHandler.getText();
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public CoreListenerAdapter(String text, String text2, String text3) {
      this.labelText3 = text;
      this.labelText = text2;
      this.labelText2 = text3;
   }

   public CoreListenerAdapter() {
   }
}
