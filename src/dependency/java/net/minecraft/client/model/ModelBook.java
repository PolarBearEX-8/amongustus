package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.InternalResourceLocationArrayHandler;
import client.util.PassiveFloatEntry;
import client.util.ResourceLocationArrayHolder;
import client.util.StandardMapEntry;
import net.minecraft.item.DiamondDust;
import net.minecraft.tileentity.TileEntityEnchanttable;

public class ModelBook extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"cover_right", "cover_left", "pages_right", "pages_left", "flipping_page_right", "flipping_page_left", "book_spine"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondDust();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      StandardMapEntry standardMapEntry = StandardMapEntry.standardMapEntry;
      Object entry = standardMapEntry.resolveResourceLocationArrayHolder(TileEntityEnchanttable.class);
      if (!(entry instanceof InternalResourceLocationArrayHandler)) {
         return null;
      } else {
         if (((ResourceLocationArrayHolder)entry).getMemberRef() == null) {
            entry = new InternalResourceLocationArrayHandler();
            ((ResourceLocationArrayHolder)entry).setStandardMapEntry(standardMapEntry);
         }

         ((InternalResourceLocationArrayHandler)entry).diamondDust = (DiamondDust)passiveFloatEntry;
         return (PreRenderDispatcher)entry;
      }
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondDust)) {
         return null;
      } else {
         DiamondDust diamondDust = (DiamondDust)passiveFloatEntry;
         return text.equals("cover_right")
            ? diamondDust.boxesManager
            : (
               text.equals("cover_left")
                  ? diamondDust.boxesManager6
                  : (
                     text.equals("pages_right")
                        ? diamondDust.boxesManager2
                        : (
                           text.equals("pages_left")
                              ? diamondDust.boxesManager4
                              : (
                                 text.equals("flipping_page_right")
                                    ? diamondDust.boxesManager3
                                    : (text.equals("flipping_page_left") ? diamondDust.boxesManager7 : (text.equals("book_spine") ? diamondDust.boxesManager5 : null))
                              )
                        )
                  )
            );
      }
   }

   public ModelBook() {
      super(TileEntityEnchanttable.class, "book", 0.0F);
   }
}
