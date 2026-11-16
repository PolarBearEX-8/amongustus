package client.event;

import client.util.ByteBufHandler;

public class ByteBufHandlerPostMotionProcessor implements PostMotionProcessor {
   private static String[] stringArray;
   public int count;
   public String labelText;
   public String labelText2;

   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
      String labelText3 = this.labelText;
      byteBufHandler.applyTextByText(labelText3);
      String labelText4 = this.labelText2;
      byteBufHandler.applyTextByText(labelText4);
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count = byteBufHandler.readInt();
      this.labelText = byteBufHandler.getTextAsText();
      this.labelText2 = byteBufHandler.getTextAsText();
   }

   public ByteBufHandlerPostMotionProcessor(int count2, String text, String text2) {
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }

   public void setLabelText2(String labelText3) {
      this.labelText2 = labelText3;
   }

   public ByteBufHandlerPostMotionProcessor() {
   }

   public void setLabelText(String labelText3) {
      this.labelText = labelText3;
   }

   public static String[] getStringArray() {
      return null;
   }
}
