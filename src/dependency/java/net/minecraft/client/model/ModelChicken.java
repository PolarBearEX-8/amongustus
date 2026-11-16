package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.ParticleBatchRenderer;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityChicken;
import net.minecraft.item.DiamondDagger;

public class ModelChicken extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "body", "right_leg", "left_leg", "right_wing", "left_wing", "bill", "chin"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondDagger();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new ParticleBatchRenderer(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondDagger)) {
         return null;
      } else {
         DiamondDagger diamondDagger = (DiamondDagger)passiveFloatEntry;
         return text.equals("head")
            ? diamondDagger.boxesManager7
            : (
               text.equals("body")
                  ? diamondDagger.boxesManager8
                  : (
                     text.equals("right_leg")
                        ? diamondDagger.boxesManager2
                        : (
                           text.equals("left_leg")
                              ? diamondDagger.boxesManager6
                              : (
                                 text.equals("right_wing")
                                    ? diamondDagger.boxesManager
                                    : (
                                       text.equals("left_wing")
                                          ? diamondDagger.boxesManager5
                                          : (text.equals("bill") ? diamondDagger.boxesManager3 : (text.equals("chin") ? diamondDagger.boxesManager4 : null))
                                    )
                              )
                        )
                  )
            );
      }
   }

   public ModelChicken() {
      super(EntityChicken.class, "chicken", 0.3F);
   }
}
