package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.ResourceLocation;

public class RenderLightningBolt extends Render {
   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityLightningBolt)entity, d, d2, d3, f, f2);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityLightningBolt)entity);
   }

   public RenderLightningBolt(RenderManager renderManager2) {
      super(renderManager2);
   }

   public void doRender(EntityLightningBolt entityLightningBolt, double d, double d2, double d3, float f, float f2) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GlStateManager.disableTexture2D();
      GlStateManager.disableLighting();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 1);
      double[] doubles = new double[8];
      double[] doubles2 = new double[8];
      double d4 = 0.0;
      double d5 = 0.0;
      Random rand = new Random(entityLightningBolt.boltVertex);

      for (int i = 7; i >= 0; i--) {
         doubles[i] = d4;
         doubles2[i] = d5;
         d4 += (double)(rand.nextInt(11) - 5);
         d5 += (double)(rand.nextInt(11) - 5);
      }

      for (int j = 0; j < 4; j++) {
         Random rand2 = new Random(entityLightningBolt.boltVertex);

         for (int k = 0; k < 3; k++) {
            int n = 7;
            int n2 = 0;
            if (k > 0) {
               n = 7 - k;
            }

            if (k > 0) {
               n2 = n - 2;
            }

            double d6 = doubles[n] - d4;
            double d7 = doubles2[n] - d5;

            for (int m = n; m >= n2; m--) {
               double d8 = d6;
               double d9 = d7;
               if (k == 0) {
                  d6 += (double)(rand2.nextInt(11) - 5);
                  d7 += (double)(rand2.nextInt(11) - 5);
               } else {
                  d6 += (double)(rand2.nextInt(31) - 15);
                  d7 += (double)(rand2.nextInt(31) - 15);
               }

               worldRenderer.begin(5, DefaultVertexFormats.POSITION_COLOR);
               float f3 = 0.5F;
               float f4 = 0.45F;
               float f5 = 0.45F;
               float f6 = 0.5F;
               double d10 = 0.1 + (double)j * 0.2;
               if (k == 0) {
                  d10 *= (double)m * 0.1 + 1.0;
               }

               double d11 = 0.1 + (double)j * 0.2;
               if (k == 0) {
                  d11 *= (double)(m - 1) * 0.1 + 1.0;
               }

               for (int idx = 0; idx < 5; idx++) {
                  double d12 = d + 0.5 - d10;
                  double d13 = d3 + 0.5 - d10;
                  if (idx == 1 || idx == 2) {
                     d12 += d10 * 2.0;
                  }

                  if (idx == 2 || idx == 3) {
                     d13 += d10 * 2.0;
                  }

                  double d14 = d + 0.5 - d11;
                  double d15 = d3 + 0.5 - d11;
                  if (idx == 1 || idx == 2) {
                     d14 += d11 * 2.0;
                  }

                  if (idx == 2 || idx == 3) {
                     d15 += d11 * 2.0;
                  }

                  worldRenderer.pos(d14 + d6, d2 + (double)(m * 16), d15 + d7).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
                  worldRenderer.pos(d12 + d8, d2 + (double)((m + 1) * 16), d13 + d9).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
               }

               instance.draw();
            }
         }
      }

      GlStateManager.disableBlend();
      GlStateManager.enableLighting();
      GlStateManager.enableTexture2D();
   }

   public ResourceLocation getEntityTexture(EntityLightningBolt entityLightningBolt) {
      return null;
   }
}
