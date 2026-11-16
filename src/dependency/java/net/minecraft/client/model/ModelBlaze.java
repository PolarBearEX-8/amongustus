package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.LineLoopRenderer;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.BuildManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBlaze;
import net.minecraft.item.TertiaryItemArrayEntry;

public class ModelBlaze extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "stick1", "stick2", "stick3", "stick4", "stick5", "stick6", "stick7", "stick8", "stick9", "stick10", "stick11", "stick12"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new TertiaryItemArrayEntry();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      LineLoopRenderer lineLoopRenderer = new LineLoopRenderer(renderManager);
      lineLoopRenderer.passiveFloatEntry = passiveFloatEntry;
      lineLoopRenderer.factor = factor;
      return lineLoopRenderer;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof TertiaryItemArrayEntry)) {
         return null;
      } else {
         TertiaryItemArrayEntry tertiaryItemArrayEntry = (TertiaryItemArrayEntry)passiveFloatEntry;
         if (text.equals("head")) {
            return tertiaryItemArrayEntry.boxesManager;
         } else {
            String text2 = "stick";
            if (!text.startsWith(text2)) {
               return null;
            } else {
               BoxesManager[] boxesManagerArray = tertiaryItemArrayEntry.boxesManagerArray;
               int count = BuildManager.computeCount(text.substring(text2.length()), -1);
               count--;
               return count >= 0 && count < boxesManagerArray.length ? boxesManagerArray[count] : null;
            }
         }
      }
   }

   public ModelBlaze() {
      super(EntityBlaze.class, "blaze", 0.5F);
   }
}
