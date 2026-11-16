package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.WorldRenderer;
import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SpecialResourceLocationEntryAdapter extends EntitySkinLocation {
   public ResourceLocation resolveResourceLocationByHUIHRVManager(HUIHRVManager hUIHRVManager) {
      return null;
   }

   public void applyHUIHRVManager(HUIHRVManager hUIHRVManager, double posX, double posY, double posZ, float factor, float factor2) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.enableTexture2D();
      GlStateManager.applyState3();
      GlStateManager.disableBlend();
      GlStateManager.glStateAction3(770, 1);
      double[] offsetArray = new double[8];
      double[] offsetArray2 = new double[8];
      double offset = 0.0;
      double offset2 = 0.0;
      Random random = new Random(hUIHRVManager.timestamp2);

      for (int index = 7; index >= 0; index--) {
         offsetArray[index] = offset;
         offsetArray2[index] = offset2;
         offset += (double)(random.nextInt(11) - 5);
         offset2 += (double)(random.nextInt(11) - 5);
      }

      for (int index2 = 0; index2 < 4; index2++) {
         Random random2 = new Random(hUIHRVManager.timestamp2);

         for (int index3 = 0; index3 < 3; index3++) {
            int count = 7;
            int count2 = 0;
            if (index3 > 0) {
               count = 7 - index3;
            }

            if (index3 > 0) {
               count2 = count - 2;
            }

            double offset3 = offsetArray[count] - offset;

            for (double offset4 = offsetArray2[count] - offset2; count >= count2; count--) {
               double offset5 = offset3;
               double offset6 = offset4;
               if (index3 == 0) {
                  offset3 += (double)(random2.nextInt(11) - 5);
                  offset4 += (double)(random2.nextInt(11) - 5);
               } else {
                  offset3 += (double)(random2.nextInt(31) - 15);
                  offset4 += (double)(random2.nextInt(31) - 15);
               }

               worldRenderer.updateAuxiliaryLoggerEntry(5, DefaultVertexFormats.auxiliaryLoggerEntry3);
               double offset7 = 0.1 + (double)index2 * 0.2;
               if (index3 == 0) {
                  offset7 *= (double)count * 0.1 + 1.0;
               }

               double offset8 = 0.1 + (double)index2 * 0.2;
               if (index3 == 0) {
                  offset8 *= (double)(count - 1) * 0.1 + 1.0;
               }

               for (int index4 = 0; index4 < 5; index4++) {
                  double offset9 = posX + 0.5 - offset7;
                  double offset10 = posZ + 0.5 - offset7;
                  if (index4 == 1 || index4 == 2) {
                     offset9 += offset7 * 2.0;
                  }

                  if (index4 == 2 || index4 == 3) {
                     offset10 += offset7 * 2.0;
                  }

                  double offset11 = posX + 0.5 - offset8;
                  double offset12 = posZ + 0.5 - offset8;
                  if (index4 == 1 || index4 == 2) {
                     offset11 += offset8 * 2.0;
                  }

                  if (index4 == 2 || index4 == 3) {
                     offset12 += offset8 * 2.0;
                  }

                  worldRenderer.getWorldRenderer(offset11 + offset3, posY + (double)(count * 16), offset12 + offset4)
                     .resolveWorldRenderer(0.45F, 0.45F, 0.5F, 0.3F)
                     .updateState();
                  worldRenderer.getWorldRenderer(offset9 + offset5, posY + (double)((count + 1) * 16), offset10 + offset6)
                     .resolveWorldRenderer(0.45F, 0.45F, 0.5F, 0.3F)
                     .updateState();
               }

               tessellator.updateState();
            }
         }
      }

      GlStateManager.enableBlend();
      GlStateManager.applyState13();
      GlStateManager.disableTexture2D();
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyHUIHRVManager((HUIHRVManager)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByHUIHRVManager((HUIHRVManager)entity);
   }

   public SpecialResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
   }
}
