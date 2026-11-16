package client.util;

import client.event.PreRenderDispatcher;
import client.render.EntitySkinLocation;
import client.render.GlassManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityEndercrystal;

public class CubeClassEntry extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"cube", "glass", "base"};
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new GlassManager(0.0F, true);
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      EntitySkinLocation entitySkinLocation = (EntitySkinLocation)Minecraft.getMinecraft().getRenderManager().getEntryMap().get(EntityEndercrystal.class);
      if (!(entitySkinLocation instanceof StandardResourceLocationEntryAdapter)) {
         BuildManager.applyText3("Not an instance of RenderEnderCrystal: " + entitySkinLocation);
         return null;
      } else {
         StandardResourceLocationEntryAdapter standardResourceLocationEntryAdapter = (StandardResourceLocationEntryAdapter)entitySkinLocation;
         standardResourceLocationEntryAdapter.passiveFloatEntry = passiveFloatEntry;
         return standardResourceLocationEntryAdapter;
      }
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof GlassManager)) {
         return null;
      } else {
         GlassManager glassManager = (GlassManager)passiveFloatEntry;
         switch (text) {
            case "cube":
               return glassManager.boxesManager;
            case "glass":
               return glassManager.boxesManager3;
            case "base":
               return glassManager.boxesManager2;
            default:
               return null;
         }
      }
   }

   public CubeClassEntry(String text) {
      super(EntityEndercrystal.class, text, 0.5F);
   }

   public CubeClassEntry() {
      this("end_crystal");
   }
}
