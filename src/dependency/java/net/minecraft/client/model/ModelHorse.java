package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.EffectPipeline;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.DiamondHammer;

public class ModelHorse extends ClassEntry {
   public static Map map = null;

   @Override
   public String[] getTextArray() {
      return new String[]{
         "head",
         "upper_mouth",
         "lower_mouth",
         "horse_left_ear",
         "horse_right_ear",
         "mule_left_ear",
         "mule_right_ear",
         "neck",
         "horse_face_ropes",
         "mane",
         "body",
         "tail_base",
         "tail_middle",
         "tail_tip",
         "back_left_leg",
         "back_left_shin",
         "back_left_hoof",
         "back_right_leg",
         "back_right_shin",
         "back_right_hoof",
         "front_left_leg",
         "front_left_shin",
         "front_left_hoof",
         "front_right_leg",
         "front_right_shin",
         "front_right_hoof",
         "mule_left_chest",
         "mule_right_chest",
         "horse_saddle_bottom",
         "horse_saddle_front",
         "horse_saddle_back",
         "horse_left_saddle_rope",
         "horse_left_saddle_metal",
         "horse_right_saddle_rope",
         "horse_right_saddle_metal",
         "horse_left_face_metal",
         "horse_right_face_metal",
         "horse_left_rein",
         "horse_right_rein"
      };
   }

   private static Map getMap() {
      if (map != null) {
         return map;
      } else {
         map = new HashMap();
         map.put("head", 0);
         map.put("upper_mouth", 1);
         map.put("lower_mouth", 2);
         map.put("horse_left_ear", 3);
         map.put("horse_right_ear", 4);
         map.put("mule_left_ear", 5);
         map.put("mule_right_ear", 6);
         map.put("neck", 7);
         map.put("horse_face_ropes", 8);
         map.put("mane", 9);
         map.put("body", 10);
         map.put("tail_base", 11);
         map.put("tail_middle", 12);
         map.put("tail_tip", 13);
         map.put("back_left_leg", 14);
         map.put("back_left_shin", 15);
         map.put("back_left_hoof", 16);
         map.put("back_right_leg", 17);
         map.put("back_right_shin", 18);
         map.put("back_right_hoof", 19);
         map.put("front_left_leg", 20);
         map.put("front_left_shin", 21);
         map.put("front_left_hoof", 22);
         map.put("front_right_leg", 23);
         map.put("front_right_shin", 24);
         map.put("front_right_hoof", 25);
         map.put("mule_left_chest", 26);
         map.put("mule_right_chest", 27);
         map.put("horse_saddle_bottom", 28);
         map.put("horse_saddle_front", 29);
         map.put("horse_saddle_back", 30);
         map.put("horse_left_saddle_rope", 31);
         map.put("horse_left_saddle_metal", 32);
         map.put("horse_right_saddle_rope", 33);
         map.put("horse_right_saddle_metal", 34);
         map.put("horse_left_face_metal", 35);
         map.put("horse_right_face_metal", 36);
         map.put("horse_left_rein", 37);
         map.put("horse_right_rein", 38);
         return map;
      }
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new EffectPipeline(renderManager, (DiamondHammer)passiveFloatEntry, factor);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondHammer();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondHammer)) {
         return null;
      } else {
         DiamondHammer diamondHammer = (DiamondHammer)passiveFloatEntry;
         switch (text) {
            case "head":
               return diamondHammer.boxesManager17;
            case "upper_mouth":
               return diamondHammer.boxesManager;
            case "lower_mouth":
               return diamondHammer.boxesManager25;
            case "horse_left_ear":
               return diamondHammer.boxesManager4;
            case "horse_right_ear":
               return diamondHammer.boxesManager10;
            case "mule_left_ear":
               return diamondHammer.boxesManager15;
            case "mule_right_ear":
               return diamondHammer.boxesManager19;
            case "neck":
               return diamondHammer.boxesManager3;
            case "horse_face_ropes":
               return diamondHammer.boxesManager5;
            case "mane":
               return diamondHammer.boxesManager7;
            case "body":
               return diamondHammer.boxesManager9;
            case "tail_base":
               return diamondHammer.boxesManager39;
            case "tail_middle":
               return diamondHammer.boxesManager29;
            case "tail_tip":
               return diamondHammer.boxesManager6;
            case "back_left_leg":
               return diamondHammer.boxesManager37;
            case "back_left_shin":
               return diamondHammer.boxesManager14;
            case "back_left_hoof":
               return diamondHammer.boxesManager8;
            case "back_right_leg":
               return diamondHammer.boxesManager31;
            case "back_right_shin":
               return diamondHammer.boxesManager23;
            case "back_right_hoof":
               return diamondHammer.boxesManager21;
            case "front_left_leg":
               return diamondHammer.boxesManager27;
            case "front_left_shin":
               return diamondHammer.boxesManager16;
            case "front_left_hoof":
               return diamondHammer.boxesManager32;
            case "front_right_leg":
               return diamondHammer.boxesManager28;
            case "front_right_shin":
               return diamondHammer.boxesManager36;
            case "front_right_hoof":
               return diamondHammer.boxesManager2;
            case "mule_left_chest":
               return diamondHammer.boxesManager33;
            case "mule_right_chest":
               return diamondHammer.boxesManager24;
            case "horse_saddle_bottom":
               return diamondHammer.boxesManager38;
            case "horse_saddle_front":
               return diamondHammer.boxesManager12;
            case "horse_saddle_back":
               return diamondHammer.boxesManager34;
            case "horse_left_saddle_rope":
               return diamondHammer.boxesManager18;
            case "horse_left_saddle_metal":
               return diamondHammer.boxesManager35;
            case "horse_right_saddle_rope":
               return diamondHammer.boxesManager20;
            case "horse_right_saddle_metal":
               return diamondHammer.boxesManager30;
            case "horse_left_face_metal":
               return diamondHammer.boxesManager11;
            case "horse_right_face_metal":
               return diamondHammer.boxesManager13;
            case "horse_left_rein":
               return diamondHammer.boxesManager22;
            case "horse_right_rein":
               return diamondHammer.boxesManager26;
            default:
               return null;
         }
      }
   }

   public ModelHorse() {
      super(EntityHorse.class, "horse", 0.75F);
   }

   public ModelHorse(Class clazz, String text, float factor) {
      super(clazz, text, factor);
   }
}
