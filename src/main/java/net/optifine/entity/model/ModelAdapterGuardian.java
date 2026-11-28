package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderGuardian;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityGuardian;
import net.optifine.config.Config;

public class ModelAdapterGuardian extends ModelAdapter {

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderGuardian renderGuardian = new RenderGuardian(renderManager);
      renderGuardian.mainModel = modelBase;
      renderGuardian.shadowSize = f;
      return renderGuardian;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{
         "body",
         "eye",
         "spine1",
         "spine2",
         "spine3",
         "spine4",
         "spine5",
         "spine6",
         "spine7",
         "spine8",
         "spine9",
         "spine10",
         "spine11",
         "spine12",
         "tail1",
         "tail2",
         "tail3"
      };
   }

   public ModelAdapterGuardian() {
      super(EntityGuardian.class, "guardian", 0.5F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelGuardian();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelGuardian)) {
         return null;
      } else {
         ModelGuardian modelGuardian = (ModelGuardian)modelBase;
         if (str.equals("body")) {
            return modelGuardian.guardianBody;
         } else if (str.equals("eye")) {
            return modelGuardian.guardianEye;
         } else {
            String str2 = "spine";
            if (str.startsWith(str2)) {
               ModelRenderer[] guardianSpines = modelGuardian.guardianSpines;
               int n = Config.parseInt(str.substring(str2.length()), -1);
               n--;
               return n >= 0 && n < guardianSpines.length ? guardianSpines[n] : null;
            } else {
               String str4 = "tail";
               if (!str.startsWith(str4)) {
                  return null;
               } else {
                  ModelRenderer[] guardianTail = modelGuardian.guardianTail;
                  int n2 = Config.parseInt(str.substring(str4.length()), -1);
                  n2--;
                  return n2 >= 0 && n2 < guardianTail.length ? guardianTail[n2] : null;
               }
            }
         }
      }
   }
}
