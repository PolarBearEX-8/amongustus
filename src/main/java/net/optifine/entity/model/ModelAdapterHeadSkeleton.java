package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntitySkull;

public class ModelAdapterHeadSkeleton extends ModelAdapter {

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
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      TileEntityRendererDispatcher instance = TileEntityRendererDispatcher.instance;
      Object specialRendererByClass = instance.getSpecialRendererByClass(TileEntitySkull.class);
      if (!(specialRendererByClass instanceof TileEntitySkullRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)specialRendererByClass).getEntityClass() == null) {
            specialRendererByClass = new TileEntitySkullRenderer();
            ((TileEntitySpecialRenderer)specialRendererByClass).setRendererDispatcher(instance);
         }

         ((TileEntitySkullRenderer)specialRendererByClass).humanoidHead = (ModelSkeletonHead)modelBase;
         return (IEntityRenderer)specialRendererByClass;
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head"};
   }

   public ModelAdapterHeadSkeleton() {
      super(TileEntitySkull.class, "head_skeleton", 0.0F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSkeletonHead(0, 0, 64, 32);
   }
}
