package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.BuildManager;
import client.util.ClassEntry;
import client.util.CompositeResourceLocationEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntitySilverfish;
import net.minecraft.item.BaseItemArrayEntry;

public class ModelSilverfish extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"body1", "body2", "body3", "body4", "body5", "body6", "body7", "wing1", "wing2", "wing3"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new BaseItemArrayEntry();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      CompositeResourceLocationEntry compositeResourceLocationEntry = new CompositeResourceLocationEntry(renderManager);
      compositeResourceLocationEntry.passiveFloatEntry = passiveFloatEntry;
      compositeResourceLocationEntry.factor = factor;
      return compositeResourceLocationEntry;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof BaseItemArrayEntry)) {
         return null;
      } else {
         BaseItemArrayEntry baseItemArrayEntry = (BaseItemArrayEntry)passiveFloatEntry;
         String text2 = "body";
         if (text.startsWith(text2)) {
            BoxesManager[] boxesManagerArray = baseItemArrayEntry.boxesManagerArray;
            int count = BuildManager.computeCount(text.substring(text2.length()), -1);
            count--;
            return count >= 0 && count < boxesManagerArray.length ? boxesManagerArray[count] : null;
         } else {
            String text3 = "wing";
            if (!text.startsWith(text3)) {
               return null;
            } else {
               BoxesManager[] boxesManagerArray2 = baseItemArrayEntry.boxesManagerArray2;
               int count2 = BuildManager.computeCount(text.substring(text3.length()), -1);
               count2--;
               return count2 >= 0 && count2 < boxesManagerArray2.length ? boxesManagerArray2[count2] : null;
            }
         }
      }
   }

   public ModelSilverfish() {
      super(EntitySilverfish.class, "silverfish", 0.3F);
   }
}
