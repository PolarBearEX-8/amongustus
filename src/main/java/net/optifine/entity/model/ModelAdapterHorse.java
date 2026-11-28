package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityHorse;

public class ModelAdapterHorse extends ModelAdapter {
   private static Map mapPartFields = null;

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelHorse)) {
         return null;
      } else {
         ModelHorse modelHorse = (ModelHorse)modelBase;
         switch (str) {
            case "head":
               return modelHorse.head;
            case "upper_mouth":
               return modelHorse.upperMouth;
            case "lower_mouth":
               return modelHorse.lowerMouth;
            case "horse_left_ear":
               return modelHorse.horseLeftEar;
            case "horse_right_ear":
               return modelHorse.horseRightEar;
            case "mule_left_ear":
               return modelHorse.muleLeftEar;
            case "mule_right_ear":
               return modelHorse.muleRightEar;
            case "neck":
               return modelHorse.neck;
            case "horse_face_ropes":
               return modelHorse.horseFaceRopes;
            case "mane":
               return modelHorse.mane;
            case "body":
               return modelHorse.body;
            case "tail_base":
               return modelHorse.tailBase;
            case "tail_middle":
               return modelHorse.tailMiddle;
            case "tail_tip":
               return modelHorse.tailTip;
            case "back_left_leg":
               return modelHorse.backLeftLeg;
            case "back_left_shin":
               return modelHorse.backLeftShin;
            case "back_left_hoof":
               return modelHorse.backLeftHoof;
            case "back_right_leg":
               return modelHorse.backRightLeg;
            case "back_right_shin":
               return modelHorse.backRightShin;
            case "back_right_hoof":
               return modelHorse.backRightHoof;
            case "front_left_leg":
               return modelHorse.frontLeftLeg;
            case "front_left_shin":
               return modelHorse.frontLeftShin;
            case "front_left_hoof":
               return modelHorse.frontLeftHoof;
            case "front_right_leg":
               return modelHorse.frontRightLeg;
            case "front_right_shin":
               return modelHorse.frontRightShin;
            case "front_right_hoof":
               return modelHorse.frontRightHoof;
            case "mule_left_chest":
               return modelHorse.muleLeftChest;
            case "mule_right_chest":
               return modelHorse.muleRightChest;
            case "horse_saddle_bottom":
               return modelHorse.horseSaddleBottom;
            case "horse_saddle_front":
               return modelHorse.horseSaddleFront;
            case "horse_saddle_back":
               return modelHorse.horseSaddleBack;
            case "horse_left_saddle_rope":
               return modelHorse.horseLeftSaddleRope;
            case "horse_left_saddle_metal":
               return modelHorse.horseLeftSaddleMetal;
            case "horse_right_saddle_rope":
               return modelHorse.horseRightSaddleRope;
            case "horse_right_saddle_metal":
               return modelHorse.horseRightSaddleMetal;
            case "horse_left_face_metal":
               return modelHorse.horseLeftFaceMetal;
            case "horse_right_face_metal":
               return modelHorse.horseRightFaceMetal;
            case "horse_left_rein":
               return modelHorse.horseLeftRein;
            case "horse_right_rein":
               return modelHorse.horseRightRein;
            default:
               return null;
         }
      }
   }

   public ModelAdapterHorse() {
      super(EntityHorse.class, "horse", 0.75F);
   }

   private static Map getMapPartFields() {
      if (mapPartFields != null) {
         return mapPartFields;
      } else {
         mapPartFields = new HashMap();
         mapPartFields.put("head", 0);
         mapPartFields.put("upper_mouth", 1);
         mapPartFields.put("lower_mouth", 2);
         mapPartFields.put("horse_left_ear", 3);
         mapPartFields.put("horse_right_ear", 4);
         mapPartFields.put("mule_left_ear", 5);
         mapPartFields.put("mule_right_ear", 6);
         mapPartFields.put("neck", 7);
         mapPartFields.put("horse_face_ropes", 8);
         mapPartFields.put("mane", 9);
         mapPartFields.put("body", 10);
         mapPartFields.put("tail_base", 11);
         mapPartFields.put("tail_middle", 12);
         mapPartFields.put("tail_tip", 13);
         mapPartFields.put("back_left_leg", 14);
         mapPartFields.put("back_left_shin", 15);
         mapPartFields.put("back_left_hoof", 16);
         mapPartFields.put("back_right_leg", 17);
         mapPartFields.put("back_right_shin", 18);
         mapPartFields.put("back_right_hoof", 19);
         mapPartFields.put("front_left_leg", 20);
         mapPartFields.put("front_left_shin", 21);
         mapPartFields.put("front_left_hoof", 22);
         mapPartFields.put("front_right_leg", 23);
         mapPartFields.put("front_right_shin", 24);
         mapPartFields.put("front_right_hoof", 25);
         mapPartFields.put("mule_left_chest", 26);
         mapPartFields.put("mule_right_chest", 27);
         mapPartFields.put("horse_saddle_bottom", 28);
         mapPartFields.put("horse_saddle_front", 29);
         mapPartFields.put("horse_saddle_back", 30);
         mapPartFields.put("horse_left_saddle_rope", 31);
         mapPartFields.put("horse_left_saddle_metal", 32);
         mapPartFields.put("horse_right_saddle_rope", 33);
         mapPartFields.put("horse_right_saddle_metal", 34);
         mapPartFields.put("horse_left_face_metal", 35);
         mapPartFields.put("horse_right_face_metal", 36);
         mapPartFields.put("horse_left_rein", 37);
         mapPartFields.put("horse_right_rein", 38);
         return mapPartFields;
      }
   }

   @Override
   public String[] getModelRendererNames() {
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

   public ModelAdapterHorse(Class clazz, String str, float f) {
      super(clazz, str, f);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelHorse();
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new RenderHorse(renderManager, (ModelHorse)modelBase, f);
   }
}
