package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntitySign;

public class ModelAdapterSign extends ModelAdapter {

   @Override
   public ModelBase makeModel() {
      return new ModelSign();
   }

   public ModelAdapterSign() {
      super(TileEntitySign.class, "sign", 0.0F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"board", "stick"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      TileEntityRendererDispatcher instance = TileEntityRendererDispatcher.instance;
      Object specialRendererByClass = instance.getSpecialRendererByClass(TileEntitySign.class);
      if (!(specialRendererByClass instanceof TileEntitySignRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)specialRendererByClass).getEntityClass() == null) {
            specialRendererByClass = new TileEntitySignRenderer();
            ((TileEntitySpecialRenderer)specialRendererByClass).setRendererDispatcher(instance);
         }

         ((TileEntitySignRenderer)specialRendererByClass).model = (ModelSign)modelBase;
         return (IEntityRenderer)specialRendererByClass;
      }
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelSign)) {
         return null;
      } else {
         ModelSign modelSign = (ModelSign)modelBase;
         return str.equals("board") ? modelSign.signBoard : (str.equals("stick") ? modelSign.signStick : null);
      }
   }
}
