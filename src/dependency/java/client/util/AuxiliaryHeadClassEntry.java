package client.util;

import net.minecraft.item.DiamondRing;

public abstract class AuxiliaryHeadClassEntry extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4"};
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondRing)) {
         return null;
      } else {
         DiamondRing diamondRing = (DiamondRing)passiveFloatEntry;
         return text.equals("head")
            ? diamondRing.boxesManager6
            : (
               text.equals("body")
                  ? diamondRing.boxesManager3
                  : (
                     text.equals("leg1")
                        ? diamondRing.boxesManager5
                        : (
                           text.equals("leg2")
                              ? diamondRing.boxesManager2
                              : (text.equals("leg3") ? diamondRing.boxesManager : (text.equals("leg4") ? diamondRing.boxesManager4 : null))
                        )
                  )
            );
      }
   }

   public AuxiliaryHeadClassEntry(Class clazz, String text, float factor) {
      super(clazz, text, factor);
   }
}
