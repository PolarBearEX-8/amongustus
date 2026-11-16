package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.AuxiliaryRendererLivingEntityAdapter;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.BoxesManagerEntry;
import client.util.BuildManager;
import client.util.HeadClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityArmorstand;

public class ModelArmorStand extends HeadClassEntry {
   @Override
   public String[] getTextArray() {
      return (String[])BuildManager.resolveTargetArray(super.getTextArray(), new String[]{"right", "left", "waist", "base"});
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      AuxiliaryRendererLivingEntityAdapter auxiliaryRendererLivingEntityAdapter = new AuxiliaryRendererLivingEntityAdapter(renderManager);
      auxiliaryRendererLivingEntityAdapter.passiveFloatEntry = passiveFloatEntry;
      auxiliaryRendererLivingEntityAdapter.factor = factor;
      return auxiliaryRendererLivingEntityAdapter;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new BoxesManagerEntry();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof BoxesManagerEntry)) {
         return null;
      } else {
         BoxesManagerEntry boxesManagerEntry = (BoxesManagerEntry)passiveFloatEntry;
         return text.equals("right")
            ? boxesManagerEntry.boxesManager19
            : (
               text.equals("left")
                  ? boxesManagerEntry.boxesManager18
                  : (text.equals("waist") ? boxesManagerEntry.boxesManager20 : (text.equals("base") ? boxesManagerEntry.boxesManager21 : super.resolveBoxesManager(boxesManagerEntry, text)))
            );
      }
   }

   public ModelArmorStand() {
      super(EntityArmorstand.class, "armor_stand", 0.0F);
   }
}
