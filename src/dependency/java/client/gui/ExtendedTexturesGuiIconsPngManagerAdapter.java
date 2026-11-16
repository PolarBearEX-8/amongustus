package client.gui;

import client.event.VirtualListener;
import client.setting.GameSettings;
import client.util.CurveTracker;
import client.util.SmoothCounter;
import client.util.SplineConverter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ExtendedTexturesGuiIconsPngManagerAdapter extends TexturesGuiIconsPngManager implements VirtualListener {
   public static final ResourceLocation resourceLocation5;
   public static final ResourceLocation resourceLocation4;
   public final Minecraft mc;
   public long timestamp;
   public SplineConverter splineConverter;

   public void applyScaledResolution(ScaledResolution scaledResolution, float factor, int count, float factor2, SmoothCounter smoothCounter) {
      GlStateManager.disableLighting();
      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, factor);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
      this.applyFloat((float)(count - 91), factor2, 0, 0, 182, 22);
      if (smoothCounter.getCount() >= 0) {
         this.applyFloat((float)(count - 91 - 1 + smoothCounter.getCount() * 20), factor2 - 1.0F, 0, 22, 24, 22);
      }

      RenderHelper.enableGUIStandardItemLighting();

      for (int index = 0; index < 9; index++) {
         this.applyCurveTracker(index, scaledResolution.getScaledWidth() / 2 - 90 + index * 20 + 2, factor2 + 3.0F, factor, smoothCounter.resolveCurveTracker(index));
      }

      RenderHelper.disableStandardItemLighting();
      GlStateManager.applyState8();
      GlStateManager.enableBlend();
   }

   public boolean isActive() {
      return this.splineConverter != null;
   }

   public void applyIndex(int count) {
      this.timestamp = Minecraft.getTimestamp();
      if (this.splineConverter != null) {
         this.splineConverter.applyIndex(count);
      } else {
         this.splineConverter = new SplineConverter(this);
      }
   }

   private float getFactor() {
      return MathHelper.clamp_float((float)(this.timestamp - Minecraft.getTimestamp() + 5000L) / 2000.0F, 0.0F, 1.0F);
   }

   @Override
   public void applySplineConverter(SplineConverter splineConverter2) {
      this.splineConverter = null;
      this.timestamp = 0L;
   }

   public void renderElement() {
      this.timestamp = Minecraft.getTimestamp();
      if (this.isActive()) {
         int count = this.splineConverter.getCount();
         if (count != -1) {
            this.splineConverter.applyIndex(count);
         }
      } else {
         this.splineConverter = new SplineConverter(this);
      }
   }

   public void applyIndexByIndex(int count) {
      int count2 = this.splineConverter.getCount() + count;

      while (
         count2 >= 0
            && count2 <= 8
            && (this.splineConverter.resolveCurveTracker(count2) == SplineConverter.curveTracker || !this.splineConverter.resolveCurveTracker(count2).isEmpty())
      ) {
         count2 += count;
      }

      if (count2 >= 0 && count2 <= 8) {
         this.splineConverter.applyIndex(count2);
         this.timestamp = Minecraft.getTimestamp();
      }
   }

   public void applyScaledResolution(ScaledResolution scaledResolution, float factor) {
      if (this.splineConverter != null) {
         float factor2 = this.getFactor();
         if (factor2 <= 0.0F) {
            this.splineConverter.updateState();
         } else {
            int count = scaledResolution.getScaledWidth() / 2;
            float factor3 = this.factor;
            this.factor = -90.0F;
            float factor4 = (float)scaledResolution.getScaledHeight() - 22.0F * factor2;
            SmoothCounter smoothCounter = this.splineConverter.getSmoothCounter();
            this.applyScaledResolution(scaledResolution, factor2, count, factor4, smoothCounter);
            this.factor = factor3;
         }
      }
   }

   public void applyScaledResolutionByScaledResolution(ScaledResolution scaledResolution) {
      int count = (int)(this.getFactor() * 255.0F);
      if (count > 3 && this.splineConverter != null) {
         CurveTracker curveTracker = this.splineConverter.getCurveTracker();
         String text = curveTracker != SplineConverter.curveTracker
            ? curveTracker.getName().getString2()
            : this.splineConverter.getAuxiliaryCustom().getIChatComponent().getString2();
         if (text != null) {
            int count2 = (scaledResolution.getScaledWidth() - this.mc.fontRendererObj.getIndex(text)) / 2;
            int count3 = scaledResolution.getScaledHeight() - 35;
            GlStateManager.pushMatrix();
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            this.mc.fontRendererObj.getIndex2(text, (float)count2, (float)count3, 16777215 + (count << 24));
            GlStateManager.enableBlend();
            GlStateManager.popMatrix();
         }
      }
   }

   private void applyCurveTracker(int count, int count2, float factor, float factor2, CurveTracker curveTracker) {
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation4);
      if (curveTracker != SplineConverter.curveTracker) {
         int count3 = (int)(factor2 * 255.0F);
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)count2, factor, 0.0F);
         float factor3 = curveTracker.isEmpty() ? 1.0F : 0.25F;
         GlStateManager.color4f2(factor3, factor3, factor3, factor2);
         curveTracker.applyFloat(factor3, count3);
         GlStateManager.popMatrix();
         String text = String.valueOf(GameSettings.resolveText(this.mc.gameSettings.scorePlayerTeamArray[count].getKeyCode()));
         if (count3 > 3 && curveTracker.isEmpty()) {
            this.mc
               .fontRendererObj
               .getIndex2(text, (float)(count2 + 19 - 2 - this.mc.fontRendererObj.getIndex(text)), factor + 6.0F + 3.0F, 16777215 + (count3 << 24));
         }
      }
   }

   public ExtendedTexturesGuiIconsPngManagerAdapter(Minecraft mc2) {
      this.mc = mc2;
   }

   static {
      String[] textArray = new String[]{"textures/gui/spectator_widgets.png", "textures/gui/widgets.png"};
      resourceLocation5 = new ResourceLocation(textArray[1]);
      resourceLocation4 = new ResourceLocation(textArray[0]);
   }
}
