package net.augustus.clickgui.graph;


import net.augustus.clickgui.components.GraphEditorComponent;
import net.augustus.settings.GraphSetting;
import net.augustus.settings.graph.GraphViewport;
public class GraphEditorCanvasContext implements GraphViewport {
   public final GraphSetting graphSetting;
   public final GraphEditorComponent editorComponent;

   @Override
   public double getMaxX() {
      return this.graphSetting.getMaxX();
   }

   @Override
   public double getMaxY() {
      return this.graphSetting.getMaxY();
   }

   @Override
   public int toScreenX(double d) {
      GraphSetting graphSetting2 = this.graphSetting;
      return GraphEditorComponent.valueXToScreenXAccess(this.editorComponent, graphSetting2, d);
   }

   @Override
   public int toScreenY(double d) {
      GraphSetting graphSetting2 = this.graphSetting;
      return GraphEditorComponent.valueYToScreenYAccess(this.editorComponent, graphSetting2, d);
   }

   @Override
   public double getMinX() {
      return this.graphSetting.getMinX();
   }

   @Override
   public int getCanvasY() {
      return GraphEditorComponent.getCanvasYAccess(this.editorComponent);
   }

   public GraphEditorCanvasContext(GraphEditorComponent graphEditorComponent, GraphSetting graphSetting2) {
      this.editorComponent = graphEditorComponent;
      this.graphSetting = graphSetting2;
   }

   @Override
   public int getCanvasHeight() {
      return 200;
   }

   @Override
   public int getCanvasWidth() {
      return 800;
   }

   @Override
   public int getCanvasX() {
      return GraphEditorComponent.getCanvasXAccess(this.editorComponent);
   }

   @Override
   public double getMinY() {
      return this.graphSetting.getMinY();
   }
}
