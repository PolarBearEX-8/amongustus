package client.util;

import client.event.PostMotionProcessor;

public class PrimaryListenerAdapter implements PostMotionProcessor {
   public String labelText2;
   public String labelText5;
   public String labelText4;
   public boolean enabled;
   public int count;
   public String labelText3;
   public String labelText;
   public int count2;

   public String getLabelText5() {
      return this.labelText5;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      String labelText6 = this.labelText3;
      byteBufHandler.applyText(labelText6);
      String labelText7 = this.labelText4;
      byteBufHandler.applyTextByText(labelText7);
      String labelText8 = this.labelText;
      byteBufHandler.applyText(labelText8);
      String labelText9 = this.labelText5;
      byteBufHandler.applyTextByText(labelText9);
      String labelText10 = this.labelText2;
      byteBufHandler.applyTextByText(labelText10);
      boolean enabled2 = this.enabled;
      byteBufHandler.writeBoolean(enabled2);
      int count3 = this.count2;
      byteBufHandler.writeInt(count3);
      int count4 = this.count;
      byteBufHandler.writeInt(count4);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.labelText3 = byteBufHandler.getText();
      this.labelText4 = byteBufHandler.getTextAsText();
      this.labelText = byteBufHandler.getText();
      this.labelText5 = byteBufHandler.getTextAsText();
      this.labelText2 = byteBufHandler.getTextAsText();
      this.enabled = byteBufHandler.readBoolean();
      this.count2 = byteBufHandler.readInt();
      this.count = byteBufHandler.readInt();
   }

   public String getLabelText4() {
      return this.labelText4;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public PrimaryListenerAdapter() {
   }

   public PrimaryListenerAdapter(String text, String text2, int count3, String text3, String text4, int count4, String text5, boolean flag) {
      this.labelText3 = text;
      this.labelText4 = text2;
      this.labelText = text3;
      this.labelText5 = text4;
      this.labelText2 = text5;
      this.enabled = flag;
      this.count2 = count3;
      this.count = count4;
   }
}
