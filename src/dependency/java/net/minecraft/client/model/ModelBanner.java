package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.InternalFloatEntryAdapter;
import client.util.PassiveFloatEntry;
import client.util.ResourceLocationArrayHolder;
import client.util.StandardMapEntry;
import client.util.TertiaryResourceLocationArrayHandler;
import net.minecraft.tileentity.TileEntityBanner;

public class ModelBanner extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"slate", "stand", "top"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new InternalFloatEntryAdapter();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      StandardMapEntry standardMapEntry = StandardMapEntry.standardMapEntry;
      Object entry = standardMapEntry.resolveResourceLocationArrayHolder(TileEntityBanner.class);
      if (!(entry instanceof TertiaryResourceLocationArrayHandler)) {
         return null;
      } else {
         if (((ResourceLocationArrayHolder)entry).getMemberRef() == null) {
            entry = new TertiaryResourceLocationArrayHandler();
            ((ResourceLocationArrayHolder)entry).setStandardMapEntry(standardMapEntry);
         }

         ((TertiaryResourceLocationArrayHandler)entry).internalFloatEntryAdapter = (InternalFloatEntryAdapter)passiveFloatEntry;
         return (PreRenderDispatcher)entry;
      }
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof InternalFloatEntryAdapter)) {
         return null;
      } else {
         InternalFloatEntryAdapter internalFloatEntryAdapter = (InternalFloatEntryAdapter)passiveFloatEntry;
         return text.equals("slate") ? internalFloatEntryAdapter.boxesManager2 : (text.equals("stand") ? internalFloatEntryAdapter.boxesManager3 : (text.equals("top") ? internalFloatEntryAdapter.boxesManager : null));
      }
   }

   public ModelBanner() {
      super(TileEntityBanner.class, "banner", 0.0F);
   }
}
