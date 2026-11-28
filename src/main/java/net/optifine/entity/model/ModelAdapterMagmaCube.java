package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderMagmaCube;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.optifine.config.Config;

public class ModelAdapterMagmaCube extends ModelAdapter {

   @Override
   public ModelBase makeModel() {
      return new ModelMagmaCube();
   }

   public ModelAdapterMagmaCube() {
      super(EntityMagmaCube.class, "magma_cube", 0.5F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"core", "segment1", "segment2", "segment3", "segment4", "segment5", "segment6", "segment7", "segment8"};
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelMagmaCube)) {
         return null;
      } else {
         ModelMagmaCube modelMagmaCube = (ModelMagmaCube)modelBase;
         if (str.equals("core")) {
            return modelMagmaCube.core;
         } else {
            String str2 = "segment";
            if (!str.startsWith(str2)) {
               return null;
            } else {
               ModelRenderer[] segments = modelMagmaCube.segments;
               int n = Config.parseInt(str.substring(str2.length()), -1);
               n--;
               return n >= 0 && n < segments.length ? segments[n] : null;
            }
         }
      }
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderMagmaCube renderMagmaCube = new RenderMagmaCube(renderManager);
      renderMagmaCube.mainModel = modelBase;
      renderMagmaCube.shadowSize = f;
      return renderMagmaCube;
   }
}
