package net.optifine.entity.model;

import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityBannerRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityBanner;

public class ModelAdapterBanner extends ModelAdapter {

   public ModelAdapterBanner() {
      super(TileEntityBanner.class, "banner", 0.0F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelBanner();
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"slate", "stand", "top"};
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelBanner)) {
         return null;
      } else {
         ModelBanner modelBanner = (ModelBanner)modelBase;
         return str.equals("slate") ? modelBanner.bannerSlate : (str.equals("stand") ? modelBanner.bannerStand : (str.equals("top") ? modelBanner.bannerTop : null));
      }
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      TileEntityRendererDispatcher instance = TileEntityRendererDispatcher.instance;
      Object specialRendererByClass = instance.getSpecialRendererByClass(TileEntityBanner.class);
      if (!(specialRendererByClass instanceof TileEntityBannerRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)specialRendererByClass).getEntityClass() == null) {
            specialRendererByClass = new TileEntityBannerRenderer();
            ((TileEntitySpecialRenderer)specialRendererByClass).setRendererDispatcher(instance);
         }

         ((TileEntityBannerRenderer)specialRendererByClass).bannerModel = (ModelBanner)modelBase;
         return (IEntityRenderer)specialRendererByClass;
      }
   }
}
