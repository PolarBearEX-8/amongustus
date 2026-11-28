package net.augustus.settings.graph;

public interface GraphViewport {
   double getMaxY();

   int getCanvasY();

   int getCanvasWidth();

   int getCanvasHeight();

   double getMinY();

   double getMinX();

   int getCanvasX();

   double getMaxX();

   int toScreenY(double d);

   int toScreenX(double d);
}
