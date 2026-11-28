package net.optifine.entity.model;

import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderRabbit;
import net.minecraft.entity.passive.EntityRabbit;

public class ModelAdapterRabbit extends ModelAdapter {
   private static Map mapPartFields = null;

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelRabbit)) {
         return null;
      } else {
         ModelRabbit modelRabbit = (ModelRabbit)modelBase;
         switch (str) {
            case "left_foot":
               return modelRabbit.rabbitLeftFoot;
            case "right_foot":
               return modelRabbit.rabbitRightFoot;
            case "left_thigh":
               return modelRabbit.rabbitLeftThigh;
            case "right_thigh":
               return modelRabbit.rabbitRightThigh;
            case "body":
               return modelRabbit.rabbitBody;
            case "left_arm":
               return modelRabbit.rabbitLeftArm;
            case "right_arm":
               return modelRabbit.rabbitRightArm;
            case "head":
               return modelRabbit.rabbitHead;
            case "right_ear":
               return modelRabbit.rabbitRightEar;
            case "left_ear":
               return modelRabbit.rabbitLeftEar;
            case "tail":
               return modelRabbit.rabbitTail;
            case "nose":
               return modelRabbit.rabbitNose;
            default:
               return null;
         }
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelRabbit();
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new RenderRabbit(renderManager, modelBase, f);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{
         "left_foot", "right_foot", "left_thigh", "right_thigh", "body", "left_arm", "right_arm", "head", "right_ear", "left_ear", "tail", "nose"
      };
   }

   public ModelAdapterRabbit() {
      super(EntityRabbit.class, "rabbit", 0.3F);
   }
}
