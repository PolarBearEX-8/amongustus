package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.render.TextureAtlasRenderer;
import client.util.BoxesManager;
import client.util.BuildManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntitySquid;
import net.minecraft.item.DiamondOre;

public class ModelSquid extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondOre();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new TextureAtlasRenderer(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondOre)) {
         return null;
      } else {
         DiamondOre diamondOre = (DiamondOre)passiveFloatEntry;
         if (text.equals("body")) {
            return diamondOre.boxesManager;
         } else {
            String text2 = "tentacle";
            if (!text.startsWith(text2)) {
               return null;
            } else {
               BoxesManager[] boxesManagerArray = diamondOre.boxesManagerArray;
               int count = BuildManager.computeCount(text.substring(text2.length()), -1);
               count--;
               return count >= 0 && count < boxesManagerArray.length ? boxesManagerArray[count] : null;
            }
         }
      }
   }

   public ModelSquid() {
      super(EntitySquid.class, "squid", 0.7F);
   }
}
