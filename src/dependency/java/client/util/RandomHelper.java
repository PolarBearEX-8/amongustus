package client.util;

import java.util.Random;

public final class RandomHelper {
   public static final Random random = new Random();

   public static String getText() {
      String text = "";
      int count = (int)Math.round(Math.random() * 4.0) + 5;
      String text2 = "aeiouy";
      String text3 = "bcdfghklmnprstvwz";
      int count2 = 0;
      int count3 = 0;
      String text4 = "blah";

      for (int index = 0; index < count; index++) {
         String text5 = text4;
         if ((random.nextBoolean() || count2 == 1) && count3 < 2) {
            while (text5.equals(text4)) {
               int count4 = (int)(Math.random() * (double)text2.length() - 1.0);
               text5 = text2.substring(count4, count4 + 1);
            }

            count2 = 0;
            count3++;
         } else {
            while (text5.equals(text4)) {
               int count5 = (int)(Math.random() * (double)text3.length() - 1.0);
               text5 = text3.substring(count5, count5 + 1);
            }

            count2++;
            count3 = 0;
         }

         text4 = text5;
         text = text.concat(text5);
      }

      int count6 = (int)Math.round(Math.random() * 2.0);
      if (count6 == 1) {
         text = text.substring(0, 1).toUpperCase() + text.substring(1);
      } else if (count6 == 2) {
         for (int index2 = 0; index2 < count; index2++) {
            if ((int)Math.round(Math.random() * 3.0) == 1) {
               text = text.substring(0, index2) + text.substring(index2, index2 + 1).toUpperCase() + (index2 == count ? "" : text.substring(index2 + 1));
            }
         }
      }

      int count7 = (int)Math.round(Math.random() * 3.0) + 1;
      int count8 = (int)Math.round(Math.random() * 3.0);
      boolean flag = random.nextBoolean();
      if (flag) {
         if (count7 == 1) {
            int count9 = (int)Math.round(Math.random() * 9.0);
            text = text.concat(Integer.toString(count9));
         } else if (count8 == 0) {
            int count10 = (int)(Math.round(Math.random() * 8.0) + 1L);

            for (int index3 = 0; index3 < count7; index3++) {
               text = text.concat(Integer.toString(count10));
            }
         } else if (count8 == 1) {
            int count11 = (int)(Math.round(Math.random() * 8.0) + 1L);
            text = text.concat(Integer.toString(count11));

            for (int index4 = 1; index4 < count7; index4++) {
               text = text.concat("0");
            }
         } else if (count8 == 2) {
            int count12 = (int)(Math.round(Math.random() * 8.0) + 1L);
            text = text.concat(Integer.toString(count12));

            for (int index5 = 0; index5 < count7; index5++) {
               int count13 = (int)Math.round(Math.random() * 9.0);
               text = text.concat(Integer.toString(count13));
            }
         } else if (count8 == 3) {
            int count14 = 99999;

            while (Integer.toString(count14).length() != count7) {
               int count15 = (int)(Math.round(Math.random() * 12.0) + 1L);
               count14 = (int)Math.pow(2.0, (double)count15);
            }

            text = text.concat(Integer.toString(count14));
         }
      }

      if (!flag && random.nextBoolean()) {
         String text6 = text;

         while (text.equals(text6)) {
            int count16 = (int)Math.round(Math.random() * 7.0);
            if (count16 == 0) {
               text = text.replace("a", "4").replace("A", "4");
            }

            if (count16 == 1) {
               text = text.replace("e", "3").replace("E", "3");
            }

            if (count16 == 2) {
               text = text.replace("g", "6").replace("G", "6");
            }

            if (count16 == 3) {
               text = text.replace("h", "4").replace("H", "4");
            }

            if (count16 == 4) {
               text = text.replace("i", "1").replace("I", "1");
            }

            if (count16 == 5) {
               text = text.replace("o", "0").replace("O", "0");
            }

            if (count16 == 6) {
               text = text.replace("s", "5").replace("S", "5");
            }

            if (count16 == 7) {
               text = text.replace("l", "7").replace("L", "7");
            }
         }
      }

      switch ((int)Math.round(Math.random() * 8.0)) {
         case 3:
            text = "xX".concat(text).concat("Xx");
            break;
         case 4:
            text = text.concat("LP");
            break;
         case 5:
            text = text.concat("HD");
      }

      return text;
   }
}
