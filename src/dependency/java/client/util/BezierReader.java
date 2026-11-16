package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class BezierReader {
   public static final String labelText = null;
   public static final String labelText2;
   public static final String labelText3;
   public static final String labelText4;
   public static final String labelText5;
   public static final String labelText6;
   public static final String labelText7;
   public static final String labelText8;
   public static final String labelText9;
   public static final String labelText10;
   public static final String labelText11;
   public static final String labelText12;
   public static final String labelText13;
   public static final String labelText14;
   public static final Map map = Maps.newHashMap();
   public static final Map map2 = Maps.newHashMap();
   public static final Map map3 = Maps.newHashMap();
   public static final String[] stringArray;

   public static int computeCount(Collection collection) {
      if (collection != null && !collection.isEmpty()) {
         float factor = 0.0F;
         float factor2 = 0.0F;
         float factor3 = 0.0F;
         float factor4 = 0.0F;

         for (PotionEffect potionEffect : (Iterable<PotionEffect>)(Iterable<?>)collection) {
            if (potionEffect.isState1()) {
               int indexOffset = Potion.potionArray[potionEffect.getIndexOffset3()].getIndexOffset1();
               if (BuildManager.isActive33()) {
                  indexOffset = LoadingManager.computeCount(potionEffect.getIndexOffset3(), indexOffset);
               }

               for (int index = 0; index <= potionEffect.getIndexOffset2(); index++) {
                  factor += (float)(indexOffset >> 16 & 0xFF) / 255.0F;
                  factor2 += (float)(indexOffset >> 8 & 0xFF) / 255.0F;
                  factor3 += (float)(indexOffset >> 0 & 0xFF) / 255.0F;
                  factor4++;
               }
            }
         }

         if (factor4 == 0.0F) {
            return 0;
         } else {
            float factor5 = factor / factor4 * 255.0F;
            float factor6 = factor2 / factor4 * 255.0F;
            float factor7 = factor3 / factor4 * 255.0F;
            return (int)factor5 << 16 | (int)factor6 << 8 | (int)factor7;
         }
      } else {
         return BuildManager.isActive33() ? LoadingManager.computeCount(0, 3694022) : 3694022;
      }
   }

   private static int computeCountByIndex(int count, int count2) {
      return checkIndex(count, count2) ? 0 : 1;
   }

   private static int computeCount(int count, int count2, boolean flag, boolean flag2, boolean flag3) {
      int count3 = count;
      if (flag3) {
         if (!checkIndex(count, count2)) {
            return 0;
         }
      } else if (flag) {
         count3 = count & ~(1 << count2);
      } else if (flag2) {
         if ((count & 1 << count2) == 0) {
            count3 = count | 1 << count2;
         } else {
            count3 = count & ~(1 << count2);
         }
      } else {
         count3 = count | 1 << count2;
      }

      return count3;
   }

   public static List resolveList(int count, boolean flag) {
      ArrayList list = null;

      for (Potion potion : Potion.potionArray) {
         if (potion != null && (!potion.isState() || flag)) {
            String text = (String)map.get(potion.getIndexOffset3());
            if (text != null) {
               int count2 = computeCount(text, 0, text.length(), count);
               if (count2 > 0) {
                  int count3 = 0;
                  String text2 = (String)map2.get(potion.getIndexOffset3());
                  if (text2 != null) {
                     count3 = computeCount(text2, 0, text2.length(), count);
                     if (count3 < 0) {
                        count3 = 0;
                     }
                  }

                  int count4;
                  if (potion.isActive()) {
                     count4 = 1;
                  } else {
                     count4 = (int)Math.round((double)(1200 * (count2 * 3 + (count2 - 1) * 2) >> count3) * potion.getCoordOffset());
                     if ((count & 16384) != 0) {
                        count4 = (int)Math.round((double)count4 * 0.75 + 0.5);
                     }
                  }

                  if (list == null) {
                     list = Lists.newArrayList();
                  }

                  PotionEffect potionEffect = new PotionEffect(potion.getIndexOffset3(), count4, count3);
                  if ((count & 16384) != 0) {
                     potionEffect.setState4(true);
                  }

                  list.add(potionEffect);
               }
            }
         }
      }

      return list;
   }

   public static String resolveText(int count) {
      int count2 = computeCountByIndex(count);
      return stringArray[count2];
   }

   private static int computeCount(int count, int count2) {
      return ((checkIndex(count, count2)) ? 1 : 0);
   }

   private static int computeCount(boolean flag, boolean flag2, boolean flag3, int count, int count2, int count3, int count4) {
      int count5 = 0;
      if (flag) {
         count5 = computeCountByIndex(count4, count2);
      } else if (count != -1) {
         if (count == 0 && computeCount(count4) == count2) {
            count5 = 1;
         } else if (count == 1 && computeCount(count4) > count2) {
            count5 = 1;
         } else if (count == 2 && computeCount(count4) < count2) {
            count5 = 1;
         }
      } else {
         count5 = computeCount(count4, count2);
      }

      if (flag2) {
         count5 *= count3;
      }

      if (flag3) {
         count5 *= -1;
      }

      return count5;
   }

   public static boolean matchesCollection(Collection collection) {
      Iterator iterator = collection.iterator();

      while (iterator.hasNext()) {
         if (!((PotionEffect)iterator.next()).isState5()) {
            return false;
         }
      }

      return true;
   }

   public static int computeCount(int count, boolean flag) {
      Integer count2 = IntegerArrayWrapper.resolveInteger(count);
      if (!flag) {
         if (map3.containsKey(count2)) {
            return (Integer)map3.get(count2);
         } else {
            int count3 = computeCount(resolveList(count2, false));
            map3.put(count2, count3);
            return count3;
         }
      } else {
         return computeCount(resolveList(count2, true));
      }
   }

   private static int computeCount(int count) {
      int count2 = count;

      int count3;
      for (count3 = 0; count2 > 0; count3++) {
         count2 &= count2 - 1;
      }

      return count3;
   }

   public static int computeCount(int count, int count2, int count3, int count4, int count5, int count6) {
      return (checkIndex(count, count2) ? 16 : 0)
         | (checkIndex(count, count3) ? 8 : 0)
         | (checkIndex(count, count4) ? 4 : 0)
         | (checkIndex(count, count5) ? 2 : 0)
         | (checkIndex(count, count6) ? 1 : 0);
   }

   public static boolean checkIndex(int count, int count2) {
      return (count & 1 << count2) != 0;
   }

   public static int computeCount(int count, String text) {
      int count2 = count;
      int length = text.length();
      boolean flag = false;
      boolean flag2 = false;
      boolean flag3 = false;
      boolean flag4 = false;
      int count3 = 0;

      for (int index = 0; index < length; index++) {
         char character = text.charAt(index);
         if (character >= '0' && character <= '9') {
            count3 = count3 * 10 + (character - '0');
            flag = true;
         } else if (character == '!') {
            if (flag) {
               count2 = computeCount(count2, count3, flag3, flag2, flag4);
               flag4 = false;
               flag3 = false;
               flag = false;
               count3 = 0;
            }

            flag2 = true;
         } else if (character == '-') {
            if (flag) {
               count2 = computeCount(count2, count3, flag3, flag2, flag4);
               flag4 = false;
               flag2 = false;
               flag = false;
               count3 = 0;
            }

            flag3 = true;
         } else if (character == '+') {
            if (flag) {
               count2 = computeCount(count2, count3, flag3, flag2, flag4);
               flag4 = false;
               flag2 = false;
               flag3 = false;
               flag = false;
               count3 = 0;
            }
         } else if (character == '&') {
            if (flag) {
               count2 = computeCount(count2, count3, flag3, flag2, flag4);
               flag2 = false;
               flag3 = false;
               flag = false;
               count3 = 0;
            }

            flag4 = true;
         }
      }

      if (flag) {
         count2 = computeCount(count2, count3, flag3, flag2, flag4);
      }

      return count2 & 32767;
   }

   private static int computeCount(String text, int count, int count2, int count3) {
      if (count < text.length() && count2 >= 0 && count < count2) {
         int count4 = text.indexOf(124, count);
         if (count4 >= 0 && count4 < count2) {
            int count5 = computeCount(text, count, count4 - 1, count3);
            if (count5 > 0) {
               return count5;
            } else {
               int count6 = computeCount(text, count4 + 1, count2, count3);
               return count6 > 0 ? count6 : 0;
            }
         } else {
            int count7 = text.indexOf(38, count);
            if (count7 >= 0 && count7 < count2) {
               int count8 = computeCount(text, count, count7 - 1, count3);
               if (count8 <= 0) {
                  return 0;
               } else {
                  int count9 = computeCount(text, count7 + 1, count2, count3);
                  return count9 <= 0 ? 0 : (count8 > count9 ? count8 : count9);
               }
            } else {
               boolean flag = false;
               boolean flag2 = false;
               boolean flag3 = false;
               boolean flag4 = false;
               boolean flag5 = false;
               byte byteValue = -1;
               int count10 = 0;
               int count11 = 0;
               int count12 = 0;

               for (int index = count; index < count2; index++) {
                  char character = text.charAt(index);
                  if (character >= '0' && character <= '9') {
                     if (flag) {
                        count11 = character - '0';
                        flag2 = true;
                     } else {
                        count10 = count10 * 10 + (character - '0');
                        flag3 = true;
                     }
                  } else if (character == '*') {
                     flag = true;
                  } else if (character == '!') {
                     if (flag3) {
                        count12 += computeCount(flag4, flag2, flag5, byteValue, count10, count11, count3);
                        flag5 = false;
                        flag = false;
                        flag2 = false;
                        flag3 = false;
                        count11 = 0;
                        count10 = 0;
                        byteValue = -1;
                     }

                     flag4 = true;
                  } else if (character == '-') {
                     if (flag3) {
                        count12 += computeCount(flag4, flag2, flag5, byteValue, count10, count11, count3);
                        flag4 = false;
                        flag = false;
                        flag2 = false;
                        flag3 = false;
                        count11 = 0;
                        count10 = 0;
                        byteValue = -1;
                     }

                     flag5 = true;
                  } else if (character != '=' && character != '<' && character != '>') {
                     if (character == '+' && flag3) {
                        count12 += computeCount(flag4, flag2, flag5, byteValue, count10, count11, count3);
                        flag4 = false;
                        flag5 = false;
                        flag = false;
                        flag2 = false;
                        flag3 = false;
                        count11 = 0;
                        count10 = 0;
                        byteValue = -1;
                     }
                  } else {
                     if (flag3) {
                        count12 += computeCount(flag4, flag2, flag5, byteValue, count10, count11, count3);
                        flag4 = false;
                        flag5 = false;
                        flag = false;
                        flag2 = false;
                        flag3 = false;
                        count11 = 0;
                        count10 = 0;
                        byteValue = -1;
                     }

                     if (character == '=') {
                        byteValue = 0;
                     } else if (character == '<') {
                        byteValue = 2;
                     } else if (character == '>') {
                        byteValue = 1;
                     }
                  }
               }

               if (flag3) {
                  count12 += computeCount(flag4, flag2, flag5, byteValue, count10, count11, count3);
               }

               return count12;
            }
         }
      } else {
         return 0;
      }
   }

   public static int computeCountByIndex(int count) {
      return computeCount(count, 5, 4, 3, 2, 1);
   }

   static {
      String[] textArray = new String[]{
         "potion.prefix.awkward",
         "potion.prefix.debonair",
         "+0-1-2-3&4-4+13",
         "potion.prefix.clear",
         "potion.prefix.stinky",
         "potion.prefix.cordial",
         "potion.prefix.odorless",
         "potion.prefix.artless",
         "+14&13-13",
         "potion.prefix.potent",
         "potion.prefix.gross",
         "!0 & !1 & 2 & !3 & 2+6",
         "+0-1+2-3&4-4+13",
         "potion.prefix.dashing",
         "potion.prefix.fancy",
         "potion.prefix.smooth",
         "-0-1+2-3&4-4+13",
         "-5+6-7",
         "potion.prefix.bungling",
         "potion.prefix.diffuse",
         "potion.prefix.thin",
         "potion.prefix.harsh",
         "potion.prefix.buttered",
         "!0 & 1 & !2 & 3 & 3+6",
         "!0 & 1 & !2 & !3 & 1+6",
         "-0+1-2-3&4-4+13",
         "+0-1-2+3&4-4+13",
         "+0+1-2+3&4-4+13",
         "potion.prefix.rank",
         "!0 & 1 & 2 & 3 & 2+6",
         "potion.prefix.bland",
         "0 & !1 & !2 & 3 & 3+6",
         "0 & !1 & 2 & !3",
         "0 & !1 & 2 & 3 & 2+6",
         "+0+1-2-3&4-4+13",
         "potion.prefix.thick",
         "!0 & !1 & !2 & 3 & 3+6",
         "potion.prefix.uninteresting",
         "0 & 1 & !2 & !3 & 0+6",
         "potion.prefix.elegant",
         "potion.prefix.foul",
         "!0 & 1 & 2 & !3 & 2+6",
         "potion.prefix.suave",
         "potion.prefix.charming",
         "potion.prefix.refined",
         "potion.prefix.sparkling",
         "potion.prefix.flat",
         "potion.prefix.acrid",
         "-0+1+2-3+13&4-4",
         "+0-1+2+3+13&4-4",
         "-0+3-4+13",
         "!0 & !1 & 2 & 3",
         "potion.prefix.mundane",
         "potion.prefix.milky",
         "0 & !1 & !2 & !3 & 0+6",
         "potion.prefix.bulky",
         "+5-6-7",
         "0 & 1 & !2 & 3 & 3+6"
      };
      labelText2 = textArray[25];
      labelText3 = textArray[2];
      labelText5 = textArray[50];
      labelText10 = textArray[56];
      labelText4 = textArray[16];
      labelText7 = textArray[26];
      labelText9 = textArray[17];
      labelText13 = textArray[49];
      labelText12 = textArray[48];
      labelText6 = textArray[12];
      labelText14 = textArray[27];
      labelText8 = textArray[34];
      labelText11 = textArray[8];
      stringArray = new String[]{
         textArray[52],
         textArray[37],
         textArray[30],
         textArray[3],
         textArray[53],
         textArray[19],
         textArray[7],
         textArray[20],
         textArray[0],
         textArray[46],
         textArray[55],
         textArray[18],
         textArray[22],
         textArray[15],
         textArray[42],
         textArray[1],
         textArray[35],
         textArray[39],
         textArray[14],
         textArray[43],
         textArray[13],
         textArray[44],
         textArray[5],
         textArray[45],
         textArray[9],
         textArray[40],
         textArray[6],
         textArray[28],
         textArray[21],
         textArray[47],
         textArray[10],
         textArray[4]
      };
      map.put(Potion.potion11.getIndexOffset3(), textArray[54]);
      map.put(Potion.potion2.getIndexOffset3(), textArray[24]);
      map.put(Potion.potion13.getIndexOffset3(), textArray[38]);
      map.put(Potion.potion21.getIndexOffset3(), textArray[32]);
      map.put(Potion.potion24.getIndexOffset3(), textArray[11]);
      map.put(Potion.potion26.getIndexOffset3(), textArray[36]);
      map.put(Potion.potion10.getIndexOffset3(), textArray[51]);
      map.put(Potion.potion3.getIndexOffset3(), textArray[23]);
      map.put(Potion.potion.getIndexOffset3(), textArray[31]);
      map.put(Potion.potion31.getIndexOffset3(), textArray[41]);
      map.put(Potion.potion30.getIndexOffset3(), textArray[29]);
      map.put(Potion.potion18.getIndexOffset3(), textArray[33]);
      map.put(Potion.potion9.getIndexOffset3(), textArray[57]);
      map2.put(Potion.potion2.getIndexOffset3(), "5");
      map2.put(Potion.potion28.getIndexOffset3(), "5");
      map2.put(Potion.potion.getIndexOffset3(), "5");
      map2.put(Potion.potion11.getIndexOffset3(), "5");
      map2.put(Potion.potion10.getIndexOffset3(), "5");
      map2.put(Potion.potion21.getIndexOffset3(), "5");
      map2.put(Potion.potion12.getIndexOffset3(), "5");
      map2.put(Potion.potion24.getIndexOffset3(), "5");
      map2.put(Potion.potion9.getIndexOffset3(), "5");
   }
}
