package client.util;

import client.event.PostMotionProcessor;

public class IndirectStringEntry implements PostMotionProcessor {
   public String labelText2;
   public String labelText3;
   public long timestamp;
   public String labelText;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      String labelText4 = this.labelText3;
      byteBufHandler.applyTextByText(labelText4);
      String labelText5 = this.labelText;
      byteBufHandler.applyTextByText(labelText5);
      String labelText6 = this.labelText2;
      byteBufHandler.applyTextByText(labelText6);
      long timestamp2 = this.timestamp;
      byteBufHandler.writeLong(timestamp2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText3 = byteBufHandler.getTextAsText();
      this.labelText = byteBufHandler.getTextAsText();
      this.labelText2 = byteBufHandler.getTextAsText();
      this.timestamp = byteBufHandler.readLong();
   }

   public IndirectStringEntry(String text, String text2, String text3, long value) {
      this.labelText3 = text;
      this.labelText = text2;
      this.labelText2 = text3;
      this.timestamp = value;
   }

   public IndirectStringEntry() {
   }
}
