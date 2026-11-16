package client.util;

import client.network.CosmeticsNetworkHandler;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.function.Consumer;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public abstract class ConsumerEntry implements MinecraftAccessor {
   public Consumer consumer;
   public final double value;
   public String labelText2;
   public String labelText;
   public boolean enabled;
   public SecretKey secretKey;

   public abstract void updateState2();

   public double getValue() {
      return this.value;
   }

   public void applyDouble(double offset) {

      try {
         double valueAsDouble = this.getValueAsDouble();
         double offset2 = valueAsDouble + offset;
         this.applyDoubleByDouble(offset2);
      } catch (Exception exception) {
      }
   }

   public void applyFlag(boolean flag, long value2) {
      if (this.enabled != flag) {
         this.enabled = flag;
         if (flag) {
            this.updateState();
         } else {
            this.updateState2();
            if (CosmeticsNetworkHandler.isToggled() && CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().canExecute()) {
               try {
                  CosmeticsNetworkHandler simpleChannelInboundHandlerAdapter = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
                  AltLongEntry altLongEntry = new AltLongEntry(SupplementalLongEntry.byteIndexArray, value2, (int)this.getValueAsDouble());
                  simpleChannelInboundHandlerAdapter.updatePostMotionProcessor(altLongEntry);
               } catch (Exception exception) {
               }
            }
         }
      }
   }

   public abstract String getTextAsText();

   private double computeValue(String text) throws Exception {
      Cipher instance = Cipher.getInstance("AES");
      instance.init(2, this.secretKey);
      byte[] byteValueArray = Base64.getDecoder().decode(text);
      byte[] byteValueArray2 = instance.doFinal(byteValueArray);
      return this.computeValue(byteValueArray2);
   }

   private SecretKey getSecretKey() throws Exception {
      KeyGenerator instance = KeyGenerator.getInstance("AES");
      instance.init(128);
      return instance.generateKey();
   }

   public abstract String getText();

   public void updateState3() throws Exception {
      double offset = 0.0;
      this.applyDoubleByDouble(offset);
      this.updateState2();
   }

   public void setConsumer(Consumer consumer2) {
      this.consumer = consumer2;
   }

   private byte[] resolveByteArray(double offset) {
      return ByteBuffer.allocate(8).putDouble(offset).array();
   }

   private double computeValue(byte[] byteValueArray) {
      return ByteBuffer.wrap(byteValueArray).getDouble();
   }

   public double getValueAsDouble() throws Exception {
      short shortValue = 29171;
      char character = '\u9d51';
      short shortValue2 = 990;
      String labelText3 = this.labelText;
      if (!this.labelText2.equals(this.resolveText(labelText3))) {
         throw new SecurityException("Data integrity violation: possible tampering detected.");
      } else {
         String labelText4 = this.labelText;
         return this.computeValue(labelText4);
      }
   }

   public abstract void updateState();

   public void applyDoubleByDouble(double offset) throws Exception {
      this.labelText = this.resolveText(offset);
      String labelText3 = this.labelText;
      this.labelText2 = this.resolveText(labelText3);
      if (this.consumer != null && offset != this.value) {
         this.consumer.accept(offset / this.value * 100.0);
      }
   }

   private String resolveText(double offset) throws Exception {
      Cipher instance = Cipher.getInstance("AES");
      instance.init(1, this.secretKey);
      byte[] byteValueArray = instance.doFinal(this.resolveByteArray(offset));
      return Base64.getEncoder().encodeToString(byteValueArray);
   }

   private String resolveText(String text) throws Exception {
      byte[] byteValueArray = MessageDigest.getInstance("SHA-256").digest(text.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(byteValueArray);
   }

   public ConsumerEntry(double offset) {
      this.value = offset;

      try {
         this.secretKey = this.getSecretKey();
         double offset2 = 0.0;
         this.applyDoubleByDouble(offset2);
      } catch (Exception exception) {
         this.secretKey = null;
      }
   }
}
