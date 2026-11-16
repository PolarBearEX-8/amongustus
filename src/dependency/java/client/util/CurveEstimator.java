package client.util;

import client.setting.FactoryOptionType;

public class CurveEstimator {
   public static final int[] countArray = new int[FactoryOptionType.values().length];

   static {
      try {
         countArray[FactoryOptionType.INVERT_MOUSE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[FactoryOptionType.VIEW_BOBBING.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[FactoryOptionType.ANAGLYPH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[FactoryOptionType.FBO_ENABLE.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[FactoryOptionType.CHAT_COLOR.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[FactoryOptionType.CHAT_LINKS.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[FactoryOptionType.CHAT_LINKS_PROMPT.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[FactoryOptionType.SNOOPER_ENABLED.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[FactoryOptionType.USE_FULLSCREEN.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[FactoryOptionType.ENABLE_VSYNC.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[FactoryOptionType.USE_VBO.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[FactoryOptionType.TOUCHSCREEN.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[FactoryOptionType.STREAM_SEND_METADATA.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray[FactoryOptionType.FORCE_UNICODE_FONT.ordinal()] = 14;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }

      try {
         countArray[FactoryOptionType.BLOCK_ALTERNATIVES.ordinal()] = 15;
      } catch (NoSuchFieldError noSuchFieldError15) {
      }

      try {
         countArray[FactoryOptionType.REDUCED_DEBUG_INFO.ordinal()] = 16;
      } catch (NoSuchFieldError noSuchFieldError16) {
      }

      try {
         countArray[FactoryOptionType.ENTITY_SHADOWS.ordinal()] = 17;
      } catch (NoSuchFieldError noSuchFieldError17) {
      }
   }
}
