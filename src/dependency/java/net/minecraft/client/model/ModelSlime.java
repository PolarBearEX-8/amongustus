package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.DefaultResourceLocationEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntitySlime;
import net.minecraft.item.DiamondRod;

public class ModelSlime extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"body", "left_eye", "right_eye", "mouth"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new DefaultResourceLocationEntry(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondRod(16);
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondRod)) {
         return null;
      } else {
         DiamondRod diamondRod = (DiamondRod)passiveFloatEntry;
         switch (text) {
            case "body":
               return diamondRod.boxesManager3;
            case "left_eye":
               return diamondRod.boxesManager;
            case "right_eye":
               return diamondRod.boxesManager2;
            case "mouth":
               return diamondRod.boxesManager4;
            default:
               return null;
         }
      }
   }

   public ModelSlime() {
      super(EntitySlime.class, "slime", 0.25F);
   }
}
