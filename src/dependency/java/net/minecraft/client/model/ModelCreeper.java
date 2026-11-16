package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.QuadBatchRenderer;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.item.DiamondOrb;

public class ModelCreeper extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "armor", "body", "leg1", "leg2", "leg3", "leg4"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondOrb();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      QuadBatchRenderer quadBatchRenderer = new QuadBatchRenderer(renderManager);
      quadBatchRenderer.passiveFloatEntry = passiveFloatEntry;
      quadBatchRenderer.factor = factor;
      return quadBatchRenderer;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondOrb)) {
         return null;
      } else {
         DiamondOrb diamondOrb = (DiamondOrb)passiveFloatEntry;
         return text.equals("head")
            ? diamondOrb.boxesManager3
            : (
               text.equals("armor")
                  ? diamondOrb.boxesManager6
                  : (
                     text.equals("body")
                        ? diamondOrb.boxesManager5
                        : (
                           text.equals("leg1")
                              ? diamondOrb.boxesManager
                              : (
                                 text.equals("leg2")
                                    ? diamondOrb.boxesManager7
                                    : (text.equals("leg3") ? diamondOrb.boxesManager2 : (text.equals("leg4") ? diamondOrb.boxesManager4 : null))
                              )
                        )
                  )
            );
      }
   }

   public ModelCreeper() {
      super(EntityCreeper.class, "creeper", 0.5F);
   }
}
