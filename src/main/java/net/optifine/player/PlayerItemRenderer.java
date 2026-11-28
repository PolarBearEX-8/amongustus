package net.optifine.player;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class PlayerItemRenderer {
   private int attachTo = 0;
   private ModelRenderer modelRenderer = null;

   public ModelRenderer getModelRenderer() {
      return this.modelRenderer;
   }

   public void render(ModelBiped modelBiped, float f) {
      ModelRenderer attachModel = PlayerItemModel.getAttachModel(modelBiped, this.attachTo);
      if (attachModel != null) {
         attachModel.postRender(f);
      }

      this.modelRenderer.render(f);
   }

   public PlayerItemRenderer(int n, ModelRenderer modelRenderer2) {
      this.attachTo = n;
      this.modelRenderer = modelRenderer2;
   }
}
