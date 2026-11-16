package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import client.util.ResourceLocationArrayHolder;
import client.util.SecondaryResourceLocationArrayHandler;
import client.util.StandardMapEntry;
import net.minecraft.item.DiamondShard;
import net.minecraft.tileentity.TileEntitySign;

public class ModelSign extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"board", "stick"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondShard();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      StandardMapEntry standardMapEntry = StandardMapEntry.standardMapEntry;
      Object entry = standardMapEntry.resolveResourceLocationArrayHolder(TileEntitySign.class);
      if (!(entry instanceof SecondaryResourceLocationArrayHandler)) {
         return null;
      } else {
         if (((ResourceLocationArrayHolder)entry).getMemberRef() == null) {
            entry = new SecondaryResourceLocationArrayHandler();
            ((ResourceLocationArrayHolder)entry).setStandardMapEntry(standardMapEntry);
         }

         ((SecondaryResourceLocationArrayHandler)entry).diamondShard = (DiamondShard)passiveFloatEntry;
         return (PreRenderDispatcher)entry;
      }
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondShard)) {
         return null;
      } else {
         DiamondShard diamondShard = (DiamondShard)passiveFloatEntry;
         return text.equals("board") ? diamondShard.boxesManager : (text.equals("stick") ? diamondShard.boxesManager2 : null);
      }
   }

   public ModelSign() {
      super(TileEntitySign.class, "sign", 0.0F);
   }
}
