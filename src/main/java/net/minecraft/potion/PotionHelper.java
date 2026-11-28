package net.minecraft.potion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.minecraft.util.IntegerCache;
import net.optifine.CustomColors;
import net.optifine.config.Config;

public class PotionHelper {
   public static final String unusedString = null;
   public static final String sugarEffect = null;
   public static final String ghastTearEffect = null;
   public static final String spiderEyeEffect = null;
   public static final String fermentedSpiderEyeEffect = null;
   public static final String speckledMelonEffect = null;
   public static final String blazePowderEffect = null;
   public static final String magmaCreamEffect = null;
   public static final String redstoneEffect = null;
   public static final String glowstoneEffect = null;
   public static final String gunpowderEffect = null;
   public static final String goldenCarrotEffect = null;
   public static final String pufferfishEffect = null;
   public static final String rabbitFootEffect = null;
   private static final Map potionRequirements = Maps.newHashMap();
   private static final Map potionAmplifiers = Maps.newHashMap();
   private static final Map DATAVALUE_COLORS = Maps.newHashMap();
   private static final String[] potionPrefixes = new String[]{
      "potion.prefix.mundane",
      "potion.prefix.uninteresting",
      "potion.prefix.bland",
      "potion.prefix.clear",
      "potion.prefix.milky",
      "potion.prefix.diffuse",
      "potion.prefix.artless",
      "potion.prefix.thin",
      "potion.prefix.awkward",
      "potion.prefix.flat",
      "potion.prefix.bulky",
      "potion.prefix.bungling",
      "potion.prefix.buttered",
      "potion.prefix.smooth",
      "potion.prefix.suave",
      "potion.prefix.debonair",
      "potion.prefix.thick",
      "potion.prefix.elegant",
      "potion.prefix.fancy",
      "potion.prefix.charming",
      "potion.prefix.dashing",
      "potion.prefix.refined",
      "potion.prefix.cordial",
      "potion.prefix.sparkling",
      "potion.prefix.potent",
      "potion.prefix.foul",
      "potion.prefix.odorless",
      "potion.prefix.rank",
      "potion.prefix.harsh",
      "potion.prefix.acrid",
      "potion.prefix.gross",
      "potion.prefix.stinky"
   };

   public static int getPotionPrefixIndex(int n) {
      return getPotionPrefixIndexFlags(n, 5, 4, 3, 2, 1);
   }

   private static int isFlagUnset(int n, int n2) {
      return checkFlag(n, n2) ? 0 : 1;
   }

   static {
      potionRequirements.put(Potion.regeneration.getId(), "0 & !1 & !2 & !3 & 0+6");
      potionRequirements.put(Potion.moveSpeed.getId(), "!0 & 1 & !2 & !3 & 1+6");
      potionRequirements.put(Potion.fireResistance.getId(), "0 & 1 & !2 & !3 & 0+6");
      potionRequirements.put(Potion.heal.getId(), "0 & !1 & 2 & !3");
      potionRequirements.put(Potion.poison.getId(), "!0 & !1 & 2 & !3 & 2+6");
      potionRequirements.put(Potion.weakness.getId(), "!0 & !1 & !2 & 3 & 3+6");
      potionRequirements.put(Potion.harm.getId(), "!0 & !1 & 2 & 3");
      potionRequirements.put(Potion.moveSlowdown.getId(), "!0 & 1 & !2 & 3 & 3+6");
      potionRequirements.put(Potion.damageBoost.getId(), "0 & !1 & !2 & 3 & 3+6");
      potionRequirements.put(Potion.nightVision.getId(), "!0 & 1 & 2 & !3 & 2+6");
      potionRequirements.put(Potion.invisibility.getId(), "!0 & 1 & 2 & 3 & 2+6");
      potionRequirements.put(Potion.waterBreathing.getId(), "0 & !1 & 2 & 3 & 2+6");
      potionRequirements.put(Potion.jump.getId(), "0 & 1 & !2 & 3 & 3+6");
      potionAmplifiers.put(Potion.moveSpeed.getId(), "5");
      potionAmplifiers.put(Potion.digSpeed.getId(), "5");
      potionAmplifiers.put(Potion.damageBoost.getId(), "5");
      potionAmplifiers.put(Potion.regeneration.getId(), "5");
      potionAmplifiers.put(Potion.harm.getId(), "5");
      potionAmplifiers.put(Potion.heal.getId(), "5");
      potionAmplifiers.put(Potion.resistance.getId(), "5");
      potionAmplifiers.put(Potion.poison.getId(), "5");
      potionAmplifiers.put(Potion.jump.getId(), "5");
   }

