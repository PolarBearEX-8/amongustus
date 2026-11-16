package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.BuildManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import client.util.PrimaryResourceLocationEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityGhast;
import net.minecraft.item.DiamondWand;

public class ModelGhast extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8", "tentacle9"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondWand();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      PrimaryResourceLocationEntry primaryResourceLocationEntry = new PrimaryResourceLocationEntry(renderManager);
      primaryResourceLocationEntry.passiveFloatEntry = passiveFloatEntry;
      primaryResourceLocationEntry.factor = factor;
      return primaryResourceLocationEntry;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondWand)) {
         return null;
      } else {
         DiamondWand diamondWand = (DiamondWand)passiveFloatEntry;
         if (text.equals("body")) {
            return diamondWand.boxesManager;
         } else {
            String text2 = "tentacle";
            if (!text.startsWith(text2)) {
               return null;
            } else {
               BoxesManager[] boxesManagerArray = diamondWand.boxesManagerArray;
               int count = BuildManager.computeCount(text.substring(text2.length()), -1);
               count--;
               return count >= 0 && count < boxesManagerArray.length ? boxesManagerArray[count] : null;
            }
         }
      }
   }

   public ModelGhast() {
      super(EntityGhast.class, "ghast", 0.5F);
   }
}
