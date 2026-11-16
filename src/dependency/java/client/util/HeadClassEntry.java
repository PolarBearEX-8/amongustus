package client.util;

public abstract class HeadClassEntry extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "headwear", "body", "left_arm", "right_arm", "left_leg", "right_leg"};
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof FloatEntryRegistry)) {
         return null;
      } else {
         FloatEntryRegistry floatEntryRegistry = (FloatEntryRegistry)passiveFloatEntry;
         return text.equals("head")
            ? floatEntryRegistry.boxesManager3
            : (
               text.equals("headwear")
                  ? floatEntryRegistry.boxesManager4
                  : (
                     text.equals("body")
                        ? floatEntryRegistry.boxesManager7
                        : (
                           text.equals("left_arm")
                              ? floatEntryRegistry.boxesManager
                              : (
                                 text.equals("right_arm")
                                    ? floatEntryRegistry.boxesManager2
                                    : (text.equals("left_leg") ? floatEntryRegistry.boxesManager6 : (text.equals("right_leg") ? floatEntryRegistry.boxesManager5 : null))
                              )
                        )
                  )
            );
      }
   }

   public HeadClassEntry(Class clazz, String text, float factor) {
      super(clazz, text, factor);
   }
}
