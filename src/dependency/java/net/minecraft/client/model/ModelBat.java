package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.AuxiliaryFloatEntryAdapter;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.GenericResourceLocationEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBat;

public class ModelBat extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "body", "right_wing", "left_wing", "outer_right_wing", "outer_left_wing"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new AuxiliaryFloatEntryAdapter();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      GenericResourceLocationEntry genericResourceLocationEntry = new GenericResourceLocationEntry(renderManager);
      genericResourceLocationEntry.passiveFloatEntry = passiveFloatEntry;
      genericResourceLocationEntry.factor = factor;
      return genericResourceLocationEntry;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof AuxiliaryFloatEntryAdapter)) {
         return null;
      } else {
         AuxiliaryFloatEntryAdapter auxiliaryFloatEntryAdapter = (AuxiliaryFloatEntryAdapter)passiveFloatEntry;
         switch (text) {
            case "head":
               return auxiliaryFloatEntryAdapter.boxesManager3;
            case "body":
               return auxiliaryFloatEntryAdapter.boxesManager5;
            case "right_wing":
               return auxiliaryFloatEntryAdapter.boxesManager;
            case "left_wing":
               return auxiliaryFloatEntryAdapter.boxesManager4;
            case "outer_right_wing":
               return auxiliaryFloatEntryAdapter.boxesManager6;
            case "outer_left_wing":
               return auxiliaryFloatEntryAdapter.boxesManager2;
            default:
               return null;
         }
      }
   }

   public ModelBat() {
      super(EntityBat.class, "bat", 0.25F);
   }
}
