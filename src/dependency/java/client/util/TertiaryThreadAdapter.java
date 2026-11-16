package client.util;

import client.event.PostMotionConsumer;
import net.minecraft.client.Minecraft;

public class TertiaryThreadAdapter extends Thread {
   public String labelText = null;
   public PostMotionConsumer postMotionConsumer = null;

   public PostMotionConsumer getPostMotionConsumer() {
      return this.postMotionConsumer;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public TertiaryThreadAdapter(String text, PostMotionConsumer postMotionConsumer2) {
      this.labelText = text;
      this.postMotionConsumer = postMotionConsumer2;
   }

   @Override
   public void run() {
      try {
         byte[] byteValueArray = AuxiliaryResponseManager.resolveByteArray(this.labelText, Minecraft.getMinecraft().getProxy());
         this.postMotionConsumer.applyText(this.labelText, byteValueArray, (Throwable)null);
      } catch (Exception exception) {
         this.postMotionConsumer.applyText(this.labelText, (byte[])null, exception);
      }
   }
}
