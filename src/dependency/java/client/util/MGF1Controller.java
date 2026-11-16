package client.util;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MGF1Controller {
   public final MessageDigest messageDigest;

   public void applyByteArray(byte[] byteValueArray, int count, int count2, int count3, byte[] byteValueArray2, int count4) throws RuntimeException {
      int count5 = count3;
      int count6 = count4;
      byte[] byteValueArray3 = new byte[4];
      byte[] byteValueArray4 = new byte[this.messageDigest.getDigestLength()];

      while (count5 > 0) {
         this.messageDigest.update(byteValueArray, count, count2);
         this.messageDigest.update(byteValueArray3);

         try {
            this.messageDigest.digest(byteValueArray4, 0, byteValueArray4.length);
         } catch (DigestException digestException) {
            throw new RuntimeException(digestException.toString());
         }

         for (int index = 0; index < byteValueArray4.length && count5 > 0; count5--) {
            int count7 = count6++;
            byteValueArray2[count7] ^= byteValueArray4[index++];
         }

         if (count5 > 0) {
            int count8 = byteValueArray3.length - 1;

            while (++byteValueArray3[count8] == 0 && count8 > 0) {
               count8--;
            }
         }
      }
   }

   public MGF1Controller(String text) throws NoSuchAlgorithmException {
      this.messageDigest = MessageDigest.getInstance(text);
   }

   public String resolveText(byte byteValue) {
      return "MGF1" + this.messageDigest.getAlgorithm();
   }
}
