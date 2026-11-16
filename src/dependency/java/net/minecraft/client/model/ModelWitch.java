package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.MeshBufferRenderer;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityWitch;
import net.minecraft.item.DiamondStaff;

public class ModelWitch extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"mole", "head", "body", "arms", "right_leg", "left_leg", "nose"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondStaff(0.0F);
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      MeshBufferRenderer meshBufferRenderer = new MeshBufferRenderer(renderManager);
      meshBufferRenderer.passiveFloatEntry = passiveFloatEntry;
      meshBufferRenderer.factor = factor;
      return meshBufferRenderer;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondStaff)) {
         return null;
      } else {
         DiamondStaff diamondStaff = (DiamondStaff)passiveFloatEntry;
         if (text.equals("mole")) {
            return diamondStaff.boxesManager8;
         } else if (text.equals("hat")) {
            return diamondStaff.boxesManager12;
         } else {
            return text.equals("head")
               ? diamondStaff.boxesManager2
               : (
                  text.equals("body")
                     ? diamondStaff.boxesManager3
                     : (
                        text.equals("arms")
                           ? diamondStaff.boxesManager
                           : (
                              text.equals("left_leg")
                                 ? diamondStaff.boxesManager4
                                 : (text.equals("right_leg") ? diamondStaff.boxesManager6 : (text.equals("nose") ? diamondStaff.boxesManager5 : null))
                           )
                     )
               );
         }
      }
   }

   public ModelWitch() {
      super(EntityWitch.class, "witch", 0.5F);
   }
}