   private static int isFlagSet(int n, int n2) {
      return checkFlag(n, n2) ? 1 : 0;
   }

   private static int parsePotionEffectsTerm(boolean flag, boolean flag2, boolean flag3, int n, int n2, int n3, int n4) {
      int n5 = 0;
      if (flag) {
         n5 = isFlagUnset(n4, n2);
      } else if (n != -1) {
         if (n == 0 && countSetFlags(n4) == n2) {
            n5 = 1;
         } else if (n == 1 && countSetFlags(n4) > n2) {
            n5 = 1;
         } else if (n == 2 && countSetFlags(n4) < n2) {
            n5 = 1;
         }
      } else {
         n5 = isFlagSet(n4, n2);
      }

      if (flag2) {
         n5 *= n3;
      }

      if (flag3) {
         n5 *= -1;
      }

      return n5;
   }

   public static int calcPotionLiquidColor(Collection items) {
      int n = 3694022;
      if (items != null && !items.isEmpty()) {
         float f = 0.0F;
         float f2 = 0.0F;
         float f3 = 0.0F;
         float f4 = 0.0F;

         for (PotionEffect potionEffect : (Iterable<PotionEffect>)(items)) {
            if (potionEffect.getIsShowParticles()) {
               int liquidColor = Potion.potionTypes[potionEffect.getPotionID()].getLiquidColor();
               if (Config.isCustomColors()) {
                  liquidColor = CustomColors.getPotionColor(potionEffect.getPotionID(), liquidColor);
               }

               for (int i = 0; i <= potionEffect.getAmplifier(); i++) {
                  f += (float)(liquidColor >> 16 & 0xFF) / 255.0F;
                  f2 += (float)(liquidColor >> 8 & 0xFF) / 255.0F;
                  f3 += (float)(liquidColor >> 0 & 0xFF) / 255.0F;
                  f4++;
               }
            }
         }

         if (f4 == 0.0F) {
            return 0;
         } else {
            f = f / f4 * 255.0F;
            f2 = f2 / f4 * 255.0F;
            f3 = f3 / f4 * 255.0F;
            return (int)f << 16 | (int)f2 << 8 | (int)f3;
         }
      } else {
         return Config.isCustomColors() ? CustomColors.getPotionColor(0, n) : n;
      }
   }

   public static int getLiquidColor(int n, boolean flag) {
      Integer integer = IntegerCache.getInteger(n);
      if (!flag) {
         if (DATAVALUE_COLORS.containsKey(integer)) {
            return (Integer)DATAVALUE_COLORS.get(integer);
         } else {
            int n2 = calcPotionLiquidColor(getPotionEffects(integer, false));
            DATAVALUE_COLORS.put(integer, n2);
            return n2;
         }
      } else {
         return calcPotionLiquidColor(getPotionEffects(integer, true));
      }
   }

   public static boolean getAreAmbient(Collection items) {
      for (PotionEffect potionEffect : (Iterable<PotionEffect>)(items)) {
         if (!potionEffect.getIsAmbient()) {
            return false;
         }
      }

      return true;
   }

   public static boolean checkFlag(int n, int n2) {
      return (n & 1 << n2) != 0;
   }

