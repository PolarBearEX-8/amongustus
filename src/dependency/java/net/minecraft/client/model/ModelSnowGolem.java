package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.PrimitiveBatchRenderer;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntitySnowman;
import net.minecraft.item.DiamondSpear;

public class ModelSnowGolem extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"body", "body_bottom", "head", "right_hand", "left_hand"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      PrimitiveBatchRenderer primitiveBatchRenderer = new PrimitiveBatchRenderer(renderManager);
      primitiveBatchRenderer.passiveFloatEntry = passiveFloatEntry;
      primitiveBatchRenderer.factor = factor;
      return primitiveBatchRenderer;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondSpear();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondSpear)) {
         return null;
      } else {
         DiamondSpear diamondSpear = (DiamondSpear)passiveFloatEntry;
         return text.equals("body")
            ? diamondSpear.boxesManager
            : (
               text.equals("body_bottom")
                  ? diamondSpear.boxesManager3
                  : (
                     text.equals("head")
                        ? diamondSpear.boxesManager2
                        : (text.equals("left_hand") ? diamondSpear.boxesManager5 : (text.equals("right_hand") ? diamondSpear.boxesManager4 : null))
                  )
            );
      }
   }

   public ModelSnowGolem() {
      super(EntitySnowman.class, "snow_golem", 0.5F);
   }
}
