package client.util;

import client.event.PreMotionConsumer;
import client.render.EntityEspRenderer;
import client.render.WorldRenderer;
import client.setting.BlockSolidType;
import java.nio.IntBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityRenderer;
import net.minecraft.item.ItemRenderer;
import net.minecraft.tileentity.TileEntityAirportal;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RotationBridge {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/end_portal.png");

   public static void applyEntityRenderer(EntityRenderer entityRenderer, int count, float factor, long value) {
      if (PreEndHandManager.count53 > 0 && --PreEndHandManager.count57 <= 0) {
         Minecraft mc = Minecraft.getMinecraft();
         mc.uNKNOWNManager.applyText("shadow pass");
         RenderGlobal renderGlobal = mc.renderGlobal;
         PreEndHandManager.enabled12 = true;
         PreEndHandManager.count57 = PreEndHandManager.count49;
         PreEndHandManager.count18 = mc.gameSettings.indexOffset64;
         mc.gameSettings.indexOffset64 = 1;
         PreEndHandManager.computeCount3("pre shadow");
         GL11.glMatrixMode(5889);
         GL11.glPushMatrix();
         GL11.glMatrixMode(5888);
         GL11.glPushMatrix();
         mc.uNKNOWNManager.applyText("shadow clear");
         EXTFramebufferObject.glBindFramebufferEXT(36160, PreEndHandManager.count51);
         PreEndHandManager.computeCount3("shadow bind sfb");
         mc.uNKNOWNManager.applyText("shadow camera");
         entityRenderer.applyFloatByFloat(factor, 2);
         PreEndHandManager.applyFloat2(factor);
         PreEndHandManager.computeCount3("shadow camera");
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager25);
         GL20.glDrawBuffers(PreEndHandManager.intBuffer6);
         PreEndHandManager.computeCount3("shadow drawbuffers");
         GL11.glReadBuffer(0);
         PreEndHandManager.computeCount3("shadow readbuffer");
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, PreEndHandManager.intBuffer2.get(0), 0);
         if (PreEndHandManager.count8 != 0) {
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, PreEndHandManager.intBuffer5.get(0), 0);
         }

         PreEndHandManager.computeCountByText("shadow fb");
         GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glClear(PreEndHandManager.count8 != 0 ? 16640 : 256);
         PreEndHandManager.computeCount3("shadow clear");
         mc.uNKNOWNManager.applyText("shadow frustum");
         CustomFloatArrayEntry customFloatArrayEntry = CustomFloatArrayEntryImpl.getCustomFloatArrayEntry();
         mc.uNKNOWNManager.applyText("shadow culling");
         EntityEspRenderer entityEspRenderer = new EntityEspRenderer(customFloatArrayEntry);
         Entity renderViewEntity = mc.getRenderViewEntity();
         double offset = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * (double)factor;
         double offset2 = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)factor;
         double offset3 = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * (double)factor;
         entityEspRenderer.processDouble(offset, offset2, offset3);
         GlStateManager.glShadeModel(7425);
         GlStateManager.disableDepth();
         GlStateManager.glDepthFunc(515);
         GlStateManager.depthMask(true);
         GlStateManager.applyFlag(true, true, true, true);
         GlStateManager.disableAlpha();
         mc.uNKNOWNManager.applyText("shadow prepareterrain");
         mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
         mc.uNKNOWNManager.applyText("shadow setupterrain");
         int count2 = entityRenderer.count5++;
         renderGlobal.applyEntityByEntity(renderViewEntity, (double)factor, entityEspRenderer, count2, mc.thePlayer.isActive23());
         mc.uNKNOWNManager.applyText("shadow updatechunks");
         mc.uNKNOWNManager.applyText("shadow terrain");
         GlStateManager.matrixMode(5888);
         GlStateManager.pushMatrix();
         GlStateManager.applyState10();
         renderGlobal.computeCount(BlockSolidType.SOLID, (double)factor, 2, renderViewEntity);
         PreEndHandManager.computeCount3("shadow terrain solid");
         GlStateManager.applyState14();
         renderGlobal.computeCount(BlockSolidType.CUTOUT_MIPPED, (double)factor, 2, renderViewEntity);
         PreEndHandManager.computeCount3("shadow terrain cutoutmipped");
         mc.getTextureManager().get(ListenerAdapter27.resourceLocation).applyFlag(false, false);
         renderGlobal.computeCount(BlockSolidType.CUTOUT, (double)factor, 2, renderViewEntity);
         PreEndHandManager.computeCount3("shadow terrain cutout");
         mc.getTextureManager().get(ListenerAdapter27.resourceLocation).updateState();
         GlStateManager.glShadeModel(7424);
         GlStateManager.alphaFunc(516, 0.1F);
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         mc.uNKNOWNManager.applyText("shadow entities");
         renderGlobal.applyEntity(renderViewEntity, entityEspRenderer, factor);
         PreEndHandManager.computeCount3("shadow entities");
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         GlStateManager.enableBlend();
         GlStateManager.enableLighting();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.alphaFunc(516, 0.1F);
         if (PreEndHandManager.count53 >= 2) {
            GlStateManager.enableState5(33989);
            PreEndHandManager.computeCount3("pre copy shadow depth");
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, PreEndHandManager.count10, PreEndHandManager.count6);
            PreEndHandManager.computeCount3("copy shadow depth");
            GlStateManager.enableState5(33984);
         }

         GlStateManager.enableBlend();
         GlStateManager.depthMask(true);
         mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
         GlStateManager.glShadeModel(7425);
         PreEndHandManager.computeCount3("shadow pre-translucent");
         GL20.glDrawBuffers(PreEndHandManager.intBuffer6);
         PreEndHandManager.computeCount3("shadow drawbuffers pre-translucent");
         PreEndHandManager.computeCountByText("shadow pre-translucent");
         if (PreEndHandManager.isActive11()) {
            mc.uNKNOWNManager.applyText("shadow translucent");
            renderGlobal.computeCount(BlockSolidType.TRANSLUCENT, (double)factor, 2, renderViewEntity);
            PreEndHandManager.computeCount3("shadow translucent");
         }

         GlStateManager.glShadeModel(7424);
         GlStateManager.depthMask(true);
         GlStateManager.enableLighting();
         GlStateManager.enableBlend();
         GL11.glFlush();
         PreEndHandManager.computeCount3("shadow flush");
         PreEndHandManager.enabled12 = false;
         mc.gameSettings.indexOffset64 = PreEndHandManager.count18;
         mc.uNKNOWNManager.applyText("shadow postprocess");
         if (PreEndHandManager.enabled8) {
            if (PreEndHandManager.count53 >= 1) {
               if (PreEndHandManager.enabledArray2[0]) {
                  GlStateManager.enableState5(33988);
                  GlStateManager.setActiveTexture(PreEndHandManager.intBuffer2.get(0));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, PreEndHandManager.enabledArray5[0] ? 9984 : 9987);
               }

               if (PreEndHandManager.count53 >= 2 && PreEndHandManager.enabledArray2[1]) {
                  GlStateManager.enableState5(33989);
                  GlStateManager.setActiveTexture(PreEndHandManager.intBuffer2.get(1));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, PreEndHandManager.enabledArray5[1] ? 9984 : 9987);
               }

               GlStateManager.enableState5(33984);
            }

            if (PreEndHandManager.count8 >= 1) {
               if (PreEndHandManager.enabledArray3[0]) {
                  GlStateManager.enableState5(33997);
                  GlStateManager.setActiveTexture(PreEndHandManager.intBuffer5.get(0));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, PreEndHandManager.enabledArray6[0] ? 9984 : 9987);
               }

               if (PreEndHandManager.count8 >= 2 && PreEndHandManager.enabledArray3[1]) {
                  GlStateManager.enableState5(33998);
                  GlStateManager.setActiveTexture(PreEndHandManager.intBuffer5.get(1));
                  GL30.glGenerateMipmap(3553);
                  GL11.glTexParameteri(3553, 10241, PreEndHandManager.enabledArray6[1] ? 9984 : 9987);
               }

               GlStateManager.enableState5(33984);
            }
         }

         PreEndHandManager.computeCount3("shadow postprocess");
         EXTFramebufferObject.glBindFramebufferEXT(36160, PreEndHandManager.count33);
         GL11.glViewport(0, 0, PreEndHandManager.count12, PreEndHandManager.count15);
         PreEndHandManager.intBuffer3 = null;
         mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager19);
         GL11.glMatrixMode(5888);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5889);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5888);
         PreEndHandManager.computeCount3("shadow end");
      }
   }

   public static void updateState11() {
      if (PreEndHandManager.enabled31) {
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager30);
      }
   }

   public static void applyBlockSolidTypeByBlockSolidType(BlockSolidType blockSolidType) {
      if (OpenGlHelper.isActive()) {
         GL11.glDisableClientState(32885);
         GL20.glDisableVertexAttribArray(PreEndHandManager.count34);
         GL20.glDisableVertexAttribArray(PreEndHandManager.count11);
         GL20.glDisableVertexAttribArray(PreEndHandManager.count39);
      }

      if (PreEndHandManager.matchesBlockSolidType(blockSolidType)) {
         GlStateManager.enableLighting();
      }
   }

   public static void applyEntityRendererByEntityRenderer(EntityRenderer entityRenderer, float factor, int count) {
      if (!PreEndHandManager.enabled12 && !PreEndHandManager.isActiveAsFlag()) {
         PreEndHandManager.updateState26();
         GlStateManager.disableBlend();
         PreEndHandManager.setEnabledByFlag(true);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         PreEndHandManager.applyFlag(PreEndHandManager.isEnabled43(), PreEndHandManager.isEnabled32());
         entityRenderer.applyFloat(factor, count, true, false, true);
         PreEndHandManager.updateState28();
         PreEndHandManager.applyFlagByFlag(true, true);
         PreEndHandManager.applyFlag(false, false);
      }
   }

   public static void updateState8() {
      if (PreEndHandManager.enabled31) {
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager30);
      }
   }

   public static void updateState4() {
      if (PreEndHandManager.enabled31) {
         if (PreEndHandManager.count56 >= 2) {
            GlStateManager.enableState5(33995);
            PreEndHandManager.computeCount3("pre copy depth");
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, PreEndHandManager.count12, PreEndHandManager.count15);
            PreEndHandManager.computeCount3("copy depth");
            GlStateManager.enableState5(33984);
         }

         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager10);
      }
   }

   public static void updateState2() {
      GlStateManager.enableBlend();
   }

   public static void updateState13() {
      if (PreEndHandManager.enabled31) {
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager19);
      }
   }

   public static void updateState7() {
   }

   public static void updateState12() {
      if (PreEndHandManager.enabled31) {
         GlStateManager.depthMask(true);
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager30);
      }
   }

   public static void applyRenderGlobal(RenderGlobal renderGlobal, Entity entity, double offset, PreMotionConsumer preMotionConsumer, int count, boolean flag) {
      renderGlobal.applyEntityByEntity(entity, offset, preMotionConsumer, count, flag);
   }

   public static void updateState9() {
      PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager9);
   }

   public static void applyEntityRenderer2(EntityRenderer entityRenderer, float factor, int count) {
      if (!PreEndHandManager.enabled12) {
         PreEndHandManager.updateState49();
         entityRenderer.applyFloat(factor, count, false, true, false);
         PreEndHandManager.updateState61();
      }
   }

   public static void updateState15() {
      GL11.glVertexPointer(3, 5126, 56, 0L);
      GL11.glColorPointer(4, 5121, 56, 12L);
      GL11.glTexCoordPointer(2, 5126, 56, 16L);
      OpenGlHelper.applyIndex4(OpenGlHelper.defaultTexUnit);
      GL11.glTexCoordPointer(2, 5122, 56, 24L);
      OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit);
      GL11.glNormalPointer(5120, 56, 28L);
      GL20.glVertexAttribPointer(PreEndHandManager.count34, 2, 5126, false, 56, 32L);
      GL20.glVertexAttribPointer(PreEndHandManager.count11, 4, 5122, false, 56, 40L);
      GL20.glVertexAttribPointer(PreEndHandManager.count39, 3, 5122, false, 56, 48L);
   }

   public static void updateState10() {
      if (PreEndHandManager.enabled31) {
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager19);
      }
   }

   public static void updateState5() {
   }

   public static void applyEntityRenderer(EntityRenderer entityRenderer, float factor, int count) {
      if (!PreEndHandManager.enabled12) {
         boolean enabled = PreEndHandManager.isEnabled35();
         boolean enabled2 = PreEndHandManager.isEnabled3();
         if (!enabled || !enabled2) {
            PreEndHandManager.updateState26();
            PreEndHandManager.setEnabledByFlag(false);
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            PreEndHandManager.applyFlag(enabled, enabled2);
            entityRenderer.applyFloat(factor, count, true, false, false);
            PreEndHandManager.updateState28();
            PreEndHandManager.applyFlagByFlag(!enabled, !enabled2);
            PreEndHandManager.applyFlag(false, false);
         }
      }
   }

   public static void updateState() {
      if (PreEndHandManager.enabled31) {
         if (PreEndHandManager.isEnabled29() && PreEndHandManager.isActive8()) {
            PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager17);
         } else {
            PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager29);
         }
      } else {
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager6);
      }
   }

   public static void updateState6() {
      PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager31);
   }

   public static void updateState3() {
   }

   public static boolean matchesTileEntityAirportal(TileEntityAirportal tileEntityAirportal, double posX, double posY, double posZ, float factor, int count, float factor2) {
      if (!PreEndHandManager.enabled12 && PreEndHandManager.noneManager7.getCount() == 0) {
         return false;
      } else {
         GlStateManager.applyState3();
         BuildManager.getTextureManagerAsTextureManager().applyResourceLocation3(resourceLocation);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry5);
         float factor3 = 0.5F;
         float factor4 = factor3 * 0.15F;
         float factor5 = factor3 * 0.3F;
         float factor6 = factor3 * 0.4F;
         float factor7 = 0.0F;
         float factor8 = 0.2F;
         float factor9 = (float)(System.currentTimeMillis() % 100000L) / 100000.0F;
         worldRenderer.getWorldRenderer(posX, posY + (double)factor2, posZ + 1.0)
            .resolveWorldRenderer(factor4, factor5, factor6, 1.0F)
            .getWorldRenderer6((double)(factor7 + factor9), (double)(factor7 + factor9))
            .resolveWorldRenderer(240, 240)
            .updateState();
         worldRenderer.getWorldRenderer(posX + 1.0, posY + (double)factor2, posZ + 1.0)
            .resolveWorldRenderer(factor4, factor5, factor6, 1.0F)
            .getWorldRenderer6((double)(factor7 + factor9), (double)(factor8 + factor9))
            .resolveWorldRenderer(240, 240)
            .updateState();
         worldRenderer.getWorldRenderer(posX + 1.0, posY + (double)factor2, posZ)
            .resolveWorldRenderer(factor4, factor5, factor6, 1.0F)
            .getWorldRenderer6((double)(factor8 + factor9), (double)(factor8 + factor9))
            .resolveWorldRenderer(240, 240)
            .updateState();
         worldRenderer.getWorldRenderer(posX, posY + (double)factor2, posZ)
            .resolveWorldRenderer(factor4, factor5, factor6, 1.0F)
            .getWorldRenderer6((double)(factor8 + factor9), (double)(factor7 + factor9))
            .resolveWorldRenderer(240, 240)
            .updateState();
         tessellator.updateState();
         GlStateManager.applyState13();
         return true;
      }
   }

   public static void applyItemRenderer(ItemRenderer itemRenderer, float factor, boolean flag) {
      PreEndHandManager.setEnabled29(true);
      GlStateManager.depthMask(true);
      if (flag) {
         GlStateManager.glDepthFunc(519);
         GL11.glPushMatrix();
         IntBuffer intBuffer = PreEndHandManager.intBuffer3;
         PreEndHandManager.applyIntBuffer(PreEndHandManager.intBuffer);
         PreEndHandManager.enabled38 = true;
         itemRenderer.applyFloatByFloat(factor);
         PreEndHandManager.enabled38 = false;
         PreEndHandManager.applyIntBuffer(intBuffer);
         GL11.glPopMatrix();
      }

      GlStateManager.glDepthFunc(515);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      itemRenderer.applyFloatByFloat(factor);
      PreEndHandManager.setEnabled29(false);
   }

   public static void updateState14() {
      if (PreEndHandManager.enabled31) {
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager14);
         if (PreEndHandManager.noneManager14.getCount() == PreEndHandManager.noneManager19.getCount()) {
            PreEndHandManager.applyIntBuffer(PreEndHandManager.intBuffer7);
            GlStateManager.depthMask(false);
         }
      }
   }

   public static void applyPreMotionConsumer(PreMotionConsumer preMotionConsumer, double posX, double posY, double posZ) {
      preMotionConsumer.processDouble(posX, posY, posZ);
   }

   public static void applyBlockSolidType(BlockSolidType blockSolidType) {
      if (PreEndHandManager.matchesBlockSolidType(blockSolidType)) {
         GlStateManager.disableAlpha();
      }

      if (OpenGlHelper.isActive()) {
         GL11.glEnableClientState(32885);
         GL20.glEnableVertexAttribArray(PreEndHandManager.count34);
         GL20.glEnableVertexAttribArray(PreEndHandManager.count11);
         GL20.glEnableVertexAttribArray(PreEndHandManager.count39);
      }
   }

   public static void updateState16() {
      if (PreEndHandManager.enabled31) {
         PreEndHandManager.enabled40 = true;
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager19);
      }
   }
}
