package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.monster.EntitySlime;

public class ModelAdapterSlime extends ModelAdapter {

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new RenderSlime(renderManager, modelBase, f);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelSlime)) {
         return null;
      } else {
         ModelSlime modelSlime = (ModelSlime)modelBase;
         switch (str) {
            case "body":
               return modelSlime.slimeBodies;
            case "left_eye":
               return modelSlime.slimeLeftEye;
            case "right_eye":
               return modelSlime.slimeRightEye;
            case "mouth":
               return modelSlime.slimeMouth;
            default:
               return null;
         }
      }
   }

   public ModelAdapterSlime() {
      super(EntitySlime.class, "slime", 0.25F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSlime(16);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body", "left_eye", "right_eye", "mouth"};
   }
}
