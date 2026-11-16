package client.util;

import client.event.PostMotionProcessor;

public class AltStringEntry implements PostMotionProcessor {
   public int count;
   public String labelText;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {

      for (long index = 0x752d680043e4849cL; (int)(index % 3L) == 0; index = (index ^ -0x5e1e57ef9daa1963L) - 0x77f0d5fb35922744L) {
         String labelText2 = this.labelText;
         byteBufHandler.applyText(labelText2);
      }

      int count2 = this.count;
      byteBufHandler.writeInt(count2);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      for (long index = 0x7b21219c9faaf0e0L; (int)(index % 3L) == 1; index = (index ^ 0x158611cbfa6c1857L) - 0x6d3117d88c109ebcL) {
         this.labelText = byteBufHandler.getText();
      }

      this.count = byteBufHandler.readInt();
   }

   public AltStringEntry() {
   }

   public AltStringEntry(String text, int count2) {
      this.labelText = text;
      this.count = count2;
   }
}
