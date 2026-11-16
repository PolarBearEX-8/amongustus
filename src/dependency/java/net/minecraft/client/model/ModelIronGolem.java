package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import client.util.SpecialResourceLocationEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityVillagergolem;
import net.minecraft.item.DiamondScythe;

public class ModelIronGolem extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head", "body", "right_arm", "left_arm", "left_leg", "right_leg"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      SpecialResourceLocationEntry specialResourceLocationEntry = new SpecialResourceLocationEntry(renderManager);
      specialResourceLocationEntry.passiveFloatEntry = passiveFloatEntry;
      specialResourceLocationEntry.factor = factor;
      return specialResourceLocationEntry;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondScythe();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondScythe)) {
         return null;
      } else {
         DiamondScythe diamondScythe = (DiamondScythe)passiveFloatEntry;
         return text.equals("head")
            ? diamondScythe.boxesManager5
            : (
               text.equals("body")
                  ? diamondScythe.boxesManager2
                  : (
                     text.equals("left_arm")
                        ? diamondScythe.boxesManager
                        : (
                           text.equals("right_arm")
                              ? diamondScythe.boxesManager3
                              : (text.equals("left_leg") ? diamondScythe.boxesManager4 : (text.equals("right_leg") ? diamondScythe.boxesManager6 : null))
                        )
                  )
            );
      }
   }

   public ModelIronGolem() {
      super(EntityVillagergolem.class, "iron_golem", 0.5F);
   }
}
