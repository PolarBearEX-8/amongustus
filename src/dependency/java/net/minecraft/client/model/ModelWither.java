package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.BuildManager;
import client.util.ClassEntry;
import client.util.ExtraResourceLocationEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.item.CustomItemArrayEntry;

public class ModelWither extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"body1", "body2", "body3", "head1", "head2", "head3"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      ExtraResourceLocationEntry extraResourceLocationEntry = new ExtraResourceLocationEntry(renderManager);
      extraResourceLocationEntry.passiveFloatEntry = passiveFloatEntry;
      extraResourceLocationEntry.factor = factor;
      return extraResourceLocationEntry;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new CustomItemArrayEntry(0.0F);
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof CustomItemArrayEntry)) {
         return null;
      } else {
         CustomItemArrayEntry customItemArrayEntry = (CustomItemArrayEntry)passiveFloatEntry;
         String text2 = "body";
         if (text.startsWith(text2)) {
            BoxesManager[] boxesManagerArray = customItemArrayEntry.boxesManagerArray;
            int count = BuildManager.computeCount(text.substring(text2.length()), -1);
            count--;
            return count >= 0 && count < boxesManagerArray.length ? boxesManagerArray[count] : null;
         } else {
            String text3 = "head";
            if (!text.startsWith(text3)) {
               return null;
            } else {
               BoxesManager[] boxesManagerArray2 = customItemArrayEntry.boxesManagerArray2;
               int count2 = BuildManager.computeCount(text.substring(text3.length()), -1);
               count2--;
               return count2 >= 0 && count2 < boxesManagerArray2.length ? boxesManagerArray2[count2] : null;
            }
         }
      }
   }

   public ModelWither() {
      super(EntityWitherboss.class, "wither", 0.5F);
   }
}
