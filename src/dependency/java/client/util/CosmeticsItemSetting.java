package client.util;

import client.event.PostMotionProcessor;

public class CosmeticsItemSetting implements PostMotionProcessor {
   public String labelText;
   public int count;

   public int getCount() {
      return this.count;
   }

   public String getText() {
      return this.labelText;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
      String labelText2 = this.labelText;
      byteBufHandler.applyTextByText(labelText2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count = byteBufHandler.readInt();
      this.labelText = byteBufHandler.getTextAsText();
   }

   public CosmeticsItemSetting() {
   }

   public CosmeticsItemSetting(String text) {
      this.labelText = text;
   }
}
