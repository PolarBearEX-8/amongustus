package client.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class EncryptedDataHelper {
   public static final int count = 245;
   public static final byte byteFlag = 2;
   public static final int count2 = 100;
   public static final SignumComparable signumComparable = SignumComparable.resolveSignumComparableByTime(2L);
   public static final int count3 = 2048;
   public static final SignumComparable signumComparable2 = SignumComparable.resolveSignumComparableByTime(65537L);
   public static final byte byteFlag2 = 1;

   public static byte[] resolveByteArray2(byte[] byteValueArray, SignumComparableController signumComparableController) throws Exception {
      if (byteValueArray.length < 2) {
         throw new Exception("Invalid encrypted data format");
      } else {
         byte byteValue = byteValueArray[0];
         if (byteValue == 1) {
            byte[] byteValueArray2 = new byte[byteValueArray.length - 1];
            System.arraycopy(byteValueArray, 1, byteValueArray2, 0, byteValueArray2.length);
            int count4 = (signumComparableController.getSignumComparable6().getCount3() + 7) / 8;

            try {
               SignumComparable signumComparable3 = resolveSignumComparable(byteValueArray2);
               SignumComparable signumComparable4 = signumComparableController.getSignumComparable2();
               SignumComparable signumComparable5 = signumComparableController.getSignumComparable3();
               SignumComparable signumComparable6 = signumComparable3.resolveSignumComparable2(signumComparable5, signumComparable4);
               SignumComparable signumComparable7 = signumComparableController.getSignumComparable4();
               SignumComparable signumComparable8 = signumComparableController.getSignumComparable5();
               SignumComparable signumComparable9 = signumComparable3.resolveSignumComparable2(signumComparable8, signumComparable7);
               SignumComparable signumComparable10 = signumComparableController.getSignumComparable();
               SignumComparable signumComparable11 = signumComparable6.resolveSignumComparable11(signumComparable9);
               SignumComparable signumComparable12 = signumComparableController.getSignumComparable2();
               SignumComparable signumComparable13 = signumComparable11.resolveSignumComparable2(signumComparable12);
               signumComparable10 = signumComparable10.resolveSignumComparable4(signumComparable13);
               SignumComparable signumComparable14 = signumComparableController.getSignumComparable2();
               SignumComparable signumComparable15 = signumComparable10.resolveSignumComparable2(signumComparable14);
               SignumComparable signumComparable16 = signumComparableController.getSignumComparable4();
               SignumComparable signumComparable17 = signumComparable15.resolveSignumComparable4(signumComparable16);
               SignumComparable signumComparable18 = signumComparable9.resolveSignumComparable5(signumComparable17);
               byte[] byteValueArray3 = resolveByteArray(signumComparable18, count4);
               return SplineQueue.resolveByteArray(byteValueArray3);
            } catch (Exception exception) {
               throw new Exception("Decryption failed: " + exception.getMessage());
            }
         } else if (byteValue == 2) {
            ByteBuffer buffer = ByteBuffer.wrap(byteValueArray);
            buffer.get();
            byte[] byteValueArray4 = new byte[(buffer.get() & 255) << 8 | buffer.get() & 255];
            buffer.get(byteValueArray4);
            byte[] byteValueArray5 = new byte[buffer.remaining()];
            buffer.get(byteValueArray5);
            byte[] byteValueArray6 = new byte[byteValueArray4.length + 1];
            byteValueArray6[0] = 1;
            System.arraycopy(byteValueArray4, 0, byteValueArray6, 1, byteValueArray4.length);
            byte[] byteValueArray7 = resolveByteArray2(byteValueArray6, signumComparableController);
            return EncryptedDataHelperImpl.resolveByteArrayByByteArray(byteValueArray5, byteValueArray7);
         } else {
            throw new Exception("Unknown encryption mode: " + byteValue);
         }
      }
   }

   public static byte[] resolveByteArray(SignumComparableController signumComparableController) {
      try {
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         SignumComparable signumComparable3 = signumComparableController.getSignumComparable6();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable3);
         SignumComparable signumComparable4 = signumComparableController.getSignumComparable7();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable4);
         SignumComparable signumComparable5 = signumComparableController.getSignumComparable8();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable5);
         SignumComparable signumComparable6 = signumComparableController.getSignumComparable2();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable6);
         SignumComparable signumComparable7 = signumComparableController.getSignumComparable4();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable7);
         byte[] byteValueArray = byteArrayOutputStream.toByteArray();
         byteArrayOutputStream.close();
         return byteValueArray;
      } catch (Exception exception) {
         throw new RuntimeException("Failed to export key pair: " + exception.getMessage());
      }
   }

   public static String resolveText(SignumComparableController signumComparableController) {
      try {
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         SignumComparable signumComparable3 = signumComparableController.getSignumComparable6();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable3);
         SignumComparable signumComparable4 = signumComparableController.getSignumComparable7();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable4);
         byte[] byteValueArray = byteArrayOutputStream.toByteArray();
         byteArrayOutputStream.close();
         return AutoRegisterEncryption.getAutoRegisterCrypto().getText(byteValueArray);
      } catch (Exception exception) {
         throw new RuntimeException("Failed to export public key: " + exception.getMessage());
      }
   }

   public static SignumComparable[] resolveSignumComparableArray(byte[] byteValueArray) {
      try {
         ByteBuffer buffer = ByteBuffer.wrap(byteValueArray);
         SignumComparable signumComparable3 = resolveSignumComparable(buffer);
         SignumComparable signumComparable4 = resolveSignumComparable(buffer);
         return new SignumComparable[]{signumComparable3, signumComparable4};
      } catch (Exception exception) {
         throw new RuntimeException("Failed to import public key: " + exception.getMessage());
      }
   }

   public static byte[] resolveByteArray(byte[] byteValueArray, SignumComparableController signumComparableController) throws Exception {
      return resolveByteArray2(byteValueArray, signumComparableController);
   }

   private static byte[] resolveByteArray2(byte[] byteValueArray, SignumComparable signumComparable3, SignumComparable signumComparable4) throws Exception {
      int count4 = (signumComparable3.getCount3() + 7) / 8;

      try {
         SignumComparable signumComparable5 = resolveSignumComparable(SplineQueue.resolveByteArray(byteValueArray, count4)).resolveSignumComparable2(signumComparable4, signumComparable3);
         return resolveByteArray(signumComparable5, count4);
      } catch (Exception exception) {
         throw new Exception("Symmetric key encryption failed: " + exception.getMessage());
      }
   }

   public static SignumComparableController resolveSignumComparableController(byte[] byteValueArray) {
      try {
         ByteBuffer buffer = ByteBuffer.wrap(byteValueArray);
         SignumComparable signumComparable3 = resolveSignumComparable(buffer);
         SignumComparable signumComparable4 = resolveSignumComparable(buffer);
         SignumComparable signumComparable5 = resolveSignumComparable(buffer);
         SignumComparable signumComparable6 = resolveSignumComparable(buffer);
         SignumComparable signumComparable7 = resolveSignumComparable(buffer);
         return new SignumComparableController(signumComparable3, signumComparable4, signumComparable5, signumComparable6, signumComparable7);
      } catch (Exception exception) {
         throw new RuntimeException("Failed to import key pair: " + exception.getMessage());
      }
   }

   public static byte[] resolveByteArray(byte[] byteValueArray, SignumComparable signumComparable3, SignumComparable signumComparable4) {
      int count4 = 0;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      byte[] entry4 = null;
      ByteBuffer buffer = null;
      count4 = (signumComparable3.getCount3() + 7) / 8;
      entry = SplineQueue.resolveByteArray(byteValueArray, count4);
      if (entry != null) {
         entry2 = resolveSignumComparable((byte[])entry);
         entry3 = ((SignumComparable)entry2).resolveSignumComparable2(signumComparable4, signumComparable3);
         entry4 = resolveByteArray((SignumComparable)entry3, count4);
         buffer = ByteBuffer.allocate(1 + entry4.length);
         buffer.put((byte)1);
         buffer.put((byte[])entry4);
         return buffer.array();
      } else {
         return new byte[1];
      }
   }

   private static SignumComparable resolveSignumComparable(byte[] byteValueArray) {
      return new SignumComparable(1, byteValueArray);
   }

   public static byte[] resolveByteArray4(byte[] byteValueArray, SignumComparable signumComparable3, SignumComparable signumComparable4) throws Exception {
      byte byteValue = 0;
      byte[] entry = null;
      int count4 = 0;
      Object entry2 = null;
      Object entry3 = null;
      Object entry4 = null;
      if (byteValueArray.length >= 2) {
         byteValue = byteValueArray[0];
         if (byteValue != 1) {
            throw new Exception("Hybrid encryption not supported for public key decryption");
         } else {
            entry = new byte[byteValueArray.length - 1];
            System.arraycopy(byteValueArray, 1, entry, 0, entry.length);
            count4 = (signumComparable3.getCount3() + 7) / 8;
            entry2 = resolveSignumComparable((byte[])entry);
            entry3 = ((SignumComparable)entry2).resolveSignumComparable2(signumComparable4, signumComparable3);
            entry4 = resolveByteArray((SignumComparable)entry3, count4);
            return SplineQueue.resolveByteArray((byte[])entry4);
         }
      } else {
         throw new Exception("Invalid encrypted data format");
      }
   }

   public static boolean matchesByteArray(byte[] byteValueArray, byte[] byteValueArray2, SignumComparable signumComparable3, SignumComparable signumComparable4) throws Exception {
      try {
         byte[] byteValueArray3 = resolveByteArrayByByteArray(byteValueArray, signumComparable3, signumComparable4);
         if (byteValueArray3.length != byteValueArray2.length) {
            return false;
         } else {
            for (int index = 0; index < byteValueArray3.length; index++) {
               if (byteValueArray3[index] != byteValueArray2[index]) {
                  return false;
               }
            }

            return true;
         }
      } catch (Exception exception) {
         return false;
      }
   }

   private static void applyByteArrayOutputStream(ByteArrayOutputStream byteArrayOutputStream, SignumComparable signumComparable3) throws IOException {
      byte[] entry = null;
      entry = signumComparable3.getByteArrayAsByteArray();
      byteArrayOutputStream.write(entry.length >>> 24);
      byteArrayOutputStream.write(entry.length >>> 16);
      byteArrayOutputStream.write(entry.length >>> 8);
      byteArrayOutputStream.write(entry.length);
      byteArrayOutputStream.write((byte[])entry);
   }

   public static SignumComparableController resolveSignumComparableController(String text) {
      try {
         ByteBuffer buffer = ByteBuffer.wrap(AutoRegisterEncryption.getDiamondToken2().resolveByteArray(text));
         SignumComparable signumComparable3 = resolveSignumComparable(buffer);
         SignumComparable signumComparable4 = resolveSignumComparable(buffer);
         SignumComparable signumComparable5 = resolveSignumComparable(buffer);
         SignumComparable signumComparable6 = resolveSignumComparable(buffer);
         SignumComparable signumComparable7 = resolveSignumComparable(buffer);
         return new SignumComparableController(signumComparable3, signumComparable4, signumComparable5, signumComparable6, signumComparable7);
      } catch (Exception exception) {
         throw new RuntimeException("Failed to import key pair: " + exception.getMessage());
      }
   }

   public static byte[] resolveByteArrayByByteArray(byte[] byteValueArray, SignumComparableController signumComparableController) throws Exception {
      return resolveByteArray3(byteValueArray, signumComparableController);
   }

   public static byte[] resolveByteArray3(byte[] byteValueArray, SignumComparable signumComparable3, SignumComparable signumComparable4) throws Exception {
      return resolveByteArray(byteValueArray, signumComparable3, signumComparable4);
   }

   public static byte[] resolveByteArray3(byte[] byteValueArray, SignumComparableController signumComparableController) throws Exception {
      short shortValue = 20018;
      short shortValue2 = -22149;
      char character = '\uc215';
      int count4 = (signumComparableController.getSignumComparable6().getCount3() + 7) / 8;
      if (byteValueArray.length > 245) {
         throw new Exception("Data to encrypt with private key too long");
      } else {
         try {
            SignumComparable signumComparable3 = resolveSignumComparable(SplineQueue.resolveByteArray(byteValueArray, count4));
            SignumComparable signumComparable4 = signumComparableController.getSignumComparable2();
            SignumComparable signumComparable5 = signumComparableController.getSignumComparable3();
            SignumComparable signumComparable6 = signumComparable3.resolveSignumComparable2(signumComparable5, signumComparable4);
            SignumComparable signumComparable7 = signumComparableController.getSignumComparable4();
            SignumComparable signumComparable8 = signumComparableController.getSignumComparable5();
            SignumComparable signumComparable9 = signumComparable3.resolveSignumComparable2(signumComparable8, signumComparable7);
            SignumComparable signumComparable10 = signumComparableController.getSignumComparable();
            SignumComparable signumComparable11 = signumComparable6.resolveSignumComparable11(signumComparable9);
            SignumComparable signumComparable12 = signumComparableController.getSignumComparable2();
            SignumComparable signumComparable13 = signumComparable11.resolveSignumComparable2(signumComparable12);
            signumComparable10 = signumComparable10.resolveSignumComparable4(signumComparable13);
            SignumComparable signumComparable14 = signumComparableController.getSignumComparable2();
            SignumComparable signumComparable15 = signumComparable10.resolveSignumComparable2(signumComparable14);
            SignumComparable signumComparable16 = signumComparableController.getSignumComparable4();
            SignumComparable signumComparable17 = signumComparable15.resolveSignumComparable4(signumComparable16);
            SignumComparable signumComparable18 = signumComparable9.resolveSignumComparable5(signumComparable17);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(1);
            byte[] byteValueArray2 = resolveByteArray(signumComparable18, count4);
            byteArrayOutputStream.write(byteValueArray2);
            return byteArrayOutputStream.toByteArray();
         } catch (Exception exception) {
            throw new Exception("Private key encryption failed: " + exception.getMessage());
         }
      }
   }

   public static String resolveTextBySignumComparable(SignumComparableController signumComparableController) {
      try {
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         SignumComparable signumComparable3 = signumComparableController.getSignumComparable6();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable3);
         SignumComparable signumComparable4 = signumComparableController.getSignumComparable7();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable4);
         SignumComparable signumComparable5 = signumComparableController.getSignumComparable8();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable5);
         SignumComparable signumComparable6 = signumComparableController.getSignumComparable2();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable6);
         SignumComparable signumComparable7 = signumComparableController.getSignumComparable4();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable7);
         byte[] byteValueArray = byteArrayOutputStream.toByteArray();
         byteArrayOutputStream.close();
         return AutoRegisterEncryption.getAutoRegisterCrypto().getText(byteValueArray);
      } catch (Exception exception) {
         throw new RuntimeException("Failed to export key pair: " + exception.getMessage());
      }
   }

   public static byte[] resolveByteArrayBySignumComparable(SignumComparableController signumComparableController) {
      try {
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         SignumComparable signumComparable3 = signumComparableController.getSignumComparable6();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable3);
         SignumComparable signumComparable4 = signumComparableController.getSignumComparable7();
         applyByteArrayOutputStream(byteArrayOutputStream, signumComparable4);
         byte[] byteValueArray = byteArrayOutputStream.toByteArray();
         byteArrayOutputStream.close();
         return byteValueArray;
      } catch (Exception exception) {
         throw new RuntimeException("Failed to export public key: " + exception.getMessage());
      }
   }

   public static byte[] resolveByteArrayByByteArray(byte[] byteValueArray, SignumComparable signumComparable3, SignumComparable signumComparable4) throws Exception {
      return resolveByteArray4(byteValueArray, signumComparable3, signumComparable4);
   }

   public static SignumComparable[] resolveSignumComparableArray(String text) {
      try {
         ByteBuffer buffer = ByteBuffer.wrap(AutoRegisterEncryption.getDiamondToken2().resolveByteArray(text));
         SignumComparable signumComparable3 = resolveSignumComparable(buffer);
         SignumComparable signumComparable4 = resolveSignumComparable(buffer);
         return new SignumComparable[]{signumComparable3, signumComparable4};
      } catch (Exception exception) {
         throw new RuntimeException("Failed to import public key: " + exception.getMessage());
      }
   }

   private static SignumComparable resolveSignumComparable(ByteBuffer buffer) {
      int count4 = 0;
      Object entry = null;
      count4 = (buffer.get() & 255) << 24 | (buffer.get() & 255) << 16 | (buffer.get() & 255) << 8 | buffer.get() & 255;
      entry = new byte[count4];
      buffer.get((byte[])entry);
      return new SignumComparable((byte[])entry);
   }

   private static byte[] resolveByteArray(SignumComparable signumComparable3, int count4) {
      byte[] entry = signumComparable3.getByteArrayAsByteArray();
      if (entry[0] == 0 && entry.length > count4) {
         byte[] entry2 = new byte[entry.length - 1];
         System.arraycopy(entry, 1, entry2, 0, entry2.length);
         entry = entry2;
      }

      if (entry.length < count4) {
         byte[] entry2 = new byte[count4];
         System.arraycopy(entry, 0, entry2, count4 - entry.length, entry.length);
         entry = entry2;
      }

      return entry;
   }
}
