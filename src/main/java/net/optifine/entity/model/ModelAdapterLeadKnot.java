package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelLeashKnot;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLeashKnot;

public class ModelAdapterLeadKnot extends ModelAdapter {

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"knot"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderLeashKnot renderLeashKnot = new RenderLeashKnot(renderManager);
      renderLeashKnot.leashKnotModel = (ModelLeashKnot)modelBase;
      renderLeashKnot.shadowSize = f;
      return renderLeashKnot;
   }

   @Override
   public ModelBase makeModel() {
      return new ModelLeashKnot();
   }

   public ModelAdapterLeadKnot() {
      super(EntityLeashKnot.class, "lead_knot", 0.0F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelLeashKnot)) {
         return null;
      } else {
         ModelLeashKnot modelLeashKnot = (ModelLeashKnot)modelBase;
         return str.equals("knot") ? modelLeashKnot.knotRenderer : null;
      }
   }
}
