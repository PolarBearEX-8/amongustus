package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import client.util.TertiaryResourceLocationEntryAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLeashknot;
import net.minecraft.item.DiamondBlade;

public class ModelLeadKnot extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"knot"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      TertiaryResourceLocationEntryAdapter tertiaryResourceLocationEntryAdapter = new TertiaryResourceLocationEntryAdapter(renderManager);
      tertiaryResourceLocationEntryAdapter.diamondBlade = (DiamondBlade)passiveFloatEntry;
      tertiaryResourceLocationEntryAdapter.factor = factor;
      return tertiaryResourceLocationEntryAdapter;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondBlade();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondBlade)) {
         return null;
      } else {
         DiamondBlade diamondBlade = (DiamondBlade)passiveFloatEntry;
         return text.equals("knot") ? diamondBlade.boxesManager : null;
      }
   }

   public ModelLeadKnot() {
      super(EntityLeashknot.class, "lead_knot", 0.0F);
   }
}
