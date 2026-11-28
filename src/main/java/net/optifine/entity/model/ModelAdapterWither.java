package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.entity.boss.EntityWither;
import net.optifine.config.Config;

public class ModelAdapterWither extends ModelAdapter {

   public ModelAdapterWither() {
      super(EntityWither.class, "wither", 0.5F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelWither)) {
         return null;
      } else {
         ModelWither modelWither = (ModelWither)modelBase;
         String str2 = "body";
         if (str.startsWith(str2)) {
            ModelRenderer[] upperBodyParts = modelWither.upperBodyParts;
            int n = Config.parseInt(str.substring(str2.length()), -1);
            n--;
            return n >= 0 && n < upperBodyParts.length ? upperBodyParts[n] : null;
         } else {
            String str4 = "head";
            if (!str.startsWith(str4)) {
               return null;
            } else {
               ModelRenderer[] heads = modelWither.heads;
               int n2 = Config.parseInt(str.substring(str4.length()), -1);
               n2--;
               return n2 >= 0 && n2 < heads.length ? heads[n2] : null;
            }
         }
      }
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderWither renderWither = new RenderWither(renderManager);
      renderWither.mainModel = modelBase;
      renderWither.shadowSize = f;
      return renderWither;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "head1", "head2", "head3"};
   }

   @Override
   public ModelBase makeModel() {
      return new ModelWither(0.0F);
   }
}
