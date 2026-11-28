package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityBlaze;
import net.optifine.config.Config;

public class ModelAdapterBlaze extends ModelAdapter {

   @Override
   public ModelBase makeModel() {
      return new ModelBlaze();
   }

   public ModelAdapterBlaze() {
      super(EntityBlaze.class, "blaze", 0.5F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelBlaze)) {
         return null;
      } else {
         ModelBlaze modelBlaze = (ModelBlaze)modelBase;
         if (str.equals("head")) {
            return modelBlaze.blazeHead;
         } else {
            String str2 = "stick";
            if (!str.startsWith(str2)) {
               return null;
            } else {
               ModelRenderer[] blazeSticks = modelBlaze.blazeSticks;
               int n = Config.parseInt(str.substring(str2.length()), -1);
               n--;
               return n >= 0 && n < blazeSticks.length ? blazeSticks[n] : null;
            }
         }
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "stick1", "stick2", "stick3", "stick4", "stick5", "stick6", "stick7", "stick8", "stick9", "stick10", "stick11", "stick12"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderBlaze renderBlaze = new RenderBlaze(renderManager);
      renderBlaze.mainModel = modelBase;
      renderBlaze.shadowSize = f;
      return renderBlaze;
   }
}
