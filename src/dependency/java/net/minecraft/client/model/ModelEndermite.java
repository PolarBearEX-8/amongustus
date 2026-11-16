package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.render.TriangleFanRenderer;
import client.util.BoxesManager;
import client.util.BuildManager;
import client.util.ClassEntry;
import client.util.CompositeIntArrayEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityEndermite;

public class ModelEndermite extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"body1", "body2", "body3", "body4"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new CompositeIntArrayEntry();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      TriangleFanRenderer triangleFanRenderer = new TriangleFanRenderer(renderManager);
      triangleFanRenderer.passiveFloatEntry = passiveFloatEntry;
      triangleFanRenderer.factor = factor;
      return triangleFanRenderer;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof CompositeIntArrayEntry)) {
         return null;
      } else {
         CompositeIntArrayEntry compositeIntArrayEntry = (CompositeIntArrayEntry)passiveFloatEntry;
         String text2 = "body";
         if (!text.startsWith(text2)) {
            return null;
         } else {
            BoxesManager[] boxesManagerArray = compositeIntArrayEntry.boxesManagerArray;
            int count = BuildManager.computeCount(text.substring(text2.length()), -1);
            count--;
            return count >= 0 && count < boxesManagerArray.length ? boxesManagerArray[count] : null;
         }
      }
   }

   public ModelEndermite() {
      super(EntityEndermite.class, "endermite", 0.3F);
   }
}
