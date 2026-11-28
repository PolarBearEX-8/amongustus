package net.augustus.ui.widgets;

import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;

public class ScrollBar {
   private double dragOffsetY;
   public double rightX;
   private double scrollSpeed;
   public double viewportHeight;
   private double thumbHeight;
   public final double itemHeight;
   private long lastUpdateNanos;
   private double pendingScrollDelta;
   public double leftX;
   public final int contentPadding;
   public int bottomY;
   private boolean dragging;
   public double thumbTopY;
   public int topY;
   public int itemCount;
   public double scrollOffset;

   public void clampScrollOffset() {
      this.scrollOffset = MathHelper.clamp_double(this.scrollOffset, Math.min(0.0, -((double)this.itemCount * this.itemHeight - (double)(this.bottomY - this.topY))), 0.0);
   }

   public void recalculateThumbMetrics() {
      double d = (double)this.itemCount * this.itemHeight + 0.0;
      double d2 = (double)(this.bottomY - this.topY);
      if (d2 < d) {
         double d3 = d2 / d;
         double d4 = d3 * d2;
         this.thumbTopY = -(this.scrollOffset / d3 * d3 * d3) + (double)this.topY;
         this.thumbHeight = d4;
         this.viewportHeight = d2;
      }
   }

   public void resetDefaultBounds() {
      this.setBounds(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() / 2 + 150, (byte)40, new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() / 2 + 156, new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - 40);
   }

   public void setItemCount(int n) {
      this.itemCount = n;
   }

   public boolean isPointOverThumb(double d, double d2) {
      return d < this.rightX && d >= this.leftX && d2 >= this.thumbTopY && d2 < this.thumbTopY + this.thumbHeight;
   }

   public boolean fitsWithoutScrolling() {
      return this.itemCount == 0 ? true : (double)(this.bottomY - this.topY) >= (double)this.itemCount * this.itemHeight + 0.0;
   }

   public void handleMouseDrag(double d) {
      this.recalculateThumbMetrics();
      double d2 = this.viewportHeight / ((double)this.itemCount * this.itemHeight + 0.0);
      double d3 = (double)((int)(-d / d2));
      if (this.dragging) {
         this.scrollOffset = d3 - this.dragOffsetY;
      }

      this.clampScrollOffset();
   }

   public void updateAndDraw() {
      long l = System.nanoTime() - this.lastUpdateNanos;
      double pendingScrollDelta2 = this.pendingScrollDelta;
      pendingScrollDelta2 = MathHelper.clamp_double(pendingScrollDelta2, -this.scrollSpeed * ((double)l / 1000000.0), this.scrollSpeed * ((double)l / 1000000.0));
      this.lastUpdateNanos = System.nanoTime();
      this.pendingScrollDelta -= pendingScrollDelta2;
      this.scrollOffset += pendingScrollDelta2;
      double scrollOffset2 = this.scrollOffset;
      this.clampScrollOffset();
      if (scrollOffset2 != this.scrollOffset) {
         this.pendingScrollDelta = 0.0;
      }

      if (!this.fitsWithoutScrolling()) {
         this.recalculateThumbMetrics();
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
         GlStateManager.disableAlpha();
         GlStateManager.shadeModel(7425);
         GlStateManager.disableTexture2D();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         double leftX2 = this.leftX;
         double rightX2 = this.rightX;
         double thumbTopY2 = this.thumbTopY;
         double thumbHeight2 = this.thumbHeight;
         worldRenderer.pos(leftX2, (double)this.bottomY, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
         worldRenderer.pos(rightX2, (double)this.bottomY, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
         worldRenderer.pos(rightX2, (double)this.topY, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
         worldRenderer.pos(leftX2, (double)this.topY, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
         instance.draw();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         worldRenderer.pos(leftX2, thumbTopY2 + thumbHeight2, 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
         worldRenderer.pos(rightX2, thumbTopY2 + thumbHeight2, 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
         worldRenderer.pos(rightX2, thumbTopY2, 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
         worldRenderer.pos(leftX2, thumbTopY2, 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
         instance.draw();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         worldRenderer.pos(leftX2, thumbTopY2 + thumbHeight2 - 1.0, 0.0).tex(0.0, 1.0).color(192, 192, 192, 255).endVertex();
         worldRenderer.pos(rightX2 - 1.0, thumbTopY2 + thumbHeight2 - 1.0, 0.0).tex(1.0, 1.0).color(192, 192, 192, 255).endVertex();
         worldRenderer.pos(rightX2 - 1.0, thumbTopY2, 0.0).tex(1.0, 0.0).color(192, 192, 192, 255).endVertex();
         worldRenderer.pos(leftX2, thumbTopY2, 0.0).tex(0.0, 0.0).color(192, 192, 192, 255).endVertex();
         instance.draw();
         GlStateManager.enableTexture2D();
         GlStateManager.shadeModel(7424);
         GlStateManager.enableAlpha();
         GlStateManager.disableBlend();
      }
   }

   public double getClampedScrollOffset() {
      return MathHelper.clamp_double(this.scrollOffset, Math.min(0.0, -((double)this.itemCount * this.itemHeight - (double)(this.bottomY - this.topY))), 0.0);
   }

   public void setBounds(int n, int n2, int n3, int n4) {
      this.leftX = (double)n;
      this.topY = n2;
      this.rightX = (double)n3;
      this.bottomY = n4;
      this.recalculateThumbMetrics();
   }

   public void scrollToBottom() {
      this.scrollOffset = MathHelper.clamp_double(-9999999.0, Math.min(0.0, -((double)this.itemCount * this.itemHeight - (double)(this.bottomY - this.topY))), 0.0);
   }

   public boolean isAtBottom() {
      return this.scrollOffset == MathHelper.clamp_double(-9999999.0, Math.min(0.0, -((double)this.itemCount * this.itemHeight - (double)(this.bottomY - this.topY))), 0.0);
   }

   public void handleMouseClick(double d, double d2) {
      this.recalculateThumbMetrics();
      double d3 = this.viewportHeight / ((double)this.itemCount * this.itemHeight + 0.0);
      double d4 = (double)((int)(-d2 / d3));
      if (this.dragging) {
         this.dragging = false;
      } else if (this.isPointOverThumb(d, d2)) {
         this.dragging = true;
         this.dragOffsetY = d4 - this.getClampedScrollOffset();
      }

      this.clampScrollOffset();
   }

   public ScrollBar(int n) {
       super();
      this.contentPadding = 0;
      this.itemHeight = (double)n;
      this.resetDefaultBounds();
   }

   public void handleMouseWheel() {
      int n = Math.round((float)Augustus.getInstance().getMouseWheelTracker().consumeDelta() / 3.0F);
      this.pendingScrollDelta += (double)n;
      if (n != 0) {
         this.scrollSpeed = Math.abs(this.pendingScrollDelta / 200.0);
      }

      this.scrollOffset = MathHelper.clamp_double(this.scrollOffset, Math.min(0.0, -((double)this.itemCount * this.itemHeight - (double)(this.bottomY - this.topY))), 0.0);
   }

   public void setLeftX(double d) {
      this.leftX = d;
   }
}
