package client.util;

import client.render.WorldRenderer;
import client.setting.GlStateManagerSType;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityAirportal;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/environment/end_sky.png");
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/entity/end_portal.png");
   public static final Random random = new Random(31100L);
   public FloatBuffer floatBuffer = VectorMatcher.resolveFloatBuffer(16);

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntityAirportal((TileEntityAirportal)tileEntity, posX, posY, posZ, factor, count);
   }

   private FloatBuffer resolveFloatBuffer(float posX, float posX2, float posY, float posZ) {
      ((Buffer)this.floatBuffer).clear();
      this.floatBuffer.put(posX).put(posX2).put(posY).put(posZ);
      ((Buffer)this.floatBuffer).flip();
      return this.floatBuffer;
   }

   public void applyTileEntityAirportal(TileEntityAirportal tileEntityAirportal, double posX, double posY, double posZ, float factor, int count) {
      if (!BuildManager.canExecute() || !RotationBridge.matchesTileEntityAirportal(tileEntityAirportal, posX, posY, posZ, factor, count, 0.75F)) {
         float value = (float)this.standardMapEntry.value2;
         float value2 = (float)this.standardMapEntry.value6;
         float value3 = (float)this.standardMapEntry.value;
         GlStateManager.applyState3();
         random.setSeed(31100L);
         float factor2 = 0.75F;

         for (int index = 0; index < 16; index++) {
            GlStateManager.pushMatrix();
            float factor3 = (float)(16 - index);
            float factor4 = 0.0625F;
            float factor5 = 1.0F / (factor3 + 1.0F);
            if (index == 0) {
               this.applyResourceLocation2(resourceLocation3);
               factor5 = 0.1F;
               factor3 = 65.0F;
               factor4 = 0.125F;
               GlStateManager.disableBlend();
               GlStateManager.glStateAction3(770, 771);
            }

            if (index >= 1) {
               this.applyResourceLocation2(resourceLocation2);
            }

            if (index == 1) {
               GlStateManager.disableBlend();
               GlStateManager.glStateAction3(1, 1);
               factor4 = 0.5F;
            }

            float factor6 = (float)(-(posY + (double)factor2));
            float factor7 = factor6 + (float)AuxiliaryIntBufferEntry.getVec3().yCoord;
            float factor8 = factor6 + factor3 + (float)AuxiliaryIntBufferEntry.getVec3().yCoord;
            float factor9 = factor7 / factor8;
            float factor10 = (float)(posY + (double)factor2) + factor9;
            GlStateManager.translate(value, factor10, value3);
            GlStateManager.applyGlStateManagerSType(GlStateManagerSType.S, 9217);
            GlStateManager.applyGlStateManagerSType(GlStateManagerSType.T, 9217);
            GlStateManager.applyGlStateManagerSType(GlStateManagerSType.R, 9217);
            GlStateManager.applyGlStateManagerSType(GlStateManagerSType.Q, 9216);
            GlStateManager.glStateAction(GlStateManagerSType.S, 9473, this.resolveFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
            GlStateManager.glStateAction(GlStateManagerSType.T, 9473, this.resolveFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
            GlStateManager.glStateAction(GlStateManagerSType.R, 9473, this.resolveFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
            GlStateManager.glStateAction(GlStateManagerSType.Q, 9474, this.resolveFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
            GlStateManager.glStateAction2(GlStateManagerSType.S);
            GlStateManager.glStateAction2(GlStateManagerSType.T);
            GlStateManager.glStateAction2(GlStateManagerSType.R);
            GlStateManager.glStateAction2(GlStateManagerSType.Q);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0F, (float)(Minecraft.getTimestamp() % 700000L) / 700000.0F, 0.0F);
            GlStateManager.translate3f3(factor4, factor4, factor4);
            GlStateManager.translate(0.5F, 0.5F, 0.0F);
            GlStateManager.rotate((float)(index * index * 4321 + index * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(-0.5F, -0.5F, 0.0F);
            GlStateManager.translate(-value, -value3, -value2);
            float factor11 = factor6 + (float)AuxiliaryIntBufferEntry.getVec3().yCoord;
            GlStateManager.translate(
               (float)AuxiliaryIntBufferEntry.getVec3().xCoord * factor3 / factor11, (float)AuxiliaryIntBufferEntry.getVec3().zCoord * factor3 / factor11, -value2
            );
            Tessellator tessellator = Tessellator.getTessellator();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
            float factor12 = (random.nextFloat() * 0.5F + 0.1F) * factor5;
            float factor13 = (random.nextFloat() * 0.5F + 0.4F) * factor5;
            float factor14 = (random.nextFloat() * 0.5F + 0.5F) * factor5;
            if (index == 0) {
               factor12 = factor13 = factor14 = 1.0F * factor5;
            }

            worldRenderer.getWorldRenderer(posX, posY + (double)factor2, posZ).resolveWorldRenderer(factor12, factor13, factor14, 1.0F).updateState();
            worldRenderer.getWorldRenderer(posX, posY + (double)factor2, posZ + 1.0).resolveWorldRenderer(factor12, factor13, factor14, 1.0F).updateState();
            worldRenderer.getWorldRenderer(posX + 1.0, posY + (double)factor2, posZ + 1.0).resolveWorldRenderer(factor12, factor13, factor14, 1.0F).updateState();
            worldRenderer.getWorldRenderer(posX + 1.0, posY + (double)factor2, posZ).resolveWorldRenderer(factor12, factor13, factor14, 1.0F).updateState();
            tessellator.updateState();
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
            this.applyResourceLocation2(resourceLocation3);
         }

         GlStateManager.enableBlend();
         GlStateManager.glStateAction(GlStateManagerSType.S);
         GlStateManager.glStateAction(GlStateManagerSType.T);
         GlStateManager.glStateAction(GlStateManagerSType.R);
         GlStateManager.glStateAction(GlStateManagerSType.Q);
         GlStateManager.applyState13();
      }
   }
}
