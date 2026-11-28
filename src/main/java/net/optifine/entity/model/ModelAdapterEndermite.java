package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderEndermite;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityEndermite;
import net.optifine.config.Config;

public class ModelAdapterEndermite extends ModelAdapter {

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "body4"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderEndermite renderEndermite = new RenderEndermite(renderManager);
      renderEndermite.mainModel = modelBase;
      renderEndermite.shadowSize = f;
      return renderEndermite;
   }

   public ModelAdapterEndermite() {
      super(EntityEndermite.class, "endermite", 0.3F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelEnderMite();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelEnderMite)) {
         return null;
      } else {
         ModelEnderMite modelEnderMite = (ModelEnderMite)modelBase;
         String str2 = "body";
         if (!str.startsWith(str2)) {
            return null;
         } else {
            ModelRenderer[] bodyParts = modelEnderMite.bodyParts;
            int n = Config.parseInt(str.substring(str2.length()), -1);
            n--;
            return n >= 0 && n < bodyParts.length ? bodyParts[n] : null;
         }
      }
   }
}