   public static int applyIngredient(int n, String str) {
      int length = str.length();
      boolean flag = false;
      boolean flag2 = false;
      boolean flag3 = false;
      boolean flag4 = false;
      int n2 = 0;

      for (int i = (byte)0; i < length; i++) {
         char ch = str.charAt(i);
         if (ch >= '0' && ch <= '9') {
            n2 *= 10;
            n2 += ch - '0';
            flag = true;
         } else if (ch == '!') {
            if (flag) {
               n = brewBitOperations(n, n2, flag3, flag2, flag4);
               flag4 = false;
               flag2 = false;
               flag3 = false;
               flag = false;
               n2 = 0;
            }

            flag2 = true;
         } else if (ch == '-') {
            if (flag) {
               n = brewBitOperations(n, n2, flag3, flag2, flag4);
               flag4 = false;
               flag2 = false;
               flag3 = false;
               flag = false;
               n2 = 0;
            }

            flag3 = true;
         } else if (ch == '+') {
            if (flag) {
               n = brewBitOperations(n, n2, flag3, flag2, flag4);
               flag4 = false;
               flag2 = false;
               flag3 = false;
               flag = false;
               n2 = 0;
            }
         } else if (ch == '&') {
            if (flag) {
               n = brewBitOperations(n, n2, flag3, flag2, flag4);
               flag4 = false;
               flag2 = false;
               flag3 = false;
               flag = false;
               n2 = 0;
            }

            flag4 = true;
         }
      }

      if (flag) {
         n = brewBitOperations(n, n2, flag3, flag2, flag4);
      }

      return n & 32767;
   }

   public static List getPotionEffects(int n, boolean flag) {
      ArrayList list = null;

      for (Potion potion : Potion.potionTypes) {
         if (potion != null && (!potion.isUsable() || flag)) {
            String str = (String)potionRequirements.get(potion.getId());
            if (str != null) {
               int n2 = parsePotionEffects(str, 0, str.length(), n);
               if (n2 > 0) {
                  int n3 = 0;
                  String str2 = (String)potionAmplifiers.get(potion.getId());
                  if (str2 != null) {
                     n3 = parsePotionEffects(str2, 0, str2.length(), n);
                     if (n3 < 0) {
                        n3 = 0;
                     }
                  }

                  if (potion.isInstant()) {
                     n2 = 1;
                  } else {
                     n2 = 1200 * (n2 * 3 + (n2 - 1) * 2);
                     n2 >>= n3;
                     n2 = (int)Math.round((double)n2 * potion.getEffectiveness());
                     if ((n & 16384) != 0) {
                        n2 = (int)Math.round((double)n2 * 0.75 + 0.5);
                     }
                  }

                  if (list == null) {
                     list = Lists.newArrayList();
                  }

                  PotionEffect potionEffect = new PotionEffect(potion.getId(), n2, n3);
                  if ((n & 16384) != 0) {
                     potionEffect.setSplashPotion(true);
                  }

                  list.add(potionEffect);
               }
            }
         }
      }

      return list;
   }

