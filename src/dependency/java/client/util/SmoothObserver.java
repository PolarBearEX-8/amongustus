package client.util;

import client.render.RenderManager;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.GoldenCrystal;
import net.minecraft.util.MathHelper;

public class SmoothObserver extends ExtraResourceLocationEntryAdapter {
   @Override
   public void applyEntityMinecart(EntityMinecart entityMinecart, float factor, IBlockState blockState) {
      this.applyVectorCalculator((VectorCalculator)entityMinecart, factor, blockState);
   }

   public void applyVectorCalculator(VectorCalculator vectorCalculator, float factor, IBlockState blockState) {
      int count = vectorCalculator.getCount14();
      if (count > -1 && (float)count - factor + 1.0F < 10.0F) {
         float factor2 = MathHelper.clamp_float(1.0F - ((float)count - factor + 1.0F) / 10.0F, 0.0F, 1.0F);
         float factor3 = factor2 * factor2;
         float factor4 = factor3 * factor3;
         float factor5 = 1.0F + factor4 * 0.3F;
         GlStateManager.translate3f3(factor5, factor5, factor5);
      }

      super.applyEntityMinecart(vectorCalculator, factor, blockState);
      if (count > -1 && count / 5 % 2 == 0) {
         GoldenCrystal goldenCrystal = Minecraft.getMinecraft().getGoldenCrystal();
         GlStateManager.enableTexture2D();
         GlStateManager.applyState3();
         GlStateManager.disableBlend();
         GlStateManager.glStateAction3(770, 772);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, (1.0F - ((float)count - factor + 1.0F) / 100.0F) * 0.8F);
         GlStateManager.pushMatrix();
         goldenCrystal.applyIBlockState(Blocks.block135.getDefaultState(), 1.0F);
         GlStateManager.popMatrix();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
         GlStateManager.applyState13();
         GlStateManager.disableTexture2D();
      }
   }

   public SmoothObserver(RenderManager renderManager) {
      super(renderManager);
   }
}
