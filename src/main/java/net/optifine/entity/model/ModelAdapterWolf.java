package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;

public class ModelAdapterWolf extends ModelAdapter {

   @Override
   public ModelBase makeModel() {
      return new ModelWolf();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelWolf)) {
         return null;
      } else {
         ModelWolf modelWolf = (ModelWolf)modelBase;
         return str.equals("head")
            ? modelWolf.wolfHeadMain
            : (
               str.equals("body")
                  ? modelWolf.wolfBody
                  : (
                     str.equals("leg1")
                        ? modelWolf.wolfLeg1
                        : (
                           str.equals("leg2")
                              ? modelWolf.wolfLeg2
                              : (
                                 str.equals("leg3")
                                    ? modelWolf.wolfLeg3
                                    : (
                                       str.equals("leg4")
                                          ? modelWolf.wolfLeg4
                                          : (str.equals("tail") ? modelWolf.wolfTail : (str.equals("mane") ? modelWolf.wolfMane : null))
                                    )
                              )
                        )
                  )
            );
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4", "tail", "mane"};
   }

   public ModelAdapterWolf() {
      super(EntityWolf.class, "wolf", 0.5F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new RenderWolf(renderManager, modelBase, f);
   }
}
