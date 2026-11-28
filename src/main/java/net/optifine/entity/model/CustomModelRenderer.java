package net.optifine.entity.model;

import net.minecraft.client.model.ModelRenderer;
import net.optifine.entity.model.anim.ModelUpdater;

public class CustomModelRenderer {
   private String modelPart;
   private boolean attach;
   private ModelRenderer modelRenderer;
   private ModelUpdater modelUpdater;

   public ModelUpdater getModelUpdater() {
      return this.modelUpdater;
   }

   public String getModelPart() {
      return this.modelPart;
   }

   public boolean isAttach() {
      return this.attach;
   }

   public ModelRenderer getModelRenderer() {
      return this.modelRenderer;
   }

   public CustomModelRenderer(String str, boolean flag, ModelRenderer modelRenderer2, ModelUpdater modelUpdater2) {
      this.modelPart = str;
      this.attach = flag;
      this.modelRenderer = modelRenderer2;
      this.modelUpdater = modelUpdater2;
   }
}
