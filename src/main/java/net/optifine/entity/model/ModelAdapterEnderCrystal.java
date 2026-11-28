package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.optifine.config.Config;

public class ModelAdapterEnderCrystal extends ModelAdapter {

   @Override
   public ModelBase makeModel() {
      return new ModelEnderCrystal(0.0F, true);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"cube", "glass", "base"};
   }

   public ModelAdapterEnderCrystal(String str) {
      super(EntityEnderCrystal.class, str, 0.5F);
   }

   public ModelAdapterEnderCrystal() {
      this("end_crystal");
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      Render render = (Render)Minecraft.getMinecraft().getRenderManager().getEntityRenderMap().get(EntityEnderCrystal.class);
      if (!(render instanceof RenderEnderCrystal)) {
         Config.warn("Not an instance of RenderEnderCrystal: " + render);
         return null;
      } else {
         RenderEnderCrystal renderEnderCrystal = (RenderEnderCrystal)render;
         renderEnderCrystal.modelEnderCrystal = modelBase;
         return renderEnderCrystal;
      }
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelEnderCrystal)) {
         return null;
      } else {
         ModelEnderCrystal modelEnderCrystal = (ModelEnderCrystal)modelBase;
         switch (str) {
            case "cube":
               return modelEnderCrystal.cube;
            case "glass":
               return modelEnderCrystal.glass;
            case "base":
               return modelEnderCrystal.base;
            default:
               return null;
         }
      }
   }
}
