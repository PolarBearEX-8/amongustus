package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.entity.passive.EntityOcelot;

public class ModelAdapterOcelot extends ModelAdapter {
   private static Map mapPartFields = null;

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new RenderOcelot(renderManager, modelBase, f);
   }

   public ModelAdapterOcelot() {
      super(EntityOcelot.class, "ocelot", 0.4F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"back_left_leg", "back_right_leg", "front_left_leg", "front_right_leg", "tail", "tail2", "head", "body"};
   }

   private static Map getMapPartFields() {
      if (mapPartFields != null) {
         return mapPartFields;
      } else {
         mapPartFields = new HashMap();
         mapPartFields.put("back_left_leg", 0);
         mapPartFields.put("back_right_leg", 1);
         mapPartFields.put("front_left_leg", 2);
         mapPartFields.put("front_right_leg", 3);
         mapPartFields.put("tail", 4);
         mapPartFields.put("tail2", 5);
         mapPartFields.put("head", 6);
         mapPartFields.put("body", 7);
         return mapPartFields;
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelOcelot();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelOcelot)) {
         return null;
      } else {
         ModelOcelot modelOcelot = (ModelOcelot)modelBase;
         switch (str) {
            case "back_left_leg":
               return modelOcelot.ocelotBackLeftLeg;
            case "back_right_leg":
               return modelOcelot.ocelotBackRightLeg;
            case "front_left_leg":
               return modelOcelot.ocelotFrontLeftLeg;
            case "front_right_leg":
               return modelOcelot.ocelotFrontRightLeg;
            case "tail":
               return modelOcelot.ocelotTail;
            case "tail2":
               return modelOcelot.ocelotTail2;
            case "head":
               return modelOcelot.ocelotHead;
            case "body":
               return modelOcelot.ocelotBody;
            default:
               return null;
         }
      }
   }
}
