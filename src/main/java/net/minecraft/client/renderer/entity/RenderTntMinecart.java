package net.minecraft.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;

public class RenderTntMinecart extends RenderMinecart {
   public RenderTntMinecart(RenderManager renderManager2) {
      super(renderManager2);
   }

   public void renderCartContents2(EntityMinecartTNT entityMinecartTNT, float f, IBlockState state) {
      int fuseTicks = entityMinecartTNT.getFuseTicks();
      if (fuseTicks > -1 && (float)fuseTicks - f + 1.0F < 10.0F) {
         float f2 = 1.0F - ((float)fuseTicks - f + 1.0F) / 10.0F;
         f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
         f2 *= f2;
         f2 *= f2;
         float f3 = 1.0F + f2 * 0.3F;
         GlStateManager.scale(f3, f3, f3);
      }

      super.renderCartContents(entityMinecartTNT, f, state);
      if (fuseTicks > -1 && fuseTicks / 5 % 2 == 0) {
         BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
         GlStateManager.disableTexture2D();
         GlStateManager.disableLighting();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 772);
         GlStateManager.color(1.0F, 1.0F, 1.0F, (1.0F - ((float)fuseTicks - f + 1.0F) / 100.0F) * 0.8F);
         GlStateManager.pushMatrix();
         blockRendererDispatcher.renderBlockBrightness(Blocks.tnt.getDefaultState(), 1.0F);
         GlStateManager.popMatrix();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
         GlStateManager.enableLighting();
         GlStateManager.enableTexture2D();
      }
   }

   @Override
   public void renderCartContents(EntityMinecart entityMinecart, float f, IBlockState state) {
      this.renderCartContents2((EntityMinecartTNT)entityMinecart, f, state);
   }
}
