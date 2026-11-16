package client.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.DiamondDust;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchanttable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class InternalResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/entity/enchanting_table_book.png");
   public DiamondDust diamondDust = new DiamondDust();

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntityEnchanttable((TileEntityEnchanttable)tileEntity, posX, posY, posZ, factor, count);
   }

   public void applyTileEntityEnchanttable(TileEntityEnchanttable tileEntityEnchanttable, double posX, double posY, double posZ, float factor, int count) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX + 0.5F, (float)posY + 0.75F, (float)posZ + 0.5F);
      float factor2 = (float)tileEntityEnchanttable.count2 + factor;
      GlStateManager.translate(0.0F, 0.1F + MathHelper.abs(factor2 * 0.1F) * 0.01F, 0.0F);
      float factor3 = tileEntityEnchanttable.factor4 - tileEntityEnchanttable.factor;

      while (factor3 >= (float) Math.PI) {
         factor3 -= (float) (Math.PI * 2);
      }

      while (factor3 < (float) -Math.PI) {
         factor3 += (float) (Math.PI * 2);
      }

      GlStateManager.rotate(-(tileEntityEnchanttable.factor + factor3 * factor) * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
      this.applyResourceLocation2(resourceLocation2);
      float factor4 = tileEntityEnchanttable.factor6 + (tileEntityEnchanttable.factor5 - tileEntityEnchanttable.factor6) * factor + 0.25F;
      float factor5 = tileEntityEnchanttable.factor6 + (tileEntityEnchanttable.factor5 - tileEntityEnchanttable.factor6) * factor + 0.75F;
      float factor6 = (factor4 - (float)MathHelper.floor_double((double)factor4)) * 1.6F - 0.3F;
      float factor7 = (factor5 - (float)MathHelper.floor_double((double)factor5)) * 1.6F - 0.3F;
      if (factor6 < 0.0F) {
         factor6 = 0.0F;
      }

      if (factor7 < 0.0F) {
         factor7 = 0.0F;
      }

      if (factor6 > 1.0F) {
         factor6 = 1.0F;
      }

      if (factor7 > 1.0F) {
         factor7 = 1.0F;
      }

      float factor8 = tileEntityEnchanttable.factor2 + (tileEntityEnchanttable.factor7 - tileEntityEnchanttable.factor2) * factor;
      GlStateManager.enableLighting();
      this.diamondDust.applyEntity((Entity)null, factor2, factor6, factor7, factor8, 0.0F, 0.0625F, true);
      GlStateManager.popMatrix();
   }
}
