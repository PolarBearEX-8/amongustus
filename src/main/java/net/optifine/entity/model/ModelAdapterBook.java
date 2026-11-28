package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityEnchantmentTable;

public class ModelAdapterBook extends ModelAdapter {

   public ModelAdapterBook() {
      super(TileEntityEnchantmentTable.class, "book", 0.0F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelBook();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelBook)) {
         return null;
      } else {
         ModelBook modelBook = (ModelBook)modelBase;
         return str.equals("cover_right")
            ? modelBook.coverRight
            : (
               str.equals("cover_left")
                  ? modelBook.coverLeft
                  : (
                     str.equals("pages_right")
                        ? modelBook.pagesRight
                        : (
                           str.equals("pages_left")
                              ? modelBook.pagesLeft
                              : (
                                 str.equals("flipping_page_right")
                                    ? modelBook.flippingPageRight
                                    : (str.equals("flipping_page_left") ? modelBook.flippingPageLeft : (str.equals("book_spine") ? modelBook.bookSpine : null))
                              )
                        )
                  )
            );
      }
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      TileEntityRendererDispatcher instance = TileEntityRendererDispatcher.instance;
      Object specialRendererByClass = instance.getSpecialRendererByClass(TileEntityEnchantmentTable.class);
      if (!(specialRendererByClass instanceof TileEntityEnchantmentTableRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)specialRendererByClass).getEntityClass() == null) {
            specialRendererByClass = new TileEntityEnchantmentTableRenderer();
            ((TileEntitySpecialRenderer)specialRendererByClass).setRendererDispatcher(instance);
         }

         ((TileEntityEnchantmentTableRenderer)specialRendererByClass).modelBook = (ModelBook)modelBase;
         return (IEntityRenderer)specialRendererByClass;
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"cover_right", "cover_left", "pages_right", "pages_left", "flipping_page_right", "flipping_page_left", "book_spine"};
   }
}
