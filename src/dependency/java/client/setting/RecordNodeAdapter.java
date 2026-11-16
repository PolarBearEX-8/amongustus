package client.setting;

public class RecordNodeAdapter {
   public static final RecordNodeAdapter recordNodeAdapter = new RecordNodeAdapter();
   public final int[] countArray = new int[FactoryOptionType.values().length];

   public RecordNodeAdapter() {
      try {
         this.countArray[FactoryOptionType.INVERT_MOUSE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray[FactoryOptionType.VIEW_BOBBING.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray[FactoryOptionType.ANAGLYPH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray[FactoryOptionType.FBO_ENABLE.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         this.countArray[FactoryOptionType.CHAT_COLOR.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         this.countArray[FactoryOptionType.CHAT_LINKS.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         this.countArray[FactoryOptionType.CHAT_LINKS_PROMPT.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         this.countArray[FactoryOptionType.SNOOPER_ENABLED.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         this.countArray[FactoryOptionType.USE_FULLSCREEN.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         this.countArray[FactoryOptionType.ENABLE_VSYNC.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         this.countArray[FactoryOptionType.USE_VBO.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         this.countArray[FactoryOptionType.TOUCHSCREEN.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         this.countArray[FactoryOptionType.STREAM_SEND_METADATA.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         this.countArray[FactoryOptionType.FORCE_UNICODE_FONT.ordinal()] = 14;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }

      try {
         this.countArray[FactoryOptionType.BLOCK_ALTERNATIVES.ordinal()] = 15;
      } catch (NoSuchFieldError noSuchFieldError15) {
      }

      try {
         this.countArray[FactoryOptionType.REDUCED_DEBUG_INFO.ordinal()] = 16;
      } catch (NoSuchFieldError noSuchFieldError16) {
      }

      try {
         this.countArray[FactoryOptionType.ENTITY_SHADOWS.ordinal()] = 17;
      } catch (NoSuchFieldError noSuchFieldError17) {
      }
   }
}
