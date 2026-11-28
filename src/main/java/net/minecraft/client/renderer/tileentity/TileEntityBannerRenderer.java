package net.minecraft.client.renderer.tileentity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.LayeredColorMaskTexture;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TileEntityBannerRenderer extends TileEntitySpecialRenderer {
   private static final Map DESIGNS = Maps.newHashMap();
   private static final ResourceLocation BANNERTEXTURES = new ResourceLocation("textures/entity/banner_base.png");
   public ModelBanner bannerModel = new ModelBanner();

   private ResourceLocation getBannerResourceLocation(TileEntityBanner tileEntityBanner) {
      String str = tileEntityBanner.getPatternResourceLocation();
      if (str.isEmpty()) {
         return null;
      } else {
         TileEntityBannerRenderer.TimedBannerTexture timedBannerTexture = (TileEntityBannerRenderer.TimedBannerTexture)DESIGNS.get(str);
         if (timedBannerTexture == null) {
            if (DESIGNS.size() >= 256) {
               long l = System.currentTimeMillis();
               Iterator it = DESIGNS.keySet().iterator();

               while (it.hasNext()) {
                  String str2 = (String)it.next();
                  TileEntityBannerRenderer.TimedBannerTexture timedBannerTexture2 = (TileEntityBannerRenderer.TimedBannerTexture)DESIGNS.get(str2);
                  if (l - timedBannerTexture2.systemTime > 60000L) {
                     Minecraft.getMinecraft().getTextureManager().deleteTexture(timedBannerTexture2.bannerTexture);
                     it.remove();
                  }
               }

               if (DESIGNS.size() >= 256) {
                  return null;
               }
            }

            List patternList = tileEntityBanner.getPatternList();
            List colorList = tileEntityBanner.getColorList();
            ArrayList list = Lists.newArrayList();

            for (TileEntityBanner.EnumBannerPattern enumBannerPattern : (Iterable<TileEntityBanner.EnumBannerPattern>)(patternList)) {
               list.add("textures/entity/banner/" + enumBannerPattern.getPatternName() + ".png");
            }

            timedBannerTexture = new TileEntityBannerRenderer.TimedBannerTexture(null);
            timedBannerTexture.bannerTexture = new ResourceLocation(str);
            Minecraft.getMinecraft().getTextureManager().loadTexture(timedBannerTexture.bannerTexture, new LayeredColorMaskTexture(BANNERTEXTURES, list, colorList));
            DESIGNS.put(str, timedBannerTexture);
         }

         timedBannerTexture.systemTime = System.currentTimeMillis();
         return timedBannerTexture.bannerTexture;
      }
   }

   @Override
   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntityBanner)tileEntity, d, d2, d3, f, n);
   }

   public void renderTileEntityAt(TileEntityBanner tileEntityBanner, double d, double d2, double d3, float f, int n) {
      boolean flag = tileEntityBanner.getWorldObj() != null;
      boolean flag2 = !flag || tileEntityBanner.getBlockType() == Blocks.standing_banner;
      int n2 = flag ? tileEntityBanner.getBlockMetadata() : 0;
      long l = flag ? tileEntityBanner.getWorldObj().getTotalWorldTime() : 0L;
      GlStateManager.pushMatrix();
      float f2 = 0.6666667F;
      if (flag2) {
         GlStateManager.translate((float)d + 0.5F, (float)d2 + 0.75F * f2, (float)d3 + 0.5F);
         GlStateManager.rotate(-((float)(n2 * 360) / 16.0F), 0.0F, 1.0F, 0.0F);
         this.bannerModel.bannerStand.showModel = true;
      } else {
         float f4 = 0.0F;
         if (n2 == 2) {
            f4 = 180.0F;
         }

         if (n2 == 4) {
            f4 = 90.0F;
         }

         if (n2 == 5) {
            f4 = -90.0F;
         }

         GlStateManager.translate((float)d + 0.5F, (float)d2 - 0.25F * f2, (float)d3 + 0.5F);
         GlStateManager.rotate(-f4, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
         this.bannerModel.bannerStand.showModel = false;
      }

      BlockPos pos = tileEntityBanner.getPos();
      float f5 = (float)(pos.getX() * 7 + pos.getY() * 9 + pos.getZ() * 13) + (float)l + f;
      this.bannerModel.bannerSlate.rotateAngleX = (-0.0125F + 0.01F * MathHelper.cos(f5 * (float) Math.PI * 0.02F)) * (float) Math.PI;
      GlStateManager.enableRescaleNormal();
      ResourceLocation bannerResourceLocation = this.getBannerResourceLocation(tileEntityBanner);
      if (bannerResourceLocation != null) {
         this.bindTexture(bannerResourceLocation);
         GlStateManager.pushMatrix();
         GlStateManager.scale(f2, -f2, -f2);
         this.bannerModel.renderBanner();
         GlStateManager.popMatrix();
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }


   public static class InnerEntry {
   }

   public static class TimedBannerTexture {
      public long systemTime;
      public ResourceLocation bannerTexture;

      public TimedBannerTexture(TileEntityBannerRenderer.InnerEntry obj) {
         this();
      }

      private TimedBannerTexture() {
      }
   }
}
