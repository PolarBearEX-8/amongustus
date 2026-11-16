package client.util;

public class PlusMinusTypePlusMinusTypeSwitchMap {
   public static final int[] countArray = new int[PlusMinusType.values().length];

   static {
      try {
         countArray[PlusMinusType.PLUS.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[PlusMinusType.MINUS.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[PlusMinusType.MUL.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[PlusMinusType.DIV.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[PlusMinusType.MOD.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[PlusMinusType.NEG.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[PlusMinusType.PI.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[PlusMinusType.SIN.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[PlusMinusType.COS.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[PlusMinusType.ASIN.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[PlusMinusType.ACOS.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[PlusMinusType.TAN.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[PlusMinusType.ATAN.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray[PlusMinusType.ATAN2.ordinal()] = 14;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }

      try {
         countArray[PlusMinusType.TORAD.ordinal()] = 15;
      } catch (NoSuchFieldError noSuchFieldError15) {
      }

      try {
         countArray[PlusMinusType.TODEG.ordinal()] = 16;
      } catch (NoSuchFieldError noSuchFieldError16) {
      }

      try {
         countArray[PlusMinusType.MIN.ordinal()] = 17;
      } catch (NoSuchFieldError noSuchFieldError17) {
      }

      try {
         countArray[PlusMinusType.MAX.ordinal()] = 18;
      } catch (NoSuchFieldError noSuchFieldError18) {
      }

      try {
         countArray[PlusMinusType.CLAMP.ordinal()] = 19;
      } catch (NoSuchFieldError noSuchFieldError19) {
      }

      try {
         countArray[PlusMinusType.ABS.ordinal()] = 20;
      } catch (NoSuchFieldError noSuchFieldError20) {
      }

      try {
         countArray[PlusMinusType.EXP.ordinal()] = 21;
      } catch (NoSuchFieldError noSuchFieldError21) {
      }

      try {
         countArray[PlusMinusType.FLOOR.ordinal()] = 22;
      } catch (NoSuchFieldError noSuchFieldError22) {
      }

      try {
         countArray[PlusMinusType.CEIL.ordinal()] = 23;
      } catch (NoSuchFieldError noSuchFieldError23) {
      }

      try {
         countArray[PlusMinusType.FRAC.ordinal()] = 24;
      } catch (NoSuchFieldError noSuchFieldError24) {
      }

      try {
         countArray[PlusMinusType.LOG.ordinal()] = 25;
      } catch (NoSuchFieldError noSuchFieldError25) {
      }

      try {
         countArray[PlusMinusType.POW.ordinal()] = 26;
      } catch (NoSuchFieldError noSuchFieldError26) {
      }

      try {
         countArray[PlusMinusType.RANDOM.ordinal()] = 27;
      } catch (NoSuchFieldError noSuchFieldError27) {
      }

      try {
         countArray[PlusMinusType.ROUND.ordinal()] = 28;
      } catch (NoSuchFieldError noSuchFieldError28) {
      }

      try {
         countArray[PlusMinusType.SIGNUM.ordinal()] = 29;
      } catch (NoSuchFieldError noSuchFieldError29) {
      }

      try {
         countArray[PlusMinusType.SQRT.ordinal()] = 30;
      } catch (NoSuchFieldError noSuchFieldError30) {
      }

      try {
         countArray[PlusMinusType.FMOD.ordinal()] = 31;
      } catch (NoSuchFieldError noSuchFieldError31) {
      }

      try {
         countArray[PlusMinusType.TIME.ordinal()] = 32;
      } catch (NoSuchFieldError noSuchFieldError32) {
      }

      try {
         countArray[PlusMinusType.IF.ordinal()] = 33;
      } catch (NoSuchFieldError noSuchFieldError33) {
      }

      try {
         countArray[PlusMinusType.SMOOTH.ordinal()] = 34;
      } catch (NoSuchFieldError noSuchFieldError34) {
      }

      try {
         countArray[PlusMinusType.TRUE.ordinal()] = 35;
      } catch (NoSuchFieldError noSuchFieldError35) {
      }

      try {
         countArray[PlusMinusType.FALSE.ordinal()] = 36;
      } catch (NoSuchFieldError noSuchFieldError36) {
      }

      try {
         countArray[PlusMinusType.NOT.ordinal()] = 37;
      } catch (NoSuchFieldError noSuchFieldError37) {
      }

      try {
         countArray[PlusMinusType.AND.ordinal()] = 38;
      } catch (NoSuchFieldError noSuchFieldError38) {
      }

      try {
         countArray[PlusMinusType.OR.ordinal()] = 39;
      } catch (NoSuchFieldError noSuchFieldError39) {
      }

      try {
         countArray[PlusMinusType.GREATER.ordinal()] = 40;
      } catch (NoSuchFieldError noSuchFieldError40) {
      }

      try {
         countArray[PlusMinusType.GREATER_OR_EQUAL.ordinal()] = 41;
      } catch (NoSuchFieldError noSuchFieldError41) {
      }

      try {
         countArray[PlusMinusType.SMALLER.ordinal()] = 42;
      } catch (NoSuchFieldError noSuchFieldError42) {
      }

      try {
         countArray[PlusMinusType.SMALLER_OR_EQUAL.ordinal()] = 43;
      } catch (NoSuchFieldError noSuchFieldError43) {
      }

      try {
         countArray[PlusMinusType.EQUAL.ordinal()] = 44;
      } catch (NoSuchFieldError noSuchFieldError44) {
      }

      try {
         countArray[PlusMinusType.NOT_EQUAL.ordinal()] = 45;
      } catch (NoSuchFieldError noSuchFieldError45) {
      }

      try {
         countArray[PlusMinusType.BETWEEN.ordinal()] = 46;
      } catch (NoSuchFieldError noSuchFieldError46) {
      }

      try {
         countArray[PlusMinusType.EQUALS.ordinal()] = 47;
      } catch (NoSuchFieldError noSuchFieldError47) {
      }

      try {
         countArray[PlusMinusType.IN.ordinal()] = 48;
      } catch (NoSuchFieldError noSuchFieldError48) {
      }

      try {
         countArray[PlusMinusType.VEC2.ordinal()] = 49;
      } catch (NoSuchFieldError noSuchFieldError49) {
      }

      try {
         countArray[PlusMinusType.VEC3.ordinal()] = 50;
      } catch (NoSuchFieldError noSuchFieldError50) {
      }

      try {
         countArray[PlusMinusType.VEC4.ordinal()] = 51;
      } catch (NoSuchFieldError noSuchFieldError51) {
      }
   }
}
