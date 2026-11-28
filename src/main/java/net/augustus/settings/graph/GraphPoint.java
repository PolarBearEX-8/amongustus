package net.augustus.settings.graph;

public class GraphPoint {
   private double y;
   private double x;
   private final boolean draggableY;
   private final boolean draggableX;
   private boolean highlightZeroY;
   private Runnable onChangeCallback;

   public double getY() {
      return this.y;
   }

   public Runnable getOnChangeCallback() {
      return this.onChangeCallback;
   }

   public void setX(double d) {
      this.x = d;
   }

   public boolean isHighlightZeroY() {
      return this.highlightZeroY;
   }

   public double getX() {
      return this.x;
   }

   public void setY(double d) {
      this.y = d;
   }

   public boolean isDraggableX() {
      return this.draggableX;
   }

   public void setYAndNotify(double yandnotify) {
      this.y = yandnotify;
      if (this.onChangeCallback != null) {
         this.onChangeCallback.run();
      }
   }

   public void setXAndNotify(double xandnotify) {
      this.x = xandnotify;
      if (this.onChangeCallback != null) {
         this.onChangeCallback.run();
      }
   }

   public boolean isDraggableY() {
      return this.draggableY;
   }

   public GraphPoint(Runnable runnable, double d, double d2, boolean flag, boolean flag2, boolean flag3) {
      this.onChangeCallback = runnable;
      this.x = d;
      this.y = d2;
      this.draggableX = flag;
      this.draggableY = flag2;
      this.highlightZeroY = flag3;
   }

   public GraphPoint(double d, double d2) {
      this(null, d, d2, true, true, false);
   }



}
