package net.augustus.clickgui.components;

import net.augustus.ClientManagers;
import net.augustus.clickgui.graph.GraphEditorCanvasContext;
import net.augustus.clickgui.graph.LinearGraphRenderer;
import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import net.augustus.settings.GraphSetting;
import net.augustus.settings.graph.GraphCurveRenderer;
import net.augustus.settings.graph.GraphPoint;
import net.augustus.settings.graph.GraphRenderResult;
import net.augustus.settings.graph.GraphViewport;
import java.awt.Color;
import java.util.List;
import java.util.Locale;
import org.lwjgl.opengl.GL11;

public class GraphEditorComponent extends GuiComponent {
   private int canvasY;
   private int totalHeight = 25;
   private static final int POINT_HALF_SIZE = 4;
   private static final int CANVAS_HEIGHT = 200;
   private int totalRightX = 150;
   private static final int AXIS_LABEL_GAP = 2;
   private int canvasX;
   private static final int CANVAS_WIDTH = 800;
   private int draggedPointIndex;
   private int componentWidth = 50;
   private static final int TICK_LENGTH = 3;

   public void mouseReleased(int mouseX2, int mouseY2, int state) {
      if (state == 0) {
         this.draggedPointIndex = -1;
      }
   }

   public boolean dragPoint(int n, int n2, int n3, GraphSetting graphSetting) {
      if (n3 != 0) {
         return false;
      } else if (this.draggedPointIndex >= 0 && this.draggedPointIndex < graphSetting.getPoints().size()) {
         GraphPoint graphPoint = (GraphPoint)graphSetting.getPoints().get(this.draggedPointIndex);
         double x2 = graphPoint.getX();
         double y2 = graphPoint.getY();
         if (graphPoint.isDraggableX()) {
            graphPoint.setXAndNotify(clamp(this.screenXToValueX(graphSetting, n), graphSetting.getMinX(), graphSetting.getMaxX()));
         }

         if (graphPoint.isDraggableY()) {
            graphPoint.setYAndNotify(clamp(this.screenYToValueY(graphSetting, n2), graphSetting.getMinY(), graphSetting.getMaxY()));
         }

         return x2 != graphPoint.getX() || y2 != graphPoint.getY();
      } else {
         return false;
      }
   }

   private void drawCurve(GraphSetting graphSetting) {
      Object curveRenderer = graphSetting.getCurveRenderer();
      if (curveRenderer == null) {
         curveRenderer = new LinearGraphRenderer();
      }

      GraphEditorCanvasContext graphEditorCanvasContext = new GraphEditorCanvasContext(this, graphSetting);
      List unmodifiablePoints = graphSetting.getUnmodifiablePoints();
      ((GraphCurveRenderer)curveRenderer).renderCurve(unmodifiablePoints, (GraphViewport)graphEditorCanvasContext);
   }

   private static double computeTickStep(double d, double d2, int n, int n2) {
      double d3 = d2 - d;
      if (d3 > 0.0 && n > 0) {
         int n3 = (int)Math.round((double)n / (double)Math.max(1, n2));
         n3 = (int)clamp((double)n3, 2.0, 10.0);
         return niceStepNumber(d3 / ((double)n3 - 1.0));
      } else {
         return 1.0;
      }
   }

   public static int valueYToScreenYAccess(GraphEditorComponent graphEditorComponent, GraphSetting graphSetting, double d) {
      return graphEditorComponent.valueYToScreenY(graphSetting, d);
   }

   public void onClose() {
      this.draggedPointIndex = -1;
   }

   private static double niceStepNumber(double d) {
      if (!(d > 0.0)) {
         return 1.0;
      } else {
         double d2 = Math.floor(Math.log10(d));
         double d3 = d / Math.pow(10.0, d2);
         double d4;
         if (d3 <= 1.0) {
            d4 = 1.0;
         } else if (d3 <= 2.0) {
            d4 = 2.0;
         } else if (d3 <= 5.0) {
            d4 = 5.0;
         } else {
            d4 = 10.0;
         }

         return d4 * Math.pow(10.0, d2);
      }
   }

