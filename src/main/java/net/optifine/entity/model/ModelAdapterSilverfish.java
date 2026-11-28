package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.entity.monster.EntitySilverfish;
import net.optifine.config.Config;

public class ModelAdapterSilverfish extends ModelAdapter {

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderSilverfish renderSilverfish = new RenderSilverfish(renderManager);
      renderSilverfish.mainModel = modelBase;
      renderSilverfish.shadowSize = f;
      return renderSilverfish;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "body4", "body5", "body6", "body7", "wing1", "wing2", "wing3"};
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSilverfish();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelSilverfish)) {
         return null;
      } else {
         ModelSilverfish modelSilverfish = (ModelSilverfish)modelBase;
         String str2 = "body";
         if (str.startsWith(str2)) {
            ModelRenderer[] silverfishBodyParts = modelSilverfish.silverfishBodyParts;
            int n = Config.parseInt(str.substring(str2.length()), -1);
            n--;
            return n >= 0 && n < silverfishBodyParts.length ? silverfishBodyParts[n] : null;
         } else {
            String str4 = "wing";
            if (!str.startsWith(str4)) {
               return null;
            } else {
               ModelRenderer[] silverfishWings = modelSilverfish.silverfishWings;
               int n2 = Config.parseInt(str.substring(str4.length()), -1);
               n2--;
               return n2 >= 0 && n2 < silverfishWings.length ? silverfishWings[n2] : null;
            }
         }
      }
   }

   public ModelAdapterSilverfish() {
      super(EntitySilverfish.class, "silverfish", 0.3F);
   }
}
