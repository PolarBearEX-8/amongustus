package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.BuildManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import client.util.SupplementalResourceLocationEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.item.DiamondCrystal;

public class ModelGuardian extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{
         "body",
         "eye",
         "spine1",
         "spine2",
         "spine3",
         "spine4",
         "spine5",
         "spine6",
         "spine7",
         "spine8",
         "spine9",
         "spine10",
         "spine11",
         "spine12",
         "tail1",
         "tail2",
         "tail3"
      };
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      SupplementalResourceLocationEntry supplementalResourceLocationEntry = new SupplementalResourceLocationEntry(renderManager);
      supplementalResourceLocationEntry.passiveFloatEntry = passiveFloatEntry;
      supplementalResourceLocationEntry.factor = factor;
      return supplementalResourceLocationEntry;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondCrystal();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondCrystal)) {
         return null;
      } else {
         DiamondCrystal diamondCrystal = (DiamondCrystal)passiveFloatEntry;
         if (text.equals("body")) {
            return diamondCrystal.boxesManager2;
         } else if (text.equals("eye")) {
            return diamondCrystal.boxesManager;
         } else {
            String text2 = "spine";
            if (text.startsWith(text2)) {
               BoxesManager[] boxesManagerArray = diamondCrystal.boxesManagerArray;
               int count = BuildManager.computeCount(text.substring(text2.length()), -1);
               count--;
               return count >= 0 && count < boxesManagerArray.length ? boxesManagerArray[count] : null;
            } else {
               String text3 = "tail";
               if (!text.startsWith(text3)) {
                  return null;
               } else {
                  BoxesManager[] boxesManagerArray2 = diamondCrystal.boxesManagerArray2;
                  int count2 = BuildManager.computeCount(text.substring(text3.length()), -1);
                  count2--;
                  return count2 >= 0 && count2 < boxesManagerArray2.length ? boxesManagerArray2[count2] : null;
               }
            }
         }
      }
   }

   public ModelGuardian() {
      super(EntityGuardian.class, "guardian", 0.5F);
   }
}
