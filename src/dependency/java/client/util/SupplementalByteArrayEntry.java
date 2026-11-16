package client.util;

import client.event.PostMotionProcessor;

public class SupplementalByteArrayEntry implements PostMotionProcessor {
   public boolean enabled;
   public byte[] byteFlagArray;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {

      for (long index = 0x52fbb1fadeb21bf9L; (int)(index % 3L) != 1; index = (index ^ -0x469abfcbc3404a8eL) + 0x5164abfc4983435aL) {
         byte[] byteFlagArray2 = this.byteFlagArray;
         byteBufHandler.applyByteArray(byteFlagArray2);
      }

      boolean enabled2 = this.enabled;
      byteBufHandler.writeBoolean(enabled2);
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.enabled = byteBufHandler.readBoolean();
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public SupplementalByteArrayEntry() {
   }

   public SupplementalByteArrayEntry(byte[] byteValueArray, boolean flag) {
      this.byteFlagArray = byteValueArray;
      this.enabled = flag;
   }
}
