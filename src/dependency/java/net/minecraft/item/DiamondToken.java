package net.minecraft.item;

import client.util.AutoRegisterCrypto;
import client.util.TJYDmLyRyQInputStream;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class DiamondToken {
   public static final DiamondToken diamondToken2;
   public static final DiamondToken diamondToken3;
   public static final DiamondToken diamondToken;
   public final boolean enabled;
   public static final int[] countArray2;
   public final boolean enabled2;
   public static final int[] countArray;

   public ByteBuffer resolveByteBuffer(ByteBuffer buffer) {
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

   public byte[] resolveByteArray(byte[] byteValueArray) {
      byte[] entry = null;
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

   public byte[] resolveByteArray(String text) {
      byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);
      return this.resolveByteArray(bytes);
   }

   public static int[] getCountArray2() {
      return countArray2;
   }

   private int computeCount(byte[] byteValueArray, int count, int count2) {
      int count3 = count;
      int[] countArray3 = this.enabled ? countArray : countArray2;
      int count4 = 0;
      int count5 = count2 - count;
      if (count5 == 0) {
         return 0;
      } else if (count5 < 2) {
         if (this.enabled2 && countArray3[0] == -1) {
            return 0;
         } else {
            throw new IllegalArgumentException("Input byte[] should at least have 2 bytes for base64 bytes");
         }
      } else {
         if (this.enabled2) {
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

   public InputStream resolveInputStream(long value, InputStream inputStream) {
      long keySalt = 0x414ceb805ad9L ^ value;
      long keySalt2 = keySalt ^ 0x7a8e1ac296d1L;
      Objects.requireNonNull(inputStream);
      return new TJYDmLyRyQInputStream(inputStream, this.enabled ? countArray : countArray2, keySalt2, this.enabled2);
   }

   public int computeCount(byte[] byteValueArray, byte[] byteValueArray2) {
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

   private int computeCount(byte[] byteValueArray, int count, int count2, byte[] byteValueArray2) {
      int count3 = count;
      int[] countArray3 = this.enabled ? countArray : countArray2;
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

            if (!this.enabled2) {
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
         if (!this.enabled2 || countArray3[byteValueArray[count3++]] >= 0) {
            throw new IllegalArgumentException("Input byte array has incorrect ending byte at " + count3);
         }
      }

      return count4;
   }

   private DiamondToken(boolean flag, boolean flag2) {
      this.enabled = flag;
      this.enabled2 = flag2;
   }

   static {
      countArray2 = new int[256];
      Arrays.fill(countArray2, -1);
      char[] base64Chars = AutoRegisterCrypto.getCharCodeArray();
      for (int index = 0; index < base64Chars.length; index++) {
         countArray2[base64Chars[index]] = index;
      }
      countArray2['='] = -2;

      countArray = new int[256];
      Arrays.fill(countArray, -1);
      char[] base64UrlChars = AutoRegisterCrypto.getCharCodeArray2();
      for (int index = 0; index < base64UrlChars.length; index++) {
         countArray[base64UrlChars[index]] = index;
      }
      countArray['='] = -2;

      diamondToken2 = new DiamondToken(false, false);
      diamondToken3 = new DiamondToken(true, false);
      diamondToken = new DiamondToken(false, true);
   }
}
