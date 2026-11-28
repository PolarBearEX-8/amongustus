package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TileEntityEnchantmentTableRenderer extends TileEntitySpecialRenderer {
   private static final ResourceLocation TEXTURE_BOOK = new ResourceLocation("textures/entity/enchanting_table_book.png");
   public ModelBook modelBook = new ModelBook();

   @Override
   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntityEnchantmentTable)tileEntity, d, d2, d3, f, n);
   }

   public void renderTileEntityAt(TileEntityEnchantmentTable table, double d, double d2, double d3, float f, int n) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d + 0.5F, (float)d2 + 0.75F, (float)d3 + 0.5F);
      float f2 = (float)table.tickCount + f;
      GlStateManager.translate(0.0F, 0.1F + MathHelper.sin(f2 * 0.1F) * 0.01F, 0.0F);
      float f3 = table.bookRotation - table.bookRotationPrev;

      while (f3 >= (float) Math.PI) {
         f3 -= (float) (Math.PI * 2);
      }

      while (f3 < (float) -Math.PI) {
         f3 += (float) (Math.PI * 2);
      }

      GlStateManager.rotate(-(table.bookRotationPrev + f3 * f) * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
      this.bindTexture(TEXTURE_BOOK);
      float f5 = table.pageFlipPrev + (table.pageFlip - table.pageFlipPrev) * f + 0.25F;
      float f6 = table.pageFlipPrev + (table.pageFlip - table.pageFlipPrev) * f + 0.75F;
      f5 = (f5 - (float)MathHelper.truncateDoubleToInt((double)f5)) * 1.6F - 0.3F;
      f6 = (f6 - (float)MathHelper.truncateDoubleToInt((double)f6)) * 1.6F - 0.3F;
      if (f5 < 0.0F) {
         f5 = 0.0F;
      }

      if (f6 < 0.0F) {
         f6 = 0.0F;
      }

      if (f5 > 1.0F) {
         f5 = 1.0F;
      }

      if (f6 > 1.0F) {
         f6 = 1.0F;
      }

      float f7 = table.bookSpreadPrev + (table.bookSpread - table.bookSpreadPrev) * f;
      GlStateManager.enableCull();
      this.modelBook.render((Entity)null, f2, f5, f6, f7, 0.0F, 0.0625F, true);
      GlStateManager.popMatrix();
   }
}
