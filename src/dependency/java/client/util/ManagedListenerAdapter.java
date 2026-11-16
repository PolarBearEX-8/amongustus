package client.util;

import client.event.PostMotionProcessor;

public class ManagedListenerAdapter implements PostMotionProcessor {
   public byte[] byteFlagArray;
   public String labelText;
   public String labelText2;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      String labelText3 = this.labelText;
      byteBufHandler.applyTextByText(labelText3);
      String labelText4 = this.labelText2;
      byteBufHandler.applyText(labelText4);
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.labelText = byteBufHandler.getTextAsText();
      this.labelText2 = byteBufHandler.getText();
   }

   public ManagedListenerAdapter(byte[] byteValueArray, String text, String text2) {
      this.byteFlagArray = byteValueArray;
      this.labelText = text != null && !text.isEmpty() ? text.substring(0, Math.min(1100, text.length())) : text;
      this.labelText2 = text2;
   }

   public ManagedListenerAdapter() {
   }
}
