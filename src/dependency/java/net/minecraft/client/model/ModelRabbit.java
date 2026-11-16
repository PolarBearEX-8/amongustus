package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.AuxiliaryHeadMainManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.NativeResourceLocationEntry;
import client.util.PassiveFloatEntry;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityRabbit;

public class ModelRabbit extends ClassEntry {
   public static Map map = null;

   @Override
   public String[] getTextArray() {
      return new String[]{
         "left_foot", "right_foot", "left_thigh", "right_thigh", "body", "left_arm", "right_arm", "head", "right_ear", "left_ear", "tail", "nose"
      };
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new AuxiliaryHeadMainManager();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new NativeResourceLocationEntry(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof AuxiliaryHeadMainManager)) {
         return null;
      } else {
         AuxiliaryHeadMainManager auxiliaryHeadMainManager = (AuxiliaryHeadMainManager)passiveFloatEntry;
         switch (text) {
            case "left_foot":
               return auxiliaryHeadMainManager.boxesManager7;
            case "right_foot":
               return auxiliaryHeadMainManager.boxesManager2;
            case "left_thigh":
               return auxiliaryHeadMainManager.boxesManager;
            case "right_thigh":
               return auxiliaryHeadMainManager.boxesManager5;
            case "body":
               return auxiliaryHeadMainManager.boxesManager8;
            case "left_arm":
               return auxiliaryHeadMainManager.boxesManager10;
            case "right_arm":
               return auxiliaryHeadMainManager.boxesManager9;
            case "head":
               return auxiliaryHeadMainManager.boxesManager4;
            case "right_ear":
               return auxiliaryHeadMainManager.boxesManager11;
            case "left_ear":
               return auxiliaryHeadMainManager.boxesManager6;
            case "tail":
               return auxiliaryHeadMainManager.boxesManager3;
            case "nose":
               return auxiliaryHeadMainManager.boxesManager12;
            default:
               return null;
         }
      }
   }

   public ModelRabbit() {
      super(EntityRabbit.class, "rabbit", 0.3F);
   }
}
