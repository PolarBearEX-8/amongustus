package client.util;

import client.event.PostMotionProcessor;

public class ExtraStringHolder implements PostMotionProcessor {
   public String labelText3;
   public boolean enabled = false;
   public byte[] byteFlagArray2;
   public int count2;
   public String labelText2;
   public String labelText;
   public byte[] byteFlagArray;
   public int count;

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray3 = this.byteFlagArray2;
      byteBufHandler.applyByteArray(byteFlagArray3);
      int count3 = this.count2;
      byteBufHandler.writeInt(count3);
      int count4 = this.count;
      byteBufHandler.writeInt(count4);
      String labelText4 = this.labelText3;
      byteBufHandler.applyTextByText(labelText4);
      String labelText5 = this.labelText2;
      byteBufHandler.applyTextByText(labelText5);
      byte[] byteFlagArray4 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray4);
      boolean enabled2 = this.enabled;
      byteBufHandler.writeBoolean(enabled2);
      String labelText6 = this.labelText;
      byteBufHandler.applyTextByText(labelText6);
   }

   public byte[] getByteFlagArray2() {
      return this.byteFlagArray2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray2 = byteBufHandler.getByteArray();
      this.count2 = byteBufHandler.readInt();
      this.count = byteBufHandler.readInt();
      this.labelText3 = byteBufHandler.getTextAsText();
      this.labelText2 = byteBufHandler.getTextAsText();
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.enabled = byteBufHandler.readBoolean();
      this.labelText = byteBufHandler.getTextAsText();
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public ExtraStringHolder(String text, String text2, int count3, int count4, byte[] byteValueArray) {
      this.labelText3 = text2;
      this.labelText2 = "";
      this.byteFlagArray = new byte[1];
      this.labelText = text;
      this.enabled = true;
      this.count = count3;
      this.count2 = count4;
      this.byteFlagArray2 = byteValueArray;
   }

   public ExtraStringHolder(String text, String text2, byte[] byteValueArray, int count3, int count4, byte[] byteValueArray2) {
      this.labelText3 = text;
      this.labelText2 = text2;
      this.byteFlagArray = byteValueArray;
      this.count = count3;
      this.count2 = count4;
      this.labelText = "";
      this.byteFlagArray2 = byteValueArray2;
   }
}
