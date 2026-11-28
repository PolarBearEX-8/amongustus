package net.augustus.clickgui.graph;


import net.augustus.settings.graph.GraphPoint;
public class GraphPointBuilder {
   private Runnable onChangeCallback;
   private boolean highlightZero;
   private double y;
   private double x;
   private boolean yEditable;
   private boolean xEditable;

   public GraphPointBuilder withX(double d) {
      this.x = d;
      return this;
   }

   public GraphPoint build() {
      return new GraphPoint(this.onChangeCallback, this.x, this.y, this.xEditable, this.yEditable, this.highlightZero);
   }

   public GraphPointBuilder withY(double d) {
      this.y = d;
      return this;
   }

   public GraphPointBuilder onChange(Runnable runnable) {
      this.onChangeCallback = runnable;
      return this;
   }

   public GraphPoint buildAt(double d, double d2) {
      return new GraphPoint(this.onChangeCallback, d, d2, this.xEditable, this.yEditable, this.highlightZero);
   }

   public GraphPointBuilder highlightZero(boolean flag) {
      this.highlightZero = flag;
      return this;
   }

   public GraphPointBuilder xEditable(boolean flag) {
      this.xEditable = flag;
      return this;
   }

   public GraphPointBuilder yEditable(boolean flag) {
      this.yEditable = flag;
      return this;
   }
}
