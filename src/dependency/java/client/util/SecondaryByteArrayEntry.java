package client.util;

import client.event.PostMotionProcessor;

public class SecondaryByteArrayEntry implements PostMotionProcessor {
   public byte[] byteFlagArray;
   public String labelText;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {

      for (long index = 0x7257c746c6c06d3cL; (int)(index % 3L) != 1; index = (index ^ 0x4bd6202bb978707dL) - 0x2d6f89fefe96e8c2L) {
         byte[] byteFlagArray2 = this.byteFlagArray;
         byteBufHandler.applyByteArray(byteFlagArray2);
      }

      String labelText2 = this.labelText;
      byteBufHandler.applyTextByText(labelText2);
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      for (long index = 0x1c4bac9c0e7b7a08L; (int)(index % 3L) != 0; index = (index ^ 0xadd3404987748baL) + 0x830262ed5aa4af1L) {
         this.byteFlagArray = byteBufHandler.getByteArray();
      }

      this.labelText = byteBufHandler.getTextAsText();
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }
}
