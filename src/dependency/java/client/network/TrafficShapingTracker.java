package client.network;

import client.util.PlayerUtil;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.handler.traffic.TrafficCounter;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class TrafficShapingTracker extends ChannelTrafficShapingHandler {
   public int count;
   public long timestamp2;
   public double value;
   public static final String labelText;
   public int count2;
   public double value2;
   public long timestamp;

   public double getCoordinate() {
      return this.value;
   }

   public void updateState2() {
      this.count2++;
   }

   private static String a(byte[] byteValueArray) {
      int count3 = 0;
      int count4;
      char[] characterArray = new char[count4 = byteValueArray.length];

      for (int index = 0; index < count4; index++) {
         int count5;
         int count6 = count5 = 255 & byteValueArray[index];

         try {
            if (count6 < 192) {
               characterArray[count3++] = (char)count5;
               continue;
            }
         } catch (RuntimeException exception) {
            throw a(exception);
         }

         if (count5 < 224) {
            char character = (char)((char)(count5 & 31) << 6);
            byte byteValue = byteValueArray[++index];
            char character2 = (char)(character | (char)(byteValue & 63));
            characterArray[count3++] = character2;
         } else if (index < count4 - 2) {
            char character3 = (char)((char)(count5 & 15) << '\f');
            byte byteValue2 = byteValueArray[++index];
            char character4 = (char)(character3 | (char)(byteValue2 & 63) << 6);
            byte byteValue3 = byteValueArray[++index];
            char character5 = (char)(character4 | (char)(byteValue3 & 63));
            characterArray[count3++] = character5;
         }
      }

      return new String(characterArray, 0, count3);
   }

   public void updateState() {
      this.count++;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public double getCoordinate2() {
      return this.value2;
   }

   public long getTimestamp2() {
      return this.timestamp2;
   }

   public TrafficShapingTracker() {
      super(1000L);
   }

   private static RuntimeException a(RuntimeException exception) {
      return exception;
   }

   static {
      try {
      Cipher cipher;
      Cipher cipher2 = cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory instance = SecretKeyFactory.getInstance("DES");
      byte[] byteValueArray = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};

      for (int index = 1; index < 8; index++) {
         byteValueArray[index] = (byte)((int)(0xa62eeb75649L << index * 8 >>> 56));
      }

      cipher2.init(2, instance.generateSecret(new DESKeySpec(byteValueArray)), new IvParameterSpec(new byte[8]));
      String text = a(
            cipher.doFinal(":±'¦ñÝk)Ë\u00800º©ü¿ÜÔ§\u009a ^¥=\u0013".getBytes("ISO-8859-1"))
         )
         .intern();
      labelText = text;
      } catch (Exception exception) {
         throw new RuntimeException(exception);
      }
   }

   public void doAccounting(TrafficCounter trafficCounter) {
      this.timestamp2 = trafficCounter.lastReadThroughput();
      this.timestamp = trafficCounter.lastWriteThroughput();
      this.value2 = PlayerUtil.getDouble((double)this.count, this.value2, 0.75);
      this.value = PlayerUtil.getDouble((double)this.count2, this.value, 0.75);
      this.count = 0;
      this.count2 = 0;
   }
}
