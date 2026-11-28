package net.augustus.ui.widgets;

import net.augustus.Augustus;
import net.augustus.render.RenderUtil;
import net.augustus.utils.input.KeyboardInputUtil;
import java.io.IOException;
import net.minecraft.util.MathHelper;

public class SmoothScrollBar {
   private int lastDragMouseX;
   private boolean dragging;
   private int lastDragMouseY;
   private double scrollVelocity;
   private double pendingScrollAmount;
   private boolean vertical;
   private long lastFrameNanos;
   private int thumbTopY;
   private int thumbBottomY;
   private boolean wasDragging;
   private int thumbRightX;
   private boolean visible;
   private int thumbLeftX;
   private double subpixelRemainder = 0.0;

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (this.visible && mouseButton == 0) {
         if (RenderUtil.isPointInBounds(mouseX, mouseY, (double)this.thumbLeftX, (double)this.thumbTopY, (double)this.thumbRightX, (double)this.thumbBottomY)) {
            this.dragging = true;
            this.lastDragMouseX = mouseX;
            this.lastDragMouseY = mouseY;
         }
      }
   }

   public int updateScrollOffset(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10) {
      int n11 = n3 - n2;
      if (n <= n11) {
         this.pendingScrollAmount = 0.0;
         return 0;
      } else {
         int n12 = Math.round((float)Augustus.getInstance().getMouseWheelTracker().lastDelta / 3.0F);
         if (!RenderUtil.isHovered((float)n5, (float)n6, (float)n7, (float)n8, n9, n10) || KeyboardInputUtil.isCtrlDown()) {
            n12 = 0;
         }

         if (this.dragging != this.wasDragging) {
            this.subpixelRemainder = 0.0;
         }

         if (this.dragging) {
            if (this.vertical) {
               double d = (double)(this.lastDragMouseY - n10) * ((double)n / (double)n11);
               n12 = (int)(d + this.subpixelRemainder);
               this.subpixelRemainder += d - (double)n12;
               this.lastDragMouseY = n10;
            } else {
               double d2 = (double)(this.lastDragMouseX - n9) * ((double)n / (double)n11);
               n12 = (int)(d2 + this.subpixelRemainder);
               this.subpixelRemainder += d2 - (double)n12;
               this.lastDragMouseX = n9;
            }
         }

         this.pendingScrollAmount += (double)n12;
         if (n12 != 0) {
            this.scrollVelocity = Math.abs(this.pendingScrollAmount / 200.0);
         }

         long l = System.nanoTime() - this.lastFrameNanos;
         long pendingScrollAmount2 = (long)this.pendingScrollAmount;
         double d3 = MathHelper.clamp_double((double)pendingScrollAmount2, -this.scrollVelocity * ((double)l / 1000000.0), this.scrollVelocity * ((double)l / 1000000.0));
         pendingScrollAmount2 = (long)(d3 + this.subpixelRemainder);
         if (!this.dragging) {
            this.subpixelRemainder += d3 - (double)pendingScrollAmount2;
         }

         this.lastFrameNanos = System.nanoTime();
         int n13 = n - n11;
         if (this.dragging) {
            pendingScrollAmount2 = (long)this.pendingScrollAmount;
            d3 = this.pendingScrollAmount;
         }

         int n14 = (int)pendingScrollAmount2;
         pendingScrollAmount2 = (long)(MathHelper.clamp_int((int)((long)n4 + pendingScrollAmount2), -n13, 0) - n4);
         this.pendingScrollAmount = (long)n14 != pendingScrollAmount2 ? 0.0 : this.pendingScrollAmount - d3;
         if (pendingScrollAmount2 != (long)n14 && n12 != 0) {
            this.resetPendingScroll();
         }

         this.wasDragging = this.dragging;
         return (int)((long)n4 + pendingScrollAmount2);
      }
   }

   public void resetPendingScroll() {
      this.pendingScrollAmount = 0.0;
   }

   public void mouseReleased(int mouseX, int mouseY, int state) {
      this.dragging = false;
      if (this.visible) {
         ;
      }
   }

   public SmoothScrollBar() {
      this.vertical = false;
   }

   public boolean drawHorizontalBar(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
      this.vertical = false;
      this.thumbLeftX = n;
      this.thumbTopY = n2;
      this.thumbRightX = n3;
      this.thumbBottomY = n4;
      int n8 = n3 - n;
      if (n6 <= n8) {
         this.visible = false;
         this.dragging = false;
         return false;
      } else {
         double d = (double)n8 / 100.0;
         int n9 = (int)((((double)(n5 - n)) / (double)n6 * 100.0) * d) + n;
         int n10 = (int)(((double)n8 / (double)n6 * 100.0) * d);
         this.thumbLeftX = n9;
         this.thumbRightX = n9 + n10;
         RenderUtil.drawRoundedRect((float)this.thumbLeftX, (float)this.thumbTopY, (float)this.thumbRightX, (float)this.thumbBottomY, 4.0F, 1, n7);
         this.visible = true;
         return true;
      }
   }

   public boolean drawVerticalBar(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
      this.vertical = true;
      int n8 = n4 - n2;
      if (n6 <= n8) {
         this.visible = false;
         this.dragging = false;
         return false;
      } else {
         double d = (double)n8 / 100.0;
         int n9 = (int)((((double)(n5 - n2)) / (double)n6 * 100.0) * d) + n2;
         int n10 = (int)(((double)n8 / (double)n6 * 100.0) * d);
         this.thumbLeftX = n;
         this.thumbTopY = n9;
         this.thumbRightX = n3;
         this.thumbBottomY = n9 + n10;
         RenderUtil.drawRoundedRect((float)this.thumbLeftX, (float)this.thumbTopY, (float)this.thumbRightX, (float)this.thumbBottomY, 4.0F, 1, n7);
         this.visible = true;
         return true;
      }
   }
}
