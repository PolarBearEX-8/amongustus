package net.augustus.settings;

import net.augustus.clickgui.components.GraphEditorComponent;
import net.augustus.clickgui.graph.GraphPointBuilder;
import net.augustus.clickgui.graph.LinearGraphRenderer;
import net.augustus.font.CustomFontUtil;
import net.augustus.settings.graph.GraphCurveRenderer;
import net.augustus.settings.graph.GraphPoint;
import net.augustus.settings.graph.GraphRenderResult;
import net.augustus.settings.property.GraphPointListProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.augustus.modules.Module;

public class GraphSetting extends Setting {
   private final GraphEditorComponent editorComponent;
   private double maxY;
   private GraphCurveRenderer curveRenderer;
   private GraphPointBuilder pointBuilder;
   private double minY;
   private int renderedHeight;
   private double minX;
   private double maxX;
   private final List defaultCoordinates;
   private final List points;
   private final GraphPointListProperty p;

   @Override
   public void onGuiClosed() {
      this.editorComponent.onClose();
   }

   public void setMinY(double d) {
      this.minY = d;
   }

   public void setMaxY(double d) {
      this.maxY = d;
   }

   @Override
   public void render(int n, int n2, int n3, int n4, int n5, int n6, CustomFontUtil customFontUtil, boolean flag) {
      super.render(n, n2, n3, n4, n5, n6, customFontUtil, flag);
      if (flag) {
         GraphRenderResult graphRenderResult = this.editorComponent.drawGraph(n3, n4, n, n2, customFontUtil, this);
         this.renderedHeight = graphRenderResult.getHeight();
         this.setFocused(graphRenderResult.isDragging());
         this.contentWidth = graphRenderResult.getRightX();
      }
   }

   public List getPoints() {
      return this.points;
   }

   @Override
   public int getHeight() {
      return this.renderedHeight;
   }

   public GraphSetting(Module module, String str, List list, double d, double d2, double d3, double d4) {
       this(module, str, list, d, d2, d3, d4, null, null);
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      this.editorComponent.mouseReleased(mouseX, mouseY, state);
   }

   public GraphCurveRenderer getCurveRenderer() {
      return this.curveRenderer;
   }

   public GraphPointBuilder getPointBuilder() {
      return this.pointBuilder;
   }

   public double getMinX() {
      return this.minX;
   }

   public GraphSetting withCurveRenderer(GraphCurveRenderer graphCurveRenderer) {
      this.curveRenderer = graphCurveRenderer;
      return this;
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      if (this.editorComponent.dragPoint(mouseX, mouseY, clickedMouseButton, this)) {
         this.notifyValueChanged();
         this.notifyUserChanged();
      }
   }

   public double getMaxY() {
      return this.maxY;
   }

   public double getMinY() {
      return this.minY;
   }

   public GraphSetting(Module module, String str, List list, double d, double d2, double d3, double d4, SettingGroup doubleValue, GraphPointBuilder graphPointBuilder) {
       super(module, doubleValue, str);
      this.editorComponent = new GraphEditorComponent();
      this.renderedHeight = 25;
      this.points = (List)(list != null ? list : new ArrayList());
      this.defaultCoordinates = new ArrayList(this.points.size());

      for (GraphPoint graphPoint : (Iterable<GraphPoint>)(this.points)) {
         this.defaultCoordinates.add(new double[]{graphPoint.getX(), graphPoint.getY()});
      }

      this.pointBuilder = graphPointBuilder != null ? graphPointBuilder : new GraphPointBuilder();
      this.p = new GraphPointListProperty(this, "P", this.defaultCoordinates);
      this.minX = d;
      this.maxX = d2;
      this.minY = d3;
      this.maxY = d4;
      this.curveRenderer = new LinearGraphRenderer();
   }

   public List getUnmodifiablePoints() {
      return Collections.unmodifiableList(this.points);
   }

   public GraphSetting withPointBuilder(GraphPointBuilder graphPointBuilder) {
      this.pointBuilder = graphPointBuilder != null ? graphPointBuilder : new GraphPointBuilder();
      return this;
   }

   public void setMaxX(double d) {
      this.maxX = d;
   }

   public void setMinX(double d) {
      this.minX = d;
   }

   public GraphSetting(Module module, String str, List list, double d, double d2, double d3, double d4, SettingGroup doubleValue) {
       this(module, str, list, d, d2, d3, d4, doubleValue, null);
   }

   public double getMaxX() {
      return this.maxX;
   }

   @Override
   public void renderTooltip() {
      this.drawDescriptionTooltip(this.editorComponent.getComponentWidth());
   }

   @Override
   public void resetToDefault() {
      this.points.clear();

      for (double[] doubles : (Iterable<double[]>)(this.defaultCoordinates)) {
         this.points.add(this.getPointBuilder().buildAt(doubles[0], doubles[1]));
      }
   }

   public GraphSetting(Module module, String str, List list, double d, double d2, double d3, double d4, GraphPointBuilder graphPointBuilder) {
       this(module, str, list, d, d2, d3, d4, null, graphPointBuilder);
   }

   @Override
   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      return this.editorComponent.selectPointAt(mouseX, mouseY, mouseButton, this);
   }
}
