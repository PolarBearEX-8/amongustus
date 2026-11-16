package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.EnhancedResourceLocationEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntitySpider;
import net.minecraft.item.DiamondArmor;

public class ModelSpider extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "neck", "body", "leg1", "leg2", "leg3", "leg4", "leg5", "leg6", "leg7", "leg8"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      EnhancedResourceLocationEntry enhancedResourceLocationEntry = new EnhancedResourceLocationEntry(renderManager);
      enhancedResourceLocationEntry.passiveFloatEntry = passiveFloatEntry;
      enhancedResourceLocationEntry.factor = factor;
      return enhancedResourceLocationEntry;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondArmor();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondArmor)) {
         return null;
      } else {
         DiamondArmor diamondArmor = (DiamondArmor)passiveFloatEntry;
         return text.equals("head")
            ? diamondArmor.boxesManager6
            : (
               text.equals("neck")
                  ? diamondArmor.boxesManager4
                  : (
                     text.equals("body")
                        ? diamondArmor.boxesManager11
                        : (
                           text.equals("leg1")
                              ? diamondArmor.boxesManager5
                              : (
                                 text.equals("leg2")
                                    ? diamondArmor.boxesManager7
                                    : (
                                       text.equals("leg3")
                                          ? diamondArmor.boxesManager8
                                          : (
                                             text.equals("leg4")
                                                ? diamondArmor.boxesManager
                                                : (
                                                   text.equals("leg5")
                                                      ? diamondArmor.boxesManager9
                                                      : (
                                                         text.equals("leg6")
                                                            ? diamondArmor.boxesManager10
                                                            : (text.equals("leg7") ? diamondArmor.boxesManager3 : (text.equals("leg8") ? diamondArmor.boxesManager2 : null))
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
            );
      }
   }

   public ModelSpider(Class clazz, String text, float factor) {
      super(clazz, text, factor);
   }

   public ModelSpider() {
      super(EntitySpider.class, "spider", 1.0F);
   }
}
