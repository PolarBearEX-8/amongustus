package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntitySkull;

public class ModelAdapterHeadHumanoid extends ModelAdapter {

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelHumanoidHead)) {
         return null;
      } else {
         ModelHumanoidHead modelHumanoidHead = (ModelHumanoidHead)modelBase;
         switch (str) {
            case "head":
               return modelHumanoidHead.skeletonHead;
            case "head2":
               return modelHumanoidHead.head;
            default:
               return null;
         }
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head"};
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

         ((TileEntitySkullRenderer)specialRendererByClass).humanoidHead = (ModelHumanoidHead)modelBase;
         return (IEntityRenderer)specialRendererByClass;
      }
   }

   public ModelAdapterHeadHumanoid() {
      super(TileEntitySkull.class, "head_humanoid", 0.0F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelHumanoidHead();
   }
}
