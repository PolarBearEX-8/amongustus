package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.entity.projectile.EntityWitherSkull;

public class ModelAdapterWitherSkull extends ModelAdapter {

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelSkeletonHead)) {
         return null;
      } else {
         ModelSkeletonHead modelSkeletonHead = (ModelSkeletonHead)modelBase;
         return str.equals("head") ? modelSkeletonHead.skeletonHead : null;
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderWitherSkull renderWitherSkull = new RenderWitherSkull(renderManager);
      renderWitherSkull.skeletonHeadModel = (ModelSkeletonHead)modelBase;
      renderWitherSkull.shadowSize = f;
      return renderWitherSkull;
   }

   public ModelAdapterWitherSkull() {
      super(EntityWitherSkull.class, "wither_skull", 0.0F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSkeletonHead();
   }
}
