package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityGhast;
import net.optifine.config.Config;

public class ModelAdapterGhast extends ModelAdapter {

   public ModelAdapterGhast() {
      super(EntityGhast.class, "ghast", 0.5F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelGhast();
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8", "tentacle9"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderGhast renderGhast = new RenderGhast(renderManager);
      renderGhast.mainModel = modelBase;
      renderGhast.shadowSize = f;
      return renderGhast;
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelGhast)) {
         return null;
      } else {
         ModelGhast modelGhast = (ModelGhast)modelBase;
         if (str.equals("body")) {
            return modelGhast.body;
         } else {
            String str2 = "tentacle";
            if (!str.startsWith(str2)) {
               return null;
            } else {
               ModelRenderer[] tentacles = modelGhast.tentacles;
               int n = Config.parseInt(str.substring(str2.length()), -1);
               n--;
               return n >= 0 && n < tentacles.length ? tentacles[n] : null;
            }
         }
      }
   }
}
