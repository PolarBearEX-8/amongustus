package client.util;

import client.event.PostMotionProcessor;
import client.event.PreUpdateInterceptor;

public class InternalByteArrayEntry implements PreUpdateInterceptor, PostMotionProcessor {
   public byte[] byteFlagArray;
   public int count2;
   public String labelText2;
   public String labelText3;
   public int count;
   public String labelText;
   public boolean enabled;

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      String labelText4 = this.labelText;
      byteBufHandler.applyTextByText(labelText4);
      String labelText5 = this.labelText3;
      byteBufHandler.applyTextByText(labelText5);
      boolean enabled2 = this.enabled;
      byteBufHandler.writeBoolean(enabled2);
      int count3 = this.count;
      byteBufHandler.writeInt(count3);
      int count4 = this.count2;
      byteBufHandler.writeInt(count4);
      String labelText6 = this.labelText2;
      byteBufHandler.applyTextByText(labelText6);
   }

   @Override
   public void setByteFlagArray(byte[] byteFlagArray2) {
      this.byteFlagArray = byteFlagArray2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
   }

   public InternalByteArrayEntry(byte[] byteValueArray, String text, String text2, String text3, int count3, boolean flag, int count4) {
      this.byteFlagArray = byteValueArray;
      this.labelText = text;
      this.labelText3 = text2;
      this.enabled = flag;
      this.count = count4;
      this.count2 = count3;
      this.labelText2 = text3;
   }

   public InternalByteArrayEntry() {
   }
}
