package net.augustus.clickgui.graph;

import net.augustus.render.RenderUtil;
import net.augustus.settings.graph.GraphCurveRenderer;
import net.augustus.settings.graph.GraphPoint;
import net.augustus.settings.graph.GraphViewport;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LinearGraphRenderer implements GraphCurveRenderer {
   private float lineWidth;

   public void renderCurve(List list, GraphViewport graphViewport) {
      if (list != null && list.size() >= 2) {
         ArrayList list2 = new ArrayList(list);
         list2.sort(Comparator.comparingDouble(GraphPoint::getX));

         for (int i = 0; i < list2.size() - 1; i++) {
            GraphPoint graphPoint = (GraphPoint)list2.get(i);
            GraphPoint graphPoint2 = (GraphPoint)list2.get(i + 1);
            double x = graphPoint.getX();
            int n;
            int n2 = graphViewport.toScreenX(x);
            int n3 = graphViewport.toScreenY(graphPoint.getY());
            n = graphViewport.toScreenX(graphPoint2.getX());
            RenderUtil.drawLine(n2, n3, n, graphViewport.toScreenY(graphPoint2.getY()), this.lineWidth, Color.WHITE);
         }
      }
   }

   public LinearGraphRenderer(float f) {
      this.lineWidth = f;
   }

   public LinearGraphRenderer() {
      this.lineWidth = 2.0F;
   }
}