   private double screenXToValueX(GraphSetting graphSetting, int n) {
      double d = (double)(n - this.canvasX) / 799.0;
      d = clamp(d, 0.0, 1.0);
      return graphSetting.getMinX() + d * (graphSetting.getMaxX() - graphSetting.getMinX());
   }

   private int valueXToScreenX(GraphSetting graphSetting, double d) {
      double minX = graphSetting.getMinX();
      double d2 = graphSetting.getMaxX() - minX;
      if (d2 == 0.0) {
         return this.canvasX;
      } else {
         double d3 = (d - minX) / d2;
         d3 = clamp(d3, 0.0, 1.0);
         return (int)Math.round((double)this.canvasX + d3 * 799.0);
      }
   }

   private int drawYAxisLabels(GraphSetting graphSetting, CustomFontUtil customFontUtil, int n) {
      double minY = graphSetting.getMinY();
      double maxY = graphSetting.getMaxY();
      if (!(maxY > minY)) {
         return 0;
      } else {
         double d = computeTickStep(minY, maxY, (short)200, (byte)22);
         if (!(d > 0.0)) {
            return 0;
         } else {
            float fontHeight = customFontUtil.getFontHeight();
            int n2 = this.canvasX + 800 + 4;
            int n3 = 0;
            double d2 = 0.0 / 0.0;

            for (double i = firstTickValue(minY, d); i <= maxY; i += d) {
               double d3 = snapNearZero(i, d);
               int n4 = this.valueYToScreenY(graphSetting, d3);
               if (Double.isNaN(d2) || !(Math.abs((double)n4 - d2) < (double)(fontHeight + 2.0F))) {
                  String str = formatAxisValue(d3, d);
                  n3 = Math.max(n3, (int)Math.ceil((double)customFontUtil.getStringWidth(str)));
                  float f = (float)n4 - fontHeight / 2.0F;
                  f = (float)clamp((double)f, (double)((float)(this.canvasY + 1)), (double)((float)(this.canvasY + 200 - 1) - fontHeight));
                  customFontUtil.drawString(str, (double)n2, (double)((int)f), n);
                  d2 = (double)n4;
               }
            }

            return n3 + 6;
         }
      }
   }

   private double screenYToValueY(GraphSetting graphSetting, int n) {
      double d = (double)(n - this.canvasY) / 199.0;
      d = clamp(d, 0.0, 1.0);
      double d2 = 1.0 - d;
      return graphSetting.getMinY() + d2 * (graphSetting.getMaxY() - graphSetting.getMinY());
   }

   private void drawControlPoints(GraphSetting graphSetting, int n) {
      List points = graphSetting.getPoints();
      int colorInt = ClientManagers.moduleManager.clickGui.hoverTextColor.getColorInt((byte)0);

      for (int i = 0; i < points.size(); i++) {
         GraphPoint graphPoint = (GraphPoint)points.get(i);
         int n2 = this.valueXToScreenX(graphSetting, graphPoint.getX());
         int n3 = this.valueYToScreenY(graphSetting, graphPoint.getY());
         int mouseY2 = this.mouseY;
         boolean isNearPoint = this.isNearPoint(n2, n3, this.mouseX, mouseY2);
         int n4 = (this.draggedPointIndex == i) ? colorInt : (isNearPoint ? RenderUtil.adjustColorBrightness(n, 0.10000000149011612) : n);
         if (graphPoint.getY() == 0.0 && graphPoint.isHighlightZeroY()) {
            n4 = Color.RED.getRGB();
         }

         RenderUtil.drawRect(n2 - 4, n3 - 4, n2 + 4, n3 + 4, n4);
      }
   }

   public static int getCanvasYAccess(GraphEditorComponent graphEditorComponent) {
      return graphEditorComponent.canvasY;
   }

