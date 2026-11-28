package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityBat;

public class ModelAdapterBat extends ModelAdapter {

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderBat renderBat = new RenderBat(renderManager);
      renderBat.mainModel = modelBase;
      renderBat.shadowSize = f;
      return renderBat;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "right_wing", "left_wing", "outer_right_wing", "outer_left_wing"};
   }

   public ModelAdapterBat() {
      super(EntityBat.class, "bat", 0.25F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelBat)) {
         return null;
      } else {
         ModelBat modelBat = (ModelBat)modelBase;
         switch (str) {
            case "head":
               return modelBat.batHead;
            case "body":
               return modelBat.batBody;
            case "right_wing":
               return modelBat.batRightWing;
            case "left_wing":
               return modelBat.batLeftWing;
            case "outer_right_wing":
               return modelBat.batOuterRightWing;
            case "outer_left_wing":
               return modelBat.batOuterLeftWing;
            default:
               return null;
         }
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelBat();
   }
}
