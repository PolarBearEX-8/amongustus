package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.entity.passive.EntitySquid;
import net.optifine.config.Config;

public class ModelAdapterSquid extends ModelAdapter {

   public ModelAdapterSquid() {
      super(EntitySquid.class, "squid", 0.7F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new RenderSquid(renderManager, modelBase, f);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelSquid)) {
         return null;
      } else {
         ModelSquid modelSquid = (ModelSquid)modelBase;
         if (str.equals("body")) {
            return modelSquid.squidBody;
         } else {
            String str2 = "tentacle";
            if (!str.startsWith(str2)) {
               return null;
            } else {
               ModelRenderer[] squidTentacles = modelSquid.squidTentacles;
               int n = Config.parseInt(str.substring(str2.length()), -1);
               n--;
               return n >= 0 && n < squidTentacles.length ? squidTentacles[n] : null;
            }
         }
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSquid();
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8"};
   }
}