   public boolean selectPointAt(int n, int n2, int n3, GraphSetting graphSetting) {
      if (n3 != 0) {
         return false;
      } else if (!RenderUtil.isHovered((float)this.canvasX, (float)this.canvasY, 800.0F, 200.0F, n, n2)) {
         return false;
      } else {
         int n4 = -1;
         double d = 1.7976931348623157E308;
         List points = graphSetting.getPoints();

         for (int i = 0; i < points.size(); i++) {
            GraphPoint graphPoint = (GraphPoint)points.get(i);
            int n5 = this.valueXToScreenX(graphSetting, graphPoint.getX());
            int n6 = this.valueYToScreenY(graphSetting, graphPoint.getY());
            double d3 = (double)(n - n5);
            double d4 = (double)(n2 - n6);
            double d5 = d3 * d3 + d4 * d4;
            double d6 = 7.0;
            if (d5 <= d6 * d6 && d5 < d) {
               d = d5;
               n4 = i;
            }
         }

         if (n4 != -1) {
            this.draggedPointIndex = n4;
            return true;
         } else {
            return false;
         }
      }
   }

   public GraphEditorComponent() {
      this.draggedPointIndex = -1;
   }

   public static int valueXToScreenXAccess(GraphEditorComponent graphEditorComponent, GraphSetting graphSetting, double d) {
      return graphEditorComponent.valueXToScreenX(graphSetting, d);
   }

   private boolean isNearPoint(int n, int n2, int n3, int n4) {
      double d = (double)(n3 - n);
      double d2 = (double)(n4 - n2);
      double d3 = 7.0;
      return d * d + d2 * d2 <= d3 * d3;
   }

   private void drawGridAndTicks(GraphSetting graphSetting, int n, int n2) {
      double minX = graphSetting.getMinX();
      double maxX = graphSetting.getMaxX();
      double minY = graphSetting.getMinY();
      double maxY = graphSetting.getMaxY();
      if (maxX > minX && maxY > minY) {
         double d = computeTickStep(minX, maxX, (short)800, (byte)55);
         double d2 = computeTickStep(minY, maxY, 200, 22);

         for (double i = firstTickValue(minX, d); i <= maxX + d * 0.5; i += d) {
            int n3 = this.valueXToScreenX(graphSetting, snapNearZero(i, d));
            RenderUtil.drawRect(n3, this.canvasY + 1, n3 + 1, this.canvasY + 200 - 1, n2);
            RenderUtil.drawRect(n3, this.canvasY + 200 - 1 - 3, n3 + 1, this.canvasY + 200 - 1, n);
         }

         for (double j = firstTickValue(minY, d2); j <= maxY + d2 * 0.5; j += d2) {
            int n4 = this.valueYToScreenY(graphSetting, snapNearZero(j, d2));
            RenderUtil.drawRect(this.canvasX + 1, n4, this.canvasX + 800 - 1, n4 + 1, n2);
            RenderUtil.drawRect(this.canvasX + 1, n4, this.canvasX + 1 + 3, n4 + 1, n);
         }
      }
   }

   private static double clamp(double value, double min, double max) {
      return value < min ? min : Math.min(value, max);
   }

   private static double firstTickValue(double d, double d2) {
      if (!(d2 > 0.0)) {
         return d;
      } else {
         double d3 = Math.ceil(d / d2) * d2;
         if (d3 < d) {
            d3 += d2;
         }

         return d3;
      }
   }

   private int valueYToScreenY(GraphSetting graphSetting, double d) {
      double minY = graphSetting.getMinY();
      double d2 = graphSetting.getMaxY() - minY;
      if (d2 == 0.0) {
         return this.canvasY + 200;
      } else {
         double d3 = (d - minY) / d2;
         d3 = clamp(d3, 0.0, 1.0);
         return (int)Math.round((double)this.canvasY + (1.0 - d3) * 199.0);
      }
   }

   private static String formatAxisValue(double d, double d2) {
      double d3 = Math.abs(d2);
      int n;
      if (!(d3 > 0.0)) {
         n = 2;
      } else {
         n = (int)Math.ceil(-Math.log10(d3));
         n = (int)clamp((double)n, 0.0, 6.0);
      }

      double d4 = Math.rint(d);
      return Math.abs(d - d4) <= Math.max(1.0E-9, d3 * 1.0E-6)
         ? String.format(Locale.US, "%.0f", d4)
         : String.format(Locale.US, "%." + n + "f", d);
   }

