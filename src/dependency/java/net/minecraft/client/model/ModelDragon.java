package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.AltResourceLocationEntry;
import client.util.BodyRotationManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityEnderdragon;

public class ModelDragon extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{
         "head", "spine", "jaw", "body", "rear_leg", "front_leg", "rear_leg_tip", "front_leg_tip", "rear_foot", "front_foot", "wing", "wing_tip"
      };
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new BodyRotationManager(0.0F);
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      AltResourceLocationEntry altResourceLocationEntry = new AltResourceLocationEntry(renderManager);
      altResourceLocationEntry.passiveFloatEntry = passiveFloatEntry;
      altResourceLocationEntry.factor = factor;
      return altResourceLocationEntry;
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof BodyRotationManager)) {
         return null;
      } else {
         BodyRotationManager bodyRotationManager = (BodyRotationManager)passiveFloatEntry;
         switch (text) {
            case "head":
               return bodyRotationManager.boxesManager4;
            case "spine":
               return bodyRotationManager.boxesManager6;
            case "jaw":
               return bodyRotationManager.boxesManager2;
            case "rear_leg":
               return bodyRotationManager.boxesManager9;
            case "front_leg":
               return bodyRotationManager.boxesManager8;
            case "rear_leg_tip":
               return bodyRotationManager.boxesManager10;
            case "front_leg_tip":
               return bodyRotationManager.boxesManager12;
            case "rear_foot":
               return bodyRotationManager.boxesManager3;
            case "front_foot":
               return bodyRotationManager.boxesManager7;
            case "wing":
               return bodyRotationManager.boxesManager11;
            case "wing_tip":
               return bodyRotationManager.boxesManager;
            default:
               return null;
         }
      }
   }

   public ModelDragon() {
      super(EntityEnderdragon.class, "dragon", 0.5F);
   }
}
