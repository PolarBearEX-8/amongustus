package cc.baritone.command;

import cc.baritone.utils.UnexpectedCharacterException;
import cc.baritone.utils.VALUELEFTRecord;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class UnkownInternalController {
   public static final int count = -1;
   public static final int count2 = 16384;
   public static final int count3 = 0;
   public static final int count4 = 2;
   public static final int[] countArray = new int[]{0, 0, 1, 1};
   public static final String labelText = "\t\u0000\u0001\u0007\u0001\u0007\u0002\u0000\u0001\u0007\u0012\u0000\u0001\u0007\u0001\u0000\u0001\t\b\u0000\u0001\u0006\u0001\u0019\u0001\u0002\u0001\u0004\u0001\n\n\u0003\u0001\u001a\u0006\u0000\u0004\u0001\u0001\u0005\u0001\u0001\u0014\u0000\u0001\u0017\u0001\b\u0001\u0018\u0003\u0000\u0001\u0012\u0001\u000b\u0002\u0001\u0001\u0011\u0001\f\u0005\u0000\u0001\u0013\u0001\u0000\u0001\r\u0003\u0000\u0001\u000e\u0001\u0014\u0001\u000f\u0001\u0010\u0005\u0000\u0001\u0015\u0001\u0000\u0001\u0016ﾂ\u0000";
   public static final char[] charCodeArray = resolveCharArray(
      "\t\u0000\u0001\u0007\u0001\u0007\u0002\u0000\u0001\u0007\u0012\u0000\u0001\u0007\u0001\u0000\u0001\t\b\u0000\u0001\u0006\u0001\u0019\u0001\u0002\u0001\u0004\u0001\n\n\u0003\u0001\u001a\u0006\u0000\u0004\u0001\u0001\u0005\u0001\u0001\u0014\u0000\u0001\u0017\u0001\b\u0001\u0018\u0003\u0000\u0001\u0012\u0001\u000b\u0002\u0001\u0001\u0011\u0001\f\u0005\u0000\u0001\u0013\u0001\u0000\u0001\r\u0003\u0000\u0001\u000e\u0001\u0014\u0001\u000f\u0001\u0010\u0005\u0000\u0001\u0015\u0001\u0000\u0001\u0016ﾂ\u0000"
   );
   public static final int[] countArray2 = getIndexArray();
   public static final String labelText2 = "\u0002\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0003\u0001\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0005\u0000\u0001\f\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0000\u0001\u0015\u0001\u0000\u0001\u0015\u0004\u0000\u0001\u0016\u0001\u0017\u0002\u0000\u0001\u0018";
   public static final int[] countArray3 = getIndexArray2();
   public static final String labelText3 = "\u0000\u0000\u0000\u001b\u00006\u0000Q\u0000l\u0000\u0087\u00006\u0000¢\u0000½\u0000Ø\u00006\u00006\u00006\u00006\u00006\u00006\u0000ó\u0000Ď\u00006\u0000ĩ\u0000ń\u0000ş\u0000ź\u0000ƕ\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u0000ư\u0000ǋ\u0000Ǧ\u0000Ǧ\u0000ȁ\u0000Ȝ\u0000ȷ\u0000ɒ\u00006\u00006\u0000ɭ\u0000ʈ\u00006";
   public static final int[] countArray4 = new int[]{
      2,
      2,
      3,
      4,
      2,
      2,
      2,
      5,
      2,
      6,
      2,
      2,
      7,
      8,
      2,
      9,
      2,
      2,
      2,
      2,
      2,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      17,
      18,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      4,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      4,
      19,
      20,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      20,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      5,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      21,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      22,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      23,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      -1,
      -1,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      24,
      25,
      26,
      27,
      28,
      29,
      30,
      31,
      32,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      33,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      34,
      35,
      -1,
      -1,
      34,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      36,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      37,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      38,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      39,
      -1,
      39,
      -1,
      39,
      -1,
      -1,
      -1,
      -1,
      -1,
      39,
      39,
      -1,
      -1,
      -1,
      -1,
      39,
      39,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      33,
      -1,
      20,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      20,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      35,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      38,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      40,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      41,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      42,
      -1,
      42,
      -1,
      42,
      -1,
      -1,
      -1,
      -1,
      -1,
      42,
      42,
      -1,
      -1,
      -1,
      -1,
      42,
      42,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      43,
      -1,
      43,
      -1,
      43,
      -1,
      -1,
      -1,
      -1,
      -1,
      43,
      43,
      -1,
      -1,
      -1,
      -1,
      43,
      43,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      44,
      -1,
      44,
      -1,
      44,
      -1,
      -1,
      -1,
      -1,
      -1,
      44,
      44,
      -1,
      -1,
      -1,
      -1,
      44,
      44,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1
   };
   public static final int count5 = 0;
   public static final int count6 = 1;
   public static final int count7 = 2;
   public static final String[] stringArray = new String[]{
      "Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"
   };
   public static final int[] countArray5 = getIndexArrayAsIndexArray();
   public static final String labelText4 = "\u0002\u0000\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0006\t\u0002\u0001\u0001\t\u0005\u0000\b\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0004\u0000\u0002\t\u0002\u0000\u0001\t";
   public Reader reader;
   public int count8;
   public int count9 = 0;
   public char[] charCodeArray2 = new char[16384];
   public int count10;
   public int count11;
   public int count12;
   public int count13;
   public int count14;
   public int count15;
   public int count16;
   public boolean enabled = true;
   public boolean enabled2;
   public StringBuffer stringBuffer = new StringBuffer();

   private void applyIndexByIndex(int count17) {
      String text;
      try {
         text = stringArray[count17];
      } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
         text = stringArray[0];
      }

      throw new Error(text);
   }

   public void applyIndex(int count17) {
      if (count17 > this.getCount()) {
         this.applyIndexByIndex(2);
      }

      this.count10 -= count17;
   }

   private static int[] getIndexArray() {
      int[] countArray6 = new int[45];
      computeCount(
         "\u0002\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0003\u0001\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0005\u0000\u0001\f\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0000\u0001\u0015\u0001\u0000\u0001\u0015\u0004\u0000\u0001\u0016\u0001\u0017\u0002\u0000\u0001\u0018",
         0,
         countArray6
      );
      return countArray6;
   }

   public final int getCount9() {
      return this.count9;
   }

   public UnkownInternalController(Reader reader2) {
      this.reader = reader2;
   }

   private boolean isActive() throws IOException {
      if (this.count12 > 0) {
         System.arraycopy(this.charCodeArray2, this.count12, this.charCodeArray2, 0, this.count13 - this.count12);
         this.count13 = this.count13 - this.count12;
         this.count11 = this.count11 - this.count12;
         this.count10 = this.count10 - this.count12;
         this.count12 = 0;
      }

      if (this.count11 >= this.charCodeArray2.length) {
         char[] characterArray = new char[this.count11 * 2];
         System.arraycopy(this.charCodeArray2, 0, characterArray, 0, this.charCodeArray2.length);
         this.charCodeArray2 = characterArray;
      }

      int count17 = this.reader.read(this.charCodeArray2, this.count13, this.charCodeArray2.length - this.count13);
      if (count17 > 0) {
         this.count13 += count17;
         return false;
      } else if (count17 == 0) {
         count17 = this.reader.read();
         if (count17 == -1) {
            return true;
         } else {
            this.charCodeArray2[this.count13++] = (char)count17;
            return false;
         }
      } else {
         return true;
      }
   }

   private static char[] resolveCharArray(String text) {
      char[] characterArray = new char[65536];
      int count17 = 0;
      int count18 = 0;

      while (count17 < 90) {
         int count19 = text.charAt(count17++);
         char character = text.charAt(count17++);

         do {
            characterArray[count18++] = character;
         } while (--count19 <= 0);
      }

      return characterArray;
   }

   public final char computeCharCode(int count17) {
      return this.charCodeArray2[this.count12 + count17];
   }

   private static int[] getIndexArrayAsIndexArray() {
      int[] countArray6 = new int[45];
      computeCount2(
         "\u0002\u0000\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0006\t\u0002\u0001\u0001\t\u0005\u0000\b\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0004\u0000\u0002\t\u0002\u0000\u0001\t",
         0,
         countArray6
      );
      return countArray6;
   }

   private static int[] getIndexArray2() {
      int[] countArray6 = new int[45];
      computeCountByText(
         "\u0000\u0000\u0000\u001b\u00006\u0000Q\u0000l\u0000\u0087\u00006\u0000¢\u0000½\u0000Ø\u00006\u00006\u00006\u00006\u00006\u00006\u0000ó\u0000Ď\u00006\u0000ĩ\u0000ń\u0000ş\u0000ź\u0000ƕ\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u0000ư\u0000ǋ\u0000Ǧ\u0000Ǧ\u0000ȁ\u0000Ȝ\u0000ȷ\u0000ɒ\u00006\u00006\u0000ɭ\u0000ʈ\u00006",
         0,
         countArray6
      );
      return countArray6;
   }

   private static int computeCount(String text, int count17, int[] countArray6) {
      int count18 = 0;
      int count19 = count17;
      int length = text.length();

      while (count18 < length) {
         int count20 = text.charAt(count18++);
         char character = text.charAt(count18++);

         do {
            countArray6[count19++] = character;
         } while (--count20 <= 0);
      }

      return count19;
   }

   public final int getCount() {
      return this.count10 - this.count12;
   }

   public final String getText() {
      return new String(this.charCodeArray2, this.count12, this.count10 - this.count12);
   }

   public final void applyReader(Reader reader2) {
      this.reader = reader2;
      this.enabled = true;
      this.enabled2 = false;
      this.count13 = this.count12 = 0;
      this.count11 = this.count10 = 0;
      this.count14 = this.count15 = this.count16 = 0;
      this.count9 = 0;
   }

   private static int computeCount2(String text, int count17, int[] countArray6) {
      int count18 = 0;
      int count19 = count17;
      int length = text.length();

      while (count18 < length) {
         int count20 = text.charAt(count18++);
         char character = text.charAt(count18++);

         do {
            countArray6[count19++] = character;
         } while (--count20 <= 0);
      }

      return count19;
   }

   public VALUELEFTRecord getVALUELEFTRecord() throws IOException, UnexpectedCharacterException {
      int count17 = this.count13;
      char[] charCodeArray3 = this.charCodeArray2;
      char[] characterArray = charCodeArray;
      int[] countArray6 = countArray4;
      int[] countArray7 = countArray3;
      int[] countArray8 = countArray5;

      while (true) {
         int count18 = this.count10;
         this.count15 = this.count15 + (count18 - this.count12);
         int count19 = -1;
         int count20 = this.count11 = this.count12 = count18;
         this.count8 = countArray[this.count9];

         int count21;
         while (true) {
            if (count20 < count17) {
               count21 = charCodeArray3[count20++];
            } else {
               if (this.enabled2) {
                  count21 = -1;
                  break;
               }

               this.count11 = count20;
               this.count10 = count18;
               boolean active = this.isActive();
               count20 = this.count11;
               count18 = this.count10;
               charCodeArray3 = this.charCodeArray2;
               count17 = this.count13;
               if (active) {
                  count21 = -1;
                  break;
               }

               count21 = charCodeArray3[count20++];
            }

            int count22 = countArray6[countArray7[this.count8] + characterArray[count21]];
            if (count22 == -1) {
               break;
            }

            this.count8 = count22;
            count22 = countArray8[this.count8];
            if ((count22 & 1) == 1) {
               count19 = this.count8;
               count18 = count20;
               if ((count22 & 8) == 8) {
                  break;
               }
            }
         }

         this.count10 = count18;
         switch (count19 < 0 ? count19 : countArray2[count19]) {
            case 1:
               throw new UnexpectedCharacterException(this.count15, 0, new Character(this.computeCharCode(0)));
            case 2:
               Long value = Long.valueOf(this.getText());
               return new VALUELEFTRecord(0, value);
            case 3:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
               break;
            case 4:
               this.stringBuffer = null;
               this.stringBuffer = new StringBuffer();
               this.setCount9(2);
               break;
            case 5:
               return new VALUELEFTRecord(1, null);
            case 6:
               return new VALUELEFTRecord(2, null);
            case 7:
               return new VALUELEFTRecord(3, null);
            case 8:
               return new VALUELEFTRecord(4, null);
            case 9:
               return new VALUELEFTRecord(5, null);
            case 10:
               return new VALUELEFTRecord(6, null);
            case 11:
               this.stringBuffer.append(this.getText());
               break;
            case 12:
               this.stringBuffer.append('\\');
               break;
            case 13:
               this.setCount9(0);
               return new VALUELEFTRecord(0, this.stringBuffer.toString());
            case 14:
               this.stringBuffer.append('"');
               break;
            case 15:
               this.stringBuffer.append('/');
               break;
            case 16:
               this.stringBuffer.append('\b');
               break;
            case 17:
               this.stringBuffer.append('\f');
               break;
            case 18:
               this.stringBuffer.append('\n');
               break;
            case 19:
               this.stringBuffer.append('\r');
               break;
            case 20:
               this.stringBuffer.append('\t');
               break;
            case 21:
               Double offset = Double.valueOf(this.getText());
               return new VALUELEFTRecord(0, offset);
            case 22:
               return new VALUELEFTRecord(0, null);
            case 23:
               Boolean flag = Boolean.valueOf(this.getText());
               return new VALUELEFTRecord(0, flag);
            case 24:
               try {
                  int count23 = Integer.parseInt(this.getText().substring(2), 16);
                  this.stringBuffer.append((char)count23);
                  break;
               } catch (Exception exception) {
                  throw new UnexpectedCharacterException(this.count15, 2, exception);
               }
            default:
               if (count21 == -1 && this.count12 == this.count11) {
                  this.enabled2 = true;
                  return null;
               }

               this.applyIndexByIndex(1);
         }
      }
   }

   public final void setCount9(int count17) {
      this.count9 = count17;
   }

   public int getCount15() {
      return this.count15;
   }

   private static int computeCountByText(String text, int count17, int[] countArray6) {
      int count18 = 0;
      int count19 = count17;
      int length = text.length();

      while (count18 < length) {
         int count20 = text.charAt(count18++) << 16;
         countArray6[count19++] = count20 | text.charAt(count18++);
      }

      return count19;
   }

   public final void close() throws IOException {
      this.enabled2 = true;
      this.count13 = this.count12;
      if (this.reader != null) {
         this.reader.close();
      }
   }

   public UnkownInternalController(InputStream inputStream) {
      this(new InputStreamReader(inputStream));
   }
}
