package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.DepthBufferFilter;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.BuildManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLavaslime;
import net.minecraft.item.AdvancedItemArrayEntry;

public class ModelMagmaCube extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"core", "segment1", "segment2", "segment3", "segment4", "segment5", "segment6", "segment7", "segment8"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      DepthBufferFilter depthBufferFilter = new DepthBufferFilter(renderManager);
      depthBufferFilter.passiveFloatEntry = passiveFloatEntry;
      depthBufferFilter.factor = factor;
      return depthBufferFilter;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new AdvancedItemArrayEntry();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof AdvancedItemArrayEntry)) {
         return null;
      } else {
         AdvancedItemArrayEntry advancedItemArrayEntry = (AdvancedItemArrayEntry)passiveFloatEntry;
         if (text.equals("core")) {
            return advancedItemArrayEntry.boxesManager;
         } else {
            String text2 = "segment";
            if (!text.startsWith(text2)) {
               return null;
            } else {
               BoxesManager[] boxesManagerArray = advancedItemArrayEntry.boxesManagerArray;
               int count = BuildManager.computeCount(text.substring(text2.length()), -1);
               count--;
               return count >= 0 && count < boxesManagerArray.length ? boxesManagerArray[count] : null;
            }
         }
      }
   }

   public ModelMagmaCube() {
      super(EntityLavaslime.class, "magma_cube", 0.5F);
   }
}
