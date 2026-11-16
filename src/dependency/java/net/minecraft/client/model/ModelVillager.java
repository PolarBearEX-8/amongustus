package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BaseResourceLocationEntry;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityVillager;
import net.minecraft.item.DiamondAmulet;

public class ModelVillager extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "body", "arms", "right_leg", "left_leg", "nose"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondAmulet(0.0F);
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      BaseResourceLocationEntry baseResourceLocationEntry = new BaseResourceLocationEntry(renderManager);
      baseResourceLocationEntry.passiveFloatEntry = passiveFloatEntry;
      baseResourceLocationEntry.factor = factor;
      return baseResourceLocationEntry;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondAmulet)) {
         return null;
      } else {
         DiamondAmulet diamondAmulet = (DiamondAmulet)passiveFloatEntry;
         return text.equals("head")
            ? diamondAmulet.boxesManager2
            : (
               text.equals("body")
                  ? diamondAmulet.boxesManager3
                  : (
                     text.equals("arms")
                        ? diamondAmulet.boxesManager
                        : (
                           text.equals("left_leg")
                              ? diamondAmulet.boxesManager4
                              : (text.equals("right_leg") ? diamondAmulet.boxesManager6 : (text.equals("nose") ? diamondAmulet.boxesManager5 : null))
                        )
                  )
            );
      }
   }

   public ModelVillager() {
      super(EntityVillager.class, "villager", 0.5F);
   }
}