   private static int parsePotionEffects(String str, int n, int n2, int n3) {
      if (n < str.length() && n2 >= 0 && n < n2) {
         int n4 = str.indexOf(124, n);
         if (n4 >= 0 && n4 < n2) {
            int n5 = parsePotionEffects(str, n, n4 - 1, n3);
            if (n5 > 0) {
               return n5;
            } else {
               int n6 = parsePotionEffects(str, n4 + 1, n2, n3);
               return n6 > 0 ? n6 : 0;
            }
         } else {
            int n7 = str.indexOf(38, n);
            if (n7 >= 0 && n7 < n2) {
               int n8 = parsePotionEffects(str, n, n7 - 1, n3);
               if (n8 <= 0) {
                  return 0;
               } else {
                  int n9 = parsePotionEffects(str, n7 + 1, n2, n3);
                  return n9 <= 0 ? 0 : (n8 > n9 ? n8 : n9);
               }
            } else {
               boolean flag = false;
               boolean flag2 = false;
               boolean flag3 = false;
               boolean flag4 = false;
               boolean flag5 = false;
               byte b = -1;
               int n10 = 0;
               int n11 = 0;
               int n12 = 0;

               for (int i = n; i < n2; i++) {
                  char ch = str.charAt(i);
                  if (ch >= '0' && ch <= '9') {
                     if (flag) {
                        n11 = ch - '0';
                        flag2 = true;
                     } else {
                        n10 *= 10;
                        n10 += ch - '0';
                        flag3 = true;
                     }
                  } else if (ch == '*') {
                     flag = true;
                  } else if (ch == '!') {
                     if (flag3) {
                        n12 += parsePotionEffectsTerm(flag4, flag2, flag5, b, n10, n11, n3);
                        flag4 = false;
                        flag5 = false;
                        flag = false;
                        flag2 = false;
                        flag3 = false;
                        n11 = 0;
                        n10 = 0;
                        b = -1;
                     }

                     flag4 = true;
                  } else if (ch == '-') {
                     if (flag3) {
                        n12 += parsePotionEffectsTerm(flag4, flag2, flag5, b, n10, n11, n3);
                        flag4 = false;
                        flag5 = false;
                        flag = false;
                        flag2 = false;
                        flag3 = false;
                        n11 = 0;
                        n10 = 0;
                        b = -1;
                     }

                     flag5 = true;
                  } else if (ch != '=' && ch != '<' && ch != '>') {
                     if (ch == '+' && flag3) {
                        n12 += parsePotionEffectsTerm(flag4, flag2, flag5, b, n10, n11, n3);
                        flag4 = false;
                        flag5 = false;
                        flag = false;
                        flag2 = false;
                        flag3 = false;
                        n11 = 0;
                        n10 = 0;
                        b = -1;
                     }
                  } else {
                     if (flag3) {
                        n12 += parsePotionEffectsTerm(flag4, flag2, flag5, b, n10, n11, n3);
                        flag4 = false;
                        flag5 = false;
                        flag = false;
                        flag2 = false;
                        flag3 = false;
                        n11 = 0;
                        n10 = 0;
                        b = -1;
                     }

                     if (ch == '=') {
                        b = 0;
                     } else if (ch == '<') {
                        b = 2;
                     } else if (ch == '>') {
                        b = 1;
                     }
                  }
               }

               if (flag3) {
                  n12 += parsePotionEffectsTerm(flag4, flag2, flag5, b, n10, n11, n3);
               }

               return n12;
            }
         }
      } else {
         return 0;
      }
   }

   public static String getPotionPrefix(int n) {
      int potionPrefixIndex = getPotionPrefixIndex(n);
      return potionPrefixes[potionPrefixIndex];
   }

   public static int getPotionPrefixIndexFlags(int n, int n2, int n3, int n4, int n5, int n6) {
      return (checkFlag(n, n2) ? 16 : 0)
         | (checkFlag(n, n3) ? 8 : 0)
         | (checkFlag(n, n4) ? 4 : 0)
         | (checkFlag(n, n5) ? 2 : 0)
         | (checkFlag(n, n6) ? 1 : 0);
   }

   private static int brewBitOperations(int n, int n2, boolean flag, boolean flag2, boolean flag3) {
      if (flag3) {
         if (!checkFlag(n, n2)) {
            return 0;
         }
      } else if (flag) {
         n &= ~(1 << n2);
      } else if (flag2) {
         if ((n & 1 << n2) == 0) {
            n |= 1 << n2;
         } else {
            n &= ~(1 << n2);
         }
      } else {
         n |= 1 << n2;
      }

      return n;
   }

   private static int countSetFlags(int n) {
      int n2;
      for (n2 = 0; n > 0; n2++) {
         n &= n - 1;
      }

      return n2;
   }
}
