package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityChest;

public class ModelAdapterChestLarge extends ModelAdapter {

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelChest)) {
         return null;
      } else {
         ModelChest modelChest = (ModelChest)modelBase;
         return str.equals("lid") ? modelChest.chestLid : (str.equals("base") ? modelChest.chestBelow : (str.equals("knob") ? modelChest.chestKnob : null));
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelLargeChest();
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"lid", "base", "knob"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      TileEntityRendererDispatcher instance = TileEntityRendererDispatcher.instance;
      Object specialRendererByClass = instance.getSpecialRendererByClass(TileEntityChest.class);
      if (!(specialRendererByClass instanceof TileEntityChestRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)specialRendererByClass).getEntityClass() == null) {
            specialRendererByClass = new TileEntityChestRenderer();
            ((TileEntitySpecialRenderer)specialRendererByClass).setRendererDispatcher(instance);
         }

         ((TileEntityChestRenderer)specialRendererByClass).largeChest = (ModelChest)modelBase;
         return (IEntityRenderer)specialRendererByClass;
      }
   }

   public ModelAdapterChestLarge() {
      super(TileEntityChest.class, "chest_large", 0.0F);
   }
}
