package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.util.BaseResourceLocationArrayHandler;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import client.util.ResourceLocationArrayHolder;
import client.util.StandardMapEntry;
import net.minecraft.item.ItemEntryRegistry;
import net.minecraft.tileentity.TileEntitySkull;

public class ModelHeadHumanoid extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      StandardMapEntry standardMapEntry = StandardMapEntry.standardMapEntry;
      Object entry = standardMapEntry.resolveResourceLocationArrayHolder(TileEntitySkull.class);
      if (!(entry instanceof BaseResourceLocationArrayHandler)) {
         return null;
      } else {
         if (((ResourceLocationArrayHolder)entry).getMemberRef() == null) {
            entry = new BaseResourceLocationArrayHandler();
            ((ResourceLocationArrayHolder)entry).setStandardMapEntry(standardMapEntry);
         }

         ((BaseResourceLocationArrayHandler)entry).diamondGem = (ItemEntryRegistry)passiveFloatEntry;
         return (PreRenderDispatcher)entry;
      }
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new ItemEntryRegistry();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof ItemEntryRegistry)) {
         return null;
      } else {
         ItemEntryRegistry itemEntryRegistry = (ItemEntryRegistry)passiveFloatEntry;
         switch (text) {
            case "head":
               return itemEntryRegistry.boxesManager;
            case "head2":
               return itemEntryRegistry.boxesManager3;
            default:
               return null;
         }
      }
   }

   public ModelHeadHumanoid() {
      super(TileEntitySkull.class, "head_humanoid", 0.0F);
   }
}