   public static int getCanvasXAccess(GraphEditorComponent graphEditorComponent) {
      return graphEditorComponent.canvasX;
   }

   private void drawXAxisLabels(GraphSetting graphSetting, CustomFontUtil customFontUtil, int n) {
      double minX = graphSetting.getMinX();
      double maxX = graphSetting.getMaxX();
      if (maxX > minX) {
         double d = computeTickStep(minX, maxX, (short)800, (byte)55);
         float fontHeight = customFontUtil.getFontHeight();
         int n2 = this.canvasY + 200 + 2;
         double d2 = 0.0 / 0.0;

         for (double i = firstTickValue(minX, d); i <= maxX + d * 0.5; i += d) {
            double d3 = snapNearZero(i, d);
            int n3 = this.valueXToScreenX(graphSetting, d3);
            String str = formatAxisValue(d3, d);
            float stringWidth = customFontUtil.getStringWidth(str);
            float f = (float)n3 - stringWidth / 2.0F;
            if (Double.isNaN(d2) || !(Math.abs((double)f - d2) < (double)(stringWidth + 6.0F))) {
               f = (float)clamp((double)f, (double)((float)(this.canvasX + 1)), (double)((float)(this.canvasX + 800 - 1) - stringWidth));
               customFontUtil.drawString(str, (double)((int)f), (double)n2, n);
               d2 = (double)f;
            }
         }
      }
   }

   private static double snapNearZero(double d, double d2) {
      return Math.abs(d) < Math.max(1.0E-12, d2 * 1.0E-9) ? 0.0 : d;
   }

   public int getComponentWidth() {
      return this.componentWidth;
   }

   public GraphRenderResult drawGraph(int n, int n2, int n3, int n4, CustomFontUtil customFontUtil, GraphSetting graphSetting) {
      super.updateLayout(n, n2, n3, n4, customFontUtil);
      String str = graphSetting.getDisplayName() + ":";
      int colorInt = ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0);
      customFontUtil.drawString(str, (double)n, (double)n2, colorInt);
      int n5 = (int)((float)n2 + customFontUtil.getFontHeight() + 6.0F);
      this.canvasX = n;
      this.canvasY = n5;
      GL11.glEnable(2848);
      int colorInt2 = ClientManagers.moduleManager.clickGui.headerColor.getColorInt((byte)0);
      RenderUtil.drawRect(this.canvasX, this.canvasY, this.canvasX + 800, this.canvasY + 200, colorInt2);
      RenderUtil.drawRect(this.canvasX, this.canvasY, this.canvasX + 800, this.canvasY + 1, colorInt2);
      RenderUtil.drawRect(this.canvasX, this.canvasY + 200 - 1, this.canvasX + 800, this.canvasY + 200, colorInt2);
      RenderUtil.drawRect(this.canvasX, this.canvasY, this.canvasX + 1, this.canvasY + 200, colorInt2);
      RenderUtil.drawRect(this.canvasX + 800 - 1, this.canvasY, this.canvasX + 800, this.canvasY + 200, colorInt2);
      this.drawGridAndTicks(graphSetting, colorInt, colorInt2);
      this.drawCurve(graphSetting);
      this.drawControlPoints(graphSetting, colorInt);
      int n6 = this.drawYAxisLabels(graphSetting, customFontUtil, colorInt);
      this.drawXAxisLabels(graphSetting, customFontUtil, colorInt);
      this.totalHeight = this.canvasY + 200 + 18 + 5 - n2;
      this.totalRightX = this.canvasX + 800 + 10 + n6;
      this.componentWidth = (int)Math.max(customFontUtil.getStringWidth(str), 800.0F);
      GL11.glDisable(2848);
      return new GraphRenderResult(this.totalHeight, this.totalRightX, this.draggedPointIndex != -1);
   }

}
