package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.PostProcessFilter;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.HeadMainManager;
import client.util.PassiveFloatEntry;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityOzelot;

public class ModelOcelot extends ClassEntry {
   public static Map map = null;

   @Override
   public String[] getTextArray() {
      return new String[]{"back_left_leg", "back_right_leg", "front_left_leg", "front_right_leg", "tail", "tail2", "head", "body"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new PostProcessFilter(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new HeadMainManager();
   }

   private static Map getMap() {
      if (map != null) {
         return map;
      } else {
         map = new HashMap();
         map.put("back_left_leg", 0);
         map.put("back_right_leg", 1);
         map.put("front_left_leg", 2);
         map.put("front_right_leg", 3);
         map.put("tail", 4);
         map.put("tail2", 5);
         map.put("head", 6);
         map.put("body", 7);
         return map;
      }
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof HeadMainManager)) {
         return null;
      } else {
         HeadMainManager headMainManager = (HeadMainManager)passiveFloatEntry;
         switch (text) {
            case "back_left_leg":
               return headMainManager.boxesManager7;
            case "back_right_leg":
               return headMainManager.boxesManager8;
            case "front_left_leg":
               return headMainManager.boxesManager3;
            case "front_right_leg":
               return headMainManager.boxesManager4;
            case "tail":
               return headMainManager.boxesManager2;
            case "tail2":
               return headMainManager.boxesManager6;
            case "head":
               return headMainManager.boxesManager;
            case "body":
               return headMainManager.boxesManager5;
            default:
               return null;
         }
      }
   }

   public ModelOcelot() {
      super(EntityOzelot.class, "ocelot", 0.4F);
   }
}
