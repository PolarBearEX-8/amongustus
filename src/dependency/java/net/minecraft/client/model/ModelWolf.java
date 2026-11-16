package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.ManagedResourceLocationEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityWolf;
import net.minecraft.item.DiamondTalisman;

public class ModelWolf extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4", "tail", "mane"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new ManagedResourceLocationEntry(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondTalisman();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondTalisman)) {
         return null;
      } else {
         DiamondTalisman diamondTalisman = (DiamondTalisman)passiveFloatEntry;
         return text.equals("head")
            ? diamondTalisman.boxesManager5
            : (
               text.equals("body")
                  ? diamondTalisman.boxesManager7
                  : (
                     text.equals("leg1")
                        ? diamondTalisman.boxesManager6
                        : (
                           text.equals("leg2")
                              ? diamondTalisman.boxesManager
                              : (
                                 text.equals("leg3")
                                    ? diamondTalisman.boxesManager4
                                    : (
                                       text.equals("leg4")
                                          ? diamondTalisman.boxesManager3
                                          : (text.equals("tail") ? diamondTalisman.boxesManager2 : (text.equals("mane") ? diamondTalisman.boxesManager8 : null))
                                    )
                              )
                        )
                  )
            );
      }
   }

   public ModelWolf() {
      super(EntityWolf.class, "wolf", 0.5F);
   }
}
