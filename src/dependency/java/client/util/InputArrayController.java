package client.util;

import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class InputArrayController {
   public final boolean enabled;
   public static final InputArrayController inputArrayController3;
   public static final InputArrayController inputArrayController;
   public static final InputArrayController inputArrayController2;
   public static final int[] countArray;
   public final boolean enabled2;
   public static final int[] countArray2;

   private int computeCount(byte[] byteValueArray, int count, int count2, byte[] byteValueArray2) {
      int count3 = count;
      AuxiliaryCharArrayEntry.getStringArray();
      int[] countArray3 = this.enabled2 ? countArray : countArray2;
      int count4 = 0;
      int count5 = 0;
      int byteValue = 18;

      while (count3 < count2) {
         int count6 = byteValueArray[count3++] & 255;
         int count7;
         if ((count7 = countArray3[count6]) >= 0) {
            count5 |= count7 << byteValue;
            byteValue -= 6;
            if (byteValue < 0) {
               byteValueArray2[count4++] = (byte)(count5 >> 16);
               byteValueArray2[count4++] = (byte)(count5 >> 8);
               byteValueArray2[count4++] = (byte)count5;
               byteValue = 18;
               count5 = 0;
            }
         } else {
            if (count7 == -2) {
               if (byteValue == 6 && (count3 == count2 || byteValueArray[count3++] != 61) || byteValue == 18) {
                  throw new IllegalArgumentException("Input byte array has wrong 4-byte ending unit");
               }
               break;
            }

            if (!this.enabled) {
               throw new IllegalArgumentException("Illegal base64 character " + Integer.toString(byteValueArray[count3 - 1], 16));
            }
         }
      }

      if (byteValue == 6) {
         byteValueArray2[count4++] = (byte)(count5 >> 16);
      } else if (byteValue == 0) {
         byteValueArray2[count4++] = (byte)(count5 >> 16);
         byteValueArray2[count4++] = (byte)(count5 >> 8);
      } else if (byteValue == 12) {
         throw new IllegalArgumentException("Last unit does not have enough valid bits");
      }

      while (count3 < count2) {
         if (!this.enabled || countArray3[byteValueArray[count3++]] >= 0) {
            throw new IllegalArgumentException("Input byte array has incorrect ending byte at " + count3);
         }
      }

      return count4;
   }

   public byte[] resolveByteArray(byte[] byteValueArray) {
      byte[] entry = null;
      AuxiliaryCharArrayEntry.getStringArray();
      int count = 0;
      int length = byteValueArray.length;
      int byteValue = 0;
      entry = new byte[this.computeCount(byteValueArray, byteValue, length)];
      int length2 = byteValueArray.length;
      byte byteValue2 = 0;
      count = this.computeCount(byteValueArray, byteValue2, length2, (byte[])entry);
      if (count != entry.length) {
         entry = Arrays.copyOf((byte[])entry, count);
      }

      return (byte[])entry;
   }

   static {
      countArray2 = new int[256];
      Arrays.fill(countArray2, -1);
      for (int index = 0; index < OutputArrayController.getCharCodeArray2().length; index++) {
         countArray2[OutputArrayController.getCharCodeArray2()[index]] = index;
      }
      countArray2['='] = -2;

      countArray = new int[256];
      Arrays.fill(countArray, -1);
      for (int index = 0; index < OutputArrayController.getCharCodeArray().length; index++) {
         countArray[OutputArrayController.getCharCodeArray()[index]] = index;
      }
      countArray['='] = -2;

      inputArrayController3 = new InputArrayController(false, false);
      inputArrayController = new InputArrayController(true, false);
      inputArrayController2 = new InputArrayController(false, true);
   }

   public static int[] getCountArray2() {
      return countArray2;
   }

   private InputArrayController(boolean flag, boolean flag2) {
      this.enabled2 = flag;
      this.enabled = flag2;
   }

   private int computeCount(byte[] byteValueArray, int count, int count2) {
      int count3 = count;
      AuxiliaryCharArrayEntry.getStringArray();
      int[] countArray3 = this.enabled2 ? countArray : countArray2;
      int count4 = 0;
      int count5 = count2 - count;
      if (count5 == 0) {
         return 0;
      } else if (count5 < 2) {
         if (this.enabled && countArray3[0] == -1) {
            return 0;
         } else {
            throw new IllegalArgumentException("Input byte[] should at least have 2 bytes for base64 bytes");
         }
      } else {
         if (this.enabled) {
            int count6 = 0;

            while (count3 < count2) {
               int count7 = byteValueArray[count3++] & 255;
               if (count7 == 61) {
                  count5 -= count2 - count3 + 1;
                  break;
               }

               if (countArray3[count7] == -1) {
                  count6++;
               }
            }

            count5 -= count6;
         } else if (byteValueArray[count2 - 1] == 61) {
            count4++;
            if (byteValueArray[count2 - 2] == 61) {
               count4++;
            }
         }

         if (count4 == 0 && (count5 & 3) != 0) {
            count4 = 4 - (count5 & 3);
         }

         return 3 * ((count5 + 3) / 4) - count4;
      }
   }

   public ByteBuffer resolveByteBuffer(ByteBuffer buffer) {
      AuxiliaryCharArrayEntry.getStringArray();
      int count = buffer.position();

      try {
         byte[] byteValueArray;
         int count2;
         int count3;
         if (buffer.hasArray()) {
            byteValueArray = buffer.array();
            count2 = buffer.arrayOffset() + buffer.position();
            count3 = buffer.arrayOffset() + buffer.limit();
            ((Buffer)buffer).position(buffer.limit());
         } else {
            byteValueArray = new byte[buffer.remaining()];
            buffer.get(byteValueArray);
            count2 = 0;
            count3 = byteValueArray.length;
         }

         byte[] byteValueArray2 = new byte[this.computeCount(byteValueArray, count2, count3)];
         return ByteBuffer.wrap(byteValueArray2, 0, this.computeCount(byteValueArray, count2, count3, byteValueArray2));
      } catch (IllegalArgumentException illegalArgumentException) {
         ((Buffer)buffer).position(count);
         throw illegalArgumentException;
      }
   }

   public byte[] resolveByteArray(String text) {
      byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);
      byte[] byteValueArray = bytes;
      return this.resolveByteArray(byteValueArray);
   }

   public int computeCount(byte[] byteValueArray, byte[] byteValueArray2) {
      AuxiliaryCharArrayEntry.getStringArray();
      int count = 0;
      int length = byteValueArray.length;
      int byteValue = 0;
      count = this.computeCount(byteValueArray, byteValue, length);
      if (byteValueArray2.length >= count) {
         int length2 = byteValueArray.length;
         byte byteValue2 = 0;
         return this.computeCount(byteValueArray, byteValue2, length2, byteValueArray2);
      } else {
         throw new IllegalArgumentException("Output byte array is too small for decoding all input bytes");
      }
   }

   public InputStream resolveInputStream(InputStream inputStream, long value, short shortValue) {
      long keySalt = (value << 16 | (long)shortValue << 48 >>> 48) ^ 0x6939fc8de612L ^ 0x67f5f621df22L;
      Objects.requireNonNull(inputStream);
      int[] countArray3 = this.enabled2 ? countArray : countArray2;
      boolean enabled3 = this.enabled;
      int[] countArray4 = countArray3;
      return new StreamClosedInputStream(keySalt, inputStream, countArray4, enabled3);
   }
}
