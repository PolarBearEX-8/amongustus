package net.augustus.settings.graph;

public class GraphRenderResult {
   private final boolean dragging;
   private final int height;
   private final int rightX;

   public boolean isDragging() {
      return this.dragging;
   }

   public GraphRenderResult(int n, int n2, boolean flag) {
      this.height = n;
      this.rightX = n2;
      this.dragging = flag;
   }

   public int getHeight() {
      return this.height;
   }

   public int getRightX() {
      return this.rightX;
   }
}
